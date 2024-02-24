package br.com.henrique.olisaude.oliSaude.Controller;

import br.com.henrique.olisaude.oliSaude.DTO.ClientDTO;
import br.com.henrique.olisaude.oliSaude.Exception.ClientExistentException;
import br.com.henrique.olisaude.oliSaude.Exception.ClientNotFoundException;
import br.com.henrique.olisaude.oliSaude.Model.Client;
import br.com.henrique.olisaude.oliSaude.Service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import javax.validation.Valid;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid ClientDTO clientDTO) {
        try {
            return new ResponseEntity<>(clientService.createClient(clientDTO), HttpStatus.CREATED);
        } catch (ClientExistentException e) {
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
    public ResponseEntity<?> updateClient(@PathVariable UUID id, @RequestBody ClientDTO clientDTO) {
        try {
            Client client = clientService.update(id ,clientDTO);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (ClientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("O id e os dados do cliente devem ser preenchidos", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getClient(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(clientService.getClient(id), HttpStatus.OK);
        } catch (ClientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
