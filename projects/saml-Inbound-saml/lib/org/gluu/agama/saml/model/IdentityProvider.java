/*
 * Janssen Project software is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2020, Janssen Project
 */

package org.gluu.agama.saml.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentityProvider implements Serializable {


    private static final long serialVersionUID = -1475615134050618334L;

    private String alias;

    private String displayName;

    private String description;
        
    private String realm;

    private String signingCertificate;
    
    private String validateSignature;
       
    private String singleLogoutServiceUrl;  
    
    private String nameIDPolicyFormat;
    
    private String idpEntityId;
    
    private String singleSignOnServiceUrl;
    
    private String encryptionPublicKey;
    
    private String providerId;

    protected boolean trustEmail;

    protected boolean storeToken;

    protected boolean addReadTokenRoleOnCreate;

    protected boolean authenticateByDefault;

    protected boolean linkOnly;

    protected String firstBrokerLoginFlowAlias;

    protected String postBrokerLoginFlowAlias;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getSigningCertificate() {
        return signingCertificate;
    }

    public void setSigningCertificate(String signingCertificate) {
        this.signingCertificate = signingCertificate;
    }

    public String getValidateSignature() {
        return validateSignature;
    }

    public void setValidateSignature(String validateSignature) {
        this.validateSignature = validateSignature;
    }

    public String getSingleLogoutServiceUrl() {
        return singleLogoutServiceUrl;
    }

    public void setSingleLogoutServiceUrl(String singleLogoutServiceUrl) {
        this.singleLogoutServiceUrl = singleLogoutServiceUrl;
    }

    public String getNameIDPolicyFormat() {
        return nameIDPolicyFormat;
    }

    public void setNameIDPolicyFormat(String nameIDPolicyFormat) {
        this.nameIDPolicyFormat = nameIDPolicyFormat;
    }

    public String getIdpEntityId() {
        return idpEntityId;
    }

    public void setIdpEntityId(String idpEntityId) {
        this.idpEntityId = idpEntityId;
    }

    public String getSingleSignOnServiceUrl() {
        return singleSignOnServiceUrl;
    }

    public void setSingleSignOnServiceUrl(String singleSignOnServiceUrl) {
        this.singleSignOnServiceUrl = singleSignOnServiceUrl;
    }

    public String getEncryptionPublicKey() {
        return encryptionPublicKey;
    }

    public void setEncryptionPublicKey(String encryptionPublicKey) {
        this.encryptionPublicKey = encryptionPublicKey;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public boolean isTrustEmail() {
        return trustEmail;
    }

    public void setTrustEmail(boolean trustEmail) {
        this.trustEmail = trustEmail;
    }

    public boolean isStoreToken() {
        return storeToken;
    }

    public void setStoreToken(boolean storeToken) {
        this.storeToken = storeToken;
    }

    public boolean isAddReadTokenRoleOnCreate() {
        return addReadTokenRoleOnCreate;
    }

    public void setAddReadTokenRoleOnCreate(boolean addReadTokenRoleOnCreate) {
        this.addReadTokenRoleOnCreate = addReadTokenRoleOnCreate;
    }

    public boolean isAuthenticateByDefault() {
        return authenticateByDefault;
    }

    public void setAuthenticateByDefault(boolean authenticateByDefault) {
        this.authenticateByDefault = authenticateByDefault;
    }

    public boolean isLinkOnly() {
        return linkOnly;
    }

    public void setLinkOnly(boolean linkOnly) {
        this.linkOnly = linkOnly;
    }

    public String getFirstBrokerLoginFlowAlias() {
        return firstBrokerLoginFlowAlias;
    }

    public void setFirstBrokerLoginFlowAlias(String firstBrokerLoginFlowAlias) {
        this.firstBrokerLoginFlowAlias = firstBrokerLoginFlowAlias;
    }

    public String getPostBrokerLoginFlowAlias() {
        return postBrokerLoginFlowAlias;
    }

    public void setPostBrokerLoginFlowAlias(String postBrokerLoginFlowAlias) {
        this.postBrokerLoginFlowAlias = postBrokerLoginFlowAlias;
    }

    @Override
    public String toString() {
        return "IdentityProvider [alias=" + alias + ", displayName=" + displayName + ", description=" + description
                + ", realm=" + realm + ", signingCertificate=" + signingCertificate + ", validateSignature="
                + validateSignature + ", singleLogoutServiceUrl=" + singleLogoutServiceUrl + ", nameIDPolicyFormat="
                + nameIDPolicyFormat + ", idpEntityId=" + idpEntityId + ", singleSignOnServiceUrl="
                + singleSignOnServiceUrl + ", encryptionPublicKey=" + encryptionPublicKey + ", providerId=" + providerId
                + ", trustEmail=" + trustEmail + ", storeToken=" + storeToken + ", addReadTokenRoleOnCreate="
                + addReadTokenRoleOnCreate + ", authenticateByDefault=" + authenticateByDefault + ", linkOnly="
                + linkOnly + ", firstBrokerLoginFlowAlias=" + firstBrokerLoginFlowAlias + ", postBrokerLoginFlowAlias="
                + postBrokerLoginFlowAlias + "]";
    }
    
      
}
