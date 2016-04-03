/*
 * This file is part of EchoPet.
 *
 * EchoPet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * EchoPet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with EchoPet.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.dsh105.echopet.compat.nms.v1_9_R1.entity.type;

import java.util.UUID;

import org.bukkit.entity.Horse;

import com.dsh105.echopet.compat.api.entity.*;
import com.dsh105.echopet.compat.api.entity.type.nms.IEntityHorsePet;
import com.dsh105.echopet.compat.nms.v1_9_R1.entity.EntityAgeablePet;
import com.google.common.base.Optional;

import net.minecraft.server.v1_9_R1.*;

@EntitySize(width = 1.4F, height = 1.6F)
@EntityPetType(petType = PetType.HORSE)
public class EntityHorsePet extends EntityAgeablePet implements IEntityHorsePet {

	private static final DataWatcherObject<Byte> VISUAL = DataWatcher.a(EntityHorsePet.class, DataWatcherRegistry.a);
	private static final DataWatcherObject<Integer> TYPE = DataWatcher.a(EntityHorsePet.class, DataWatcherRegistry.b);
	private static final DataWatcherObject<Integer> VARIANT = DataWatcher.a(EntityHorsePet.class, DataWatcherRegistry.b);
	private static final DataWatcherObject<Optional<UUID>> OWNER = DataWatcher.a(EntityHorsePet.class, DataWatcherRegistry.m);
	private static final DataWatcherObject<Integer> ARMOR = DataWatcher.a(EntityHorsePet.class, DataWatcherRegistry.b);
    private int rearingCounter = 0;
    int stepSoundCount = 0;

    public EntityHorsePet(World world) {
        super(world);
    }

    public EntityHorsePet(World world, IPet pet) {
        super(world, pet);
    }

    @Override
    public void setSaddled(boolean flag) {
		this.setHorseVisual(4, flag);
    }

	public EnumHorseType getType(){
		return EnumHorseType.a(this.datawatcher.get(TYPE));
	}

	public void setType(HorseType ht){
		setVariant(ht.getBukkitVariant());
	}

	public void setVariant(Horse.Variant variant){
		if(variant != Horse.Variant.HORSE){
			this.setArmour(HorseArmour.NONE);
		}
		this.datawatcher.set(TYPE, Integer.valueOf(EnumHorseType.values()[variant.ordinal()].k()));
	}

	public int getVariant(){
		return this.datawatcher.get(VARIANT);
	}

	public void setVariant(HorseVariant v, HorseMarking m){
		setColor(v.getBukkitColour());
		setStyle(m.getBukkitStyle());
	}

	public void setColor(Horse.Color color){
		this.datawatcher.set(VARIANT, (color.ordinal() & 0xFF | getStyle().ordinal() << 8));
	}

	public void setStyle(Horse.Style style){
		this.datawatcher.set(VARIANT, getColor().ordinal() & 0xFF | style.ordinal() << 8);
	}

	public Horse.Style getStyle(){
		return Horse.Style.values()[(getVariant() >>> 8)];
	}

	public Horse.Color getColor(){
		return Horse.Color.values()[(getVariant() & 0xFF)];
	}

	/*
	@Override
	public void setType(HorseType t) {
	    if (t != HorseType.NORMAL) {
	        this.setArmour(HorseArmour.NONE);
	    }
			this.datawatcher.set(TYPE, Integer.valueOf(t.getId()));
	}
	
	@Override
	public void setVariant(HorseVariant v, HorseMarking m) {
		this.datawatcher.set(VARIANT, m.getId(v));
	}*/

    @Override
    public void setArmour(HorseArmour a) {
		this.datawatcher.set(ARMOR, Integer.valueOf(a.getId()));
    }

    @Override
    public void setChested(boolean flag) {
        this.setHorseVisual(8, flag);
    }

    @Override
    public boolean attack(Entity entity) {
        boolean flag = super.attack(entity);
        if (flag) {
			setHorseVisual(64, true);
			if(getType().g()){
				makeSound("entity.horse.angry", 1.0F, 1.0F);
			}else{
				makeSound("entity.donkey.angry", 1.0F, 1.0F);
			}
        }
        return flag;
    }

    @Override
    protected void initDatawatcher() {
        super.initDatawatcher();
		this.datawatcher.register(VISUAL, Byte.valueOf((byte) 0));
		this.datawatcher.register(TYPE, Integer.valueOf(EnumHorseType.HORSE.k()));
		this.datawatcher.register(VARIANT, Integer.valueOf(0));
		this.datawatcher.register(OWNER, Optional.absent());
		this.datawatcher.register(ARMOR, Integer.valueOf(EnumHorseArmor.NONE.a()));
    }

    @Override
	protected void makeStepSound(BlockPosition pos, Block block){
		SoundEffectType soundeffecttype = block.w();

		if(this.world.getType(pos) == Blocks.SNOW){
			soundeffecttype = Blocks.SNOW_LAYER.w();
        }

		if(!block.getBlockData().getMaterial().isLiquid()){
			EnumHorseType enumhorsetype = getType();
			if((isVehicle()) && (!enumhorsetype.g())){
				this.stepSoundCount += 1;
				if((this.stepSoundCount > 5) && (this.stepSoundCount % 3 == 0)){
					makeSound("entity.horse.gallop", soundeffecttype.a() * 0.15F, soundeffecttype.b());
					if((enumhorsetype == EnumHorseType.HORSE) && (this.random.nextInt(10) == 0)){
						makeSound("entity.horse.breathe", soundeffecttype.a() * 0.6F, soundeffecttype.b());
					}
				}else if(this.stepSoundCount <= 5){
					makeSound("entity.horse.step_wood", soundeffecttype.a() * 0.15F, soundeffecttype.b());
				}
			}else if(soundeffecttype == SoundEffectType.a){
				makeSound("entity.horse.step_wood", soundeffecttype.a() * 0.15F, soundeffecttype.b());
			}else{
				makeSound("entity.horse.step", soundeffecttype.a() * 0.15F, soundeffecttype.b());
			}
        }
    }

    @Override
    public void g(float sideMot, float forwMot) {
        super.g(sideMot, forwMot);
        if (forwMot <= 0.0F) {
            this.stepSoundCount = 0;
        }
    }

    @Override
    public SizeCategory getSizeCategory() {
        if (this.isBaby()) {
            return SizeCategory.TINY;
        } else {
            return SizeCategory.LARGE;
        }
    }

    @Override
    public void onLive() {
        super.onLive();
        if (rearingCounter > 0 && ++rearingCounter > 20) {
			setHorseVisual(64, false);
        }
    }

    @Override
    protected void doJumpAnimation() {
		makeSound("entity.horse.gallop", 0.4F, 1.0F);
        this.rearingCounter = 1;
		setHorseVisual(64, true);
    }

	/**
	 * 2: Is tamed
	 * 4: Saddle
	 * 8: Has Chest
	 * 16: Bred
	 * 32: Eating haystack
	 * 64: Rear
	 * 128: Mouth open
	 */
	private boolean getHorseVisual(int i){
		return (((Byte) this.datawatcher.get(VISUAL)).byteValue() & i) != 0;
	}
	
	/**
	 * 2: Is tamed
	 * 4: Saddle
	 * 8: Has Chest
	 * 16: Bred
	 * 32: Eating haystack
	 * 64: Rear
	 * 128: Mouth open
	 */
	private void setHorseVisual(int i, boolean flag){
		byte b0 = ((Byte) this.datawatcher.get(VISUAL)).byteValue();
		if(flag){
			this.datawatcher.set(VISUAL, Byte.valueOf((byte) (b0 | i)));
		}else{
			this.datawatcher.set(VISUAL, Byte.valueOf((byte) (b0 & (i ^ 0xFFFFFFFF))));
		}
	}
}
