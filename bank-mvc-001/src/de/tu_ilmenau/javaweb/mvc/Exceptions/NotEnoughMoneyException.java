package de.tu_ilmenau.javaweb.mvc.Exceptions;

/**
 * Author : Binbin Luo
 * Date : 07.04.2023
 */
public class NotEnoughMoneyException extends Exception {
    // 继承Exception
    // 异常类，有一个无参构造，一个有参构造

    public NotEnoughMoneyException() {
    }
    public NotEnoughMoneyException(String msg) {
       super(msg);
    }
}
