import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJdc {
    public static void main(String[] args) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL="jdbc:mysql://localhost:3306/hr";
           Connection connection= DriverManager.getConnection(dbURL,"root","Mai-91");
          Statement statement= connection.createStatement();
         // int reslt=statement.executeUpdate("insert into regions values(5,'iii')");
        //  int reslt=statement.executeUpdate("delete from regions where region_id in(5,6)") ;
        //  int reslt=statement.executeUpdate("update regions set region_name='Test update' where region_id=5") ;
         //   System.out.println(reslt);
            ResultSet resultSet=statement.executeQuery("select region_id,region_name from regions");
            while (resultSet.next()){
                String id= resultSet.getString("region_id");
                String name= resultSet.getString("region_name");
                System.out.println(id+"\t"+name);

            }
          statement.close();
          connection.close();
        }catch (Exception e){
            System.out.println("Exception"+e.getMessage());
        }
    }
}
