package progetto;

import java.util.ArrayList;
import java.util.Objects;
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
        assegnaRuoloMacchinari(macchinari,idMacchinariWorker,idMacchinariManager,idMacchinariExecutive);

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
            stampaNumeroProdottiPerCategoria(prodotti);
            //1.3
            //L'ID del macchinario più utilizzato per creare i prodotti finali
            System.out.println(idMacchinarioPiuUtilizzato(prodotti,macchinari));
            //1.4
            //Il numero di lavoratori non coerenti
            System.out.println(numeroLavoratoriNonCoerenti(lavoratori,macchinari));
            //1.5
            //Il numero di macchinari per ogni zona
            String stringaZone = "";
            for(String s : zone){
                if (s!="E"){
                    stringaZone = stringaZone + contaMacchinariPerZona(macchinari,s) + " ";
                }else stringaZone = stringaZone + contaMacchinariPerZona(macchinari,s);
            }
            System.out.println(stringaZone);
            //System.out.print("\n");
            //1.6
            //Il numero di macchinari distinti utilizzati per produrre i prodotti finali per ogni categoria
            String macchinariPerCategoria = "";
            macchinariPerCategoria = numeroMacchinariPerCategoriaProdotto(prodotti,"micro")+ " " +
                                     numeroMacchinariPerCategoriaProdotto(prodotti,"macro")+ " " +
                                     numeroMacchinariPerCategoriaProdotto(prodotti,"aggregato");
            System.out.println(macchinariPerCategoria);
            //System.out.print(NumeroMacchinariPerCategoriaProdotto(prodotti,"micro")+ " ");
            //System.out.print(NumeroMacchinariPerCategoriaProdotto(prodotti,"macro")+ " ");
            //System.out.print(NumeroMacchinariPerCategoriaProdotto(prodotti,"aggregato")+ "\n");

            //1.7
            //La categoria del prodotto finale con la catena di macchinari più lunga
            stampaCategoriaProdottoConCatenaPiuLunga(prodotti);
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
            Task3(macchinari, sequenzaProdotti);
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
            if (macchinari[i].getIdMacchinariInConflitto().size()>q) contatoreT2P1++; //aumento il contatore se il numero di macchinari in conflitto é maggiore di q
        }
        /*for (int i = 0; i < macchinari.length; i++){
            ArrayList<String> possibiliConflitti = macchinari[i].getIdMacchinariInConflitto(); //NON DOBBIAMO CONTROLLARE IL BLOCCO LAVORATIVO PER LA TASK2
            int numeroConflittiMacchinario = 0;
            for (String s: possibiliConflitti) {
                if(macchinari[i].inConflitto(findMacchinarioById(macchinari, s))){
                    numeroConflittiMacchinario++;
                }
            }
            if(numeroConflittiMacchinario > q) contatoreT2P1++;
        }*/
        //2: Vi sono al più p macchinari il cui slot temporale è formato da almeno r ore
        int contatoreT2P2=0;
        for (int i = 0; i < macchinari.length; i++){
            if (macchinari[i].numOre()>=r) contatoreT2P2++; //se il numero di ore del macchinario supera r, allora aumento il contatore di 1
        }
        //3: Per ogni macchinario, vi è al più un altro macchinario che è ha un conflitto verso esso,
        boolean T2P3 = true;
        int contatoreConflitti;
        for(int i = 0; i < macchinari.length; i++){
            contatoreConflitti = 0;
            for(int j = 0; j < macchinari.length; j++){
                if(i!=j){
                    if(macchinari[j].getIdMacchinariInConflitto().contains(macchinari[i].getId())){
                        contatoreConflitti++;   //se per il macchinario i conflitti verso di esso supera 1 allora la terza condizione sará false
                        if (contatoreConflitti > 1) T2P3 = false;
                    }
                    //if(macchinari[j].inConflitto(macchinari[i])){         //NON DOBBIAMO CONTROLLARE IL BLOCCO LAVORATIVO PER LA TASK2
                    //   contatoreConflitti++;
                    //    if (contatoreConflitti > 1) T2P3 = false;
                    //}
                }
            }
        }
        //4: Per ogni macchinario, esso è impiegato per al più due prodotti con categoria diversa tra loro.
        boolean T2P4 = true;
        boolean[] tipoProdottoTrovati = new boolean[3];
        int numeroCategorieInCuiIlMacchinarioEImpiegato = 0;
        for(int i = 0; i < macchinari.length; i++){
            tipoProdottoTrovati[0] = false;
            tipoProdottoTrovati[1] = false;
            tipoProdottoTrovati[2] = false;
            numeroCategorieInCuiIlMacchinarioEImpiegato = 0;
            for(int j = 0; j < prodotti.length; j++){
                if(prodotti[j].getCatenaDiMacchinari().contains(macchinari[i].getId())){
                    switch(prodotti[j].getCategoria().toLowerCase()){
                        case "micro":
                            if(!tipoProdottoTrovati[0]) {
                                tipoProdottoTrovati[0]= true;
                                numeroCategorieInCuiIlMacchinarioEImpiegato++;
                            }
                            break;
                        case "macro":
                            if(!tipoProdottoTrovati[1]) {
                                tipoProdottoTrovati[1]= true;
                                numeroCategorieInCuiIlMacchinarioEImpiegato++;
                            }
                            break;
                        case "aggregato":
                            if(!tipoProdottoTrovati[2]) {
                                tipoProdottoTrovati[2]= true;
                                numeroCategorieInCuiIlMacchinarioEImpiegato++;
                            }
                            break;
                        default:
                    }
                }
            }
            if(numeroCategorieInCuiIlMacchinarioEImpiegato>2) T2P4 = false;
        }
        if((contatoreT2P1<=p) && (contatoreT2P2<=p) && (T2P3) && (T2P4)){
            System.out.println("YES");
        }
        else System.out.println("NO");
    }
    static void Task3(Macchinario[] macchinari, Prodotto[] sequenzaProdotti){
        /*Una sequenza di prodotti finali da creare è valida se le seguenti condizioni sono tutte vere:
        1. per ogni prodotto nella sequenza, la sua catena non ha due o più macchinari i cui slot
        temporali sono sovrapposti; due slot temporali si dicono sovrapposti se hanno almeno 1 ora
        in comune;
        2. l'intera sequenza deve avere almeno prodotto per ogni categoria ed entrambe le tipologie
        di macchinari devono essere utilizzati in ogni catena;
        3. per ogni prodotto nella sequenza, la sua catena può contenere al più due macchinari in
        conflitto.
        * */

        //1)
        boolean T3P1=true; //se rimane true è VALID
        for(Prodotto p: sequenzaProdotti)
        {
            Macchinario[] sequenzaMacchinari = new Macchinario[p.getCatenaDiMacchinari().size()];
            for(int i=0; i<sequenzaMacchinari.length;i++)
                sequenzaMacchinari[i]=findMacchinarioById(macchinari,p.getCatenaDiMacchinari().get(i));
            for (int i = 0; i < sequenzaMacchinari.length && T3P1; i++)
                for (int x =i+1; x < sequenzaMacchinari.length && T3P1; x++) //controllo dal macchinario successivo
                    if (sequenzaMacchinari[i].oreSovrapposte(sequenzaMacchinari[x].getRangeOrario()))
                        T3P1 = false;
        }
        //2)
        //(if prodotti.lenght < 3, false)
        boolean c1=false,c2=false,c3=false; //categorie
        for(Prodotto p: sequenzaProdotti)
            if(!c1 || !c2 || !c3) //controllo che intera sequenza abbia almeno un prodotto per ogni cat
            {
                switch (p.getCategoria()) {
                    case "micro" -> c1 = true;
                    case "macro" -> c2 = true;
                    case "aggregato" -> c3 = true;
                }
            }
        boolean T3P2=c1&&c2&&c3;
        for(int i =0; i<sequenzaProdotti.length && T3P2;i++)
        {
            boolean tipo1=false,tipo2=false;
            //controllo che entrambe tipologie del macchinario siano presenti in ogni catena
            for(int x=0; x<sequenzaProdotti[i].getCatenaDiMacchinari().size() && (!tipo1 || !tipo2);i++)
            {
                String tipoM= Objects.requireNonNull(findMacchinarioById(macchinari, sequenzaProdotti[i].getCatenaDiMacchinari().get(x))).getTipologia();
                if(tipoM.equals("pezzo"))
                    tipo1=true;
                else if(tipoM.equals("utensile"))
                    tipo2=true;
            }
            if(!(tipo1&&tipo2))
                T3P2=false;
        }
        // 3) posso avere al max 2 macchinari in conflitto per ogni catena
        boolean T3P3=true;
        for(int i =0; i<sequenzaProdotti.length && T3P3;i++) //controllo ogni prodotto
        {
            int conflitti=0;
            for(String s1 : sequenzaProdotti[i].getCatenaDiMacchinari()) //per ogni macchinario della catena del prodotto
            {
                Macchinario m1= findMacchinarioById(macchinari, s1);
                for(String s2 : sequenzaProdotti[i].getCatenaDiMacchinari()) //controllo tutti i macchinari per vedere se
                    if(m1.inConflitto(findMacchinarioById(macchinari,s2)))              //conflittano con m1
                        conflitti++;
            }
            if(conflitti>2)
                T3P3=false;
        }

        if(T3P1&&T3P2&&T3P3)
            System.out.println("VALID");
        else
            System.out.println("NOT VALID");

    }

    static void stampaNumeroProdottiPerCategoria(Prodotto v[])
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
    static String idMacchinarioPiuUtilizzato(Prodotto p[], Macchinario m[])
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
    static boolean coerente(Lavoratore l, Macchinario m[])
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
    static int numeroLavoratoriNonCoerenti(Lavoratore l[], Macchinario m[])
    {
        int n=0;
        for (Lavoratore lavoratore : l)
            if (!coerente(lavoratore, m))
                n++;
        return n;
    }

    static int contaMacchinariPerZona(Macchinario m[], String zona)
    {
        int n=0;
        for( Macchinario mac : m)
            if(mac.getBloccoLavorativo().equals(zona))
                n++;
        return n;
    }

    static int numeroMacchinariPerCategoriaProdotto(Prodotto p[], String categoria)
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
    static void stampaCategoriaProdottoConCatenaPiuLunga(Prodotto p[])
    {//La categoria del prodotto finale con la catena di macchinari più lunga
        //prima trovo il prodotto con la catena più lunga, poi ne stampo la categoria
        int[] lCatena = new int[p.length]; //array parallelo ai prodotti con la lunghezza della catena
        int indexMaggiore=0;
        for(int i=0; i< p.length;i++)
        {
            lCatena[i] = p[i].getCatenaDiMacchinari().size();
            if(lCatena[i]==lCatena[indexMaggiore])
                if(p[i].getCategoria().compareToIgnoreCase(p[indexMaggiore].getCategoria())<0)
                    indexMaggiore=i;
            else if(lCatena[i]>lCatena[indexMaggiore])
                indexMaggiore=i;
        }
        System.out.println(p[indexMaggiore].getCategoria());
    }

    static void assegnaRuoloMacchinari(Macchinario m[], ArrayList<String> MW, ArrayList<String> MM, ArrayList<String> ME)
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
