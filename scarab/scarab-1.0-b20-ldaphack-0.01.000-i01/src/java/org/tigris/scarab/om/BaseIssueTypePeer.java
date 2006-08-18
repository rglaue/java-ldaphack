package org.tigris.scarab.om;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.torque.NoRowsException;
import org.apache.torque.TooManyRowsException;
import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.map.MapBuilder;
import org.apache.torque.map.TableMap;
import org.apache.torque.om.DateKey;
import org.apache.torque.om.NumberKey;
import org.apache.torque.om.StringKey;
import org.apache.torque.om.ObjectKey;
import org.apache.torque.om.SimpleKey;
import org.apache.torque.util.BasePeer;
import org.apache.torque.util.Criteria;

import com.workingdogs.village.DataSetException;
import com.workingdogs.village.QueryDataSet;
import com.workingdogs.village.Record;

// Local classes
import org.tigris.scarab.om.map.*;


  
/**
 */
public abstract class BaseIssueTypePeer
    extends BasePeer
{

    /** the default database name for this class */
    public static final String DATABASE_NAME = "scarab";

     /** the table name for this class */
    public static final String TABLE_NAME = "SCARAB_ISSUE_TYPE";

    /**
     * @return the map builder for this peer
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static MapBuilder getMapBuilder()
        throws TorqueException
    {
        return getMapBuilder(IssueTypeMapBuilder.CLASS_NAME);
    }

      /** the column name for the ISSUE_TYPE_ID field */
    public static final String ISSUE_TYPE_ID;
      /** the column name for the NAME field */
    public static final String NAME;
      /** the column name for the DESCRIPTION field */
    public static final String DESCRIPTION;
      /** the column name for the PARENT_ID field */
    public static final String PARENT_ID;
      /** the column name for the DEDUPE field */
    public static final String DEDUPE;
      /** the column name for the DELETED field */
    public static final String DELETED;
      /** the column name for the LOCKED field */
    public static final String LOCKED;
      /** the column name for the ISDEFAULT field */
    public static final String ISDEFAULT;
  
    static
    {
          ISSUE_TYPE_ID = "SCARAB_ISSUE_TYPE.ISSUE_TYPE_ID";
          NAME = "SCARAB_ISSUE_TYPE.NAME";
          DESCRIPTION = "SCARAB_ISSUE_TYPE.DESCRIPTION";
          PARENT_ID = "SCARAB_ISSUE_TYPE.PARENT_ID";
          DEDUPE = "SCARAB_ISSUE_TYPE.DEDUPE";
          DELETED = "SCARAB_ISSUE_TYPE.DELETED";
          LOCKED = "SCARAB_ISSUE_TYPE.LOCKED";
          ISDEFAULT = "SCARAB_ISSUE_TYPE.ISDEFAULT";
          if (Torque.isInit())
        {
            try
            {
                getMapBuilder(IssueTypeMapBuilder.CLASS_NAME);
            }
            catch (Exception e)
            {
                log.error("Could not initialize Peer", e);
            }
        }
        else
        {
            Torque.registerMapBuilder(IssueTypeMapBuilder.CLASS_NAME);
        }
    }
 
    /** number of columns for this peer */
    public static final int numColumns =  8;

    /** A class that can be returned by this peer. */
    protected static final String CLASSNAME_DEFAULT =
        "org.tigris.scarab.om.IssueType";

    /** A class that can be returned by this peer. */
    protected static final Class CLASS_DEFAULT = initClass(CLASSNAME_DEFAULT);

    /**
     * Class object initialization method.
     *
     * @param className name of the class to initialize
     * @return the initialized class
     */
    private static Class initClass(String className)
    {
        Class c = null;
        try
        {
            c = Class.forName(className);
        }
        catch (Throwable t)
        {
            log.error("A FATAL ERROR has occurred which should not "
                + "have happened under any circumstance.  Please notify "
                + "the Torque developers <torque-dev@db.apache.org> "
                + "and give as many details as possible (including the error "
                + "stack trace).", t);

            // Error objects should always be propogated.
            if (t instanceof Error)
            {
                throw (Error) t.fillInStackTrace();
            }
        }
        return c;
    }

    /**
     * Get the list of objects for a ResultSet.  Please not that your
     * resultset MUST return columns in the right order.  You can use
     * getFieldNames() in BaseObject to get the correct sequence.
     *
     * @param results the ResultSet
     * @return the list of objects
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List resultSet2Objects(java.sql.ResultSet results)
            throws TorqueException
    {
        try
        {
            QueryDataSet qds = null;
            List rows = null;
            try
            {
                qds = new QueryDataSet(results);
                rows = getSelectResults(qds);
            }
            finally
            {
                if (qds != null)
                {
                    qds.close();
                }
            }

            return populateObjects(rows);
        }
        catch (SQLException e)
        {
            throw new TorqueException(e);
        }
        catch (DataSetException e)
        {
            throw new TorqueException(e);
        }
    }


  
    /**
     * Method to do inserts.
     *
     * @param criteria object used to create the INSERT statement.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static ObjectKey doInsert(Criteria criteria)
        throws TorqueException
    {
        return BaseIssueTypePeer
            .doInsert(criteria, (Connection) null);
    }

    /**
     * Method to do inserts.  This method is to be used during a transaction,
     * otherwise use the doInsert(Criteria) method.  It will take care of
     * the connection details internally.
     *
     * @param criteria object used to create the INSERT statement.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static ObjectKey doInsert(Criteria criteria, Connection con)
        throws TorqueException
    {
                                      // check for conversion from boolean to int
        if (criteria.containsKey(DEDUPE))
        {
            Object possibleBoolean = criteria.get(DEDUPE);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(DEDUPE, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  // check for conversion from boolean to int
        if (criteria.containsKey(DELETED))
        {
            Object possibleBoolean = criteria.get(DELETED);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(DELETED, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  // check for conversion from boolean to int
        if (criteria.containsKey(LOCKED))
        {
            Object possibleBoolean = criteria.get(LOCKED);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(LOCKED, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  // check for conversion from boolean to int
        if (criteria.containsKey(ISDEFAULT))
        {
            Object possibleBoolean = criteria.get(ISDEFAULT);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(ISDEFAULT, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
      
        setDbName(criteria);

        if (con == null)
        {
            return BasePeer.doInsert(criteria);
        }
        else
        {
            return BasePeer.doInsert(criteria, con);
        }
    }

    /**
     * Add all the columns needed to create a new object.
     *
     * @param criteria object containing the columns to add.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void addSelectColumns(Criteria criteria)
            throws TorqueException
    {
          criteria.addSelectColumn(ISSUE_TYPE_ID);
          criteria.addSelectColumn(NAME);
          criteria.addSelectColumn(DESCRIPTION);
          criteria.addSelectColumn(PARENT_ID);
          criteria.addSelectColumn(DEDUPE);
          criteria.addSelectColumn(DELETED);
          criteria.addSelectColumn(LOCKED);
          criteria.addSelectColumn(ISDEFAULT);
      }

    /**
     * Create a new object of type cls from a resultset row starting
     * from a specified offset.  This is done so that you can select
     * other rows than just those needed for this object.  You may
     * for example want to create two objects from the same row.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static IssueType row2Object(Record row,
                                             int offset,
                                             Class cls)
        throws TorqueException
    {
        try
        {
            IssueType obj = (IssueType) cls.newInstance();
            IssueTypePeer.populateObject(row, offset, obj);
                  obj.setModified(false);
              obj.setNew(false);

            return obj;
        }
        catch (InstantiationException e)
        {
            throw new TorqueException(e);
        }
        catch (IllegalAccessException e)
        {
            throw new TorqueException(e);
        }
    }

    /**
     * Populates an object from a resultset row starting
     * from a specified offset.  This is done so that you can select
     * other rows than just those needed for this object.  You may
     * for example want to create two objects from the same row.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void populateObject(Record row,
                                      int offset,
                                      IssueType obj)
        throws TorqueException
    {
        try
        {
                obj.setIssueTypeId(row.getValue(offset + 0).asIntegerObj());
                  obj.setName(row.getValue(offset + 1).asString());
                  obj.setDescription(row.getValue(offset + 2).asString());
                  obj.setParentId(row.getValue(offset + 3).asIntegerObj());
                  obj.setDedupe(row.getValue(offset + 4).asBoolean());
                  obj.setDeleted(row.getValue(offset + 5).asBoolean());
                  obj.setLocked(row.getValue(offset + 6).asBoolean());
                  obj.setIsdefault(row.getValue(offset + 7).asBoolean());
              }
        catch (DataSetException e)
        {
            throw new TorqueException(e);
        }
    }

    /**
     * Method to do selects.
     *
     * @param criteria object used to create the SELECT statement.
     * @return List of selected Objects
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List doSelect(Criteria criteria) throws TorqueException
    {
        return populateObjects(doSelectVillageRecords(criteria));
    }

    /**
     * Method to do selects within a transaction.
     *
     * @param criteria object used to create the SELECT statement.
     * @param con the connection to use
     * @return List of selected Objects
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List doSelect(Criteria criteria, Connection con)
        throws TorqueException
    {
        return populateObjects(doSelectVillageRecords(criteria, con));
    }

    /**
     * Grabs the raw Village records to be formed into objects.
     * This method handles connections internally.  The Record objects
     * returned by this method should be considered readonly.  Do not
     * alter the data and call save(), your results may vary, but are
     * certainly likely to result in hard to track MT bugs.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List doSelectVillageRecords(Criteria criteria)
        throws TorqueException
    {
        return BaseIssueTypePeer
            .doSelectVillageRecords(criteria, (Connection) null);
    }

    /**
     * Grabs the raw Village records to be formed into objects.
     * This method should be used for transactions
     *
     * @param criteria object used to create the SELECT statement.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List doSelectVillageRecords(Criteria criteria, Connection con)
        throws TorqueException
    {
        if (criteria.getSelectColumns().size() == 0)
        {
            addSelectColumns(criteria);
        }

                                      // check for conversion from boolean to int
        if (criteria.containsKey(DEDUPE))
        {
            Object possibleBoolean = criteria.get(DEDUPE);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(DEDUPE, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  // check for conversion from boolean to int
        if (criteria.containsKey(DELETED))
        {
            Object possibleBoolean = criteria.get(DELETED);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(DELETED, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  // check for conversion from boolean to int
        if (criteria.containsKey(LOCKED))
        {
            Object possibleBoolean = criteria.get(LOCKED);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(LOCKED, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  // check for conversion from boolean to int
        if (criteria.containsKey(ISDEFAULT))
        {
            Object possibleBoolean = criteria.get(ISDEFAULT);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(ISDEFAULT, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
      
        setDbName(criteria);

        // BasePeer returns a List of Value (Village) arrays.  The array
        // order follows the order columns were placed in the Select clause.
        if (con == null)
        {
            return BasePeer.doSelect(criteria);
        }
        else
        {
            return BasePeer.doSelect(criteria, con);
        }
    }

    /**
     * The returned List will contain objects of the default type or
     * objects that inherit from the default.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List populateObjects(List records)
        throws TorqueException
    {
        List results = new ArrayList(records.size());

        // populate the object(s)
        for (int i = 0; i < records.size(); i++)
        {
            Record row = (Record) records.get(i);
              results.add(IssueTypePeer.row2Object(row, 1,
                IssueTypePeer.getOMClass()));
          }
        return results;
    }
 

    /**
     * The class that the Peer will make instances of.
     * If the BO is abstract then you must implement this method
     * in the BO.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static Class getOMClass()
        throws TorqueException
    {
        return CLASS_DEFAULT;
    }

    /**
     * Method to do updates.
     *
     * @param criteria object containing data that is used to create the UPDATE
     *        statement.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doUpdate(Criteria criteria) throws TorqueException
    {
         BaseIssueTypePeer
            .doUpdate(criteria, (Connection) null);
    }

    /**
     * Method to do updates.  This method is to be used during a transaction,
     * otherwise use the doUpdate(Criteria) method.  It will take care of
     * the connection details internally.
     *
     * @param criteria object containing data that is used to create the UPDATE
     *        statement.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doUpdate(Criteria criteria, Connection con)
        throws TorqueException
    {
        Criteria selectCriteria = new Criteria(DATABASE_NAME, 2);
                   selectCriteria.put(ISSUE_TYPE_ID, criteria.remove(ISSUE_TYPE_ID));
                                                // check for conversion from boolean to int
        if (criteria.containsKey(DEDUPE))
        {
            Object possibleBoolean = criteria.get(DEDUPE);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(DEDUPE, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                      // check for conversion from boolean to int
        if (criteria.containsKey(DELETED))
        {
            Object possibleBoolean = criteria.get(DELETED);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(DELETED, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                      // check for conversion from boolean to int
        if (criteria.containsKey(LOCKED))
        {
            Object possibleBoolean = criteria.get(LOCKED);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(LOCKED, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                      // check for conversion from boolean to int
        if (criteria.containsKey(ISDEFAULT))
        {
            Object possibleBoolean = criteria.get(ISDEFAULT);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(ISDEFAULT, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
          
        setDbName(criteria);

        if (con == null)
        {
            BasePeer.doUpdate(selectCriteria, criteria);
        }
        else
        {
            BasePeer.doUpdate(selectCriteria, criteria, con);
        }
    }

    /**
     * Method to do deletes.
     *
     * @param criteria object containing data that is used DELETE from database.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
     public static void doDelete(Criteria criteria) throws TorqueException
     {
         IssueTypePeer
            .doDelete(criteria, (Connection) null);
     }

    /**
     * Method to do deletes.  This method is to be used during a transaction,
     * otherwise use the doDelete(Criteria) method.  It will take care of
     * the connection details internally.
     *
     * @param criteria object containing data that is used DELETE from database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
     public static void doDelete(Criteria criteria, Connection con)
        throws TorqueException
     {
                                      // check for conversion from boolean to int
        if (criteria.containsKey(DEDUPE))
        {
            Object possibleBoolean = criteria.get(DEDUPE);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(DEDUPE, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  // check for conversion from boolean to int
        if (criteria.containsKey(DELETED))
        {
            Object possibleBoolean = criteria.get(DELETED);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(DELETED, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  // check for conversion from boolean to int
        if (criteria.containsKey(LOCKED))
        {
            Object possibleBoolean = criteria.get(LOCKED);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(LOCKED, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  // check for conversion from boolean to int
        if (criteria.containsKey(ISDEFAULT))
        {
            Object possibleBoolean = criteria.get(ISDEFAULT);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(ISDEFAULT, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
      
        setDbName(criteria);

        if (con == null)
        {
            BasePeer.doDelete(criteria);
        }
        else
        {
            BasePeer.doDelete(criteria, con);
        }
     }

    /**
     * Method to do selects
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List doSelect(IssueType obj) throws TorqueException
    {
        return doSelect(buildSelectCriteria(obj));
    }

    /**
     * Method to do inserts
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doInsert(IssueType obj) throws TorqueException
    {
          obj.setPrimaryKey(doInsert(buildCriteria(obj)));
          obj.setNew(false);
        obj.setModified(false);
    }

    /**
     * @param obj the data object to update in the database.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doUpdate(IssueType obj) throws TorqueException
    {
        doUpdate(buildCriteria(obj));
        obj.setModified(false);
    }

    /**
     * @param obj the data object to delete in the database.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(IssueType obj) throws TorqueException
    {
        doDelete(buildSelectCriteria(obj));
    }

    /**
     * Method to do inserts.  This method is to be used during a transaction,
     * otherwise use the doInsert(IssueType) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to insert into the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doInsert(IssueType obj, Connection con)
        throws TorqueException
    {
          obj.setPrimaryKey(doInsert(buildCriteria(obj), con));
          obj.setNew(false);
        obj.setModified(false);
    }

    /**
     * Method to do update.  This method is to be used during a transaction,
     * otherwise use the doUpdate(IssueType) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to update in the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doUpdate(IssueType obj, Connection con)
        throws TorqueException
    {
        doUpdate(buildCriteria(obj), con);
        obj.setModified(false);
    }

    /**
     * Method to delete.  This method is to be used during a transaction,
     * otherwise use the doDelete(IssueType) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to delete in the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(IssueType obj, Connection con)
        throws TorqueException
    {
        doDelete(buildSelectCriteria(obj), con);
    }

    /**
     * Method to do deletes.
     *
     * @param pk ObjectKey that is used DELETE from database.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(ObjectKey pk) throws TorqueException
    {
        BaseIssueTypePeer
           .doDelete(pk, (Connection) null);
    }

    /**
     * Method to delete.  This method is to be used during a transaction,
     * otherwise use the doDelete(ObjectKey) method.  It will take
     * care of the connection details internally.
     *
     * @param pk the primary key for the object to delete in the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(ObjectKey pk, Connection con)
        throws TorqueException
    {
        doDelete(buildCriteria(pk), con);
    }

    /** Build a Criteria object from an ObjectKey */
    public static Criteria buildCriteria( ObjectKey pk )
    {
        Criteria criteria = new Criteria();
              criteria.add(ISSUE_TYPE_ID, pk);
          return criteria;
     }

    /** Build a Criteria object from the data object for this peer */
    public static Criteria buildCriteria( IssueType obj )
    {
        Criteria criteria = new Criteria(DATABASE_NAME);
              if (!obj.isNew())
            criteria.add(ISSUE_TYPE_ID, obj.getIssueTypeId());
              criteria.add(NAME, obj.getName());
              criteria.add(DESCRIPTION, obj.getDescription());
              criteria.add(PARENT_ID, obj.getParentId());
              criteria.add(DEDUPE, obj.getDedupe());
              criteria.add(DELETED, obj.getDeleted());
              criteria.add(LOCKED, obj.getLocked());
              criteria.add(ISDEFAULT, obj.getIsdefault());
          return criteria;
    }

    /** Build a Criteria object from the data object for this peer, skipping all binary columns */
    public static Criteria buildSelectCriteria( IssueType obj )
    {
        Criteria criteria = new Criteria(DATABASE_NAME);
              if (!obj.isNew())
                    criteria.add(ISSUE_TYPE_ID, obj.getIssueTypeId());
                          criteria.add(NAME, obj.getName());
                          criteria.add(DESCRIPTION, obj.getDescription());
                          criteria.add(PARENT_ID, obj.getParentId());
                          criteria.add(DEDUPE, obj.getDedupe());
                          criteria.add(DELETED, obj.getDeleted());
                          criteria.add(LOCKED, obj.getLocked());
                          criteria.add(ISDEFAULT, obj.getIsdefault());
              return criteria;
    }
 
    
        /**
     * Retrieve a single object by pk
     *
     * @param pk the primary key
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @throws NoRowsException Primary key was not found in database.
     * @throws TooManyRowsException Primary key was not found in database.
     */
    public static IssueType retrieveByPK(Integer pk)
        throws TorqueException, NoRowsException, TooManyRowsException
    {
        return retrieveByPK(SimpleKey.keyFor(pk));
    }

    /**
     * Retrieve a single object by pk
     *
     * @param pk the primary key
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @throws NoRowsException Primary key was not found in database.
     * @throws TooManyRowsException Primary key was not found in database.
     */
    public static IssueType retrieveByPK(Integer pk, Connection con)
        throws TorqueException, NoRowsException, TooManyRowsException
    {
        return retrieveByPK(SimpleKey.keyFor(pk), con);
    }
  
    /**
     * Retrieve a single object by pk
     *
     * @param pk the primary key
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @throws NoRowsException Primary key was not found in database.
     * @throws TooManyRowsException Primary key was not found in database.
     */
    public static IssueType retrieveByPK(ObjectKey pk)
        throws TorqueException, NoRowsException, TooManyRowsException
    {
        Connection db = null;
        IssueType retVal = null;
        try
        {
            db = Torque.getConnection(DATABASE_NAME);
            retVal = retrieveByPK(pk, db);
        }
        finally
        {
            Torque.closeConnection(db);
        }
        return(retVal);
    }

    /**
     * Retrieve a single object by pk
     *
     * @param pk the primary key
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @throws NoRowsException Primary key was not found in database.
     * @throws TooManyRowsException Primary key was not found in database.
     */
    public static IssueType retrieveByPK(ObjectKey pk, Connection con)
        throws TorqueException, NoRowsException, TooManyRowsException
    {
        Criteria criteria = buildCriteria(pk);
        List v = doSelect(criteria, con);
        if (v.size() == 0)
        {
            throw new NoRowsException("Failed to select a row.");
        }
        else if (v.size() > 1)
        {
            throw new TooManyRowsException("Failed to select only one row.");
        }
        else
        {
            return (IssueType)v.get(0);
        }
    }

    /**
     * Retrieve a multiple objects by pk
     *
     * @param pks List of primary keys
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List retrieveByPKs(List pks)
        throws TorqueException
    {
        Connection db = null;
        List retVal = null;
        try
        {
           db = Torque.getConnection(DATABASE_NAME);
           retVal = retrieveByPKs(pks, db);
        }
        finally
        {
            Torque.closeConnection(db);
        }
        return(retVal);
    }

    /**
     * Retrieve a multiple objects by pk
     *
     * @param pks List of primary keys
     * @param dbcon the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List retrieveByPKs( List pks, Connection dbcon )
        throws TorqueException
    {
        List objs = null;
        if (pks == null || pks.size() == 0)
        {
            objs = new LinkedList();
        }
        else
        {
            Criteria criteria = new Criteria();
              criteria.addIn( ISSUE_TYPE_ID, pks );
          objs = doSelect(criteria, dbcon);
        }
        return objs;
    }

 



          
                                
  
    
  
      /**
     * Returns the TableMap related to this peer.  This method is not
     * needed for general use but a specific application could have a need.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    protected static TableMap getTableMap()
        throws TorqueException
    {
        return Torque.getDatabaseMap(DATABASE_NAME).getTable(TABLE_NAME);
    }
   
    private static void setDbName(Criteria crit)
    {
        // Set the correct dbName if it has not been overridden
        // crit.getDbName will return the same object if not set to
        // another value so == check is okay and faster
        if (crit.getDbName() == Torque.getDefaultDB())
        {
            crit.setDbName(DATABASE_NAME);
        }
    }
}
