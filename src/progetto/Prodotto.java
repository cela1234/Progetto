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

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public int getTempoDiProduzione() {
        return tempoDiProduzione;
    }
    public void setTempoDiProduzione(int tempoDiProduzione) {
        this.tempoDiProduzione = tempoDiProduzione;
    }
    public ArrayList<String> getCatenaDiMacchinari() {
        return catenaDiMacchinari;
    }
    public void setCatenaDiMacchinari(ArrayList<String> catenaDiMacchinari) {
        this.catenaDiMacchinari = catenaDiMacchinari;
    }
}
