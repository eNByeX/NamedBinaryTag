package com.github.soniex2.nbx.nbt.tag;

import com.github.soniex2.nbx.nbt.NBTWriter;

import java.io.IOException;

/**
 * @author soniex2
 */
public class TagLong implements ITag {

    private long data;

    public TagLong(long data) {
        this.data = data;
    }

    @Override
    public void writePayload(NBTWriter nbtWriter) throws IOException {
        nbtWriter.writeLong(data);
    }
}
