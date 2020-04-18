package de.kiessla.hetzner.dyndns.dns.meta;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class Pagination {
    private int page;

    @JsonAlias("per_page")
    private int perPage;

    @JsonAlias("previous_page")
    private int previousPage;

    @JsonAlias("next_page")
    private int nextPage;

    @JsonAlias("last_page")
    private int lastPage;

    @JsonAlias("total_entries")
    private int totalEntries;
}
