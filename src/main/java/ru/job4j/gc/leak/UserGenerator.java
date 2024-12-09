package ru.job4j.gc.leak;

import ru.job4j.gc.leak.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 4. Найти утечку памяти [#504882 #523298]
 */
public class UserGenerator implements Generate {

    public static final String PATH_NAMES = "files/names.txt";
    public static final String PATH_SURNAMES = "files/surnames.txt";
    public static final String PATH_PATRONS = "files/patr.txt";

    public static final String SEPARATOR = " ";
    public static final Integer NEW_USERS = 1000;

    public List<String> names;
    public List<String> surnames;
    public List<String> patrons;
    private final List<User> users = new ArrayList<>();
    private Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    @Override
    public void generate() {
        users.clear();
        for (int i = 0; i < NEW_USERS; i++) {
            var name = surnames.get(random.nextInt(surnames.size())) + SEPARATOR
                    + names.get(random.nextInt(names.size())) + SEPARATOR
                    + patrons.get(random.nextInt(patrons.size()));
            users.add(new User(name));
        }
    }

    private void readAll() {
        try {
            names = read(PATH_NAMES);
            surnames = read(PATH_SURNAMES);
            patrons = read(PATH_PATRONS);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }
}