package com.github.soniex2.nbt;

import java.io.DataOutputStream;
import java.io.OutputStream;

/**
 * @author soniex2
 */
public class NBTWriter extends DataOutputStream {
    private NBTTagInfo tagInfo;

    public NBTWriter(OutputStream out, NBTTagInfo tagInfo) {
        super(out);
        this.tagInfo = tagInfo;
    }

    public void setTagInfo(NBTTagInfo tagInfo) {
        this.tagInfo = tagInfo;
    }

    public NBTTagInfo getTagInfo() {
        return tagInfo;
    }
}
