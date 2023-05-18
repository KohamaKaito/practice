package com.example.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * PracticeApplication
 * アプリケーションの起点となるクラス
 *
 * @author kohama
 * @version 0.0
 */
@SpringBootApplication
public class PracticeApplication extends SpringBootServletInitializer {

    /**
     * mainメソッド
     * アプリケーションを起動するmainメソッド
     *
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
    }

    /**
     * warファイルのデプロイ時に使用するメソッド
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PracticeApplication.class);
    }
}
