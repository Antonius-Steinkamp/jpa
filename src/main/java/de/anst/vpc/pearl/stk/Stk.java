/**
 * PearlType.java created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.pearl.stk;

import org.springframework.stereotype.Component;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import de.anst.vpc.cc.ControlCycle;
import de.anst.vpc.pearl.Pearl;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

/**
 * PearlType created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */

@Entity
@Table(name = "stk")
@FieldNameConstants
@NoArgsConstructor
public class Stk  extends AbstractEntity implements Comparable<Stk>{

	@NotNull
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="pearl_id", nullable=false)

    private Pearl pearl;

	@NotNull
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="cc_id", nullable=false)
    private ControlCycle cc;

	@PositiveOrZero
	@Getter @Setter
	private Double menge;

	@Override
	public int compareTo(Stk o) { // TODO
		return 0;
	}

	@Component
	public static class Persister extends JpaCrudService<Stk, Long, StkRepository> {

		private static final long serialVersionUID = Stk.Persister.class.hashCode();

		protected final StkRepository repository;
		public Persister(StkRepository repository) {
			super(repository);
			this.repository = repository;
			if (repository.count() == 0) {
			}
		}
	}



}
