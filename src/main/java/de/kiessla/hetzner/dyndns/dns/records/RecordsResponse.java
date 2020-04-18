package de.kiessla.hetzner.dyndns.dns.records;

import lombok.Getter;

import java.util.List;

public class RecordsResponse {
    @Getter
    List<Record> records;
}
