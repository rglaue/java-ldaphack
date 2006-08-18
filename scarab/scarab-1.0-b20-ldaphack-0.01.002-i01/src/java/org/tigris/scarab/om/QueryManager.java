package org.tigris.scarab.om;

/* ================================================================
 * Copyright (c) 2000-2003 CollabNet.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 * any, must include the following acknowlegement: "This product includes
 * software developed by CollabNet <http://www.Collab.Net/>."
 * Alternately, this acknowlegement may appear in the software itself, if
 * and wherever such third-party acknowlegements normally appear.
 *
 * 4. The hosted project names must not be used to endorse or promote
 * products derived from this software without prior written
 * permission. For written permission, please contact info@collab.net.
 *
 * 5. Products derived from this software may not use the "Tigris" or
 * "Scarab" names nor may "Tigris" or "Scarab" appear in their names without
 * prior written permission of CollabNet.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL COLLAB.NET OR ITS CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of CollabNet.
 */

import org.apache.torque.TorqueException;
import org.apache.torque.om.Persistent;

/** 
 * This class manages Query objects.
 *
 * @author <a href="mailto:jmcnally@collab.net">John McNally</a>
 * @version $Id: QueryManager.java 7365 2003-03-15 21:56:59Z jon $
 */
public class QueryManager
    extends BaseQueryManager
{
    /**
     * Creates a new <code>IssueManager</code> instance.
     *
     * @exception TorqueException if an error occurs
     */
    public QueryManager()
        throws TorqueException
    {
        super();
        setRegion(getClassName().replace('.', '_'));
    }

    protected Persistent putInstanceImpl(Persistent om)
        throws TorqueException
    {
        Persistent oldOm = super.putInstanceImpl(om);
        //List listeners = (List)listenersMap
        //    .get(AttributeTypePeer.ATTRIBUTE_TYPE_ID);
        //notifyListeners(listeners, oldOm, om);
        getMethodResult().removeAll(QueryPeer.QUERY_PEER, 
                                    QueryPeer.GET_QUERIES);
        getMethodResult().removeAll(QueryPeer.QUERY_PEER, 
                                    QueryPeer.GET_USER_QUERIES);
        getMethodResult().removeAll(QueryPeer.QUERY_PEER, 
                                    QueryPeer.GET_MODULE_QUERIES);
        return oldOm;
    }

    public Query getInstanceImpl()
    {
        return new Query();
    }
}