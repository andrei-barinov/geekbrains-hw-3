package ru.geekbrains.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.geekbrains.FillRepository;

@Controller
public class JsonController {
    @Autowired
    private ApplicationContext applicationContext;
    @GetMapping("/json/get")
    public String getJson(Model model) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(((FillRepository) applicationContext.getBean("fillRepository")).getProduct());
        model.addAttribute("message", jsonStr);
        return "json";
    }
}
