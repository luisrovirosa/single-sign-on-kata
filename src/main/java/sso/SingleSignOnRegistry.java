package sso;

public interface SingleSignOnRegistry {

    SSOToken registerNewSession(String userName, String password);

    boolean isValid(SSOToken token);

    void unregister(SSOToken token);

}