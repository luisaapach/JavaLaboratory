package Objects;

import Utils.DictionaryPair;

import java.util.ArrayList;

public class Dictionary {
    ArrayList<DictionaryPair> dictionary = new ArrayList<>();

    public Dictionary(){}
    public Dictionary(ArrayList<DictionaryPair> dictionary) {
        this.dictionary = dictionary;
    }

    public static class WordAlreadyExists extends Exception {
        public WordAlreadyExists(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class EmptyWord extends Exception {
        public EmptyWord(String errorMessage) {
            super(errorMessage);
        }
    }
    public void addDictionaryPair(DictionaryPair newDictionaryPair) throws WordAlreadyExists, EmptyWord {
        if(newDictionaryPair.getWord().length()==0){
            throw new EmptyWord("The word must have the length > 0");
        }
        DictionaryPair alreadyExists = dictionary.stream()
                .filter(dictionaryPair -> newDictionaryPair.getWord().equals(dictionaryPair.getWord()) && newDictionaryPair.getLanguage().equals(dictionaryPair.getLanguage()))
                .findAny()
                .orElse(null);
        if(alreadyExists!=null){
            throw new WordAlreadyExists("The word \"" + newDictionaryPair.getWord() + "\" for language \"" + newDictionaryPair.getLanguage() + "\" already exists inside the dictionary!");
        }

        //System.out.println(newDictionaryPair);
        dictionary.add(newDictionaryPair);
    }

    public ArrayList<DictionaryPair> getDictionary() {
        return dictionary;
    }
}
