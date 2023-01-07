package progetto;

import java.util.ArrayList;

public class Prodotto {
    private String id;
    private String categoria;
    private int tempoDiProduzione;
    ArrayList<String> catenaDiMacchinari;

    public Prodotto(String id, String categoria, int tempoDiProduzione, ArrayList<String> catenaDiMacchinari){
        this.id = id;
        this.categoria = categoria;
        this.tempoDiProduzione = tempoDiProduzione;
        this.catenaDiMacchinari = catenaDiMacchinari;
    }
}
