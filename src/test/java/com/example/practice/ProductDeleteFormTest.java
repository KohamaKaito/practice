package com.example.practice;

import com.example.practice.ProductDeleteForm;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductDeleteFormTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testValidForm() {
        // 有効なフォームの作成
        ProductDeleteForm form = new ProductDeleteForm();
        form.setId("1");

        // バリデーションの実行
        Set<ConstraintViolation<ProductDeleteForm>> violations = validator.validate(form);

        // エラーメッセージの検証
        assertEquals(0, violations.size());
    }

    @Test
    public void testInvalidForm() {
        // 無効なフォームの作成（IDが空）
        ProductDeleteForm form = new ProductDeleteForm();
        form.setId("");

        // バリデーションの実行
        Set<ConstraintViolation<ProductDeleteForm>> violations = validator.validate(form);

        // エラーメッセージの検証
        assertEquals(2, violations.size());
        for (ConstraintViolation<ProductDeleteForm> violation : violations) {
            assertEquals("エラー：IDを入力してください", violation.getMessage());
            assertEquals("id", violation.getPropertyPath().toString());
        }
    }

    @Test
    public void testInvalidFormWithNonNumericId() {
        // 無効なフォームの作成（IDが半角数字以外）
        ProductDeleteForm form = new ProductDeleteForm();
        form.setId("abc");

        // バリデーションの実行
        Set<ConstraintViolation<ProductDeleteForm>> violations = validator.validate(form);

        // エラーメッセージの検証
        assertEquals(1, violations.size());
        ConstraintViolation<ProductDeleteForm> violation = violations.iterator().next();
        assertEquals("エラー：IDに半角数字以外は入力できません", violation.getMessage());
        assertEquals("id", violation.getPropertyPath().toString());
    }
}