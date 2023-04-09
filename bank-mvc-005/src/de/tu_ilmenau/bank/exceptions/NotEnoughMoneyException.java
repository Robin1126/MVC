package de.tu_ilmenau.bank.exceptions;

/**
 * Author : Binbin Luo
 * Date : 08.04.2023
 */
public class NotEnoughMoneyException extends Exception{
    public NotEnoughMoneyException() {
    }

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
