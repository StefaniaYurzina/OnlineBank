package com.bank.services.interfaces;

import com.bank.models.Access;

public interface AccessService {
    Access get(String email);
    Access delete(Long id);

    boolean check(String code);
}
