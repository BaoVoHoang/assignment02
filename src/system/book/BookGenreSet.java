package system.book;

import java.util.HashSet;
import java.util.Set;

public class BookGenreSet {
    private Set<String> genreSet;

    public BookGenreSet() {
        this.genreSet = new HashSet<>();
    }

    public void addGenre(String genre) {
        genreSet.add(genre);
    }

    public Set<String> getGenres() {
        return genreSet;
    }
}
