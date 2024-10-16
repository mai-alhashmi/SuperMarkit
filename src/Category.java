import java.sql.ResultSet;
import java.util.ArrayList;

public class Category {
    private int id;
    private String name;
    private int category_id;


    static ArrayList<Category> categoryArrayList() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            DB db = new DB();
            ResultSet resultSet = db.getStatement().executeQuery("SELECT id, name from Categories");
           categories.add(null);
            while (resultSet.next()) {
                Category c = new Category();
                c.id = resultSet.getInt("id");
                c.name = resultSet.getString("name");
                categories.add(c);
            }
            db.close();
        } catch (Exception ex) {
            System.out.println("exception error" + ex.getMessage());
        }
        return categories;
    }
    static ArrayList<Category>listSubCategory(int cid) {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            DB db = new DB();
            ResultSet resultSet = db.getStatement().executeQuery("SELECT id, name from Categories where category_id=" + cid);
            categories.add(null);
            while (resultSet.next()) {
                Category c = new Category();
                c.id = resultSet.getInt("id");
                c.name = resultSet.getString("name");
                categories.add(c);
            }
            db.close();
        } catch (Exception ex) {
            System.out.println("exception error" + ex.getMessage());
        }
        return categories;
    }
    static ArrayList<Category>listMainCategory() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            DB db = new DB();
            ResultSet resultSet = db.getStatement().executeQuery("SELECT id, name from Categories where category_id is null");
            categories.add(null);
            while (resultSet.next()) {
                Category c = new Category();
                c.id = resultSet.getInt("id");
                c.name = resultSet.getString("name");
                categories.add(c);
            }
            db.close();
        } catch (Exception ex) {
            System.out.println("exception error" + ex.getMessage());
        }
        return categories;
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

    @Override
    public String toString() {
        return name;
    }
    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}