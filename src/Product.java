import com.mysql.cj.xdevapi.AddResult;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;

public class Product extends JInternalFrame {
    private DefaultTableModel dtm;
    private JComboBox<Category> co;
    private JColorChooser color;
    private boolean editAble = true;

    public Product(User loginUser) {
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setTitle("Product");

        setBounds(100, 20, 600, 400);
        JTabbedPane tabbedPane = new JTabbedPane();
        getContentPane().add(tabbedPane);
        JPanel addProductPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        addProductPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel pp = new JPanel(new BorderLayout());
        pp.add(addProductPanel);
        tabbedPane.addTab("Add Product", pp);
        tabbedPane.setBackground(Color.GREEN);

        JPanel showProductPanel = new JPanel(new BorderLayout());
        showProductPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tabbedPane.addTab("Show Product", showProductPanel);
        tabbedPane.setBackgroundAt(1,Color.RED);
        tabbedPane.setBackgroundAt(0,Color.yellow);
        JList list=new JList<>();
        JPanel filePanel = new JPanel(new GridLayout(1, 2, 5, 5));
        JTextField textFieldImage = new JTextField();
        textFieldImage.setEnabled(false);
        textFieldImage.setBackground(Color.WHITE);
        filePanel.add(textFieldImage);

        JButton buttonImage = new JButton("ChoiceImage");
        filePanel.add(buttonImage);
        buttonImage.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int choice = fileChooser.showOpenDialog(null);
            if (choice == 0) {
                textFieldImage.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        String[] columnNames = {"id", "name", "price", "color", "comment", "images", "brand", "size", "qty", "Category_id", "user_id", "created_at"};
        dtm = new DefaultTableModel(null, columnNames);
        JTable table = new JTable(dtm);
        JScrollPane tableScrollPane = new JScrollPane(table);
        showProductPanel.add(tableScrollPane, BorderLayout.CENTER);
        filShowProduct();

        JLabel labelName = new JLabel("Name:");
        JLabel labelPrice = new JLabel("Price:");
        JLabel labelColor = new JLabel("Color:");
        JLabel labelComment = new JLabel("Comment:");
        JLabel labelImage = new JLabel("Image:");
        JLabel labelBrand = new JLabel("Brand:");
        JLabel labelSize = new JLabel("Size:");
        JLabel labelQty = new JLabel("Quantity:");
        JLabel labelCategoryId = new JLabel("Category ID:");

        JTextField textName = new JTextField();
        JTextField textPrice = new JTextField();
        JTextField textColor = new JTextField();
        JTextField textComment = new JTextField();

        JTextField textBrand = new JTextField();
        JTextField textSize = new JTextField();
        JTextField textQty = new JTextField();

        co = new JComboBox<>(Category.categoryArrayList().toArray(new Category[0]));
        color = new JColorChooser();
        addProductPanel.add(labelName);
        addProductPanel.add(textName);
        addProductPanel.add(labelPrice);
        addProductPanel.add(textPrice);
        addProductPanel.add(labelColor);
        addProductPanel.add(color);
        addProductPanel.add(labelComment);
        addProductPanel.add(textComment);
        addProductPanel.add(labelImage);
        addProductPanel.add(filePanel);
        addProductPanel.add(labelBrand);
        addProductPanel.add(textBrand);
        addProductPanel.add(labelSize);
        addProductPanel.add(textSize);
        addProductPanel.add(labelQty);
        addProductPanel.add(textQty);
        addProductPanel.add(labelCategoryId);
        addProductPanel.add(co);

        JPanel panelSouth = new JPanel();
        JButton buttonAdd = new JButton("Add Product");
        buttonAdd.setBackground(Color.orange);
        JButton buttonDelete = new JButton("Delete");
        buttonDelete.setBackground(Color.orange);
        JButton buttonClear = new JButton("Clear");
        buttonClear.setBackground(Color.orange);
        panelSouth.add(buttonAdd);

        panelSouth.add(buttonClear);

        pp.add(panelSouth , BorderLayout.SOUTH);
       showProductPanel.add(buttonDelete , BorderLayout.SOUTH);

       buttonClear.addActionListener(e -> {
            textName.setText("");
            textPrice.setText("");
            textColor.setText("");
            textComment.setText("");
            textFieldImage.setText("");
            textBrand.setText("");
            textSize.setText("");
            textQty.setText("");
        });
//
        buttonAdd.addActionListener(e -> {
            editAble = false;
            Category category = (Category) co.getSelectedItem();
//            int coId=category.getId();
            try {
                DB db = new DB();

                db.getStatement().executeUpdate("INSERT INTO product (name, price, color, comment, images, brand, size, qty, Category_id,user_id) " +
                        "VALUES ('" + textName.getText() + "', '" + textPrice.getText() + "', '" + textColor.getText() + "', '" +
                        textComment.getText() + "', '" + textFieldImage.getText() + "', '" + textBrand.getText() + "', '" +
                        textSize.getText() + "', '" + textQty.getText() + "', '" + category.getId() + "',"+loginUser.getId()+")");
                db.close();

                filShowProduct();
            } catch (Exception exception) {
                System.out.println("exception error" + exception.getMessage());
            }
            editAble = true;
        });

        table.getModel().addTableModelListener(e -> {
            if (editAble) {
                int row = e.getFirstRow();
                int col = e.getColumn();

                if (row >= 0 && row < table.getRowCount() && col >= 0 && col < table.getColumnCount()) {
                    String value = (String) table.getValueAt(row, col);
                    String productId = (String) table.getValueAt(row, 0);

                    try {
                        DB db = new DB();
                        db.getStatement().executeUpdate("UPDATE product SET " + columnNames[col] + " = '" + value + "' WHERE id = " + productId);
                        db.close();
                    } catch (Exception exception) {
                        System.out.println("exception error" + exception.getMessage());
                    }
                } else {
                    System.out.println("Invalid index: row " + row + ", col " + col);
                }
            }

        });
        buttonDelete.addActionListener(e -> {
            editAble=false;
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String productId = (String) table.getValueAt(selectedRow, 0);
                try {
                    DB db = new DB();
                    db.getStatement().executeUpdate("DELETE FROM product WHERE id = " + productId);
                    db.close();

                    dtm.removeRow(selectedRow);
                } catch (Exception exception) {
                    System.out.println("exception error" + exception.getMessage());
                }
            }
            editAble=true;
        });

    }
    //
    private Category getCategoryById(int id) {
        for (Category category : Category.categoryArrayList()) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }
    void filShowProduct() {
        dtm.setRowCount(0);
        DB db = null;
        try {
            db = new DB();
            ResultSet resultSet = db.getStatement().executeQuery("SELECT p.id, p.name, p.price, p.color, p.comment, p.images, p.brand, p.size, p.qty, p.Category_id, p.user_id, p.created_at,c.name Category_name\n" +
                    "FROM product p join categories c on(p.Category_id=c.id )");
            while (resultSet.next()) {
                String[] row = {
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("price"),
                        resultSet.getString("color"),
                        resultSet.getString("comment"),
                        resultSet.getString("images"),
                        resultSet.getString("brand"),
                        resultSet.getString("size"),
                        resultSet.getString("qty"),
                        resultSet.getString("Category_name"),
                        resultSet.getString("user_id"),
                        resultSet.getString("created_at")
                };
                dtm.addRow(row);
            }
            db.close();
        } catch (Exception exception) {
            System.out.println("exception error" + exception.getMessage());
        }
    }
}
