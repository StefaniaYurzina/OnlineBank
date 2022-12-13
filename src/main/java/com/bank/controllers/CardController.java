package com.bank.controllers;

import com.bank.services.implementations.CardServiceImpl;
import com.bank.singleton.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/card")
public class CardController {
    private final CardServiceImpl cardService;

    @Autowired
    public CardController(CardServiceImpl cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/add")
    public @ResponseBody String add(@RequestParam String number, @RequestParam String currency, @RequestParam Integer cvv,
                                    @RequestParam String date, @RequestParam Long idClient) {
        return Json.INSTANCE.get().toJson(cardService.add(number, currency, cvv, date, idClient));
    }

    @GetMapping
    public @ResponseBody String get(@RequestParam Long id) {
        return Json.INSTANCE.get().toJson(cardService.get(id));
    }

    @PostMapping("/takeoff")
    public @ResponseBody String takeOff(@RequestParam Long idClient, @RequestParam String number,
                                        @RequestParam Float money) {
        return Json.INSTANCE.get().toJson(cardService.takeOff(idClient, number, money));
    }

    @PostMapping("/topup")
    public @ResponseBody String topUp(@RequestParam Long idClient, @RequestParam String number,
                                      @RequestParam Float money) {
        return Json.INSTANCE.get().toJson(cardService.topUp(idClient, number, money));
    }

    @PostMapping("/transfer")
    public @ResponseBody String transfer(@RequestParam Long idClient, @RequestParam String numberOwner,
                                         @RequestParam Float money, @RequestParam String numberRecipient) {
        return Json.INSTANCE.get().toJson(cardService.transfer(idClient, numberOwner, money, numberRecipient));
    }
}
