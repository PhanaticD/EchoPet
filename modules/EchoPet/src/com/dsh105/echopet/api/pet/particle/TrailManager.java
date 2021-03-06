package com.dsh105.echopet.api.pet.particle;

import java.util.List;

import org.bukkit.configuration.ConfigurationSection;

import com.dsh105.commodus.config.YAMLConfig;
import com.dsh105.echopet.compat.api.particle.Trail;
import com.dsh105.echopet.compat.api.particle.Trails;
import com.dsh105.echopet.compat.api.util.ParticleEffect;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * @Author Borlea
 * @Github https://github.com/borlea/
 * @Website http://codingforcookies.com/
 * @since Jul 11, 2016
 */
public class TrailManager implements Trails{

	List<ParticleTrail> trails = Lists.newArrayList();

	public TrailManager(YAMLConfig config){
		if(config.config().isSet("trails")){
			ConfigurationSection cs = config.getConfigurationSection("trails");
			for(String key : cs.getKeys(false)){
				String particleName = config.getString("trails." + key + ".particleName").toUpperCase();
				if(ParticleEffect.fromName(particleName) == null){
					System.out.println("Unknown particle effect: " + particleName);
					return;
				}
				String permission = config.getString("trails." + key + ".permission");
				int interval = config.getInt("trails." + key + ".interval");
				float speed = (float) config.getDouble("trails." + key + ".speed");
				int count = config.getInt("trails." + key + ".count");
				double x = config.getDouble("trails." + key + ".x");
				double y = config.getDouble("trails." + key + ".y");
				double z = config.getDouble("trails." + key + ".z");
				float xOffset = (float) config.getDouble("trails." + key + ".xOffset");
				float yOffset = (float) config.getDouble("trails." + key + ".yOffset");
				float zOffset = (float) config.getDouble("trails." + key + ".zOffset");
				ParticleTrail particle = new ParticleTrail(key, particleName, permission, interval, speed, count, x, y, z, xOffset, yOffset, zOffset);
				addTrail(particle);
			}
		}
	}

	public List<Trail> getTrails(){// should be using a set..but i wanna transform
		return Lists.transform(trails, new Function<ParticleTrail, Trail>(){

			public Trail apply(ParticleTrail t){
				return (Trail) t;
			}
		});
	}

	public Trail getTrailByName(String name){
		for(Trail particle : getTrails()){
			if(particle.getName().equalsIgnoreCase(name)) return particle;
		}
		return null;
	}

	public void addTrail(ParticleTrail particle){
		trails.add(particle);
	}
}
