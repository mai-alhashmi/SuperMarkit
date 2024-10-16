import javax.swing.*;
import java.awt.*;

public class DemoLayout extends JFrame {
    public DemoLayout(String title){
        setBounds(300,150,500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        JButton b1=new JButton("One");
        JButton b2=new JButton("tow");
        JButton b3=new JButton("three");
        JButton b4=new JButton("Four");
        JButton b5=new JButton("Five");
        gbc.gridx=0;
        gbc.gridy=0;
        add(b1,gbc);
       gbc.gridx=0;
       gbc.gridy=1;
       add(b2,gbc);
       gbc.gridx=1;
       gbc.gridy=0;
       add(b3,gbc);
       gbc.insets=new Insets(20,20,20,20);
        gbc.gridx=1;
        gbc.gridy=1;
        add(b4,gbc);
        gbc.insets=new Insets(20,20,20,20);
        gbc.gridx=0;
        gbc.gridy=2;
        add(b5,gbc);

    }

    public static void main(String[] args) {
        new DemoLayout("").setVisible(true);
    }
}
