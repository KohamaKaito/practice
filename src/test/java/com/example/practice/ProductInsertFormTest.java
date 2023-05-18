package com.example.practice;

import com.example.practice.ProductInsertForm;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ProductInsertFormTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    public void testValidProductInsertForm() {
        // 正しいデータの作成
        ProductInsertForm form = new ProductInsertForm();
        form.setName("Sample Product");
        form.setPrice("1000");

        // バリデーションの実行
        Set<javax.validation.ConstraintViolation<ProductInsertForm>> violations = validator.validate(form);

        // バリデーションエラーがないことを検証
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidProductInsertForm() {
        // 不正なデータの作成
        ProductInsertForm form = new ProductInsertForm();
        form.setName("商品１");
        form.setPrice("ABC");

        // バリデーションの実行
        Set<javax.validation.ConstraintViolation<ProductInsertForm>> violations = validator.validate(form);

        // バリデーションエラーがあることを検証
        assertEquals(3, violations.size());
    }
}
