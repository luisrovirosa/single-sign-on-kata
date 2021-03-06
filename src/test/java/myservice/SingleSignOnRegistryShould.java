package myservice;

import org.junit.Before;
import org.junit.Test;
import sso.AuthenticationGateway;
import sso.SSOToken;
import sso.SingleSignOnRegistry;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class SingleSignOnRegistryShould {

    public static final String VALID_USERNAME = "luisrovirosa";
    public static final String VALID_PASSWORD = "aPassword";
    protected AuthenticationGateway gateway;

    @Before
    public void setUp() throws Exception {
        gateway = mock(AuthenticationGateway.class);
        when(gateway.credentialsAreValid(VALID_USERNAME, VALID_PASSWORD)).thenReturn(true);
    }

    @Test
    public void return_a_sso_token_when_register_new_session_with_valid_credentials() {
        SingleSignOnRegistry registry = registry();

        SSOToken ssoToken = registry.registerNewSession(VALID_USERNAME, VALID_PASSWORD);

        assertThat(ssoToken, not(nullValue()));
    }

    @Test
    public void return_a_null_token_when_register_new_session_with_invalid_credentials() {
        SingleSignOnRegistry registry = registry();

        SSOToken ssoToken = registry.registerNewSession("invalidUsername", "invalidPassword");

        assertThat(ssoToken, is(nullValue()));
    }

    @Test
    public void be_valid_a_token_provided_by_registry() {
        SingleSignOnRegistry registry = registry();
        SSOToken ssoToken = registry.registerNewSession(VALID_USERNAME, VALID_PASSWORD);

        boolean isValid = registry.isValid(ssoToken);

        assertThat(isValid, is(true));
    }

    @Test
    public void be_invalid_a_token_not_provided_by_registry() {
        SingleSignOnRegistry registry = registry();
        SSOToken ssoToken = new SSOToken();

        boolean isValid = registry.isValid(ssoToken);

        assertThat(isValid, is(false));
    }

    @Test
    public void be_invalid_a_token_provided_by_registry_after_unregister() {
        SingleSignOnRegistry registry = registry();
        SSOToken ssoToken = registry.registerNewSession(VALID_USERNAME, VALID_PASSWORD);
        registry.unregister(ssoToken);

        boolean isValid = registry.isValid(ssoToken);

        assertThat(isValid, is(false));
    }

    protected abstract SingleSignOnRegistry registry();
}
