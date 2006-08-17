

package org.tigris.scarab.om;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.manager.AbstractBaseManager;
import org.apache.torque.manager.CacheListener;
import org.apache.torque.manager.MethodResultCache;
import org.apache.torque.om.ObjectKey;
import org.apache.torque.om.SimpleKey;
import org.apache.torque.om.Persistent;
import org.apache.torque.util.Criteria;

/**
 * This class manages AttachmentType objects.
 * This class was autogenerated by Torque  *
 * You should not use this class directly.  It should not even be
 * extended all references should be to AttachmentTypeManager
 */
public abstract class BaseAttachmentTypeManager
    extends AbstractBaseManager
{
    /** The name of the manager */
    protected static String MANAGED_CLASS = "org.tigris.scarab.om.AttachmentType";

    /** The name of our class to pass to Torque as the default manager. */
    protected static String DEFAULT_MANAGER_CLASS
        = "org.tigris.scarab.om.AttachmentTypeManager";

    /**
     * Retrieves an implementation of the manager, based on the settings in
     * the configuration.
     *
     * @return an implementation of AttachmentTypeManager.
     */
    public static AttachmentTypeManager getManager()
    {
        return (AttachmentTypeManager)
            Torque.getManager(AttachmentTypeManager.MANAGED_CLASS,
                AttachmentTypeManager.DEFAULT_MANAGER_CLASS);
    }

    /**
     * Static accessor for the @see #getInstanceImpl().
     *
     * @return a <code>AttachmentType</code> value
     * @exception TorqueException if an error occurs
     */
    public static AttachmentType getInstance()
        throws TorqueException
    {
        return getManager().getInstanceImpl();
    }

    /**
     * Static accessor for the @see #getInstanceImpl(ObjectKey).
     *
     * @param id an <code>ObjectKey</code> value
     * @return a <code>AttachmentType</code> value
     * @exception TorqueException if an error occurs
     */
    public static AttachmentType getInstance(ObjectKey id)
        throws TorqueException
    {
        return getManager().getInstanceImpl(id);
    }

    /**
     * Static accessor for the @see #getInstanceImpl(ObjectKey, boolean).
     *
     * @param id an <code>ObjectKey</code> value
     * @param fromCache if true, look for cached AttachmentTypes before loading
     * from storage.
     * @return a <code>AttachmentType</code> value
     * @exception TorqueException if an error occurs
     */
    public static AttachmentType getInstance(ObjectKey id, boolean fromCache)
        throws TorqueException
    {
        return getManager().getInstanceImpl(id, fromCache);
    }

    /**
     * Static accessor for the @see #getInstanceImpl(ObjectKey).
     *
     * @param id an <code>ObjectKey</code> value
     * @return a <code>AttachmentType</code> value
     * @exception TorqueException if an error occurs
     */
    public static AttachmentType getInstance(Integer id)
        throws TorqueException
    {
        return getManager().getInstanceImpl(SimpleKey.keyFor(id));
    }

    /**
     * Static accessor for the @see #getInstanceImpl(ObjectKey).
     *
     * @param id an <code>ObjectKey</code> value
     * @param fromCache if true, look for cached AttachmentTypes before loading
     * from storage.
     * @return a <code>AttachmentType</code> value
     * @exception TorqueException if an error occurs
     */
    public static AttachmentType getInstance(Integer id, boolean fromCache)
        throws TorqueException
    {
        return getManager().getInstanceImpl(SimpleKey.keyFor(id), fromCache);
    }

    /**
     * Static accessor for the @see #getInstancesImpl(List).
     *
     * @param ids a <code>List</code> value
     * @return a <code>List</code> value
     * @exception TorqueException if an error occurs
     */
    public static List getInstances(List ids)
        throws TorqueException
    {
        return getManager().getInstancesImpl(ids);
    }

    /**
     * Static accessor for the @see #getInstancesImpl(List, boolean).
     *
     * @param ids a <code>List</code> value
     * @param fromCache if true, look for cached AttachmentTypes before loading
     * from storage.
     * @return a <code>List</code> value
     * @exception TorqueException if an error occurs
     */
    public static List getInstances(List ids, boolean fromCache)
        throws TorqueException
    {
        return getManager().getInstancesImpl(ids, fromCache);
    }

    public static void putInstance(Persistent om)
        throws TorqueException
    {
        getManager().putInstanceImpl(om);
    }

    public static void clear()
        throws TorqueException
    {
        getManager().clearImpl();
    }

    public static boolean exists(AttachmentType obj)
        throws TorqueException
    {
        return getManager().existsImpl(obj);
    }

    public static MethodResultCache getMethodResult()
    {
        return getManager().getMethodResultCache();
    }

    public static void addCacheListener(CacheListener listener)
    {
        getManager().addCacheListenerImpl(listener);
    }

    /**
     * Creates a new <code>BaseAttachmentTypeManager</code> instance.
     *
     * @exception TorqueException if an error occurs
     */
    public BaseAttachmentTypeManager()
        throws TorqueException
    {
        setClassName("org.tigris.scarab.om.AttachmentType");
    }

    /**
     * Get a fresh instance of a AttachmentTypeManager
     */
    protected AttachmentType getInstanceImpl()
        throws TorqueException
    {
        AttachmentType obj = null;
        try
        {
            obj = (AttachmentType) getOMInstance();
        }
        catch (Exception e)
        {
            throw new TorqueException(e);
        }
        return obj;
    }


    /**
     * Get a AttachmentType with the given id.
     *
     * @param id <code>ObjectKey</code> value
     */
    protected AttachmentType getInstanceImpl(ObjectKey id)
        throws TorqueException
    {
        return (AttachmentType) getOMInstance(id);
    }

    /**
     * Get a AttachmentType with the given id.
     *
     * @param id <code>ObjectKey</code> value
     * @param fromCache if true, look for cached AttachmentTypes before loading
     * from storage.
     */
    protected AttachmentType getInstanceImpl(ObjectKey id, boolean fromCache)
        throws TorqueException
    {
        return (AttachmentType) getOMInstance(id, fromCache);
    }

    /**
     * Gets a list of AttachmentTypes based on id's.
     *
     * @param ids a List of <code>ObjectKeys</code> value
     * @return a <code>List</code> of AttachmentTypes
     * @exception TorqueException if an error occurs
     */
    protected List getInstancesImpl(List ids)
        throws TorqueException
    {
        return getOMs(ids);
    }

    /**
     * Gets a list of AttachmentTypes based on id's.
     *
     * @param ids a List of <code>ObjectKeys</code> value
     * @param fromCache if true, look for cached AttachmentTypes before loading
     * from storage.
     * @return a <code>List</code> of AttachmentTypes
     * @exception TorqueException if an error occurs
     */
    protected List getInstancesImpl(List ids, boolean fromCache)
        throws TorqueException
    {
        return getOMs(ids, fromCache);
    }

    /**
     * check for a duplicate project name
     */
    protected boolean existsImpl(AttachmentType om)
        throws TorqueException
    {
        Criteria crit = AttachmentTypePeer
            .buildCriteria((AttachmentType)om);
        return AttachmentTypePeer.doSelect(crit).size() > 0;
    }


    protected Persistent retrieveStoredOM(ObjectKey id)
        throws TorqueException
    {
        return AttachmentTypePeer.retrieveByPK(id);
    }

    /**
     * Gets a list of ModuleEntities based on id's.
     *
     * @param ids a <code>NumberKey[]</code> value
     * @return a <code>List</code> value
     * @exception TorqueException if an error occurs
     */
    protected List retrieveStoredOMs(List ids)
        throws TorqueException
    {
        return AttachmentTypePeer.retrieveByPKs(ids);
    }
}
