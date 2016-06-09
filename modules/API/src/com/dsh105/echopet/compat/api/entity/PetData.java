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

import java.util.List;

import com.google.common.collect.ImmutableList;

public enum PetData {

    ANGRY("angry", Type.BOOLEAN),
    BABY("baby", Type.BOOLEAN),
	BLACK("black", Type.COLOUR, Type.CAT, Type.HORSE_COLOR, Type.RABBIT_TYPE),
    BLACK_AND_WHITE("blackandwhite", Type.RABBIT_TYPE),
	BLACKSMITH("blacksmith", Type.PROF, Type.ZOMBIE_PROFESSION),
	BLACK_DOTS("blackSpot", Type.HORSE_STYLE),
    BLUE("blue", Type.COLOUR),
	BROWN("brown", Type.COLOUR, Type.RABBIT_TYPE),
	BUTCHER("butcher", Type.PROF, Type.ZOMBIE_PROFESSION),
    CHESTED("chested", Type.BOOLEAN),
	CHESTNUT("chestnut", Type.HORSE_COLOR),
	CREAMY("creamy", Type.HORSE_COLOR),
    CYAN("cyan", Type.COLOUR),
	DARK_BROWN("darkbrown", Type.HORSE_COLOR),
    DIAMOND("diamond", Type.HORSE_ARMOUR),
	DONKEY("donkey", Type.HORSE_VARIANT),
    ELDER("elder", Type.BOOLEAN),
	FARMER("farmer", Type.PROF, Type.ZOMBIE_PROFESSION),
    FIRE("fire", Type.BOOLEAN),
	GRAY("gray", Type.COLOUR, Type.HORSE_COLOR),
    GREEN("green", Type.COLOUR),
    GOLD("gold", Type.HORSE_ARMOUR),
	HUSK("husk", Type.ZOMBIE_PROFESSION),
    IRON("iron", Type.HORSE_ARMOUR),
    THE_KILLER_BUNNY("killerbunny", Type.RABBIT_TYPE),
    LARGE("large", Type.SIZE),
	LIBRARIAN("librarian", Type.PROF, Type.ZOMBIE_PROFESSION),
    LIGHTBLUE("lightBlue", Type.COLOUR),
    LIME("lime", Type.COLOUR),
    MAGENTA("magenta", Type.COLOUR),
    MEDIUM("medium", Type.SIZE),
	MULE("mule", Type.HORSE_VARIANT),
    NOARMOUR("noarmour", Type.HORSE_ARMOUR),
	NONE("noMarking", Type.HORSE_STYLE),
	HORSE("normal", Type.HORSE_VARIANT),
    ORANGE("orange", Type.COLOUR),
    PINK("pink", Type.COLOUR),
	POWER("powered", Type.BOOLEAN),
	PRIEST("priest", Type.PROF, Type.ZOMBIE_PROFESSION),
    PURPLE("purple", Type.COLOUR),
    RED("red", Type.CAT, Type.COLOUR),
    SADDLE("saddle", Type.BOOLEAN),
    SALT_AND_PEPPER("saltandpepper", Type.RABBIT_TYPE),
    SCREAMING("screaming", Type.BOOLEAN),
	SHEARED("sheared", Type.BOOLEAN),
    SHIELD("shield", Type.BOOLEAN),
    SIAMESE("siamese", Type.CAT),
    SILVER("silver", Type.COLOUR),
	SKELETON_HORSE("skeleton", Type.HORSE_VARIANT),
    SMALL("small", Type.SIZE),
    TAMED("tamed", Type.BOOLEAN),
	VILLAGER("villager", Type.BOOLEAN),
	WHITEFIELD("whitePatch", Type.HORSE_STYLE),
	WHITE_DOTS("whiteSpot", Type.HORSE_STYLE),
	WHITE_SOCKS("whiteSocks", Type.HORSE_STYLE),
	WHITE("white", Type.COLOUR, Type.HORSE_COLOR, Type.RABBIT_TYPE),
    WILD("wild", Type.CAT),
    WITHER("wither", Type.BOOLEAN),
    YELLOW("yellow", Type.COLOUR),
	UNDEAD_HORSE("zombie", Type.HORSE_VARIANT);


    private String configOptionString;
    private List<Type> t;

    PetData(String configOptionString, Type... t) {
        this.configOptionString = configOptionString;
        this.t = ImmutableList.copyOf(t);
    }

    public String getConfigOptionString() {
        return this.configOptionString;
    }

    public List<Type> getTypes() {
        return this.t;
    }

    public boolean isType(Type t) {
        return this.t.contains(t);
    }

    public enum Type {
		BOOLEAN,
		COLOUR,
		CAT,
		SIZE,
		PROF,
		ZOMBIE_PROFESSION,
		HORSE_VARIANT,
		HORSE_COLOR,
		HORSE_STYLE,
		HORSE_ARMOUR,
		RABBIT_TYPE
    }
}
