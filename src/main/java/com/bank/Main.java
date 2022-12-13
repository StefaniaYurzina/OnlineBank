package com.bank;

import com.bank.config.DBInfo;
import com.bank.config.InitializationDB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        InitializationDB initializer = new InitializationDB(new DBInfo());
        initializer.initDB();

        SpringApplication.run(Main.class, args);
    }
}
