package de.kiessla.hetzner.dyndns.dns.records;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordUpdate {
    private String name;
    private int ttl;
    private String type;
    private String value;

    @JsonProperty("zone_id")
    private String zoneId;
}
