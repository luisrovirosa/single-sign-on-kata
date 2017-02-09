package myservice;

import sso.SingleSignOnRegistry;

public class InMemorySingleSignOnRegistryShould extends SingleSignOnRegistryShould {
    protected SingleSignOnRegistry registry() {
        return new InMemorySingleSignOnRegistry(gateway);
    }
}
