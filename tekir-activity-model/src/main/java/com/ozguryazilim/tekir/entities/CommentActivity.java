package com.ozguryazilim.tekir.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author oyas
 */
@Entity
@DiscriminatorValue("COMMENT")
public class CommentActivity extends Activity{
    
}
