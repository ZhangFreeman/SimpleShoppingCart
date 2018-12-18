package dao;

import entity.Cart;

import java.util.List;
import java.util.Map;

public interface CartDao {

    public boolean addToCart(int gid, int uid, int num);

    public boolean deleteFromCart(int gid, int uid);

    public Map<Integer, Integer> getCartItems(int uid);
}
