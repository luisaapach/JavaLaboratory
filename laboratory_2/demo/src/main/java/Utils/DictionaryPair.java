package Utils;

public class DictionaryPair {
    /**
     * Bean Component
     * DictionaryPair contains :
     *      -> String language
     *      -> String word
     *      -> String definition
     *      -> Setters and Getters for every member
     *      -> An empty constructor
     */
    private String language;
    private String word;
    private String definition;


    public DictionaryPair(){
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getDefinition() {
        return definition;
    }

    public String getLanguage() {
        return language;
    }

    public String getWord() {
        return word;
    }
}
