package com.vub.model;

import java.util.Comparator;

public class ProfileSlotComparator  implements Comparator<ProfileSlot> {

	@Override
	public int compare(ProfileSlot o1, ProfileSlot o2) {
		return o1.getDate().compareTo(o2.getDate());
	}

}
