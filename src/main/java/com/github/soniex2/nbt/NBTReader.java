package com.github.soniex2.nbt;

import com.github.soniex2.nbt.tag.ITag;
import com.github.soniex2.nbt.tag.handler.ITagsetHandler;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author soniex2
 */
public class NBTReader extends DataInputStream {
    private NBTTagInfo tagInfo;

    public NBTReader(InputStream in, NBTTagInfo tagInfo) {
        super(in);
        this.tagInfo = tagInfo;
    }

    public void setTagInfo(NBTTagInfo tagInfo) {
        this.tagInfo = tagInfo;
    }

    public ITag readTag() {
        List<ITagsetHandler> tagsetHandlers = tagInfo.getTagsetHandlers();
        // todo
        return null;
    }

    public NBTTagInfo getTagInfo() {
        return tagInfo;
    }
}
