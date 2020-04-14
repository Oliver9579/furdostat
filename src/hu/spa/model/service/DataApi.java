package hu.spa.model.service;

import hu.spa.model.domain.GuestLog;
import hu.spa.model.domain.LogEntry;

import java.io.File;
import java.util.List;

public class DataApi {

    private final FileReader fileReader;
    private final DataParser dataParser;
    private final GuestLogTransformer transformer;

    public DataApi(FileReader fileReader, DataParser dataParser, GuestLogTransformer transformer) {
        this.fileReader = fileReader;
        this.dataParser = dataParser;
        this.transformer = transformer;
    }

    public List<GuestLog> getData(String input) {
        List<String> lines = fileReader.read(input);
        List<LogEntry> logEntries = dataParser.parse(lines);
        return transformer.transform(logEntries);
    }
}
