package com.chopdawg.josh.orion.utils;

import java.util.Comparator;

import com.chopdawg.josh.orion.bb.IBounding;

/**
 * 
 * @author SK83RJOSH
 */
public class IndexComparator implements Comparator<Object> {
	public int compare(Object o1, Object o2) {
		if(((IBounding) o1).getZ() > ((IBounding) o2).getZ())
			return 1;
		else if(((IBounding) o1).getZ() == ((IBounding) o2).getZ())
			return  0;
		else
			return -1;
	}
}
