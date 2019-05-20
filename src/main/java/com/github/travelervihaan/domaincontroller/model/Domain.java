package com.github.travelervihaan.domaincontroller.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="domain")
public class Domain {
	
	@Id
	private String id;
	private String domainName;
	
	public Domain(String domainName) {
		this.domainName = domainName;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	@Override
    public String toString() {
        return domainName;
    }
	
}
