package de.kiessla.hetzner.dyndns;

import de.kiessla.hetzner.dyndns.dns.DnsService;
import de.kiessla.hetzner.dyndns.myip.MyIpService;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class Application extends TimerTask {

    private static final String API_TOKEN_ENV = "HETZNER_DNS_API_TOKEN";
    private static String apiKey;

    public static void main(String[] args) {
        apiKey = System.getenv(API_TOKEN_ENV);

        if (Objects.isNull(apiKey) || apiKey.length() == 0) {
            log.error("Unable to obtain API key from environment variable: " + API_TOKEN_ENV);
            log.error("Please provide your API key in this environment variable.");
            log.error("Exiting program ...");
            return;
        }

        new Timer().schedule(new Application(), 1000, 30000);
    }

    public static InputStream getResourceStream(String file) {
        return Application.class.getClassLoader()
                                .getResourceAsStream(file);
    }

    @Override
    public void run() {
        ApplicationProperties properties = new ApplicationProperties();
        MyIpService myIpService = new MyIpService();
        DnsService dnsService = new DnsService(apiKey);

        var zoneName = properties.getZoneName();
        var recordTtl = properties.getRecordTtl();
        var recordName = properties.getRecordName();

        var myIp = myIpService.getMyIp();
        var record = dnsService.getRecord(zoneName, recordName);

        if (record.isEmpty()) {
            log.error("Error while getting record {} in zone {}", zoneName, recordName);
            return;
        }

        if (myIp.equals(record.get()
                              .getValue())) {
            log.info("Public IP hasn't changed. No update needed.");
            return;
        }

        dnsService.updateRecord(record.get(),
                                myIp,
                                recordTtl);
    }
}
