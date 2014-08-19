package com.github.soniex2.nbx.nbt;

import com.github.soniex2.nbx.nbt.tag.ITag;
import com.github.soniex2.nbx.nbt.tag.TagCompound;
import com.github.soniex2.nbx.nbt.tag.handler.ITagsetHandler;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author soniex2
 */
public class NBTReader extends DataInputStream {
    private NBTTagInfo tagInfo;
    private String rootTagName = null;

    public NBTReader(InputStream in, NBTTagInfo tagInfo) {
        super(in);
        this.tagInfo = tagInfo;
    }

    public void setTagInfo(NBTTagInfo tagInfo) {
        this.tagInfo = tagInfo;
    }

    public ITag readTag() throws IOException {
        List<ITagsetHandler> tagsetHandlers = tagInfo.getTagsetHandlers();
        byte type = readByte();
        Class<? extends ITag> tagType = tagInfo.getTag(type);
        if (!TagCompound.class.equals(tagType)) {
            throw new IOException();
        }
        for (ITagsetHandler tagsetHandler : tagsetHandlers) {
            if (tagsetHandler.canRead(tagType)) {
                rootTagName = tagsetHandler.readName(tagType, this);
                return tagsetHandler.readPayload(tagType, rootTagName, this);
            }
        }
        throw new IOException();
    }

    public String getRootTagName() {
        return rootTagName;
    }

    public NBTTagInfo getTagInfo() {
        return tagInfo;
    }
}
