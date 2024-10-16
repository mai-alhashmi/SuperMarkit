import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class Order {
    private int id, customerId;
    private Date dateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public static void addOrder(int id, String date) {
        try {
            DB db = new DB();
            Connection connection = db.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement("insert into orders(id , order_time)values(?,?)");
            prepareStatement.setInt(1,id);
            prepareStatement.setString(2,date);
            prepareStatement.executeUpdate();

//            String qry = "insert into orders( id , order_time) values(" + id + " , '" + date + "')";
//            db.getStatement().executeUpdate(qry) ;
            db.close();
        } catch (Exception exception) {
            System.out.println("exception error" + exception.getMessage());
        }
    }

    public static int getLastOrderId() {
        try {
            DB db = new DB();
            ResultSet resultSet = db.getStatement().executeQuery("select max(id) m from orders");
            int newOrderID = 1;
            if (resultSet.next()) {
                newOrderID = resultSet.getInt("m") + 1;
            }
            db.close();
            return newOrderID;
        } catch (Exception ex) {
            System.out.println("Exception : " + ex.getMessage());
            return 1;
        }
    }
}