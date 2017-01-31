package myservice;

import sso.AuthenticationGateway;
import sso.SSOToken;
import sso.SingleSignOnRegistry;

import java.util.ArrayList;
import java.util.List;

public class InMemorySingleSignOnRegistry implements SingleSignOnRegistry {

    private final List<SSOToken> validTokens = new ArrayList<SSOToken>();
    private final AuthenticationGateway authenticationGateway;

    public InMemorySingleSignOnRegistry(AuthenticationGateway authenticationGateway) {
        this.authenticationGateway = authenticationGateway;
    }

    public SSOToken registerNewSession(String userName, String password) {
        if (!authenticationGateway.credentialsAreValid(userName, password)){
            return null;
        }
        SSOToken ssoToken = new SSOToken();
        validTokens.add(ssoToken);
        return ssoToken;
    }

    public boolean isValid(SSOToken token) {
        return validTokens.contains(token);
    }

    public void unregister(SSOToken token) {
        validTokens.remove(token);
    }
}
