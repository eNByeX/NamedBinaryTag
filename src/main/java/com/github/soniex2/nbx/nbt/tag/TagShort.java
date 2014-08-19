package com.github.soniex2.nbx.nbt.tag;

import com.github.soniex2.nbx.nbt.NBTWriter;

import java.io.IOException;

/**
 * @author soniex2
 */
public class TagShort implements ITag {

    private short data;

    public TagShort(short data) {
        this.data = data;
    }

    @Override
    public void writePayload(NBTWriter nbtWriter) throws IOException {
        nbtWriter.writeShort(data);
    }

}
