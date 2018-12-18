package db;

import dao.ItemDao;
import dao.UserDao;
import entity.Item;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {

    private Connection conn = null;

    private PreparedStatement pstmt = null;

    public ItemDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Item getItem(int id) {
        Item item = null;
        ResultSet rs = null;
        try {
            String sql = "select * from shop_item where id =?";
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(1, id + "");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                item = new Item();
                item.setPrice(rs.getInt("price"));
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPicture(rs.getString("image"));

            }
            pstmt.close();
            rs.close();
            return item;
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
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        ResultSet rs = null;
        try {
            String sql = "select * from shop_item";
            pstmt = this.conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setPrice(rs.getInt("price"));
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPicture(rs.getString("image"));
                items.add(item);
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

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = new DBConnection().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ItemDao itemDao = new ItemDaoImpl(conn);
        Item item = itemDao.getItem(1);
        System.out.println(itemDao.getAllItems().size());
    }
}
