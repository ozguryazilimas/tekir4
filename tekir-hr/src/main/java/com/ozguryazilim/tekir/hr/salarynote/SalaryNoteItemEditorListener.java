/**
 * 
 */
package com.ozguryazilim.tekir.hr.salarynote;

import com.ozguryazilim.tekir.entities.SalaryNoteItem;

/**
 * @author oktay
 *
 */
public interface SalaryNoteItemEditorListener <E extends SalaryNoteItem>{

    /**
     * Editor için yeni bir satır ekleyip editorü açar.
     */
    public void addItem();
    
    public void editItem(E item);
    
    public void removeItem(int index);
    
    /**
     * Editor sonucu editlenen değer.
     * 
     * @param item 
     */
    void saveItem( E item );
    
    public void calculateSummaries();

}
