package org.tigris.scarab.om;


import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.fulcrum.intake.Retrievable;
import org.apache.torque.TorqueException;
import org.apache.torque.om.BaseObject;
import org.apache.torque.om.ComboKey;
import org.apache.torque.om.DateKey;
import org.apache.torque.om.NumberKey;
import org.apache.torque.om.ObjectKey;
import org.apache.torque.om.SimpleKey;
import org.apache.torque.om.StringKey;
import org.apache.torque.om.Persistent;
import org.apache.torque.util.Criteria;
import org.apache.torque.util.Transaction;


/**
 * You should not use this class directly.  It should not even be
 * extended all references should be to AttachmentType
 */
public abstract class BaseAttachmentType extends BaseObject
    implements org.apache.fulcrum.intake.Retrievable
{
    /** The Peer class */
    private static final AttachmentTypePeer peer =
        new AttachmentTypePeer();

        
    /** The value for the attachmentTypeId field */
    private Integer attachmentTypeId;
      
    /** The value for the name field */
    private String name;
                                                                
    /** The value for the searchable field */
    private boolean searchable = false;
  
    
    /**
     * Get the AttachmentTypeId
     *
     * @return Integer
     */
    public Integer getAttachmentTypeId()
    {
        return attachmentTypeId;
    }

                                              
    /**
     * Set the value of AttachmentTypeId
     *
     * @param v new value
     */
    public void setAttachmentTypeId(Integer v) throws TorqueException
    {
    
                  if (!ObjectUtils.equals(this.attachmentTypeId, v))
              {
            this.attachmentTypeId = v;
            setModified(true);
        }
    
          
                                  
        // update associated Attachment
        if (collAttachments != null)
        {
            for (int i = 0; i < collAttachments.size(); i++)
            {
                ((Attachment) collAttachments.get(i))
                        .setTypeId(v);
            }
        }
                      }
  
    /**
     * Get the Name
     *
     * @return String
     */
    public String getName()
    {
        return name;
    }

                        
    /**
     * Set the value of Name
     *
     * @param v new value
     */
    public void setName(String v) 
    {
    
                  if (!ObjectUtils.equals(this.name, v))
              {
            this.name = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the Searchable
     *
     * @return boolean
     */
    public boolean getSearchable()
    {
        return searchable;
    }

                        
    /**
     * Set the value of Searchable
     *
     * @param v new value
     */
    public void setSearchable(boolean v) 
    {
    
                  if (this.searchable != v)
              {
            this.searchable = v;
            setModified(true);
        }
    
          
              }
  
         
                                
            
    /**
     * Collection to store aggregation of collAttachments
     */
    protected List collAttachments;

    /**
     * Temporary storage of collAttachments to save a possible db hit in
     * the event objects are add to the collection, but the
     * complete collection is never requested.
     */
    protected void initAttachments()
    {
        if (collAttachments == null)
        {
            collAttachments = new ArrayList();
        }
    }

            
    /**
     * Method called to associate a Attachment object to this object
     * through the Attachment foreign key attribute
     *
     * @param l Attachment
     * @throws TorqueException
     */
    public void addAttachment(Attachment l) throws TorqueException
    {
        getAttachments().add(l);
        l.setAttachmentType((AttachmentType)this);
    }

    /**
     * The criteria used to select the current contents of collAttachments
     */
    private Criteria lastAttachmentsCriteria = null;

    /**
     * If this collection has already been initialized, returns
     * the collection. Otherwise returns the results of
     * getAttachments(new Criteria())
     *
     * @throws TorqueException
     */
    public List getAttachments() throws TorqueException
    {
        if (collAttachments == null)
        {
            collAttachments = getAttachments(new Criteria(10));
        }
        return collAttachments;
    }

    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this AttachmentType has previously
     * been saved, it will retrieve related Attachments from storage.
     * If this AttachmentType is new, it will return
     * an empty collection or the current collection, the criteria
     * is ignored on a new object.
     *
     * @throws TorqueException
     */
    public List getAttachments(Criteria criteria) throws TorqueException
    {
        if (collAttachments == null)
        {
            if (isNew())
            {
               collAttachments = new ArrayList();
            }
            else
            {
                      criteria.add(AttachmentPeer.ATTACHMENT_TYPE_ID, getAttachmentTypeId() );
                      collAttachments = AttachmentPeer.doSelect(criteria);
            }
        }
        else
        {
            // criteria has no effect for a new object
            if (!isNew())
            {
                // the following code is to determine if a new query is
                // called for.  If the criteria is the same as the last
                // one, just return the collection.
                      criteria.add(AttachmentPeer.ATTACHMENT_TYPE_ID, getAttachmentTypeId() );
                      if (!lastAttachmentsCriteria.equals(criteria))
                {
                    collAttachments = AttachmentPeer.doSelect(criteria);
                }
            }
        }
        lastAttachmentsCriteria = criteria;

        return collAttachments;
    }

    /**
     * If this collection has already been initialized, returns
     * the collection. Otherwise returns the results of
     * getAttachments(new Criteria(),Connection)
     * This method takes in the Connection also as input so that
     * referenced objects can also be obtained using a Connection
     * that is taken as input
     */
    public List getAttachments(Connection con) throws TorqueException
    {
        if (collAttachments == null)
        {
            collAttachments = getAttachments(new Criteria(10),con);
        }
        return collAttachments;
    }

    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this AttachmentType has previously
     * been saved, it will retrieve related Attachments from storage.
     * If this AttachmentType is new, it will return
     * an empty collection or the current collection, the criteria
     * is ignored on a new object.
     * This method takes in the Connection also as input so that
     * referenced objects can also be obtained using a Connection
     * that is taken as input
     */
    public List getAttachments(Criteria criteria,Connection con) throws TorqueException
    {
        if (collAttachments == null)
        {
            if (isNew())
            {
               collAttachments = new ArrayList();
            }
            else
            {
                       criteria.add(AttachmentPeer.ATTACHMENT_TYPE_ID, getAttachmentTypeId() );
                       collAttachments = AttachmentPeer.doSelect(criteria,con);
             }
         }
         else
         {
             // criteria has no effect for a new object
             if (!isNew())
             {
                 // the following code is to determine if a new query is
                 // called for.  If the criteria is the same as the last
                 // one, just return the collection.
                     criteria.add(AttachmentPeer.ATTACHMENT_TYPE_ID, getAttachmentTypeId() );
                     if (!lastAttachmentsCriteria.equals(criteria))
                 {
                     collAttachments = AttachmentPeer.doSelect(criteria,con);
                 }
             }
         }
         lastAttachmentsCriteria = criteria;

         return collAttachments;
     }

                                    
              
                    
                    
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this AttachmentType is new, it will return
     * an empty collection; or if this AttachmentType has previously
     * been saved, it will retrieve related Attachments from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in AttachmentType.
     */
    protected List getAttachmentsJoinIssue(Criteria criteria)
        throws TorqueException
    {
        if (collAttachments == null)
        {
            if (isNew())
            {
               collAttachments = new ArrayList();
            }
            else
            {
                            criteria.add(AttachmentPeer.ATTACHMENT_TYPE_ID, getAttachmentTypeId() );
                            collAttachments = AttachmentPeer.doSelectJoinIssue(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.

                            criteria.add(AttachmentPeer.ATTACHMENT_TYPE_ID, getAttachmentTypeId() );
                        if (!lastAttachmentsCriteria.equals(criteria))
            {
                collAttachments = AttachmentPeer.doSelectJoinIssue(criteria);
            }
        }
        lastAttachmentsCriteria = criteria;

        return collAttachments;
    }
                  
                    
                              
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this AttachmentType is new, it will return
     * an empty collection; or if this AttachmentType has previously
     * been saved, it will retrieve related Attachments from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in AttachmentType.
     */
    protected List getAttachmentsJoinAttachmentType(Criteria criteria)
        throws TorqueException
    {
        if (collAttachments == null)
        {
            if (isNew())
            {
               collAttachments = new ArrayList();
            }
            else
            {
                            criteria.add(AttachmentPeer.ATTACHMENT_TYPE_ID, getAttachmentTypeId() );
                            collAttachments = AttachmentPeer.doSelectJoinAttachmentType(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.

                            criteria.add(AttachmentPeer.ATTACHMENT_TYPE_ID, getAttachmentTypeId() );
                        if (!lastAttachmentsCriteria.equals(criteria))
            {
                collAttachments = AttachmentPeer.doSelectJoinAttachmentType(criteria);
            }
        }
        lastAttachmentsCriteria = criteria;

        return collAttachments;
    }
                  
                    
                    
                                            
                                                                          
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this AttachmentType is new, it will return
     * an empty collection; or if this AttachmentType has previously
     * been saved, it will retrieve related Attachments from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in AttachmentType.
     */
    protected List getAttachmentsJoinScarabUserImplRelatedByCreatedBy(Criteria criteria)
        throws TorqueException
    {
        if (collAttachments == null)
        {
            if (isNew())
            {
               collAttachments = new ArrayList();
            }
            else
            {
                            criteria.add(AttachmentPeer.ATTACHMENT_TYPE_ID, getAttachmentTypeId() );
                            collAttachments = AttachmentPeer.doSelectJoinScarabUserImplRelatedByCreatedBy(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.

                            criteria.add(AttachmentPeer.ATTACHMENT_TYPE_ID, getAttachmentTypeId() );
                        if (!lastAttachmentsCriteria.equals(criteria))
            {
                collAttachments = AttachmentPeer.doSelectJoinScarabUserImplRelatedByCreatedBy(criteria);
            }
        }
        lastAttachmentsCriteria = criteria;

        return collAttachments;
    }
                  
                    
                    
                                            
                                                                          
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this AttachmentType is new, it will return
     * an empty collection; or if this AttachmentType has previously
     * been saved, it will retrieve related Attachments from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in AttachmentType.
     */
    protected List getAttachmentsJoinScarabUserImplRelatedByModifiedBy(Criteria criteria)
        throws TorqueException
    {
        if (collAttachments == null)
        {
            if (isNew())
            {
               collAttachments = new ArrayList();
            }
            else
            {
                            criteria.add(AttachmentPeer.ATTACHMENT_TYPE_ID, getAttachmentTypeId() );
                            collAttachments = AttachmentPeer.doSelectJoinScarabUserImplRelatedByModifiedBy(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.

                            criteria.add(AttachmentPeer.ATTACHMENT_TYPE_ID, getAttachmentTypeId() );
                        if (!lastAttachmentsCriteria.equals(criteria))
            {
                collAttachments = AttachmentPeer.doSelectJoinScarabUserImplRelatedByModifiedBy(criteria);
            }
        }
        lastAttachmentsCriteria = criteria;

        return collAttachments;
    }
                            


          
    private static List fieldNames = null;

    /**
     * Generate a list of field names.
     *
     * @return a list of field names
     */
    public static synchronized List getFieldNames()
    {
        if (fieldNames == null)
        {
            fieldNames = new ArrayList();
              fieldNames.add("AttachmentTypeId");
              fieldNames.add("Name");
              fieldNames.add("Searchable");
              fieldNames = Collections.unmodifiableList(fieldNames);
        }
        return fieldNames;
    }

    /**
     * Retrieves a field from the object by name passed in as a String.
     *
     * @param name field name
     * @return value
     */
    public Object getByName(String name)
    {
          if (name.equals("AttachmentTypeId"))
        {
                return getAttachmentTypeId();
            }
          if (name.equals("Name"))
        {
                return getName();
            }
          if (name.equals("Searchable"))
        {
                return Boolean.valueOf(getSearchable());
            }
          return null;
    }
    
    /**
     * Retrieves a field from the object by name passed in
     * as a String.  The String must be one of the static
     * Strings defined in this Class' Peer.
     *
     * @param name peer name
     * @return value
     */
    public Object getByPeerName(String name)
    {
          if (name.equals(AttachmentTypePeer.ATTACHMENT_TYPE_ID))
        {
                return getAttachmentTypeId();
            }
          if (name.equals(AttachmentTypePeer.ATTACHMENT_TYPE_NAME))
        {
                return getName();
            }
          if (name.equals(AttachmentTypePeer.SEARCHABLE))
        {
                return Boolean.valueOf(getSearchable());
            }
          return null;
    }

    /**
     * Retrieves a field from the object by Position as specified
     * in the xml schema.  Zero-based.
     *
     * @param pos position in xml schema
     * @return value
     */
    public Object getByPosition(int pos)
    {
            if (pos == 0)
        {
                return getAttachmentTypeId();
            }
              if (pos == 1)
        {
                return getName();
            }
              if (pos == 2)
        {
                return Boolean.valueOf(getSearchable());
            }
              return null;
    }
     
    /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.
     *
     * @throws Exception
     */
    public void save() throws Exception
    {
          save(AttachmentTypePeer.getMapBuilder()
                .getDatabaseMap().getName());
      }

    /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.
       * Note: this code is here because the method body is
     * auto-generated conditionally and therefore needs to be
     * in this file instead of in the super class, BaseObject.
       *
     * @param dbName
     * @throws TorqueException
     */
    public void save(String dbName) throws TorqueException
    {
        Connection con = null;
          try
        {
            con = Transaction.begin(dbName);
            save(con);
            Transaction.commit(con);
        }
        catch(TorqueException e)
        {
            Transaction.safeRollback(con);
            throw e;
        }
      }

      /** flag to prevent endless save loop, if this object is referenced
        by another object which falls in this transaction. */
    private boolean alreadyInSave = false;
      /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.  This method
     * is meant to be used as part of a transaction, otherwise use
     * the save() method and the connection details will be handled
     * internally
     *
     * @param con
     * @throws TorqueException
     */
    public void save(Connection con) throws TorqueException
    {
          if (!alreadyInSave)
        {
            alreadyInSave = true;


  
            // If this object has been modified, then save it to the database.
            if (isModified())
            {
                if (isNew())
                {
                    AttachmentTypePeer.doInsert((AttachmentType)this, con);
                    setNew(false);
                }
                else
                {
                    AttachmentTypePeer.doUpdate((AttachmentType)this, con);
                }

                      if (isCacheOnSave())
                {
                    AttachmentTypeManager.putInstance(this);
                }
              }

                                      
                            if (collAttachments != null)
            {
                for (int i = 0; i < collAttachments.size(); i++)
                {
                    ((Attachment)collAttachments.get(i)).save(con);
                }
            }
                          alreadyInSave = false;
        }
      }

    /**
     * Specify whether to cache the object after saving to the db.
     * This method returns false
     */
    protected boolean isCacheOnSave()
    {
        return true;
    }

                        
      /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param attachmentTypeId ObjectKey
     */
    public void setPrimaryKey(ObjectKey attachmentTypeId)
        throws TorqueException {
            setAttachmentTypeId(new Integer(((NumberKey)attachmentTypeId).intValue()));
        }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) throws TorqueException
    {
            setAttachmentTypeId(new Integer(key));
        }

  
    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
          return SimpleKey.keyFor(getAttachmentTypeId());
      }
 
    /**
     * get an id that differentiates this object from others
     * of its class.
     */
    public String getQueryKey()
    {
        if (getPrimaryKey() == null)
        {
            return "";
        }
        else
        {
            return getPrimaryKey().toString();
        }
    }

    /**
     * set an id that differentiates this object from others
     * of its class.
     */
    public void setQueryKey(String key)
        throws TorqueException
    {
        setPrimaryKey(key);
    }

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
       * It then fills all the association collections and sets the
     * related objects to isNew=true.
       */
      public AttachmentType copy() throws TorqueException
    {
        AttachmentType copyObj = new AttachmentType();
            copyObj.setAttachmentTypeId(attachmentTypeId);
          copyObj.setName(name);
          copyObj.setSearchable(searchable);
  
                      copyObj.setAttachmentTypeId((Integer)null);
                        
                                      
                
        List v = getAttachments();
        for (int i = 0; i < v.size(); i++)
        {
            Attachment obj = (Attachment) v.get(i);
            copyObj.addAttachment(obj.copy());
        }
                            return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public AttachmentTypePeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("AttachmentType:\n");
        str.append("AttachmentTypeId = ")
               .append(getAttachmentTypeId())
             .append("\n");
        str.append("Name = ")
               .append(getName())
             .append("\n");
        str.append("Searchable = ")
               .append(getSearchable())
             .append("\n");
        return(str.toString());
    }
}
