package br.com.henrique.olisaude.oliSaude.Exception;

public class ClientNotFoundException extends Exception{
    private static final String CLIENT_NOT_FOUND = "Cliente não encontrado.";
    
    public ClientNotFoundException() {
        super(CLIENT_NOT_FOUND);
    }
}
