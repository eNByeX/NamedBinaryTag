package com.github.soniex2.nbx.nbt.tag;

import com.github.soniex2.nbx.nbt.NBTReader;
import com.github.soniex2.nbx.nbt.NBTWriter;

import java.io.IOException;

/**
 * @author soniex2
 */
public class TagIntArray implements ITag {
    private int[] data;

    public TagIntArray(int[] data) {
        this.data = data;
    }

    public TagIntArray(NBTReader nbtReader) throws IOException {
        data = new int[nbtReader.readInt()];
        for (int i = 0; i < data.length; i++) {
            data[i] = nbtReader.readInt();
        }
    }

    @Override
    public void writePayload(NBTWriter nbtWriter) throws IOException {
        nbtWriter.writeInt(data.length);
        for (int b : data) {
            nbtWriter.writeInt(b);
        }
    }
}
