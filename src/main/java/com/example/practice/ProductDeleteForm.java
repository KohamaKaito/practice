package com.example.practice;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class ProductDeleteForm {

    // id
    @Pattern(regexp = "^[0-9]+$", message = "エラー：IDに半角数字以外は入力できません")
    @NotEmpty(message = "エラー：IDを入力してください")
    private String id;

}
