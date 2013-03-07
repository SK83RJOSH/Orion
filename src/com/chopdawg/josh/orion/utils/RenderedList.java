package com.chopdawg.josh.orion.utils;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;

import com.chopdawg.josh.orion.bb.IBounding;

@SuppressWarnings("hiding")
public class RenderedList<IBounding> extends AbstractList<IBounding> {
    private ArrayList<IBounding> internalList = new ArrayList<IBounding>();

    public void add(int position, IBounding e) {
        internalList.add(e);
        sort();
    }

    public IBounding get(int i) {
        return internalList.get(i);
    }

    public int size() {
        return internalList.size();
    }
    
    public void drop(IBounding e) {
    	if(internalList.contains(e))
    		internalList.remove(e);
    }
    
    public void sort() {
    	Collections.sort(internalList, new IndexComparator());
    }
}