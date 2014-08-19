package com.github.soniex2.nbt.tag;

import com.github.soniex2.nbt.NBTWriter;

import java.io.IOException;

/**
 * @author soniex2
 */
public class TagByte implements ITag {

    private byte data;

    public TagByte(byte data) {
        this.data = data;
    }

    @Override
    public void writePayload(NBTWriter nbtWriter) throws IOException {
        nbtWriter.writeByte(data);
    }
}
