package com.github.soniex2.nbx.nbt.tag.handler;

import com.github.soniex2.nbx.nbt.NBTReader;
import com.github.soniex2.nbx.nbt.tag.*;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author soniex2
 */
public class DefaultTagsetHandler implements ITagsetHandler {
    private static final HashMap<Class<? extends ITag>, Integer> readable = new HashMap<Class<? extends ITag>, Integer>();

    static {
        readable.put(TagEnd.class, 0);
        readable.put(TagByte.class, 1);
        readable.put(TagShort.class, 2);
        readable.put(TagInt.class, 3);
        readable.put(TagLong.class, 4);
        readable.put(TagFloat.class, 5);
        readable.put(TagDouble.class, 6);
        readable.put(TagByteArray.class, 7);
        readable.put(TagString.class, 8);
        readable.put(TagList.class, 9);
        readable.put(TagCompound.class, 10);
        readable.put(TagIntArray.class, 11);
    }

    @Override
    public boolean canRead(Class<? extends ITag> tagType) {
        return readable.containsKey(tagType);
    }

    @Override
    public String readName(Class<? extends ITag> tagType, NBTReader nbtReader) throws IOException {
        if (!readable.containsKey(tagType)) throw new IOException("Unknown tag: " + tagType.getName());
        switch (readable.get(tagType)) {
            case 0:
                return null;
            default:
                return nbtReader.readUTF();
        }
    }

    @Override
    public ITag readPayload(Class<? extends ITag> tagType, String name, NBTReader nbtReader) throws IOException {
        switch (readable.get(tagType)) {
            case 0: // Shouldn't happen
                throw new IOException("Mismatched TAG_End");
            case 1:
                return new TagByte(nbtReader.readByte());
            case 2:
                return new TagShort(nbtReader.readShort());
            case 3:
                return new TagInt(nbtReader.readInt());
            case 4:
                return new TagLong(nbtReader.readLong());
            case 5:
                return new TagFloat(nbtReader.readFloat());
            case 6:
                return new TagDouble(nbtReader.readDouble());
            case 7:
                return new TagByteArray(nbtReader);
            case 8:
                return new TagString(nbtReader.readUTF());
            case 9:
                return new TagList(nbtReader);
            case 10:
                return new TagCompound(nbtReader);
            case 11:
                return new TagIntArray(nbtReader);
            default:
                throw new IOException("Unknown tag: " + tagType.getName());
        }
    }
}
