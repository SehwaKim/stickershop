package com.boot.stickershop.controller;

import com.boot.stickershop.domain.Product;
import com.boot.stickershop.dto.ProductSearch;
import com.boot.stickershop.service.ProductService;
import com.boot.stickershop.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public String getProductList(@ModelAttribute ProductSearch productSearch, ModelMap modelMap){
        Page<Product> productPage = productService.getProductList(productSearch);

        Pagination pagination = new Pagination((int) productPage.getTotalElements(), 18, productSearch.getPage(), 5);

        modelMap.addAttribute("list", productPage);
        modelMap.addAttribute("pager", pagination);

        return "/products/list";
    }
}
