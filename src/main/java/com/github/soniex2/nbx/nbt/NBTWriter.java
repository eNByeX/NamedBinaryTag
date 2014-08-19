package com.github.soniex2.nbx.nbt;

import com.github.soniex2.nbx.nbt.tag.ITag;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author soniex2
 */
public class NBTWriter extends DataOutputStream {
    private NBTTagInfo tagInfo;
    private String rootTagName;

    public NBTWriter(OutputStream out, NBTTagInfo tagInfo, String rootTagName) {
        super(out);
        this.tagInfo = tagInfo;
        this.rootTagName = rootTagName;
    }

    public void writeTag(ITag tag) throws IOException {
        if (getRootTagName() == null) throw new IOException("No root tag name");
        int type = tagInfo.getTagId(tag.getClass());
        if (type == -1) throw new IOException();
        writeByte(type);
        writeUTF(getRootTagName());
        tag.writePayload(this);
    }

    public String getRootTagName() {
        return rootTagName;
    }

    public void setRootTagName(String rootTagName) {
        this.rootTagName = rootTagName;
    }

    public void setTagInfo(NBTTagInfo tagInfo) {
        this.tagInfo = tagInfo;
    }

    public NBTTagInfo getTagInfo() {
        return tagInfo;
    }
}
