package dao;

import entity.Item;

import java.util.List;

public interface ItemDao {

    public Item getItem(int id);

    public List<Item> getAllItems();

}
