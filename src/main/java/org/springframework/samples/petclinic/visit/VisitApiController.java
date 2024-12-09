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

    public VisitApiController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping(value = "/pet/{petId}", consumes = "application/json", produces = "application/json")
	Visit scheduleVisit(@PathVariable int petId, @Valid @RequestBody Visit visit) {
		return visitService.saveVisit(petId, visit);
	}

}

@Service
class VisitService {

    private final VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Transactional
    Visit saveVisit(int petId, Visit visit) {
        visit.setPetId(petId);
        visitRepository.save(visit);
        Visit latestVisit = visitRepository.findByPetId(petId).stream().max(Comparator.comparing(Visit::getDate)).orElseThrow();
        return latestVisit;
    }

}
