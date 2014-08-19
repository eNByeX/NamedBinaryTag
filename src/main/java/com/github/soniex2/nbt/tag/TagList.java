package com.github.soniex2.nbt.tag;

import com.github.soniex2.nbt.NBTReader;
import com.github.soniex2.nbt.NBTTagInfo;
import com.github.soniex2.nbt.NBTWriter;
import com.github.soniex2.nbt.tag.handler.ITagsetHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author soniex2
 */
public class TagList implements ITag {
    private ArrayList<ITag> tagList = new ArrayList<ITag>();

    private Class<? extends ITag> listType = null;

    public TagList() {
    }

    public TagList(NBTReader nbtReader) throws IOException {
        int type = nbtReader.readByte();
        int items = nbtReader.readInt();
        NBTTagInfo tagInfo = nbtReader.getTagInfo();
        List<ITagsetHandler> tagsetHandlers = tagInfo.getTagsetHandlers();
        Class<? extends ITag> tagType = tagInfo.getTag(type);
        if (tagType == null) throw new IOException("Unknown tag type");
        listType = tagType;
        outer:
        for (int i = 0; i < items; i++) {
            for (ITagsetHandler tagsetHandler : tagsetHandlers) {
                if (tagsetHandler.canRead(tagType)) {
                    // name = null
                    // = anonymous tag
                    ITag tag = tagsetHandler.readPayload(tagType, null, nbtReader);
                    if (!tag.getClass().equals(listType)) {
                        // Wut?!
                        throw new IOException("Found idiot: " + tagsetHandler.getClass().getName());
                    }
                    tagList.add(tag);
                    continue outer;
                }
            }
            throw new IOException();
        }
    }

    @Override
    public void writePayload(NBTWriter nbtWriter) throws IOException {
        NBTTagInfo tagInfo = nbtWriter.getTagInfo();
        int tagId = tagInfo.getTagId(listType);
        if (tagId == -1) throw new IOException();
        nbtWriter.writeByte(tagId);
        nbtWriter.writeInt(tagList.size());
        for (ITag tag : tagList) {
            if (!tag.getClass().equals(listType)) {
                // Wut?!
                throw new IOException();
            }
            tag.writePayload(nbtWriter);
        }
    }
}
