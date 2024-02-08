package de.anst.i18n;

import java.time.LocalDateTime;

import de.anst.data.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@FieldNameConstants
public class Translation extends AbstractEntity {

	@Getter @Setter
	@NonNull
    private String original;

	@Getter @Setter
	@NonNull
    private String locale;

	@Getter @Setter
	@NonNull
    private String translated;

	@Getter @Setter
    private LocalDateTime udate; // last update Date

	@Getter @Setter
    private LocalDateTime cdate; // creation Date

	@Getter @Setter
    private LocalDateTime rdate; // last read Date

    public Translation() {
    	cdate = LocalDateTime.now();
    	udate = cdate;
    }
    
    @PreUpdate
    public void preUpdateFunction(){
        udate = LocalDateTime.now();
    }
    

}
