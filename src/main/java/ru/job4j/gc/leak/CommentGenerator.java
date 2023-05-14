package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommentGenerator implements Generate {
    private String pathPhrases = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";
    private List<Comment> comments = new ArrayList<>();
    private List<String> phrases;
    private UserGenerator userGenerator;
    private Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    /**
     * Генератор комментариев. Также при создании заполним список фразами, а при вызове generate
     * зачистим список, а затем сгенерируем 50 комментариев из случайных фраз.
     */

    private void read() {
        try {
            phrases = read(pathPhrases);
        } catch (IOException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void generate() {
        comments.clear();
        for (int i = 0; i < 50; i++) {
            String comment = String.format("%s%n%s%n%s",
                    phrases.get(random.nextInt(phrases.size())),
                    phrases.get(random.nextInt(phrases.size())),
                    phrases.get(random.nextInt(phrases.size()))
            );
            comments.add(new Comment(comment, userGenerator.randomUser()));
        }
    }
}
