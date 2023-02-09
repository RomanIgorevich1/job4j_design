package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ExampleCar {
    public static void main(String[] args) {
        final CreatingCar myCar = new CreatingCar(true, 2019,
                new Car("Mazda"), new String[] {"Car Model", "Color", "Engine"});
       final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(myCar));
        final String myCarJson = "{"
                + "\"type\":true,"
                + "\"year\":2019,"
                + "\"car\":"
                + "{"
                + "\"brand\":\"Mazda\""
                + "},"
                + "\"descriptions\":"
                + "[\"CX-5\",\"Black\", \"2.5\"]"
                + "}";
        final CreatingCar carMod = gson.fromJson(myCarJson, CreatingCar.class);
        System.out.println(carMod);
    }
}
