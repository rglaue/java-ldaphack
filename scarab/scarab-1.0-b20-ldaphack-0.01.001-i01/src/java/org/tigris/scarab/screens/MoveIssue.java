package org.tigris.scarab.screens;

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

// Turbine Stuff 
import org.apache.turbine.RunData;
import org.apache.turbine.TemplateContext;
import org.apache.turbine.tool.IntakeTool;

// Scarab Stuff
import org.tigris.scarab.tools.ScarabRequestTool;
import org.tigris.scarab.tools.ScarabLocalizationTool;
import org.tigris.scarab.util.Log;

/**
 * Handles dynamic title
 *
 * @author <a href="mailto:jmcnally@collab.net">John McNally</a>
 * @version $Id: MoveIssue.java 9711 2005-05-29 17:03:34Z jorgeuriarte $
 */
public class MoveIssue extends Default
{
    private static final String MOVE = "move";
    private static final String COPY = "copy";
    private static final Integer MOVE_CHOICE = new Integer(0);
    private static final Integer COPY_CHOICE = new Integer(1);
    private static final String KEY = "MoveIssueTitle1";

    private TemplateContext context;
    private RunData data;
    
    protected String getTitle(ScarabRequestTool scarabR,
            ScarabLocalizationTool l10n)
    	throws Exception
    {
    	return getTitle(scarabR, l10n, data, context);
    }

    protected String getTitle(ScarabRequestTool scarabR,
                              ScarabLocalizationTool l10n,
                              RunData data, TemplateContext context)
        throws Exception
    {
        String title = null;
        try
        {
            String action = ((IntakeTool) context.get("intake"))
                .get("MoveIssue").getDefault().get("Action").toString();
            String l10nKey = getKey();
            if (COPY.equals(action))
            {
                title = l10n.format(l10nKey, COPY_CHOICE);
            }
            else if (MOVE.equals(action))
            {
                title = l10n.format(l10nKey, MOVE_CHOICE);
            }
            else 
            {
                scarabR.setAlertMessage(l10n.get("NoActionSpecified"));
                setTarget(data, "ViewIssue.vm");
            }
        }
        catch (Exception e)
        {
            title = "Error; Copy or Move?";
            Log.get().warn("Error determining title", e);
        }
        return title;
    }

    protected String getKey()
    {
        return KEY;
    }
    
    protected void doBuildTemplate(RunData data, TemplateContext context)
    	throws Exception
    {
    	this.data = data;
    	this.context = context;
    	super.doBuildTemplate(data, context);
    }
}
