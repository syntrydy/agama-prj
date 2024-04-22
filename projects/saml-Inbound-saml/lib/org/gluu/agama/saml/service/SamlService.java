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
    private SamlConfig samlConfig;
    private SamlUtil samlUtil = new SamlUtil();

    public SamlService() {
        logger.error("SamlService constructor");
        logger.error(" this.serverUrl :{}", this.serverUrl);
    }

    public SamlService(String serverUrl, String realm, String clientId, String clientSecret, String grantType,
            String scope, String username, String password, String spMetadataUrl, String tokenUrl, String idpUrl,
            String extIDPTokenUrl) {
        logger.error(
                "Get  serverUrl:{}, realm:{}, clientId:{}, clientSecret:{}, grantType:{}, scope:{}, username:{}, password:{}, spMetadataUrl:{}, tokenUrl:{}, idpUrl:{}, extIDPTokenUrl:{}",
                serverUrl, realm, clientId, clientSecret, grantType, scope, username, password, spMetadataUrl, tokenUrl,
                idpUrl, extIDPTokenUrl);

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
        this.extIDPTokenUrl = extIDPTokenUrl;

        this.samlConfig = new SamlConfig(serverUrl, realm, clientId, clientSecret, grantType, scope, username, password,
                spMetadataUrl, tokenUrl, idpUrl, extIDPTokenUrl);
        logger.error("SamlService instance created - samlConfig:{} ", samlConfig);
    }

    public Map<String, List> getIdps() throws JsonProcessingException {
        logger.info("Get All idp ");
        Map<String, List> idpMap = new HashMap<>();

        List<IdentityProvider> idpList = getIdpList();
        logger.info("idpList:{}", idpList);
        idpMap.put("idps", idpList);
        logger.info("Returning IDP details idpMap:{}", idpMap);
        return idpMap;
    }
    
    private List<IdentityProvider> getIdpList() throws JsonProcessingException {

        logger.info("Fetch All IDP details");
        String token = getToken();
        logger.info("Access token:{}", token);

        String idpUrl = samlUtil.getIdpUrl(this.serverUrl, this.idpUrl, this.realm);
        String idpJsonString = samlClient.getAllIdp(idpUrl, token);
        logger.info("\n Returning IDP details idpJsonString:{}", idpJsonString);
        List<IdentityProvider> idpList = createIdentityProviderList(idpJsonString);
        logger.info("Returning IDP details idpList:{}", idpList);

        return idpList;
    }
    
    public Map<String, Object> getIdpData(String idpAlias) throws JsonProcessingException, IOException {
        logger.info("Get External IDP Data idpAlias:{}",idpAlias);
        Map<String, Object> idpData = new HashMap<>();

        IdentityProvider idp = getIdpDetails(idpAlias);
        logger.info("idp:{}", idp);
        idpData.put("idp", idp);
        //idpData.put("idpUrl", "https://pujavs-advanced-ewe.gluu.info/kc/realms/jans/protocol/openid-connect/auth?client_id=jans-307f57ee-8978-4426-8405-137e64bc4754&redirect_uri=https://pujavs-advanced-ewe.gluu.info/kc/realms/jans/account&response_type=code&kc_idp_hint=busy-starfish.gluu.info");
        idpData.put("idpUrl", "https://pujavs-advanced-ewe.gluu.info/kc/realms/jans/protocol/openid-connect/auth?client_id=jans-307f57ee-8978-4426-8405-137e64bc4754&redirect_uri=https://pujavs-advanced-ewe.gluu.info/jans-auth/fl/callback&response_type=code&kc_idp_hint=busy-starfish.gluu.info");
        
        String redirectUrl = getRedirectUrl(idp);
        logger.info("Returning IDP details redirectUrl:{}, idpData:{}", redirectUrl, idpData);
        return idpData;
    }

    private IdentityProvider getIdpDetails(String idpAlias) throws JsonProcessingException, IOException {

        logger.info("Fetch IDP details - idpAlias:{}, this.idpUrl:{}, samlConfig.getIdpUrl():{} ", idpAlias,
                this.idpUrl, samlConfig.getIdpUrl());
        String token = getToken();
        logger.info("Access token:{}", token);
        IdentityProvider idp = null;
        if (StringUtils.isBlank(idpAlias)) {
            return idp;
        }

        logger.info("Fetch IDP details for idp:{}", idp);
        String json = samlClient.getIdpDetails(this.idpUrl, idpAlias, token);
        logger.info("IDP json:{}", json);
        idp = this.createIdentityProvider(json);
        logger.info("IDP idpAlias:{}, idp:{}", idpAlias, idp);

        return idp;
    }

    private List<IdentityProvider> getIdpDetails(String idpUrl, List<IdentityProvider> idpList)
            throws JsonProcessingException, IOException {

        logger.info("Fetch IDP details - idpUrl:{}, idpList:{} ", idpUrl, idpList);
        String token = getToken();
        logger.info("Access token:{}", token);

        if (idpList == null || idpList.isEmpty()) {
            return idpList;
        }

        for (IdentityProvider idp : idpList) {
            logger.info("Fetch IDP details for idp:{}", idp);
            String json = samlClient.getIdpDetails(idpUrl, idp.getAlias(), token);
            logger.info("IDP json:{}", json);
            idp = this.createIdentityProvider(json);
        }

        return idpList;
    }

    public String getExtIDPToken(String idpAlias) throws JsonProcessingException {

        logger.info("Fetch Ext IDP Response for idpAlias:{} ", idpAlias);
        String token = getToken();
        logger.info("Token for IDP response is :{}", token);

        String url = samlUtil.getExtIDPTokenUrl(this.serverUrl, this.extIDPTokenUrl, this.realm, idpAlias);
        logger.info("URL for Ext IDP response is :{}", url);

        String idpJsonString = samlClient.getExtIDPTokenResponse(url, token);
        logger.info("Returning Ext IDP Response details idpJsonString:{}", idpJsonString);

        return idpJsonString;
    }

    private String getToken() throws JsonProcessingException {
        logger.info("Fetch Token for client");
        String token = samlClient.getAccessToken(samlUtil.getTokenUrl(this.serverUrl, this.tokenUrl, this.realm),
                this.clientId, this.clientSecret, this.grantType, this.scope, this.username, this.password,
                this.serverUrl);
        logger.info("Access token:{}", token);
        return token;
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
        idpList = new ArrayList<IdentityProvider>();
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

                String internalId = Jackson.getElement(jsonIdentityProvider, "internalId");
                logger.info(" i:{},internalId:{}", i, internalId);

                idp = new IdentityProvider();
                if (StringUtils.isNotBlank(alias)) {
                    idp.setAlias(alias);
                    idp.setDisplayName(displayName);
                    idp.setInternalId("123");
                    idpList.add(idp);
                }
            }
        }
        logger.info("Finally idpList:{}", idpList);
        return idpList;
    }

    private IdentityProvider createIdentityProvider(String jsonIdentityProvider)
            throws JsonProcessingException, IOException {
        logger.info("jsonIdentityProvider:{}", jsonIdentityProvider);
        IdentityProvider idp = null;
        if (StringUtils.isBlank(jsonIdentityProvider)) {
            return idp;
        }

        idp = Jackson.getObject(jsonIdentityProvider, idp);
        logger.info("idp:{}", idp);

        return idp;
    }
    
    private String getRedirectUrl(IdentityProvider idp) {
        logger.info("Create Redirect Url - idp:{}",idp );
        //https://pujavs-advanced-ewe.gluu.info/kc/realms/jans/protocol/
        //openid-connect/auth?client_id=jans-307f57ee-8978-4426-8405-137e64bc4754
        //&redirect_uri=https://pujavs-advanced-ewe.gluu.info/jans-auth/agama.saml/callback&response_type=code&kc_idp_hint=busy-starfish.gluu.info"
        StringBuilder sb = new StringBuilder();
        sb.append(this.idpUrl);
        sb.append(this.realm);
        sb.append("/protocol/openid-connect/auth");
        sb.append("?client_id=");
        sb.append(this.clientId);
        sb.append("&redirect_uri=");
        sb.append("https://pujavs-advanced-ewe.gluu.info/jans-auth/fl/callback");
        
        logger.info("Create Redirect Url - sb:{}",sb );
        
        return sb.toString();
    }

}
