package com.upl.nibss.hcmlib.utils;

import com.upl.nibss.hcmlib.config.JWTRedisToken;
import com.upl.nibss.hcmlib.dto.ApprovalDetail;
import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.model.Authorizers;
import com.upl.nibss.hcmlib.model.Awarers;
import com.upl.nibss.hcmlib.service.Impl.AwarerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by stanlee on 15/02/2018.
 */
@Service
public class SuperOperationService {

    @Autowired
    private JWTRedisToken jwtRedisToken;

    @Autowired
    private AwarerService awarerService;

    public static Authorizers getNextAuthorizer(Set<Authorizers> authorizers) throws Exception{

        Authorizers authorizerWithMinPriority = null;
        for (Authorizers authorizer : authorizers) {
            if (authorizerWithMinPriority == null && (authorizer.getApprovalStatus() == ApprovalStatus.PENDING_APPROVAL)){
                authorizerWithMinPriority = authorizer;
            }
            else if (authorizerWithMinPriority != null && (authorizerWithMinPriority.getRoles().getOrder_value() > authorizer.getRoles().getOrder_value())
                    && authorizer.getApprovalStatus() == ApprovalStatus.PENDING_APPROVAL){
                authorizerWithMinPriority = authorizer;
            }
        }

        return authorizerWithMinPriority;
    }

    /**
     * Used to Generate email approval detail such that while approving
     * form the email this information is decryted and passed to the backend for approval(Used for approving or disapproving)
     * @param sessionId
     * @param moduleName
     * @param requestId
     * @param authorizerId
     * @param approverId
     * @return
     */
    public String encyptApprovalDetail(String sessionId, String moduleName, Long requestId, Long authorizerId, Long approverId){
        return jwtRedisToken.generateToken(new ApprovalDetail(sessionId,moduleName,requestId,authorizerId,approverId));
    }

    public void updateAwarenessinfo(Set<Awarers> awarersSet) throws Exception{
        //Get all the people who needs to be aware of the approval
        List<Awarers> awarers = new ArrayList<>(awarersSet);
        awarers.stream().forEach(awarer->{
            awarer.setNotificationDate(new Timestamp(System.currentTimeMillis()));
        });
        awarerService.save(awarers);
    }

    public String generateApprovalUrl(String approvalBaseUrl, Long recordId, Long authorizerId, Long approverId, String sessionId, String moduleName) throws Exception{
        String details = approvalBaseUrl
                .replace("{encrypteddata}", encyptApprovalDetail(sessionId,moduleName, recordId,authorizerId,approverId));
        return details;
    }

    public String generateApprovedMessage(String moduleName) throws Exception{
        String details = "<strong>"+moduleName+" Request have been Approved<strong>";
        details += "<hr><br/>";
        return details;
    }

    public String generateDisApprovedMessage(String moduleName) throws Exception{
        String details = "<strong>"+moduleName+" Request was Rejected</strong>";
        details += "<hr><br/>";
        return details;
    }
}
