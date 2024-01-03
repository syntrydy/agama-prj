### Config parameters

```
{
    "AS_ENDPOINT": "https://account-dev.gluu.cloud",
    "AS_SSA": "ey...1BfBZQ0g",
    "AS_CLIENT_ID": "b71618...de21995120",
    "AS_CLIENT_SECRET": "52b9...9c505",
    "AS_REDIRECT_URI": "https://account-dev.gluu.cloud/.well-known/openid-configuration",
    "PORTAL_JWKS": "https://account-dev.gluu.cloud/jans-auth/restv1/jwks",
    "PASSWURD_KEY_A_KEYSTORE": "/etc/certs/passwurd_api.pkcs12",
    "PASSWURD_KEY_A_PASSWORD": "changeit",
    "PASSWURD_API_URL": "https://cloud-dev.gluu.cloud/scan/passwurd",
    "ORG_ID": "github:m...ena"
  }
```


### Feature - Agama project for inbound SAML feature.

1. SAML Discover Page

2. Redirect to appropraite SAML IDP Flow 

3. Create IDP


### Assumptions

1. Jans Server with SAML fetaure is installed.



### Cases in SAML Inbound API dont work

1. If no underlying IDP 


