package de.kiessla.hetzner.dyndns.dns.records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class Record {
    private String id;
    private String type;
    private String name;
    private String value;
    private int ttl;

    @JsonAlias("zone_id")
    private String zoneId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z z")
    private String created;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z z")
    private String modified;
}
