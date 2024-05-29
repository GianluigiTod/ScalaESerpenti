package src.game.giocatore;

import src.configurazione.Configurazione;
import src.game.Board;
import src.game.GameMediatorEsteso;

public class Player extends AbstractGiocatore {
    private int numDadi;
    private boolean attivareDivieto;

    public Player(String nome, Configurazione config, GameMediatorEsteso mediator) {
        super(nome, config, mediator, false);
    }

    @Override
    public int[] gestioneTiro(int tiro1, int tiro2) {
        mediator.notify(this, "chiediNumDadi");
        if(numDadi == 1){
            tiro1=dado.roll();
            tiro2=0;
        }else{
            tiro1=dado.roll();
            tiro2=dado.roll();
        }
        return new int[]{tiro1, tiro2};
    }

    @Override
    public boolean divietoSosta() {
        mediator.notify(this, "chiediUsaCartaDivieto");
        if(attivareDivieto){
            nTurniStop=0;
            numCarte--;
            return true;
        }
        return false;
    }

    @Override
    public void gestisciTurno() {
        //Questo metodo verrà chiamato nel metodo play di Gioco
        nuovoTurno();
    }

    @Override
    public void nuovoTurno() {
        //il nome lo puoi usare per settare nella board il giocatore/pedina corrente, così ad esempio quando
        //dobbiamo aggiornare una certa pedina, sappiamo quale dobbiamo aggiornare, comunque avere informazioni riguardo
        //a chi sta visualizzando l'interfaccia in quel momento è sempre buono e potrebbe servire in qualsiasi altra
        //implementazione dell'interfaccia grafica(ricordo che Board è un'interfaccia che fa da mediatore tra Giocatore e la GUI)
        super.nuovoTurno();
    }

    public void setNumDadi(int numDadi) {
        this.numDadi = numDadi;
    }

    public void setAttivareDivieto(boolean attivareDivieto){
        this.attivareDivieto = attivareDivieto;
    }
}
