package com.boot.stickershop.controller.api;

import com.boot.stickershop.domain.BasketProduct;
import com.boot.stickershop.domain.Order;
import com.boot.stickershop.domain.Product;
import com.boot.stickershop.domain.User;
import com.boot.stickershop.dto.BasketItem;
import com.boot.stickershop.service.BasketProductService;
import com.boot.stickershop.service.OrderService;
import com.boot.stickershop.service.ProductService;
import com.boot.stickershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderApiController {
}
