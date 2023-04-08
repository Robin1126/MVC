package de.tu_ilmenau.javaweb.mvc.servlet;

import de.tu_ilmenau.javaweb.mvc.exceptions.AppException;
import de.tu_ilmenau.javaweb.mvc.exceptions.NotEnoughMoneyException;

/**
 * Author : Binbin Luo
 * Date : 08.04.2023
 * Service处理核心业务
 * 在这里专注处理业务，不和其它代码混合在一起
 *
 * 一般起名 XxxService， XxxBiz
 * 事务一定是在service当中控制的
 */
public class AccountService {

    // 这里定义一个 AccountDao 因为每个方法都有可能需要连接数据库
    private AccountDao accountDao = new AccountDao();

    // 这里的方法起名，一定要体现出处理的是什么业务。
    // 我们要体用一个能够实现转帐的业务方法（一个业务对应一个方法）

    /**
     * 转账业务逻辑的实现
     * @param fromActno 转出账号
     * @param toActno 转入账号
     * @param money 金额
     */
    public void transfer(String fromActno, String toActno, double money)
            throws NotEnoughMoneyException, AppException {
        // 查询余额是否充足
        Account fromAct = accountDao.selectByActno(fromActno);
        if (fromAct.getBalance() < money) {
            throw new NotEnoughMoneyException("余额不足");
        }
        // 程序到这里说明余额充足，查目标账户的余额
        Account toAct = accountDao.selectByActno(toActno);

        // 修改余额(这里只是修改了java对象中的余额，还需要update到数据库)
        fromAct.setBalance(fromAct.getBalance() - money);
        toAct.setBalance(toAct.getBalance() + money);

        // 提交到数据库,更新数据库
        int count = accountDao.update(fromAct);
        count += accountDao.update(toAct);
        if (count != 2) {
            throw new AppException("账户转账异常！");
        }
    }
}
