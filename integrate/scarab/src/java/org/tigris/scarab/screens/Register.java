package org.tigris.scarab.screens;

/* ================================================================
 * Copyright (c) 2003 CollabNet.  All rights reserved.
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
 * software developed by CollabNet <http://www.collab.net/>."
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
 * IN NO EVENT SHALL COLLABNET OR ITS CONTRIBUTORS BE LIABLE FOR ANY
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
//Turbine Stuff 
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;
import org.apache.turbine.tool.IntakeTool;

//Scarab Stuff
import org.tigris.scarab.om.ScarabUser;
import org.tigris.scarab.util.ScarabConstants;

/**
 * This scren class works around a bug in Scarab with multiple
 * intake objects.  See SCB1245.
 * 
 * @author <a href="mailto:anthony@ypwong.org">Anthony Wong </a>
 * @version $Id: Register.java 9232 2004-11-04 16:05:47Z dep4b $
 */
public class Register extends Default {
    /**
     * builds up the context for display of variables on the page.
     */
    public void doBuildTemplate(RunData data, TemplateContext context) throws Exception {
        super.doBuildTemplate(data, context);

        Object su = data.getUser().getTemp(ScarabConstants.SESSION_REGISTER);
        if (su != null && su instanceof ScarabUser) {
            IntakeTool intake = (IntakeTool) context.get(ScarabConstants.INTAKE_TOOL);
            // If there is a ScarabUser object in session, assign
            // values of its fields to the default Register intake
            // group, so that the template can re-show the input
            // values.
            ((ScarabUser) su).setPassword("");
            intake.get("Register", IntakeTool.DEFAULT_KEY, true).getProperties((ScarabUser) su);
            // Now we can safely remove the user that is stored in the
            // session
            data.getUser().setTemp(ScarabConstants.SESSION_REGISTER, null);
        }
    }
}