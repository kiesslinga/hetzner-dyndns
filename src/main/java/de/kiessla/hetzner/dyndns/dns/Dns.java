package de.kiessla.hetzner.dyndns.dns;

import de.kiessla.hetzner.dyndns.dns.records.RecordUpdate;
import de.kiessla.hetzner.dyndns.dns.records.RecordsResponse;
import de.kiessla.hetzner.dyndns.dns.zones.ZonesResponse;
import feign.Param;
import feign.RequestLine;

public interface Dns {

    @RequestLine("GET /zones")
    ZonesResponse zones();

    @RequestLine("GET /records?zone_id={zoneId}")
    RecordsResponse records(@Param("zoneId") String zoneId);

    @RequestLine("PUT /records/{recordId}")
    void updateRecord(RecordUpdate recordUpdate, @Param("recordId") String recordId);

}
