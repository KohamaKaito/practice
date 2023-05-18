package com.example.practice;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * ProductUpdateFormクラス
 * 更新フォームに対応するオブジェクトクラス
 *
 * @author kohama
 */
@Data
public class ProductUpdateForm implements Serializable {

    // id
    @Pattern(regexp = "^[0-9]+$", message = "エラー：IDに半角数字以外は入力できません")
    @NotEmpty(message = "エラー：IDを入力してください")
    private String id;

    // name
    @Pattern(regexp = "^[^０-９ａ-ｚＡ-Ｚ]+$", message = "エラー：名前に全角英数字は入力できません")
    @Pattern(regexp = "^[^ｦ-ﾟ]+$", message = "エラー：名前に半角カタカナは入力できません")
    @Length(max=10, message = "エラー：名前は10文字以内で入力してください")
    @NotEmpty(message = "エラー：名前を入力してください")
    private String name;

    // price
    @NotBlank(message = "エラー：価格を入力してください")
    @Pattern(regexp = "^[0-9]+$", message = "エラー：価格に半角数字以外は入力できません")
    @Range(min=0, max=2147483647, message = "エラー：2147483647以内の整数で入力してください")
    private String price;

}
