package de.kiessla.hetzner.dyndns.dns.zones;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Zone {
    private String id;
    private String name;
    private int ttl;
    private String registrar;

    @JsonAlias("legacy_dns_host")
    private String legacyDnsHost;

    @JsonAlias("legacy_ns")
    private List<String> legacyNs;
    private List<String> ns;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z z")
    private Date created;

    // 2020-04-07 01:54:20.033384945 +0000 UTC m=+652.159757288
    private String verified;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z z")
    private String modified;
    private String project;
    private String owner;
    private String permission;

    @JsonAlias("zone_type")
    private ZoneType zoneType;
    private String status;
    private boolean paused;

    @JsonAlias("is_secondary_dns")
    private boolean isSecondaryDns;

    @JsonAlias("txt_verification")
    private TxtVerification txtVerification;

    @JsonAlias("records_count")
    private int recordsCount;
}
