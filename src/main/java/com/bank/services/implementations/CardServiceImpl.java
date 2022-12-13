package com.bank.services.implementations;

import com.bank.models.Card;
import com.bank.repository.CardDAO;
import com.bank.services.interfaces.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {
    private final CardDAO cardDAO;

    @Autowired
    public CardServiceImpl(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    @Override
    public Card add(String number, String currency, Integer cvv, String date, Long idClient) {
        Card card = new Card();
        card.setCvv(cvv);
        card.setCurrency(currency);
        card.setNumber(number);
        card.setDate(date);
        card.setIdClient(idClient);
        card.setBalance(0F);

        cardDAO.save(card);
        return card;
    }

    @Override
    public Optional<Card> get(Long id) {
        return cardDAO.findById(id);
    }

    @Override
    public Card takeOff(Long idClient, String number, Float money) {
        Optional<Card> optionalCard = cardDAO.findByIdClientAndNumber(idClient, number);
        Card card = new Card();

        if (optionalCard.isPresent()) {
            card = optionalCard.get();

            if (card.getBalance() > money) {
                float balance = card.getBalance() - money;
                card.setBalance(balance);

                cardDAO.save(card);
            } else {
                return new Card();
            }
        }

        return card;
    }

    @Override
    public Card topUp(Long idClient, String number, Float money) {
        Optional<Card> optionalCard = cardDAO.findByIdClientAndNumber(idClient, number);
        Card card = new Card();

        if (optionalCard.isPresent()) {
            card = optionalCard.get();

            float balance = card.getBalance() + money;
            card.setBalance(balance);

            cardDAO.save(card);
        }

        return card;
    }

    @Override
    public Card transfer(Long idClient, String numberOwner, Float money, String numberRecipient) {
        Optional<Card> optionalCard = cardDAO.findByIdClientAndNumber(idClient, numberOwner);
        Optional<Card> cardOptional = cardDAO.findByNumber(numberRecipient);
        Card cardOwner = new Card();

        if (optionalCard.isPresent() && cardOptional.isPresent()) {
            cardOwner = optionalCard.get();
            Card cardRecipient = cardOptional.get();

            if (cardOwner.getBalance() < money) {
                float balance = cardOwner.getBalance() - money;
                float balanceRecipient = cardRecipient.getBalance() + money;
                cardOwner.setBalance(balance);
                cardRecipient.setBalance(balanceRecipient);

                cardDAO.save(cardOwner);
                cardDAO.save(cardRecipient);
            } else {
                return new Card();
            }
        }

        return cardOwner;
    }
}
