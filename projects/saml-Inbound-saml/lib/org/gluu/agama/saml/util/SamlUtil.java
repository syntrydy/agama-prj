package org.gluu.agama.saml.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SamlUtil {

    private static final Logger LOG = LoggerFactory.getLogger(SamlUtil.class);

    public SamlUtil() {
    }

    public String getSpMetadataUrl(String serverUrl, String metadataUrl, String realm, String idpName) {
        LOG.info("Get SP Metadata Url - serverUrl:{}, metadataUrl:{}, realm:{}, serverUrl, idpName:{}", serverUrl,
                metadataUrl, realm, idpName);

        StringBuilder sb = new StringBuilder();
        sb.append(serverUrl).append(metadataUrl);
        String spMetadataUrl = String.format(sb.toString(), realm, idpName);
        LOG.info("SP Metadata Url - spMetadataUrl:{}", spMetadataUrl);
        return spMetadataUrl;
    }

    public String getTokenUrl(String serverUrl, String partialTokenUrl, String realm) {
        LOG.info("Get SAML Token Url - serverUrl:{}, partialTokenUrl, realm:{}", serverUrl, partialTokenUrl, realm);

        StringBuilder sb = new StringBuilder();
        sb.append(serverUrl).append(partialTokenUrl);
        String tokenUrl = String.format(sb.toString(), realm);

        LOG.info("SAML Token Url - tokenUrl:{}", tokenUrl);
        return tokenUrl;
    }

    public String getIdpUrl(String serverUrl, String partialIdpUrl, String realm) {
        LOG.info("Get SAML IDP Url - serverUrl:{}, partialIdpUrl:{}, realm:{}", serverUrl, partialIdpUrl, realm);

        StringBuilder sb = new StringBuilder();
        sb.append(serverUrl).append(partialIdpUrl);
        String idpUrl = String.format(sb.toString(), realm);

        LOG.info("SAML IDP Url - idpUrl:{}", idpUrl);
        return idpUrl;
    }
    
    public String getExtIDPTokenUrl(String serverUrl, String partialExtIDPTokenUrl, String realm, String idpAlias) {
        LOG.info("Get SAML IDP Url - serverUrl:{}, partialExtIDPTokenUrl:{}, realm:{}, idpAlias:{}", serverUrl, partialExtIDPTokenUrl, realm, idpAlias);

        StringBuilder sb = new StringBuilder();
        sb.append(serverUrl).append(partialExtIDPTokenUrl);
        LOG.info("getExtIDPTokenUrl - sb:{}", sb);
        String extIDPTokenUrl = String.format(sb.toString(), realm, idpAlias);

        LOG.info("extIDPTokenUrl:{}", extIDPTokenUrl);
        return extIDPTokenUrl;
    }


}
