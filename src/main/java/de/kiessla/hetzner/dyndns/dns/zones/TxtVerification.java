package de.kiessla.hetzner.dyndns.dns.zones;

import lombok.Data;

@Data
public class TxtVerification {
    private String name;
    private String token;
}
