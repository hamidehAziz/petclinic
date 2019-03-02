package org.springframework.samples.petclinic.visit;

import org.springframework.stereotype.Service;

@Service
public class VisitService {
    private VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository){
        this.visitRepository = visitRepository;
    }

    public void save(Visit visit) {
        visitRepository.save(visit);
    }
}
