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

    private static final SSOToken VALID_TOKEN = new SSOToken();
    private static final SSOToken INVALID_TOKEN = new SSOToken();

    @Test
    public void valid_SSO_token_say_hello() {
        SingleSignOnRegistry registry = mock(SingleSignOnRegistry.class);
        when(registry.isValid(VALID_TOKEN)).thenReturn(true);
        MyService service = new MyService(registry);

        Response response = service.handleRequest(new Request("Luis", VALID_TOKEN));

        assertEquals("Hello Luis!", response.getText());
    }

    @Test
    public void invalid_SSO_token_does_not_say_hello() {
        SingleSignOnRegistry registry = mock(SingleSignOnRegistry.class);
        when(registry.isValid(INVALID_TOKEN)).thenReturn(false);
        MyService service = new MyService(registry);

        Response response = service.handleRequest(new Request("Luis", INVALID_TOKEN));

        assertEquals("Invalid token", response.getText());
    }
}
