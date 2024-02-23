package br.com.henrique.olisaude.oliSaude.Controller;

import br.com.henrique.olisaude.oliSaude.DTO.ClientDTO;
import br.com.henrique.olisaude.oliSaude.Model.Client;
import br.com.henrique.olisaude.oliSaude.Service.ClientService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
public class ClientController {

    private static final String ERROR_CREATE_CLIENT = "Não foi possível realizar o cadastro do cliente.";
    private ClientService clientService;


    @PostMapping("/create")
    public ResponseEntity create(@RequestBody @Valid ClientDTO clientDTO) {
        Client client = clientService.createClient(clientDTO);
        if (client == null) {
            return ResponseEntity.badRequest().body(ERROR_CREATE_CLIENT);
        }
        return ResponseEntity.status(201).body(client);
    }
}
