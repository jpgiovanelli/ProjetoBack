package br.com.projback.projetoback.model;

public enum TipoConta {
    CONTA_CORRENTE(1),
    CONTA_POUPANCA(2),
    CONTA_INVESTIMENTO(3);
    private int id;
    TipoConta(int id) {
        this.id = id;
    }
}
