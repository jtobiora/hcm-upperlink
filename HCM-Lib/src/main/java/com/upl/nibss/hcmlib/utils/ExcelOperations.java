package com.upl.nibss.hcmlib.utils;

import com.google.common.io.Files;
import com.upl.nibss.hcmlib.utils.interfaces.IExcelOperation;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by stanlee on 09/01/2018.
 */
@Service
public abstract class ExcelOperations implements IExcelOperation {
//
//    @Autowired
//    private IEmployeeService employeeService;
//
//    @Autowired
//    private IDepartmentService departmentService;
//
//    @Autowired
//    private IJobRoleService jobRoleService;
//
//    @Autowired
//    private IGradeLevelService gradeLevelService;

    private final static String EMPLOYEE_EXCEL_FILE = "creation_template.xls";

    private static final Logger logger = LoggerFactory.getLogger(ExcelOperations.class);

    public File generateExcelFile() throws Exception {

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        createHeaderRowAndDropDownConstants(workbook, sheet);
        createAllCustomCell(workbook);

        File file = new File(EMPLOYEE_EXCEL_FILE);

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            workbook.write(outputStream);
        }

        return file;
    }

//    public void writeDropDown(Workbook workbook, Sheet sheet) throws Exception {
//
//        //Set the marital status
//        List<String> maritalStatus = new ArrayList<>();
//        maritalStatus.add("SINGLE");
//        maritalStatus.add("MARRIED");
//        maritalStatus.add("DIVORCED");
//        maritalStatus.add("SEPARATED");
//        maritalStatus.add("WIDOWED");
//        dropDownList(workbook, sheet, maritalStatus,8, "maritalStatus");
//
//        //set job role title
//        dropDownList(workbook, sheet, jobRoleService.getAlljobRoleTitle(), 13, "jobTitle");
//
//        //set department
//        dropDownList(workbook, sheet, departmentService.getAllDepartmentNames(), 15, "departments");
//
//        //StaffType
//        List<String> staffType = new ArrayList<>();
//        staffType.add("CONTRACT");
//        staffType.add("PERMANENT");
//        dropDownList(workbook, sheet, staffType, 16, "staffType");
//
//        //set supervisor
//        List<NameType> employeeNames = employeeService.getAllEmployeeNames();
//        List<String> names = new ArrayList<>();
//        employeeNames.forEach(nameType -> names.add(nameType.getFirstName()+"__"+nameType.getLastName()));
//        dropDownList(workbook, sheet, names, 17, "supervisor");
//
//        //set second level supervisor
//        dropDownList(workbook, sheet, names, 18, "secondLevelSupervisor");
//
//        //set grade level
//        dropDownList(workbook, sheet, gradeLevelService.getAllGradeLevelNames(), 19, "gradelevels");
//
//        //Set the marital status
//        List<String> gender = new ArrayList<>();
//        gender.add("MALE");
//        gender.add("FEMALE");
//        dropDownList(workbook, sheet, gender,20, "gender");
//
//    }

    public void setCellStyle(Sheet sheet){
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(false);
        font.setFontHeightInPoints((short) 12);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFont(font);
        cellStyle.setWrapText(true);
    }

    public void createHeaderRowAndDropDownConstants(Workbook workbook,Sheet sheet) throws Exception{

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        cellStyle.setFont(font);

        Row row = sheet.createRow(0);
        row.setRowStyle(cellStyle);

        setTitle(row);
        writeDropDown(workbook,sheet);
    }

//    public void createAllCustomCell(Workbook workbook){
//        setCellAsString(workbook, 1);//EMPLOYEE NO
//        setCellAsDate(workbook, 7);//BIRTH DATE
//        setCellAsDate(workbook, 10);//EXPECTED RESUMPTION DATE
//        setCellAsDate(workbook, 11);//ACTUAL RESUMPTION DATE
//        setCellAsDate(workbook, 12);//EXPECTED CONFIRMATION DATE
//        setCellAsString(workbook, 14);//SALARY
//    }

//    public void setTitle(Row row){
//
//        row.createCell(1).setCellValue("EMPLOYEE NO");
//        row.createCell(2).setCellValue("TITLE");
//        row.createCell(3).setCellValue("FIRST NAME");
//        row.createCell(4).setCellValue("LAST NAME");
//        row.createCell(5).setCellValue("MIDDLE NAME");
//        row.createCell(6).setCellValue("EMAIL");
//        row.createCell(7).setCellValue("BIRTH DATE"); //Date
//        row.createCell(8).setCellValue("MARITAL STATUS");
//        row.createCell(9).setCellValue("PERSONAL DESC");
//        row.createCell(10).setCellValue("EXPECTED RESUMPTION DATE");//Date
//        row.createCell(11).setCellValue("ACTUAL RESUMPTION DATE");//Date
//        row.createCell(12).setCellValue("EXPECTED CONFIRMATION DATE");//Date
//        row.createCell(13).setCellValue("JOB ROLE");
//        row.createCell(14).setCellValue("SALARY");
//        row.createCell(15).setCellValue("DEPARTMENT");
//        row.createCell(16).setCellValue("STAFF TYPE");
//        row.createCell(17).setCellValue("SUPERVISOR");
//        row.createCell(18).setCellValue("SECOND LEVEL SUPERVISOR");
//        row.createCell(19).setCellValue("GRADE LEVEL");
//        row.createCell(20).setCellValue("GENDER");
//    }

    public void dropDownList(Workbook workbook, Sheet sheet, List<String> contents, int cellNumber, String typeName){
        CellRangeAddressList addressList = new CellRangeAddressList(1, 1000, cellNumber, cellNumber);
        int size = contents.size();

        //create another sheet
        Sheet newSheet = workbook.createSheet(typeName);
        for (int i = 0; i < size; i++) {
            Row row = newSheet.createRow(i);
            row.createCell(0).setCellValue(contents.get(i));
        }

        //Use namedCell
        Name namedCell = workbook.createName();
        namedCell.setNameName(typeName+"1");
        namedCell.setRefersToFormula("'"+typeName+"'!$A$1:$A$" + size);

        DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint(typeName+"1");
        DataValidation dataValidation = new HSSFDataValidation(addressList, dvConstraint);
        dataValidation.setSuppressDropDownArrow(false);
        sheet.addValidationData(dataValidation);

        //hide the sheet
        workbook.setSheetHidden(workbook.getSheetIndex(newSheet),true);
    }

    public String validateFile(MultipartFile file){

        //get the file
        if (file == null){
            return "file is null";
        }

        if (!"application/vnd.ms-excel".equalsIgnoreCase(file.getContentType())){
            return "Not an excel file";
        }

        return null;
    }

//    public List<Employee> fetchFromExcelSheet(Workbook workbook, int totalRowCount) throws Exception {
//
//        Sheet sheet1 = workbook.getSheetAt(0);
//
//        List<Employee> generatedEmployees = new ArrayList<>();
//
//        //get All employees (Activated And Deleted)
//        List<Employee> employeeList = employeeService.getAll();
//        Map<String,Employee> employeeNumberMap = new HashMap<>();
//        Map<String, Employee> actualEmployeeMap = new HashMap<>();
//        employeeList.forEach(employee -> {
//            actualEmployeeMap.put(employee.getEmpName().getFirstName()+"__"+employee.getEmpName().getLastName(), employee);
//            employeeNumberMap.put(employee.getEmployeeNo(), employee);
//        });
//
//        //get All Activated employees
//        //Email Address
//        List<Employee> employees = employeeService.getAllActivated();
//        Map<String, Employee> emailMap = new HashMap<>();
//        Map<String, Employee> employeeMap = new HashMap<>();
//        employees.forEach(employee -> {
//            employeeMap.put(employee.getEmpName().getFirstName()+"__"+employee.getEmpName().getLastName(), employee);
//            emailMap.put(employee.getEmail(), employee);
//        });
//
//
//        //get All Activated job title
//        List<JobRole> jobRoles = jobRoleService.getAllActivated();
//        Map<String, JobRole> jobRoleMap = new HashMap<>();
//        jobRoles.forEach(jobRole -> jobRoleMap.put(jobRole.getJobTitle(), jobRole));
//
//        //get All Activated department
//        List<Department> departmentList = departmentService.getAllActivated();
//        Map<String, Department> departmentMap = new HashMap<>();
//        departmentList.forEach(department -> departmentMap.put(department.getDeptName(), department));
//
//        //get GradeLeve
//        List<GradeLevel> gradeLevels = gradeLevelService.getAllActivated();
//        Map<String, GradeLevel> gradeLevelMap = new HashMap<>();
//        gradeLevels.forEach(gradeLevel -> gradeLevelMap.put(gradeLevel.getGradeName(), gradeLevel));
//
//
//        for (Row row : sheet1) {
//
//            //if it is the title then skip
//            if (sheet1.getRow(0) == row){
//                continue;
//            }
//
//            //count the number of department filled by user.
//            ++totalRowCount;
//
//            Employee employee = new Employee();
//            int errorCellValue = 21;
//
//            try {
//                //EMPLOYEE NO
//                Cell cellEmployeeNo = row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//                if (cellEmployeeNo == null){
//                    row.createCell(errorCellValue).setCellValue("Employee number is required.");
//                    continue;
//                }
//                String employeeNumber = cellEmployeeNo.getRichStringCellValue().getString();
//                if (employeeNumberMap.get(employeeNumber) != null){
//                    row.createCell(errorCellValue).setCellValue("Employee number already exist.");
//                    continue;
//                }
//
//                employee.setEmployeeNo(employeeNumber);
//
//            } catch (Exception e) {
//                row.createCell(errorCellValue).setCellValue("Employee number is invalid. Please start with a ' ");
//                continue;
//            }
//
//            //TITLE
//            String title = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
//
//            //FIRST NAME
//            Cell cellFirstName = row.getCell(3, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//            if (cellFirstName == null){
//                row.createCell(errorCellValue).setCellValue("Employee first name is required");
//                continue;
//            }
//
//            //LAST NAME
//            Cell cellLastName = row.getCell(4, Row.MissingCellPolicy.RETURN_NULL_AND_BLANK);
//            if (cellLastName == null){
//                row.createCell(errorCellValue).setCellValue("Employee last Name is required");
//                continue;
//            }
//
//            //MIDDLE NAME
//            String middleName = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
//            employee.setEmpName(new NameType(title, cellFirstName.getStringCellValue(), cellLastName.getStringCellValue(), middleName));
//
//            //EMAIL
//            Cell cellEmail = row.getCell(6, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//            if (cellEmail == null){
//                row.createCell(errorCellValue).setCellValue("Employee email address is required");
//                continue;
//            }
//            String emailAddress = cellEmail.getStringCellValue();
//            if (emailMap.get(emailAddress) != null){
//                row.createCell(errorCellValue).setCellValue("Email Address already exists");
//                continue;
//            }
//            employee.setEmail(emailAddress);
//
//            //BIRTH DATE
//            Cell cellBirthDate = row.getCell(7, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//            if (cellBirthDate == null){
//                row.createCell(errorCellValue).setCellValue("Date of birth is required");
//                continue;
//            }
//
//            //MARITAL STATUS
//            String cellMaritalStatus = row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
//
//            //PERSONAL DESC
//            String personalDescription = row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
//
//            //EXPECTED RESUMPTION DATE
//            Cell cellExpectedResumptionDate = row.getCell(10, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//            if (cellExpectedResumptionDate == null){
//                row.createCell(errorCellValue).setCellValue("Expected Resumption Date is required");
//                continue;
//            }
//            employee.setExpectedResumptionDate(cellExpectedResumptionDate.getDateCellValue());
//
//            //ACTUAL RESUMPTION DATE
//            Cell cellActualResumptionDate = row.getCell(11, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//            if (cellActualResumptionDate == null){
//                row.createCell(errorCellValue).setCellValue("Actual Resumption Date is required");
//                continue;
//            }
//            employee.setActualResumptionDate(cellActualResumptionDate.getDateCellValue());
//
//            //EXPECTED CONFIRMATION DATE
//            Cell cellExpectedConfirmationDate = row.getCell(12, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//            if (cellExpectedConfirmationDate == null){
//                row.createCell(errorCellValue).setCellValue("Expected Confirmation Date is required");
//                continue;
//            }
//            employee.setExpectedConfirmationDate(cellExpectedConfirmationDate.getDateCellValue());
//
//            String jobRoleTitle = row.getCell(13, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
//            JobRole jobRole = jobRoleMap.get(jobRoleTitle);
//            if (jobRole == null){
//                row.createCell(errorCellValue).setCellValue("Job Role is required");
//                continue;
//            }
//            employee.setJobRole(jobRole);
//
//            //SALARY
//            double salary = row.getCell(14, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
//            try {
//                employee.setSalary(new BigDecimal(salary));
//            } catch (Exception e) {
//                employee.setSalary(BigDecimal.ZERO);
//            }
//
//            //DEPARTMENT
//            String departmentName = row.getCell(15, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
//            Department department = departmentMap.get(departmentName);
//            if (department == null){
//                row.createCell(errorCellValue).setCellValue("Department is required");
//                continue;
//            }
//            employee.setDepartment(new DepartmentEmbeddable(department.getId(),department.getDeptName()));
//
//            //STAFF TYPE
//            String staffType = row.getCell(16, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
//            employee.setEmployeeType(StaffType.valueOf(staffType));
//
//            //SUPERVISOR
//            String supervisorName = row.getCell(17, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
//            logger.info(supervisorName);
//            Employee supervisor = employeeMap.get(supervisorName);
//            if (supervisor == null){
//                row.createCell(errorCellValue).setCellValue("Employee is not a supervisor, Supervisor is required");
//                continue;
//            }
//            supervisor.setSupervisor(true);
//            employee.setEmployeeSupervisor(supervisor);
//
//            //SECOND LEVEL SUPERVISOR
//            String secondLevelSupervisorName = row.getCell(18, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
//            Employee secondLevelSupervisor = employeeMap.get(secondLevelSupervisorName);
//            if (secondLevelSupervisor == null){
//                row.createCell(errorCellValue).setCellValue("Second Level Supervisor is required");
//                continue;
//            }
//            employee.setEmployeeSecondLevelSupervisor(secondLevelSupervisor);
//
//            //GRADE LEVEL
//            String gradeLevelName = row.getCell(19, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
//            GradeLevel gradeLevel = gradeLevelMap.get(gradeLevelName);
//            if (gradeLevel == null){
//                row.createCell(errorCellValue).setCellValue("Grade level is required");
//                continue;
//            }
//            employee.setGradeLevel(gradeLevel);
//
//            //GENDER
//            String cellGender = row.getCell(20, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
//            employee.setEmployeePersonDetail(new Person(GenderType.valueOf(cellGender), cellBirthDate.getDateCellValue(), MaritalType.valueOf(cellMaritalStatus), personalDescription));
//
//            row.createCell(errorCellValue).setCellValue("Created Successfully");
//
//            //add to the Map
//            generatedEmployees.add(employee);
//
//            //add to the List
//            actualEmployeeMap.put(employee.getEmpName().getFirstName()+"__"+employee.getEmpName().getLastName(), employee);
//            emailMap.put(employee.getEmail(), employee);
//            employeeNumberMap.put(employee.getEmployeeNo(), employee);
//
//        }
//
//        return generatedEmployees;
//    }

    public void setCellAsDate(Workbook workbook, int cellNumber){

        Sheet sheetAt = workbook.getSheetAt(0);

        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();

        // Set the date format of date
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));

        //1000 rows starting from row 1
        for (int i = 1; i < 1000; i++) {
            Cell cell = sheetAt.createRow(i).createCell(cellNumber);
            cell.setCellStyle(cellStyle);
        }

    }

    public void setCellAsString(Workbook workbook, int cellNumber){

        Sheet sheetAt = workbook.getSheetAt(0);

        //1000 rows starting from row 1
        for (int i = 1; i < 1000; i++) {
            Cell cell = sheetAt.createRow(i).createCell(cellNumber);
            cell.setCellType(CellType.STRING);
        }

    }


    public ResponseEntity<InputStreamResource> getGeneratedExcelFile() throws Exception{
        File excelFile = generateExcelFile();
        return ResponseEntity
                .ok()
                .contentLength(new Double(excelFile.length()).longValue())
                .contentType(
                        MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(new InputStreamResource(new ByteArrayInputStream(Files.toByteArray(excelFile))));
    }

    public ResponseEntity<InputStreamResource> bulkCreation(MultipartFile file, String downloadFileName) throws Exception{

        String error = validateFile(file);
        String messageName = "message";
        if (error != null){
            return ResponseEntity.badRequest().header(messageName,error).body(null);
        }

        File excelFile = new File(file.getOriginalFilename());
        if (!excelFile.exists()){excelFile.createNewFile();}
        try (FileOutputStream fos = new FileOutputStream(excelFile)){
            fos.write(file.getBytes());
        }

        Workbook workbook = WorkbookFactory.create(excelFile);
        int totalRowCount = 0;

        List<Object> createdObjects = generateAndSaveObjects(workbook, totalRowCount);

        File finalExcelFile = new File(downloadFileName+".xls");

        //response with stat and file
        try (FileOutputStream outputStream = new FileOutputStream(finalExcelFile)) {
            workbook.write(outputStream);
        }

        //remove
        excelFile.deleteOnExit();

        return ResponseEntity
                .ok()
                .header(messageName, "Successfully created "+createdObjects.size()+" out of "+totalRowCount)
                .contentLength(new Double(finalExcelFile.length()).longValue())
                .contentType(
                        MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(new InputStreamResource(new ByteArrayInputStream(Files.toByteArray(finalExcelFile))));
    }


}
