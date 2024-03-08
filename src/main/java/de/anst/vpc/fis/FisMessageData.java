/**
 * FisMessage.java created 05.03.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.fis;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

/**
 * FisMessage created 05.03.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
@Data
@ToString
public class FisMessageData {
	private String name; // Name dieser Perle
	private String type; // von diesem Typ
	private String mp; // Hier gemeldet
	private LocalDateTime mpDate; // Dann wurde diese Meldung erstellt
}
