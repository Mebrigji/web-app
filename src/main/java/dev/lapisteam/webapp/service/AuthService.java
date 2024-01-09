package dev.lapisteam.webapp.service;


import dev.lapisteam.webapp.objects.Account;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Getter
    private static AuthService instance;

    public AuthService(){
        instance = this;
    }

    @Getter
    private Map<String, Account> cache = new HashMap<>();

}
