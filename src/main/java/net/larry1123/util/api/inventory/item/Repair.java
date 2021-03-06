/*
 * Copyright 2014 ElecEntertainment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.larry1123.util.api.inventory.item;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.BaseItem;
import net.canarymod.api.inventory.Item;

/**
 * @author Larry1123
 * @since 1/26/14 - 5:45 AM
 */
public class Repair {

    /**
     * Repairs the item in the player's hand fully
     *
     * @param player The player to have an item repaired for
     */
    public static void repairItemInHand(Player player) {
        if (player == null) {
            return;
        }
        Item item = player.getItemHeld();
        repairItem(item);
    }

    /**
     * Repairs the item in the player's hand by the requested amount
     *
     * @param player The player to have an item repaired for
     * @param amount How much to repair the item by
     */
    public static void repairItemInHand(Player player, int amount) {
        if (player == null) {
            return;
        }
        Item item = player.getItemHeld();
        repairItem(item, amount);
    }

    /**
     * Fully repairs an item, if the item is damageable.
     * Will not do anything if the item is not damaged.
     * Will not set the damage above the max damage that the item can have.
     *
     * @param item What item to be repaired
     */
    public static void repairItem(Item item) {
        if (item == null) {
            return;
        }
        if (item.getId() == 0) {
            return;
        }
        if (item.getAmount() == 0) {
            return;
        }
        repairItem(item, getMaxDamage(item));
    }

    /**
     * Repairs an item, if the item is damageable, for the given amount.
     * Will not do anything if the item is not damaged.
     * Will not set the damage above the max damage that the item can have.
     *
     * @param item What item to be repaired
     * @param amount How much to repair the item by
     */
    public static void repairItem(Item item, int amount) {
        if (item == null) {
            return;
        }
        if (item.getId() == 0) {
            return;
        }
        if (item.getAmount() == 0) {
            return;
        }
        BaseItem baseItem = item.getBaseItem();
        repairItem(item, baseItem, amount);
    }

    private static void repairItem(Item item, BaseItem baseItem, int amount) {
        if (canRepairItem(item, baseItem)) {
            if (getMaxDamage(item) - item.getDamage() + amount < getMaxDamage(item)) {
                item.setDamage(item.getDamage() - amount);
            }
            else {
                item.setDamage(0);
            }
        }
    }

    /**
     * Checks if this item is able to be repaired
     *
     * @param item Item to check
     *
     * @return {@code true} if item can be repaired, {@code false} item can not be repaired, or is not damaged
     */
    public static boolean canRepairItem(Item item) {
        return item != null && canRepairItem(item, item.getBaseItem());
    }

    public static boolean isItemDamaged(Item item) {
        return item.getBaseItem().getMaxDamage() - item.getDamage() != 0;
    }

    /**
     * Checks if this item is able to be repaired
     *
     * @param item Item to check
     * @param baseItem BaseItem that is to this Item
     *
     * @return {@code true} if item can be repaired, {@code false} item can not be repaired, or is not damaged
     */
    private static boolean canRepairItem(Item item, BaseItem baseItem) {
        return (item != null && baseItem != null) && baseItem.isDamageable() && isItemDamaged(item);
    }

    private static int getMaxDamage(Item item) {
        return item.getBaseItem().getMaxDamage();
    }

}
