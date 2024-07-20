package com.Assignment.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class JsonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Lob
    private String jsonmodel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJsonmodel() {
		return jsonmodel;
	}

	public void setJsonmodel(String jsonmodel) {
		this.jsonmodel = jsonmodel;
	}

	@Override
	public String toString() {
		return "JsonModel [id=" + id + ", jsonmodel=" + jsonmodel + "]";
	}

	public JsonModel() {
		super();
		// TODO Auto-generated constructor stub
	}

    
    // Getters and Setters
}
