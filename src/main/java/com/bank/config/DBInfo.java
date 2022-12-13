package com.bank.config;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DBInfo {
    private String address;
    private String login;
    private String password;

    public DBInfo() {
        try(BufferedReader bufferedReader = new BufferedReader(
                new FileReader("./src/main/resources/application.properties")
        )) {
            int index = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                index++;

                if (index == 3) {
                    address = line.split("=")[1];
                }
                if (index == 4) {
                    login = line.split("=")[1];
                }
                if (index == 5) {
                    password = line.split("=")[1];
                }
                if (index == 6) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public @NotNull String url() {
        return address;
    }

    public @NotNull String login() {
        return login;
    }

    public @NotNull String password() {
        return password;
    }
}
