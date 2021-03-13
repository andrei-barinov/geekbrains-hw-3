package ru.geekbrains.boot.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.geekbrains.boot.model.Product;

@Controller
public class XmlController {


    @GetMapping ("xml/get")
    public String getXml(Model model) throws JsonProcessingException, JSONException {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = new Product(10, "Car", 100);
        String StrJson = objectMapper.writeValueAsString(product);
        JSONObject json = new JSONObject(StrJson);
        String strXml = XML.toString(json);
        model.addAttribute("message", strXml);
        return "/repository/format/xml";
    }

}
