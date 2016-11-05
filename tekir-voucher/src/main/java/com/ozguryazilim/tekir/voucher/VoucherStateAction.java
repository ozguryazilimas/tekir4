/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher;

import java.util.Objects;

/**
 * Voucher State Geçişleri için action tanımları.
 * 
 * 
 * 
 * @author Hakan Uygun
 */
public class VoucherStateAction {
    
    /**
     * Create işlemlerini tanımlamak için özel bir action. 
     * StateChangeEvent üzerinde kullanılır.
     */
    public static final VoucherStateAction CREATE = new VoucherStateAction("CREATE", "", Boolean.FALSE, "");
    
    private final String name;
    private final String icon;
    private final Boolean needDialog;
    private final String permission;

    public VoucherStateAction(String name, String icon, Boolean needDialog, String permission) {
        this.name = name;
        this.icon = icon;
        this.needDialog = needDialog;
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public Boolean hasDialog() {
        return needDialog;
    }
    
    public String getPermission() {
        return permission;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.name);
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
        final VoucherStateAction other = (VoucherStateAction) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    
}
