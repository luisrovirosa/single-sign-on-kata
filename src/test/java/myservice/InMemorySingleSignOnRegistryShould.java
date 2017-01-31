package myservice;

import org.junit.Test;
import sso.AuthenticationGateway;
import sso.SSOToken;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class InMemorySingleSignOnRegistryShould {

    public static final String AN_USERNAME = "luisrovirosa";
    public static final String A_PASSWORD = "aPassword";

    @Test
    public void return_a_sso_token_when_register_new_session() {
        InMemorySingleSignOnRegistry registry = registry();

        SSOToken ssoToken = registry.registerNewSession(AN_USERNAME, A_PASSWORD);

        assertThat(ssoToken, not(nullValue()));
    }

    @Test
    public void be_valid_a_token_provided_by_registry() {
        InMemorySingleSignOnRegistry registry = registry();
        SSOToken ssoToken = registry.registerNewSession(AN_USERNAME, A_PASSWORD);

        boolean isValid = registry.isValid(ssoToken);

        assertThat(isValid, is(true));
    }

    @Test
    public void be_invalid_a_token_not_provided_by_registry() {
        InMemorySingleSignOnRegistry registry = registry();
        SSOToken ssoToken = new SSOToken();

        boolean isValid = registry.isValid(ssoToken);

        assertThat(isValid, is(false));
    }

    @Test
    public void be_invalid_a_token_provided_by_registry_after_unregister() {
        InMemorySingleSignOnRegistry registry = registry();
        SSOToken ssoToken = registry.registerNewSession(AN_USERNAME, A_PASSWORD);
        registry.unregister(ssoToken);

        boolean isValid = registry.isValid(ssoToken);

        assertThat(isValid, is(false));
    }

    private InMemorySingleSignOnRegistry registry() {
        AuthenticationGateway mock = mock(AuthenticationGateway.class);
        return new InMemorySingleSignOnRegistry(mock);
    }
}
