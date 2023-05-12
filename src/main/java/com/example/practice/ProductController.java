package com.example.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * ProductControllerクラス
 * サーバーとViewの掛け橋をするクラス
 * ロジック部分はserviceクラスに任せる
 *
 * @author kohama
 */
@Controller
public class ProductController {

    // サービスのインスタンス生成
    @Autowired
    private ProductService productService;

    /**
     * indexメソッド
     * ルートURLがリクエストされた時にindex.htmlを表示するメソッド
     *
     * @param model Viewに渡されるデータを保持するオブジェクト
     * @return index.html
     */
    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    /**
     * showProductメソッド
     * 商品一覧を取得してViewに渡すメソッド
     *
     * @param model Viewに渡されるデータを保持するオブジェクト
     * @return product.html
     */
    @GetMapping("/product")
    public String showProduct(Model model) {
        List<ProductEntity> productList = productService.selectProduct();
        model.addAttribute("productList", productList);
        return "product";
    }

    /**
     * insertProductメソッド
     * Viewから受け取った値をデータベースに追加するメソッド
     *
     * @param name  追加したい名前
     * @param price 追加したい価格
     * @param model Viewに渡されるデータを保持するオブジェクト
     * @return product.html
     */
    @PostMapping("/product/insert")
    public String insertProduct(@RequestParam("insertName") String name, @RequestParam("insertPrice") int price, Model model) {
        productService.insertProduct(name, price);
        showProduct(model);
        return "redirect:/product";
    }

    /**
     * updateProductメソッド
     * Viewから受け取った値でデータベースを更新するメソッド
     *
     * @param id    更新対象のid
     * @param name  更新したい名前
     * @param price 更新したい価格
     * @param model Viewに渡されるデータを保持するオブジェクト
     * @return product.html
     */
    @PostMapping("/product/update")
    public String updateProduct(@RequestParam("updateId") int id, @RequestParam("updateName") String name, @RequestParam("updatePrice") int price, Model model) {
        productService.updateProduct(id, name, price);
        showProduct(model);
        return "redirect:/product";
    }

    /**
     * deleteProductメソッド
     * Viewから受け取った値でデータベースを削除するメソッド
     *
     * @param id    削除対象のid
     * @param model Viewに渡されるデータを保持するオブジェクト
     * @return product.html
     */
    @PostMapping("/product/delete")
    public String deleteProduct(@RequestParam("deleteId") int id, Model model) {
        productService.deleteProduct(id);
        showProduct(model);
        return "redirect:/product";
    }

}
