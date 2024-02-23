package br.com.henrique.olisaude.oliSaude.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.henrique.olisaude.oliSaude.Model.HealthProblem;

@Repository
public interface IHealthProblemRepo extends JpaRepository<HealthProblem, UUID>{
    public HealthProblem findbyNameAndLevel(String name, int level);
}
