package myservice;

import sso.Request;
import sso.Response;
import sso.SingleSignOnRegistry;

public class MyService {

    private SingleSignOnRegistry registry;

    public MyService(SingleSignOnRegistry registry) {
        this.registry = registry;
    }

    public Response handleRequest(Request request) {
        return new Response("Hello " + request.getName() + "!");
    }
}
