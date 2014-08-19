package com.github.soniex2.nbt.tag;

import com.github.soniex2.nbt.NBTWriter;

import java.io.IOException;

/**
 * @author soniex2
 */
public interface ITag {

    public void writePayload(NBTWriter nbtWriter) throws IOException;

}
