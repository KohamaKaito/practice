package com.example.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        model.addAttribute("productInsertForm", new ProductInsertForm());
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
    public String insertProduct(@Validated @ModelAttribute ProductInsertForm form, BindingResult result, Model model) {
        if(result.hasErrors()){
            System.out.println("error");
            List<String> errorList = new ArrayList<String>();
            for(ObjectError error:result.getAllErrors()){
                errorList.add(error.getDefaultMessage());
                System.out.println(error.getDefaultMessage());
            }
            List<ProductEntity> productList = productService.selectProduct();
            model.addAttribute("productList", productList);
            model.addAttribute("productInsertForm", new ProductInsertForm());
            model.addAttribute("validationError", errorList);
            return "product";
        }
        System.out.println(form.getName());
        System.out.println(form.getPrice());
        productService.insertProduct(form.getName(), Integer.parseInt(form.getPrice()));
        List<ProductEntity> productList = productService.selectProduct();
        model.addAttribute("productList", productList);
        model.addAttribute("productInsertForm", new ProductInsertForm());

        return "product";
    }

    @RequestMapping(value = "/product/submitInsertForm", method = RequestMethod.POST)
    public String submitInsertForm(@Validated @ModelAttribute ProductInsertForm form, BindingResult result, Model model){

        model.addAttribute("productInsertForm", new ProductInsertForm());
        if(result.hasErrors()){
            // バリデーションエラーがある場合の処理
            return "redirect:/product";
        }
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
        return "redirect:/product";
    }


}
