/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher;

import java.util.Objects;
import org.apache.deltaspike.core.api.config.view.ViewConfig;

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
    private Integer order = 50;
    private Boolean silence = Boolean.FALSE;

    public VoucherStateAction(String name, String icon, Boolean needDialog, String permission) {
        this.name = name;
        this.icon = icon;
        this.needDialog = needDialog;
        this.permission = permission;
    }
    
    /**
     * Permission için action name kullanılacak
     * @param name
     * @param icon
     * @param needDialog 
     */
    public VoucherStateAction(String name, String icon, Boolean needDialog) {
        this.name = name;
        this.icon = icon;
        this.needDialog = needDialog;
        this.permission = name;
    }
    
    /**
     * Permission için action name kullanılacak. Dialog kullanmayacak
     * @param name
     * @param icon
     */
    public VoucherStateAction(String name, String icon) {
        this.name = name;
        this.icon = icon;
        this.needDialog = Boolean.FALSE;
        this.permission = name;
    }

    /**
     * Sesiz bir transition için kullanılır.
     * 
     * UI üzerinde kullanıcıya gösterilmez. Bir State'den diğerine sadece programatik olarak geçiş için kullanılır.
     * 
     * @param name 
     */
    public VoucherStateAction(String name) {
        this.name = name;
        this.icon = "";
        this.needDialog = Boolean.FALSE;
        this.permission = "";
        this.silence = Boolean.TRUE;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getSilence() {
        return silence;
    }

    
    public Class<? extends ViewConfig> execute(){
        return null;
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
