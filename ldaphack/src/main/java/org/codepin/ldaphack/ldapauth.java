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
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
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
 
import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.*;

import java.io.*;
import java.util.*;

/**
 * <p>Simple implementation of an authorization mechanism for ldap.
 * The intention of this class is to be used in hacking LDAP authentication
 * into a third-party software application.</p>
 * Example code to illustrate:
 * <code><pre>
 *   // code before integrating hack:
 *    if (isAuthenticatedUser(username,password) {
 *        userObject = getUserObject(username);
 *        System.out.println("You are authorized");
 *    } else {
 *        System.out.println("You are not authorized!");
 *        System.exit(1);
 *    }
 *
 *   // code after integrating hack (assumes you created properties file, see {@link ldapauth#ldapauth(String)}):
 *    ldapauth la = new ldapauth(); 
 *    String userDN = la.searchfordn(username);
 *    if (userDN != null) {
 *        // authenticate via LDAP if found in LDAP
 *        if (la.bindauthdn(userDN,password) {
 *            userObject = getUserObject(username);
 *            System.out.println("You are authorized via LDAP");
 *        } else {
 *            System.out.println("You are not authorized via LDAP");
 *            System.exit(1);
 *        }
 *    } else if (isAuthenticatedUser(username,password) {
 *        // else authenticate via the application if not found in LDAP
 *        userObject = getUserObject(username);
 *        System.out.println("You are authorized");
 *    } else {
 *        System.out.println("You are not authorized!");
 *        System.exit(1);
 *    }
 * </pre></code>
 * @author Russell E Glaue
 */
public class ldapauth {

    // The username to authenticate
    String     username = "";

    // The password to authenticate the user with
    String     password = "";

    // The binding DN string:  uid=user,dc=ex,dc=com
    // We bind to ldap with this DN
    String       binddn = "";

    // The password for the binding DN
    String bindpassword = "";

    // The search base, the base of our searches
    String       basedn = "ou=People,dc=example,dc=org";

    // The search filter
    String searchfilter = "";

    // The attribute to find the username in for an LDAP entry
    String     authattr = "uid";

    // Should we allow an empty password for authentication
    // Some LDAP servers will bind the user as anonymous if
    // the password string is empty.
    // Setting this to false causes empty password strings
    // to evaluate the user as not authenticated
    boolean allowemptypassword = false;

    // The LDAP provider URL
    String  providerurl = "ldap://ldap.example.org:389";

    // The properties file
    String propertyfile = "ldaphack.ldapauth.properties";

    // The loaded properties
    Properties properties;

    // Are we in Debug mode?
    private boolean DEBUG;

    /**
     * Are we in DEBUG mode? true = yes, false = no
     * This function is private to force this value to only be set via the
     * property file. This is done as a security measure since turning on
     * DEBUG mode causes the user password to be printed out to System.err.
     * @param debug
     */
    private void setDEBUG (boolean debug) {
        this.DEBUG = debug;
    }

    /**
     * Communicate an error message to the appropriate output.
     * @param message error message string
     * @param type    info, debug
     */
    private void notifyError (String message, String type) {
        String errorType = "INFO"; // by default
        if (type.equalsIgnoreCase("DEBUG")) {
            // If message is a DEBUG message
            if (this.DEBUG) {
                // and we are in DEBUG mode
                // set type and continue to output
                errorType = "DEBUG";
            } else {
                // or we are not in DEBUG mode
                // we do not print out debug messages
                return;
            }
        }
        /* We're assuming System.err is pointing to the desireable location
         * for error messages */
        System.err.println("[ldaphack:"+errorType+"] "+message);
    }

    /**
     * load properties from the properties file set in the object
     * the property file is loaded as XML
     * default properties file is {@link propertyfile}
     */
    private Properties loadProperties () {
        return loadProperties(this.propertyfile);
    }
    /**
     * load properties from a given properties file
     * the property file is loaded as XML
     * @param propertiesFile
     */
    private Properties loadProperties (String propertiesFile) {
        ClassLoader cl = this.getClass().getClassLoader();
        Properties props = new Properties();
        try {
                props.loadFromXML(cl.getResourceAsStream(propertiesFile)); // InputStream
                return props;
            } catch (IOException e) {
                notifyError(("Could not load propertyfile " + propertiesFile + " : " + e.toString()),"INFO");
                return null;
            }
    }

    /**
     * write properties to the properties file set in the object
     * the property file is written as XML
     * default properties file is {@link propertyfile}
     */
    private boolean writeProperties () {
        return writeProperties(this.properties, this.propertyfile);
    }
    /**
     * write properties to a given properties file
     * the property file is written as XML
     * @param propertiesFile
     */
    private boolean writeProperties (Properties props, String propertiesFile) {
        try {
                // props.store(new FileOutputStream(propertiesFile), null);
                props.storeToXML(new FileOutputStream(propertiesFile), null);
                return true;
            } catch (IOException e) {
                notifyError(("Could not write propertyfile " + propertiesFile + " : " + e.toString()),"INFO");
                return false;
            }
    }

    /**
     * Set the Property File for this object.
     * <p>property key: PropertyFile</p>
     * @param propertiesFile
     */
    public void setPropertyFile (String propertiesFile) {
        this.propertyfile = propertiesFile;
    }

    /**
     * Get the Property File set for this object
     * <p>property key: PropertyFile</p>
     */
    public String getPropertyFile() {
        return this.propertyfile;
    }

    /**
     * Set the Properties object of this object
     * @param properties
     */
    public void setProperties (Properties properties) {
        this.properties = properties;
    }

    /**
     * Get the Properties object of this object
     */
    public Properties getProperties() {
        return this.properties;
    }

    /**
     * Reload the Properties into this object from the properties file.
     */
    public void reloadProperties() {
        this.properties = loadProperties(this.propertyfile);
        setBindDN(this.properties.getProperty("BindDN"));
        setBindPassword(this.properties.getProperty("BindPassword"));
        setProviderUrl(this.properties.getProperty("ProviderUrl"));
        setSearchFilter(this.properties.getProperty("SearchFilter"));
        setBaseDN(this.properties.getProperty("BaseDN"));
        setAuthAttribute(this.properties.getProperty("AuthAttr","uid"));
        setAllowEmptyPassword( new Boolean(this.properties.getProperty("AllowEmptyPassword","false")).booleanValue() );
        setDEBUG( new Boolean(this.properties.getProperty("DEBUG","false")).booleanValue() );
    }

    /**
     * <p>Create an initialized instance of ldapauth with given property file.
     * Upon creation the provided property file is read in and the obect is
     * set up with the values.<br />
     * The Property File by default is attempted to be found in the classpath
     * as "ldaphack.ldapauth.properties" and is expected to be in XML format.
     * </p>
     *
     * Here is an example properties file:
     * <code><pre>
     * &lt;!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd"&gt;
     * &lt;properties&gt;
     *   &lt;entry key="ProviderUrl"&gt;ldap://ldap.example.org:389&lt;/entry&gt;
     *   &lt;entry key="SearchFilter"&gt;(&amp;amp;(uid={0})(objectClass=inetOrgPerson))&lt;/entry&gt;
     *   &lt;entry key="BaseDN"&gt;dc=example,dc=org&lt;/entry&gt;
     *   &lt;entry key="AllowEmptyPassword"&gt;false&lt;/entry&gt;
     *   &lt;entry key="AuthAttribute"&gt;uid&lt;/entry&gt;
     *   &lt;entry key="BindDN"&gt;uid=ldapreadonlyuser,ou=People,o=cait.org&lt;/entry&gt;
     *   &lt;entry key="BindPassword"&gt;secret&lt;/entry&gt;
     *   &lt;entry key="DEBUG"&gt;false&lt;/entry&gt;
     * &lt;/properties&gt;
     * </pre></code>
     * <em>Note that</em> <code> &amp; </code> <em>must be escaped as</em>
     * <code> &amp;amp; </code> <em>in XML, or you'll get an error when loaded.</em>
     * <p> If you do not use a property file, an error will be send to
     * System.err, but you can still use the object and use the setter methods
     * to set your properties for the object.</p>
     * @param propertiesFile
     */
    public ldapauth (String propertiesFile) {
        this.propertyfile = propertiesFile;
        reloadProperties();
    }
    /**
     * <p>Create an initialized instance of ldapauth using object's default
     * property file.
     * Upon creation the property file is read in and the obect is
     * set up with the values.<br/>
     * The propertyFile by default is attempted to be found in the classpath
     * as "ldaphack.ldapauth.properties" and is expected to be in XML format.
     * <br/>
     * Refer to {@link ldapauth#ldapauth(String)} for an example properties
     * file.</p>
     */
    public ldapauth () {
        reloadProperties();
    }

    /**
     * Set the username to authorize
     * @param userName
     */
    public void setUsername (String userName) {
        this.username = userName;
    }
    /**
     * Get the username set to authorize
     */
    public String getUsername () {
        return this.username;
    }

    /**
     * Set the password to authorize the username with
     * @param userPassword
     */
    public void setPassword (String userPassword) {
        this.password = userPassword;
    }
    /**
     * Get the password set to authorize the username with
     */
    public String getPassword () {
        return this.password;
    }

    /**
     * Set the Manager DN to bind to LDAP for searching purposes. Anonymous
     * binds are performed for searching LDAP unless a BindDN is supplied.
     * This is necessary to be set if anonymous binds are not allowed to fully
     * search ldap with the search filter used by {@link ldapauth#searchfordn(String,String)}
     * and {@link ldapauth#bindauthusername(String,String)} functions.
     * <p>property key: BindDN</p>
     * @param ManagerBindDN
     */
    public void setBindDN (String ManagerBindDN) {
        this.binddn = ManagerBindDN;
    }
    /**
     * Get the Manager DN set to bind to LDAP for searching purposes
     * <p>property key: BindDN</p>
     */
    public String getBindDN () {
        return this.binddn;
    }

    /**
     * Set the password of the Manager DN
     * <p>property key: BindPassword</p>
     * @param ManagerBindPassword
     */
    public void setBindPassword (String ManagerBindPassword) {
        this.bindpassword = ManagerBindPassword;
    }
    /**
     * Get the password set for the Manager DN
     * <p>property key: BindPassword</p>
     */
    public String getBindPassword () {
        return this.bindpassword;
    }

    /**
     * Set the attribute used to uniquely identify the user in LDAP for
     * authentication purposes.
     * Usually this will be the attribute which makes up the user DN which can
     * be uid or cn or even mail.
     * Be sure this attribute is unique and LDAP does not allow duplicates.
     * default is uid
     * <p>property key: AuthAttribute</p>
     * @param ldapAuthAttribute
     */
    public void setAuthAttribute (String ldapAuthAttribute) {
        this.authattr = ldapAuthAttribute;
    }
    /**
     * Get the attribute set to uniquely identify the user in LDAP for
     * authentication purposes.
     * <p>property key: AuthAttribute</p>
     */
    public String getAuthAttribute () {
        return this.authattr;
    }

    /**
     * Set true to allow empty password to be used with username
     * authentication, or false to disallow empty passwords to be used
     * in attempting authentication.<br/>
     * If an empty password is provided, some LDAP servers will successfully
     * bind the user as anonymous. If your LDAP server does this, you will
     * want to set this to false so that supplying an emtpy password will
     * return an authentication failure. You will want to set this even if
     * you just don't want allow empty password strings.
     * default is false to not allow empty password strings
     * <p>property key: AllowEmptyPassword</p>
     * @param allowEmptyPassword
     */
    public void setAllowEmptyPassword (boolean allowEmptyPassword) {
        this.allowemptypassword = allowEmptyPassword;
    }
    /**
     * Get the value of allowing empty passwords during authentication.
     * <p>property key: AllowEmptyPassword</p>
     */
    public boolean getAllowEmptyPassword () {
        return this.allowemptypassword;
    }

    /**
     * Set the LDAP URL to be used for connecting to the LDAP server.
     * example: ldap://ldap.example.org/
     * <p>property key: ProviderUrl</p>
     * @param providerUrl
     */
    public void setProviderUrl (String providerUrl) {
        this.providerurl = providerUrl;
    }
    /**
     * Get the LDAP URL to be used for connecting to the LDAP server.
     * <p>property key: ProviderUrl</p>
     */
    public String getProviderUrl () {
        return this.providerurl;
    }

    /**
     * Set the Base DN you want to confine your connection or search to within
     * the LDAP server. example: dc=example,dc=org
     * <p>property key: BaseDN</p>
     * @param BaseDN
     */
    public void setBaseDN (String BaseDN) {
        this.basedn = BaseDN;
    }
    /**
     * Get the Base DN set to confine the connection or search within the
     * LDAP server.
     * <p>property key: BaseDN</p>
     */
    public String getBaseDN () {
        return this.basedn;
    }

    /**
     * Set the search filter you want to have used when searching for
     * authorized accounts within LDAP. Use the string {0} in your filter
     * string to indicate where the username should be substituted in.
     * example: (&(uid={0})(objectClass=Person))
     * <p>property key: SearchFilter</p>
     * @param searchFilter
     */
    public void setSearchFilter (String searchFilter) {
        this.searchfilter = searchFilter;
    }
    /**
     * Get the search filter set to be use when searching for authorized
     * accounts within LDAP.
     * <p>property key: SearchFilter</p>
     */
    public String getSearchFilter () {
        return this.searchfilter;
    }

    /**
     * Get the search filter resulting from substituting the username into it.
     * The string "{0}" is replaced with the username in the search filter.
     * The searchFilter is taken from what is set in this object.
     * The username is taken from what is set in this object.
     */
    public String getCompletedSearchFilter () {
        return getCompletedSearchFilter(this.searchfilter,this.username);
    }
    /**
     * Get the search filter resulting from substituting the username into it.
     * The string "{0}" is replaced with the username in the search filter.
     * The searchFilter is taken from what is set in this object.
     * The username is taken from what is provided.
     * @param username
     */
    public String getCompletedSearchFilter (String username) {
        return getCompletedSearchFilter(this.searchfilter,username);
    }
    /**
     * Get the search filter resulting from substituting the username into it.
     * The string "{0}" is replaced with the username in the search filter.
     * The searchFilter is taken from what is provided.
     * The username is taken from what is provided.
     * @param searchFilter
     * @param username
     */
    public String getCompletedSearchFilter (String searchFilter, String username) {
        String completedSearchFilter = stringSubstitute(searchFilter,"{0}",username);
        return completedSearchFilter;
    }

    /**
     * Attempt to authenticate a user from the username and password set in the
     * object. (See {@link ldapauth#bindauthusername(String,String)})
     */
    public boolean bindauthusername () {
        String         dn = searchfordn(this.username);
        return bindauthdn(dn,this.password);
    }
    /**
     * Attempt to authenticate a user from a provided username and the password
     * set in the object which by default the password is an empty string.
     * (See {@link ldapauth#bindauthusername(String,String)})
     * @param username
     */
    public boolean bindauthusername (String username) {
        String         dn = searchfordn(username);
        return bindauthdn(dn,this.password);
    }
    /**
     * Attempt to authenticate a user from a provided username and password.
     * The username is passed to {@link ldapauth#searchfordn(String)}
     * to retrieve a DN for the user, then the result of the userDN and given
     * password being passed into {@link ldapauth#bindauthdn(String,String)}
     * is returned.
     * @param username
     * @param password
     */
    public boolean bindauthusername (String username, String password) {
        String         dn = searchfordn(username);
        return bindauthdn(dn,password);
    }
    /**
     * Attempt to authenticate a user from a provided user DN and the password
     * set in the object which by default the password is an empty string.
     * @param userDN
     */
    public boolean bindauthdn (String userDN) {
        return bindauthdn(userDN,this.password);
    }
    /**
     * Attempt to authenticate a user from a provided user DN and password
     * @param userDN
     * @param password
     */
    public boolean bindauthdn (String userDN, String password) {

        if (this.properties == null) {
	        notifyError("Authentication failed, because ldapauth properties not loaded!","INFO");
            return false;
        }
        if (userDN == null) {
	        notifyError("Authentication failed, username userDN is empty string!","INFO");
            return false;
        }
        if (! this.allowemptypassword) {
            if ((password == null) || (password.equals(""))) {
                notifyError("Authentication failed, password is empty string!","INFO");
                return false;
            }
        }

        Hashtable authEnv = new Hashtable(11);

        authEnv.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        authEnv.put(Context.PROVIDER_URL, this.providerurl);

        authEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
        notifyError(("DN is: \""+userDN+"\""),"DEBUG");
        authEnv.put(Context.SECURITY_PRINCIPAL, userDN);
        notifyError(("PASSWORD is: \""+password+"\""),"DEBUG");
        authEnv.put(Context.SECURITY_CREDENTIALS, password);

	try {
	        DirContext authContext = new InitialDirContext(authEnv);
                authContext.close();

            } catch (CommunicationException ce) {
                notifyError( (
                    "DefaultIntitalDirContextFactory.communicationFailure" + "Unable to connect to LDAP server" +
                    ce.toString()),
                    "INFO");
                notifyError( exceptionToString(ce), "DEBUG" );
                return false;
            } catch (javax.naming.AuthenticationException ae) {
                notifyError( (
                    "DefaultIntitalDirContextFactory.badCredentials" +
                    "Bad credentials" +
                    ae.toString()),
                    "INFO");
                notifyError( exceptionToString(ae), "DEBUG" );
                return false;
            } catch (NamingException nx) {
                notifyError( (
                    "DefaultIntitalDirContextFactory.unexpectedException" +
                    "Failed to obtain InitialDirContext due to unexpected exception" +
                    nx.toString()),
                    "INFO");
                notifyError( exceptionToString(nx), "DEBUG" );
                return false;
            }

            notifyError((userDN+" Authentication Success!"),"INFO");
            return true;
    }

    /**
     * Search for the userDN using the searchFilter and username set in this
     * object.
     */
    public String searchfordn () {
        return searchfordn(this.searchfilter, this.username);
    }
    /**
     * Search for the userDN using the provided username and the searchFilter
     * set in this object.
     * @param username
     */
    public String searchfordn (String username) {
        return searchfordn(this.searchfilter, username);
    }
    /**
     * Search for the userDN using the provided searchFilter and username.
     * If an annonymous bind to the LDAP server cannot fully search LDAP with
     * the provided search filter, ensure you have provided an account which
     * can perform the search using
     * {@link ldapauth#setBindDN} and {@link ldapauth#setBindPassword} or
     * setting the equivelant property in the property file.
     * @param searchFilter
     * @param username
     */
    public String searchfordn (String searchFilter, String username) {

        if (this.properties == null) {
	        notifyError("ldap search failed, because ldapauth properties not loaded!","INFO");
            return null;
        }

        String cSearchFilter = getCompletedSearchFilter(searchFilter, username);
        Hashtable   ldapEnv = new Hashtable(11);

        ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        ldapEnv.put(Context.PROVIDER_URL, providerurl);

        if (binddn != null) {
            ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
            ldapEnv.put(Context.SECURITY_PRINCIPAL, this.binddn);
            ldapEnv.put(Context.SECURITY_CREDENTIALS, this.bindpassword);
        } else {
            ldapEnv.put(Context.SECURITY_AUTHENTICATION, "none");
        }

        // String[]    returnAttribute = {"dn","uid"};
        String[]    returnAttribute = {"*"};
        SearchControls srchControls = new SearchControls();
        String             dn = null;

        srchControls.setReturningAttributes(returnAttribute);
        srchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        try {
            // DirContext srchContext = new InitialDirContext(ldapEnv);
            DirContext srchContext = new InitialDirContext(ldapEnv);
            NamingEnumeration searchResults = srchContext.search(this.basedn, cSearchFilter, srchControls);
            // Probably want to test for nulls here
            if (searchResults.hasMore()) {
                SearchResult srchRes = (SearchResult)searchResults.nextElement();
                NamingEnumeration attrs = srchRes.getAttributes().getIDs();

                if (srchRes.getName().trim().length() == 0) {
                    dn = this.basedn;
                } else {
                    dn = srchRes.getName() + "," + this.basedn;
                }

                /*
                // DEBUG - return LDAP record found
                String attrDebugMessage = "Attributes for found record\n";
                attrDebugMessage = (attrDebugMessage+"    DN: "+dn+"\n    ");
                while (attrs.hasMore()) {
                    String attr = (String)attrs.nextElement();
                    attrDebugMessage = (attrDebugMessage+attr);
                    if (attrs.hasMore()) {
                        attrDebugMessage = (attrDebugMessage+",");
                    }
                }
                notifyError(attrDebugMessage,"DEBUG");
                */
            }
            srchContext.close();
        } catch (NamingException namEx) {
            notifyError( namEx.toString(), "INFO" );
            notifyError( exceptionToString(namEx), "DEBUG" );
        }

        return dn;
    }


    /**
     * Some String-Management functions
     * Reference: http://www.happycodings.com/ (August, 2006)
     * http://www.java.happycodings.com/Java_Util_Package/code27.html
     */


    /**
     * Strips a string of white space
     * @return String with trailing white spaces removed, or null
     * if they are no remaining characters
     * @param value String to be trimmed
     */
    private String stringTrim( String value ) {
        if( value != null ) {
            value = value.trim();
            if( value.length() == 0 ) {
                value = null;
            }
        }
        return value;
    }


    /**
     * Replacement utility - substitutes <b>all</b> occurrences of 'src' with 'dest' in the string 'name'
     * @param name the string that the substitution is going to take place on
     * @param src the string that is going to be replaced
     * @param dest the string that is going to be substituted in
     * @return String with the substituted strings
     */
    private String stringSubstitute( String name, String src, String dest ) {
        if( name == null || src == null || name.length() == 0 ) {
            return name;
        }

        if( dest == null ) {
            dest = "";
        }

        int index = name.indexOf( src );
        if( index == -1 ) {
            return name;
        }

        StringBuffer buf = new StringBuffer();
        int lastIndex = 0;
        while( index != -1 ) {
            buf.append( name.substring( lastIndex, index ) );
            buf.append( dest );
            lastIndex = index + src.length();
            index = name.indexOf( src, lastIndex );
        }
        buf.append( name.substring( lastIndex ) );
        return buf.toString();
    }

    /**
     * Converts an exception into a string
     * @param t the exception to be converted
     * @return String a string representation of the exception
     */
    private String exceptionToString( Throwable t ) {
        if( t != null ) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter( sw );
            pw.println( t.getMessage() );
            pw.println( "\n=====================\n" );
            t.printStackTrace( pw );
            pw.println( "\n=====================\n" );
            pw.close();
            return sw.toString();
        }
        else {
            return "";
        }
    }
}
