import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Market extends JFrame {
    private JDesktopPane desktopPane;
    private User loginUser;
    public Market(User loginUser,LoginFrame loginFrame ) {
        this.loginUser = loginUser;
        setTitle("Welcome " + loginUser.getName());
        desktopPane = new JDesktopPane();
        getContentPane().add(desktopPane);
        setBounds(100, 100, 800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.YELLOW);
        setJMenuBar(menuBar);
        JMenu menueMange = new JMenu("Manage");
        JMenu menueOrders = new JMenu("Customers and Orders");
        menuBar.add(menueMange);
        menuBar.add(menueOrders);
        menuBar.setBackground(Color.yellow);
        JMenuItem menuItemUsers = new JMenuItem("Users");
        JMenuItem menuItemCategory = new JMenuItem(" add Category");
        JMenuItem menuItemAllCat = new JMenuItem(" AllCat");
       // JMenuItem menuItemAllOrder = new JMenuItem(" AllOrder");
        JMenuItem menuItemShowCategories = new JMenuItem(" show Categories");
        JMenuItem menuItemShowProduct = new JMenuItem("ShowProduct");
        JMenuItem menuItemCustomer = new JMenuItem("Customer");
        JMenuItem menuItemAddCustomer = new JMenuItem("AddCustomer");
        JMenuItem menuItemShowCustomers = new JMenuItem("Show Customers");
        JMenuItem menuItemOrders = new JMenuItem("Orders");
        JMenuItem menuItemLoginOut = new JMenuItem("LoginOut");
        menueMange.addSeparator();
        menueMange.add(menuItemUsers);
        menueMange.setForeground(Color.blue);
        menueMange.addSeparator();
        menueMange.add(menuItemCategory);
        menueMange.addSeparator();
        menueMange.add(menuItemAllCat);
        menueMange.addSeparator();
        menueMange.add(menuItemShowCategories);
        menueMange.addSeparator();
        menueMange.add(menuItemShowProduct);
        menueMange.addSeparator();
        menueMange.add(menuItemLoginOut);
        menueOrders.add(menuItemCustomer);
        menueOrders.setForeground(Color.blue);
        menueOrders.addSeparator();
        menueOrders.add(menuItemAddCustomer);
        menueOrders.addSeparator();
        menueOrders.add(menuItemShowCustomers);
        menueOrders.addSeparator();
        menueOrders.add(menuItemOrders);
        menueOrders.addSeparator();
       // menueOrders.add(menuItemAllOrder);


        //
        //
        menuItemCategory.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(null, "Enter your gategores");
            if (name != null && !name.trim().equals("")) {
                try {
                    DB db = new DB();
                    db.getStatement().executeUpdate("insert into categories(name) values('" + name + "')");
                    db.close();

                } catch (Exception exception) {
                    System.out.println("Exception" + exception.getMessage());

                }
            }
        });
        menuItemShowCategories.addActionListener(e -> {
            String allCategories = "";
            try {
                DB db = new DB();
                ResultSet resultSet = db.getStatement().executeQuery("select id,name from categories");
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    allCategories += (id + " " + name + "\n");
                }
                db.close();
            } catch (Exception exception) {
                System.out.println("Exception" + exception.getMessage());

            }
            JOptionPane.showMessageDialog(null, allCategories);
        });
        menuItemUsers.addActionListener(e -> {
            UserFram userFram = new UserFram(loginUser);
            userFram.setVisible(true);
            desktopPane.add(userFram);
        });

        menuItemCustomer.addActionListener(e -> {
            CustomerFrame customerFrame = new CustomerFrame();
            customerFrame.setVisible(true);
            desktopPane.add(customerFrame);
        });
        menuItemAddCustomer.addActionListener(e -> {

            String name = JOptionPane.showInputDialog(null, "Enter your Customers");
            if (name != null && !name.trim().equals("")) {
                try {
                    DB db = new DB();
                    db.getStatement().executeUpdate("insert into Customers(name) values('" + name + "')");
                    db.close();

                } catch (Exception exception) {
                    System.out.println("Exception" + exception.getMessage());
                }
            }
        });

        menuItemShowCustomers.addActionListener(e -> {

            String allCustomers = "";
            try {
                DB db = new DB();
                ResultSet resultSet = db.getStatement().executeQuery("select id,name from customers");
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    allCustomers += (id + " " + name + "\n");
                }
                db.close();
            } catch (Exception exception) {
                System.out.println("Exception" + exception.getMessage());

            }
            //   JOptionPane.showMessageDialog(null, allCustomers);
        });
        menuItemShowProduct.addActionListener(e -> {
            Product product = new Product(loginUser);
            product.setVisible(true);
            desktopPane.add(product);

        });
        menuItemShowProduct.addActionListener(e -> {

            String allProduct = "";
            try {
                DB db = new DB();
                ResultSet resultSet = db.getStatement().executeQuery("select id,name from product");
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    allProduct += (id + " " + name + "\n");
                }
                db.close();
            } catch (Exception exception) {
                System.out.println("Exception" + exception.getMessage());

            }

        });
        menuItemLoginOut.addActionListener(e -> {
            this.setVisible(false);
           loginFrame.setVisible(true);
        });

        menuItemAllCat.addActionListener(e -> {
           CategoryFrame c = new CategoryFrame();
           c.setVisible(true);
            desktopPane.add(c);
        });
        menuItemOrders.addActionListener(e -> {
            TestDes testDes=new TestDes();
            testDes.setVisible(true);
            desktopPane.add(testDes);
        });


    }
    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }
}
