package com.s28572.tpo07;

import com.google.googlejavaformat.java.Formatter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tpo07Application {

    public static void main(String[] args) {
        SpringApplication.run(Tpo07Application.class, args);
//        try {
//            String formattedSourceCode = new Formatter().formatSource("public class Main { public static void main(String[] args) { System.out.println(\"Hello, World!\"); } }");
//            System.out.println(formattedSourceCode);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
