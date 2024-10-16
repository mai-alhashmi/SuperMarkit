import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFram extends JInternalFrame {
    private DefaultTableModel dtm;
    private JTextField textFieldName;
    private JTextField textFieldMobile;
    private JTextField textFieldEmail;
    private JPasswordField textFieldPassword;

    private JComboBox<String> roleComBox;
    private boolean editAble=true;

    public UserFram(User loginUser) {
        setTitle("UserManger");
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);

        setBounds(100, 50, 400, 350);
        String[] columName = {"id", "name", "mobile", "email", "role", "created_at", "created_by"};
       // String[] dbColumNames = {"id", "name", "mobile", "email", "role", "created_at", "created_by"};
        dtm = new DefaultTableModel(null, columName);
        fillUserTable();

        JTable table = new JTable(dtm);
        String arrRol[]={"Admin","User","Editor"};
        JComboBox<String>comboBox=new JComboBox<>(arrRol);
        TableColumn column=table.getColumnModel().getColumn(4);
        column.setCellEditor(new DefaultCellEditor(comboBox));
        table.setBackground(Color.lightGray);
        JScrollPane scrollPane = new JScrollPane(table);
      //  scrollPane.setForeground(Color.orange);
        getContentPane().add(scrollPane);
        scrollPane.setBackground(Color.blue);

        JLabel labelName = new JLabel("Name");
        labelName.setForeground(Color.blue);
        JLabel labelMobile = new JLabel("Mobile");
        labelMobile.setForeground(Color.blue);
        JLabel labelEmail = new JLabel("Email");
        labelEmail.setForeground(Color.blue);
        JLabel labelRole=new JLabel("Role");
        JLabel label = new JLabel("");
        JLabel labelPassword = new JLabel("Password");
        textFieldName = new JTextField();
        textFieldMobile = new JTextField();
        textFieldEmail = new JTextField();
        textFieldPassword=new JPasswordField();
        roleComBox=new JComboBox<>(arrRol);
        JButton buttonAdd = new JButton("Add User");
        buttonAdd.setBackground(Color.orange);

        JPanel panel = new JPanel(new GridLayout(2, 6, 2, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(labelName);
        panel.add(labelMobile);
        panel.add(labelEmail);
        panel.add(labelRole);
        panel.add(labelPassword);
        panel.add(label);
        panel.add(textFieldName);
        panel.add(textFieldMobile);
        panel.add(textFieldEmail);
        panel.add(roleComBox);
        panel.add(textFieldPassword);
        panel.add(buttonAdd);
        getContentPane().add(panel, BorderLayout.NORTH);

        JPanel panelSouth=new JPanel();
        JButton buttondelete = new JButton("delete User");
        buttondelete.setBackground(Color.orange);

        panelSouth.add(buttondelete);

        getContentPane().add(panelSouth, BorderLayout.SOUTH);
        buttonAdd.addActionListener(e -> {
            editAble=false;
            try {
                DB db =new DB();

                db.getStatement().executeUpdate("insert into users(name,mobile,email,role,created_by,password) values('" + textFieldName.getText() + "','" + textFieldMobile.getText() + "','" + textFieldEmail.getText() + "','" + roleComBox.getSelectedItem()+ "',"+ loginUser.getId()+",'"+textFieldPassword.getText()+"')");
                db.close();

                fillUserTable();
            } catch(Exception exception) {
                System.out.println("exception error"+exception.getMessage());
            }
            editAble=true;
        });
        buttondelete.addActionListener(e -> {
            editAble=false;
           int row=table.getSelectedRow();
           String userId=(String)dtm.getValueAt(row,0);
            try {
               DB db=new DB();
               db.getStatement().executeUpdate("delete from users where id="+userId);
               db.close();
               dtm.removeRow(row);
           }catch (Exception exception){
               System.out.println("exception error"+exception.getMessage());
           }
            editAble=true;
        });
         table.getModel().addTableModelListener(e ->{
             if (editAble){
             int row=e.getFirstRow();
             int col=e.getColumn();
             String value=(String) table.getValueAt(row,col);
             String userId=(String)table.getValueAt(row,0);
             try {
           DB db=new DB();
           db.getStatement().executeUpdate("update users set "+columName[col]+"='"+value+"' where id ="+userId);
                     db.close();
             }catch (Exception exception) {
                 System.out.println("exception error" + exception.getMessage());

             }
             }
         } );
    }
    void fillUserTable() {
        dtm.setRowCount(0);
        DB db = null;
        try {
            db = new DB();
            ResultSet resultSet = db.getStatement().executeQuery("SELECT u.id, u.name,u.mobile, u.email, u.password, u.role, u.created_at,u.created_by,c.name created_name \n" +
                    "from users u left join users c on (u.created_by=c.id)");
            while (resultSet.next()) {
                String[] row = {
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("mobile"),
                        resultSet.getString("email"),
                        resultSet.getString("role"),
                        resultSet.getString("created_at"),
                        resultSet.getString("created_name")
                };
                dtm.addRow(row);
            }
            db.close();
        } catch (Exception e) {
            System.out.println("exception error : " + e.getMessage());
        }
    }
}







