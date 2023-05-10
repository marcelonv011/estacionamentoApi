package br.com.uniamerica.estacionamento.estacionamentoapi.entity;

public enum Tipo {

    CARRO(1, "Carro"),
    MOTO(2, "Moto"),
    VAM(3, "Vam");

    public final int valorNum;
    public final String valor;

    private Tipo(int valorNum, String valor){
        this.valorNum = valorNum;
        this.valor = valor;
    }

}
