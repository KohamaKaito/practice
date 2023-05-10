package com.example.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
public class ProductController {
    @Autowired
    private ProductDao productDao;

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/product")
    public String productList(Model model) {
        List<ProductEntity> productList = productDao.selectAll();
        model.addAttribute("userList", productList);
        return "product";
    }
}
