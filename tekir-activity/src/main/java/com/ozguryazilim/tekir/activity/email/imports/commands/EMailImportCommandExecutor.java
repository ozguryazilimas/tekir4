/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity.email.imports.commands;

import com.ozguryazilim.tekir.activity.email.imports.EMailActivityImporter;
import com.ozguryazilim.telve.messagebus.command.AbstractCommandExecuter;
import com.ozguryazilim.telve.messagebus.command.CommandExecutor;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author oyas
 */
@CommandExecutor(command = EMailImportCommand.class)
public class EMailImportCommandExecutor extends AbstractCommandExecuter<EMailImportCommand>{

    private static final Logger LOG = LoggerFactory.getLogger(EMailImportCommandExecutor.class);
    @Inject
    private EMailActivityImporter importer;
    
    @Override
    public void execute(EMailImportCommand command) {
        
        LOG.debug("EMail Activity Import Start");
        
        importer.importMail(command.getEml());
    }
    
}
