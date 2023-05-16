package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String result = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(key))) {
                result = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
