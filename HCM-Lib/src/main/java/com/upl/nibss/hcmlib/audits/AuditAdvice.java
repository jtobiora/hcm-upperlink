package com.upl.nibss.hcmlib.audits;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.upl.nibss.hcmlib.cache.CustomDataCacheService;
import com.upl.nibss.hcmlib.config.JWTRedisToken;
import com.upl.nibss.hcmlib.dto.UserDetail;
import com.upl.nibss.hcmlib.model.Audits;
import com.upl.nibss.hcmlib.model.Employee;
import com.upl.nibss.hcmlib.model.User;
import com.upl.nibss.hcmlib.repo.AuditRepo;
import com.upl.nibss.hcmlib.repo.UserRepo;
import com.upl.nibss.hcmlib.utils.JsonConverter;
import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Service
public class AuditAdvice {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuditRepo auditRepo;

    @Autowired
    private JWTRedisToken jwtRedisToken;

    @Autowired
    private CustomDataCacheService customDataCacheService;

    private static final Logger logger = LoggerFactory.getLogger(AuditAdvice.class);


    /*public final <T extends AbstractEntity> int deleteAll(Class<T> clazz) {
        CriteriaDelete<T> criteriaDelete = entityManager.getCriteriaBuilder().createCriteriaDelete(clazz);
        criteriaDelete.from(clazz);
        return entityManager.createQuery(criteriaDelete).executeUpdate();
    }

    public final <T extends AbstractEntity> int delete(Class clazz) {
        CriteriaDelete<T> criteriaDelete = entityManager.getCriteriaBuilder().createCriteriaDelete(clazz);
        criteriaDelete.from(clazz);
        return entityManager.createQuery(criteriaDelete).executeUpdate();
    }

    public <T> List<?> getEntities(Class clazz) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = builder.createQuery(clazz);
        Root<T> root = cq.from(clazz);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }*/

    private Object getEntity(Object entity, String id) {
        Long convertedId = null;
        try {
            convertedId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return entityManager.find(entity.getClass(), convertedId); //TODO there is an error here (Cant convert 'true' to Long)
    }

    //"execution(public * org.springframework.data.jpa.repository.JpaRepository+.*(..))"
//    @Around("execution(* com.upl.nibss.hcmlib.repo.*Repo.save(..))" + " && args(entity,..)")
//    @Around("execution(* org.springframework.data.jpa.repository.JpaRepository.save(..)) && !execution(* com.upl.nibss.hcmlib.repo.AuditRepo.*(..))" + " && args(entity,..)")
    public Object saveEntity(ProceedingJoinPoint jp, Object entity) {

        /*try {
            System.out.println("class for-name: " + Class.forName(entity.getClass().getName()));
            Class<?> the = Class.forName(entity.getClass().getCanonicalName());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        Object newEntity;
        Object oldEntity;

        String newEntityJson;
        String oldEntityJson;

        String crudAction;
//        String entityJson = JsonConverter.getJsonRecursive(entity);

        ObjectMapper objectMapper = new ObjectMapper();
        String entityJson = null;
        try {
            entityJson = objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            logger.error("Unable to parse entity to json", e);
        }

        Pair pair = getPair(entityJson);
        String id = pair.getValue();
        if(id.isEmpty()){
            oldEntityJson = "";
            crudAction = "Create";
        }else {
            oldEntity = getEntity(entity, id);
            oldEntityJson = JsonConverter.getJsonRecursive(oldEntity);
            crudAction = "Update";
        }
        try {
            newEntity = jp.proceed();
            if(newEntity == null){
                newEntityJson = "";
            }else {
                newEntityJson = JsonConverter.getJsonRecursive(newEntity);
            }
        } catch (Throwable e) {
            return null;
        }

        saveAudit(crudAction, entity, oldEntityJson, newEntityJson);
        //List<?> exitReasons = getEntities(entity.getClass());
        //System.out.println("size: " + exitReasons.size());

        return newEntity;
    }

//    @Around("execution(* org.springframework.data.jpa.repository.JpaRepository.delete(..)) && !execution(* com.upl.nibss.hcmlib.repo.AuditRepo.*(..))" + " && args(entity,..)")
    public void deleteEntity(ProceedingJoinPoint jp, Object entity) {
        Object oldEntity;

        String newEntityJson = "";
        String oldEntityJson;
        String id;

        if(entity instanceof Long){
            id = String.valueOf( entity );
        }else if(entity instanceof Iterable){
            //TODO handle auditing for multiple delete instances.
            id = "";
        }else {
            String entityJson = JsonConverter.getJsonRecursive(entity);
            Pair pair = getPair(entityJson);
            id = pair.getValue();
        }

        oldEntity = getEntity(entity, id);
        oldEntityJson = JsonConverter.getJsonRecursive(oldEntity);

        String crudAction = "Delete";

        try {
            jp.proceed();

        } catch (Throwable e) {
            System.out.println("Sorry! An error was encountered!");
        }

        saveAudit(crudAction, entity, oldEntityJson, newEntityJson);
    }

    private Pair getPair(String entityJson){

        Pair pair = new Pair();
        entityJson = entityJson.replace("{", "").replace("}", "");
        String[] parameterArray = entityJson.split(",");
        String id = "";
        String value = "";
        String[] valueArray = parameterArray[0].split(":");

        if (valueArray.length > 1){
            value = valueArray[valueArray.length - 1];
            id = valueArray[valueArray.length - 2].trim().replace("\"", "");
            if(value == null || value.isEmpty() || value.equalsIgnoreCase("null")){
                value = "";
            }
        }

        pair.setKey(id);
        pair.setValue(value);
        return pair;
    }

    private String getUserAgent() {
        try {
            HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if(httpServletRequest != null){
                return httpServletRequest.getHeader("user-agent");
            }
        } catch (Exception e) {

        }
        return "";
    }

    private String getIpAddress(){
        try {
            return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRemoteAddr();
        } catch (IllegalStateException e) {
            return "within_the_server";
        }
    }

    private UserDetail getUsernameAndEmployeeDetails(){
        UserDetail userDetail = null;

        try {
            //get the user's information
            userDetail = jwtRedisToken.decodeToken(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader("Authorization"));
            if (userDetail != null) {
                return userDetail;
            }
        } catch (IllegalStateException e) {
//            logger.error("Exceptions", e);
        }

        return getSystemUser();
    }

    private UserDetail getSystemUser(){

        String userObject = String.valueOf(customDataCacheService.get("user"));

        if (userObject.equalsIgnoreCase("null") || userObject.equalsIgnoreCase(null)){

            //fetch from DB
            User user = userRepo.findUserByUsername("user");

            if (user != null && user.getEmployee() != null){
                UserDetail userDetail = new UserDetail(user.getUsername(), user.getEmployee().getId());
                userObject = new Gson().toJson(userDetail);
                customDataCacheService.save("user", userObject);
            }else {
                System.out.println("User is null or the user.Employee is NULL");
            }

        }

        return new Gson().fromJson(userObject, UserDetail.class);
    }

    private void saveAudit(String crudAction, Object entity, String oldEntityJson, String newEntityJson){
        Audits audits = new Audits();
        UserDetail userDetail = getUsernameAndEmployeeDetails();
        if (userDetail == null){
            throw new RuntimeException("No user found for this action... so aborting the action....");
        }
        audits.setCrudAction(crudAction);
        audits.setUsername(userDetail.getName());
        audits.setCrudActionDescription(crudAction + "d " + entity.getClass().getSimpleName());
        audits.setEntity(entity.getClass().getSimpleName());
        audits.setValueBefore(oldEntityJson);
        audits.setValueAfter(newEntityJson);
        audits.setIpAddress(getIpAddress());
        audits.setUserAgent(getUserAgent());
        audits.setEmployee(new Employee(userDetail.getEmployeeId()));
        auditRepo.save(audits);
    }

    @Data
    private class Pair {
        private String key;
        private String value;
    }
}
