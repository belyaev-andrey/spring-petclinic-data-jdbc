package org.springframework.samples.petclinic.visit;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;

@RestController
@RequestMapping("/api/visits")
class VisitApiController {

    private final VisitService visitService;
    private final VisitDtoMapper visitDtoMapper;

    VisitApiController(VisitService visitService, VisitDtoMapper visitDtoMapper) {
        this.visitService = visitService;
        this.visitDtoMapper = visitDtoMapper;
    }

    @PostMapping(value = "/pet/{petId}", consumes = "application/json", produces = "application/json")
	VisitDto scheduleVisit(@PathVariable int petId, @Valid @RequestBody Visit visit) {
        Visit newVisit = visitService.saveVisit(petId, visit);
        return visitDtoMapper.mapVisit(newVisit);
	}

}

@Service
class VisitService {

    private final VisitRepository visitRepository;

    VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Transactional
    Visit saveVisit(int petId, Visit visit) {
        visit.setPetId(petId);
        visitRepository.save(visit);
        Visit latestVisit = visitRepository.findByPetId(petId).stream().max(Comparator.comparing(Visit::getId)).orElseThrow();
        return latestVisit;
    }

}
