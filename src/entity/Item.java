package entity;

public class Item {

    private int id;
    private String name;
    private int price;
    private String picture;

    public Item(){ }

    public Item(int id,String name,String city,int price,int number,String picture) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.price = price;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    @Override
    public int hashCode() {
        return 31 * this.getId()+this.getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }
        if(obj instanceof Item){
            Item i = (Item)obj;
            if(this.getId()==i.getId()&&this.getName().equals(i.getName())){
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "ID: "+this.getId()+", Name"+this.getName();
    }
}
