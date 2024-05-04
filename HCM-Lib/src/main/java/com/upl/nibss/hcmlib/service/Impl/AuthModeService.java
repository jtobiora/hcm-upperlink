package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.service.interfaces.IAuthModeService;
import com.upl.nibss.hcmlib.enums.AuthMode;
import com.upl.nibss.hcmlib.model.Settings;
import com.upl.nibss.hcmlib.repo.SettingRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by toyin.oladele on 30/10/2017.
 */
@Service
public class AuthModeService implements IAuthModeService{


    private static final Logger logger = LoggerFactory.getLogger(AuthModeService.class);

    @Autowired
    private SettingRepo settingRepo;

    @Override
    public AuthMode get() throws Exception {

        Settings setting = settingRepo.findByName(AuthMode.AUTHENTICATION_MODE.toString());
        return setting != null ?  AuthMode.valueOf(setting.getValue()) : null;

    }

    @Override
    public AuthMode create(AuthMode authMode) throws Exception{

        Settings settings = new Settings();
        settings.setName(AuthMode.AUTHENTICATION_MODE.toString());
        settings.setValue(authMode.toString());
        Settings save = settingRepo.save(settings);
        return AuthMode.valueOf(save.getValue());

    }

    @Override
    public AuthMode update(AuthMode authMode) throws Exception{

        Settings setting = settingRepo.findByName(AuthMode.AUTHENTICATION_MODE.toString());
        setting.setValue(authMode.toString());
        setting = settingRepo.save(setting);
        return AuthMode.valueOf(setting.getValue());

    }
}
