package com.github.soniex2.nbx.nbt.tag;

import com.github.soniex2.nbx.nbt.NBTWriter;

import java.io.IOException;

/**
 * @author soniex2
 */
public class TagDouble implements ITag {
    private double data;

    public TagDouble(double data) {
        this.data = data;
    }

    @Override
    public void writePayload(NBTWriter nbtWriter) throws IOException {
        nbtWriter.writeDouble(data);
    }
}
