package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

/**
 *
 * @author Michael Farmer
 */
public class Logger {
    private static final String FILENAME = "log.txt";

    public static String log(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Logger() {}
    
    public static void log (String username, boolean success) {
        try (FileWriter fw = new FileWriter(FILENAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
                pw.println(ZonedDateTime.now() + " " + username + (success ? " Success" : " Failure"));
            } catch (IOException e) {
               System.out.println("Logger Error: " + e.getMessage());
        }
    }
}
