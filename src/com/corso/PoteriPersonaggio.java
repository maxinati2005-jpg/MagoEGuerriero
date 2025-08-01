package com.corso;

public enum PoteriPersonaggio {
    // Guerriero (Nome visualizzato, Danno, Costo in Forza, Valore Effetto)
    ATTACCO_BASE_GUERRIERO("Un Colpo Al Viso Dell Avversario", 20, 20, 0),
    ATTACCO_FRONTALE_MISTO("Una raffica di pugni e calci veloce e precisa", 50, 40, 0),
    RAFFICA_DI_ENERGIA("Una scarica di colpi che distrugge il terreno e bombarda l'avversario", 135, 100, 0),
    ONDA_ENERGETICA("Una concentrazione di energia in un solo punto violentemente lanciata verso l'avversario attraverso un raggio", 250, 200, 0),
    SPIRIT_BOMB("Il Guerriero raccoglie l'energia di tutte le forme viventi della Terra in un'enorme sfera d'energia scaraventandola verso l'avversario", 570, 400, 0),
    ONDA_ENERGETICA_X10_SUPER_SAJAN("Un'enorme quantità di energia sprigionata in un solo colpo, disintegrando qualsiasi cosa incontri, con un colpo moltiplicato X 10 e una forza moltiplicata X150", 700, 800, 0),

    // Mago (Nome visualizzato, Danno, Costo in Forza/Mana, Valore Effetto)
    ATTACCO_BASE_MAGO("Una Piccola Scarica Di Fiamme Sprigionata Sull Avversario", 25, 20, 0),
    FULMINE_ELETTRICO("Un fulmine di poca carica e intensità magica", 70, 40, 0),
    ONDA_FUOCO_DISTRUTTIVO("Un'onda di fiamme che pervade il campo di battaglia", 150, 120, 0),
    PALLA_DI_GHIACCIO("Un enorme agglomerato di ghiaccio con punte affilate come rasoi che, se lanciato, provoca danni micidiali", 300, 400, 0),
    CASTIGO_DEL_VUOTO("Un turbine che dal cielo risucchia la forza vitale di tutte le creature", 400, 500, 0),
    ARMAGHEDDON("Tempesta che pervade il mondo di fulmini viola, distruggendo ogni cosa e forma di vita", 875, 800, 0),

    // Poteri di Cura, Rafforzamento e Recupero Stamina/Mana
    // CURA: Costano Mana/Forza e recuperano Vita
    CURA_LEGGERA("Una piccola cura che ripristina la vitalità.", 0, 30, 50), // Costo: 30 Energia, Cura: 50 Vita
    CURA_MAGGIORE("Una potente cura che ripristina molta vitalità.", 0, 100, 100), // Costo: 100 Energia, Cura: 100 Vita
    AUMENTO_FORZA_TEMPORANEO("Aumenta temporaneamente la forza del personaggio.", 0, 40, 50),

    // RECUPERO STAMINA/MANA: Costano Vita e recuperano Mana/Forza
    RECUPERO_STAMINA_LEGGERO("Recupera una piccola quantità di stamina/mana consumando vita.", 0, 0, 75), // Effetto: 75 Energia, Costo: 0 (gestito come costo vita)
    RECUPERO_STAMINA_MAGGIORE("Recupera una grande quantità di stamina/mana consumando vita.", 0, 0, 150); // Effetto: 150 Energia, Costo: 0 (gestito come costo vita)


    private final String descrizione;
    private final int danno;
    private final int costo; // Questo sarà il costo in Forza/Mana (per attacchi e cure VITA)
    private final int valoreEffetto;

    // Costruttore corretto con 4 parametri int
    PoteriPersonaggio(String descrizione, int danno, int costo, int valoreEffetto) {
        this.descrizione = descrizione;
        this.danno = danno;
        this.costo = costo;
        this.valoreEffetto = valoreEffetto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getDanno() {
        return danno;
    }

    public int getCosto() {
        return costo;
    }

    public int getValoreEffetto() {
        return valoreEffetto;
    }

    @Override
    public String toString() {
        String baseName = name().replace("_", " ");
        StringBuilder info = new StringBuilder();

        if (danno > 0) {
            info.append("Danno: ").append(danno);
        }
        if (valoreEffetto > 0) {
            if (info.length() > 0) {
                info.append(", ");
            }
            if (baseName.toLowerCase().contains("cura")) {
                info.append("Cura Vita: ").append(valoreEffetto);
            } else if (baseName.toLowerCase().contains("recupero stamina") || baseName.toLowerCase().contains("recupero mana")) {
                info.append("Recupero Energia: ").append(valoreEffetto);
            } else {
                info.append("Effetto: ").append(valoreEffetto);
            }
        }

        if (baseName.toLowerCase().contains("recupero stamina") || baseName.toLowerCase().contains("recupero mana")) {
             info.append(", Costo: Vita (vedi descrizione)");
        } else {
             info.append(", Costo: ").append(costo).append(" Energia");
        }
       
        return baseName + " (" + info.toString() + ") - " + descrizione;
    }
}