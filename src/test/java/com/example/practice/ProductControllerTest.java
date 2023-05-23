package com.example.practice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testShowProduct() throws Exception {
        when(productService.selectProduct()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/product"))
                .andExpect(status().isOk())
                .andExpect(view().name("product"));
    }

    @Test
    public void testInsertProduct() throws Exception {
        ProductInsertForm form = new ProductInsertForm();
        form.setName("hoge");
        form.setPrice("100");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/product/insert")
                        .flashAttr("productInsertForm", form))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/product"));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        ProductUpdateForm form = new ProductUpdateForm();
        form.setId("1");
        form.setName("hoge");
        form.setPrice("100");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/product/update")
                        .flashAttr("productUpdateForm", form))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/product"));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        ProductDeleteForm form = new ProductDeleteForm();
        form.setId("1");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/product/delete")
                        .flashAttr("productDeleteForm",form))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/product"));
    }

}