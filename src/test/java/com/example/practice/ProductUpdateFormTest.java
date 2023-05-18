package com.example.practice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductUpdateFormTest {

    private Validator validator;

    @InjectMocks
    private ProductUpdateForm productUpdateForm;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validateId_ValidId_NoValidationErrors() {
        productUpdateForm.setId("12345");
        Set<ConstraintViolation<ProductUpdateForm>> violations = validator.validate(productUpdateForm);
        assertTrue(violations.isEmpty());
    }

    @Test
    void validateId_IdWithNonNumericCharacter_ValidationErrors() {
        productUpdateForm.setId("abcde");
        Set<ConstraintViolation<ProductUpdateForm>> violations = validator.validate(productUpdateForm);
        assertEquals(1, violations.size());
        ConstraintViolation<ProductUpdateForm> violation = violations.iterator().next();
        assertEquals("エラー：IDに半角数字以外は入力できません", violation.getMessage());
    }

    // Write similar tests for other fields (name and price)

}