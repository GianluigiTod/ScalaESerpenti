package src.configurazione;

public class InfoGiocatore {
    private String nome;
    private String tipo;

    public InfoGiocatore(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }
    public String getNome() {
        return nome;
    }
    public String getTipo() {
        return tipo;
    }
}
