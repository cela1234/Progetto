package progetto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Progetto {
    public static void main(String[] Args){
        String[] zone = {"A","B","C","D","E"};
        Macchinario macchinari[];
        Lavoratore lavoratori[];
        Prodotto prodotti[];
        ArrayList<String> idMacchinariWorker = new ArrayList<>();
        ArrayList<String> idMacchinariManager = new ArrayList<>();
        ArrayList<String> idMacchinariExecutive = new ArrayList<>();
        Scanner input =  new Scanner(System.in);
        String toAnalyze = input.nextLine();
        String[] quantitaPerClasse = toAnalyze.split(" ");
        lavoratori =  new Lavoratore[Integer.parseInt(quantitaPerClasse[0])];
        macchinari =  new Macchinario[Integer.parseInt(quantitaPerClasse[1])];
        prodotti =  new Prodotto[Integer.parseInt(quantitaPerClasse[2])];
        for(int i = 0; i < lavoratori.length; i++){
            toAnalyze = input.nextLine();
            String[] datiLavoratore = toAnalyze.split(" ");
            lavoratori[i] = new Lavoratore(datiLavoratore[0], datiLavoratore[1], datiLavoratore[2], Integer.parseInt(datiLavoratore[3]), datiLavoratore[4]);
        }
        for(int i = 0; i < 3; i++){
            /*
            toAnalyze = input.nextLine();
            toAnalyze = toAnalyze.substring(toAnalyze.lastIndexOf(" ") + 1);
            String idMacchinariDaInserire[] = toAnalyze.split(",");
            for (int j = 0; j < idMacchinariDaInserire.length; j++){
                switch(i){
                    case 0: // caso worker
                        idMacchinariWorker.add(idMacchinariDaInserire[j]);
                        break;
                    case 1: // caso manager
                        idMacchinariManager.add(idMacchinariDaInserire[j]);
                        break;
                    case 2: // caso executive
                        idMacchinariExecutive.add(idMacchinariDaInserire[j]);
                        break;
                    default:
                }
            }*/ //caso hardcoded
            toAnalyze = input.nextLine();
            String ArrayDaAnalizzare[] = toAnalyze.split(" -> ");
            String idMacchinariDaInserire[] = ArrayDaAnalizzare[1].split(",");
            for (int j = 0; j < idMacchinariDaInserire.length; j++){
                switch(ArrayDaAnalizzare[0]){
                    case "worker": // caso worker
                        idMacchinariWorker.add(idMacchinariDaInserire[j]);
                        break;
                    case "manager": // caso manager
                        idMacchinariManager.add(idMacchinariDaInserire[j]);
                        break;
                    case "executive": // caso executive
                        idMacchinariExecutive.add(idMacchinariDaInserire[j]);
                        break;
                    default:
                }
            } //caso che decide in base a cosa é stato scritto prima di " -> "
        }
        for (int i = 0; i < macchinari.length; i++){
            toAnalyze = input.nextLine();
            String[] ArrayDaAnalizzare = toAnalyze.split(" ");
            String[] numeriRange = ArrayDaAnalizzare[2].split(",");
            String[] macchinariInConflitto = ArrayDaAnalizzare[4].split(",");
            ArrayList<String> conflitti = new ArrayList<>();
            for(int j = 0; j < macchinariInConflitto.length; j++) { conflitti.add(macchinariInConflitto[j]);}
            macchinari[i] = new Macchinario(ArrayDaAnalizzare[0], ArrayDaAnalizzare[1], Integer.parseInt(numeriRange[0]), Integer.parseInt(numeriRange[1]), ArrayDaAnalizzare[3], conflitti);
        }
        //una volta popolato l'array macchinari, gli assegno il ruolo
        AssegnaRuoloMacchinari(macchinari,idMacchinariWorker,idMacchinariManager,idMacchinariExecutive);

        for(int i = 0; i < prodotti.length; i++){
            toAnalyze = input.nextLine();
            String[] ArrayDaAnalizzare = toAnalyze.split(" ");
            String[] catenaArray = ArrayDaAnalizzare[3].split("->");
            ArrayList<String> catena = new ArrayList<>();
            for(int j = 0; j < catenaArray.length; j++) {catena.add(catenaArray[j]);}
            prodotti[i] = new Prodotto(ArrayDaAnalizzare[0], ArrayDaAnalizzare[1], Integer.parseInt(ArrayDaAnalizzare[2]), catena);
        }
        for(int i = 0; i < lavoratori.length; i++){
            toAnalyze = input.nextLine();
            String[] ArrayToAnalyze = toAnalyze.split(" -> ");
            String[] macchinariDaAssegnareAlLavoratore = ArrayToAnalyze[1].split(",");
            for(int j = 0; j < lavoratori.length; j++){
                if (lavoratori[j].getIdLavoratore().equals(ArrayToAnalyze[0]))
                    for (int k = 0; k < macchinariDaAssegnareAlLavoratore.length; k++){
                        lavoratori[j].assegnaMacchinarioLavoratore(macchinariDaAssegnareAlLavoratore[k]);
                    }
            }
        }
        toAnalyze = input.nextLine();
        String[] resolveTask = toAnalyze.split(" ");
        if (resolveTask[0].equalsIgnoreCase("TASK1"))
        {
            //task1
            //1.1 Il numero totale di prodotti finali creati e il numero totale di macchinari utilizzati per crearli
            System.out.println(prodotti.length +" "+ macchinari.length);

            //1.2
            //il numero totale di prodotti finali creati per ogni categoria
            StampaNumeroProdottiPerCategoria(prodotti);
            //1.3
            //L'ID del macchinario più utilizzato per creare i prodotti finali
            System.out.println(IdMacchinarioPiuUtilizzato(prodotti,macchinari));
            //1.4
            //Il numero di lavoratori non coerenti
            System.out.println(NumeroLavoratoriNonCoerenti(lavoratori,macchinari));
            //1.5
            //Il numero di macchinari per ogni zona
            String stringaZone = "";
            for(String s : zone){
                if (s!="E"){
                    stringaZone = stringaZone + ContaMacchinariPerZona(macchinari,s) + " ";
                }else stringaZone = stringaZone + ContaMacchinariPerZona(macchinari,s);
            }
            System.out.println(stringaZone);
            //System.out.print("\n");
            //1.6
            //Il numero di macchinari distinti utilizzati per produrre i prodotti finali per ogni categoria
            String macchinariPerCategoria = "";
            macchinariPerCategoria = NumeroMacchinariPerCategoriaProdotto(prodotti,"micro")+ " " +
                                     NumeroMacchinariPerCategoriaProdotto(prodotti,"macro")+ " " +
                                     NumeroMacchinariPerCategoriaProdotto(prodotti,"aggregato");
            System.out.println(macchinariPerCategoria);
            //System.out.print(NumeroMacchinariPerCategoriaProdotto(prodotti,"micro")+ " ");
            //System.out.print(NumeroMacchinariPerCategoriaProdotto(prodotti,"macro")+ " ");
            //System.out.print(NumeroMacchinariPerCategoriaProdotto(prodotti,"aggregato")+ "\n");

            //1.7
            //La categoria del prodotto finale con la catena di macchinari più lunga
            StampaCategoriaProdottoConCatenaPiuLunga(prodotti);
        }
        else if (resolveTask[0].equalsIgnoreCase("TASK2")) //formato task2 nell'input: "TASK2 "
        {
            int p = Integer.parseInt(resolveTask[1]);
            int q = Integer.parseInt(resolveTask[2]);
            int r = Integer.parseInt(resolveTask[3]);
            Task2(p, r, q, macchinari, prodotti, lavoratori);

        }
        else if (resolveTask[0].equalsIgnoreCase("TASK3"))
        {
            Prodotto[] sequenzaProdotti = new Prodotto[Integer.parseInt(resolveTask[1])];
            for(int i = 0; i < sequenzaProdotti.length; i++)
            {
                String idProdottoSequenza = input.nextLine();
                sequenzaProdotti[i] = findProdottoById(prodotti, idProdottoSequenza);
            }
            Task3(macchinari, prodotti, lavoratori, sequenzaProdotti);
        }
    }
    static Macchinario findMacchinarioById(Macchinario[] m, String id){
        for (int i = 0; i < m.length; i++){
            if (m[i].getId().equals(id)) return m[i];
        }
        return null;
    }
    static Prodotto findProdottoById(Prodotto[] p, String id){
        for (int i = 0; i < p.length; i++){
            if (p[i].getId().equals(id)) return p[i];
        }
        return null;
    }
    static void Task2(int p, int r, int q, Macchinario[] macchinari, Prodotto[] prodotti, Lavoratore[] lavoratori){
        //1: Vi sono al più p macchinari con al più q conflitti
        int contatoreT2P1=0;
        for (int i = 0; i < macchinari.length; i++){
            if (macchinari[i].getIdMacchinariInConflitto().size()<=q) contatoreT2P1++; //aumento il contatore se il numero di macchinari in conflitto é minore o uguale a q
        }
        //2: Vi sono al più p macchinari il cui slot temporale è formato da almeno r ore
        int contatoreT2P2=0;
        for (int i = 0; i < macchinari.length; i++){
            if (macchinari[i].numOre()>=r) contatoreT2P2++; //se il numero di ore del macchinario supera r, allora aumento il contatore di 1
        }
        //3
        boolean T2P3 = true;
        int contatoreConflitti;
        for(int i = 0; i < macchinari.length; i++){
            contatoreConflitti = 0;
            for(int j = 0; j < macchinari.length; j++){
                if(i!=j){
                    if(macchinari[j].getIdMacchinariInConflitto().contains(macchinari[i].getId())){
                        contatoreConflitti++;
                        if (contatoreConflitti > 1) T2P3 = false;
                    }
                }
            }
        }
        //4
        boolean T2P4 = true;
        boolean[] tipoProdottoTrovati = new boolean[3];
        int numeroProdottiInCuiIlMacchinarioEImpiegato = 0;
        for(int i = 0; i < macchinari.length; i++){
            tipoProdottoTrovati[0] = false;
            tipoProdottoTrovati[1] = false;
            tipoProdottoTrovati[2] = false;
            numeroProdottiInCuiIlMacchinarioEImpiegato = 0;
            for(int j = 0; j < prodotti.length; j++){
                if(prodotti[j].getCatenaDiMacchinari().contains(macchinari[i].getId())){
                    switch(prodotti[j].getCategoria().toLowerCase()){
                        case "micro":
                            if (tipoProdottoTrovati[0]) T2P4 = false;

                            else tipoProdottoTrovati[0]=true;
                            break;
                        case "macro":
                            if (tipoProdottoTrovati[1]) T2P4 = false;
                            else tipoProdottoTrovati[1]=true;
                            break;
                        case "aggregato":
                            if (tipoProdottoTrovati[2]) T2P4 = false;
                            else tipoProdottoTrovati[2]=true;
                            break;
                        default:
                    }
                    numeroProdottiInCuiIlMacchinarioEImpiegato++;
                    if (numeroProdottiInCuiIlMacchinarioEImpiegato > 2) T2P4 = false;
                }
            }
        }
        if((contatoreT2P1<=p) && (contatoreT2P2<=p) && (T2P3) && (T2P4)){
            System.out.println("YES");
        }
        else System.out.println("NO");
    }
    static void Task3(Macchinario[] macchinari, Prodotto[] prodotti, Lavoratore[] lavoratori, Prodotto[] sequenzaProdotti){

    }
    static void StampaNumeroProdottiPerCategoria(Prodotto v[])
    {
        int nMacro=0,nMicro=0,nAggregato=0;
        for (Prodotto prodotto : v) {
            String c = prodotto.getCategoria();
            switch (c) {
                case "micro" -> nMicro++;
                case "aggregato" -> nAggregato++;
                case "macro" -> nMacro++;
            }
        }
        System.out.println(nMicro+" "+nMacro+" "+nAggregato);
    }
    static String IdMacchinarioPiuUtilizzato(Prodotto p[], Macchinario m[])
    {
        int[] utilizzi = new int[m.length];

        for (Prodotto prodotto : p) {
            ArrayList<String> macchinariUsati = prodotto.getCatenaDiMacchinari();
            // scorro tutti i macchinari usati da p[i]
            for (String idMacchinario : macchinariUsati) {
                int n = 0;
                while (!idMacchinario.equals(m[n].getId()))
                    n++;
                //ora ho l'indice del macchinario nel vettore m
                utilizzi[n]++;
            }
        }
        //ora ho il vettore utilizzi, parallelo al vettore m, con gli utilizzi di ciascun macchinario
        //trovo il più utilizzato
        int indiceFinale=0;
        for(int i=0; i<m.length;i++)
        {
            if(utilizzi[i]>utilizzi[indiceFinale])
                indiceFinale=i;
            else if(utilizzi[i]==utilizzi[indiceFinale])
            { //se hanno lo stesso numero di utilizzi, prendo chi precede in alfabeto
                if(m[i].getId().compareToIgnoreCase(m[indiceFinale].getId())<0)
                    indiceFinale=i;
            }
        }
        return m[indiceFinale].getId();
    }
    static boolean Coerente(Lavoratore l, Macchinario m[])
    {
        ArrayList<String> mLavoratore=l.getMacchinariLavoratore();
        for (String s : mLavoratore) {
            int indiceMacchinario = 0;
            while (!s.equals(m[indiceMacchinario].getId()))
                indiceMacchinario++;
            if (!m[indiceMacchinario].getRuolo().equals(l.getRuolo()))
                return false;
        }
        return true;
    }
    static int NumeroLavoratoriNonCoerenti(Lavoratore l[], Macchinario m[])
    {
        int n=0;
        for (Lavoratore lavoratore : l)
            if (!Coerente(lavoratore, m))
                n++;
        return n;
    }

    static int ContaMacchinariPerZona(Macchinario m[], String zona)
    {
        int n=0;
        for( Macchinario mac : m)
            if(mac.getBloccoLavorativo().equals(zona))
                n++;
        return n;
    }

    static int NumeroMacchinariPerCategoriaProdotto(Prodotto p[],String categoria)
    {
        ArrayList<String> idMacchinari=new ArrayList<>();
        for (Prodotto prodotto : p) {
            if (prodotto.getCategoria().equals(categoria)) { // se il prodotto è della categoria che mi interessa mi salvo l'id dei macchinari utilizzati per costruirlo
                // se non l'avessi già salvato
                ArrayList<String> catenaMacchinari = prodotto.getCatenaDiMacchinari();
                //scorro tuttti i macchinari
                for (String s : catenaMacchinari) {
                    //per ogni macchinario, se non è già nella lista idMacchinari lo aggiungo
                    boolean presente = false;
                    for (String value : idMacchinari)
                        if (value.equals(s)) {
                            presente = true;
                            break;
                        }
                    if (!presente)
                        idMacchinari.add(s);
                }
            }
        }
        return idMacchinari.size();
    }
    static void StampaCategoriaProdottoConCatenaPiuLunga(Prodotto p[])
    {
        //prima trovo il prodotto con la catena più lunga, poi ne stampo la categoria
        int[] lCatena = new int[p.length]; //array parallelo ai prodotti con la lunghezza della catena
        int indexMaggiore=0;
        for(int i=0; i< p.length;i++)
        {
            lCatena[i] = p[i].getCatenaDiMacchinari().size();
            if(lCatena[i]>lCatena[indexMaggiore])
                indexMaggiore=i;
        }
        System.out.println(p[indexMaggiore].getCategoria());
    }

    static void AssegnaRuoloMacchinari(Macchinario m[], ArrayList<String> MW, ArrayList<String> MM, ArrayList<String> ME)
    {
        for(Macchinario mac : m)
        {
            for (int i=0; i<MW.size() && mac.getRuolo()==null; i++ )
                if (mac.getId().equals(MW.get(i)))
                    mac.setRuolo("worker");

            for (int i=0; i<MM.size() && mac.getRuolo()==null; i++ )
                if (mac.getId().equals(MM.get(i)))
                    mac.setRuolo("manager");

            for (int i=0; i<ME.size() && mac.getRuolo()==null; i++ )
                if (mac.getId().equals(ME.get(i)))
                    mac.setRuolo("executive");
        }
    }
}
