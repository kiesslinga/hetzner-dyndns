package de.kiessla.hetzner.dyndns.dns.zones;

import lombok.Data;

@Data
public class ZoneType {
    private String id;
    private String name;
    private String description;
    private Object prices;
}
