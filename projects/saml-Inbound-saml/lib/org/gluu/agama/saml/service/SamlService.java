package org.gluu.agama.saml.service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.gluu.agama.saml.client.SamlClient;
import org.gluu.agama.saml.util.SamlUtil;

class SamlService {

    private static final Logger logger = LoggerFactory.getLogger(SamlService.class);

    private String serverUrl;
    private String realm;
    private String clientId;
    private String clientSecret;
    private String grantType;
    private String scope;
    private String username;
    private String password;
    private String spMetadataUrl;
    private String tokenUrl;
    private String idpUrl;

    private SamlClient samlClient = new SamlClient();
    private SamlUtil samlUtil = new SamlUtil();

    public SamlService(String serverUrl, String realm, String clientId, String clientSecret, String grantType,
            String scope, String username, String password, String spMetadataUrl, String tokenUrl, String idpUrl) {
        logger.info("Get  tokenUrl:{}, clientId:{}, grantType:{}, scope:{}, username:{}, serverUrl:{}", tokenUrl,
                clientId, grantType, scope, username, serverUrl);

        this.serverUrl = serverUrl;
        this.realm = realm;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.grantType = grantType;
        this.scope = scope;
        this.username = username;
        this.password = password;
        this.spMetadataUrl = spMetadataUrl;
        this.tokenUrl = tokenUrl;
        this.idpUrl = idpUrl;
    }

    public Map<String, String> getAllIdp() {
        logger.info("Fetch All IDP details");

        String token = getToken();
        logger.info("Access token:{}", token);
        
        String idpJsonString = samlClient.getAllIdp(samlUtil.getIdpUrl(this.serverUrl, this.idpUrl, this.realm), token);
        logger.info("Returning IDP details idpJsonString:{}", idpJsonString);
        Map<String, String> idpMap = createIdentityProviderMap(idpJsonString);
        logger.info("Returning IDP details idpMap:{}", idpMap);
        return idpMap;
    }

    public String getToken() {
        logger.info("Fetch Token for client");
        String token = samlClient.getAccessToken(samlUtil.getTokenUrl(this.serverUrl, this.tokenUrl, this.realm),
                this.clientId, this.clientSecret, this.grantType, this.scope, this.username, this.password,
                this.serverUrl);
        logger.info("Access token:{}", token);
        return token;
    }

    private Map<String, String> createIdentityProviderMap(String jsonIdentityProviderList) throws IOException {
        logger.info("jsonIdentityProviderList:{}", jsonIdentityProviderList);
        Map<String, String>  idpMap = null;
        if (StringUtils.isBlank(jsonIdentityProviderList)) {
            return idpMap;
        }

        JSONArray jsonArray = new JSONArray(jsonIdentityProviderList);
        int count = jsonArray.length(); // get totalCount of all jsonObjects
        idpList = new ArrayList<>();
        for (int i = 0; i < count; i++) { // iterate through jsonArray
            JSONObject jsonObject = jsonArray.getJSONObject(i); // get jsonObject @ i position
            logger.trace(" i:{},{}", i, jsonObject);
            if (jsonObject != null) {
                String alias = Jackson.getElement(jsonIdentityProvider, "alias");
                String displayName = jsonIdentityProvider, "displayName");
                logger.trace(" i:{},alias:{}, displayName:{}", i, alias, displayName);
                if(StringUtils.isNotBlank(alias)) {
                    idpMap.put(alias,StringUtils.isNotBlank(displayName)?displayName:alias);
                }
            }
        }
        logger.info("idpMap:{}", idpMap);
        return idpMap;
    }

}
