package jvm_security;

import java.io.FileWriter;
import java.io.IOException;

public class ActivateSecurityManager {

    public static void main(String[] args) throws IOException {

//        System.setSecurityManager(new SecurityManager());

        String version = System.getProperty("java.version");
        System.out.println(version);

        String home = System.getProperty("user.home");
        System.out.println(home);

//        try (FileWriter writer = new FileWriter("c:/temp/out.txt")){
//            writer.write("Hello, World");
//        }
    }
}
