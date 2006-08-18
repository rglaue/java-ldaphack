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
public abstract class BaseRModuleAttributePeer
    extends BasePeer
{

    /** the default database name for this class */
    public static final String DATABASE_NAME = "scarab";

     /** the table name for this class */
    public static final String TABLE_NAME = "SCARAB_R_MODULE_ATTRIBUTE";

    /**
     * @return the map builder for this peer
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static MapBuilder getMapBuilder()
        throws TorqueException
    {
        return getMapBuilder(RModuleAttributeMapBuilder.CLASS_NAME);
    }

      /** the column name for the MODULE_ID field */
    public static final String MODULE_ID;
      /** the column name for the ATTRIBUTE_ID field */
    public static final String ATTRIBUTE_ID;
      /** the column name for the ISSUE_TYPE_ID field */
    public static final String ISSUE_TYPE_ID;
      /** the column name for the DISPLAY_VALUE field */
    public static final String DISPLAY_VALUE;
      /** the column name for the ACTIVE field */
    public static final String ACTIVE;
      /** the column name for the REQUIRED field */
    public static final String REQUIRED;
      /** the column name for the PREFERRED_ORDER field */
    public static final String PREFERRED_ORDER;
      /** the column name for the QUICK_SEARCH field */
    public static final String QUICK_SEARCH;
      /** the column name for the DEFAULT_TEXT_FLAG field */
    public static final String DEFAULT_TEXT_FLAG;
  
    static
    {
          MODULE_ID = "SCARAB_R_MODULE_ATTRIBUTE.MODULE_ID";
          ATTRIBUTE_ID = "SCARAB_R_MODULE_ATTRIBUTE.ATTRIBUTE_ID";
          ISSUE_TYPE_ID = "SCARAB_R_MODULE_ATTRIBUTE.ISSUE_TYPE_ID";
          DISPLAY_VALUE = "SCARAB_R_MODULE_ATTRIBUTE.DISPLAY_VALUE";
          ACTIVE = "SCARAB_R_MODULE_ATTRIBUTE.ACTIVE";
          REQUIRED = "SCARAB_R_MODULE_ATTRIBUTE.REQUIRED";
          PREFERRED_ORDER = "SCARAB_R_MODULE_ATTRIBUTE.PREFERRED_ORDER";
          QUICK_SEARCH = "SCARAB_R_MODULE_ATTRIBUTE.QUICK_SEARCH";
          DEFAULT_TEXT_FLAG = "SCARAB_R_MODULE_ATTRIBUTE.DEFAULT_TEXT_FLAG";
          if (Torque.isInit())
        {
            try
            {
                getMapBuilder(RModuleAttributeMapBuilder.CLASS_NAME);
            }
            catch (Exception e)
            {
                log.error("Could not initialize Peer", e);
            }
        }
        else
        {
            Torque.registerMapBuilder(RModuleAttributeMapBuilder.CLASS_NAME);
        }
    }
 
    /** number of columns for this peer */
    public static final int numColumns =  9;

    /** A class that can be returned by this peer. */
    protected static final String CLASSNAME_DEFAULT =
        "org.tigris.scarab.om.RModuleAttribute";

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
        return BaseRModuleAttributePeer
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
        if (criteria.containsKey(ACTIVE))
        {
            Object possibleBoolean = criteria.get(ACTIVE);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(ACTIVE, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  // check for conversion from boolean to int
        if (criteria.containsKey(REQUIRED))
        {
            Object possibleBoolean = criteria.get(REQUIRED);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(REQUIRED, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                        // check for conversion from boolean to int
        if (criteria.containsKey(QUICK_SEARCH))
        {
            Object possibleBoolean = criteria.get(QUICK_SEARCH);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(QUICK_SEARCH, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  // check for conversion from boolean to int
        if (criteria.containsKey(DEFAULT_TEXT_FLAG))
        {
            Object possibleBoolean = criteria.get(DEFAULT_TEXT_FLAG);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(DEFAULT_TEXT_FLAG, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
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
          criteria.addSelectColumn(MODULE_ID);
          criteria.addSelectColumn(ATTRIBUTE_ID);
          criteria.addSelectColumn(ISSUE_TYPE_ID);
          criteria.addSelectColumn(DISPLAY_VALUE);
          criteria.addSelectColumn(ACTIVE);
          criteria.addSelectColumn(REQUIRED);
          criteria.addSelectColumn(PREFERRED_ORDER);
          criteria.addSelectColumn(QUICK_SEARCH);
          criteria.addSelectColumn(DEFAULT_TEXT_FLAG);
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
    public static RModuleAttribute row2Object(Record row,
                                             int offset,
                                             Class cls)
        throws TorqueException
    {
        try
        {
            RModuleAttribute obj = (RModuleAttribute) cls.newInstance();
            RModuleAttributePeer.populateObject(row, offset, obj);
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
                                      RModuleAttribute obj)
        throws TorqueException
    {
        try
        {
                obj.setModuleId(row.getValue(offset + 0).asIntegerObj());
                  obj.setAttributeId(row.getValue(offset + 1).asIntegerObj());
                  obj.setIssueTypeId(row.getValue(offset + 2).asIntegerObj());
                  obj.setDisplayValue(row.getValue(offset + 3).asString());
                  obj.setActive(row.getValue(offset + 4).asBoolean());
                  obj.setRequired(row.getValue(offset + 5).asBoolean());
                  obj.setOrder(row.getValue(offset + 6).asInt());
                  obj.setQuickSearch(row.getValue(offset + 7).asBoolean());
                  obj.setDefaultTextFlag(row.getValue(offset + 8).asBoolean());
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
        return BaseRModuleAttributePeer
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
        if (criteria.containsKey(ACTIVE))
        {
            Object possibleBoolean = criteria.get(ACTIVE);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(ACTIVE, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  // check for conversion from boolean to int
        if (criteria.containsKey(REQUIRED))
        {
            Object possibleBoolean = criteria.get(REQUIRED);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(REQUIRED, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                        // check for conversion from boolean to int
        if (criteria.containsKey(QUICK_SEARCH))
        {
            Object possibleBoolean = criteria.get(QUICK_SEARCH);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(QUICK_SEARCH, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  // check for conversion from boolean to int
        if (criteria.containsKey(DEFAULT_TEXT_FLAG))
        {
            Object possibleBoolean = criteria.get(DEFAULT_TEXT_FLAG);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(DEFAULT_TEXT_FLAG, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
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
              results.add(RModuleAttributePeer.row2Object(row, 1,
                RModuleAttributePeer.getOMClass()));
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
         BaseRModuleAttributePeer
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
                   selectCriteria.put(MODULE_ID, criteria.remove(MODULE_ID));
                       selectCriteria.put(ATTRIBUTE_ID, criteria.remove(ATTRIBUTE_ID));
                       selectCriteria.put(ISSUE_TYPE_ID, criteria.remove(ISSUE_TYPE_ID));
                            // check for conversion from boolean to int
        if (criteria.containsKey(ACTIVE))
        {
            Object possibleBoolean = criteria.get(ACTIVE);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(ACTIVE, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                      // check for conversion from boolean to int
        if (criteria.containsKey(REQUIRED))
        {
            Object possibleBoolean = criteria.get(REQUIRED);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(REQUIRED, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                                // check for conversion from boolean to int
        if (criteria.containsKey(QUICK_SEARCH))
        {
            Object possibleBoolean = criteria.get(QUICK_SEARCH);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(QUICK_SEARCH, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                      // check for conversion from boolean to int
        if (criteria.containsKey(DEFAULT_TEXT_FLAG))
        {
            Object possibleBoolean = criteria.get(DEFAULT_TEXT_FLAG);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(DEFAULT_TEXT_FLAG, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
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
         RModuleAttributePeer
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
        if (criteria.containsKey(ACTIVE))
        {
            Object possibleBoolean = criteria.get(ACTIVE);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(ACTIVE, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  // check for conversion from boolean to int
        if (criteria.containsKey(REQUIRED))
        {
            Object possibleBoolean = criteria.get(REQUIRED);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(REQUIRED, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                        // check for conversion from boolean to int
        if (criteria.containsKey(QUICK_SEARCH))
        {
            Object possibleBoolean = criteria.get(QUICK_SEARCH);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(QUICK_SEARCH, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  // check for conversion from boolean to int
        if (criteria.containsKey(DEFAULT_TEXT_FLAG))
        {
            Object possibleBoolean = criteria.get(DEFAULT_TEXT_FLAG);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(DEFAULT_TEXT_FLAG, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
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
    public static List doSelect(RModuleAttribute obj) throws TorqueException
    {
        return doSelect(buildSelectCriteria(obj));
    }

    /**
     * Method to do inserts
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doInsert(RModuleAttribute obj) throws TorqueException
    {
          doInsert(buildCriteria(obj));
          obj.setNew(false);
        obj.setModified(false);
    }

    /**
     * @param obj the data object to update in the database.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doUpdate(RModuleAttribute obj) throws TorqueException
    {
        doUpdate(buildCriteria(obj));
        obj.setModified(false);
    }

    /**
     * @param obj the data object to delete in the database.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(RModuleAttribute obj) throws TorqueException
    {
        doDelete(buildSelectCriteria(obj));
    }

    /**
     * Method to do inserts.  This method is to be used during a transaction,
     * otherwise use the doInsert(RModuleAttribute) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to insert into the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doInsert(RModuleAttribute obj, Connection con)
        throws TorqueException
    {
          doInsert(buildCriteria(obj), con);
          obj.setNew(false);
        obj.setModified(false);
    }

    /**
     * Method to do update.  This method is to be used during a transaction,
     * otherwise use the doUpdate(RModuleAttribute) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to update in the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doUpdate(RModuleAttribute obj, Connection con)
        throws TorqueException
    {
        doUpdate(buildCriteria(obj), con);
        obj.setModified(false);
    }

    /**
     * Method to delete.  This method is to be used during a transaction,
     * otherwise use the doDelete(RModuleAttribute) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to delete in the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(RModuleAttribute obj, Connection con)
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
        BaseRModuleAttributePeer
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
          SimpleKey[] keys = (SimpleKey[])pk.getValue();
                    criteria.add(MODULE_ID, keys[0]);
                      criteria.add(ATTRIBUTE_ID, keys[1]);
                      criteria.add(ISSUE_TYPE_ID, keys[2]);
                    return criteria;
     }

    /** Build a Criteria object from the data object for this peer */
    public static Criteria buildCriteria( RModuleAttribute obj )
    {
        Criteria criteria = new Criteria(DATABASE_NAME);
              criteria.add(MODULE_ID, obj.getModuleId());
              criteria.add(ATTRIBUTE_ID, obj.getAttributeId());
              criteria.add(ISSUE_TYPE_ID, obj.getIssueTypeId());
              criteria.add(DISPLAY_VALUE, obj.getDisplayValue());
              criteria.add(ACTIVE, obj.getActive());
              criteria.add(REQUIRED, obj.getRequired());
              criteria.add(PREFERRED_ORDER, obj.getOrder());
              criteria.add(QUICK_SEARCH, obj.getQuickSearch());
              criteria.add(DEFAULT_TEXT_FLAG, obj.getDefaultTextFlag());
          return criteria;
    }

    /** Build a Criteria object from the data object for this peer, skipping all binary columns */
    public static Criteria buildSelectCriteria( RModuleAttribute obj )
    {
        Criteria criteria = new Criteria(DATABASE_NAME);
                      criteria.add(MODULE_ID, obj.getModuleId());
                          criteria.add(ATTRIBUTE_ID, obj.getAttributeId());
                          criteria.add(ISSUE_TYPE_ID, obj.getIssueTypeId());
                          criteria.add(DISPLAY_VALUE, obj.getDisplayValue());
                          criteria.add(ACTIVE, obj.getActive());
                          criteria.add(REQUIRED, obj.getRequired());
                          criteria.add(PREFERRED_ORDER, obj.getOrder());
                          criteria.add(QUICK_SEARCH, obj.getQuickSearch());
                          criteria.add(DEFAULT_TEXT_FLAG, obj.getDefaultTextFlag());
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
    public static RModuleAttribute retrieveByPK(ObjectKey pk)
        throws TorqueException, NoRowsException, TooManyRowsException
    {
        Connection db = null;
        RModuleAttribute retVal = null;
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
    public static RModuleAttribute retrieveByPK(ObjectKey pk, Connection con)
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
            return (RModuleAttribute)v.get(0);
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
              Iterator iter = pks.iterator();
            while (iter.hasNext())
            {
                ObjectKey pk = (ObjectKey)iter.next();
                SimpleKey[] keys = (SimpleKey[])pk.getValue();
                            Criteria.Criterion c0 = criteria.getNewCriterion(
                        MODULE_ID, keys[0], Criteria.EQUAL);
                                    Criteria.Criterion c1 = criteria.getNewCriterion(
                        ATTRIBUTE_ID, keys[1], Criteria.EQUAL);
                                    c0.and(c1);
                              Criteria.Criterion c2 = criteria.getNewCriterion(
                        ISSUE_TYPE_ID, keys[2], Criteria.EQUAL);
                                    c1.and(c2);
                          criteria.or(c0);
            }
          objs = doSelect(criteria, dbcon);
        }
        return objs;
    }

 
    /**
     * retrieve object using using pk values.
     *
       * @param module_id Integer
       * @param attribute_id Integer
       * @param issue_type_id Integer
       */
    public static RModuleAttribute retrieveByPK(
       Integer module_id
          , Integer attribute_id
          , Integer issue_type_id
              ) throws TorqueException
    {
        Connection db = null;
        RModuleAttribute retVal = null;
        try
        {
           db = Torque.getConnection(DATABASE_NAME);
           retVal = retrieveByPK(
         module_id
          , attribute_id
          , issue_type_id
                     , db);
        }
        finally
        {
            Torque.closeConnection(db);
        }
        return(retVal);
    }

      /**
     * retrieve object using using pk values.
     *
       * @param module_id Integer
       * @param attribute_id Integer
       * @param issue_type_id Integer
       * @param con Connection
     */
    public static RModuleAttribute retrieveByPK(
       Integer module_id
          , Integer attribute_id
          , Integer issue_type_id
             ,Connection con) throws TorqueException
    {

        Criteria criteria = new Criteria(5);
          criteria.add(MODULE_ID, module_id);
          criteria.add(ATTRIBUTE_ID, attribute_id);
          criteria.add(ISSUE_TYPE_ID, issue_type_id);
          List v = doSelect(criteria, con);
        if (v.size() != 1)
        {
            throw new TorqueException("Failed to select one and only one row.");
        }
        else
        {
            return (RModuleAttribute) v.get(0);
        }
    }



              
                                              
                
                

    /**
     * selects a collection of RModuleAttribute objects pre-filled with their
     * Attribute objects.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in RModuleAttributePeer.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    protected static List doSelectJoinAttribute(Criteria criteria)
        throws TorqueException
    {
        setDbName(criteria);

        RModuleAttributePeer.addSelectColumns(criteria);
        int offset = numColumns + 1;
        AttributePeer.addSelectColumns(criteria);


                        criteria.addJoin(RModuleAttributePeer.ATTRIBUTE_ID,
            AttributePeer.ATTRIBUTE_ID);
        

                                                                                                  // check for conversion from boolean to int
        if (criteria.containsKey(ACTIVE))
        {
            Object possibleBoolean = criteria.get(ACTIVE);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(ACTIVE, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                                    // check for conversion from boolean to int
        if (criteria.containsKey(REQUIRED))
        {
            Object possibleBoolean = criteria.get(REQUIRED);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(REQUIRED, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                                                      // check for conversion from boolean to int
        if (criteria.containsKey(QUICK_SEARCH))
        {
            Object possibleBoolean = criteria.get(QUICK_SEARCH);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(QUICK_SEARCH, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                                    // check for conversion from boolean to int
        if (criteria.containsKey(DEFAULT_TEXT_FLAG))
        {
            Object possibleBoolean = criteria.get(DEFAULT_TEXT_FLAG);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(DEFAULT_TEXT_FLAG, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  
        List rows = BasePeer.doSelect(criteria);
        List results = new ArrayList();

        for (int i = 0; i < rows.size(); i++)
        {
            Record row = (Record) rows.get(i);

                            Class omClass = RModuleAttributePeer.getOMClass();
                    RModuleAttribute obj1 = (RModuleAttribute) RModuleAttributePeer
                .row2Object(row, 1, omClass);
                     omClass = AttributePeer.getOMClass();
                    Attribute obj2 = (Attribute)AttributePeer
                .row2Object(row, offset, omClass);

            boolean newObject = true;
            for (int j = 0; j < results.size(); j++)
            {
                RModuleAttribute temp_obj1 = (RModuleAttribute)results.get(j);
                Attribute temp_obj2 = (Attribute)temp_obj1.getAttribute();
                if (temp_obj2.getPrimaryKey().equals(obj2.getPrimaryKey()))
                {
                    newObject = false;
                              temp_obj2.addRModuleAttribute(obj1);
                              break;
                }
            }
                      if (newObject)
            {
                obj2.initRModuleAttributes();
                obj2.addRModuleAttribute(obj1);
            }
                      results.add(obj1);
        }
        return results;
    }
                                                            
                        
                

    /**
     * selects a collection of RModuleAttribute objects pre-filled with their
     * ScarabModule objects.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in RModuleAttributePeer.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    protected static List doSelectJoinScarabModule(Criteria criteria)
        throws TorqueException
    {
        setDbName(criteria);

        RModuleAttributePeer.addSelectColumns(criteria);
        int offset = numColumns + 1;
        ScarabModulePeer.addSelectColumns(criteria);


                        criteria.addJoin(RModuleAttributePeer.MODULE_ID,
            ScarabModulePeer.MODULE_ID);
        

                                                                                                  // check for conversion from boolean to int
        if (criteria.containsKey(ACTIVE))
        {
            Object possibleBoolean = criteria.get(ACTIVE);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(ACTIVE, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                                    // check for conversion from boolean to int
        if (criteria.containsKey(REQUIRED))
        {
            Object possibleBoolean = criteria.get(REQUIRED);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(REQUIRED, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                                                      // check for conversion from boolean to int
        if (criteria.containsKey(QUICK_SEARCH))
        {
            Object possibleBoolean = criteria.get(QUICK_SEARCH);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(QUICK_SEARCH, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                                    // check for conversion from boolean to int
        if (criteria.containsKey(DEFAULT_TEXT_FLAG))
        {
            Object possibleBoolean = criteria.get(DEFAULT_TEXT_FLAG);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(DEFAULT_TEXT_FLAG, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  
        List rows = BasePeer.doSelect(criteria);
        List results = new ArrayList();

        for (int i = 0; i < rows.size(); i++)
        {
            Record row = (Record) rows.get(i);

                            Class omClass = RModuleAttributePeer.getOMClass();
                    RModuleAttribute obj1 = (RModuleAttribute) RModuleAttributePeer
                .row2Object(row, 1, omClass);
                     omClass = ScarabModulePeer.getOMClass(row, offset);
                    ScarabModule obj2 = (ScarabModule)ScarabModulePeer
                .row2Object(row, offset, omClass);

            boolean newObject = true;
            for (int j = 0; j < results.size(); j++)
            {
                RModuleAttribute temp_obj1 = (RModuleAttribute)results.get(j);
                ScarabModule temp_obj2 = (ScarabModule)temp_obj1.getModule();
                if (temp_obj2.getPrimaryKey().equals(obj2.getPrimaryKey()))
                {
                    newObject = false;
                              temp_obj2.addRModuleAttribute(obj1);
                              break;
                }
            }
                      if (newObject)
            {
                obj2.initRModuleAttributes();
                obj2.addRModuleAttribute(obj1);
            }
                      results.add(obj1);
        }
        return results;
    }
                                                            
                
                

    /**
     * selects a collection of RModuleAttribute objects pre-filled with their
     * IssueType objects.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in RModuleAttributePeer.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    protected static List doSelectJoinIssueType(Criteria criteria)
        throws TorqueException
    {
        setDbName(criteria);

        RModuleAttributePeer.addSelectColumns(criteria);
        int offset = numColumns + 1;
        IssueTypePeer.addSelectColumns(criteria);


                        criteria.addJoin(RModuleAttributePeer.ISSUE_TYPE_ID,
            IssueTypePeer.ISSUE_TYPE_ID);
        

                                                                                                  // check for conversion from boolean to int
        if (criteria.containsKey(ACTIVE))
        {
            Object possibleBoolean = criteria.get(ACTIVE);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(ACTIVE, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                                    // check for conversion from boolean to int
        if (criteria.containsKey(REQUIRED))
        {
            Object possibleBoolean = criteria.get(REQUIRED);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(REQUIRED, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                                                      // check for conversion from boolean to int
        if (criteria.containsKey(QUICK_SEARCH))
        {
            Object possibleBoolean = criteria.get(QUICK_SEARCH);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(QUICK_SEARCH, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                                    // check for conversion from boolean to int
        if (criteria.containsKey(DEFAULT_TEXT_FLAG))
        {
            Object possibleBoolean = criteria.get(DEFAULT_TEXT_FLAG);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(DEFAULT_TEXT_FLAG, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  
        List rows = BasePeer.doSelect(criteria);
        List results = new ArrayList();

        for (int i = 0; i < rows.size(); i++)
        {
            Record row = (Record) rows.get(i);

                            Class omClass = RModuleAttributePeer.getOMClass();
                    RModuleAttribute obj1 = (RModuleAttribute) RModuleAttributePeer
                .row2Object(row, 1, omClass);
                     omClass = IssueTypePeer.getOMClass();
                    IssueType obj2 = (IssueType)IssueTypePeer
                .row2Object(row, offset, omClass);

            boolean newObject = true;
            for (int j = 0; j < results.size(); j++)
            {
                RModuleAttribute temp_obj1 = (RModuleAttribute)results.get(j);
                IssueType temp_obj2 = (IssueType)temp_obj1.getIssueType();
                if (temp_obj2.getPrimaryKey().equals(obj2.getPrimaryKey()))
                {
                    newObject = false;
                              temp_obj2.addRModuleAttribute(obj1);
                              break;
                }
            }
                      if (newObject)
            {
                obj2.initRModuleAttributes();
                obj2.addRModuleAttribute(obj1);
            }
                      results.add(obj1);
        }
        return results;
    }
                    
  
                                    
          
        
                                  
                

    /**
     * selects a collection of RModuleAttribute objects pre-filled with
     * all related objects.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in RModuleAttributePeer.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    protected static List doSelectJoinAllExceptAttribute(Criteria criteria)
        throws TorqueException
    {
        setDbName(criteria);

        addSelectColumns(criteria);
        int offset2 = numColumns + 1;
                                    
                                                  
                    ScarabModulePeer.addSelectColumns(criteria);
        int offset3 = offset2 + ScarabModulePeer.numColumns;
                                                                
                    IssueTypePeer.addSelectColumns(criteria);
        int offset4 = offset3 + IssueTypePeer.numColumns;
                                                                                                                                              // check for conversion from boolean to int
        if (criteria.containsKey(ACTIVE))
        {
            Object possibleBoolean = criteria.get(ACTIVE);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(ACTIVE, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                                    // check for conversion from boolean to int
        if (criteria.containsKey(REQUIRED))
        {
            Object possibleBoolean = criteria.get(REQUIRED);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(REQUIRED, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                                                      // check for conversion from boolean to int
        if (criteria.containsKey(QUICK_SEARCH))
        {
            Object possibleBoolean = criteria.get(QUICK_SEARCH);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(QUICK_SEARCH, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                                    // check for conversion from boolean to int
        if (criteria.containsKey(DEFAULT_TEXT_FLAG))
        {
            Object possibleBoolean = criteria.get(DEFAULT_TEXT_FLAG);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(DEFAULT_TEXT_FLAG, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  
        List rows = BasePeer.doSelect(criteria);
        List results = new ArrayList();

        for (int i = 0; i < rows.size(); i++)
        {
            Record row = (Record)rows.get(i);

                            Class omClass = RModuleAttributePeer.getOMClass();
                    RModuleAttribute obj1 = (RModuleAttribute)RModuleAttributePeer
                .row2Object(row, 1, omClass);
                                                
                                                                              
                                                        
                            
              
                           omClass = ScarabModulePeer.getOMClass(row, offset2);
                          ScarabModule obj2 = (ScarabModule)ScarabModulePeer
                .row2Object( row, offset2, omClass);

               boolean  newObject = true;
            for (int j = 0; j < results.size(); j++)
            {
                RModuleAttribute temp_obj1 = (RModuleAttribute)results.get(j);
                ScarabModule temp_obj2 = (ScarabModule)temp_obj1.getModule();
                if (temp_obj2.getPrimaryKey().equals(obj2.getPrimaryKey()))
                {
                    newObject = false;
                                    temp_obj2.addRModuleAttribute(obj1);
                                    break;
                }
            }
                            if (newObject)
            {
                obj2.initRModuleAttributes();
                obj2.addRModuleAttribute(obj1);
            }
                                                                                    
                                                        
                            
              
                           omClass = IssueTypePeer.getOMClass();
                          IssueType obj3 = (IssueType)IssueTypePeer
                .row2Object( row, offset3, omClass);

               newObject = true;
            for (int j = 0; j < results.size(); j++)
            {
                RModuleAttribute temp_obj1 = (RModuleAttribute)results.get(j);
                IssueType temp_obj3 = (IssueType)temp_obj1.getIssueType();
                if (temp_obj3.getPrimaryKey().equals(obj3.getPrimaryKey()))
                {
                    newObject = false;
                                    temp_obj3.addRModuleAttribute(obj1);
                                    break;
                }
            }
                            if (newObject)
            {
                obj3.initRModuleAttributes();
                obj3.addRModuleAttribute(obj1);
            }
                                                                results.add(obj1);
        }
        return results;
    }
        
        
                                  
                

    /**
     * selects a collection of RModuleAttribute objects pre-filled with
     * all related objects.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in RModuleAttributePeer.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    protected static List doSelectJoinAllExceptScarabModule(Criteria criteria)
        throws TorqueException
    {
        setDbName(criteria);

        addSelectColumns(criteria);
        int offset2 = numColumns + 1;
                                    
                    AttributePeer.addSelectColumns(criteria);
        int offset3 = offset2 + AttributePeer.numColumns;
                                                                
                                                  
                    IssueTypePeer.addSelectColumns(criteria);
        int offset4 = offset3 + IssueTypePeer.numColumns;
                                                                                                                                              // check for conversion from boolean to int
        if (criteria.containsKey(ACTIVE))
        {
            Object possibleBoolean = criteria.get(ACTIVE);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(ACTIVE, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                                    // check for conversion from boolean to int
        if (criteria.containsKey(REQUIRED))
        {
            Object possibleBoolean = criteria.get(REQUIRED);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(REQUIRED, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                                                      // check for conversion from boolean to int
        if (criteria.containsKey(QUICK_SEARCH))
        {
            Object possibleBoolean = criteria.get(QUICK_SEARCH);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(QUICK_SEARCH, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                                    // check for conversion from boolean to int
        if (criteria.containsKey(DEFAULT_TEXT_FLAG))
        {
            Object possibleBoolean = criteria.get(DEFAULT_TEXT_FLAG);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(DEFAULT_TEXT_FLAG, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  
        List rows = BasePeer.doSelect(criteria);
        List results = new ArrayList();

        for (int i = 0; i < rows.size(); i++)
        {
            Record row = (Record)rows.get(i);

                            Class omClass = RModuleAttributePeer.getOMClass();
                    RModuleAttribute obj1 = (RModuleAttribute)RModuleAttributePeer
                .row2Object(row, 1, omClass);
                                                
                                                        
                            
              
                           omClass = AttributePeer.getOMClass();
                          Attribute obj2 = (Attribute)AttributePeer
                .row2Object( row, offset2, omClass);

               boolean  newObject = true;
            for (int j = 0; j < results.size(); j++)
            {
                RModuleAttribute temp_obj1 = (RModuleAttribute)results.get(j);
                Attribute temp_obj2 = (Attribute)temp_obj1.getAttribute();
                if (temp_obj2.getPrimaryKey().equals(obj2.getPrimaryKey()))
                {
                    newObject = false;
                                    temp_obj2.addRModuleAttribute(obj1);
                                    break;
                }
            }
                            if (newObject)
            {
                obj2.initRModuleAttributes();
                obj2.addRModuleAttribute(obj1);
            }
                                                                                                
                                                                  
                                                        
                            
              
                           omClass = IssueTypePeer.getOMClass();
                          IssueType obj3 = (IssueType)IssueTypePeer
                .row2Object( row, offset3, omClass);

               newObject = true;
            for (int j = 0; j < results.size(); j++)
            {
                RModuleAttribute temp_obj1 = (RModuleAttribute)results.get(j);
                IssueType temp_obj3 = (IssueType)temp_obj1.getIssueType();
                if (temp_obj3.getPrimaryKey().equals(obj3.getPrimaryKey()))
                {
                    newObject = false;
                                    temp_obj3.addRModuleAttribute(obj1);
                                    break;
                }
            }
                            if (newObject)
            {
                obj3.initRModuleAttributes();
                obj3.addRModuleAttribute(obj1);
            }
                                                                results.add(obj1);
        }
        return results;
    }
        
        
                                  
                

    /**
     * selects a collection of RModuleAttribute objects pre-filled with
     * all related objects.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in RModuleAttributePeer.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    protected static List doSelectJoinAllExceptIssueType(Criteria criteria)
        throws TorqueException
    {
        setDbName(criteria);

        addSelectColumns(criteria);
        int offset2 = numColumns + 1;
                                    
                    AttributePeer.addSelectColumns(criteria);
        int offset3 = offset2 + AttributePeer.numColumns;
                                                                
                    ScarabModulePeer.addSelectColumns(criteria);
        int offset4 = offset3 + ScarabModulePeer.numColumns;
                                                                
                                                                                                                                // check for conversion from boolean to int
        if (criteria.containsKey(ACTIVE))
        {
            Object possibleBoolean = criteria.get(ACTIVE);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(ACTIVE, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                                    // check for conversion from boolean to int
        if (criteria.containsKey(REQUIRED))
        {
            Object possibleBoolean = criteria.get(REQUIRED);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(REQUIRED, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                                                      // check for conversion from boolean to int
        if (criteria.containsKey(QUICK_SEARCH))
        {
            Object possibleBoolean = criteria.get(QUICK_SEARCH);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(QUICK_SEARCH, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                                    // check for conversion from boolean to int
        if (criteria.containsKey(DEFAULT_TEXT_FLAG))
        {
            Object possibleBoolean = criteria.get(DEFAULT_TEXT_FLAG);
            if (possibleBoolean instanceof Boolean)
            {
                criteria.add(DEFAULT_TEXT_FLAG, ((Boolean) possibleBoolean).booleanValue() ? 1 : 0);
            }
         }
                  
        List rows = BasePeer.doSelect(criteria);
        List results = new ArrayList();

        for (int i = 0; i < rows.size(); i++)
        {
            Record row = (Record)rows.get(i);

                            Class omClass = RModuleAttributePeer.getOMClass();
                    RModuleAttribute obj1 = (RModuleAttribute)RModuleAttributePeer
                .row2Object(row, 1, omClass);
                                                
                                                        
                            
              
                           omClass = AttributePeer.getOMClass();
                          Attribute obj2 = (Attribute)AttributePeer
                .row2Object( row, offset2, omClass);

               boolean  newObject = true;
            for (int j = 0; j < results.size(); j++)
            {
                RModuleAttribute temp_obj1 = (RModuleAttribute)results.get(j);
                Attribute temp_obj2 = (Attribute)temp_obj1.getAttribute();
                if (temp_obj2.getPrimaryKey().equals(obj2.getPrimaryKey()))
                {
                    newObject = false;
                                    temp_obj2.addRModuleAttribute(obj1);
                                    break;
                }
            }
                            if (newObject)
            {
                obj2.initRModuleAttributes();
                obj2.addRModuleAttribute(obj1);
            }
                                                                                                
                                                        
                            
              
                           omClass = ScarabModulePeer.getOMClass(row, offset3);
                          ScarabModule obj3 = (ScarabModule)ScarabModulePeer
                .row2Object( row, offset3, omClass);

               newObject = true;
            for (int j = 0; j < results.size(); j++)
            {
                RModuleAttribute temp_obj1 = (RModuleAttribute)results.get(j);
                ScarabModule temp_obj3 = (ScarabModule)temp_obj1.getModule();
                if (temp_obj3.getPrimaryKey().equals(obj3.getPrimaryKey()))
                {
                    newObject = false;
                                    temp_obj3.addRModuleAttribute(obj1);
                                    break;
                }
            }
                            if (newObject)
            {
                obj3.initRModuleAttributes();
                obj3.addRModuleAttribute(obj1);
            }
                                                                                    
                                              results.add(obj1);
        }
        return results;
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
