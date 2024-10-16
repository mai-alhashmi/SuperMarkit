import java.sql.SQLException;

public class OrderDetails {
    private  int id, orderId, productId, qty;
    private double Price, vat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double Price() {
        return Price;
    }

    public void setPrice(double unitPrice) {
        this.Price = unitPrice;
    }



    public void setTax(double vat) {
        this.vat = vat;
    }

    public double getPrice() {
        return Price;
    }

    public double getVat() {
        return vat;
    }

    public static void addOrderDetail(int orderId, String productId, String qty, String Price , String vat ){
        try {
            DB db = new DB();

            String qry = "insert into order_details( order_id ,product_id ,qty,price,vat) values(" + orderId + " , " + productId
                    + "," + qty + " ," +Price + "," + vat +  ")";
            db.getStatement().executeUpdate(qry);
            db.close();

        } catch(Exception exception) {
                System.out.println("exception error"+exception.getMessage());

        }
    }
}

