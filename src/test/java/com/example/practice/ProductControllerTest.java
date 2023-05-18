package com.example.practice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private RedirectAttributes redirectAttributes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowProduct() {
        List<ProductEntity> productList = new ArrayList<>();
        when(productService.selectProduct()).thenReturn(productList);

        String viewName = productController.showProduct(model);

        assertEquals("product", viewName);
        verify(model).addAttribute(eq("productList"), eq(productList));
        verify(model).addAttribute(eq("productInsertForm"), any(ProductInsertForm.class));
        verify(model).addAttribute(eq("productUpdateForm"), any(ProductUpdateForm.class));
        verify(model).addAttribute(eq("productDeleteForm"), any(ProductDeleteForm.class));
    }

    @Test
    public void testInsertProduct_ValidForm() {
        ProductInsertForm form = new ProductInsertForm();
        form.setName("Test Product");
        form.setPrice("1000");

        String viewName = productController.insertProduct(form, bindingResult, model, redirectAttributes);

        assertEquals("redirect:/product", viewName);
        verify(productService).insertProduct(eq("Test Product"), eq(1000));
    }

    @Test
    public void testInsertProduct_InvalidForm() {
        ProductInsertForm form = new ProductInsertForm();
        form.setName(""); // 無効な名前
        form.setPrice("1000");

        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(new ArrayList<>());

        String viewName = productController.insertProduct(form, bindingResult, model, redirectAttributes);

        assertEquals("redirect:/product", viewName);
        verify(redirectAttributes).addFlashAttribute(eq("errorList"), anyList());
        verify(productService).insertProduct(anyString(), anyInt()); // insertProductメソッドは呼ばれない
    }

    @Test
    public void testUpdateProduct_ValidForm() {
        ProductUpdateForm form = new ProductUpdateForm();
        form.setId("1");
        form.setName("Updated Product");
        form.setPrice("2000");

        String viewName = productController.updateProduct(form, bindingResult, model, redirectAttributes);

        assertEquals("redirect:/product", viewName);
        verify(productService).updateProduct(eq(1), eq("Updated Product"), eq(2000));
    }

    @Test
    public void testUpdateProduct_InvalidForm() {
        ProductUpdateForm form = new ProductUpdateForm();
        form.setId(""); // 無効なID
        form.setName("Updated Product");
        form.setPrice("2000");

        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(new ArrayList<>());

        String viewName = productController.updateProduct(form, bindingResult, model, redirectAttributes);

        assertEquals("redirect:/product", viewName);
        verify(redirectAttributes).addFlashAttribute(eq("errorList"), anyList());
        verify(productService).updateProduct(anyInt(), anyString(), anyInt()); // updateProductメソッドは呼ばれない
    }

    @Test
    public void testUpdateProduct_NonexistentId() {
        ProductUpdateForm form = new ProductUpdateForm();
        form.setId("999"); // 存在しないID
        form.setName("Updated Product");
        form.setPrice("2000");

        when(bindingResult.hasErrors()).thenReturn(false);
        when(productService.existsId(999)).thenReturn(false);

        String viewName = productController.updateProduct(form, bindingResult, model, redirectAttributes);

        assertEquals("redirect:/product", viewName);
        verify(redirectAttributes).addFlashAttribute(eq("errorList"), anyList());
        verify(productService).existsId(999);
        verify(productService).updateProduct(anyInt(), anyString(), anyInt()); // updateProductメソッドは呼ばれない
    }

    @Test
    public void testDeleteProduct_ValidForm() {
        ProductDeleteForm form = new ProductDeleteForm();
        form.setId("1");

        String viewName = productController.deleteProduct(form, bindingResult, model, redirectAttributes);

        assertEquals("redirect:/product", viewName);
        verify(productService).deleteProduct(eq(1));
    }

    @Test
    public void testDeleteProduct_InvalidForm() {
        ProductDeleteForm form = new ProductDeleteForm();
        form.setId(""); // 無効なID

        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(new ArrayList<>());

        String viewName = productController.deleteProduct(form, bindingResult, model, redirectAttributes);

        assertEquals("redirect:/product", viewName);
        verify(redirectAttributes).addFlashAttribute(eq("errorList"), anyList());
        verify(productService).deleteProduct(anyInt()); // deleteProductメソッドは呼ばれない
    }

    @Test
    public void testDeleteProduct_NonexistentId() {
        ProductDeleteForm form = new ProductDeleteForm();
        form.setId("999"); // 存在しないID

        when(bindingResult.hasErrors()).thenReturn(false);
        when(productService.existsId(999)).thenReturn(false);

        String viewName = productController.deleteProduct(form, bindingResult, model, redirectAttributes);

        assertEquals("redirect:/product", viewName);
        verify(redirectAttributes).addFlashAttribute(eq("errorList"), anyList());
        verify(productService).existsId(999);
        verify(productService).deleteProduct(anyInt()); // deleteProductメソッドは呼ばれない
    }
}