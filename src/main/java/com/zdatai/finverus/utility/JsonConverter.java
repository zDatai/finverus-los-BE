package com.zdatai.finverus.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;

public class JsonConverter {

    //copied
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Converts a Java object to a JSON string.
     *
     * @param object the Java object to convert
     * @return the JSON string representation of the object
     * @throws JsonProcessingException if there is an error during conversion
     */
    public static String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    /**
     * Converts a Java object to a JSON string and handles exceptions internally.
     *
     * @param object the Java object to convert
     * @return the JSON string representation of the object, or an empty JSON object '{}' if an error occurs
     */
    public static String getJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            System.err.println("Error converting object to JSON: " + e.getMessage());
            return null;
        }
    }

    /**
     * Converts a JSON string to a Java object.
     *
     * @param json  the JSON string to convert
     * @param clazz the class type of the desired object
     * @param <T>   the type parameter
     * @return the Java object represented by the JSON string
     * @throws JsonProcessingException if there is an error during conversion
     */
    public static <T> T fromJson(String json, Class<T> clazz) throws JsonProcessingException {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);

        return objectMapper.readValue(json, clazz);
    }


    public static <T> List<T> fromJsonList(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

}
