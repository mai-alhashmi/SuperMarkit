import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductDb {
    //id, name, price, color, comment, images, brand, size, qty, Category_id, user_id, created_at
    private int id, price;
    private String name, brand, size, qty;

    public ProductDb() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public ProductDb(int id, int price, String name, String brand, String size, String qty) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.brand = brand;
        this.size = size;
        this.qty = qty;
    }


    @Override
    public String toString() {
        return name + " - price : " +price;


    }

    static ArrayList<ProductDb> productsArrayList() {
        ArrayList<ProductDb> products = new ArrayList<>();
        try {
            DB db = new DB();
            ResultSet resultSet = db.getStatement().executeQuery("SELECT id, name,price,qty,size from product");
            products.add(null);
            while (resultSet.next()) {
                ProductDb p = new ProductDb();
                p.id = resultSet.getInt("id");
                p.price = resultSet.getInt("price");
                p.name = resultSet.getString("name");
                products.add(p);
            }
            db.close();
        } catch (Exception ex) {
            System.out.println("exception error" + ex.getMessage());
        }
        return products;
    }

    static ArrayList<ProductDb> productsArrayList(int category_id) {
        ArrayList<ProductDb> products = new ArrayList<>();
        try {
            DB db = new DB();
            ResultSet resultSet = db.getStatement().executeQuery("SELECT id, name,price,qty,size from product where category_id= " + category_id);
            products.add(null);
            while (resultSet.next()) {
                ProductDb p = new ProductDb();
                p.id = resultSet.getInt("id");
                p.price = resultSet.getInt("price");
                p.name = resultSet.getString("name");
                products.add(p);
            }
            db.close();
        } catch (Exception ex) {
            System.out.println("exception error" + ex.getMessage());
        }
        return products;
    }
}