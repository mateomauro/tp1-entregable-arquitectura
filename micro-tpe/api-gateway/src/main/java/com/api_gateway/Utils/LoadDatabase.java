package com.api_gateway.Utils;

import com.api_gateway.Entity.Authority;
import com.api_gateway.Repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadDatabase {

    @Autowired
    private AuthorityRepository authorityRepository;

    // dar de alta Autoridades
    public void loadAuthorities() throws Exception {
        Authority ADMIN = new Authority("ADMIN");
        Authority MANAGER = new Authority("MANAGER");
        Authority USER = new Authority("USER");
        try {
            if(authorityRepository.findAll().isEmpty()) {
                authorityRepository.save(ADMIN);
                authorityRepository.save(MANAGER);
                authorityRepository.save(USER);
            }
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
