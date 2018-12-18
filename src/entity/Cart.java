package entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Cart {

    private Map<Item, Integer> goods;
    private double totalPrice;
    private int id;

    public Cart() {
        goods = new HashMap<Item,Integer>();
        totalPrice = .0;
        id = -1;
    }

    public Map<Item, Integer> getGoods() {
        return goods;
    }

    public void setGoods(Map<Item, Integer> goods) {
        this.goods = goods;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean addGoodsInCart(Item item ,int number) {
        if(goods.containsKey(item)) {
            goods.put(item, goods.get(item)+number);
        }
        else {
            goods.put(item, number);
        }
        calTotalPrice();
        return true;
    }

    public boolean removeGoodsFromCart(Item item) {
        goods.remove(item);
        calTotalPrice();
        return true;
    }

    public double calTotalPrice() {
        double sum = .0;
        Set<Item> keys = goods.keySet();
        Iterator<Item> it = keys.iterator();
        while(it.hasNext()) {
            Item i = it.next();
            sum += i.getPrice() * goods.get(i);
        }
        this.setTotalPrice(sum);
        return this.getTotalPrice();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
