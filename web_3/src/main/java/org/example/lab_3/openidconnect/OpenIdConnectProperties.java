package org.example.lab_3.openidconnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class OpenIdConnectProperties {

    @Autowired
    private Environment environment;

    public String getOpenIdConnectEndpoint() {
        return environment.getProperty("openidconnect.endpoint");
    }

    public String getOpenIdConnectClientId() {
        return environment.getProperty("openidconnect.clientId");
    }

    public String getOpenIdConnectClientSecret() {
        return environment.getProperty("openidconnect.clientSecret");
    }
}
