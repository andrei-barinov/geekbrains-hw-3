package ru.geekbrains.boot.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.geekbrains.boot.model.Product;

@Controller
public class JsonController {
    @GetMapping("/json/get")
    public String getJson(Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Product product = new Product(10, "Car", 100);
        String StrJson = mapper.writeValueAsString(product);
        model.addAttribute("message", StrJson);
        return "/repository/format/json";
    }
}
