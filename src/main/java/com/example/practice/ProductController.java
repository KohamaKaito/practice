package com.example.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ProductController {

    // サービスのインスタンス生成
    @Autowired
    private ProductService productService;

    //
    // ルートURLがリクエストされた時にindex.htmlを表示するメソッド
    //
    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    //
    // データベースから商品一覧を取得してViewに渡すメソッド
    //
    @RequestMapping("/product")
    public String showProduct(Model model) {
        List<ProductEntity> productList = productService.selectProduct();
        model.addAttribute("productList", productList);
        return "product";
    }

    //
    // Viewから受け取った値をデータベースに追加するメソッド
    //
    @PostMapping("/insertProduct")
    public String insertProduct(@RequestParam("name") String name, @RequestParam("price") int price, Model model) {
        productService.insertProduct(name, price);
        showProduct(model);
        return "product";
    }

    //
    // Viewから受け取った値でデータベースを更新するメソッド
    //
    @PostMapping("/updateProduct")
    public String updateProduct(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("price") int price, Model model) {
        productService.updateProduct(id, name, price);
        showProduct(model);
        return "product";
    }
}
