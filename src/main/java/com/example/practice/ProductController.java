package com.example.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
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

    @Autowired
    private ProductService productService;

    /**
     * indexメソッド
     * ルートURLがリクエストされた時にindex.htmlを表示するメソッド
     */
    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    /**
     * showProductメソッド
     * 商品一覧を取得してViewに渡すメソッド
     */
    @GetMapping("/product")
    public String showProduct(Model model) {
        List<ProductEntity> productList = productService.selectProduct();
        model.addAttribute("productList", productList);
        model.addAttribute("productInsertForm", new ProductInsertForm());
        model.addAttribute("productUpdateForm", new ProductUpdateForm());
        model.addAttribute("productDeleteForm", new ProductDeleteForm());
        return "product";
    }

    /**
     * insertProductメソッド
     * Viewから受け取った値をデータベースに追加するメソッド
     */
    @PostMapping("/product/insert")
    public String insertProduct(@Validated @ModelAttribute ProductInsertForm form, BindingResult result, Model model, RedirectAttributes ra) {
        // エラーが発生した際の処理
        if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            // リダイレクト先にエラー情報を送る
            ra.addFlashAttribute("errorList", errorList);
            return "redirect:/product";
        }
        // エラーがなければ追加
        productService.insertProduct(form.getName(), Integer.parseInt(form.getPrice()));
        return "redirect:/product";
    }


    /**
     * updateProductメソッド
     * Viewから受け取った値でデータベースを更新するメソッド
     */
    @PostMapping("/product/update")
    public String updateProduct(@Validated @ModelAttribute ProductUpdateForm form, BindingResult result, Model model, RedirectAttributes ra) {
        // エラーが発生した際の処理
        if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            // リダイレクト先にエラー情報を送る
            ra.addFlashAttribute("errorList", errorList);
            return "redirect:/product";
        }
        // IDが存在しない際の処理
        if(!productService.existsId(Integer.parseInt(form.getId()))){
            List<String> errorList = new ArrayList<String>();
            errorList.add("エラー：IDが存在しません");
            // リダイレクト先にエラー情報を送る
            ra.addFlashAttribute("errorList", errorList);
            return "redirect:/product";
        }
        // エラーがなければ更新
        productService.updateProduct(Integer.parseInt(form.getId()), form.getName(), Integer.parseInt(form.getPrice()));
        return "redirect:/product";
    }

    /**
     * deleteProductメソッド
     * Viewから受け取った値でデータベースを削除するメソッド
     */
    @PostMapping("/product/delete")
    public String deleteProduct(@Validated @ModelAttribute ProductDeleteForm form, BindingResult result, Model model, RedirectAttributes ra) {
        // エラーが発生した際の処理
        if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            // リダイレクト先にエラー情報を送る
            ra.addFlashAttribute("errorList", errorList);
            return "redirect:/product";
        }
        // IDが存在しない際の処理
        if(!productService.existsId(Integer.parseInt(form.getId()))){
            List<String> errorList = new ArrayList<String>();
            errorList.add("エラー：IDが存在しません");
            // リダイレクト先にエラー情報を送る
            ra.addFlashAttribute("errorList", errorList);
            return "redirect:/product";
        }
        // エラーがなければ削除
        productService.deleteProduct(Integer.parseInt(form.getId()));
        List<ProductEntity> productList = productService.selectProduct();
        return "redirect:/product";
    }

}
