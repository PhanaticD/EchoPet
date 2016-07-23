package com.dsh105.echopet.api.pet.particle;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.dsh105.echopet.compat.api.entity.IPet;
import com.dsh105.echopet.compat.api.particle.Trail;
import com.dsh105.echopet.compat.api.plugin.EchoPet;
import com.dsh105.echopet.compat.api.util.ParticleEffect;

/**
 * @Author Borlea
 * @Github https://github.com/borlea/
 * @Website http://codingforcookies.com/
 * @since Jul 11, 2016
 */
public class ParticleTrail implements Trail{

	private final String name, particleType, permission;
	private int tickDelay;
	private float speed;
	private int count;
	private double x, y, z;
	private float xOffset, yOffset, zOffset;
	private BukkitTask runnable;

	public ParticleTrail(String name, String particleType, String permission, int tickDelay, float speed, int count, double x, double y, double z, float xOffset, float yOffset, float zOffset){
		this.name = name;
		this.particleType = particleType;
		this.permission = permission;
		this.tickDelay = tickDelay;
		this.speed = speed;
		this.count = count;
		this.x = x;
		this.y = y;
		this.z = z;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.zOffset = zOffset;
	}

	@Override
	public String getName(){
		return name;
	}

	@Override
	public String getParticleType(){
		return particleType;
	}

	@Override
	public String getPermission(){
		return permission;
	}

	@Override
	public int getTickDelay(){
		return tickDelay;
	}

	@Override
	public float getSpeed(){
		return speed;
	}

	@Override
	public int getCount(){
		return count;
	}

	@Override
	public double getX(){
		return x;
	}

	@Override
	public double getY(){
		return y;
	}

	@Override
	public double getZ(){
		return z;
	}

	@Override
	public float getXOffset(){
		return xOffset;
	}

	@Override
	public float getYOffset(){
		return yOffset;
	}

	@Override
	public float getZOffset(){
		return zOffset;
	}

	@Override
	public void cancel(){
		if(runnable != null){
			runnable.cancel();
			runnable = null;
		}
	}

	@Override
	public BukkitTask run(final IPet pet){
		cancel();
		runnable = new BukkitRunnable(){

			public void run(){
				if(pet == null || pet.getOwner() == null || pet.getCraftPet() == null || pet.getCraftPet().getLocation() == null){
					cancel();
					return;
				}
				ParticleEffect.fromName(particleType).display(xOffset, yOffset, zOffset, speed, count, pet.getLocation().add(x, y, z), 256D);
			}
		}.runTaskTimer(EchoPet.getPlugin(), tickDelay, tickDelay);
		return runnable;
	}

	@Override
	public ParticleTrail clone(){
		return new ParticleTrail(name, particleType, permission, tickDelay, speed, count, x, y, z, xOffset, yOffset, zOffset);
	}
}
