/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * Voucher'ların durumlarını takip etmek için bir veri modeli.
 * 
 * Bir enum yerine bir nesne olmasının temel nedeni, Durum bilgisinin:
 * - Taslak, Açık, Kapalı 
 * - Olumlu, Olumsuz, Nötr 
 * olma hallerinin de önemli olması.
 * 
 * Ayrıca farklı voucher'lar farklı state'ler tanımlayabilirler. Bunun için VoucherFormBase üzerinde fasit bir FSM kullanılacak.
 * 
 * Bu model veri tabanına JPA Converter ile yazılacak ve tek bir String alanda duracak. Böylece Like ile arama yapılabilecek.
 * 
 * @author Hakan Uygun
 */
public class VoucherState implements Serializable{
    
    //StateChangeEvent için ilk create operasyonunda kullanmak için. Initial state gibi düşünülebilir.
    public static final VoucherState CREATE = new VoucherState( "CREATE", VoucherStateType.DRAFT, VoucherStateEffect.NEUTRAL);
    public static final VoucherState DRAFT = new VoucherState( "DRAFT", VoucherStateType.DRAFT, VoucherStateEffect.NEUTRAL);
    public static final VoucherState OPEN = new VoucherState( "OPEN", VoucherStateType.OPEN, VoucherStateEffect.NEUTRAL);
    public static final VoucherState CLOSE = new VoucherState( "CLOSE", VoucherStateType.CLOSE, VoucherStateEffect.NEUTRAL);
    
    private final String name;
    private final VoucherStateType type;
    private final VoucherStateEffect effect;

    public VoucherState(String name, VoucherStateType type, VoucherStateEffect effect) {
        this.name = name;
        this.type = type;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public VoucherStateType getType() {
        return type;
    }

    public VoucherStateEffect getEffect() {
        return effect;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.type);
        hash = 41 * hash + Objects.hashCode(this.effect);
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
        final VoucherState other = (VoucherState) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (this.effect != other.effect) {
            return false;
        }
        return true;
    }

    /**
     * type-effect-name formatı ile verilen string'den VoucherState oluşturur.
     * 
     * @param s
     * @return 
     */
    public static VoucherState valueOf( String s ){
        //TODO: Acaba exception mı versek?
        if( s == null || s.length() == 0 ){
            return DRAFT;
        }
        
        String[] ss = s.split("-");
        return new VoucherState( ss[2], VoucherStateType.valueOf(ss[0]), VoucherStateEffect.valueOf(ss[1]) );
    }
    
    @Override
    public String toString() {
        return type + "-" + effect + "-" + name;
    }
    
    
}
