package src.game;

public interface Giocatore {

    void gestisciTurno(Board board);
    boolean isWinner();//metodo getter
    void isGameOver();
    int lancioDadi();
    void spostamento();
}
