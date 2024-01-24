package org.gluu.agama.saml.service;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.gluu.agama.saml.client.SamlClient;

class SamlService {
    
    private static final Logger logger = LoggerFactory.getLogger(SamlService.class);
    
    private String serverUrl;
    private String clientId;
    private String clientSecret;
    private String grantType;
    private String scope;
    private String username;
    private String password;
    private String tokenUrl;
    private SamlClient samlClient = new SamlClient();
    
    public SamlService(String tokenUrl,  String clientId,  String clientSecret,
            String grantType,  String scope,  String username,  String password,
            String serverUrl) {  
        logger.info("Get  tokenUrl:{}, clientId:{}, grantType:{}, scope:{}, username:{}, serverUrl:{}", tokenUrl,
                clientId, grantType, scope, username, serverUrl);
        
        this.serverUrl = serverUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.grantType = grantType;
        this.scope = scope;
        this.username = username;
        this.password = password;
        this.tokenUrl = tokenUrl;
    }
    
    
    public Map<String, String> getAllIdp() {
        logger.info("Fetch All IDP details");
        Map<String,String> idpMap = null;
        
        
        
        logger.info("Returning IDP details idpMap:{}",idpMap); 
        return idpMap;
    }
    
    public String getToken() {
        logger.info("Fetch Token for client");
        String token = null;
        
        
        
        logger.info("Access token:{}",token); 
        return token;
    }
   
    
   
}
