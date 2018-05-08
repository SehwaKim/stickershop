package com.boot.stickershop.controller;

import com.boot.stickershop.domain.Product;
import com.boot.stickershop.service.ProductService;
import com.boot.stickershop.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public String getProductList(@RequestParam(name = "categoryId", required = false) Long categoryId
            , @RequestParam(name = "page", defaultValue = "1") int page
            , @RequestParam(name = "sort", defaultValue = "favor") String sort
            , @RequestParam(name = "keyword", required = false) String keyword
            , @RequestParam(name = "minPrice", required = false) Integer minPrice
            , @RequestParam(name = "maxPrice", required = false) Integer maxPrice
            , ModelMap modelMap){

        Page<Product> productPage = productService.getProductList(categoryId, page, sort, keyword, minPrice, maxPrice);

        //Pagination pagination = new Pagination();

        modelMap.addAttribute("list", productPage);
        //modelMap.addAttribute("pagination", pagination);

        return "/products/list";
    }
}
