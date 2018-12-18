package dao;

import entity.User;

public interface UserDao {

    public User getUser(int id);

    public int addUser(String userName, String passwd);
}
