package de.anst.person;

import java.time.LocalDate;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@Table(name = "persons")
@FieldNameConstants
public class Person extends AbstractEntity {

	@Getter @Setter
    private String firstName;
	
	@Getter @Setter
    private String lastName;

	@Email
	@Getter @Setter
	@NotNull
    private String email;
    
	@Getter @Setter
	private String phone;

	@Getter @Setter
    private LocalDate dateOfBirth;
	
	@Getter @Setter
    private String occupation;

	@Getter @Setter
	private String role;

	@Getter @Setter
    private boolean important;

	public static class Persister extends JpaCrudService<Person, Long, PersonRepository> {

		public Persister(PersonRepository repository) {
			super(repository);
		}
	}
}
