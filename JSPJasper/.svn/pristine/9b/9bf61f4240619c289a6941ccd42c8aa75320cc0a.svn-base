
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nitin
 */
public class MyConnFactory {

    static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "root");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyConnFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MyConnFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
