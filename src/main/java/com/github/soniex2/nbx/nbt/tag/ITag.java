package com.github.soniex2.nbx.nbt.tag;

import com.github.soniex2.nbx.nbt.NBTWriter;

import java.io.IOException;

/**
 * @author soniex2
 */
public interface ITag {

    public void writePayload(NBTWriter nbtWriter) throws IOException;

}
