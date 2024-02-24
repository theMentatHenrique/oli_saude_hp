package br.com.henrique.olisaude.oliSaude.Service;

import br.com.henrique.olisaude.oliSaude.DTO.ClientDTO;
import br.com.henrique.olisaude.oliSaude.Exception.ClientExistException;
import br.com.henrique.olisaude.oliSaude.Exception.ClientNotFoundException;
import br.com.henrique.olisaude.oliSaude.Model.Client;
import br.com.henrique.olisaude.oliSaude.Model.HealthProblem;
import br.com.henrique.olisaude.oliSaude.Repository.IClientRepo;
import br.com.henrique.olisaude.oliSaude.Repository.IHealthProblemRepo;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.id.uuid.UuidGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private IClientRepo clientRepo;

    private IHealthProblemRepo healthProblemRepo;

    private static final ModelMapper modelMapper = new ModelMapper();

    public Client createClient(ClientDTO clientDTO) throws ClientExistException, Exception {
        Client client = modelMapper.map(clientDTO, Client.class);
       try {
            if (clientRepo.findByName(client.getName()) != null) {throw new ClientExistException();}
            
           if (client != null && client.getHealthProblems() != null) {
              client.setHealthProblems(checkSavedProblems(client.getHealthProblems()));
            }
           return clientRepo.save(client);
        } catch (Exception e) {
          throw new Exception(e.getMessage());
        }
    }

    public List<Client> listAll() throws Exception {
        try {
            return clientRepo.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Client update(UUID id, ClientDTO clientDTO) throws ClientNotFoundException, Exception {
        try {
            if (id == null || clientDTO == null) {return null;}
            
            clientRepo.findById(id).orElseThrow(() -> new ClientNotFoundException());
            Client client = modelMapper.map(clientDTO, Client.class);
            client.setId(id);
            client.setHealthProblems(checkSavedProblems(client.getHealthProblems()));
            clientRepo.save(client);
            return client;

        } catch (ClientNotFoundException e) {
            throw new ClientNotFoundException();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }       
    }

    private List<HealthProblem> checkSavedProblems(List<HealthProblem> healthProblems) {
        List<HealthProblem> listProblems = new ArrayList<>();
        if (healthProblems != null) {
            for (HealthProblem healthProblem : healthProblems) {
                HealthProblem problemModel = healthProblemRepo.findbyNameAndLevel(healthProblem.getName(), healthProblem.getLevel());
                listProblems.add(problemModel != null ? problemModel : healthProblem);
            }
        }
        return listProblems;
    }
}
