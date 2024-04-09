package org.gluu.agama.saml.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

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
import org.gluu.agama.saml.model.SamlConfig;
import org.gluu.agama.saml.model.IdentityProvider;
import org.gluu.agama.saml.util.SamlUtil;
import org.gluu.agama.saml.util.Jackson;

public class SamlService {

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
    private String extIDPTokenUrl;

    private SamlClient samlClient = new SamlClient();
    private SamlConfig samlConfig ;
    private SamlUtil samlUtil = new SamlUtil();

    public SamlService(String serverUrl, String realm, String clientId, String clientSecret, String grantType,
            String scope, String username, String password, String spMetadataUrl, String tokenUrl, String idpUrl, String extIDPTokenUrl) {
        logger.error("Get  serverUrl:{}, realm:{}, clientId:{}, clientSecret:{}, grantType:{}, scope:{}, username:{}, password:{}, spMetadataUrl:{}, tokenUrl:{}, idpUrl:{}, extIDPTokenUrl:{}", serverUrl,
                realm, clientId, clientSecret, grantType, scope, username, password, spMetadataUrl, tokenUrl, idpUrl, extIDPTokenUrl);

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
        this. extIDPTokenUrl = extIDPTokenUrl;
        
        this.samlConfig = new SamlConfig(serverUrl, realm, clientId, clientSecret, grantType,
                scope, username, password,  spMetadataUrl,  tokenUrl,  idpUrl,  extIDPTokenUrl);
        logger.error("SamlService instance created - samlConfig:{} ", samlConfig);
    }
    
    public Map<String, List> getIdps() throws JsonProcessingException{
        logger.info("getHardcodedIdp() ");
        Map<String, List> idpMap = new HashMap<>();
        
        List<IdentityProvider> idpList = getIdpList();
        logger.info("idpList:{}", idpList);
        idpMap.put("idps", idpList);
        logger.info("Returning IDP details idpMap:{}", idpMap);
        return idpMap;
    }

    public Map<String, String> getIdpMap() throws JsonProcessingException {

        logger.info("Fetch All IDP details");
        String token = getToken();
        logger.info("Access token:{}", token);

        String idpJsonString = samlClient.getAllIdp(samlUtil.getIdpUrl(this.serverUrl, this.idpUrl, this.realm), token);
        logger.info("Returning IDP details idpJsonString:{}", idpJsonString);
        Map<String, String> idpMap = createIdentityProviderMap(idpJsonString);
        logger.info("Returning IDP details idpMap:{}", idpMap);

        return idpMap;
    }

    public List<IdentityProvider> getIdpList() throws JsonProcessingException {

        logger.info("Fetch All IDP details");
        String token = getToken();
        logger.info("Access token:{}", token);

        String idpJsonString = samlClient.getAllIdp(samlUtil.getIdpUrl(this.serverUrl, this.idpUrl, this.realm), token);
        logger.info("Returning IDP details idpJsonString:{}", idpJsonString);
        List<IdentityProvider> idpList = createIdentityProviderList(idpJsonString);
        logger.info("Returning IDP details idpList:{}", idpList);

        return idpList;
    }
    
    public String getExtIDPToken(String idpAlias) throws JsonProcessingException {

        logger.info("Fetch Ext IDP Response for idpAlias:{} ",idpAlias);
        String token = getToken();
        logger.info("Token for IDP response is :{}", token);

        String url = samlUtil.getExtIDPTokenUrl(this.serverUrl, this.extIDPTokenUrl, this.realm, idpAlias);
        logger.info("URL for Ext IDP response is :{}", url);
        
        String idpJsonString = samlClient.getExtIDPTokenResponse(url, token);
        logger.info("Returning Ext IDP Response details idpJsonString:{}", idpJsonString);


        return idpJsonString;
    }

    public String getToken() throws JsonProcessingException {
        logger.info("Fetch Token for client");
        String token = samlClient.getAccessToken(samlUtil.getTokenUrl(this.serverUrl, this.tokenUrl, this.realm),
                this.clientId, this.clientSecret, this.grantType, this.scope, this.username, this.password,
                this.serverUrl);
        logger.info("Access token:{}", token);
        return token;
    }

    private Map<String, String> createIdentityProviderMap(String jsonIdentityProviderList)
            throws JsonProcessingException {
        logger.info("jsonIdentityProviderList:{}", jsonIdentityProviderList);
        Map<String, String> idpMap = null;
        if (StringUtils.isBlank(jsonIdentityProviderList)) {
            return idpMap;
        }

        JSONArray jsonArray = new JSONArray(jsonIdentityProviderList);
        int count = jsonArray.length(); // get totalCount of all jsonObjects
        idpMap = new HashMap<>();
        for (int i = 0; i < count; i++) { // iterate through jsonArray
            JSONObject jsonObject = jsonArray.getJSONObject(i); // get jsonObject @ i position
            logger.info(" i:{}, jsonObject:{}", i, jsonObject);
            if (jsonObject != null) {
                String jsonIdentityProvider = jsonObject.toString();
                logger.info(" jsonIdentityProvider:{}", jsonIdentityProvider);

                String alias = Jackson.getElement(jsonIdentityProvider, "alias");
                String displayName = Jackson.getElement(jsonIdentityProvider, "displayName");
                String singleSignOnServiceUrl = Jackson.getElement(jsonIdentityProvider, "singleSignOnServiceUrl");
                logger.info(" i:{},alias:{}, displayName:{}, singleSignOnServiceUrl:{}", i, alias, displayName, singleSignOnServiceUrl);

                if (StringUtils.isNotBlank(alias)) {
                    idpMap.put(alias, alias);
                }
            }
        }
        logger.info("idpMap:{}", idpMap);
        return idpMap;
    }

    private List<IdentityProvider> createIdentityProviderList(String jsonIdentityProviderList)
            throws JsonProcessingException {
        logger.info("jsonIdentityProviderList:{}", jsonIdentityProviderList);
        List<IdentityProvider> idpList = null;
        if (StringUtils.isBlank(jsonIdentityProviderList)) {
            return idpList;
        }

        JSONArray jsonArray = new JSONArray(jsonIdentityProviderList);
        int count = jsonArray.length(); // get totalCount of all jsonObjects
        idpList = new ArrayList<>();
        IdentityProvider idp = null;
        for (int i = 0; i < count; i++) { // iterate through jsonArray
            JSONObject jsonObject = jsonArray.getJSONObject(i); // get jsonObject @ i position
            logger.info(" i:{}, jsonObject{}:", i, jsonObject);
            if (jsonObject != null) {
                String jsonIdentityProvider = jsonObject.toString();
                logger.info(" jsonIdentityProvider:{}", jsonIdentityProvider);

                String alias = Jackson.getElement(jsonIdentityProvider, "alias");
                logger.info(" i:{},alias:{}", i, alias);

                String displayName = Jackson.getElement(jsonIdentityProvider, "displayName");
                logger.info(" i:{},displayName:{}", i, displayName);

                String singleSignOnServiceUrl = Jackson.getElement(jsonIdentityProvider, "singleSignOnServiceUrl");
                logger.info(" i:{},singleSignOnServiceUrl:{}", i, singleSignOnServiceUrl);

                logger.info(" i:{},alias:{}, displayName:{}, singleSignOnServiceUrl:{}", i, alias, displayName,
                        singleSignOnServiceUrl);
                logger.info(" i:{},alias:{}, displayName:{}", i, alias, displayName);

                idp = new IdentityProvider();
                if (StringUtils.isNotBlank(alias)) {
                    idp.setAlias(alias);
                    idp.setDisplayName(displayName);
                    idp.setSingleSignOnServiceUrl(singleSignOnServiceUrl);

                    idpList.add(idp);
                }
            }
        }
        logger.info("Finally idpList:{}", idpList);
        return idpList;
    }
    
    

}
