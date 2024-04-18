package br.com.projback.projetoback.model;

public enum TipoEndereco {
    RESIDENCIAL(1),
    LOJA(2);
    private int id;
    TipoEndereco(int id) {
        this.id = id;
    }
}
