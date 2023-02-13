package ru.job4j.serialization.json;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class ExampleCar {
    public static void main(String[] args) {
        final MyCar myCar = new MyCar(true, 2019, new ModelCar("Mazda 6"), new String[] {"Color", "Engine"});
       final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(myCar));
        final String myCarJson = "{"
                + "\"move\":true,"
                + "\"year\":2019,"
                + "\"modelCar\":"
                + "{"
                + "\"model\":\"Mazda\""
                + "},"
                + "\"descriptions\":"
                + "[\"CX-5\",\"Black\", \"2.5\"]"
                + "}";
        final MyCar carMod = gson.fromJson(myCarJson, MyCar.class);
        System.out.println(carMod);
    }
}
