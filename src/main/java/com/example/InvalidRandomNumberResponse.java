package com.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InvalidRandomNumberResponse {
    private final int type;
    private final int length;
    private final List<Integer> data;
    private final boolean success;

    @JsonCreator
    public InvalidRandomNumberResponse(
            @JsonProperty(value = "type") int type,
            @JsonProperty(value = "length") int length,
            @JsonProperty(value = "data") List<Integer> data,
            @JsonProperty(value = "success") boolean success
    ) {
        this.type = type;
        this.length = length;
        this.data = data;
        this.success = success;
    }

    public int getType() {
        return type;
    }

    public int getLength() {
        return length;
    }

    public List<Integer> getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }
}
