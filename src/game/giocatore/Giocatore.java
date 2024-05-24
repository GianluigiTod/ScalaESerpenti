package src.game.giocatore;

public interface Giocatore {

    default void gestisciTurno(){
        nuovoTurno();
        lancioDadi();
        spostamento();
        gameOver();
        if(isWinner()){
            finePartita();
        }else{
            gestisciEvento();
        }
    }
    void gestisciEvento();
    boolean isWinner();//metodo getter
    void nuovoTurno();
    void gameOver();
    void lancioDadi();
    void finePartita();
    void spostamento();//ad esempio, una volta che cambio la posizione del giocatore, posso avvisare l'observer
    // del board e fargli sapere (VEDERE PATTERN OBSERVER) che il giocatore con quel determinato nome si è aggiornato
    //perciò bisogna aggiornare la pedina a cui è collegato (la board ha la lista dei giocatori dalla configurazione)
    //che avrà un metodo draw che verrà rieseguito
}
