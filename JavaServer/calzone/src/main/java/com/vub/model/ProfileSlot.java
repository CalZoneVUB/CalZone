package com.vub.model;

import java.util.List;

public class ProfileSlot {
	private String title;
	private List<String> descriptions;
	private String iconText;
	private Badge badge;
	private Color color;
	
	public enum Badge {
		bullhorn, time;
		}
	public enum Color {
		warning, success, error;
		}
	
	
	/**
	 * @return the badge
	 */
	public Badge getBadge() {
		return badge;
	}
	/**
	 * @param badge the badge to set
	 */
	public void setBadge(Badge badge) {
		this.badge = badge;
	}
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return the descriptions
	 */
	public List<String> getDescriptions() {
		return descriptions;
	}
	/**
	 * @param descriptions the descriptions to set
	 */
	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}
	/**
	 * @return the iconText
	 */
	public String getIconText() {
		return iconText;
	}
	/**
	 * @param iconText the iconText to set
	 */
	public void setIconText(String iconText) {
		this.iconText = iconText;
	};
	
	
	
	

}