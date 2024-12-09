package org.springframework.samples.petclinic.visit;

import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.owner.PetRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
class VisitDtoMapper {

    private final PetRepository petRepository;

    public VisitDtoMapper(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    VisitDto mapVisit(Visit visit) {
        Pet pet = petRepository.findById(visit.getPetId());
        Integer ownerId = pet.getOwner();
        return new VisitDto(visit.getId(), ownerId, pet.getId(), visit.getDate(), visit.getDescription());
    }

}

record VisitDto(Long id, Integer ownerId, Integer petId, LocalDate date, String description) { }
