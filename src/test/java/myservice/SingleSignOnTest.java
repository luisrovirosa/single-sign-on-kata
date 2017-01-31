package myservice;

import org.junit.Ignore;
import org.junit.Test;
import sso.AuthenticationGateway;
import sso.Request;
import sso.Response;
import sso.SSOToken;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SingleSignOnTest {

    public static final String VALID_USER_NAME = "luisrovirosa";
    public static final String VALID_PASSWORD = "validPassword";

    @Test
    @Ignore
    public void after_login_its_possible_use_my_service_with_sso_token(){
        AuthenticationGateway authenticationGateway = new DummyAuthenticationGateway();
        InMemorySingleSignOnRegistry registry = new InMemorySingleSignOnRegistry(authenticationGateway);
        MyService service = new MyService(registry);

        SSOToken ssoToken = registry.registerNewSession(VALID_USER_NAME, VALID_PASSWORD);
        Response response = service.handleRequest(new Request("Luis", ssoToken));

        assertThat(response.getText(), is("Hello Luis!"));
    }

    private class DummyAuthenticationGateway implements AuthenticationGateway {
        public boolean credentialsAreValid(String username, String password) {
            return username.equals(VALID_USER_NAME) && password.equals(VALID_PASSWORD);
        }
    }
}
