package src.configurazione;

public class Scala {
    private int casellaInizio;
    private int casellaFine;

    public Scala(int casellaInizio, int casellaFine){
        /*
        if(casellaInizio / nColonne < casellaFine / nColonne && casellaInizio < nRighe*nColonne casellaFine < nRighe*nColonne && casellaInizio>0 && casellaFine>0){

        }Questo va scritto prima di creare la scala
         */
        this.casellaInizio = casellaInizio;
        this.casellaFine = casellaFine;
    }

    public int getCasellaInizio() {
        return casellaInizio;
    }
    public int getCasellaFine() {
        return casellaFine;
    }
}
