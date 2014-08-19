package com.github.soniex2.nbx.nbt;

import com.github.soniex2.nbx.nbt.tag.*;
import com.github.soniex2.nbx.nbt.tag.handler.DefaultTagsetHandler;
import com.github.soniex2.nbx.nbt.tag.handler.ITagsetHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author soniex2
 */
public class NBTTagInfo {
    @SuppressWarnings("unchecked")
    private final Class<? extends ITag>[] tags = new Class[256];
    private List<ITagsetHandler> handlers = new ArrayList<ITagsetHandler>();

    public void registerTag(int i, Class<? extends ITag> tag) {
        tags[i & 0xFF] = tag;
    }

    public Class<? extends ITag> getTag(int id) {
        return tags[id & 0xFF];
    }

    public void registerTagsetHandler(ITagsetHandler handler) {
        handlers.add(handler);
    }

    public void defaultTags() {
        registerTagsetHandler(new DefaultTagsetHandler());
        registerTag(0, TagEnd.class);
        registerTag(1, TagByte.class);
        registerTag(2, TagShort.class);
        registerTag(3, TagInt.class);
        registerTag(4, TagLong.class);
        registerTag(5, TagFloat.class);
        registerTag(6, TagDouble.class);
        registerTag(7, TagByteArray.class);
        registerTag(8, TagString.class);
        registerTag(9, TagList.class);
        registerTag(10, TagCompound.class);
        registerTag(11, TagIntArray.class);
    }

    public List<ITagsetHandler> getTagsetHandlers() {
        return Collections.unmodifiableList(handlers);
    }

    public int getTagId(Class<? extends ITag> tag) {
        // no better way to do this :(
        for (int i = 0; i < tags.length; i++) {
            if (tags[i].equals(tag)) return i;
        }
        return -1;
    }
}
