package org.naehas.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Parser<T> {
    public String toJson(T obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

}
