package br.com.henrique.olisaude.oliSaude.Service;

import br.com.henrique.olisaude.oliSaude.DTO.ClientDTO;
import br.com.henrique.olisaude.oliSaude.DTO.HealthProblemDTO;
import br.com.henrique.olisaude.oliSaude.Exception.ClientExistentException;
import br.com.henrique.olisaude.oliSaude.Exception.ClientNotFoundException;
import br.com.henrique.olisaude.oliSaude.Model.Client;
import br.com.henrique.olisaude.oliSaude.Model.HealthProblem;
import br.com.henrique.olisaude.oliSaude.Repository.IClientRepo;
import br.com.henrique.olisaude.oliSaude.Repository.IHealthProblemRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private IClientRepo clientRepo;
    @Autowired
    private IHealthProblemRepo healthProblemRepo;
    private static final ModelMapper modelMapper = new ModelMapper();

    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        if (clientRepo.findByName(client.getName()) != null) {throw new ClientExistentException();}

        List<HealthProblem> healthProblems = client.getHealthProblems();
        if (healthProblems != null && !healthProblems.isEmpty()) {
            client.setHealthProblems(checkSavedProblems(client.getHealthProblems()));
        }
        return modelMapper.map(clientRepo.save(client), ClientDTO.class);
    }

    public List<ClientDTO> listAll() {
        return clientRepo.findAll().stream().map(client -> modelMapper.map(client, ClientDTO.class)).toList();
    }

    public Client update(UUID id, ClientDTO clientDTO) {
        if (!clientRepo.existsById(id)) {
            throw new ClientNotFoundException();
        }
        Client client = modelMapper.map(clientDTO, Client.class);
        client.setId(id);
        client.setHealthProblems(checkSavedProblems(client.getHealthProblems()));
        clientRepo.save(client);
        return client;
    }

    private List<HealthProblem> checkSavedProblems(List<HealthProblem> healthProblems) {
        List<HealthProblem> listProblems = new ArrayList<>();
        if (healthProblems != null) {
            for (HealthProblem healthProblem : healthProblems) {
                HealthProblem problemModel = healthProblemRepo.findHealthProblemByNameAndLevel(healthProblem.getName(), healthProblem.getLevel());
                listProblems.add(problemModel != null ? problemModel : healthProblem);
            }
        }
        return listProblems;
    }

    public List<HealthProblemDTO> getTop10ClientsWithRiskProblem() {
        return clientRepo.findtop10Results().stream().map(m -> modelMapper.map(m, HealthProblemDTO.class)).toList();
    }

    public Client getClient(UUID id) {
        return clientRepo.findById(id).orElseThrow(ClientNotFoundException::new);
    }
}
