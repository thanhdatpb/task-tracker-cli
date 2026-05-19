package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Task;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

public class JsonUtils {
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .setPrettyPrinting()
            .create();

    public static String serializeTasks(List<Task> tasks) {
        return gson.toJson(tasks);
    }

    public static List<Task> deserializeTasks(String json) {
        Type type = new TypeToken<List<Task>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
