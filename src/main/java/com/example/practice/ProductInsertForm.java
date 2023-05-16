package com.example.practice;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Data
public class ProductInsertForm implements Serializable {

    // nullでないことを検証する
    @NotEmpty(message = "エラー：名前を入力してください")
    private String name;

    // nullでないことを検証する
    @NotBlank(message = "エラー：価格を入力してください")
    @Range(min=0, max=2147483647, message = "エラー：2147483647以内の整数で入力してください")
    private String price;

}
