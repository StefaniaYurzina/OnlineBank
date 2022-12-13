package com.bank.controllers;

import com.bank.services.implementations.AccessServiceImpl;
import com.bank.singleton.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/access")
public class AccessController {
    private final AccessServiceImpl accessService;

    @Autowired
    public AccessController(AccessServiceImpl accessService) {
        this.accessService = accessService;
    }

    @GetMapping
    public @ResponseBody String get(@RequestParam String email) {
        return Json.INSTANCE.get().toJson(accessService.get(email));
    }

    @PostMapping
    public @ResponseBody String check(@RequestParam String code) {
        return Json.INSTANCE.get().toJson(accessService.check(code));
    }

    @DeleteMapping
    public @ResponseBody String delete(@RequestParam Long id) {
        return Json.INSTANCE.get().toJson(accessService.delete(id));
    }
}
