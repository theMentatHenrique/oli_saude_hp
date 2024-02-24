package br.com.henrique.olisaude.oliSaude.Exception;


public class ClientExistentException extends Exception{
    private static final String CLIENT_ALREADY_EXIST = "Cliente já existente.";
    
    public ClientExistentException() {
        super(CLIENT_ALREADY_EXIST);
    }
}
