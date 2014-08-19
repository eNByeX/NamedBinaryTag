package com.github.soniex2.nbt.tag;

import com.github.soniex2.nbt.NBTReader;
import com.github.soniex2.nbt.NBTTagInfo;
import com.github.soniex2.nbt.NBTWriter;
import com.github.soniex2.nbt.tag.handler.ITagsetHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author soniex2
 */
public class TagCompound implements ITag {

    private HashMap<String, ITag> tagMap = new HashMap<String, ITag>();

    public TagCompound() {
    }

    public TagCompound(NBTReader nbtReader) throws IOException {
        NBTTagInfo tagInfo = nbtReader.getTagInfo();
        List<ITagsetHandler> tagsetHandlers = tagInfo.getTagsetHandlers();
        parseLoop:
        while (true) {
            byte type = nbtReader.readByte();
            Class<? extends ITag> tagType = tagInfo.getTag(type);
            if (tagType == null) {
                throw new IOException(String.format("Unknown tag type %d", type));
            }
            if (TagEnd.class.equals(tagType)) {
                break;
            }
            for (ITagsetHandler tagsetHandler : tagsetHandlers) {
                if (tagsetHandler.canRead(tagType)) {
                    String tagName = tagsetHandler.readName(tagType, nbtReader);
                    ITag tag = tagsetHandler.readPayload(tagType, tagName, nbtReader);
                    setTag(tagName, tag);
                    continue parseLoop;
                }
            }
            throw new IOException();
        }
    }

    public void setTag(String tagName, ITag tag) {
        if (tagName == null || tag == null) throw new NullPointerException();
        tagMap.put(tagName, tag);
    }

    public ITag getTag(String tagName) {
        return tagMap.get(tagName);
    }

    @Override
    public void writePayload(NBTWriter nbtWriter) throws IOException {
        NBTTagInfo tagInfo = nbtWriter.getTagInfo();
        for (Map.Entry<String, ITag> entry : tagMap.entrySet()) {
            ITag tag = entry.getValue();
            int tagType = tagInfo.getTagId(tag.getClass());
            if (tagType == -1) {
                throw new IOException();
            }
            nbtWriter.writeByte(tagType);
            nbtWriter.writeUTF(entry.getKey());
            tag.writePayload(nbtWriter);
        }
        // Write TAG_End
        int tagEnd = tagInfo.getTagId(TagEnd.class);
        if (tagEnd == -1) throw new IOException();
        nbtWriter.writeByte(tagEnd);
    }

}
