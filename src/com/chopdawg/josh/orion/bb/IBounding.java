package com.chopdawg.josh.orion.bb;

/**
 * 
 * @author SK83RJOSH
 */
public interface IBounding {
	public void makeSolid();
	public boolean isSolid();
	public void set(float x, float y, float z);
	public float getX();
	public float getY();
	public float getZ();
	public int getWidth();
	public int getHeight();
}