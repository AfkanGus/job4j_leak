package ru.job4j.gc.leak;

import ru.job4j.gc.leak.models.Post;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 4. Найти утечку памяти [#504882 #523298]
 */
public class PostStore {

    private Map<Integer, Post> posts = new HashMap<>();

    private AtomicInteger atomicInteger = new AtomicInteger(1);

    public Post add(Post post) {
        var id = atomicInteger.getAndIncrement();
        post.setId(id);
        posts.put(id, post);
        return post;
    }

    public void removeAll() {
        posts.clear();
    }

    public Collection<Post> getPosts() {
        return posts.values();
    }
}