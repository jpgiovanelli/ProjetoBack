package br.com.projback.projetoback.exception;

public class LojistaException extends Exception{
    private String field;
    private String message;
    public LojistaException(String field, String message){
        super(message);
        this.field = field;
        this.message = message;
    }
    public String getField(){
        return this.field;
    }
    public String getMessage(){
        return this.message;
    }
}
