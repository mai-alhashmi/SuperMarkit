import javax.swing.*;

public class MiUi extends JFrame {

    private JPanel panel1;
    private JTextArea textArea1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JComboBox comboBox1;
    private JList list1;
    private JTable table1;
    private JScrollBar scrollBar1;
    private JProgressBar progressBar1;
    private JSpinner spinner1;
    private JTree tree1;
    private JList list2;

    public MiUi(String title) {
        setBounds(150,100,800,500);
        getContentPane().add(panel1);
    }

    public static void main(String[] args) {
        MiUi ui1=new MiUi("");
        ui1.setVisible(true);
    }


}
