package org.springframework.samples.petclinic.owner;

public record OwnerDto(Integer id, String firstName, String lastName, String telephone) {

	public OwnerDto(Owner owner) {
		this(owner.getId(), owner.getFirstName(), owner.getLastName(), owner.getTelephone());
	}
}
