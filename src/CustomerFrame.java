import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerFrame extends JInternalFrame {
    private DefaultTableModel dtm;
    private JTextField textFieldName;
    private JTextField textFieldphone;
    private JTextField textFieldEmail;
    private JTextField textFieldaddress;

   private boolean editAble=true;

    public CustomerFrame() {
        setTitle("Customer");
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);

        setBounds(300, 150, 500, 400);
        String[] columName = {"id", "name", "mobile", "address", "email", "password", "created_at", "phone", "street_no", "postal_cood"};

        dtm = new DefaultTableModel(null, columName);
        fileCustomerTable();
        JTable table = new JTable(dtm);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);
        scrollPane.setBackground(Color.blue);
        JLabel labelName = new JLabel("Name");
        labelName.setForeground(Color.blue);
        JLabel labelphone = new JLabel("phone");
        labelphone.setForeground(Color.blue);
        JLabel labelEmail = new JLabel("Email");
        labelEmail.setForeground(Color.blue);
        JLabel labelAddress = new JLabel("Address");
        labelAddress.setForeground(Color.blue);
        JLabel label = new JLabel("");
        textFieldName = new JTextField();
        textFieldphone = new JTextField();
        textFieldEmail = new JTextField();
        textFieldaddress = new JTextField();

        JButton buttonAdd = new JButton("Add Customer");
        buttonAdd.setBackground(Color.orange);

        JPanel panel = new JPanel(new GridLayout(2, 4, 2, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(labelName);
        panel.add(labelphone);
        panel.add(labelEmail);
        panel.add(labelAddress);
        panel.add(label);
        panel.add(textFieldName);
        panel.add(textFieldphone);
        panel.add(textFieldEmail);
        panel.add(textFieldaddress);
        panel.add(buttonAdd);
        getContentPane().add(panel, BorderLayout.NORTH);

        JPanel panelSouth = new JPanel();
        JButton buttondelete = new JButton("delete Customer");
        buttondelete.setBackground(Color.orange);

        panelSouth.add(buttondelete);
        getContentPane().add(panelSouth, BorderLayout.SOUTH);
        buttonAdd.addActionListener(e -> {
            editAble=false;

                    DB db = null;
                    try {
                        db = new DB();
                        db.getStatement().executeUpdate("insert into customers(name,phone,email,address) values('" + textFieldName.getText() + "','" + textFieldphone.getText() + "','" + textFieldEmail.getText() + "','" + textFieldaddress.getText() + "')");

                        db.close();
                        fileCustomerTable();
                    } catch (Exception ex) {


                        System.out.println("exception error" + ex.getMessage());
                    }
                    editAble=true;
        });

        buttondelete.addActionListener(e -> {
            editAble=false;
            int row = table.getSelectedRow();
            String customerId = (String) dtm.getValueAt(row, 0);
            try {
                DB db = new DB();
                db.getStatement().executeUpdate("delete from customers where id=" + customerId);
                db.close();
                dtm.removeRow(row);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            editAble=true;
        });
        table.getModel().addTableModelListener(e ->{
            if (editAble) {


                int row = e.getFirstRow();
                int col = e.getColumn();
                String value = (String) table.getValueAt(row, col);
                String customerId = (String) table.getValueAt(row, 0);
                try {
                    DB db = new DB();
                    db.getStatement().executeUpdate("update customers set " + columName[col] + "='" + value + "' where id =" + customerId);
                    db.close();
                } catch (Exception exception) {
                    System.out.println("exception error" + exception.getMessage());

                }
            }
        } );
    }

    void fileCustomerTable() {
        dtm.setRowCount(0);
        DB db = null;
        try {
            db = new DB();
            ResultSet resultSet = db.getStatement().executeQuery("select id, name, mobile, address, email, password, created_at, phone, street_no, postal_cood from customers");
            while (resultSet.next()) {
                String[] row = {

                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("mobile"),
                        resultSet.getString("address"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("created_at"),
                        resultSet.getString("phone"),
                        resultSet.getString("street_no"),
                        resultSet.getString("postal_cood"),


                };
                dtm.addRow(row);
            }
            db.close();
        } catch (Exception e) {
            System.out.println("exception error : " + e.getMessage());

        }

    }


}