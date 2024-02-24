package br.com.henrique.olisaude.oliSaude.Service;

import org.springframework.stereotype.Service;

import br.com.henrique.olisaude.oliSaude.Model.HealthProblem;
import br.com.henrique.olisaude.oliSaude.Repository.IHealthProblemRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HealthProblemService {

    private IHealthProblemRepo healthProblemRepo;

    public HealthProblem findHealthProblem(String name, int level) {
        return healthProblemRepo.findHealthProblemByNameAndLevel(name, level);
    }
    
}
