package de.tu_ilmenau.javaweb.mvc.exceptions;

/**
 * Author : Binbin Luo
 * Date : 08.04.2023
 */
public class AppException extends Exception{
    public AppException() {
    }

    public AppException(String message) {
        super(message);
    }
}
