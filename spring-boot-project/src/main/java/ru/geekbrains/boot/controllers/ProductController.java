package ru.geekbrains.boot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.boot.model.Product;
import ru.geekbrains.boot.services.ProductService;


@Controller
@RequestMapping("/repository")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String showAll(Model model){
        model.addAttribute("productList", productService.findAll());
        return "/repository/products";
    }

    @GetMapping("/new")
    public String newProduct(Model uiModel){
        uiModel.addAttribute("product", new Product());
        return "/repository/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("product") Product product){
        productService.save(product);
        return "redirect:/repository";
    }
}
