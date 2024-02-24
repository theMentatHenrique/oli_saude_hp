package br.com.henrique.olisaude.oliSaude.Exception;


public class ClientExistException extends Exception{
    private static final String CLIENT_ALREADY_EXIST = "Cliente já existente.";
    
    public ClientExistException() {
        super(CLIENT_ALREADY_EXIST);
    }
}
