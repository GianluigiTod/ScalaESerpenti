package src.game;

import src.game.giocatore.AbstractGiocatore;
import src.game.giocatore.Giocatore;

public interface Board {
    void gestisciEvento(int posizione);
    void avvisoEventoEffettuato();
    void cominciaTurno(String nome, boolean automatico);
    void avvisoLancioEffettuato();
    void avvisoSpostamentoEffettuato();
    void avvisoFinePartita();
    void draw();
}
