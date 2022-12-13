package com.bank.services.interfaces;

import com.bank.models.Card;

import java.util.Optional;

public interface CardService {
    Card add(String number, String currency, Integer cvv, String date, Long idClient);
    Optional<Card> get(Long id);
    Card takeOff(Long idClient, String number, Float money);
    Card topUp(Long idClient, String number, Float money);
    Card transfer(Long idClient, String numberOwner, Float money, String numberRecipient);
}
