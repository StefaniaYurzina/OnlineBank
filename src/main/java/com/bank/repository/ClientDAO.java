package com.bank.repository;

import com.bank.models.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientDAO extends CrudRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
}
