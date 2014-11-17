package com.datasnap.android.eventsandproperties;

public class Communication extends Property{
	
	private String description;
    private Tags tags;
    private String id;
    private String status;
    private String communicationVendorId;
    private String name;
    private Type types;
    private Content content;
	
	
    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Tags getPropTags() {
		return tags;
	}
	public void setPropTags(Tags tags) {
		this.tags = tags;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCommunicationVendorId() {
		return communicationVendorId;
	}
	public void setCommunicationVendorId(String communicationVendorId) {
		this.communicationVendorId = communicationVendorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Type getPropTypes() {
		return types;
	}
	public void setPropTypes(Type types) {
		this.types = types;
	}
	public Content getPropContent() {
		return content;
	}
	public void setPropContent(Content content) {
		this.content = content;
	}

    
    
	
    
    
}



