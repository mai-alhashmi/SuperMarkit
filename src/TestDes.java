import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;

public class TestDes extends JInternalFrame {
    private JPanel panel1;
    private JTextField textFieldQty;
    private JButton addButton;
    private JLabel labLProduct;
    private JComboBox<Category> comboBoxCat;
    private JComboBox<Category> comboBoxSubCat;
    private JComboBox<ProductDb> comboBoxProduct;
    private JLabel lableTotal;
    private JLabel lableTotal1;
    private JButton buttonSave;
    //  private JList list1;
    private DefaultTableModel dtm;


    public TestDes() {
        setBounds(100, 20, 600, 400);

        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);


        //  String columns[] = {"id", "name", "qty", "price", "vat", "total"};
        String columns[] = {"id", "name", "qty", "price", "vat (19%)", "total"};
        dtm = new DefaultTableModel(null, columns);
        JTable tableProducts = new JTable(dtm);
        tableProducts.setForeground(Color.blue);
        JScrollPane scrollPane = new JScrollPane(tableProducts);
        getContentPane().add(panel1);
        panel1.add(scrollPane);
        for (Category category : Category.listMainCategory()) {
            comboBoxCat.addItem(category);
        }
        comboBoxCat.addActionListener(e -> {
            Category mainCat = (Category) comboBoxCat.getSelectedItem();
            comboBoxSubCat.removeAllItems();
            for (Category category : Category.listSubCategory(mainCat.getId())) {
                comboBoxSubCat.addItem(category);
            }

        });
        for (ProductDb productDb : ProductDb.productsArrayList()) {
            comboBoxProduct.addItem(productDb);
        }
        comboBoxSubCat.addActionListener(e -> {
            Category mainCat = (Category) comboBoxSubCat.getSelectedItem();
            comboBoxProduct.removeAllItems();
            if (mainCat != null) {
                for (ProductDb productDb : ProductDb.productsArrayList(mainCat.getId())) {
                    comboBoxProduct.addItem(productDb);
                }
            }
        });
        addButton.addActionListener(e -> {

            ProductDb productDb = (ProductDb) comboBoxProduct.getSelectedItem();
            int qty = Integer.parseInt(textFieldQty.getText());
            double rowTotal = Math.round(productDb.getPrice() * 1.19 * qty);
            String[] row = {
                    productDb.getId() + "",
                    productDb.getName(),
                    qty + "",
                    productDb.getPrice() + "",
                    //    productDb.getPrice() * 0.19  + "(19%)",
                    productDb.getPrice() * 0.19 + "",
                    rowTotal + ""
            };
            dtm.addRow(row);
            double s = Double.parseDouble(lableTotal1.getText());
            lableTotal1.setText((s + rowTotal) + "");

        });
        buttonSave.addActionListener(e -> {
            int oId = Order.getLastOrderId();
            Order.addOrder(oId, "2024-9-20 10:30");
            // {"id" ,"name" ,"qty" ,"price" ,"vat" , "total"};
            int orderID = Order.getLastOrderId();
            Order.addOrder(orderID, "2024-09-20 11:07");
            for (int i = 0; i < dtm.getRowCount(); i++) {
                String prodId = (String) dtm.getValueAt(i, 0);
                String qty = (String) dtm.getValueAt(i, 2);
                String price = (String) dtm.getValueAt(i, 3);
                String vat = (String) dtm.getValueAt(i, 4);
                //   String total = (String) dtm.getValueAt(i,5);
                OrderDetails.addOrderDetail(orderID, prodId, qty, price, vat);
            }
        });

        }
    }



