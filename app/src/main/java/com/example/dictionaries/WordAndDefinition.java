package com.example.dictionaries;

public class WordAndDefinition {
    private String word;
    private String definition;

    public WordAndDefinition(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }
}
