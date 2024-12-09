package org.springframework.samples.petclinic.owner;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owners")
class OwnerApiController {

	private final OwnerRepository ownerRepository;

	public OwnerApiController(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@GetMapping("/api/owners/{ownerId}")
	public @ResponseBody OwnerDto fetchOwner(@PathVariable("ownerId") int ownerId) {
		Owner owner = ownerRepository.findById(ownerId);
		return new OwnerDto(owner);
	}

}
