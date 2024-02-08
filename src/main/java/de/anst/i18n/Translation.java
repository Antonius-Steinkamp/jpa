package de.anst.i18n;

import java.time.LocalDateTime;

import de.anst.data.AbstractEntity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@FieldNameConstants
public class Translation extends AbstractEntity {

	@Getter @Setter
	@NotNull
    private String original;

	@Getter @Setter
	@NotNull
    private String locale;

	@Getter @Setter
	@NotNull
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
