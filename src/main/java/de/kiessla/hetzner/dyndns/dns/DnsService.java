package de.kiessla.hetzner.dyndns.dns;

import de.kiessla.hetzner.dyndns.dns.records.Record;
import de.kiessla.hetzner.dyndns.dns.records.RecordUpdate;
import de.kiessla.hetzner.dyndns.dns.zones.Zone;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.util.Optional;

public class DnsService {

    private final Dns dns;

    public DnsService(String apiKey) {
        this.dns = Feign.builder()
                        .decoder(new JacksonDecoder())
                        .encoder(new JacksonEncoder())
                        .logger(new Logger.ErrorLogger())
                        .logLevel(Logger.Level.BASIC)
                        .target(new HetznerIdentityTarget(apiKey));
    }

    public Optional<Record> getRecord(String zoneName,
                                      String recordName) {
        return getZoneWithName(zoneName).flatMap(zone -> getRecordWithName(zone.getId(), recordName));
    }

    public void updateRecord(Record record,
                             String publicIp,
                             int ttl) {
        dns.updateRecord(getRecordUpdate(record, publicIp, ttl),
                         record.getId());
    }

    private Optional<Zone> getZoneWithName(String zoneName) {
        return dns.zones()
                  .getZones()
                  .stream()
                  .filter(zone -> zoneName.equals(zone.getName()))
                  .findFirst();
    }

    private Optional<Record> getRecordWithName(String zoneId, String recordName) {
        return dns.records(zoneId)
                  .getRecords()
                  .stream()
                  .filter(record -> recordName.equals(record.getName()))
                  .findFirst();
    }

    private RecordUpdate getRecordUpdate(Record record,
                                         String publicIp,
                                         int ttl) {
        return RecordUpdate.builder()
                           .name(record.getName())
                           .ttl(ttl)
                           .type(record.getType())
                           .zoneId(record.getZoneId())
                           .value(publicIp)
                           .build();
    }

}
