package org.codepin.ldaphack;

import org.codepin.ldaphack.ldapauth;
import java.util.Properties;

public class ldaptest {

    /**
     * Used to print the usage for running this program from the command-line.
     * This method will normally be called when the user doesn't pass in the
     * correct number of arguments.
     */
    public static void printUsage() {
        String str = null;
        str = "--------------------------------------------------\n";
        str = "Ensure properties file ldapauth.properties is in";
        str = "your classpath if not supplied on command line.";
        str = "--------------------------------------------------\n";
        str = "java ldaptest <username> <properties file>";
        System.out.println(str);
    }

    public static void runtest (String[] args) {
        if (args.length == 1) {
            runtest(args[0]);
        } else if (args.length == 2) {
            runtest(args[0],args[1]);
        } else {
            runtest(args[0],args[1],args[2]);
        }
    }
    public static void runtest (String username) {
        runtest(username,"",null);
    }
    public static void runtest (String username, String password) {
        runtest(username,password,null);
    }
    public static void runtest (String username, String password, String propertyfile) {
        ldapauth la;
        if (propertyfile == null) {
            la = new ldapauth();
        } else {
            la = new ldapauth(propertyfile);
        }

        System.out.println("-----\n");
        Properties myprop = la.getProperties();
        if (myprop == null) {
            System.out.println("property file is null");
            la.setSearchFilter("(uid={0})");
        } else {
            myprop.list(System.out);
        }
        System.out.println("-----\n");

        String dn = la.searchfordn(username);
        System.out.println("User found DN for " + username + ": [" + dn + "]");

        boolean bo = la.bindauthdn(dn,password);
        if (bo) {
            System.out.println("DN ["+dn+"] is AUTHORIZED");
        } else {
            System.out.println("DN ["+dn+"] is NOT AUTHORIZED");
        }

    }

    /**
     * Sole entry point to the class and application.
     * @param args Array of String arguments.
     */
    public static void main(String[] args) {

        if (args.length < 1) {
            printUsage();
            System.exit(1);
        }

        runtest(args);
    }

}
