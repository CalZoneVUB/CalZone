package com.vub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DisplayName class which is used in the Room class. 
 * Background info: A room does not necessarily have a separate display name (more often than not it won't),
 * and thus these are kept in a different table, so we don't get a lot of null-values in the DB.
 * They are thus associated with a different model.7
 * 
 * @author Sam
 *
 */
@Entity
@Table(name="DisplayRoom")
public class DisplayName {
	@Id
	@GeneratedValue
	@Column(name="RoomID")
	int id;
	@Column(name="DisplayName")
	String displayName;

	/** 
	 * Get the display name
	 * @return	the display name
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * Set a new display name
	 * @param displayName A new display name
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
