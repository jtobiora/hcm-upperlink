package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.dto.authsetting.PasswordSettingRequest;
import com.upl.nibss.hcmlib.model.Settings;

import java.util.List;

/**
 * Created by toyin.oladele on 31/10/2017.
 */
public interface ISettingsService {

    //SETTING NAMES
    //loginSecurity
    String MAX_LOGIN_ATTEMPT = "MAX_LOGIN_ATTEMPT";
    String LOCKOUT_DURATION = "LOCKOUT_DURATION";

    //Password
    String PASSWORD_LENGTH = "PASSWORD_LENGTH";
    String MIN_SPECIAL_CHAR = "MIN_SPECIAL_CHAR";
    String MIN_LOWER_CASE = "MIN_LOWER_CASE";
    String MIN_UPPER_CASE = "MIN_UPPER_CASE";
    String MIN_DIGIT = "MIN_DIGIT";

    //Loan
    String LOAN_ELIGIBLE_TENURE = "LOAN_ELIGIBLE_TENURE";

    //NonLeaveDays
    String NON_LEAVE_DAYS = "NON_LEAVE_DAYS";

    List<Settings> getAllSettings() throws Exception;
    List<Settings> getSettingsByNames(List<String> names) throws Exception;
    Settings getSettingByName(String name) throws Exception;

    //creates and update
    Settings saveSetting(String settingValue, String settingName) throws Exception;
//    Settings createSetting(String settingValue, String settingName) throws Exception;
//    Settings updateSetting(String settingValue, String settingName) throws Exception;

    Settings createMaxLoginAttempt(String settingValue) throws Exception;
    Settings updateMaxLoginAttempt(String settingValue) throws Exception;

    Settings createLockoutDuration(String settingValue) throws Exception;
    Settings updateLockoutDuration(String settingValue) throws Exception;

    List<Settings> createPasswordConfig(PasswordSettingRequest request) throws Exception;
    List<Settings> updatePasswordConfig(PasswordSettingRequest request) throws Exception;

    Settings saveLoanSettings(int numberOfMonth) throws Exception;

    Settings saveNonLeaveDaysSettings(String nonLeaveDays) throws Exception;
}
