import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryFrame extends JInternalFrame {
    private JComboBox<Category> comBoxCategory;
    private DefaultTableModel dtm;
    public CategoryFrame() {
        setTitle("CategoryManger");
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setBounds(100, 50, 400, 350);
        JPanel panel = new JPanel(new GridLayout(2, 3, 2, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel labelName = new JLabel("Name");
        labelName.setForeground(Color.blue);
        JLabel labelCategory = new JLabel("Category");
        labelCategory.setForeground(Color.blue);
        JTextField textFieldName = new JTextField("");
        comBoxCategory = new JComboBox<>(Category.categoryArrayList().toArray(new Category[0]));
        panel.add(labelName);
        panel.add(labelCategory);
        panel.add(new JLabel());
        panel.add(textFieldName);
        panel.add(comBoxCategory);
        JButton buttonSave = new JButton("Save");
        buttonSave.setForeground(Color.blue);
        panel.add(buttonSave);
        add(panel, BorderLayout.NORTH);
        String[] colName = {"id", "name", "category"};
        dtm = new DefaultTableModel(null, colName);
        fillCatTable();
        JTable table = new JTable(dtm);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        buttonSave.addActionListener(e -> {
            try {
                DB db = new DB();
                Category selectedCat = (Category)  comBoxCategory.getSelectedItem();
                String qry = "";
                if (selectedCat == null) {
                    qry = "insert into categories(name ) values('" + textFieldName.getText() + "')";
                } else {
                    qry = "insert into categories(name ,category_id) values('" + textFieldName.getText() + "','" + selectedCat.getId() + "')";
                }

                db.getStatement().executeUpdate(qry);
                db.close();
                fillCatTable();
            } catch (Exception exception) {
                System.out.println("exception error" + exception.getMessage());
            }

        });
        JPanel panelSouth=new JPanel();
        JButton buttonDelete = new JButton("Delete ");
        buttonDelete.setBackground(Color.green);
        panelSouth.add(buttonDelete);
        getContentPane().add(panelSouth, BorderLayout.SOUTH);

        buttonDelete.addActionListener(e -> {
            int row=table.getSelectedRow();
            String  categoryId=(String)dtm.getValueAt(row,0);
            try {
                DB db=new DB();
                db.getStatement().executeUpdate("DELETE FROM categories WHERE id = " +  categoryId);
                db.close();
                dtm.removeRow(row);
            }catch (Exception exception){
                System.out.println("exception error"+exception.getMessage());
            }

        });
    }
        private void fillCatTable(){
            dtm.setRowCount(0);
            DB db = null;
            try {
                db = new DB();
                ResultSet resultSet = db.getStatement().executeQuery("SELECT c.id , c.name ,c.category_id ,m.name main_cat from categories c left join categories m on (c.category_id =m.id)");
                while (resultSet.next()){
                    String [] row = {
                            resultSet.getString("id") ,
                            resultSet.getString("name") ,
                            resultSet.getString("main_cat")
                    };
                    dtm.addRow(row);
                }
                db.close();

        }   catch(Exception exception) {
            System.out.println("exception error"+exception.getMessage());
    }

    }
}
