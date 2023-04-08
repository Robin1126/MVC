package de.tu_ilmenau.threadlocal;

/**
 * Author : Binbin Luo
 * Date : 08.04.2023
 */
public class UserDao {
    public void insert() {
        Connection connection = DBUtil.getConnection();

        System.out.println("User DAO insert");
    }
}
