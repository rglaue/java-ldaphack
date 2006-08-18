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
 * extended all references should be to Activity
 */
public abstract class BaseActivity extends BaseObject
    implements org.apache.fulcrum.intake.Retrievable
{
    /** The Peer class */
    private static final ActivityPeer peer =
        new ActivityPeer();

        
    /** The value for the activityId field */
    private Long activityId;
      
    /** The value for the issueId field */
    private Long issueId;
      
    /** The value for the attributeId field */
    private Integer attributeId;
      
    /** The value for the transactionId field */
    private Long transactionId;
      
    /** The value for the oldNumericValue field */
    private Integer oldNumericValue;
      
    /** The value for the newNumericValue field */
    private Integer newNumericValue;
      
    /** The value for the oldUserId field */
    private Integer oldUserId;
      
    /** The value for the newUserId field */
    private Integer newUserId;
      
    /** The value for the oldOptionId field */
    private Integer oldOptionId;
      
    /** The value for the newOptionId field */
    private Integer newOptionId;
      
    /** The value for the oldValue field */
    private String oldValue;
      
    /** The value for the newValue field */
    private String newValue;
      
    /** The value for the dependId field */
    private Integer dependId;
      
    /** The value for the description field */
    private String description;
      
    /** The value for the endDate field */
    private Date endDate;
      
    /** The value for the attachmentId field */
    private Long attachmentId;
  
    
    /**
     * Get the ActivityId
     *
     * @return Long
     */
    public Long getActivityId()
    {
        return activityId;
    }

                        
    /**
     * Set the value of ActivityId
     *
     * @param v new value
     */
    public void setActivityId(Long v) 
    {
    
                  if (!ObjectUtils.equals(this.activityId, v))
              {
            this.activityId = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the IssueId
     *
     * @return Long
     */
    public Long getIssueId()
    {
        return issueId;
    }

                              
    /**
     * Set the value of IssueId
     *
     * @param v new value
     */
    public void setIssueId(Long v) throws TorqueException
    {
    
                  if (!ObjectUtils.equals(this.issueId, v))
              {
            this.issueId = v;
            setModified(true);
        }
    
                                  
                if (aIssue != null && !ObjectUtils.equals(aIssue.getIssueId(), v))
                {
            aIssue = null;
        }
      
              }
  
    /**
     * Get the AttributeId
     *
     * @return Integer
     */
    public Integer getAttributeId()
    {
        return attributeId;
    }

                              
    /**
     * Set the value of AttributeId
     *
     * @param v new value
     */
    public void setAttributeId(Integer v) throws TorqueException
    {
    
                  if (!ObjectUtils.equals(this.attributeId, v))
              {
            this.attributeId = v;
            setModified(true);
        }
    
                                  
                if (aAttribute != null && !ObjectUtils.equals(aAttribute.getAttributeId(), v))
                {
            aAttribute = null;
        }
      
              }
  
    /**
     * Get the TransactionId
     *
     * @return Long
     */
    public Long getTransactionId()
    {
        return transactionId;
    }

                              
    /**
     * Set the value of TransactionId
     *
     * @param v new value
     */
    public void setTransactionId(Long v) throws TorqueException
    {
    
                  if (!ObjectUtils.equals(this.transactionId, v))
              {
            this.transactionId = v;
            setModified(true);
        }
    
                                  
                if (aActivitySet != null && !ObjectUtils.equals(aActivitySet.getActivitySetId(), v))
                {
            aActivitySet = null;
        }
      
              }
  
    /**
     * Get the OldNumericValue
     *
     * @return Integer
     */
    public Integer getOldNumericValue()
    {
        return oldNumericValue;
    }

                        
    /**
     * Set the value of OldNumericValue
     *
     * @param v new value
     */
    public void setOldNumericValue(Integer v) 
    {
    
                  if (!ObjectUtils.equals(this.oldNumericValue, v))
              {
            this.oldNumericValue = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the NewNumericValue
     *
     * @return Integer
     */
    public Integer getNewNumericValue()
    {
        return newNumericValue;
    }

                        
    /**
     * Set the value of NewNumericValue
     *
     * @param v new value
     */
    public void setNewNumericValue(Integer v) 
    {
    
                  if (!ObjectUtils.equals(this.newNumericValue, v))
              {
            this.newNumericValue = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the OldUserId
     *
     * @return Integer
     */
    public Integer getOldUserId()
    {
        return oldUserId;
    }

                              
    /**
     * Set the value of OldUserId
     *
     * @param v new value
     */
    public void setOldUserId(Integer v) throws TorqueException
    {
    
                  if (!ObjectUtils.equals(this.oldUserId, v))
              {
            this.oldUserId = v;
            setModified(true);
        }
    
                                                                                  
                if (aScarabUserRelatedByOldUserId != null && !ObjectUtils.equals(aScarabUserRelatedByOldUserId.getUserId(), v))
                {
            aScarabUserRelatedByOldUserId = null;
        }
      
              }
  
    /**
     * Get the NewUserId
     *
     * @return Integer
     */
    public Integer getNewUserId()
    {
        return newUserId;
    }

                              
    /**
     * Set the value of NewUserId
     *
     * @param v new value
     */
    public void setNewUserId(Integer v) throws TorqueException
    {
    
                  if (!ObjectUtils.equals(this.newUserId, v))
              {
            this.newUserId = v;
            setModified(true);
        }
    
                                                                                  
                if (aScarabUserRelatedByNewUserId != null && !ObjectUtils.equals(aScarabUserRelatedByNewUserId.getUserId(), v))
                {
            aScarabUserRelatedByNewUserId = null;
        }
      
              }
  
    /**
     * Get the OldOptionId
     *
     * @return Integer
     */
    public Integer getOldOptionId()
    {
        return oldOptionId;
    }

                              
    /**
     * Set the value of OldOptionId
     *
     * @param v new value
     */
    public void setOldOptionId(Integer v) throws TorqueException
    {
    
                  if (!ObjectUtils.equals(this.oldOptionId, v))
              {
            this.oldOptionId = v;
            setModified(true);
        }
    
                                                                          
                if (aAttributeOptionRelatedByOldOptionId != null && !ObjectUtils.equals(aAttributeOptionRelatedByOldOptionId.getOptionId(), v))
                {
            aAttributeOptionRelatedByOldOptionId = null;
        }
      
              }
  
    /**
     * Get the NewOptionId
     *
     * @return Integer
     */
    public Integer getNewOptionId()
    {
        return newOptionId;
    }

                              
    /**
     * Set the value of NewOptionId
     *
     * @param v new value
     */
    public void setNewOptionId(Integer v) throws TorqueException
    {
    
                  if (!ObjectUtils.equals(this.newOptionId, v))
              {
            this.newOptionId = v;
            setModified(true);
        }
    
                                                                          
                if (aAttributeOptionRelatedByNewOptionId != null && !ObjectUtils.equals(aAttributeOptionRelatedByNewOptionId.getOptionId(), v))
                {
            aAttributeOptionRelatedByNewOptionId = null;
        }
      
              }
  
    /**
     * Get the OldValue
     *
     * @return String
     */
    public String getOldValue()
    {
        return oldValue;
    }

                        
    /**
     * Set the value of OldValue
     *
     * @param v new value
     */
    public void setOldValue(String v) 
    {
    
                  if (!ObjectUtils.equals(this.oldValue, v))
              {
            this.oldValue = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the NewValue
     *
     * @return String
     */
    public String getNewValue()
    {
        return newValue;
    }

                        
    /**
     * Set the value of NewValue
     *
     * @param v new value
     */
    public void setNewValue(String v) 
    {
    
                  if (!ObjectUtils.equals(this.newValue, v))
              {
            this.newValue = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the DependId
     *
     * @return Integer
     */
    public Integer getDependId()
    {
        return dependId;
    }

                              
    /**
     * Set the value of DependId
     *
     * @param v new value
     */
    public void setDependId(Integer v) throws TorqueException
    {
    
                  if (!ObjectUtils.equals(this.dependId, v))
              {
            this.dependId = v;
            setModified(true);
        }
    
                                  
                if (aDepend != null && !ObjectUtils.equals(aDepend.getDependId(), v))
                {
            aDepend = null;
        }
      
              }
  
    /**
     * Get the Description
     *
     * @return String
     */
    public String getDescription()
    {
        return description;
    }

                        
    /**
     * Set the value of Description
     *
     * @param v new value
     */
    public void setDescription(String v) 
    {
    
                  if (!ObjectUtils.equals(this.description, v))
              {
            this.description = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the EndDate
     *
     * @return Date
     */
    public Date getEndDate()
    {
        return endDate;
    }

                        
    /**
     * Set the value of EndDate
     *
     * @param v new value
     */
    public void setEndDate(Date v) 
    {
    
                  if (!ObjectUtils.equals(this.endDate, v))
              {
            this.endDate = v;
            setModified(true);
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
  
      
        
                  
        private Issue aIssue;

    /**
     * Declares an association between this object and a Issue object
     *
     * @param v Issue
     * @throws TorqueException
     */
    public void setIssue(Issue v) throws TorqueException
    {
            if (v == null)
        {
                  setIssueId((Long) null);
              }
        else
        {
            setIssueId(v.getIssueId());
        }
            aIssue = v;
    }

                        
    /**
     * Get the associated Issue object
     *
     * @return the associated Issue object
     * @throws TorqueException
     */
    public Issue getIssue() throws TorqueException
    {
        if ( !ObjectUtils.equals(getIssueId(), null) )
        {
                return IssueManager.getInstance(SimpleKey.keyFor(getIssueId()));
            }
        return aIssue;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setIssueKey(ObjectKey key) throws TorqueException
    {
    
                    setIssueId(new Long(((NumberKey) key).longValue()));
              }
  
        
                  
        private Attribute aAttribute;

    /**
     * Declares an association between this object and a Attribute object
     *
     * @param v Attribute
     * @throws TorqueException
     */
    public void setAttribute(Attribute v) throws TorqueException
    {
            if (v == null)
        {
                  setAttributeId((Integer) null);
              }
        else
        {
            setAttributeId(v.getAttributeId());
        }
            aAttribute = v;
    }

                        
    /**
     * Get the associated Attribute object
     *
     * @return the associated Attribute object
     * @throws TorqueException
     */
    public Attribute getAttribute() throws TorqueException
    {
        if ( !ObjectUtils.equals(getAttributeId(), null) )
        {
                return AttributeManager.getInstance(SimpleKey.keyFor(getAttributeId()));
            }
        return aAttribute;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setAttributeKey(ObjectKey key) throws TorqueException
    {
    
                    setAttributeId(new Integer(((NumberKey) key).intValue()));
              }
  
        
                  
        private ActivitySet aActivitySet;

    /**
     * Declares an association between this object and a ActivitySet object
     *
     * @param v ActivitySet
     * @throws TorqueException
     */
    public void setActivitySet(ActivitySet v) throws TorqueException
    {
            if (v == null)
        {
                  setTransactionId((Long) null);
              }
        else
        {
            setTransactionId(v.getActivitySetId());
        }
            aActivitySet = v;
    }

                        
    /**
     * Get the associated ActivitySet object
     *
     * @return the associated ActivitySet object
     * @throws TorqueException
     */
    public ActivitySet getActivitySet() throws TorqueException
    {
        if ( !ObjectUtils.equals(getTransactionId(), null) )
        {
                return ActivitySetManager.getInstance(SimpleKey.keyFor(getTransactionId()));
            }
        return aActivitySet;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setActivitySetKey(ObjectKey key) throws TorqueException
    {
    
                    setTransactionId(new Long(((NumberKey) key).longValue()));
              }
  
            
                        
            private ScarabUser aScarabUserRelatedByOldUserId;

    /**
     * Declares an association between this object and a ScarabUser object
     *
     * @param v ScarabUser
     * @throws TorqueException
     */
    public void setScarabUserRelatedByOldUserId(ScarabUser v) throws TorqueException
    {
            if (v == null)
        {
                  setOldUserId((Integer) null);
              }
        else
        {
            setOldUserId(v.getUserId());
        }
            aScarabUserRelatedByOldUserId = v;
    }

                        
    /**
     * Get the associated ScarabUser object
     *
     * @return the associated ScarabUser object
     * @throws TorqueException
     */
    public ScarabUser getScarabUserRelatedByOldUserId() throws TorqueException
    {
        if ( !ObjectUtils.equals(getOldUserId(), null) )
        {
                return ScarabUserManager.getInstance(SimpleKey.keyFor(getOldUserId()));
            }
        return aScarabUserRelatedByOldUserId;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setScarabUserRelatedByOldUserIdKey(ObjectKey key) throws TorqueException
    {
    
                    setOldUserId(new Integer(((NumberKey) key).intValue()));
              }
  
            
                        
            private ScarabUser aScarabUserRelatedByNewUserId;

    /**
     * Declares an association between this object and a ScarabUser object
     *
     * @param v ScarabUser
     * @throws TorqueException
     */
    public void setScarabUserRelatedByNewUserId(ScarabUser v) throws TorqueException
    {
            if (v == null)
        {
                  setNewUserId((Integer) null);
              }
        else
        {
            setNewUserId(v.getUserId());
        }
            aScarabUserRelatedByNewUserId = v;
    }

                        
    /**
     * Get the associated ScarabUser object
     *
     * @return the associated ScarabUser object
     * @throws TorqueException
     */
    public ScarabUser getScarabUserRelatedByNewUserId() throws TorqueException
    {
        if ( !ObjectUtils.equals(getNewUserId(), null) )
        {
                return ScarabUserManager.getInstance(SimpleKey.keyFor(getNewUserId()));
            }
        return aScarabUserRelatedByNewUserId;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setScarabUserRelatedByNewUserIdKey(ObjectKey key) throws TorqueException
    {
    
                    setNewUserId(new Integer(((NumberKey) key).intValue()));
              }
  
        
                        
            private AttributeOption aAttributeOptionRelatedByOldOptionId;

    /**
     * Declares an association between this object and a AttributeOption object
     *
     * @param v AttributeOption
     * @throws TorqueException
     */
    public void setAttributeOptionRelatedByOldOptionId(AttributeOption v) throws TorqueException
    {
            if (v == null)
        {
                  setOldOptionId((Integer) null);
              }
        else
        {
            setOldOptionId(v.getOptionId());
        }
            aAttributeOptionRelatedByOldOptionId = v;
    }

                        
    /**
     * Get the associated AttributeOption object
     *
     * @return the associated AttributeOption object
     * @throws TorqueException
     */
    public AttributeOption getAttributeOptionRelatedByOldOptionId() throws TorqueException
    {
        if ( !ObjectUtils.equals(getOldOptionId(), null) )
        {
                return AttributeOptionManager.getInstance(SimpleKey.keyFor(getOldOptionId()));
            }
        return aAttributeOptionRelatedByOldOptionId;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setAttributeOptionRelatedByOldOptionIdKey(ObjectKey key) throws TorqueException
    {
    
                    setOldOptionId(new Integer(((NumberKey) key).intValue()));
              }
  
        
                        
            private AttributeOption aAttributeOptionRelatedByNewOptionId;

    /**
     * Declares an association between this object and a AttributeOption object
     *
     * @param v AttributeOption
     * @throws TorqueException
     */
    public void setAttributeOptionRelatedByNewOptionId(AttributeOption v) throws TorqueException
    {
            if (v == null)
        {
                  setNewOptionId((Integer) null);
              }
        else
        {
            setNewOptionId(v.getOptionId());
        }
            aAttributeOptionRelatedByNewOptionId = v;
    }

                        
    /**
     * Get the associated AttributeOption object
     *
     * @return the associated AttributeOption object
     * @throws TorqueException
     */
    public AttributeOption getAttributeOptionRelatedByNewOptionId() throws TorqueException
    {
        if ( !ObjectUtils.equals(getNewOptionId(), null) )
        {
                return AttributeOptionManager.getInstance(SimpleKey.keyFor(getNewOptionId()));
            }
        return aAttributeOptionRelatedByNewOptionId;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setAttributeOptionRelatedByNewOptionIdKey(ObjectKey key) throws TorqueException
    {
    
                    setNewOptionId(new Integer(((NumberKey) key).intValue()));
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
  
        
                  
        private Depend aDepend;

    /**
     * Declares an association between this object and a Depend object
     *
     * @param v Depend
     * @throws TorqueException
     */
    public void setDepend(Depend v) throws TorqueException
    {
            if (v == null)
        {
                  setDependId((Integer) null);
              }
        else
        {
            setDependId(v.getDependId());
        }
            aDepend = v;
    }

                        
    /**
     * Get the associated Depend object
     *
     * @return the associated Depend object
     * @throws TorqueException
     */
    public Depend getDepend() throws TorqueException
    {
        if ( !ObjectUtils.equals(getDependId(), null) )
        {
                return DependManager.getInstance(SimpleKey.keyFor(getDependId()));
            }
        return aDepend;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setDependKey(ObjectKey key) throws TorqueException
    {
    
                    setDependId(new Integer(((NumberKey) key).intValue()));
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
              fieldNames.add("ActivityId");
              fieldNames.add("IssueId");
              fieldNames.add("AttributeId");
              fieldNames.add("TransactionId");
              fieldNames.add("OldNumericValue");
              fieldNames.add("NewNumericValue");
              fieldNames.add("OldUserId");
              fieldNames.add("NewUserId");
              fieldNames.add("OldOptionId");
              fieldNames.add("NewOptionId");
              fieldNames.add("OldValue");
              fieldNames.add("NewValue");
              fieldNames.add("DependId");
              fieldNames.add("Description");
              fieldNames.add("EndDate");
              fieldNames.add("AttachmentId");
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
          if (name.equals("ActivityId"))
        {
                return getActivityId();
            }
          if (name.equals("IssueId"))
        {
                return getIssueId();
            }
          if (name.equals("AttributeId"))
        {
                return getAttributeId();
            }
          if (name.equals("TransactionId"))
        {
                return getTransactionId();
            }
          if (name.equals("OldNumericValue"))
        {
                return getOldNumericValue();
            }
          if (name.equals("NewNumericValue"))
        {
                return getNewNumericValue();
            }
          if (name.equals("OldUserId"))
        {
                return getOldUserId();
            }
          if (name.equals("NewUserId"))
        {
                return getNewUserId();
            }
          if (name.equals("OldOptionId"))
        {
                return getOldOptionId();
            }
          if (name.equals("NewOptionId"))
        {
                return getNewOptionId();
            }
          if (name.equals("OldValue"))
        {
                return getOldValue();
            }
          if (name.equals("NewValue"))
        {
                return getNewValue();
            }
          if (name.equals("DependId"))
        {
                return getDependId();
            }
          if (name.equals("Description"))
        {
                return getDescription();
            }
          if (name.equals("EndDate"))
        {
                return getEndDate();
            }
          if (name.equals("AttachmentId"))
        {
                return getAttachmentId();
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
          if (name.equals(ActivityPeer.ACTIVITY_ID))
        {
                return getActivityId();
            }
          if (name.equals(ActivityPeer.ISSUE_ID))
        {
                return getIssueId();
            }
          if (name.equals(ActivityPeer.ATTRIBUTE_ID))
        {
                return getAttributeId();
            }
          if (name.equals(ActivityPeer.TRANSACTION_ID))
        {
                return getTransactionId();
            }
          if (name.equals(ActivityPeer.OLD_NUMERIC_VALUE))
        {
                return getOldNumericValue();
            }
          if (name.equals(ActivityPeer.NEW_NUMERIC_VALUE))
        {
                return getNewNumericValue();
            }
          if (name.equals(ActivityPeer.OLD_USER_ID))
        {
                return getOldUserId();
            }
          if (name.equals(ActivityPeer.NEW_USER_ID))
        {
                return getNewUserId();
            }
          if (name.equals(ActivityPeer.OLD_OPTION_ID))
        {
                return getOldOptionId();
            }
          if (name.equals(ActivityPeer.NEW_OPTION_ID))
        {
                return getNewOptionId();
            }
          if (name.equals(ActivityPeer.OLD_VALUE))
        {
                return getOldValue();
            }
          if (name.equals(ActivityPeer.NEW_VALUE))
        {
                return getNewValue();
            }
          if (name.equals(ActivityPeer.DEPEND_ID))
        {
                return getDependId();
            }
          if (name.equals(ActivityPeer.DESCRIPTION))
        {
                return getDescription();
            }
          if (name.equals(ActivityPeer.END_DATE))
        {
                return getEndDate();
            }
          if (name.equals(ActivityPeer.ATTACHMENT_ID))
        {
                return getAttachmentId();
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
                return getActivityId();
            }
              if (pos == 1)
        {
                return getIssueId();
            }
              if (pos == 2)
        {
                return getAttributeId();
            }
              if (pos == 3)
        {
                return getTransactionId();
            }
              if (pos == 4)
        {
                return getOldNumericValue();
            }
              if (pos == 5)
        {
                return getNewNumericValue();
            }
              if (pos == 6)
        {
                return getOldUserId();
            }
              if (pos == 7)
        {
                return getNewUserId();
            }
              if (pos == 8)
        {
                return getOldOptionId();
            }
              if (pos == 9)
        {
                return getNewOptionId();
            }
              if (pos == 10)
        {
                return getOldValue();
            }
              if (pos == 11)
        {
                return getNewValue();
            }
              if (pos == 12)
        {
                return getDependId();
            }
              if (pos == 13)
        {
                return getDescription();
            }
              if (pos == 14)
        {
                return getEndDate();
            }
              if (pos == 15)
        {
                return getAttachmentId();
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
          save(ActivityPeer.getMapBuilder()
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
                    ActivityPeer.doInsert((Activity)this, con);
                    setNew(false);
                }
                else
                {
                    ActivityPeer.doUpdate((Activity)this, con);
                }

                      if (isCacheOnSave())
                {
                    ActivityManager.putInstance(this);
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
     * @param activityId ObjectKey
     */
    public void setPrimaryKey(ObjectKey activityId)
         {
            setActivityId(new Long(((NumberKey)activityId).longValue()));
        }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) 
    {
            setActivityId(new Long(key));
        }

  
    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
          return SimpleKey.keyFor(getActivityId());
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
      public Activity copy() throws TorqueException
    {
        Activity copyObj = new Activity();
            copyObj.setActivityId(activityId);
          copyObj.setIssueId(issueId);
          copyObj.setAttributeId(attributeId);
          copyObj.setTransactionId(transactionId);
          copyObj.setOldNumericValue(oldNumericValue);
          copyObj.setNewNumericValue(newNumericValue);
          copyObj.setOldUserId(oldUserId);
          copyObj.setNewUserId(newUserId);
          copyObj.setOldOptionId(oldOptionId);
          copyObj.setNewOptionId(newOptionId);
          copyObj.setOldValue(oldValue);
          copyObj.setNewValue(newValue);
          copyObj.setDependId(dependId);
          copyObj.setDescription(description);
          copyObj.setEndDate(endDate);
          copyObj.setAttachmentId(attachmentId);
  
                      copyObj.setActivityId((Long)null);
                                                                                                      
                return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public ActivityPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("Activity:\n");
        str.append("ActivityId = ")
               .append(getActivityId())
             .append("\n");
        str.append("IssueId = ")
               .append(getIssueId())
             .append("\n");
        str.append("AttributeId = ")
               .append(getAttributeId())
             .append("\n");
        str.append("TransactionId = ")
               .append(getTransactionId())
             .append("\n");
        str.append("OldNumericValue = ")
               .append(getOldNumericValue())
             .append("\n");
        str.append("NewNumericValue = ")
               .append(getNewNumericValue())
             .append("\n");
        str.append("OldUserId = ")
               .append(getOldUserId())
             .append("\n");
        str.append("NewUserId = ")
               .append(getNewUserId())
             .append("\n");
        str.append("OldOptionId = ")
               .append(getOldOptionId())
             .append("\n");
        str.append("NewOptionId = ")
               .append(getNewOptionId())
             .append("\n");
        str.append("OldValue = ")
               .append(getOldValue())
             .append("\n");
        str.append("NewValue = ")
               .append(getNewValue())
             .append("\n");
        str.append("DependId = ")
               .append(getDependId())
             .append("\n");
        str.append("Description = ")
               .append(getDescription())
             .append("\n");
        str.append("EndDate = ")
               .append(getEndDate())
             .append("\n");
        str.append("AttachmentId = ")
               .append(getAttachmentId())
             .append("\n");
        return(str.toString());
    }
}
