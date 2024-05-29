package src.game;

import src.game.giocatore.Giocatore;
import src.game.giocatore.Player;

public class GameMediatorEsteso extends GameMediator{

    public GameMediatorEsteso(Board board) {
        super(board);
    }

    @Override
    public void notify(Object sender, String event) {
        super.notify(sender, event);
        if(event.equals("chiediNumDadi")){
            int res = board.restituisciNumDadi();
            ((Player)sender).setNumDadi(res);
        }else if(event.equals("chiediUsaCartaDivieto")){
            boolean res = board.chiediUsaCartaDivieto();
            ((Player)sender).setAttivareDivieto(res);
        }else if(event.equals("chiediNumDadi")){

        }
    }
}
