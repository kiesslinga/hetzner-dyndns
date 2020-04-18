package de.kiessla.hetzner.dyndns.dns.zones;

import de.kiessla.hetzner.dyndns.dns.meta.MetaData;
import lombok.Getter;

import java.util.List;

public class ZonesResponse {
    @Getter
    List<Zone> zones;

    @Getter
    MetaData meta;
}
