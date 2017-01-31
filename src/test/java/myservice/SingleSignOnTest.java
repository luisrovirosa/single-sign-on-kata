package myservice;

import org.junit.Ignore;
import org.junit.Test;
import sso.Request;
import sso.Response;
import sso.SSOToken;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SingleSignOnTest {

    @Test
    @Ignore
    public void after_login_its_possible_use_my_service_with_sso_token(){
        InMemorySingleSignOnRegistry registry = new InMemorySingleSignOnRegistry();
        MyService service = new MyService(registry);

        SSOToken ssoToken = registry.registerNewSession("luisrovirosa", "validPassword");
        Response response = service.handleRequest(new Request("Luis", ssoToken));

        assertThat(response.getText(), is("Hello Luis!"));
    }
}
