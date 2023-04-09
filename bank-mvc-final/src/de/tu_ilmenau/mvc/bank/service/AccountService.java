package de.tu_ilmenau.mvc.bank.service;

import de.tu_ilmenau.mvc.bank.exceptions.AppException;
import de.tu_ilmenau.mvc.bank.exceptions.NotEnoughMoneyException;

/**
 * Author : Binbin Luo
 * Date : 09.04.2023
 */
public interface AccountService {
    void transfer(String fromActno, String toActno, double money)
            throws NotEnoughMoneyException, AppException;
}
