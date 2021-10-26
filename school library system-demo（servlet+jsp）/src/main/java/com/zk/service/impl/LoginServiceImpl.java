package com.zk.service.impl;

import com.zk.repository.AdminRepository;
import com.zk.repository.ReaderRepository;
import com.zk.repository.impl.AdminRepositoryImpl;
import com.zk.repository.impl.ReaderRepositoryImpl;
import com.zk.service.LoginService;

public class LoginServiceImpl implements LoginService {
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();
    private AdminRepository adminRepository = new AdminRepositoryImpl();
    @Override
    public Object login(String username, String password,String type) {
        Object object=null;
        switch(type){
            case "reader":
                object= readerRepository.login(username,password);
                break;
            case "admin":
                object =adminRepository.login(username,password);
        }

        return object;
    }


}
