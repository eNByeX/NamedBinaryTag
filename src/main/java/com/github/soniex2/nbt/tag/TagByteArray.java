package com.github.soniex2.nbt.tag;

import com.github.soniex2.nbt.NBTReader;
import com.github.soniex2.nbt.NBTWriter;

import java.io.IOException;

/**
 * @author soniex2
 */
public class TagByteArray implements ITag {
    private byte[] data;

    public TagByteArray(byte[] data) {
        this.data = data;
    }

    public TagByteArray(NBTReader nbtReader) throws IOException {
        data = new byte[nbtReader.readInt()];
        for (int i = 0; i < data.length; i++) {
            data[i] = nbtReader.readByte();
        }
    }

    @Override
    public void writePayload(NBTWriter nbtWriter) throws IOException {
        nbtWriter.writeInt(data.length);
        for (byte b : data) {
            nbtWriter.writeByte(b);
        }
    }
}
