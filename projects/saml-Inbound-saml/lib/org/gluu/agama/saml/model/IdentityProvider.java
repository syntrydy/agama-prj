/*
 * Janssen Project software is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2020, Janssen Project
 */

package org.gluu.agama.saml.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentityProvider implements Serializable {

    private static final long serialVersionUID = -1475615134050618334L;

    protected String alias;
    
    protected String displayName;
    
    protected String internalId;
    
    protected String providerId;
    
    protected boolean enabled = true;
    
    protected boolean trustEmail;
    
    protected boolean storeToken;
    
    protected boolean addReadTokenRoleOnCreate;
    
    protected boolean authenticateByDefault;
    
    protected boolean linkOnly;
    
    protected String firstBrokerLoginFlowAlias;
    
    protected String postBrokerLoginFlowAlias;
    
    protected Map<String, String> config = new HashMap<>();

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

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public Map<String, String> getConfig() {
        return config;
    }

    public void setConfig(Map<String, String> config) {
        this.config = config;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "IdentityProvider [alias=" + alias + ", displayName=" + displayName + ", internalId=" + internalId
                + ", providerId=" + providerId + ", enabled=" + enabled + ", trustEmail=" + trustEmail + ", storeToken="
                + storeToken + ", addReadTokenRoleOnCreate=" + addReadTokenRoleOnCreate + ", authenticateByDefault="
                + authenticateByDefault + ", linkOnly=" + linkOnly + ", firstBrokerLoginFlowAlias="
                + firstBrokerLoginFlowAlias + ", postBrokerLoginFlowAlias=" + postBrokerLoginFlowAlias + ", config="
                + config + "]";
    }

}
