package com.dsh105.echopet.compat.api.particle;

import org.bukkit.scheduler.BukkitTask;

import com.dsh105.echopet.compat.api.entity.IPet;

/**
 * @Author Borlea
 * @Github https://github.com/borlea/
 * @Website http://codingforcookies.com/
 * @since Jul 11, 2016
 */
public interface Trail{

	public String getName();

	public String getParticleType();

	public String getPermission();

	public int getTickDelay();

	public float getSpeed();

	public int getCount();

	public double getX();

	public double getY();

	public double getZ();

	public float getXOffset();

	public float getYOffset();

	public float getZOffset();

	public void cancel();

	public BukkitTask run(final IPet pet);

	public Trail clone();
}
