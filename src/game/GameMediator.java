package src.game;

import src.game.giocatore.Giocatore;

public class GameMediator implements Mediator{
    protected Board board;

    public GameMediator(Board board) {
        this.board = board;
    }

    @Override
    public void notify(Object sender, String event) {
        if (event.equals("cominciaTurno")) {
            board.cominciaTurno(((Giocatore)sender).getNome(), ((Giocatore)sender).isAutomatico());
        } else if (event.equals("lancioEffettuato")) {
            board.avvisoLancioEffettuato();
        } else if (event.equals("spostamentoEffettuato")) {
            board.avvisoSpostamentoEffettuato();
        } else if (event.equals("gestisciEvento")) {
            board.gestisciEvento(((Giocatore)sender).getPosizione());
            board.avvisoEventoEffettuato();
        } else if (event.equals("finePartita")) {
            board.avvisoFinePartita();
        }
    }
}
