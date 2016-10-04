
package com.retail.app.to;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Item {

    @SerializedName("identifier")
    @Expose
    private List<Identifier> identifier = new ArrayList<Identifier>();
    @SerializedName("errors")
    @Expose
    private List<Errors> errors = new ArrayList<Errors>();
    @SerializedName("relation")
    @Expose
    private String relation;
    @SerializedName("relation_description")
    @Expose
    private String relationDescription;
    @SerializedName("data_page_link")
    @Expose
    private String dataPageLink;
    @SerializedName("imn_identifier")
    @Expose
    private Integer imnIdentifier;
    @SerializedName("is_orderable")
    @Expose
    private Boolean isOrderable;
    @SerializedName("is_sellable")
    @Expose
    private Boolean isSellable;
    @SerializedName("general_description")
    @Expose
    private String generalDescription;
    @SerializedName("is_circular_publish")
    @Expose
    private Boolean isCircularPublish;
    @SerializedName("business_process_status")
    @Expose
    private List<BusinessProcessStatu> businessProcessStatus = new ArrayList<BusinessProcessStatu>();
    @SerializedName("dpci")
    @Expose
    private String dpci;
    @SerializedName("department_id")
    @Expose
    private Integer departmentId;
    @SerializedName("class_id")
    @Expose
    private Integer classId;
    @SerializedName("item_id")
    @Expose
    private Integer itemId;
    @SerializedName("online_description")
    @Expose
    private OnlineDescription onlineDescription;
    @SerializedName("store_description")
    @Expose
    private StoreDescription storeDescription;
    @SerializedName("alternate_description")
    @Expose
    private List<AlternateDescription> alternateDescription = new ArrayList<AlternateDescription>();
    @SerializedName("features")
    @Expose
    private List<Feature> features = new ArrayList<Feature>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Item() {
    }

    /**
     * 
     * @param onlineDescription
     * @param generalDescription
     * @param classId
     * @param relationDescription
     * @param relation
     * @param isSellable
     * @param itemId
     * @param businessProcessStatus
     * @param alternateDescription
     * @param imnIdentifier
     * @param storeDescription
     * @param features
     * @param dataPageLink
     * @param isCircularPublish
     * @param departmentId
     * @param isOrderable
     * @param dpci
     * @param identifier
     */
    public Item(List<Identifier> identifier, List<Errors> errors, String relation, String relationDescription, String dataPageLink, Integer imnIdentifier, Boolean isOrderable, Boolean isSellable, String generalDescription, Boolean isCircularPublish, List<BusinessProcessStatu> businessProcessStatus, String dpci, Integer departmentId, Integer classId, Integer itemId, OnlineDescription onlineDescription, StoreDescription storeDescription, List<AlternateDescription> alternateDescription, List<Feature> features) {
        this.identifier = identifier;
        this.errors = errors;
        this.relation = relation;
        this.relationDescription = relationDescription;
        this.dataPageLink = dataPageLink;
        this.imnIdentifier = imnIdentifier;
        this.isOrderable = isOrderable;
        this.isSellable = isSellable;
        this.generalDescription = generalDescription;
        this.isCircularPublish = isCircularPublish;
        this.businessProcessStatus = businessProcessStatus;
        this.dpci = dpci;
        this.departmentId = departmentId;
        this.classId = classId;
        this.itemId = itemId;
        this.onlineDescription = onlineDescription;
        this.storeDescription = storeDescription;
        this.alternateDescription = alternateDescription;
        this.features = features;
    }

    /**
     * 
     * @return
     *     The identifier
     */
    public List<Identifier> getIdentifier() {
        return identifier;
    }

    /**
     * 
     * @param identifier
     *     The identifier
     */
    public void setIdentifier(List<Identifier> identifier) {
        this.identifier = identifier;
    }

    
    
    public List<Errors> getErrors() {
		return errors;
	}

	public void setErrors(List<Errors> errors) {
		this.errors = errors;
	}

	/**
     * 
     * @return
     *     The relation
     */
    public String getRelation() {
        return relation;
    }

    /**
     * 
     * @param relation
     *     The relation
     */
    public void setRelation(String relation) {
        this.relation = relation;
    }

    /**
     * 
     * @return
     *     The relationDescription
     */
    public String getRelationDescription() {
        return relationDescription;
    }

    /**
     * 
     * @param relationDescription
     *     The relation_description
     */
    public void setRelationDescription(String relationDescription) {
        this.relationDescription = relationDescription;
    }

    /**
     * 
     * @return
     *     The dataPageLink
     */
    public String getDataPageLink() {
        return dataPageLink;
    }

    /**
     * 
     * @param dataPageLink
     *     The data_page_link
     */
    public void setDataPageLink(String dataPageLink) {
        this.dataPageLink = dataPageLink;
    }

    /**
     * 
     * @return
     *     The imnIdentifier
     */
    public Integer getImnIdentifier() {
        return imnIdentifier;
    }

    /**
     * 
     * @param imnIdentifier
     *     The imn_identifier
     */
    public void setImnIdentifier(Integer imnIdentifier) {
        this.imnIdentifier = imnIdentifier;
    }

    /**
     * 
     * @return
     *     The isOrderable
     */
    public Boolean getIsOrderable() {
        return isOrderable;
    }

    /**
     * 
     * @param isOrderable
     *     The is_orderable
     */
    public void setIsOrderable(Boolean isOrderable) {
        this.isOrderable = isOrderable;
    }

    /**
     * 
     * @return
     *     The isSellable
     */
    public Boolean getIsSellable() {
        return isSellable;
    }

    /**
     * 
     * @param isSellable
     *     The is_sellable
     */
    public void setIsSellable(Boolean isSellable) {
        this.isSellable = isSellable;
    }

    /**
     * 
     * @return
     *     The generalDescription
     */
    public String getGeneralDescription() {
        return generalDescription;
    }

    /**
     * 
     * @param generalDescription
     *     The general_description
     */
    public void setGeneralDescription(String generalDescription) {
        this.generalDescription = generalDescription;
    }

    /**
     * 
     * @return
     *     The isCircularPublish
     */
    public Boolean getIsCircularPublish() {
        return isCircularPublish;
    }

    /**
     * 
     * @param isCircularPublish
     *     The is_circular_publish
     */
    public void setIsCircularPublish(Boolean isCircularPublish) {
        this.isCircularPublish = isCircularPublish;
    }

    /**
     * 
     * @return
     *     The businessProcessStatus
     */
    public List<BusinessProcessStatu> getBusinessProcessStatus() {
        return businessProcessStatus;
    }

    /**
     * 
     * @param businessProcessStatus
     *     The business_process_status
     */
    public void setBusinessProcessStatus(List<BusinessProcessStatu> businessProcessStatus) {
        this.businessProcessStatus = businessProcessStatus;
    }

    /**
     * 
     * @return
     *     The dpci
     */
    public String getDpci() {
        return dpci;
    }

    /**
     * 
     * @param dpci
     *     The dpci
     */
    public void setDpci(String dpci) {
        this.dpci = dpci;
    }

    /**
     * 
     * @return
     *     The departmentId
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * 
     * @param departmentId
     *     The department_id
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 
     * @return
     *     The classId
     */
    public Integer getClassId() {
        return classId;
    }

    /**
     * 
     * @param classId
     *     The class_id
     */
    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    /**
     * 
     * @return
     *     The itemId
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * 
     * @param itemId
     *     The item_id
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * 
     * @return
     *     The onlineDescription
     */
    public OnlineDescription getOnlineDescription() {
        return onlineDescription;
    }

    /**
     * 
     * @param onlineDescription
     *     The online_description
     */
    public void setOnlineDescription(OnlineDescription onlineDescription) {
        this.onlineDescription = onlineDescription;
    }

    /**
     * 
     * @return
     *     The storeDescription
     */
    public StoreDescription getStoreDescription() {
        return storeDescription;
    }

    /**
     * 
     * @param storeDescription
     *     The store_description
     */
    public void setStoreDescription(StoreDescription storeDescription) {
        this.storeDescription = storeDescription;
    }

    /**
     * 
     * @return
     *     The alternateDescription
     */
    public List<AlternateDescription> getAlternateDescription() {
        return alternateDescription;
    }

    /**
     * 
     * @param alternateDescription
     *     The alternate_description
     */
    public void setAlternateDescription(List<AlternateDescription> alternateDescription) {
        this.alternateDescription = alternateDescription;
    }

    /**
     * 
     * @return
     *     The features
     */
    public List<Feature> getFeatures() {
        return features;
    }

    /**
     * 
     * @param features
     *     The features
     */
    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
