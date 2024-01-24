package org.gluu.agama.saml.client;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SamlClient {
    
    
    private static final Logger logger = LoggerFactory.getLogger(SamlClient.class);

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer  ";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String ACCESS_TOKEN_NULL = "Access Token is null!!!";
    public static final String IDP_URL_NULL = "IDP URL is null!!!";
    public static final String ACCESS_TOKEN = "access_token";

    public SamlClient(){}
    
    public static String getAccessToken(final String tokenUrl, final String clientId, final String clientSecret,
            final String grantType, final String scope, final String username, final String password,
            final String serverUrl) throws Exception {
        logger.info("Get  tokenUrl:{}, clientId:{}, grantType:{}, scope:{}, username:{}, serverUrl:{}", tokenUrl,
                clientId, grantType, scope, username, serverUrl);

        Builder request = getClientBuilder(tokenUrl);
        request.header(AUTHORIZATION, "Basic " + clientId + ":" + clientSecret);
        request.header(CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED);

        final MultivaluedHashMap<String, String> multivaluedHashMap = new MultivaluedHashMap<>();
        multivaluedHashMap.add("client_id", clientId);
        multivaluedHashMap.add("client_secret", clientSecret);
        multivaluedHashMap.add("grant_type",
                (StringUtils.isNotBlank(grantType) ? grantType.toLowerCase() : "password"));
        multivaluedHashMap.add("scope", scope);
        multivaluedHashMap.add("username", username);
        multivaluedHashMap.add("password", password);
        multivaluedHashMap.add("redirect_uri", serverUrl);
        Response response = request.post(Entity.form(multivaluedHashMap));

        String token = null;
        if (response != null) {
            logger.trace(
                    "Response for Access Token -  response.getStatus():{}, response.getStatusInfo():{}, response.getEntity().getClass():{}",
                    response.getStatus(), response.getStatusInfo(), response.getEntity().getClass());
            String entity = response.readEntity(String.class);
            if (response.getStatusInfo().equals(Status.OK)) {
                token = Jackson.getElement(entity, ACCESS_TOKEN);
            } else {
                throw new WebApplicationException(
                        "Error while Access Token is " + response.getStatusInfo() + " - " + entity, response);
            }
        }

        return token;
    }

    public String getAllIdp(String idpUrl, String token) {
        logger.info(" All IDP - idpUrl:{}", idpUrl);

        Builder client = getClientBuilder(idpUrl);
        client.header(CONTENT_TYPE, MediaType.APPLICATION_JSON);
        client.header(AUTHORIZATION, BEARER + token);
        Response response = client.get();
        logger.debug("All IDP - response:{}", response);

        String identityProviderJsonList = null;
        if (response != null) {
            logger.trace(
                    "Fetch all IDP response.getStatus():{}, response.getStatusInfo():{}, response.getEntity().getClass():{}",
                    response.getStatus(), response.getStatusInfo(), response.getEntity().getClass());
            String entity = response.readEntity(String.class);
            logger.trace("Get All IDP entity:{}", entity);
            if (response.getStatusInfo().equals(Status.OK)) {

                identityProviderJsonList = entity;
            } else {
                throw new WebApplicationException(
                        "Error while fetching All IDP is " + response.getStatusInfo() + " - " + entity, response);
            }
        }

        return identityProviderJsonList;
    }
    
    private static Builder getClientBuilder(String url) {
        return ClientBuilder.newClient().target(url).request();
    }

}
