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
package net.larry1123.util.api.entity;

import net.canarymod.ToolBox;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.position.Location;

/**
 * TODO need to remake this class kind of derp
 */
public class EtoBLib {

    /**
     * One Block Back
     *
     * @param eLocation
     *
     * @return
     */
    public Block blokeB(Location eLocation) {

        Location location = new Location(eLocation.getWorld(), eLocation.getX(), eLocation.getY(), eLocation.getZ(), eLocation.getPitch(), eLocation.getRotation());

        Block block = null;
        Boolean f_b = false;

        getBlocky(block, f_b, location);

        return block;

    }

    /**
     * One Block in front
     *
     * @param eLocation
     *
     * @return
     */
    public Block blokey(Location eLocation) {

        Location location = new Location(eLocation.getWorld(), eLocation.getX(), eLocation.getY(), eLocation.getZ(), eLocation.getPitch(), eLocation.getRotation());

        Block block = null;
        Boolean f_b = true;

        getBlocky(block, f_b, location);

        return block;

    }

    public void centerLocation(Location loc) {
        loc.setX(ToolBox.floorToBlock(loc.getX()));
        loc.setY(ToolBox.floorToBlock(loc.getY()));
        loc.setZ(ToolBox.floorToBlock(loc.getZ()));
    }

    public void getBlocky(Block block, Boolean f_b, Location location) {

        // gets the location on to the right block
        centerLocation(location);

        BlockFace direction = getDirection(location.getRotation());

        block = location.getWorld().getBlockAt((int) location.getX(), (int) location.getY(), (int) location.getZ());

        if (f_b) {
            block = block.getFacingBlock(direction);
        }
        else {
            switch (direction) {
                case EAST:
                    block = block.getFacingBlock(BlockFace.WEST);
                    break;
                case NORTH:
                    block = block.getFacingBlock(BlockFace.SOUTH);
                    break;
                case SOUTH:
                    block = block.getFacingBlock(BlockFace.NORTH);
                    break;
                case WEST:
                    block = block.getFacingBlock(BlockFace.EAST);
                    break;
                default:
                    setBlockToAir(block, location);
                    break;
            }
        }

        if (location.getY() > 256) {
            setBlockToAir(block, location);
        }
    }

    public BlockFace getDirection(float number) {

        BlockFace f = BlockFace.UNKNOWN;

        if ((number >= 135) || (number <= -135)) {
            f = BlockFace.NORTH;
        }
        if ((number > -135) && (number < -45)) {
            f = BlockFace.EAST;
        }
        if ((number >= -45) && (number <= 45)) {
            f = BlockFace.SOUTH;
        }
        if ((number > 45) && (number < 135)) {
            f = BlockFace.WEST;
        }

        return f;
    }

    public void setBlockToAir(Block block, Location location) {
        block.setType(BlockType.Air);
        block.setX((int) location.getX());
        block.setY((int) location.getY());
        block.setZ((int) location.getZ());
    }

}
