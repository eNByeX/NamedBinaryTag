package com.github.soniex2.nbx.nbt.tag.handler;

import com.github.soniex2.nbx.nbt.NBTReader;
import com.github.soniex2.nbx.nbt.tag.ITag;

import java.io.IOException;

/**
 * @author soniex2
 */
public interface ITagsetHandler {

    boolean canRead(Class<? extends ITag> tagType);

    String readName(Class<? extends ITag> tagType, NBTReader nbtReader) throws IOException;

    ITag readPayload(Class<? extends ITag> tagType, String name, NBTReader nbtReader) throws IOException;
}
