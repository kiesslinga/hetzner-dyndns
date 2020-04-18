package de.kiessla.hetzner.dyndns.myip;

import feign.RequestLine;

public interface MyIp {

    @RequestLine("GET /")
    String get();

}
