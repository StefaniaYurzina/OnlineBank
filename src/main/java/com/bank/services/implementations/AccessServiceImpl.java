package com.bank.services.implementations;

import com.bank.hashCode.CRC32Hash;
import com.bank.models.Access;
import com.bank.models.Card;
import com.bank.models.Client;
import com.bank.repository.AccessDAO;
import com.bank.repository.CardDAO;
import com.bank.repository.ClientDAO;
import com.bank.services.interfaces.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccessServiceImpl implements AccessService {
    private final AccessDAO accessDAO;
    private final ClientDAO clientDAO;
    private final CardDAO cardDAO;
    private static final String NOREPLY_ADDRESS = "noreply@baeldung.com";

//    @Autowired
//    private JavaMailSender mailSender;

    @Autowired
    public AccessServiceImpl(AccessDAO accessDAO, ClientDAO clientDAO, CardDAO cardDAO) {
        this.accessDAO = accessDAO;
        this.clientDAO = clientDAO;
        this.cardDAO = cardDAO;
    }

    @Override
    public Access get(String email) {
        Access access = new Access();
        Optional<Client> optionalClient = clientDAO.findByEmail(email);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();

            Optional<Card> optionalCard = cardDAO.findByIdClient(client.getId());

            if (optionalCard.isPresent()) {
                Card card = optionalCard.get();

                String code = CRC32Hash.getHash(client.getEmail() + card.getNumber());

                access.setCode(code);
                accessDAO.save(access);

//                sendMail(code, client.getEmail());
            }
        }

        return access;
    }

    @Override
    public Access delete(Long id) {
        Access access = new Access();

        Optional<Access> optionalAccess = accessDAO.findById(id);
        if (optionalAccess.isPresent()) {
            access = optionalAccess.get();
            accessDAO.delete(access);
        }

        return access;
    }

    @Override
    public boolean check(String code) {
        return false;
    }

//    private void sendMail(String code, String email) {
//        try {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom(NOREPLY_ADDRESS);
//            message.setSubject("Your code.");
//            message.setText("Your activation code is " + code);
//            message.setTo(email);
//
//            mailSender.send(message);
//        } catch (MailException e) {
//            throw new IllegalStateException(e.getMessage());
//        }
//    }
}
