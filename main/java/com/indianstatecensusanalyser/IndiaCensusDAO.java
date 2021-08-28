package com.indianstatecensusanalyser;

public class IndiaCensusDAO {
    public String state;

    public IndiaCensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
    }
}

