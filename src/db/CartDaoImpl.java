package db;

import dao.CartDao;
import entity.Cart;
import entity.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartDaoImpl implements CartDao {

    private Connection conn = null;

    private PreparedStatement pstmt = null;

    public CartDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean addToCart(int gid, int uid, int num) {
        int rs = 0;
        try {
            String message = this.getItem(uid, gid);
            //System.out.println(message);

            if (!"".equals(message)) {
                int goodsCount = Integer.valueOf(message.split("&")[1]);
                String sql = "update shop_cart_item set quantity=? where user_id=? and item_id=?";
                pstmt = this.conn.prepareStatement(sql);
                pstmt.setInt(1, goodsCount + num);
                pstmt.setInt(2, uid);
                pstmt.setInt(3, gid);
            } else {
                String sql = "insert into shop_cart_item (user_id,item_id,quantity) values (?,?,?);";
                pstmt = this.conn.prepareStatement(sql);
                pstmt.setInt(1, uid);
                pstmt.setInt(2, gid);
                pstmt.setInt(3, num);
            }
            rs = pstmt.executeUpdate();
            pstmt.close();
            return rs == 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
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


    @Override
    public boolean deleteFromCart(int gid, int uid) {
        int rs = 0;
        try {
            String sql = "delete from shop_cart_item where user_id = ? and item_id= ?";
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(1, uid + "");
            pstmt.setString(2, gid + "");
            rs = pstmt.executeUpdate();
            pstmt.close();
            return rs == 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
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

    @Override
    public Map<Integer, Integer> getCartItems(int uid) {
        Map<Integer, Integer> items = new HashMap<>();
        ResultSet rs = null;
        try {
            String sql = "select * from shop_cart_item where user_id = ? ";
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(1, uid + "");
            rs = pstmt.executeQuery();
            while (rs.next()) {

                items.put(rs.getInt("item_id"), rs.getInt("quantity"));
            }
            pstmt.close();
            rs.close();
            return items;
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

    public String getItem(int uid, int gid) {
        ResultSet rs = null;
        try {
            String sql = "select * from shop_cart_item where user_id =? and item_id=?";
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, uid);
            pstmt.setInt(2, gid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("item_id") + "&" + rs.getInt("quantity");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

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
        return "";
    }

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = new DBConnection().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        CartDao cartDao = new CartDaoImpl(conn);
        System.out.println(cartDao.addToCart(6, 1, 2));
        System.out.println(cartDao.getCartItems(1).size());
        System.out.println(cartDao.deleteFromCart(5, 1));
        System.out.println(cartDao.getCartItems(1).size());
    }
}
