package ru.job4j.ood.srp.report;

import com.google.gson.*;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;
import java.util.*;
import java.util.function.Predicate;

public class ReportJSON implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private Gson gson;

    public ReportJSON(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        gson = new GsonBuilder()
                .setDateFormat("dd:MM:yyyy HH:mm")
                .setPrettyPrinting()
                .create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {

        JsonArray array = new JsonArray();
            for (Employee user : store.findBy(filter)) {
                JsonObject object = new JsonObject();
                object.add("name", gson.toJsonTree((user.getName())));
                object.add("hired", gson.toJsonTree(dateTimeParser.parse(user.getHired())));
                object.add("fired", gson.toJsonTree(dateTimeParser.parse(user.getFired())));
                object.add("salary", gson.toJsonTree(user.getSalary()));
                array.add(object);
            }
        return gson.toJson(array);
    }
}
