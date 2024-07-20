package com.Assignment.service;
import com.Assignment.dao.JsonRepository;
import com.Assignment.entities.JsonModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;

@Service
public class JsonService {

    @Autowired
    private JsonRepository jsonRepository;

    private String jsonTemplate = "{\"menu\": {\"id\": \"file\",\"value\": \"File\",\"popup\": {\"menuitem\": [{\"value\": \"New\", \"onclick\": \"CreateDoc()\"},{\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},{\"value\": \"Save\", \"onclick\": \"SaveDoc()\"}]}}}";

    public JsonModel manipulateJson(Map<String, String> replacements) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonTemplate);

            replaceValues(root, replacements);

            String modifiedJson = mapper.writeValueAsString(root);

            JsonModel jsonModel = new JsonModel();
            jsonModel.setJsonmodel(modifiedJson);
            return jsonRepository.save(jsonModel);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void replaceValues(JsonNode node, Map<String, String> replacements) {
        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;
            Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                if (field.getValue().isValueNode() && replacements.containsKey(field.getValue().asText())) {
                    objectNode.put(field.getKey(), replacements.get(field.getValue().asText()));
                } else {
                    replaceValues(field.getValue(), replacements);
                }
            }
        } else if (node.isArray()) {
            for (JsonNode arrayItem : node) {
                replaceValues(arrayItem, replacements);
            }
        }
    }
}