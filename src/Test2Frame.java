//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class Product extends JInternalFrame {
//    private DefaultTableModel dtm;
//
//    public Product() {
//        setClosable(true);
//        setResizable(true);
//        setMaximizable(true);
//        setIconifiable(true);
//        setTitle("Product");
//
//        setBounds(100, 20, 400, 400);
//        JTabbedPane tabbedPane = new JTabbedPane();
//        getContentPane().add(tabbedPane);
//        JPanel addProductPanel = new JPanel(new GridLayout(9, 1, 10, 10));
//        addProductPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        tabbedPane.addTab("Add Product", addProductPanel);
//
//        JPanel showProductPanel = new JPanel(new BorderLayout());
//
//        String[] columName = {"id", "name", "price", "color", "comment", "images", "brand", "size", "qty", "Category_id", "user_id", "created_at"};
//        dtm = new DefaultTableModel(null, columName);
//        JTable table = new JTable(dtm);
//
//        showProductPanel.add(new JScrollPane(table), BorderLayout.CENTER);
//
//        tabbedPane.addTab("Show Product", showProductPanel);
//
//        JLabel labelName = new JLabel("Name");
//        JLabel labelprice = new JLabel("price");
//        JLabel labelColor = new JLabel("Color");
//        JLabel labelcomment = new JLabel("comment");
//        JLabel labelimage = new JLabel("images");
//        JLabel labelbrand = new JLabel("brand");
//        JLabel labelsize = new JLabel("size");
//        JLabel labelqty = new JLabel("qty");
//        JLabel labelCategory_id = new JLabel("Category_id");
//        JTextField textName = new JTextField();
//        JTextField textPrice = new JTextField();
//        JTextField textColor = new JTextField();
//        JTextField textComment = new JTextField();
//        JTextField textImage = new JTextField();
//        JTextField textBrand = new JTextField();
//        JTextField textSize = new JTextField();
//        JTextField textqty = new JTextField();
//        JTextField textCategory_Id = new JTextField();
//        addProductPanel.add(labelName);
//        addProductPanel.add(textName);
//
//        addProductPanel.add(labelprice);
//        addProductPanel.add(textPrice);
//
//        addProductPanel.add(labelColor);
//        addProductPanel.add(textColor);
//
//        addProductPanel.add(labelcomment);
//        addProductPanel.add(textComment);
//
//        addProductPanel.add(labelimage);
//        addProductPanel.add(textImage);
//
//        addProductPanel.add(labelbrand);
//        addProductPanel.add(textBrand);
//
//        addProductPanel.add(labelsize);
//        addProductPanel.add(textSize);
//
//        addProductPanel.add(labelqty);
//        addProductPanel.add(textqty);
//
//        addProductPanel.add(labelCategory_id);
//        addProductPanel.add(textCategory_Id);
//
//        JPanel panelSouth = new JPanel();
//        JButton buttonAdd = new JButton("ِAdd");
//        buttonAdd.setBackground(Color.orange);
//        panelSouth.add(buttonAdd);
//
//        JButton buttonShow = new JButton("Show Product");
//        buttonShow.setBackground(Color.orange);
//        panelSouth.add(buttonShow);
//
//        getContentPane().add(panelSouth, BorderLayout.SOUTH);
//
//        buttonAdd.addActionListener(e -> {
//            try {
//                DB db = new DB();
//                db.getStatement().executeUpdate("insert into products(name, price, color, comment, images, brand, size, qty, Category_id, user_id, created_at) values('" + textName.getText() + "','" + textPrice.getText() + "','" + textColor.getText() + "','" + textComment.getText() +
//                        "','" + textImage.getText() + "','" + textBrand.getText() + "','" + textSize.getText() +
//                        "','" + textqty.getText() + "','" + textCategory_Id.getText() + "', 1, NOW())");
//                db.close();
//                filShowProduct(); // refresh the table
//            } catch (Exception exception) {
//                System.out.println("exception error" + exception.getMessage());
//            }
//        });
//
//        buttonShow.addActionListener(e -> {
//            filShowProduct(); // refresh the table
//        });
//
//        table.getModel().addTableModelListener(e -> {
//            int row = e.getFirstRow();
//            int col = e.getColumn();
//            String value = (String) table.getValueAt(row, col);
//            String showProduct = (String) table.getValueAt(row, 0);
//            try {
//                DB db = new DB();
//                db.getStatement().executeUpdate("update products set " + columName[col] + "='" + value + "' where id =" + showProduct);
//                db.close ();
//            } catch (Exception exception) {
//                System.out.println("exception error" + exception.getMessage());
//            }
//        });
//    }
//
//    void filShowProduct() {
//        dtm.setRowCount(0);
//        DB db = null;
//        try {
//            db = new DB();
//            ResultSet resultSet = db.getStatement().executeQuery("select id, name, price, color, comment");
//
//            throw new RuntimeException(new Exception());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}