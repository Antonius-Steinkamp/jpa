package de.anst.person;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

import org.vaadin.crudui.crud.CrudListener;

import de.anst.data.AbstractEntity;
import de.anst.data.BaseService;
import de.anst.data.JpaCrudService;

@Entity
@Table(name = "persons")
public class Person extends AbstractEntity {

	@Getter @Setter
    private String firstName;
	
	@Getter @Setter
    private String lastName;

	@Email
	@Getter @Setter
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

	public static class VPersister extends BaseService<Person, Long, PersonRepository> {

		public VPersister(PersonRepository repository) {
			super(repository);
		}
	}
	
	public static class CPersister extends JpaCrudService<Person, Long> {
		
		public CPersister(PersonRepository repository) {
			super(repository);
		}
		
	}
}
