package com.boot.stickershop.controller;

import com.boot.stickershop.domain.BasketProduct;
import com.boot.stickershop.domain.User;
import com.boot.stickershop.domain.UserRole;
import com.boot.stickershop.dto.UserJoinForm;
import com.boot.stickershop.service.BasketProductService;
import com.boot.stickershop.service.ProductService;
import com.boot.stickershop.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.boot.stickershop.domain.QBasketProduct.basketProduct;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    BasketProductService basketProductService;

    @Autowired
    ProductService productService;

    @GetMapping("/joinform")
    public String joinform(UserJoinForm userJoinForm,ModelMap modelMap){

        modelMap.addAttribute("userJoinForm", userJoinForm);

        return "users/joinform";
    }

    @PostMapping("/join")
    public String join(@Valid UserJoinForm userJoinForm, BindingResult bindingResult, HttpSession session){
        if(bindingResult.hasErrors()){
            return "users/joinform";
        }

        if(!userJoinForm.getPassword().equals(userJoinForm.getRePassword())){
            FieldError error = new FieldError("userJoinForm","rePassword",
                                                                        "비밀번호가 일치하지 않습니다.");
            bindingResult.addError(error);
            return "users/joinform";
        }

        User userByEmail = userService.getUserByEmail(userJoinForm.getEmail());
        if(userByEmail!=null){
            FieldError error = new FieldError("userJoinForm","email","중복된 이메일이 있습니다");
            bindingResult.addError(error);
            return "users/joinform";
        }

        User user = new User();
        BeanUtils.copyProperties(userJoinForm, user);


        // 비밀번호 암호화
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 권한 부여
        UserRole userRole = userService.getUserRole("USER");
        user.addUserRole(userRole);

        // insert
        User saved = userService.addUser(user);

        // 세션에 존재하는 장바구니 옮기기
        Map<Long, Integer> basket = (Map) session.getAttribute("basket");
        if(basket != null && basket.size() > 0){
            for(Long productId : basket.keySet()){
                BasketProduct basketProduct = new BasketProduct();
                basketProduct.setUser(saved);
                basketProduct.setProduct(productService.getProduct(productId));
                basketProduct.setQuantity(basket.get(productId));
                basketProductService.addBasket(basketProduct);
            }
            session.removeAttribute("basket");
        }

        return "users/loginform";
    }

    @GetMapping("/login")
    public String login(){
        return "users/loginform";
    }

}
