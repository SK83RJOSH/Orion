package com.chopdawg.josh.orion.utils;

import java.util.Comparator;

import com.chopdawg.josh.orion.entities.Entity;

public class IndexComparator implements Comparator<Object> {
	public int compare(Object o1, Object o2) {
		if(((Entity) o1).getZ() > ((Entity) o2).getZ())
			return 1;
		else if(((Entity) o1).getZ() == ((Entity) o2).getZ())
			return  0;
		else
			return -1;
	}
}
