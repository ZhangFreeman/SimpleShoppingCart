package service;

import dao.UserDao;
import db.DBConnection;
import db.UserDaoImpl;
import entity.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserService implements UserDao {

    private UserDao userDao = null;

    public UserService() {
        Connection conn = null;
        try {
            conn = new DBConnection().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        userDao = new UserDaoImpl(conn);
    }

    @Override
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public int addUser(String userName, String passwd) {
        return userDao.addUser(userName, passwd);
    }

    public static String pwdHashing(String password) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(password.getBytes());
        byte[] messageDigestMD5 = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte bytes : messageDigestMD5) {
            sb.append(String.format("%02x", bytes & 0xff));
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        String res = UserService.pwdHashing("123");
        System.out.println(res);

    }
}
