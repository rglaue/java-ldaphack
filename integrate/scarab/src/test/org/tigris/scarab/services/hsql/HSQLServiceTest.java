package org.tigris.scarab.services.hsql;
/* ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001-2003 The Apache Software Foundation.  All rights
 * reserved.
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
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Apache" and "Apache Software Foundation" and
 *    "Apache Turbine" must not be used to endorse or promote products
 *    derived from this software without prior written permission. For
 *    written permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    "Apache Turbine", nor may "Apache" appear in their name, without
 *    prior written permission of the Apache Software Foundation.
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
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.fulcrum.TurbineServices;
import org.apache.fulcrum.hsqldb.HSQLService;
import org.apache.fulcrum.hsqldb.HSQLServiceImpl;
import org.apache.turbine.services.yaaficomponent.YaafiComponentService;
import org.tigris.scarab.test.BaseTurbineTestCase;

/**
 * Verify that HSQLService starts..
 * 
 * @author     pti
 */
public class HSQLServiceTest extends BaseTurbineTestCase {

	private HSQLServiceImpl server = null;

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
        
		YaafiComponentService yaafi = (YaafiComponentService)TurbineServices.getInstance().getService(YaafiComponentService.SERVICE_NAME);

		server = (HSQLServiceImpl)yaafi.lookup(HSQLService.class.getName());
		
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		server = null;
		super.tearDown();
	}

	public void testGetService() {
		assertNotNull("Got no Service back", server);
	}

	public void testStarted() throws Exception {

		assertTrue("Server was not started", server.isRunning());
		
        // Load the HSQL Database Engine JDBC driver
        Class.forName("org.hsqldb.jdbcDriver");

        Connection conn = DriverManager.getConnection("jdbc:hsqldb:"
                                           + "/scarab",    // filenames
                                           "sa",                     // username
                                           "");
        assertFalse("Oops, hsqldb connection was not opened.", conn.isClosed());
        conn.close();
	}

	

}
