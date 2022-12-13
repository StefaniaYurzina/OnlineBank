package com.bank.repository;

import com.bank.models.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CardDAO extends CrudRepository<Card, Long> {
    Optional<Card> findByIdClient(Long id);
    Optional<Card> findByIdClientAndNumber(Long id, String number);
    Optional<Card> findByNumber(String number);
}
