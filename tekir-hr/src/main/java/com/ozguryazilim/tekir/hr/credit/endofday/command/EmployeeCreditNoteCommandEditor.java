/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.hr.credit.endofday.command;

import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.hr.config.EmployeePages;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditor;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditorBase;
import javax.inject.Inject;

/**
 * Personel Alacak Fişi oluşturma için komut editörü.
 *
 * Personel Alacak Fişi komutunun oluşturulduğu ve düzenlendiği sınıf.
 * 
 * @author Erdem Uslu
 * @version 4.0.0
 * @since 2017-07-10
 * @see CommandEditor
 * @see CommandEditorBase
 * @see EmployeeCreditNoteCommand
 * @see EmployeePages
 */
@CommandEditor(command = EmployeeCreditNoteCommand.class, page = EmployeePages.EmployeeCreditNoteCommand.class)
public class EmployeeCreditNoteCommandEditor extends CommandEditorBase<EmployeeCreditNoteCommand>{

    @Inject
    private CurrencyService currencyService;
    
    @Override
    public EmployeeCreditNoteCommand createNewCommand() {
        EmployeeCreditNoteCommand result = new EmployeeCreditNoteCommand();
        
        result.setCurrency(currencyService.getDefaultCurrency());
        
        return result;
    }
    
}
