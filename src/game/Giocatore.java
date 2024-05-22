package src.game;

public class Giocatore {
    private String nome;
    private int ID;
    private int posizione;//la posizione del giocatore è semplicemente l'indice della cella in cui si trova

    public Giocatore(String nome, int ID) {
        this.nome = nome;
        this.ID = ID;
        posizione = 0;
    }

    public int getPosizione(){
        return posizione;
    }
    public void setPosizione(int posizione){
        this.posizione = posizione;
    }
    public int getID(){
        return ID;
    }

    public String toString() {
        return nome;
    }
}
