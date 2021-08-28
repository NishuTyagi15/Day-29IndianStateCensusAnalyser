package com.indianstatecensusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {
    List<IndiaCensusDAO> csvFileList;
    public StateCensusAnalyser() {
        this.csvFileList = new ArrayList<IndiaCensusDAO>();
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException, IOException, CSVBuilderException {
        Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
        ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
        Iterator<IndiaCensusCSV> csvIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);
        while (csvIterator.hasNext()) {
            this.csvFileList.add(new IndiaCensusDAO(csvIterator.next()));
        }
        return csvFileList.size();
    }

    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> iterable = () -> iterator;
        int numOfEntries = (int) StreamSupport.stream(iterable.spliterator(), false).count();
        return numOfEntries;
    }
}
