package ru.geekbrains.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import ru.geekbrains.FillRepository;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping(value = "/product_xml")
    public String getProductXml() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.writeValueAsString(((FillRepository) applicationContext.getBean("fillRepository")).getProduct());
    }
}
