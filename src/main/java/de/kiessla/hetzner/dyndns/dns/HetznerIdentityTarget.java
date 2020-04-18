package de.kiessla.hetzner.dyndns.dns;

import de.kiessla.hetzner.dyndns.ApplicationProperties;
import feign.Request;
import feign.RequestTemplate;
import feign.Target;

import java.nio.charset.StandardCharsets;

public class HetznerIdentityTarget implements Target<Dns> {

    private static final String HEADER_KEY_API_TOKEN = "Auth-API-Token";
    private static final String HEADER_KEY_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_VALUE_CONTENT_TYPE = "application/json; charset=utf-8";

    private final String apiKey;

    public HetznerIdentityTarget(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Class<Dns> type() {
        return Dns.class;
    }

    @Override
    public String name() {
        return HetznerIdentityTarget.class.getCanonicalName();
    }

    @Override
    public String url() {
        return new ApplicationProperties().getHetznerUrl();
    }

    @Override
    public Request apply(RequestTemplate input) {
        input.header(HEADER_KEY_API_TOKEN, apiKey);
        input.header(HEADER_KEY_CONTENT_TYPE, HEADER_VALUE_CONTENT_TYPE);

        return Request.create(Request.HttpMethod.valueOf(input.method()),
                              url().concat(input.url()),
                              input.headers(),
                              Request.Body.create(input.body(), StandardCharsets.UTF_8),
                              input);
    }
}
