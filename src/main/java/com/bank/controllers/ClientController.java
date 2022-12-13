package com.bank.controllers;

import com.bank.services.implementations.ClientServiceImpl;
import com.bank.singleton.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientServiceImpl clientService;

    @Autowired
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public @ResponseBody String add(@RequestParam String name, @RequestParam String email) {
        Pattern regexMail = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[a-z]{2,4}\\b");
        Pattern regexName = Pattern.compile("^[a-zA-Z]*$");

        Matcher matcherName = regexName.matcher(name);
        Matcher matcherMail = regexMail.matcher(email);

        if (!matcherMail.matches() || !matcherName.matches()) {
            return Json.INSTANCE.get().toJson("Incorrect personal data");
        } else {
            return Json.INSTANCE.get().toJson(clientService.add(name, email));
        }
    }

    @GetMapping
    public @ResponseBody String get(@RequestParam Long id) {
        return Json.INSTANCE.get().toJson(clientService.get(id));
    }

    @DeleteMapping
    public @ResponseBody String delete(@RequestParam Long id) {
        return Json.INSTANCE.get().toJson(clientService.delete(id));
    }
}
