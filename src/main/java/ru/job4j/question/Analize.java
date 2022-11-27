package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Map<Integer, String> mapPrevious = new HashMap<>();
        Map<Integer, String> mapCurrent = new HashMap<>();
        for (User curr : current) {
            mapCurrent.put(curr.getId(), curr.getName());
        }
        for (User prev : previous) {
            mapPrevious.put(prev.getId(), prev.getName());
        }
        for (Map.Entry<Integer, String> curr : mapCurrent.entrySet()) {
            if (!mapPrevious.containsKey(curr.getKey())) {
                info.setAdded(info.getAdded() + 1);
            }
        }
        for (Map.Entry<Integer, String> prev : mapPrevious.entrySet()) {
            if (mapCurrent.containsKey(prev.getKey()) && !mapCurrent.containsValue(prev.getValue())) {
                info.setChanged(info.getChanged() + 1);
                continue;
            }
            if (!mapCurrent.containsKey(prev.getKey())) {
                info.setDeleted(info.getDeleted() + 1);
            }
        }
        return info;
    }
}
