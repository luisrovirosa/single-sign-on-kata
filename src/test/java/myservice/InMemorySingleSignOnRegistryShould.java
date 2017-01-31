package myservice;

import org.junit.Test;
import sso.SSOToken;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class InMemorySingleSignOnRegistryShould {

    public static final String AN_USERNAME = "luisrovirosa";
    public static final String A_PASSWORD = "aPassword";

    @Test
    public void return_a_sso_token_when_register_new_session() {
        InMemorySingleSignOnRegistry registry = new InMemorySingleSignOnRegistry();

        SSOToken ssoToken = registry.registerNewSession(AN_USERNAME, A_PASSWORD);

        assertThat(ssoToken, not(nullValue()));
    }

    @Test
    public void be_valid_a_token_provided_by_registry() {
        InMemorySingleSignOnRegistry registry = new InMemorySingleSignOnRegistry();
        SSOToken ssoToken = registry.registerNewSession(AN_USERNAME, A_PASSWORD);

        boolean isValid = registry.isValid(ssoToken);

        assertThat(isValid, is(true));
    }

    @Test
    public void be_invalid_a_token_not_provided_by_registry() {
        InMemorySingleSignOnRegistry registry = new InMemorySingleSignOnRegistry();
        SSOToken ssoToken = new SSOToken();

        boolean isValid = registry.isValid(ssoToken);

        assertThat(isValid, is(false));
    }
}
