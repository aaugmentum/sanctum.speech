package com.api.model.reponse;

import java.util.ArrayList;

public class ResponseResult {
    private ArrayList<SpeechRecognitionResult> results;

    public ArrayList<SpeechRecognitionResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<SpeechRecognitionResult> results) {
        this.results = results;
    }
}
