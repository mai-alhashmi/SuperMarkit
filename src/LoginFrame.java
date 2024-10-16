import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    public LoginFrame() {
       setTitle("Login");
        setBounds(200,150,400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        JPanel panel=new JPanel();
        panel.setBackground(Color.GREEN);

        GridBagConstraints c=new GridBagConstraints();
        JLabel labelEmail=new JLabel("Email");
        labelEmail.setForeground(Color.blue);
        JLabel labelPasw=new JLabel("Password");
        labelPasw.setForeground(Color.blue);
     JLabel labelMessage = new JLabel(); labelMessage.setForeground(Color.RED);
        JTextField textFieldEmail=new JTextField("a@gmail.com");
        textFieldEmail.setPreferredSize(new Dimension(150,30));
        JPasswordField textFildPasw=new JPasswordField("123");
        textFildPasw.setPreferredSize(new Dimension(150,30));
        JButton buttonLogin=new JButton("login");
        buttonLogin.setBackground(Color.GREEN);

        c.insets=new Insets(10,10,10,10);
        c.gridx=0;
        c.gridy=0;
        add(labelEmail,c);
        c.gridx=1;
        c.gridy=0;
        add(textFieldEmail,c);
        c.gridx=0;
        c.gridy=1;
        add(labelPasw,c);
        c.gridx=1;
        c.gridy=1;
        add(textFildPasw,c);
        c.gridx=1;
        c.gridy=2;
        c.fill=GridBagConstraints.HORIZONTAL;
        add(buttonLogin,c);
     c.gridx=1;
     c.gridy=3;
     c.fill=GridBagConstraints.HORIZONTAL;
     add(labelMessage,c);
        buttonLogin.addActionListener(e -> {

         String email = textFieldEmail.getText().trim();
         String pasw= textFildPasw.getText();
         try {
        DB db=new DB();
          ResultSet resultSet = db.getStatement().executeQuery("select * from users where email='"+email+"' and password='"+pasw+"'");
          if (resultSet.next()) {

           User loginUser = new User(email,pasw);
           loginUser.setId(resultSet.getInt("id"));
           loginUser.setName(resultSet.getString("name"));
           loginUser.setMobile(resultSet.getString("mobile"));
           loginUser.setRole(resultSet.getString("role"));
           loginUser.setCreatedAt(resultSet.getDate("created_at"));
           loginUser.setCreatedBy(resultSet.getInt("created_by"));


           new Market(loginUser,this).setVisible(true);
              this.setVisible(false);
          }else {
           labelMessage.setText("Invalid email or password.");
          }
          db.close();
         } catch (Exception ex) {
          ex.printStackTrace();
         }

        });
    }
    public static void main(String[] args) {
       LoginFrame l = new LoginFrame();

       l.setVisible(true);
    }
}
