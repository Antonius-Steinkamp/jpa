/**
 * FisMessage.java created 05.03.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.fis;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

/**
 * FisMessage created 05.03.2024 by
 * <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
@Entity
@Table(name = "fismessage", uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "mpName" }, name = "name_mp") })

@FieldNameConstants
@NoArgsConstructor
public class FisMessage extends AbstractEntity implements Comparable<FisMessage> {

	@Getter
	@Setter
	@NotNull
	private String name; // Name dieser Perle

	@Getter
	@Setter
	@NotNull
	private String type; // von diesem Typ

	@Getter
	@Setter
	@NotNull
	private String mpName; // Der externe Name

	@Getter
	@Setter
	@NotNull
	private LocalDateTime mpDate; // Dann wurde diese Meldung erstellt

	@Getter
	@Setter
	private LocalDateTime arrivalTime; // arrival-Time

	public FisMessage(FisMessageData data) {
		super();

		name = data.getName();
		type = data.getType();
		mpName = data.getMp();
		mpDate = data.getMpDate();

		arrivalTime = LocalDateTime.now();
	}

	@Override
	public int compareTo(FisMessage o) {
		if (mpDate.isAfter(o.getMpDate())) {
			return 1;
		}
		if (mpDate.isBefore(o.getMpDate())) {
			return -1;
		}

		return 0;
	}

	@Component
	public static class Persister extends JpaCrudService<FisMessage, Long, FisMessageRepository> {
		private static final long serialVersionUID = FisMessage.Persister.class.hashCode();

		private final FisMessageRepository repository;

		public Persister(FisMessageRepository repository) {
			super(repository);
			this.repository = repository;

			if (repository.count() == 0) {
				// Generate Data (if necessary)
			}
		}
		
		public List<FisMessage> findByNameAndMpName(String name, String mpName) {
			return repository.findByNameAndMpName(name, mpName);
		}
	}

}
