package com.ozguryazilim.tekir.core.exchange.tcmb.command;

import com.ozguryazilim.tekir.core.config.CorePages;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditor;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditorBase;

/**
 *
 * @author oyas
 */
@CommandEditor(command = TcmbExchangeRatesCommand.class, page = CorePages.TcmbExchangeRatesCommand.class)
public class TcmbExchangeRatesCommandEditor extends CommandEditorBase<TcmbExchangeRatesCommand>{

    @Override
    public TcmbExchangeRatesCommand createNewCommand() {
        TcmbExchangeRatesCommand cm = new TcmbExchangeRatesCommand();
               
        return cm;
    }
    
}
