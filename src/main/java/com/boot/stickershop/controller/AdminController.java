package com.boot.stickershop.controller;

import com.boot.stickershop.domain.Product;
import com.boot.stickershop.dto.ProductSearch;
import com.boot.stickershop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    ProductService productService;

    @GetMapping
    public String index(){
        return "admin/index";
    }

    @GetMapping("/products")
    public String products(@ModelAttribute ProductSearch productSearch, ModelMap modelMap){
        productSearch.setAdmin(true);
        Page<Product> list = productService.getProductList(productSearch);

        modelMap.addAttribute("list", list);

        return "admin/products";
    }
}
