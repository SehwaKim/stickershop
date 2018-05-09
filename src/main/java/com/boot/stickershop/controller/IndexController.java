package com.boot.stickershop.controller;

import com.boot.stickershop.domain.Product;
import com.boot.stickershop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public String index(ModelMap modelMap){
        List<Product> list = productService.getProductListMain();

        modelMap.addAttribute("list", list);

        return "main/index";
    }
}
