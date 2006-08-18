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

// JDK classes
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// Commons classes
import org.apache.commons.lang.StringUtils;

// Turbine classes
import org.apache.torque.TorqueException;
import org.apache.torque.om.Persistent;
import org.apache.torque.util.Criteria;

import java.sql.Connection;
import org.apache.fulcrum.security.TurbineSecurity;
import org.apache.fulcrum.security.util.RoleSet;
import org.apache.fulcrum.security.util.TurbineSecurityException;
import org.apache.fulcrum.security.entity.User;
import org.apache.fulcrum.security.entity.Group;
import org.apache.fulcrum.security.entity.Role;

// Scarab classes
import org.tigris.scarab.om.Module;
import org.tigris.scarab.om.MITList;
import org.tigris.scarab.om.ScarabUserManager;
import org.tigris.scarab.tools.localization.L10NKeySet;
import org.tigris.scarab.util.ScarabConstants;
import org.tigris.scarab.util.ScarabException;
import org.tigris.scarab.util.ScarabPaginatedList;
import org.tigris.scarab.util.ScarabLocalizedTorqueException;
import org.tigris.scarab.util.ScarabLocalizedTurbineSecurityException;
import org.tigris.scarab.services.cache.ScarabCache;

// FIXME! do not like referencing servlet inside of business objects
// though I have forgotten how I might avoid it
import org.apache.turbine.Turbine;
import org.apache.fulcrum.security.impl.db.entity
    .TurbinePermissionPeer;
import org.apache.fulcrum.security.impl.db.entity
    .TurbineUserGroupRolePeer;
import org.apache.fulcrum.security.impl.db.entity
    .TurbineRolePermissionPeer;
import org.apache.fulcrum.security.impl.db.entity.TurbineUserPeer;

/**
 * The ScarabModule class is the focal point for dealing with
 * Modules. It implements the concept of a Module which is a
 * single module and is the base interface for all Modules. In code,
 * one should <strong>never reference ScarabModule directly</strong>
 * -- use its Module interface instead.  This allows us to swap
 * out Module implementations by modifying the Scarab.properties
 * file.
 *
 * @author <a href="mailto:jon@collab.net">Jon S. Stevens</a>
 * @author <a href="mailto:jmcnally@collab.net">John McNally</a>
 * @version $Id: ScarabModule.java 9710 2005-05-28 08:45:40Z jorgeuriarte $
 */
public class ScarabModule
    extends BaseScarabModule
    implements Persistent, Module, Group
{
    private static final String GET_USERS = "getUsers";

    protected static final Integer ROOT_ID = new Integer(0);

    private String httpDomain = null;
    private String instanceId = null;
    private String port       = null;
    private String scheme     = null;
    private String scriptName = null;

    /**
     * Get the value of domain.
     * @return value of domain.
     */
    public String getHttpDomain()
    {
        if (httpDomain == null || httpDomain.length() == 0)
        {
            try
            {
                httpDomain = GlobalParameterManager
                    .getString(ScarabConstants.HTTP_DOMAIN);
            }
            catch (Exception e)
            {
                getLog().error("Error getting HTTP_DOMAIN:", e);
            }
        }
        return httpDomain;
    }
    
    /**
     * Set the value of domain.
     * @param v  Value to assign to domain.
     */
    public void setHttpDomain(String v)
    {
        if (v != null)
        {
            this.httpDomain = v;
        }
    }

    /**
     * Get the value of the Scarab instance id.
     * @return value of domain.
     */
    public String getScarabInstanceId()
    {
        if (instanceId == null || instanceId.length() == 0)
        {
            try
            {
                instanceId = GlobalParameterManager
                    .getString(ScarabConstants.INSTANCE_ID);
            }
            catch (Exception e)
            {
                getLog().error("Error getting DOMAIN_NAME:", e);
            }
        }
        return instanceId;
    }
    
    /**
     * Set the value of Scarab domain name.
     * The value can be an arbirtrary String.
     * Note: This instance attriute is NOT related to ip/email-domains!
     * @param v  Value to assign to domain.
     */
    public void setScarabInstanceId(String v)
    {
        if (v != null)
        {
            this.instanceId = v;
        }
    }

    /**
     * Get the value of port.
     * @return value of port.
     */
    public String getPort() 
        throws Exception
    {
        if (port == null)
        {
            port = GlobalParameterManager
                    .getString(ScarabConstants.HTTP_PORT);
        }
        return port;
    }
    
    /**
     * Set the value of port.
     * @param v  Value to assign to port.
     */
    public void setPort(String v)
        throws Exception
    {
        if (v != null)
        {
            this.port = v;
        }
    }

    /**
     * Get the value of scheme.
     * @return value of scheme.
     */
    public String getScheme() 
        throws Exception
    {
        if (scheme == null)
        {
            scheme = GlobalParameterManager
                    .getString(ScarabConstants.HTTP_SCHEME);
        }
        return scheme;
    }
    
    /**
     * Set the value of scheme.
     * @param v  Value to assign to scheme.
     */
    public void setScheme(String v) 
        throws Exception
    {
        if (v != null)
        {
            this.scheme = v;
        }
    }

    /**
     * Get the value of scriptName.
     * @return value of scriptName.
     */
    public String getScriptName() 
        throws Exception
    {
        if (scriptName == null)
        {
            scriptName = GlobalParameterManager
                    .getString(ScarabConstants.HTTP_SCRIPT_NAME);
        }
        return scriptName;
    }
    
    /**
     * Set the value of scriptName.
     * @param v  Value to assign to scriptName.
     */
    public void setScriptName(String v) 
        throws Exception
    {
        if (v != null)
        {
            this.scriptName = v;
        }
    }

    /**
     * @see org.tigris.scarab.om.Module#getUsers(String)
     */
    public ScarabUser[] getUsers(String permission)
    {
        List perms = new ArrayList(1);
        perms.add(permission);
        return getUsers(perms);
    }

    /**
     * @see org.tigris.scarab.om.Module#getUsers(List)
     */
    public ScarabUser[] getUsers(List permissions)
    {
        ScarabUser[] result = null;
        Object obj = ScarabCache.get(this, GET_USERS, 
                                     (Serializable)permissions); 
        if (obj == null) 
        {        
            Criteria crit = new Criteria();
            crit.setDistinct();
            if (permissions.size() == 1) 
            {
                crit.add(TurbinePermissionPeer.NAME, permissions.get(0));
            }
            else if (permissions.size() > 1)
            {
                crit.addIn(TurbinePermissionPeer.NAME, permissions);
            }      
            
            if (permissions.size() >= 1)
            {
                ArrayList groups = new ArrayList(2);
                groups.add(getModuleId());
                groups.add(ROOT_ID);
                crit.addJoin(TurbinePermissionPeer.PERMISSION_ID, 
                             TurbineRolePermissionPeer.PERMISSION_ID);
                crit.addJoin(TurbineRolePermissionPeer.ROLE_ID, 
                             TurbineUserGroupRolePeer.ROLE_ID);
                crit.addIn(TurbineUserGroupRolePeer.GROUP_ID, groups);
                crit.addJoin(ScarabUserImplPeer.USER_ID, 
                             TurbineUserGroupRolePeer.USER_ID);
                
                crit.add(ScarabUserImplPeer.getColumnName(User.CONFIRM_VALUE),(Object)ScarabUser.DELETED,Criteria.NOT_EQUAL);
                

                try
                {
                    User[] users = TurbineSecurity.getUsers(crit);
                    result = new ScarabUser[users.length];
                    for (int i=result.length-1; i>=0; i--) 
                    {
                        result[i] = (ScarabUser)users[i];
                    }
                }
                catch (Exception e)
                {
                    getLog().error(
                        "An exception prevented retrieving any users", e);
                    // this method should probably throw the exception, but
                    // until the interface is changed, wrap it in a RuntimeExc.
                    throw new RuntimeException(
                        "Please check turbine.log for more info: " + 
                        e.getMessage()); //EXCEPTION
                }
            }
            else 
            {
                result = new ScarabUser[0];
            }
            ScarabCache.put(result, this, GET_USERS, 
                            (Serializable)permissions);
        }
        else 
        {
            result = (ScarabUser[])obj;
        }
        return result;
    }


    /**
     * @see org.tigris.scarab.om.Module#getUsers(String, String, String, String, IssueType)
     * @param mitList MITs to restrict the user's search. If null, it will not be restricted.
     */
    public ScarabPaginatedList getUsers(String name, String username, 
                                        MITList mitList, 
                                        int pageNum, int resultsPerPage,
                                        final String sortColumn, String sortPolarity,
                                        boolean includeCommitters)
        throws Exception
    {
        final int polarity = sortPolarity.equals("asc") ? 1 : -1; 
        List result = null;
        ScarabPaginatedList paginated = null; 

        Comparator c = new Comparator() 
        {
            public int compare(Object o1, Object o2) 
            {
                int i = 0;
                if ("username".equals(sortColumn))
                {
                    i =  polarity * ((ScarabUser)o1).getUserName()
                              .compareTo(((ScarabUser)o2).getUserName());
                }
                else
                {
                    i =  polarity * ((ScarabUser)o1).getName()
                             .compareTo(((ScarabUser)o2).getName());
                }
                return i;
             }
        };

            Criteria crit = new Criteria();//
            Criteria critCount = new Criteria();
            critCount.addSelectColumn("COUNT(DISTINCT " + TurbineUserPeer.USERNAME + ")");
            if (mitList != null)
            {
                List modules = mitList.getModules();
                for (Iterator it = modules.iterator(); it.hasNext(); )
                {
                    Module mod = (Module)it.next();
                    List perms = mitList.getUserAttributePermissions();
                    if (includeCommitters && !perms.contains(org.tigris.scarab.services.security.ScarabSecurity.ISSUE__ENTER))
                    {
                        perms.add(org.tigris.scarab.services.security.ScarabSecurity.ISSUE__ENTER);
                    }

                    crit.addIn(TurbinePermissionPeer.PERMISSION_NAME, perms);
                    crit.setDistinct();
                    critCount.addIn(TurbinePermissionPeer.PERMISSION_NAME, perms);
                }
                crit.addIn(TurbineUserGroupRolePeer.GROUP_ID, mitList.getModuleIds());
                critCount.addIn(TurbineUserGroupRolePeer.GROUP_ID, mitList.getModuleIds());
            }
            crit.addJoin(TurbineUserPeer.USER_ID, TurbineUserGroupRolePeer.USER_ID);
            crit.addJoin(TurbineUserGroupRolePeer.ROLE_ID, TurbineRolePermissionPeer.ROLE_ID);
            crit.addJoin(TurbineRolePermissionPeer.PERMISSION_ID, TurbinePermissionPeer.PERMISSION_ID);
            critCount.addJoin(TurbineUserPeer.USER_ID, TurbineUserGroupRolePeer.USER_ID);
            critCount.addJoin(TurbineUserGroupRolePeer.ROLE_ID, TurbineRolePermissionPeer.ROLE_ID);
            critCount.addJoin(TurbineRolePermissionPeer.PERMISSION_ID, TurbinePermissionPeer.PERMISSION_ID);            

            if (name != null)
            {
                int nameSeparator = name.indexOf(" ");
                if (nameSeparator != -1) 
                {
                    String firstName = name.substring(0, nameSeparator);
                    String lastName = name.substring(nameSeparator+1, name.length());
                    crit.add(ScarabUserImplPeer.FIRST_NAME, 
                             addWildcards(firstName), Criteria.LIKE);
                    crit.add(ScarabUserImplPeer.LAST_NAME, 
                             addWildcards(lastName), Criteria.LIKE);
                    critCount.add(ScarabUserImplPeer.FIRST_NAME, 
                            addWildcards(firstName), Criteria.LIKE);
                    critCount.add(ScarabUserImplPeer.LAST_NAME, 
                            addWildcards(lastName), Criteria.LIKE);
                    
                }
                else 
                {
                    String[] tableAndColumn = StringUtils.split(ScarabUserImplPeer.FIRST_NAME, ".");
                    Criteria.Criterion fn = crit.getNewCriterion(tableAndColumn[0],
                                                                 tableAndColumn[1], 
                                                                 addWildcards(name), 
                                                                 Criteria.LIKE);
                    tableAndColumn = StringUtils.split(ScarabUserImplPeer.LAST_NAME, ".");
                    Criteria.Criterion ln = crit.getNewCriterion(tableAndColumn[0],
                                                                 tableAndColumn[1], 
                                                                 addWildcards(name), 
                                                                 Criteria.LIKE);
                    fn.or(ln);
                    crit.add(fn);
                    critCount.add(fn);
                }
            }

            if (username != null)
            {
                crit.add(ScarabUserImplPeer.LOGIN_NAME, 
                         addWildcards(username), Criteria.LIKE);
                critCount.add(ScarabUserImplPeer.LOGIN_NAME, 
                        addWildcards(username), Criteria.LIKE);
            }
            
            String col = ScarabUserImplPeer.FIRST_NAME;
            if (sortColumn.equals("username"))
                col = ScarabUserImplPeer.USERNAME;
            if (sortPolarity.equals("asc"))
            {
                crit.addAscendingOrderByColumn(col);
            }
            else
            {
                crit.addDescendingOrderByColumn(col);
            }
            
            int totalResultSize = ScarabUserImplPeer.getUsersCount(critCount);
            
            crit.setOffset((pageNum - 1)* resultsPerPage);
            crit.setLimit(resultsPerPage);
            result = ScarabUserImplPeer.doSelect(crit);

            // if there are results, sort the result set
            if (totalResultSize > 0 && resultsPerPage > 0)
            {

                paginated = new ScarabPaginatedList(result, totalResultSize,
                                                    pageNum,
                                                    resultsPerPage);
            }
            else 
            {
                paginated = new ScarabPaginatedList();
            }
        
        return paginated;
    }


    /**
     * @see org.tigris.scarab.om.Module#getUsers(String, String, String, String, IssueType)
     * This implementation adds wildcard prefix and suffix and performs an SQL 
     * LIKE query for each of the String args that are not null.
     * WARNING: This is potentially a very EXPENSIVE method.
     */
    public List getUsers(String firstName, String lastName, 
                         String username, String email, IssueType issueType)
        throws Exception
    {
        List result = null;
        // 4th element is ignored due to bug in torque
        Serializable[] keys = {this, GET_USERS, firstName, null, lastName, 
                               username, email, issueType};
        Object obj = ScarabCache.get(keys); 
        if (obj == null) 
        {
            ScarabUser[] eligibleUsers = getUsers(getUserPermissions(issueType));
            if (eligibleUsers == null || eligibleUsers.length == 0) 
            {
                result = Collections.EMPTY_LIST;
            }
            else 
            {
                List userIds = new ArrayList();
                for (int i = 0; i < eligibleUsers.length; i++)
                {
                    userIds.add(eligibleUsers[i].getUserId());
                }
                Criteria crit = new Criteria();
                crit.addIn(ScarabUserImplPeer.USER_ID, userIds);
                
                if (firstName != null)
                {
                    crit.add(ScarabUserImplPeer.FIRST_NAME, 
                             addWildcards(firstName), Criteria.LIKE);
                }
                if (lastName != null)
                {
                    crit.add(ScarabUserImplPeer.LAST_NAME, 
                             addWildcards(lastName), Criteria.LIKE);
                }
                if (username != null)
                {
                    crit.add(ScarabUserImplPeer.LOGIN_NAME, 
                             addWildcards(username), Criteria.LIKE);
                }
                if (email != null)
                {
                    crit.add(ScarabUserImplPeer.EMAIL, addWildcards(email), 
                             Criteria.LIKE);
                }
                result = ScarabUserImplPeer.doSelect(crit);
            }
            ScarabCache.put(result, keys);
        }
        else 
        {
            result = (List)obj;
        }
        return result;
    }

    private Object addWildcards(String s)
    {
        return new StringBuffer(s.length() + 2)
            .append('%').append(s).append('%').toString(); 
    }

    /**
     * Wrapper method to perform the proper cast to the BaseModule method
     * of the same name. FIXME: find a better way
     */
    public void setParent(Module v)
        throws Exception
    {
        super.setModuleRelatedByParentId(v);
        // setting the name to be null so that 
        // it gets rebuilt with the new information
        setName(null);
        resetAncestors();
    }

    /**
     * Cast the getScarabModuleRelatedByParentId() to a Module
     */
    public Module getParent()
        throws Exception
    {
        return super.getModuleRelatedByParentId();
    }

    /**
     * Override method to make sure the module name gets recalculated.
     *
     * @param id a <code>Integer</code> value
     */
    public void setParentId(Integer id)
        throws TorqueException
    {
        super.setParentId(id);
        // setting the name to be null so that 
        // it gets rebuilt with the new information
        setName(null);
        resetAncestors();
    }

    /**
     * This method returns a complete list of RModuleIssueTypes
     * which are not deleted, have a IssueType.PARENT_ID of 0 and
     * sorted ascending by PREFERRED_ORDER.
     */
    public List getRModuleIssueTypes()
        throws TorqueException
    {
        return super.getRModuleIssueTypes("preferredOrder","asc");
    }

    /**
     * Returns RModuleAttributes associated with this Module.  Tries to find
     * RModuleAttributes associated directly through the db, but if none are
     * found it should look up the parent module tree until it finds a 
     * non-empty list.
     */
    public List getRModuleAttributes(Criteria crit)
        throws TorqueException
    {
        return super.getRModuleAttributes(crit);
    }

    /**
     * Returns associated RModuleOptions.  if a related AttributeOption is
     * deleted the RModuleOption will not show up in this list.
     *
     * @param crit a <code>Criteria</code> value
     * @return a <code>List</code> value
     */
    public List getRModuleOptions(Criteria crit)
        throws TorqueException
    {
        crit.addJoin(RModuleOptionPeer.OPTION_ID, 
                     AttributeOptionPeer.OPTION_ID)
            .add(AttributeOptionPeer.DELETED, false);
        return super.getRModuleOptions(crit);
    }


    public boolean allowsIssues()
    {
        return (true);
    }
    
    /**
     * Saves the module into the database
     */
    public void save() 
        throws TurbineSecurityException
    {
        try
        {
            super.save();
        }
        catch (Exception e)
        {
            throw new ScarabLocalizedTurbineSecurityException(e); //EXCEPTION
        }
    }

    /**
     * Saves the module into the database. Note that this
     * cannot be used within a activitySet if the module isNew()
     * because dbCon.commit() is called within the method. An
     * update can be done within a activitySet though.
     */
    public void save(Connection dbCon) 
        throws TorqueException
    {
        // if new, make sure the code has a value.
        if (isNew())
        {
            Criteria crit = new Criteria();
            crit.add(ScarabModulePeer.MODULE_NAME, getRealName());
            crit.add(ScarabModulePeer.PARENT_ID, getParentId());
            // FIXME: this should be done with a method in Module
            // that takes the two criteria values as a argument so that other 
            // implementations can benefit from being able to get the 
            // list of modules. -- do not agree - jdm

            List result;
            try {
                result = ScarabModulePeer.doSelect(crit);
            }
            catch (TorqueException te)
            {
             throw new ScarabLocalizedTorqueException(
                     new ScarabException(
                             L10NKeySet.ExceptionTorqueGeneric, te));
            }
            
            if (result.size() > 0)
            {
                throw new ScarabLocalizedTorqueException(
                        new ScarabException(
                                L10NKeySet.ExceptionModuleAllreadyExists,
                        getRealName(), 
                        getParentId()));
            }

            String code = getCode();
            if (code == null || code.length() == 0)
            {
                if (getParentId().equals(ROOT_ID))
                {
                    throw new ScarabLocalizedTorqueException(new ScarabException(L10NKeySet.ExceptionTopLevelModuleWithoutCode));
                }

                try
                {
                    setCode(getParent().getCode());
                }
                catch (Exception e)
                {
                    throw new ScarabLocalizedTorqueException(new ScarabException(L10NKeySet.ExceptionCantPropagateModuleCode, e));
                }
            }

            // need to do this before the relationship save below
            // in order to set the moduleid for the new module.
            super.save(dbCon);
            try
            {
                dbCon.commit();
            }
            catch (Exception e)
            {
                throw new ScarabLocalizedTorqueException(new ScarabException(L10NKeySet.ExceptionGeneric, e));
            }
            
            if (getOwnerId() == null) 
            {
                throw new ScarabLocalizedTorqueException(new  ScarabException(L10NKeySet.ExceptionSaveNeedsOwner));
            }
            // grant the ower of the module the Project Owner role
            try
            {
                User user = ScarabUserManager.getInstance(getOwnerId());
                // FIXME: get this Project Owner string out of here and into
                //        a constant (JSS)
                Role role = TurbineSecurity.getRole("Project Owner");
                grant (user, role);
                setInitialAttributesAndIssueTypes();
            }
            catch (Exception e)
            {
                throw new ScarabLocalizedTorqueException(new ScarabException(L10NKeySet.ExceptionGeneric, e));
            }
        }
        else
        {
            super.save(dbCon);
        }
        
        // clear out the cache beause we want to make sure that
        // things get updated properly.
        ScarabCache.clear();
    }

    // *******************************************************************
    // Turbine Group implementation get/setName and save are defined in
    // parent class AbstractScarabModule
    // *******************************************************************

    /**
     * Removes a group from the system.
     *
     * @throws TurbineSecurityException if the Group could not be removed.
     */
    public void remove()
        throws TurbineSecurityException
    {
        throw new TurbineSecurityException("Not implemented"); //EXCEPTION
    }

    /**
     * Renames the group.
     *
     * @param name The new Group name.
     * @throws TurbineSecurityException if the Group could not be renamed.
     */
    public void rename(String name)
        throws TurbineSecurityException
    {
        throw new TurbineSecurityException("Not implemented"); //EXCEPTION
    }

    /**
     * Grants a Role in this Group to an User.
     *
     * @param user An User.
     * @param role A Role.
     * @throws TurbineSecurityException if there is a problem while assigning
     * the Role.
     */
    public void grant(User user, Role role)
        throws TurbineSecurityException
    {
        TurbineSecurity.grant(user,this,role);
    }

    /**
     * Grants Roles in this Group to an User.
     *
     * @param user An User.
     * @param roleSet A RoleSet.
     * @throws TurbineSecurityException if there is a problem while assigning
     * the Roles.
     */
    public void grant(User user, RoleSet roleSet)
        throws TurbineSecurityException
    {
        throw new TurbineSecurityException("Not implemented"); //EXCEPTION
    }

    /**
     * Revokes a Role in this Group from an User.
     *
     * @param user An User.
     * @param role A Role.
     * @throws TurbineSecurityException if there is a problem while unassigning
     * the Role.
     */
    public void revoke(User user, Role role)
        throws TurbineSecurityException
    {
        throw new TurbineSecurityException("Not implemented"); //EXCEPTION
    }

    /**
     * Revokes Roles in this group from an User.
     *
     * @param user An User.
     * @param roleSet a RoleSet.
     * @throws TurbineSecurityException if there is a problem while unassigning
     * the Roles.
     */
    public void revoke(User user, RoleSet roleSet)
        throws TurbineSecurityException
    {
        throw new TurbineSecurityException("Not implemented"); //EXCEPTION
    }

    /**
     * Used for ordering Groups.
     *
     * @param obj The Object to compare to.
     * @return -1 if the name of the other object is lexically greater than 
     * this group, 1 if it is lexically lesser, 0 if they are equal.
     */
    public int compareTo(Object obj)
    {
        //---------------------------------------------------------------------
        // dr@bitonic.com : commented out as per conversation with John McNally
        //   over IRC on 20-Dec-2001
        //---------------------------------------------------------------------
        //if (this.getClass() != obj.getClass())
        //{
        //    throw new ClassCastException();
        //}
        String name1 = ((Group)obj).getName();
        String name2 = this.getName();

        return name2.compareTo(name1);
    }


    /**
     * All emails related to this module will have a copy sent to
     * this address.  A system-wide default email address can be specified in 
     * Scarab.properties with the key: scarab.email.archive.toAddress
     */
    public String getArchiveEmail()
    {
        String email = super.getArchiveEmail();
        if (email == null || email.length() == 0) 
        {
            email = Turbine.getConfiguration()
                .getString(ScarabConstants.ARCHIVE_EMAIL_ADDRESS, null);
        }
        
        return email;
    }

    /**
     * returns an array of Roles that can be approved without need for
     * moderation.
     */
    public String[] getAutoApprovedRoles()
    {
        return Turbine.getConfiguration()
            .getStringArray(ScarabConstants.AUTO_APPROVED_ROLES);
    }

    /**
     * Gets all module roles.
     */
    public List getRoles() 
        throws Exception
    {
        return new ArrayList(0);
    }

    public String toString()
    {
        return '{' + super.toString() + " - ID=" + getModuleId() + " - " 
            + getName() + '}';
    }
}

