package de.tu_ilmenau.mvc.bank.pojo;

/**
 * Author : Binbin Luo
 * Date : 08.04.2023
 */
public class Account {
    // 一般使用包装类，防止出现null异常
    // 普通简单的对象被称为pojo对象 plain Ordinary Java Object， 也叫做Java Bean

    // 主键
    private Long id;
    // AccountNumber
    private String actno;
    // 余额balance
    private Double balance;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", actno='" + actno + '\'' +
                ", balance=" + balance +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActno() {
        return actno;
    }

    public void setActno(String actno) {
        this.actno = actno;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Account(Long id, String actno, Double balance) {
        this.id = id;
        this.actno = actno;
        this.balance = balance;
    }

    public Account() {
    }
}
