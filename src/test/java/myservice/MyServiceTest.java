package myservice;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import sso.Request;
import sso.Response;
import sso.SSOToken;
import sso.SingleSignOnRegistry;

public class MyServiceTest {

    @Test
    public void valid_SSO_token_say_hello() {
        SingleSignOnRegistry registry = mock(SingleSignOnRegistry.class);
        SSOToken validToken = new SSOToken();
        when(registry.isValid(validToken)).thenReturn(true);
        MyService service = new MyService(registry);

        Response response = service.handleRequest(new Request("Luis", validToken));

        assertEquals("Hello Luis!", response.getText());
    }

    @Test
    public void invalid_SSO_token_does_not_say_hello() {
        SingleSignOnRegistry registry = mock(SingleSignOnRegistry.class);
        SSOToken invalidToken = new SSOToken();
        when(registry.isValid(invalidToken)).thenReturn(false);
        MyService service = new MyService(registry);

        Response response = service.handleRequest(new Request("Luis", invalidToken));

        assertEquals("Invalid token", response.getText());
    }
}
