package br.com.henrique.olisaude.oliSaude.Service;

import br.com.henrique.olisaude.oliSaude.DTO.ClientDTO;
import br.com.henrique.olisaude.oliSaude.Model.Client;
import br.com.henrique.olisaude.oliSaude.Model.HealthProblem;
import br.com.henrique.olisaude.oliSaude.Repository.IClientRepo;
import br.com.henrique.olisaude.oliSaude.Repository.IHealthProblemRepo;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private IClientRepo clientRepo;

    private IHealthProblemRepo healthProblemRepo;

    private static final ModelMapper modelMapper = new ModelMapper();

    public Client createClient(ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
       try {
            if (clientRepo.findByName(client.getName()) != null) { return null;}
            
           if (client != null && client.getHealthProblems() != null) {
                for (HealthProblem healthProblem : client.getHealthProblems()) {
                    HealthProblem problemPersisted = healthProblemRepo.findbyNameAndLevel(healthProblem.getName(), healthProblem.getLevel());
                    if (healthProblem != null) {
                        healthProblem = problemPersisted;
                    }
                }
            }
           return clientRepo.save(client);
        } catch (Exception e) {
           return null;
        }
    }
}
