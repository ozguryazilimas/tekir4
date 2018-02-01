/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity.email.imports.commands;

import com.ozguryazilim.telve.messagebus.command.AbstractCommand;

/**
 * Tek bir e-posta taşır içerisinde.
 * <p>
 * Böylece importer kuyruktan çıkan her bir e-posta ile tek tek ilgilenir.
 *
 * @author Hakan Uygun
 */
public class EMailImportCommand extends AbstractCommand {

    private String eml;
    private EmailDirection direction;

    public EMailImportCommand() {
    }

    public EMailImportCommand(String eml, EmailDirection direction) {
        this.eml = eml;
        this.direction = direction;
    }

    public String getEml() {
        return eml;
    }

    public void setEml(String eml) {
        this.eml = eml;
    }

    public EmailDirection getDirection() {
        return direction;
    }

    public void setDirection(EmailDirection direction) {
        this.direction = direction;
    }
}
