package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.Product;
import ru.geekbrains.dao.RepositoryDAO;

@Controller
@RequestMapping("/repository")
public class RepositoryControllers {

    @Autowired
    private RepositoryDAO repositoryDAO;

    @GetMapping()
    public String index(Model model){
        model.addAttribute("productList", repositoryDAO.index());
        return "repository/repo";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("product", repositoryDAO.show(id));
        return "/repository/show";
    }

    @GetMapping("/new")
    public String newProduct(Model uiModel){
        uiModel.addAttribute("product", new Product());
        return "repository/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("product") Product product){
        repositoryDAO.save(product);
        return "redirect:/repository";
    }

}
