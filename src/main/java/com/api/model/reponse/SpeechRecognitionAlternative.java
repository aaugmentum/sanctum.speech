package com.api.model.reponse;

import java.util.ArrayList;

public class SpeechRecognitionAlternative {
    private String transcript;
    private double confidence;
    private ArrayList<WordInfo> words;

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public ArrayList<WordInfo> getWords() {
        return words;
    }

    public void setWords(ArrayList<WordInfo> words) {
        this.words = words;
    }
}
