package org.tigris.scarab.om;

/* ================================================================
 * Copyright (c) 2000-2002 CollabNet.  All rights reserved.
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
 * software developed by Collab.Net <http://www.Collab.Net/>."
 * Alternately, this acknowlegement may appear in the software itself, if
 * and wherever such third-party acknowlegements normally appear.
 * 
 * 4. The hosted project names must not be used to endorse or promote
 * products derived from this software without prior written
 * permission. For written permission, please contact info@collab.net.
 * 
 * 5. Products derived from this software may not use the "Tigris" or 
 * "Scarab" names nor may "Tigris" or "Scarab" appear in their names without 
 * prior written permission of Collab.Net.
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
 * individuals on behalf of Collab.Net.
 */ 

import org.apache.torque.om.Persistent;
import org.apache.torque.util.Criteria;
import org.tigris.scarab.services.security.ScarabSecurity;
import org.apache.fulcrum.security.util.TurbineSecurityException;
import org.tigris.scarab.util.ScarabConstants;
import java.util.List;
import java.util.Iterator;

/** 
 * This class is for dealing with Attributes associated to Users and Modules.
 *
 * @author <a href="mailto:jmcnally@collab.net">John D. McNally</a>
 * @author <a href="mailto:jon@collab.net">Jon Scott Stevens</a>
 * @version $Id: RModuleUserAttribute.java 9104 2004-05-10 21:04:51Z dabbous $
 */
public  class RModuleUserAttribute 
    extends org.tigris.scarab.om.BaseRModuleUserAttribute
    implements Persistent
{
    /**
     * Delete the record. Must have USER__EDIT_PREFERENCES to be
     * able to execute this method.
     */
    public void delete(ScarabUser user) throws Exception 
    { 
        boolean hasPermission = true;
        if (getModule() != null)
        {
            hasPermission = user.hasPermission(ScarabSecurity.USER__EDIT_PREFERENCES,getModule());
        }
        else //getModule() is null for X Project queries, so get the modules from the MIT List
        {
            List moduleList = user.getCurrentMITList().getModules();
            for (Iterator iter = moduleList.iterator(); iter.hasNext(); )
            {
                hasPermission = user.hasPermission(ScarabSecurity.USER__EDIT_PREFERENCES,(Module)iter.next());
                if (!hasPermission)
                {
                    break;
                }
            }
        }
        if (hasPermission)
        {
            Criteria c = new Criteria()
                .add(RModuleUserAttributePeer.MODULE_ID, getModuleId())
                .add(RModuleUserAttributePeer.USER_ID, getUserId())
                .add(RModuleUserAttributePeer.ISSUE_TYPE_ID, getIssueTypeId())
                .add(RModuleUserAttributePeer.LIST_ID, getListId())
                .add(RModuleUserAttributePeer.ATTRIBUTE_ID, getAttributeId());
            RModuleUserAttributePeer.doDelete(c);
        }
        else
        {
            throw new TurbineSecurityException(ScarabConstants.NO_PERMISSION_MESSAGE); //EXCEPTION
        }
    }
}
