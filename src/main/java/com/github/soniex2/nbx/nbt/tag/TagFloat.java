package com.github.soniex2.nbx.nbt.tag;

import com.github.soniex2.nbx.nbt.NBTWriter;

import java.io.IOException;

/**
 * @author soniex2
 */
public class TagFloat implements ITag {

    private float data;

    public TagFloat(float data) {
        this.data = data;
    }

    @Override
    public void writePayload(NBTWriter nbtWriter) throws IOException {
        nbtWriter.writeFloat(data);
    }
}
