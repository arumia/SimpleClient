package hr;
/**
 *
 * @author mkowalczyk
 */
import java.sql.*;
import javafx.scene.control.Alert;

public class DBConnection {

private static Connection conn;

public static Connection getConnection(){
String DB_URL = "jdbc:oracle:thin:@arumia.pw:9705:ZOO"; //you should have port 1521
String DB_USER = "zoo";
        String DB_PASS = "zoo";
 try {

            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You are connected to database...");
            alert.show();
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error to database connection");
            alert.setContentText("Details: "+ exc.getMessage());
            alert.show();
        }

return conn;
}
}
