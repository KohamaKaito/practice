package com.example.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * PracticeApplication
 * アプリケーションの起点となるクラス
 *
 * @author kohama
 * @version 0.0
 */
@SpringBootApplication
public class PracticeApplication {

    /**
     * mainメソッド
     * アプリケーションを起動するmainメソッド
     *
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
    }
}
