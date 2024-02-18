package br.com.henrique.olisaude.oliSaude.Repository;

import br.com.henrique.olisaude.oliSaude.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IClientRepo extends JpaRepository<UUID, Client> {

    @Query("SELECT c FROM client c ORDER BY c.healthRisk DESC LIMIT 10")
    List<Client> findtop10Results();
}
