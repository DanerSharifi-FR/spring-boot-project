package org.imt.tournamentmaster.service.reporting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.imt.tournamentmaster.model.match.Match;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsonReportingService implements ReportingService {

    private final ObjectMapper objectMapper;

    public JsonReportingService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public String report(Match match) throws IOException {
        return objectMapper.writeValueAsString(match);
    }
}