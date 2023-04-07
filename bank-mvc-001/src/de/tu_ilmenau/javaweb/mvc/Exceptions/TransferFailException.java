package de.tu_ilmenau.javaweb.mvc.Exceptions;

/**
 * Author : Binbin Luo
 * Date : 07.04.2023
 */
public class TransferFailException extends Exception{
    public TransferFailException() {
    }

    public TransferFailException(String message) {
        super(message);
    }
}
