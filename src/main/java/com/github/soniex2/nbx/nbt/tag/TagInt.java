package com.github.soniex2.nbx.nbt.tag;

import com.github.soniex2.nbx.nbt.NBTWriter;

import java.io.IOException;

/**
 * @author soniex2
 */
public class TagInt implements ITag {

    private int data;

    public TagInt(int data) {
        this.data = data;
    }

    @Override
    public void writePayload(NBTWriter nbtWriter) throws IOException {
        nbtWriter.writeInt(data);
    }
}
