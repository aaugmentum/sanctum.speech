package com.api.model.reponse;

import java.util.ArrayList;

public class SpeechRecognitionResult {
    private  ArrayList<SpeechRecognitionAlternative> alternatives;

    public ArrayList<SpeechRecognitionAlternative> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(ArrayList<SpeechRecognitionAlternative> alternatives) {
        this.alternatives = alternatives;
    }
}
