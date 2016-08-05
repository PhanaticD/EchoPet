package com.dsh105.echopet.api.pet.particle;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
	private Trail parentTrail;
	private Collection<String> subTrailNames;
	private Set<Trail> subTrails;
	private BukkitTask runnable;

	public ParticleTrail(String name, String particleType, String permission, Collection<String> subTrailNames, int tickDelay, float speed, int count, double x, double y, double z, float xOffset, float yOffset, float zOffset){
		this.subTrails = new HashSet<>();
		this.name = name;
		this.particleType = particleType;
		this.permission = permission;
		this.subTrailNames = subTrailNames;
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
	public Trail getParentTrail(){
		return parentTrail;
	}

	@Override
	public void setParentTrail(Trail parentTrail){
		this.parentTrail = parentTrail;
	}

	@Override
	public Collection<String> getSubTrailNames(){
		return subTrailNames;
	}

	@Override
	public Set<Trail> getSubTrails(){
		return subTrails;
	}

	@Override
	public void addSubTrail(Trail subTrail){
		subTrails.add(subTrail);
	}

	public void start(final IPet pet){
		cancel();
		runnable = new BukkitRunnable(){

			public void run(){
				if(pet == null || pet.getOwner() == null || pet.getCraftPet() == null || pet.getCraftPet().getLocation() == null){
					cancel();
					return;
				}
				displayTrail(pet);
			}
		}.runTaskTimer(EchoPet.getPlugin(), tickDelay, tickDelay);
		for(Trail trail : subTrails){
			if(trail.getTickDelay() != getTickDelay()){
				trail.start(pet);
				// TODO: Make it search other subTrails for another trail with the same tick delay.
				// With that you can save a few bukkit runnables.
			}
		}
	}

	@Override
	public void cancel(){
		if(runnable != null){
			runnable.cancel();
			runnable = null;
		}
		for(Trail trail : subTrails){
			trail.cancel();
		}
	}

	public boolean isRunning(){
		return runnable != null;
	}

	@Override
	public void displayTrail(final IPet pet){
		ParticleEffect.fromName(particleType).display(xOffset, yOffset, zOffset, speed, count, pet.getLocation().add(x, y, z), 256D);
		for(Trail trail : subTrails){
			if(trail.getTickDelay() == getTickDelay()){
				trail.displayTrail(pet);
			}
		}
	}

	@Override
	public ParticleTrail clone(){
		// We can clone the subTrailNames because it should never be modified after loading.
		ParticleTrail clone = new ParticleTrail(name, particleType, permission, subTrailNames, tickDelay, speed, count, x, y, z, xOffset, yOffset, zOffset);
		Set<Trail> subTrailClone = new HashSet<>();
		for(Trail trail : getSubTrails()){
			Trail trailClone = trail.clone();
			trailClone.setParentTrail(clone);
			subTrailClone.add(trailClone);
		}
		clone.getSubTrails().addAll(subTrailClone);
		return clone;
	}
}
