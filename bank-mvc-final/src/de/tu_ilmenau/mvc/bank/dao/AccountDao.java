package de.tu_ilmenau.mvc.bank.dao;

import de.tu_ilmenau.mvc.bank.pojo.Account;

import java.util.List;

/**
 * Author : Binbin Luo
 * Date : 09.04.2023
 */
public interface AccountDao {
    int insert(Account act);
    int deleteByActno(String actno);
    int deleteById(Long id);
    int update(Account act);
    Account selectByActno(String actno);
    List<Account> selectAll();
}
