

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
 * This class manages Modification objects.
 * This class was autogenerated by Torque  *
 * You should not use this class directly.  It should not even be
 * extended all references should be to ModificationManager
 */
public abstract class BaseModificationManager
    extends AbstractBaseManager
{
    /** The name of the manager */
    protected static String MANAGED_CLASS = "org.tigris.scarab.om.Modification";

    /** The name of our class to pass to Torque as the default manager. */
    protected static String DEFAULT_MANAGER_CLASS
        = "org.tigris.scarab.om.ModificationManager";

    /**
     * Retrieves an implementation of the manager, based on the settings in
     * the configuration.
     *
     * @return an implementation of ModificationManager.
     */
    public static ModificationManager getManager()
    {
        return (ModificationManager)
            Torque.getManager(ModificationManager.MANAGED_CLASS,
                ModificationManager.DEFAULT_MANAGER_CLASS);
    }

    /**
     * Static accessor for the @see #getInstanceImpl().
     *
     * @return a <code>Modification</code> value
     * @exception TorqueException if an error occurs
     */
    public static Modification getInstance()
        throws TorqueException
    {
        return getManager().getInstanceImpl();
    }

    /**
     * Static accessor for the @see #getInstanceImpl(ObjectKey).
     *
     * @param id an <code>ObjectKey</code> value
     * @return a <code>Modification</code> value
     * @exception TorqueException if an error occurs
     */
    public static Modification getInstance(ObjectKey id)
        throws TorqueException
    {
        return getManager().getInstanceImpl(id);
    }

    /**
     * Static accessor for the @see #getInstanceImpl(ObjectKey, boolean).
     *
     * @param id an <code>ObjectKey</code> value
     * @param fromCache if true, look for cached Modifications before loading
     * from storage.
     * @return a <code>Modification</code> value
     * @exception TorqueException if an error occurs
     */
    public static Modification getInstance(ObjectKey id, boolean fromCache)
        throws TorqueException
    {
        return getManager().getInstanceImpl(id, fromCache);
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
     * @param fromCache if true, look for cached Modifications before loading
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

    public static boolean exists(Modification obj)
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
     * Creates a new <code>BaseModificationManager</code> instance.
     *
     * @exception TorqueException if an error occurs
     */
    public BaseModificationManager()
        throws TorqueException
    {
        setClassName("org.tigris.scarab.om.Modification");
    }

    /**
     * Get a fresh instance of a ModificationManager
     */
    protected Modification getInstanceImpl()
        throws TorqueException
    {
        Modification obj = null;
        try
        {
            obj = (Modification) getOMInstance();
        }
        catch (Exception e)
        {
            throw new TorqueException(e);
        }
        return obj;
    }


    /**
     * Get a Modification with the given id.
     *
     * @param id <code>ObjectKey</code> value
     */
    protected Modification getInstanceImpl(ObjectKey id)
        throws TorqueException
    {
        return (Modification) getOMInstance(id);
    }

    /**
     * Get a Modification with the given id.
     *
     * @param id <code>ObjectKey</code> value
     * @param fromCache if true, look for cached Modifications before loading
     * from storage.
     */
    protected Modification getInstanceImpl(ObjectKey id, boolean fromCache)
        throws TorqueException
    {
        return (Modification) getOMInstance(id, fromCache);
    }

    /**
     * Gets a list of Modifications based on id's.
     *
     * @param ids a List of <code>ObjectKeys</code> value
     * @return a <code>List</code> of Modifications
     * @exception TorqueException if an error occurs
     */
    protected List getInstancesImpl(List ids)
        throws TorqueException
    {
        return getOMs(ids);
    }

    /**
     * Gets a list of Modifications based on id's.
     *
     * @param ids a List of <code>ObjectKeys</code> value
     * @param fromCache if true, look for cached Modifications before loading
     * from storage.
     * @return a <code>List</code> of Modifications
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
    protected boolean existsImpl(Modification om)
        throws TorqueException
    {
        Criteria crit = ModificationPeer
            .buildCriteria((Modification)om);
        return ModificationPeer.doSelect(crit).size() > 0;
    }


    protected Persistent retrieveStoredOM(ObjectKey id)
        throws TorqueException
    {
        return ModificationPeer.retrieveByPK(id);
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
        return ModificationPeer.retrieveByPKs(ids);
    }
}
