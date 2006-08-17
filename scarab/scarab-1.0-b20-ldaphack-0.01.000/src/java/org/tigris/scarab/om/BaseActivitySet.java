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
 * extended all references should be to ActivitySet
 */
public abstract class BaseActivitySet extends BaseObject
    implements org.apache.fulcrum.intake.Retrievable
{
    /** The Peer class */
    private static final ActivitySetPeer peer =
        new ActivitySetPeer();

        
    /** The value for the activitySetId field */
    private Long activitySetId;
      
    /** The value for the typeId field */
    private Integer typeId;
      
    /** The value for the attachmentId field */
    private Long attachmentId;
      
    /** The value for the createdBy field */
    private Integer createdBy;
      
    /** The value for the createdDate field */
    private Date createdDate;
  
    
    /**
     * Get the ActivitySetId
     *
     * @return Long
     */
    public Long getActivitySetId()
    {
        return activitySetId;
    }

                                              
    /**
     * Set the value of ActivitySetId
     *
     * @param v new value
     */
    public void setActivitySetId(Long v) throws TorqueException
    {
    
                  if (!ObjectUtils.equals(this.activitySetId, v))
              {
            this.activitySetId = v;
            setModified(true);
        }
    
          
                                  
        // update associated Activity
        if (collActivitys != null)
        {
            for (int i = 0; i < collActivitys.size(); i++)
            {
                ((Activity) collActivitys.get(i))
                        .setTransactionId(v);
            }
        }
                                          
        // update associated Issue
        if (collIssues != null)
        {
            for (int i = 0; i < collIssues.size(); i++)
            {
                ((Issue) collIssues.get(i))
                        .setCreatedTransId(v);
            }
        }
                      }
  
    /**
     * Get the TypeId
     *
     * @return Integer
     */
    public Integer getTypeId()
    {
        return typeId;
    }

                              
    /**
     * Set the value of TypeId
     *
     * @param v new value
     */
    public void setTypeId(Integer v) throws TorqueException
    {
    
                  if (!ObjectUtils.equals(this.typeId, v))
              {
            this.typeId = v;
            setModified(true);
        }
    
                                  
                if (aActivitySetType != null && !ObjectUtils.equals(aActivitySetType.getTypeId(), v))
                {
            aActivitySetType = null;
        }
      
              }
  
    /**
     * Get the AttachmentId
     *
     * @return Long
     */
    public Long getAttachmentId()
    {
        return attachmentId;
    }

                              
    /**
     * Set the value of AttachmentId
     *
     * @param v new value
     */
    public void setAttachmentId(Long v) throws TorqueException
    {
    
                  if (!ObjectUtils.equals(this.attachmentId, v))
              {
            this.attachmentId = v;
            setModified(true);
        }
    
                                  
                if (aAttachment != null && !ObjectUtils.equals(aAttachment.getAttachmentId(), v))
                {
            aAttachment = null;
        }
      
              }
  
    /**
     * Get the CreatedBy
     *
     * @return Integer
     */
    public Integer getCreatedBy()
    {
        return createdBy;
    }

                              
    /**
     * Set the value of CreatedBy
     *
     * @param v new value
     */
    public void setCreatedBy(Integer v) throws TorqueException
    {
    
                  if (!ObjectUtils.equals(this.createdBy, v))
              {
            this.createdBy = v;
            setModified(true);
        }
    
                                          
                if (aScarabUser != null && !ObjectUtils.equals(aScarabUser.getUserId(), v))
                {
            aScarabUser = null;
        }
      
              }
  
    /**
     * Get the CreatedDate
     *
     * @return Date
     */
    public Date getCreatedDate()
    {
        return createdDate;
    }

                        
    /**
     * Set the value of CreatedDate
     *
     * @param v new value
     */
    public void setCreatedDate(Date v) 
    {
    
                  if (!ObjectUtils.equals(this.createdDate, v))
              {
            this.createdDate = v;
            setModified(true);
        }
    
          
              }
  
      
            
                  
        private ScarabUser aScarabUser;

    /**
     * Declares an association between this object and a ScarabUser object
     *
     * @param v ScarabUser
     * @throws TorqueException
     */
    public void setScarabUser(ScarabUser v) throws TorqueException
    {
            if (v == null)
        {
                  setCreatedBy((Integer) null);
              }
        else
        {
            setCreatedBy(v.getUserId());
        }
            aScarabUser = v;
    }

                        
    /**
     * Get the associated ScarabUser object
     *
     * @return the associated ScarabUser object
     * @throws TorqueException
     */
    public ScarabUser getScarabUser() throws TorqueException
    {
        if ( !ObjectUtils.equals(getCreatedBy(), null) )
        {
                return ScarabUserManager.getInstance(SimpleKey.keyFor(getCreatedBy()));
            }
        return aScarabUser;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setScarabUserKey(ObjectKey key) throws TorqueException
    {
    
                    setCreatedBy(new Integer(((NumberKey) key).intValue()));
              }
  
        
                  
        private ActivitySetType aActivitySetType;

    /**
     * Declares an association between this object and a ActivitySetType object
     *
     * @param v ActivitySetType
     * @throws TorqueException
     */
    public void setActivitySetType(ActivitySetType v) throws TorqueException
    {
            if (v == null)
        {
                  setTypeId((Integer) null);
              }
        else
        {
            setTypeId(v.getTypeId());
        }
            aActivitySetType = v;
    }

                        
    /**
     * Get the associated ActivitySetType object
     *
     * @return the associated ActivitySetType object
     * @throws TorqueException
     */
    public ActivitySetType getActivitySetType() throws TorqueException
    {
        if ( !ObjectUtils.equals(getTypeId(), null) )
        {
                return ActivitySetTypeManager.getInstance(SimpleKey.keyFor(getTypeId()));
            }
        return aActivitySetType;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setActivitySetTypeKey(ObjectKey key) throws TorqueException
    {
    
                    setTypeId(new Integer(((NumberKey) key).intValue()));
              }
  
        
                  
        private Attachment aAttachment;

    /**
     * Declares an association between this object and a Attachment object
     *
     * @param v Attachment
     * @throws TorqueException
     */
    public void setAttachment(Attachment v) throws TorqueException
    {
            if (v == null)
        {
                  setAttachmentId((Long) null);
              }
        else
        {
            setAttachmentId(v.getAttachmentId());
        }
            aAttachment = v;
    }

                        
    /**
     * Get the associated Attachment object
     *
     * @return the associated Attachment object
     * @throws TorqueException
     */
    public Attachment getAttachment() throws TorqueException
    {
        if ( !ObjectUtils.equals(getAttachmentId(), null) )
        {
                return AttachmentManager.getInstance(SimpleKey.keyFor(getAttachmentId()));
            }
        return aAttachment;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setAttachmentKey(ObjectKey key) throws TorqueException
    {
    
                    setAttachmentId(new Long(((NumberKey) key).longValue()));
              }
     
                                
            
    /**
     * Collection to store aggregation of collActivitys
     */
    protected List collActivitys;

    /**
     * Temporary storage of collActivitys to save a possible db hit in
     * the event objects are add to the collection, but the
     * complete collection is never requested.
     */
    protected void initActivitys()
    {
        if (collActivitys == null)
        {
            collActivitys = new ArrayList();
        }
    }

            
    /**
     * Method called to associate a Activity object to this object
     * through the Activity foreign key attribute
     *
     * @param l Activity
     * @throws TorqueException
     */
    public void addActivity(Activity l) throws TorqueException
    {
        getActivitys().add(l);
        l.setActivitySet((ActivitySet)this);
    }

    /**
     * The criteria used to select the current contents of collActivitys
     */
    private Criteria lastActivitysCriteria = null;

    /**
     * If this collection has already been initialized, returns
     * the collection. Otherwise returns the results of
     * getActivitys(new Criteria())
     *
     * @throws TorqueException
     */
    public List getActivitys() throws TorqueException
    {
        if (collActivitys == null)
        {
            collActivitys = getActivitys(new Criteria(10));
        }
        return collActivitys;
    }

    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this ActivitySet has previously
     * been saved, it will retrieve related Activitys from storage.
     * If this ActivitySet is new, it will return
     * an empty collection or the current collection, the criteria
     * is ignored on a new object.
     *
     * @throws TorqueException
     */
    public List getActivitys(Criteria criteria) throws TorqueException
    {
        if (collActivitys == null)
        {
            if (isNew())
            {
               collActivitys = new ArrayList();
            }
            else
            {
                      criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                      collActivitys = ActivityPeer.doSelect(criteria);
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
                      criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                      if (!lastActivitysCriteria.equals(criteria))
                {
                    collActivitys = ActivityPeer.doSelect(criteria);
                }
            }
        }
        lastActivitysCriteria = criteria;

        return collActivitys;
    }

    /**
     * If this collection has already been initialized, returns
     * the collection. Otherwise returns the results of
     * getActivitys(new Criteria(),Connection)
     * This method takes in the Connection also as input so that
     * referenced objects can also be obtained using a Connection
     * that is taken as input
     */
    public List getActivitys(Connection con) throws TorqueException
    {
        if (collActivitys == null)
        {
            collActivitys = getActivitys(new Criteria(10),con);
        }
        return collActivitys;
    }

    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this ActivitySet has previously
     * been saved, it will retrieve related Activitys from storage.
     * If this ActivitySet is new, it will return
     * an empty collection or the current collection, the criteria
     * is ignored on a new object.
     * This method takes in the Connection also as input so that
     * referenced objects can also be obtained using a Connection
     * that is taken as input
     */
    public List getActivitys(Criteria criteria,Connection con) throws TorqueException
    {
        if (collActivitys == null)
        {
            if (isNew())
            {
               collActivitys = new ArrayList();
            }
            else
            {
                       criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                       collActivitys = ActivityPeer.doSelect(criteria,con);
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
                     criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                     if (!lastActivitysCriteria.equals(criteria))
                 {
                     collActivitys = ActivityPeer.doSelect(criteria,con);
                 }
             }
         }
         lastActivitysCriteria = criteria;

         return collActivitys;
     }

                                                                  
              
                    
                    
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this ActivitySet is new, it will return
     * an empty collection; or if this ActivitySet has previously
     * been saved, it will retrieve related Activitys from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in ActivitySet.
     */
    protected List getActivitysJoinIssue(Criteria criteria)
        throws TorqueException
    {
        if (collActivitys == null)
        {
            if (isNew())
            {
               collActivitys = new ArrayList();
            }
            else
            {
                            criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                            collActivitys = ActivityPeer.doSelectJoinIssue(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.

                            criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                        if (!lastActivitysCriteria.equals(criteria))
            {
                collActivitys = ActivityPeer.doSelectJoinIssue(criteria);
            }
        }
        lastActivitysCriteria = criteria;

        return collActivitys;
    }
                  
                    
                    
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this ActivitySet is new, it will return
     * an empty collection; or if this ActivitySet has previously
     * been saved, it will retrieve related Activitys from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in ActivitySet.
     */
    protected List getActivitysJoinAttribute(Criteria criteria)
        throws TorqueException
    {
        if (collActivitys == null)
        {
            if (isNew())
            {
               collActivitys = new ArrayList();
            }
            else
            {
                            criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                            collActivitys = ActivityPeer.doSelectJoinAttribute(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.

                            criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                        if (!lastActivitysCriteria.equals(criteria))
            {
                collActivitys = ActivityPeer.doSelectJoinAttribute(criteria);
            }
        }
        lastActivitysCriteria = criteria;

        return collActivitys;
    }
                  
                    
                              
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this ActivitySet is new, it will return
     * an empty collection; or if this ActivitySet has previously
     * been saved, it will retrieve related Activitys from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in ActivitySet.
     */
    protected List getActivitysJoinActivitySet(Criteria criteria)
        throws TorqueException
    {
        if (collActivitys == null)
        {
            if (isNew())
            {
               collActivitys = new ArrayList();
            }
            else
            {
                            criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                            collActivitys = ActivityPeer.doSelectJoinActivitySet(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.

                            criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                        if (!lastActivitysCriteria.equals(criteria))
            {
                collActivitys = ActivityPeer.doSelectJoinActivitySet(criteria);
            }
        }
        lastActivitysCriteria = criteria;

        return collActivitys;
    }
                  
                    
                    
                                            
                                                                          
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this ActivitySet is new, it will return
     * an empty collection; or if this ActivitySet has previously
     * been saved, it will retrieve related Activitys from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in ActivitySet.
     */
    protected List getActivitysJoinScarabUserImplRelatedByOldUserId(Criteria criteria)
        throws TorqueException
    {
        if (collActivitys == null)
        {
            if (isNew())
            {
               collActivitys = new ArrayList();
            }
            else
            {
                            criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                            collActivitys = ActivityPeer.doSelectJoinScarabUserImplRelatedByOldUserId(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.

                            criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                        if (!lastActivitysCriteria.equals(criteria))
            {
                collActivitys = ActivityPeer.doSelectJoinScarabUserImplRelatedByOldUserId(criteria);
            }
        }
        lastActivitysCriteria = criteria;

        return collActivitys;
    }
                  
                    
                    
                                            
                                                                          
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this ActivitySet is new, it will return
     * an empty collection; or if this ActivitySet has previously
     * been saved, it will retrieve related Activitys from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in ActivitySet.
     */
    protected List getActivitysJoinScarabUserImplRelatedByNewUserId(Criteria criteria)
        throws TorqueException
    {
        if (collActivitys == null)
        {
            if (isNew())
            {
               collActivitys = new ArrayList();
            }
            else
            {
                            criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                            collActivitys = ActivityPeer.doSelectJoinScarabUserImplRelatedByNewUserId(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.

                            criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                        if (!lastActivitysCriteria.equals(criteria))
            {
                collActivitys = ActivityPeer.doSelectJoinScarabUserImplRelatedByNewUserId(criteria);
            }
        }
        lastActivitysCriteria = criteria;

        return collActivitys;
    }
                  
                    
                    
                                            
                                                                          
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this ActivitySet is new, it will return
     * an empty collection; or if this ActivitySet has previously
     * been saved, it will retrieve related Activitys from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in ActivitySet.
     */
    protected List getActivitysJoinAttributeOptionRelatedByOldOptionId(Criteria criteria)
        throws TorqueException
    {
        if (collActivitys == null)
        {
            if (isNew())
            {
               collActivitys = new ArrayList();
            }
            else
            {
                            criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                            collActivitys = ActivityPeer.doSelectJoinAttributeOptionRelatedByOldOptionId(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.

                            criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                        if (!lastActivitysCriteria.equals(criteria))
            {
                collActivitys = ActivityPeer.doSelectJoinAttributeOptionRelatedByOldOptionId(criteria);
            }
        }
        lastActivitysCriteria = criteria;

        return collActivitys;
    }
                  
                    
                    
                                            
                                                                          
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this ActivitySet is new, it will return
     * an empty collection; or if this ActivitySet has previously
     * been saved, it will retrieve related Activitys from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in ActivitySet.
     */
    protected List getActivitysJoinAttributeOptionRelatedByNewOptionId(Criteria criteria)
        throws TorqueException
    {
        if (collActivitys == null)
        {
            if (isNew())
            {
               collActivitys = new ArrayList();
            }
            else
            {
                            criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                            collActivitys = ActivityPeer.doSelectJoinAttributeOptionRelatedByNewOptionId(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.

                            criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                        if (!lastActivitysCriteria.equals(criteria))
            {
                collActivitys = ActivityPeer.doSelectJoinAttributeOptionRelatedByNewOptionId(criteria);
            }
        }
        lastActivitysCriteria = criteria;

        return collActivitys;
    }
                  
                    
                    
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this ActivitySet is new, it will return
     * an empty collection; or if this ActivitySet has previously
     * been saved, it will retrieve related Activitys from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in ActivitySet.
     */
    protected List getActivitysJoinAttachment(Criteria criteria)
        throws TorqueException
    {
        if (collActivitys == null)
        {
            if (isNew())
            {
               collActivitys = new ArrayList();
            }
            else
            {
                            criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                            collActivitys = ActivityPeer.doSelectJoinAttachment(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.

                            criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                        if (!lastActivitysCriteria.equals(criteria))
            {
                collActivitys = ActivityPeer.doSelectJoinAttachment(criteria);
            }
        }
        lastActivitysCriteria = criteria;

        return collActivitys;
    }
                  
                    
                    
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this ActivitySet is new, it will return
     * an empty collection; or if this ActivitySet has previously
     * been saved, it will retrieve related Activitys from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in ActivitySet.
     */
    protected List getActivitysJoinDepend(Criteria criteria)
        throws TorqueException
    {
        if (collActivitys == null)
        {
            if (isNew())
            {
               collActivitys = new ArrayList();
            }
            else
            {
                            criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                            collActivitys = ActivityPeer.doSelectJoinDepend(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.

                            criteria.add(ActivityPeer.TRANSACTION_ID, getActivitySetId() );
                        if (!lastActivitysCriteria.equals(criteria))
            {
                collActivitys = ActivityPeer.doSelectJoinDepend(criteria);
            }
        }
        lastActivitysCriteria = criteria;

        return collActivitys;
    }
                            


                          
            
    /**
     * Collection to store aggregation of collIssues
     */
    protected List collIssues;

    /**
     * Temporary storage of collIssues to save a possible db hit in
     * the event objects are add to the collection, but the
     * complete collection is never requested.
     */
    protected void initIssues()
    {
        if (collIssues == null)
        {
            collIssues = new ArrayList();
        }
    }

            
    /**
     * Method called to associate a Issue object to this object
     * through the Issue foreign key attribute
     *
     * @param l Issue
     * @throws TorqueException
     */
    public void addIssue(Issue l) throws TorqueException
    {
        getIssues().add(l);
        l.setActivitySet((ActivitySet)this);
    }

    /**
     * The criteria used to select the current contents of collIssues
     */
    private Criteria lastIssuesCriteria = null;

    /**
     * If this collection has already been initialized, returns
     * the collection. Otherwise returns the results of
     * getIssues(new Criteria())
     *
     * @throws TorqueException
     */
    public List getIssues() throws TorqueException
    {
        if (collIssues == null)
        {
            collIssues = getIssues(new Criteria(10));
        }
        return collIssues;
    }

    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this ActivitySet has previously
     * been saved, it will retrieve related Issues from storage.
     * If this ActivitySet is new, it will return
     * an empty collection or the current collection, the criteria
     * is ignored on a new object.
     *
     * @throws TorqueException
     */
    public List getIssues(Criteria criteria) throws TorqueException
    {
        if (collIssues == null)
        {
            if (isNew())
            {
               collIssues = new ArrayList();
            }
            else
            {
                      criteria.add(IssuePeer.CREATED_TRANS_ID, getActivitySetId() );
                      collIssues = IssuePeer.doSelect(criteria);
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
                      criteria.add(IssuePeer.CREATED_TRANS_ID, getActivitySetId() );
                      if (!lastIssuesCriteria.equals(criteria))
                {
                    collIssues = IssuePeer.doSelect(criteria);
                }
            }
        }
        lastIssuesCriteria = criteria;

        return collIssues;
    }

    /**
     * If this collection has already been initialized, returns
     * the collection. Otherwise returns the results of
     * getIssues(new Criteria(),Connection)
     * This method takes in the Connection also as input so that
     * referenced objects can also be obtained using a Connection
     * that is taken as input
     */
    public List getIssues(Connection con) throws TorqueException
    {
        if (collIssues == null)
        {
            collIssues = getIssues(new Criteria(10),con);
        }
        return collIssues;
    }

    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this ActivitySet has previously
     * been saved, it will retrieve related Issues from storage.
     * If this ActivitySet is new, it will return
     * an empty collection or the current collection, the criteria
     * is ignored on a new object.
     * This method takes in the Connection also as input so that
     * referenced objects can also be obtained using a Connection
     * that is taken as input
     */
    public List getIssues(Criteria criteria,Connection con) throws TorqueException
    {
        if (collIssues == null)
        {
            if (isNew())
            {
               collIssues = new ArrayList();
            }
            else
            {
                       criteria.add(IssuePeer.CREATED_TRANS_ID, getActivitySetId() );
                       collIssues = IssuePeer.doSelect(criteria,con);
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
                     criteria.add(IssuePeer.CREATED_TRANS_ID, getActivitySetId() );
                     if (!lastIssuesCriteria.equals(criteria))
                 {
                     collIssues = IssuePeer.doSelect(criteria,con);
                 }
             }
         }
         lastIssuesCriteria = criteria;

         return collIssues;
     }

                              
              
                    
                    
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this ActivitySet is new, it will return
     * an empty collection; or if this ActivitySet has previously
     * been saved, it will retrieve related Issues from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in ActivitySet.
     */
    protected List getIssuesJoinScarabModule(Criteria criteria)
        throws TorqueException
    {
        if (collIssues == null)
        {
            if (isNew())
            {
               collIssues = new ArrayList();
            }
            else
            {
                            criteria.add(IssuePeer.CREATED_TRANS_ID, getActivitySetId() );
                            collIssues = IssuePeer.doSelectJoinScarabModule(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.

                            criteria.add(IssuePeer.CREATED_TRANS_ID, getActivitySetId() );
                        if (!lastIssuesCriteria.equals(criteria))
            {
                collIssues = IssuePeer.doSelectJoinScarabModule(criteria);
            }
        }
        lastIssuesCriteria = criteria;

        return collIssues;
    }
                  
                    
                    
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this ActivitySet is new, it will return
     * an empty collection; or if this ActivitySet has previously
     * been saved, it will retrieve related Issues from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in ActivitySet.
     */
    protected List getIssuesJoinIssueType(Criteria criteria)
        throws TorqueException
    {
        if (collIssues == null)
        {
            if (isNew())
            {
               collIssues = new ArrayList();
            }
            else
            {
                            criteria.add(IssuePeer.CREATED_TRANS_ID, getActivitySetId() );
                            collIssues = IssuePeer.doSelectJoinIssueType(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.

                            criteria.add(IssuePeer.CREATED_TRANS_ID, getActivitySetId() );
                        if (!lastIssuesCriteria.equals(criteria))
            {
                collIssues = IssuePeer.doSelectJoinIssueType(criteria);
            }
        }
        lastIssuesCriteria = criteria;

        return collIssues;
    }
                  
                    
                              
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this ActivitySet is new, it will return
     * an empty collection; or if this ActivitySet has previously
     * been saved, it will retrieve related Issues from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in ActivitySet.
     */
    protected List getIssuesJoinActivitySet(Criteria criteria)
        throws TorqueException
    {
        if (collIssues == null)
        {
            if (isNew())
            {
               collIssues = new ArrayList();
            }
            else
            {
                            criteria.add(IssuePeer.CREATED_TRANS_ID, getActivitySetId() );
                            collIssues = IssuePeer.doSelectJoinActivitySet(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.

                            criteria.add(IssuePeer.CREATED_TRANS_ID, getActivitySetId() );
                        if (!lastIssuesCriteria.equals(criteria))
            {
                collIssues = IssuePeer.doSelectJoinActivitySet(criteria);
            }
        }
        lastIssuesCriteria = criteria;

        return collIssues;
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
              fieldNames.add("ActivitySetId");
              fieldNames.add("TypeId");
              fieldNames.add("AttachmentId");
              fieldNames.add("CreatedBy");
              fieldNames.add("CreatedDate");
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
          if (name.equals("ActivitySetId"))
        {
                return getActivitySetId();
            }
          if (name.equals("TypeId"))
        {
                return getTypeId();
            }
          if (name.equals("AttachmentId"))
        {
                return getAttachmentId();
            }
          if (name.equals("CreatedBy"))
        {
                return getCreatedBy();
            }
          if (name.equals("CreatedDate"))
        {
                return getCreatedDate();
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
          if (name.equals(ActivitySetPeer.TRANSACTION_ID))
        {
                return getActivitySetId();
            }
          if (name.equals(ActivitySetPeer.TYPE_ID))
        {
                return getTypeId();
            }
          if (name.equals(ActivitySetPeer.ATTACHMENT_ID))
        {
                return getAttachmentId();
            }
          if (name.equals(ActivitySetPeer.CREATED_BY))
        {
                return getCreatedBy();
            }
          if (name.equals(ActivitySetPeer.CREATED_DATE))
        {
                return getCreatedDate();
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
                return getActivitySetId();
            }
              if (pos == 1)
        {
                return getTypeId();
            }
              if (pos == 2)
        {
                return getAttachmentId();
            }
              if (pos == 3)
        {
                return getCreatedBy();
            }
              if (pos == 4)
        {
                return getCreatedDate();
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
          save(ActivitySetPeer.getMapBuilder()
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
                    ActivitySetPeer.doInsert((ActivitySet)this, con);
                    setNew(false);
                }
                else
                {
                    ActivitySetPeer.doUpdate((ActivitySet)this, con);
                }

                      if (isCacheOnSave())
                {
                    ActivitySetManager.putInstance(this);
                }
              }

                                      
                            if (collActivitys != null)
            {
                for (int i = 0; i < collActivitys.size(); i++)
                {
                    ((Activity)collActivitys.get(i)).save(con);
                }
            }
                                          
                            if (collIssues != null)
            {
                for (int i = 0; i < collIssues.size(); i++)
                {
                    ((Issue)collIssues.get(i)).save(con);
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
     * @param activitySetId ObjectKey
     */
    public void setPrimaryKey(ObjectKey activitySetId)
        throws TorqueException {
            setActivitySetId(new Long(((NumberKey)activitySetId).longValue()));
        }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) throws TorqueException
    {
            setActivitySetId(new Long(key));
        }

  
    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
          return SimpleKey.keyFor(getActivitySetId());
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
      public ActivitySet copy() throws TorqueException
    {
        ActivitySet copyObj = new ActivitySet();
            copyObj.setActivitySetId(activitySetId);
          copyObj.setTypeId(typeId);
          copyObj.setAttachmentId(attachmentId);
          copyObj.setCreatedBy(createdBy);
          copyObj.setCreatedDate(createdDate);
  
                      copyObj.setActivitySetId((Long)null);
                                    
                                      
                
        List v = getActivitys();
        for (int i = 0; i < v.size(); i++)
        {
            Activity obj = (Activity) v.get(i);
            copyObj.addActivity(obj.copy());
        }
                                                  
                
        v = getIssues();
        for (int i = 0; i < v.size(); i++)
        {
            Issue obj = (Issue) v.get(i);
            copyObj.addIssue(obj.copy());
        }
                            return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public ActivitySetPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("ActivitySet:\n");
        str.append("ActivitySetId = ")
               .append(getActivitySetId())
             .append("\n");
        str.append("TypeId = ")
               .append(getTypeId())
             .append("\n");
        str.append("AttachmentId = ")
               .append(getAttachmentId())
             .append("\n");
        str.append("CreatedBy = ")
               .append(getCreatedBy())
             .append("\n");
        str.append("CreatedDate = ")
               .append(getCreatedDate())
             .append("\n");
        return(str.toString());
    }
}
