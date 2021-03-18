package ru.geekbrains.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.geekbrains.FillRepository;

@Controller
public class XmlController {
    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/xml/get")
    public String getXml(Model model) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(((FillRepository) applicationContext.getBean("fillRepository")).getProduct());
        JSONObject json = new JSONObject(jsonStr);
        String strXml = XML.toString(json);
        //XmlMapper xmlMapper = new XmlMapper();
        //String strXml = xmlMapper.writeValueAsString(((FillRepository) applicationContext.getBean("fillRepository")).getProduct());
        model.addAttribute("message", strXml);
        return "xml";
    }
}
