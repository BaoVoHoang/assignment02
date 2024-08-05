package system.book;

import java.util.HashSet;
import java.util.Set;

public class BookLanguageSet {
    private Set<String> languageSet;

    public BookLanguageSet() {
        this.languageSet = new HashSet<>();
    }

    public void addLanguage(String language) {
        languageSet.add(language);
    }

    public Set<String> getLanguages() {
        return languageSet;
    }
}
