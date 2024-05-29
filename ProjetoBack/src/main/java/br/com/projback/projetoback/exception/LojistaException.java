package br.com.projback.projetoback.exception;

public class LojistaException extends Exception{
    private String field;
    public LojistaException(String field, String message){
        super(message);
        this.field = field;
    }
    public String getField(){
        return this.field;
    }
}
