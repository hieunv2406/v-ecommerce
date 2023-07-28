package com.vm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {
    private Long id;
    private String key;
    private String message;
    private Object object;
    private File file;
    private Map<String, String> errors;

    public ResultDto(String key, String message, Object object, HashMap<String, String> errors) {
        this.key = key;
        this.message = message;
        this.object = object;
        this.errors = errors;
    }

    public ResultDto(String key, String message, HashMap<String, String> errors) {
        this.key = key;
        this.message = message;
        this.errors = errors;
    }

    public ResultDto(String key) {
        this.key = key;
    }

    public ResultDto(String key, String message) {
        this.key = key;
        this.message = message;
    }

    public ResultDto(String key, String keyMap, String valueMap) {
        Map<String, String> errors = new HashMap<>();
        errors.put(keyMap, valueMap);
        this.key = key;
        this.errors = errors;
    }

    public ResultDto(String key, String message, Object object) {
        this.key = key;
        this.message = message;
        this.object = object;
    }
}