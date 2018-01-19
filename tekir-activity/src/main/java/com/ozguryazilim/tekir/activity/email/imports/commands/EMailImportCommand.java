/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity.email.imports.commands;

import com.ozguryazilim.telve.messagebus.command.AbstractCommand;

/**
 * Tek bir e-posta taşır içerisinde. 
 * 
 * Böylece importer kuyruktan çıkan her bir e-posta ile tek tek ilgilenir.
 * 
 * @author Hakan Uygun
 */
public class EMailImportCommand extends AbstractCommand{
    
    private String eml;

    public String getEml() {
        return eml;
    }

    public void setEml(String eml) {
        this.eml = eml;
    }
    
    
}
