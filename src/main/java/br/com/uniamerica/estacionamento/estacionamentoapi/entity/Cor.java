package br.com.uniamerica.estacionamento.estacionamentoapi.entity;

public enum Cor {
    BRANCO(1,"Branco"),
    PRETO(2, "Preto"),
    CINZA(3,"Cinza"),
    VERMELHO(4,"Vermelho"),
    AZUL(5,"Azul"),
    AMARELHO(6,"Amarelho");

    public final int valorNum;
    public final String valor;

    private Cor(int valorNum,String valor){
        this.valorNum = valorNum;
        this.valor = valor;
    }
}
