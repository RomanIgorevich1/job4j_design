package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
public class CarJson {
    public static void main(String[] args) {
        /*
          JSONObject из json-строки строки
         */
        JSONObject jsonCreating = new JSONObject("{\"brand\":\"Mazda\"}");
        /*
          JSONArray из ArrayList
         */
        List<String> list = new ArrayList<>();
        list.add("CX-5");
        list.add("Black");
        list.add("2.5");
        JSONArray jsonDescription = new JSONArray(list);
        final Car myCar = new Car(true, 2019,
                new CreatingCar("Mazda"), new String[] {"Car Model", "Color", "Engine"});
        /*
           JSONObject напрямую методом put
         */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", myCar.isType());
        jsonObject.put("year", myCar.getYear());
        jsonObject.put("creatingCar", jsonCreating);
        jsonObject.put("description", jsonDescription);
        System.out.println(jsonObject);
        /*
          Преобразуем объект myCar в json-строку
         */
        System.out.println(new JSONObject(myCar));
    }
}
