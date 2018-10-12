package com.ozguryazilim.tekir.activity.email.imports.model;

import java.io.Serializable;

/**
 *
 * @author oyas
 */
public class EMailAttacment implements Serializable{
    
    private String name;
    private String mimeType;
    private Integer size;
    private byte[] content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "EMailAttacment{" + "name=" + name + ", mimeType=" + mimeType + ", size=" + size + '}';
    }
    
    
}
