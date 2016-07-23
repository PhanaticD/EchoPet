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

package com.dsh105.echopet.compat.api.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;

import com.dsh105.echopet.compat.api.particle.Trail;

public interface IPet{

	public IEntityPet spawnPet(Player owner);

    public IEntityPet getEntityPet();

    public Creature getCraftPet();

    public Location getLocation();

    public Player getOwner();

    public String getNameOfOwner();

    public UUID getOwnerUUID();

    public Object getOwnerIdentification();

    public PetType getPetType();

    public boolean isRider();

    public IPet getRider();

    public boolean isOwnerInMountingProcess();

    public String getPetName();

    public String getPetNameWithoutColours();
    
    public String serialisePetName();

    public boolean setPetName(String name);

    public boolean setPetName(String name, boolean sendFailMessage);

	public ArrayList<PetData> getPetData();

	public void removeRider(boolean makeSound, boolean makeParticles);

	public void removePet(boolean makeSound, boolean makeParticles);

    public boolean teleportToOwner();

    public boolean teleport(Location to);

    public boolean isOwnerRiding();

    public boolean isHat();

    public void ownerRidePet(boolean flag);

	public void setAsHat(boolean flag);

    public IPet createRider(final PetType pt, boolean sendFailMessage);

	public InventoryView getInventoryView();

	public void setInventoryView(InventoryView dataMenu);

	public List<Trail> getTrails();

	public void addTrail(Trail trail);

	public void removeTrail(Trail trail);
}