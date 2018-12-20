package com.ozguryazilim.tekir.feed;

import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.entities.Note;
import com.ozguryazilim.telve.forms.EntityChangeAction;
import com.ozguryazilim.telve.forms.EntityChangeEvent;
import com.ozguryazilim.telve.qualifiers.After;
import com.ozguryazilim.telve.qualifiers.EntityQualifier;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;

/**
 * Telve Note girişlerini yakalayıp bunları Feed sistemine ekler.
 * 
 * @author Hakan Uygun
 */
@Feeder
public class NoteFeeder extends AbstractFeeder<Note>{
    
    /**
     * Note entity'si change event'i yakalıyıp feed haline getirir.
     * 
     * @param event 
     */
    public void feed(@Observes(during = TransactionPhase.AFTER_SUCCESS) @After @EntityQualifier(entity = Note.class) EntityChangeEvent event ){
        
        if( event.getAction() != EntityChangeAction.INSERT) return;
        
        if( event.getEntity() instanceof Note ){
            Note note = (Note) event.getEntity();
            
            List<FeaturePointer> mentions = new ArrayList<>();
            mentions.add(note.getFeaturePointer());
            
            sendFeed( note.getPriority(), getClass().getSimpleName(), note.getOwner(), note.getFeaturePointer().getBusinessKey(), note.getBody(), mentions);
        }
        
    }
    
}
