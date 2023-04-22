package Pages;

import java.security.SecureRandom;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class PasswordEncryption {
    private static final int BCRYPT_ROUNDS = 12;

    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt(BCRYPT_ROUNDS, new SecureRandom());
        return BCrypt.hashpw(password, salt);
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    /* public static void savePasswordToDatabase(String hashedPassword) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://dt5.ehb.be/2223PROGPROJGR3", "2223PROGPROJGR3", "QSUOMC");

            stmt = conn.prepareStatement("INSERT INTO PASSWORDS (hash, salt) VALUES (?, ?)");
            stmt.setString(1, hashedPassword);
            stmt.setString(2, hashedPassword.substring(0, 29));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/

}
