package br.com.henrique.olisaude.oliSaude.Controller;

import br.com.henrique.olisaude.oliSaude.DTO.ClientDTO;
import br.com.henrique.olisaude.oliSaude.Exception.ClientExistException;
import br.com.henrique.olisaude.oliSaude.Exception.ClientNotFoundException;
import br.com.henrique.olisaude.oliSaude.Model.Client;
import br.com.henrique.olisaude.oliSaude.Service.ClientService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import javax.validation.Valid;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
public class ClientController {

    private ClientService clientService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid ClientDTO clientDTO) {
        try {
            return new ResponseEntity<>(clientService.createClient(clientDTO), HttpStatus.CREATED);
        } catch (ClientExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> listAll() {
        try {
            return new ResponseEntity<>(clientService.listAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClient(UUID id, ClientDTO clientDTO) {

        try {
            Client client = clientService.update(id ,clientDTO);
            if (client == null) {
                return new ResponseEntity<>("Cliente nulo.", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(client, HttpStatus.OK);

        } catch (ClientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
