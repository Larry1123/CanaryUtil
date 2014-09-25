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
package net.larry1123.util.api.err;

import net.canarymod.api.inventory.Item;

/**
 * @author Larry1123
 * @since 9/25/2014 - 6:22 PM
 */
public final class ItemChecks {

    /**
     * Checks if the item is air
     *
     * @param item    The Item to check
     * @throws IllegalStateException if {@code item} is air
     */
    public static void itemNotAir(Item item) {
        if (item.getId() == 0) {
            throw new IllegalStateException();
        }
    }

    /**
     * Checks if the item is air
     *
     * @param item            The Item to check
     * @param errorMessage    The error message to use if the check fails
     * @throws IllegalStateException if {@code item} is air
     */
    public static void itemNotAir(Item item, Object errorMessage) {
        if (item.getId() == 0) {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }

    /**
     * Checks if the item has an amount
     *
     * @param item    The Item to check
     * @throws IllegalStateException if {@code item} has an amount of 0
     */
    public static void itemHasAmount(Item item) {
        if (item.getAmount() == 0) {
            throw new IllegalStateException();
        }
    }

    /**
     * Checks if the item has an amount
     *
     * @param item            The Item to check
     * @param errorMessage    The error message to use if the check fails
     * @throws IllegalStateException if {@code item} has an amount of 0
     */
    public static void itemHasAmount(Item item, Object errorMessage) {
        if (item.getAmount() == 0) {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }

}
