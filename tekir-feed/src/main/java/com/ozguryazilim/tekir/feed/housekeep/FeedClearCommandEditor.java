package com.ozguryazilim.tekir.feed.housekeep;

import com.ozguryazilim.tekir.feed.config.FeedPages;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditor;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditorBase;

/**
 *
 * @author oyas
 */
@CommandEditor(command = FeedClearCommand.class, page = FeedPages.FeedClearCommand.class)
public class FeedClearCommandEditor extends CommandEditorBase<FeedClearCommand>{

    @Override
    public FeedClearCommand createNewCommand() {
        FeedClearCommand cm = new FeedClearCommand();
        
        cm.setInterval("30d");
        
        return cm;
    }
    
}
