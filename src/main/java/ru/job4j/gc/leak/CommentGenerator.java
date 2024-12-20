package ru.job4j.gc.leak;

import ru.job4j.gc.leak.models.Comment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 4. Найти утечку памяти [#504882 #523298]
 */
public class CommentGenerator implements Generate {

    public static final String PATH_PHRASES = "files/phrases.txt";

    public static final String SEPARATOR = System.lineSeparator();
    private final List<Comment> comments = new ArrayList<>();
    public static final Integer COUNT = 50;
    private List<String> phrases;
    private UserGenerator userGenerator;
    private Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() {
        try {
            phrases = read(PATH_PHRASES);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void generate() {
        comments.clear();
        for (int i = 0; i < COUNT; i++) {
            String text = String.format("%s%s%s%s%s",
                    phrases.get(random.nextInt(phrases.size())), SEPARATOR,
                    phrases.get(random.nextInt(phrases.size())), SEPARATOR,
                    phrases.get(random.nextInt(phrases.size())));
            comments.add(new Comment(text,
                    userGenerator.randomUser()));
        }
    }
}