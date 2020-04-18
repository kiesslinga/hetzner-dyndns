package de.kiessla.hetzner.dyndns.myip;

import de.kiessla.hetzner.dyndns.ApplicationProperties;
import feign.Feign;
import feign.Logger;

public class MyIpService {

    private final MyIp myIp;

    public MyIpService() {
        myIp = Feign.builder()
                    .logger(new Logger.ErrorLogger())
                    .logLevel(Logger.Level.BASIC)
                    .target(MyIp.class, new ApplicationProperties().getMyIpUrl());
    }

    public String getMyIp() {
        return myIp.get();
    }

}
