package service;

import dao.ItemDao;
import db.DBConnection;
import db.ItemDaoImpl;
import entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemService implements ItemDao {

    private ItemDao itemDao = null;

    public ItemService() {
        Connection conn = null;
        try {
            conn = new DBConnection().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        itemDao = new ItemDaoImpl(conn);
    }

    @Override
    public List<Item> getAllItems() {

        return itemDao.getAllItems();
    }

    @Override
    public Item getItem(int id) {

        return itemDao.getItem(id);
    }
}
