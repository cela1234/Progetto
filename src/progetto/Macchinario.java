package progetto;

import java.util.ArrayList;

public class Macchinario {
    private String id;
    private String bloccoLavorativo;
    private int rangeOrario[];
    private String tipologia;
    private ArrayList<String> idMacchinariInConflitto;

    public Macchinario(String id, String bloccoLavorativo, int a, int b, String tipologia, ArrayList<String> idMacchinariInConflitto){
        rangeOrario = new int[2];
        this.id = id;
        this.bloccoLavorativo = bloccoLavorativo;
        rangeOrario[0] = a;
        rangeOrario[1] = b;
        this.tipologia = tipologia;
        this.idMacchinariInConflitto = idMacchinariInConflitto;
    }

    public int numOre(){
        return rangeOrario[1] - rangeOrario[0];
    }

    public boolean oreSovrapposte(int[] a){
        if ((rangeOrario[0] <= a[0] && rangeOrario[1] >= a[0]) || (rangeOrario[0] <= a[1] && rangeOrario[1] >= a[1]))   return true;

        return false;
    }

    public String getId() {
        return id;
    }

    public String getBloccoLavorativo() {
        return bloccoLavorativo;
    }

    public int[] getRangeOrario() {
        return rangeOrario;
    }

    public String getTipologia() {
        return tipologia;
    }

    public ArrayList<String> getIdMacchinariInConflitto() {
        return idMacchinariInConflitto;
    }


}
