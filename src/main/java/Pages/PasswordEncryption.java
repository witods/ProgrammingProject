package Pages;

import org.mindrot.jbcrypt.BCrypt;

import java.security.SecureRandom;

public class PasswordEncryption {
    private static final int BCRYPT_ROUNDS = 12;

    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt(BCRYPT_ROUNDS, new SecureRandom());
        return BCrypt.hashpw(password, salt);
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
