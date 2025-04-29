package ve312.com.materiasCrud.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptarContras {
    public static void main(String[] args) {
        String password = "123";
        System.out.println("password =" + password);
        System.out.println("Pasword encriptado:" + encriptar(password));
    }

    public static String encriptar(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
