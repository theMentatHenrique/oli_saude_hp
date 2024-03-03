package br.com.henrique.olisaude.oliSaude.Controller;

import br.com.henrique.olisaude.oliSaude.DTO.ClientDTO;
import br.com.henrique.olisaude.oliSaude.Exception.ClientExistentException;
import br.com.henrique.olisaude.oliSaude.Exception.ClientNotFoundException;
import br.com.henrique.olisaude.oliSaude.Service.ClientService;

import lombok.SneakyThrows;
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
        return new ResponseEntity<>(clientService.createClient(clientDTO), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(clientService.listAll(), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClient(@PathVariable UUID id, @RequestBody ClientDTO clientDTO) {
        return new ResponseEntity<>(clientService.update(id ,clientDTO), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getClient(@PathVariable UUID id) {
        return new ResponseEntity<>(clientService.getClient(id), HttpStatus.OK);
    }
}
