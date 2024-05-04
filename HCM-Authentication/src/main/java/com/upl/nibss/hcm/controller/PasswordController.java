package com.upl.nibss.hcm.controller;

import com.upl.nibss.hcm.dto.Password;
import com.upl.nibss.hcm.dto.PasswordSetting;
import com.upl.nibss.hcm.enums.Errors;
import com.upl.nibss.hcm.enums.Success;
import com.upl.nibss.hcm.service.interfaces.ILoginService;
import com.upl.nibss.hcmlib.config.JWTRedisToken;
import com.upl.nibss.hcmlib.dto.UserDetail;
import com.upl.nibss.hcmlib.enums.AuthMode;
import com.upl.nibss.hcmlib.enums.Constants;
import com.upl.nibss.hcmlib.model.Settings;
import com.upl.nibss.hcmlib.model.User;
import com.upl.nibss.hcmlib.service.interfaces.ISettingsService;
import com.upl.nibss.hcmlib.service.interfaces.IUserService;
import com.upl.nibss.hcmlib.utils.GenericResponse;
import com.upl.nibss.hcmlib.utils.SmtpMailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by toyin.oladele on 18/10/2017.
 */
@RestController
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    private JWTRedisToken   jwtRedisToken;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ILoginService iLoginService;

    @Autowired
    private ISettingsService iSettingsService;

    @Autowired
    private SmtpMailSender smtpMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${email_from}")
    private String from;

    private static final Logger logger = LoggerFactory.getLogger(PasswordController.class);

    @PutMapping(name = "password.change", value = "/change", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GenericResponse> changePassword(@RequestBody Password password, @RequestHeader("Authorization") String authorization) throws Exception{
        //NOTE THAT USERNAME CANNOT BE CHANGED.
        //confirm that this is actual user that is logged in by verifying the token existence in the memory and the content of the token
        //get the user using the new username(Only Activated user)
        //Confirm that the user is activate
        //confirm that the new password meets the password requirement configuration if the authentication is via database not Active Directory
        //if true then update the password with the new password
        //send a congrats message. //maybe log out the user

        UserDetail userDetail = jwtRedisToken.decodeToken(authorization);

        if (password == null || password.getNewPassword() == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.DATA_NOT_PROVIDED.getValue().replace("{}","Password")),HttpStatus.BAD_REQUEST);
        }

        User user = iUserService.getActivatedUserByUsername(userDetail.getName());
        if (user == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.UNKNOWN_DATA.getValue().replace("{}","user")), HttpStatus.BAD_REQUEST);
        }

        AuthMode authMode = iLoginService.confirmAuthenticationMode();
        if (AuthMode.ACTIVE_DIRECTORY.equals(authMode)){
            return new ResponseEntity<>(GenericResponse.getErrorResponse("Authentication mode is configured to use Active Directory, Please contact HCM for change of password"), HttpStatus.BAD_REQUEST);
        }

        String result = validatePassword(password);
        logger.info("result is {}",result);
        if (!Constants.EMPTY.equals(result)){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(result), HttpStatus.BAD_REQUEST);
        }

        //Updated the password
        user.setPassword(passwordEncoder.encode(password.getNewPassword()));
        iUserService.save(user);

        return new ResponseEntity<>(GenericResponse.getSuccessResponse(Success.SUCCESS.getValue()),HttpStatus.OK);
    }

//    @PutMapping(name = "password.forgot", value = "/forgot", produces = "application/json")
//    public ResponseEntity<GenericResponse> forgotPassword(@RequestHeader("Authorization") String authorization){
//        //Send the user a link to reset password
//    }

    @PutMapping(name = "password.reset", value = "/reset", produces = "application/json")
    public ResponseEntity<GenericResponse> resetPassword(@RequestParam("email") String userEmail) throws Exception{
        //Generate a new password for the user and sent to the user's email address

        if (userEmail == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.DATA_NOT_PROVIDED.getValue().replace("{}","email")), HttpStatus.BAD_REQUEST);
        }

        User user = iUserService.getActivatedUserByEmail(userEmail);
        if (user == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.UNKNOWN_DATA.getValue().replace("{}","user")), HttpStatus.BAD_REQUEST);
        }

        AuthMode authMode = iLoginService.confirmAuthenticationMode();
        if (AuthMode.ACTIVE_DIRECTORY.equals(authMode)){
            return new ResponseEntity<>(GenericResponse.getErrorResponse("Authentication mode is configured to use Active Directory, Please contact HCM for change of password"), HttpStatus.BAD_REQUEST);
        }

        String password = iUserService.generatePassword();
        user.setPassword(passwordEncoder.encode(password));
        user.setGeneratedPassword(true); //The user is required to change his/her password to his/her preferred password.
        iUserService.save(user);

        //send Mail
        String details = "";
        details += "<strong>Generated Password :</strong> " + password + "<br/>";
        details += "<hr><br/>";

        String[] email = {user.getEmployee().getEmail()};
        smtpMailSender.sendNotificationToUser(from, email, "Reset Password", "Reset Password", "Please view details of generated password below", details);

        return new ResponseEntity<>(GenericResponse.getSuccessResponse("New Password have been sent to your email"), HttpStatus.OK);
    }

    private String validatePassword(Password password) throws Exception {

        PasswordSetting passwordSetting = new PasswordSetting();

        List<Settings> allSettings = iSettingsService.getAllSettings();
        logger.info("{}",allSettings);
        allSettings.forEach(settings -> {
            if (ISettingsService.MIN_DIGIT.equals(settings.getName())){
                passwordSetting.setMinDigit(Integer.valueOf(settings.getValue()));
            }

            if (ISettingsService.MIN_LOWER_CASE.equals(settings.getName())){
                passwordSetting.setMinLowerCase(Integer.valueOf(settings.getValue()));
            }

            if (ISettingsService.MIN_SPECIAL_CHAR.equals(settings.getName())){
                passwordSetting.setMinSpecialChar(Integer.valueOf(settings.getValue()));
            }

            if (ISettingsService.MIN_UPPER_CASE.equals(settings.getName())){
                passwordSetting.setMinUppercase(Integer.valueOf(settings.getValue()));
            }

            if (ISettingsService.PASSWORD_LENGTH.equals(settings.getName())){
                passwordSetting.setPasswordLength(Integer.valueOf(settings.getValue()));
            }
        });

        int count = password.getNewPassword().length();
        if (count < passwordSetting.getPasswordLength()){
            return "Password Not acceptable. Minimum password length is "+passwordSetting.getPasswordLength();
        }

        count = password.getNewPassword().replaceAll("\\P{Digit}", "").replaceAll("\\p{Blank}","").length();
        if (count < passwordSetting.getMinDigit()){
            return "Password not acceptable. Minimum number of digit is "+passwordSetting.getMinDigit();
        }

        count = password.getNewPassword().replaceAll("\\P{Lower}","").replaceAll("\\p{Blank}","").length();
        if (count < passwordSetting.getMinLowerCase()){
            return "Password not acceptable. Minimum number of lower case is "+passwordSetting.getMinLowerCase();
        }

        count = password.getNewPassword().replaceAll("\\P{Upper}","").replaceAll("\\p{Blank}","").length();
        if (count < passwordSetting.getMinUppercase()){
            return "Password Not acceptable. Minimum number of Upper case is "+passwordSetting.getMinUppercase();
        }

        count = password.getNewPassword().replaceAll("\\p{Upper}","").replaceAll("\\p{Lower}","").replaceAll("\\p{Digit}", "").replaceAll("\\p{Blank}","").length();
        if (count < passwordSetting.getMinSpecialChar()){
            return "Password Not acceptable. Minimum number of special char is "+passwordSetting.getMinSpecialChar();
        }

        return Constants.EMPTY;
    }

}
