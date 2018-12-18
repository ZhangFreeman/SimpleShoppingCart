package service;

import dao.CartDao;
import dao.ItemDao;
import db.CartDaoImpl;
import db.DBConnection;
import db.ItemDaoImpl;
import entity.Cart;
import entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class CartService implements CartDao {

    CartDao cartDao = null;
    ItemDao itemDao = null;

    public CartService() {
        Connection conn = null;
        try {
            conn = new DBConnection().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        itemDao = new ItemDaoImpl(conn);
        cartDao = new CartDaoImpl(conn);
    }

    @Override
    public boolean addToCart(int uid, int gid, int num) {
        return cartDao.addToCart(gid, uid, num);
    }

    @Override
    public boolean deleteFromCart(int gid, int uid) {
        return cartDao.deleteFromCart(gid, uid);
    }

    @Override
    public Map<Integer, Integer> getCartItems(int uid) {
        return cartDao.getCartItems(uid);
    }


    public Cart getCart(int uid) {
        Map<Integer, Integer> items = cartDao.getCartItems(uid);

        Set<Integer> keys = items.keySet();
        Iterator<Integer> it = keys.iterator();
        Cart cart = new Cart();
        cart.setId(uid);
        while(it.hasNext()) {
            int itemId = it.next();
            Item item = itemDao.getItem(itemId);
            cart.addGoodsInCart(item, items.get(itemId));
        }
        return cart;
    }

    public static void main(String[] args) {


        CartService cartService = new CartService();

        System.out.println(cartService.addToCart(6, 1, 2));
        System.out.println(cartService.getCartItems(1).size());
        System.out.println(cartService.getCart(1));
        System.out.println(cartService.deleteFromCart(5, 1));
        System.out.println(cartService.getCartItems(1).size());
    }

}
