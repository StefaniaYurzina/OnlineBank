package com.bank.services.implementations;

import com.bank.models.Client;
import com.bank.repository.ClientDAO;
import com.bank.services.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientDAO clientDAO;

    @Autowired
    public ClientServiceImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public Client add(String name, String email) {
        Client client = new Client();
        client.setEmail(email);
        client.setName(name);

        clientDAO.save(client);

        return client;
    }

    @Override
    public Optional<Client> get(Long id) {
        return clientDAO.findById(id);
    }

    @Override
    public Optional<Client> delete(Long id) {
        Optional<Client> optionalClient = clientDAO.findById(id);

        optionalClient.ifPresent(clientDAO::delete);

        return optionalClient;
    }
}
