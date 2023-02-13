package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonCar {
    public static void main(String[] args) {
        /* JSONObject из json-строки строки */
        JSONObject jsonModel = new JSONObject("{\"model\":\"Mazda\"}");
        /* JSONArray из ArrayList */
        List<String> listdesc = new ArrayList<>();
        listdesc.add("Black");
        listdesc.add("2.5");
        JSONArray jsonDescription = new JSONArray(listdesc);
        final MyCar myCar = new MyCar(true, 2019,
                new ModelCar("Mazda 6"), new String[] {"Color", "Engine"});
        JSONObject jsonCar = new JSONObject();
        jsonCar.put("move", myCar.isMove());
        jsonCar.put("year", myCar.getYear());
        jsonCar.put("model", jsonModel);
        jsonCar.put("description", jsonDescription);
        System.out.println(jsonCar);
        System.out.println(new JSONObject(myCar));
    }
}