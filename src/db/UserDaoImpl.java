package db;

import dao.UserDao;
import entity.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    private Connection conn = null;

    private PreparedStatement pstmt = null;

    public UserDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public User getUser(int id) {
        User user = null;
        ResultSet rs = null;
        try {
            String sql = "select * from shop_user where id =?";
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(1, id + "");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUid(rs.getInt("id"));
                user.setUname(rs.getString("username"));
                user.setPasswd(rs.getString("password"));
            }
            pstmt.close();
            rs.close();
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

    @Override
    public int addUser(String userName, String passwd) {
        int rs = 0;
        try {
            String sql = "insert into shop_user (username, password) values (?,?);";
            pstmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, userName);
            pstmt.setString(2, passwd);
            pstmt.executeUpdate();

            ResultSet resultSet = pstmt.getGeneratedKeys();
            if (resultSet.next()){
                rs=resultSet.getInt(1);
                return rs;
            }
            pstmt.close();
            return -1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = new DBConnection().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        UserDao userDao = new UserDaoImpl(conn);
        User user = userDao.getUser(1);
        System.out.println(user.getUname());
        int res = userDao.addUser("Cat", "123456");
        System.out.println(res);

    }
}
