package myservice;

import sso.SSOToken;
import sso.SingleSignOnRegistry;

public class InMemorySingleSignOnRegistry implements SingleSignOnRegistry {
    public SSOToken registerNewSession(String userName, String password) {
        return new SSOToken();
    }

    public boolean isValid(SSOToken token) {
        return false;
    }

    public void unregister(SSOToken token) {

    }
}
