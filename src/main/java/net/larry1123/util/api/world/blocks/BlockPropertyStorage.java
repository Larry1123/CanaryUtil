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
package net.larry1123.util.api.world.blocks;

import net.canarymod.Canary;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.properties.BlockProperty;
import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Made to store a BlockProperty
 * <p/>
 * Must store the Block's Type can't get a new BlockProperty for remaking from json without it
 *
 * @author Larry1123
 * @since 4/16/2015 - 5:32 PM
 */
public class BlockPropertyStorage {

    private final String name;
    private final String blockTypeMachineName;
    private final short blockTypeData;
    private final Comparable value;

    private BlockPropertyStorage(String name, String blockTypeMachineName, short blockTypeData, Comparable<? extends Object> value) {
        this.name = name;
        this.blockTypeMachineName = blockTypeMachineName;
        this.blockTypeData = blockTypeData;
        this.value = value;
    }

    public BlockPropertyStorage(BlockProperty blockProperty, Block block) {
        this(blockProperty.getName(), block.getType().getMachineName(), block.getType().getData(), block.getValue(blockProperty));
    }

    public BlockPropertyStorage(String name, Block block) {
        this(block.getPropertyForName(name), block);
    }

    public BlockPropertyStorage(String json) throws ParseException, ClassNotFoundException {
        // TODO add a bit of validation
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(json);
        name = (String) jsonObject.get("keyName");
        blockTypeMachineName = (String) jsonObject.get("blockTypeMachineName");
        blockTypeData = (Short) jsonObject.get("blockTypeData");
        try {
            byte[] bytes = Base64.decodeBase64((String) jsonObject.get("value"));
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
            value = (Comparable) objectInputStream.readObject();
        }
        catch (IOException e) {
            throw new Error();
            // Not sure how that could happen but I will see what I may need to do
        }
    }

    /**
     * Gets the Property's name
     *
     * @return property name
     */
    public String getName() {
        return name;
    }

    public BlockType getBlockType() {
        return BlockType.fromStringAndData(blockTypeMachineName, blockTypeData);
    }

    public BlockProperty getBlockProperty() {
        return Canary.factory().getObjectFactory().getPropertyInstance(getBlockType(), getName());
    }

    public Comparable getValue() {
        return value;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("keyName", getName());
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(getValue());
            String base64Object = Base64.encodeBase64String(byteArrayOutputStream.toByteArray());
            jsonObject.put("value", base64Object);
        }
        catch (IOException e) {
            e.printStackTrace();
            // TODO throw
        }
        jsonObject.put("blockTypeMachineName", blockTypeMachineName);
        jsonObject.put("blockTypeData", blockTypeData);
        return jsonObject;
    }

    public String toJSONString() {
        return toJSONObject().toJSONString();
    }

}
