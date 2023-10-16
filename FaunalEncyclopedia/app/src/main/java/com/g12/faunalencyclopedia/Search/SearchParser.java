package com.g12.faunalencyclopedia.Search;
import java.util.List;

public class SearchParser {

    private final List<String> tokens;
    private int index = 0;

    public SearchParser(List<String> tokens) {
        this.tokens = tokens;
    }

    public Object parse() {
        if (index >= tokens.size()) {
            throw new RuntimeException("Unexpected end of input");
        }

        String token = tokens.get(index);

        if (token.startsWith("#")) {
            return parseHashtagQuery();
        } else {
            return parseWordQuery();
        }
    }

    private Object parseHashtagQuery() {
        String hashtag = tokens.get(index++);
        // Process the hashtag token, e.g., remove the '#' prefix
        return new HashtagQuery(hashtag.substring(1));
    }

    private Object parseWordQuery() {
        String word = tokens.get(index++);
        return new WordQuery(word);
    }
}
