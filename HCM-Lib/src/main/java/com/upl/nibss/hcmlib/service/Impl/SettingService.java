package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.dto.authsetting.PasswordSettingRequest;
import com.upl.nibss.hcmlib.service.interfaces.ISettingsService;
import com.upl.nibss.hcmlib.model.Settings;
import com.upl.nibss.hcmlib.repo.SettingRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toyin.oladele on 31/10/2017.
 */
@Service
public class SettingService implements ISettingsService {

    private final static Logger logger = LoggerFactory.getLogger(SettingService.class);

    @Autowired
    private SettingRepo settingRepo;

    @Override
    public List<Settings> getAllSettings() throws Exception{
        return settingRepo.findAll();
    }

    @Override
    public List<Settings> getSettingsByNames(List<String> names) throws Exception {
        return settingRepo.findByNames(names);
    }

    @Override
    public Settings getSettingByName(String name) throws Exception {
        return settingRepo.findByName(name);
    }

    @Override
    public Settings saveSetting(String settingValue, String settingName)  throws Exception{

        Settings setting = settingRepo.findByName(settingName);
        if (setting == null){
            setting = new Settings(settingName, settingValue);
        }else {
            setting.setValue(settingValue);
        }

        return settingRepo.save(setting);
    }

//    @Override
//    public Settings createSetting(String settingValue, String settingName)  throws Exception{
//
//        Settings setting = settingRepo.findByName(settingName);
//        if (setting == null){
//            setting = new Settings(settingName, settingValue);
//           return settingRepo.save(setting);
//        }
//        return null;
//
//    }
//
//    @Override
//    public Settings updateSetting(String settingValue, String settingName) {
//
//        Settings setting = settingRepo.findByName(settingName);
//        if (setting != null){
//            setting.setValue(settingValue);
//            return settingRepo.save(setting);
//        }
//        return null;
//    }

    @Override
    public Settings createMaxLoginAttempt(String settingValue) throws Exception {
        logger.info("creating Max login Attempt");
        return saveSetting(settingValue, MAX_LOGIN_ATTEMPT);
    }

    @Override
    public Settings updateMaxLoginAttempt(String settingValue) throws Exception {
        return saveSetting(settingValue, MAX_LOGIN_ATTEMPT);
    }

    @Override
    public Settings createLockoutDuration(String settingValue) throws Exception {
        return saveSetting(settingValue, LOCKOUT_DURATION);
    }

    @Override
    public Settings updateLockoutDuration(String settingValue) throws Exception {
        return saveSetting(settingValue, LOCKOUT_DURATION);
    }

    @Override
    public List<Settings> createPasswordConfig(PasswordSettingRequest request) throws Exception {

        List<Settings> settings = new ArrayList<>();
        settings.add(new Settings(PASSWORD_LENGTH,request.getPasswordLength()));
        settings.add(new Settings(MIN_UPPER_CASE,request.getMinUpperCase()));
        settings.add(new Settings(MIN_LOWER_CASE,request.getMinLowerCase()));
        settings.add(new Settings(MIN_SPECIAL_CHAR,request.getMinSpecialChar()));
        settings.add(new Settings(MIN_DIGIT,request.getMinDigit()));
        return settingRepo.save(settings);
    }

    @Override
    public List<Settings> updatePasswordConfig(PasswordSettingRequest request) throws Exception {

        List<String> names = new ArrayList<>();
        names.add(PASSWORD_LENGTH);
        names.add(MIN_UPPER_CASE);
        names.add(MIN_LOWER_CASE);
        names.add(MIN_SPECIAL_CHAR);
        names.add(MIN_DIGIT);

        List<Settings> settings = settingRepo.findByNames(names);
        settings.stream().forEach(setting -> {

            if (PASSWORD_LENGTH.equals(setting.getName())) {
                setting.setValue(request.getPasswordLength());
            }

            if (MIN_UPPER_CASE.equals(setting.getName())) {
                setting.setValue(request.getMinUpperCase());
            }

            if (MIN_LOWER_CASE.equals(setting.getName())) {
                setting.setValue(request.getMinLowerCase());
            }

            if (MIN_SPECIAL_CHAR.equals(setting.getName())) {
                setting.setValue(request.getMinSpecialChar());
            }

            if (MIN_DIGIT.equals(setting.getName())) {
                setting.setValue(request.getMinDigit());
            }

        });
        return settingRepo.save(settings);
    }

    @Override
    public Settings saveLoanSettings(int numberOfMonth) throws Exception {
        return saveSetting(String.valueOf(numberOfMonth), LOAN_ELIGIBLE_TENURE);
    }

    @Override
    public Settings saveNonLeaveDaysSettings(String nonLeaveDays) throws Exception {
        return saveSetting(nonLeaveDays, NON_LEAVE_DAYS);
    }
}
