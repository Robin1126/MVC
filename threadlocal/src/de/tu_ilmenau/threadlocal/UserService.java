package de.tu_ilmenau.threadlocal;

/**
 * Author : Binbin Luo
 * Date : 08.04.2023
 */
public class UserService {
    private UserDao userDao = new UserDao();

    public void save() {
        Connection connection = DBUtil.getConnection(); // 新建connection 绑定大map

        userDao.insert();
    }
}
