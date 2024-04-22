package org.gluu.agama.saml.model;

import java.io.Serializable;

public class SamlConfig  implements Serializable {

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
    
    public SamlConfig() {}
        
    public SamlConfig(String serverUrl, String realm, String clientId, String clientSecret, String grantType,
            String scope, String username, String password, String spMetadataUrl, String tokenUrl, String idpUrl, String extIDPTokenUrl) {
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
    }
    
    public String getServerUrl() {
        return serverUrl;
    }
    
    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
    
    public String getRealm() {
        return realm;
    }
    
    public void setRealm(String realm) {
        this.realm = realm;
    }
    
    public String getClientId() {
        return clientId;
    }
    
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    public String getClientSecret() {
        return clientSecret;
    }
    
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
    
    public String getGrantType() {
        return grantType;
    }
    
    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
    
    public String getScope() {
        return scope;
    }
    
    public void setScope(String scope) {
        this.scope = scope;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getSpMetadataUrl() {
        return spMetadataUrl;
    }
    
    public void setSpMetadataUrl(String spMetadataUrl) {
        this.spMetadataUrl = spMetadataUrl;
    }
    
    public String getTokenUrl() {
        return tokenUrl;
    }
    
    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }
    
    public String getIdpUrl() {
        return idpUrl;
    }
    
    public void setIdpUrl(String idpUrl) {
        this.idpUrl = idpUrl;
    }
    
    public String getExtIDPTokenUrl() {
        return extIDPTokenUrl;
    }
    
    public void setExtIDPTokenUrl(String extIDPTokenUrl) {
        this.extIDPTokenUrl = extIDPTokenUrl;
    }
    
    
    @Override
    public String toString() {
        return "SamlConfig [serverUrl=" + serverUrl + ", realm=" + realm + ", clientId=" + clientId + ", clientSecret="
                + clientSecret + ", grantType=" + grantType + ", scope=" + scope + ", username=" + username
                + ", password=" + password + ", spMetadataUrl=" + spMetadataUrl + ", tokenUrl=" + tokenUrl + ", idpUrl="
                + idpUrl + ", extIDPTokenUrl=" + extIDPTokenUrl + "]";
    }
    
    
}
