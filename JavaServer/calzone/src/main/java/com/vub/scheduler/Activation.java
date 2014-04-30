package com.vub.scheduler;

public class Activation {
	/**
	 * The name of the rule which was activated.
	 */
	private String ruleName;

	/**
	 * Default no-args constructor.
	 */
	public Activation() {
	}

	/**
	 * Simple constructor for just noting a rule name for an activation.
	 * 
	 * @param ruleName
	 *            The name of the rule which was activated.
	 */
	public Activation(String ruleName) {
		this.ruleName = ruleName;
	}

	/**
	 * @return The name of the rule which was activated.
	 */
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * @param ruleName The name of the rule which was activated.
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Activation[ruleName=" + ruleName);
		sb.append("]");
		return sb.toString();
	}
}
