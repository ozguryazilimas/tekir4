package com.ozguryazilim.tekir.core.code;

import java.io.Serializable;
import java.util.Objects;

/**
 * AutoCode için meta veri model sınıfı.
 * 
 * AutoCode annotation'ı ile verilen bilgiler ve gerektiğinde config/kahve'den alınanlar ile oluşturulur.
 * 
 * @see AutoCode
 * 
 * @author Hakan Uygun
 */
public class AutoCodeMetaModel implements Serializable{
    
    /**
     * Eğer verilmez ise üzerinde tanımlandığı sınıfın adını kullanır
     * @return 
     */
    private String cosumer;
    
    /**
     * Bu kod üretici için varsayılan davranış.
     * @return 
     */
    private AutoCodeType type = AutoCodeType.SEMIAUTOMATIC;
    
    /**
     * Bu ayarların kullanıcı tarafından değiştirilip değiştirilemiyeceği
     * @return 
     */
    private boolean configurable = true;
    
    /**
     * Caption için kullanılacak message-key.
     * 
     * Eğer verilmez ise autoCode.consumer.{consumer()} değeri kullanılır.
     * @return 
     */
    private String caption;
    
    /**
     * Sıra numarasının varsayılan uzunluk bilgisi.
     * 
     * Varsayılan 6
     * 
     * @return 
     */
    private int size = 6;
    
    /**
     * Üretilecek kod için varsayılan prefix.
     * 
     * Eğer verilmez ise comsumer değerinin ilk üç karakteri kullanılır.
     * @return 
     */
    private String serial;

    public String getCosumer() {
        return cosumer;
    }

    public void setCosumer(String cosumer) {
        this.cosumer = cosumer;
    }

    public AutoCodeType getType() {
        return type;
    }

    public void setType(AutoCodeType type) {
        this.type = type;
    }

    public boolean isConfigurable() {
        return configurable;
    }

    public void setConfigurable(boolean configurable) {
        this.configurable = configurable;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.cosumer);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AutoCodeMetaModel other = (AutoCodeMetaModel) obj;
        if (!Objects.equals(this.cosumer, other.cosumer)) {
            return false;
        }
        return true;
    }
    
    
}
