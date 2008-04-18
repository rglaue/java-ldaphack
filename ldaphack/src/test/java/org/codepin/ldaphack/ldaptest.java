package org.codepin.ldaphack;

/* ====================================================================
 * Copyright (c) 2006 Center for the Application of Information
 * Technologies, Western Illinois University.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *      "This product includes software developed by Center for the
 *       Application of Information Technologies, Western Illinois
 *       University (http://www.cait.org)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CAIT", "Center for the Application for Informtaion
 *    Technologies", "WIU", "Western Illinois University", "CodePin" and
 *    the names of CodePin hosted projects must not be used to endorse or
 *    promote products derived from this software without prior written
 *    permission. For written permission, please contact info@cait.org.
 *
 * 5. Products derived from this software may not use the names "CodePin",
 *    "CodePin LDAP Hack", nor may "CodePin" appear in their name, without
 *    prior written permission of Center for the Application of Information
 *    Technologies, Western Illinois University.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE CENTER FOR THE APPLICATION OF
 * INFORMATION TECHNOLOGIES, WESTERN ILLINOIS UNIVERSITY OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of Center for the Application for Information
 * Technologies, Western Illinois University.  For more information on
 * the Center for the Application for Information Technologies, please
 * see <http://www.cait.org/>. For more information on Western Illinois
 * University, please see <http://www.wiu.edu>.
 */

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
        if (dn != null) {
          System.out.println("User found DN for " + username + ": [" + dn + "]");
        } else {
          System.out.println("No DN found for " + username + ": [" + dn + "]");
          System.exit(1);
        }

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
