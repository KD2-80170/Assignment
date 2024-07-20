package com.Assignment.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Assignment.entities.JsonModel;
import com.Assignment.service.JsonService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/json")
public class JsonController {

    @Autowired
    private JsonService jsonService;

    @PostMapping("/manipulate")
    public JsonModel manipulateJson(@RequestBody String input) {
        Map<String, String> replacements = parseInput(input);
        return jsonService.manipulateJson(replacements);
    }

    private Map<String, String> parseInput(String input) {
        Map<String, String> replacements = new HashMap<>();
        String[] pairs = input.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":::");
            if (keyValue.length == 2) {
                replacements.put(keyValue[0].replace("\"", ""), keyValue[1].replace("\"", ""));
            }
        }
        return replacements;
    }
}

