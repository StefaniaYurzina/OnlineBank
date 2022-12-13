package com.bank.services.interfaces;

import com.bank.models.Client;

import java.util.Optional;

public interface ClientService {
    Client add(String name, String email);
    Optional<Client> get(Long id);
    Optional<Client> delete(Long id);
}
