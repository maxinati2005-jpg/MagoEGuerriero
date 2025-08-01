package com.corso;

public class Mago extends Personaggio implements PoteriSpeciali {

    public Mago(String nome, int età, double livelloVita, double livelloForza) {
        super(nome, età, livelloVita, livelloForza);
        
        // Tutti i parametri numerici passati al costruttore di Poteri sono ora castati a (double)
        this.aggiungiPotere(new Poteri(
            PoteriPersonaggio.ATTACCO_BASE_MAGO.name().replace("_", " "),
            PoteriPersonaggio.ATTACCO_BASE_MAGO.getDescrizione(),
            (double) PoteriPersonaggio.ATTACCO_BASE_MAGO.getDanno(),
            (double) PoteriPersonaggio.ATTACCO_BASE_MAGO.getCosto(),
            (double) PoteriPersonaggio.ATTACCO_BASE_MAGO.getValoreEffetto()
        ));
        this.aggiungiPotere(new Poteri(
            PoteriPersonaggio.FULMINE_ELETTRICO.name().replace("_", " "),
            PoteriPersonaggio.FULMINE_ELETTRICO.getDescrizione(),
            (double) PoteriPersonaggio.FULMINE_ELETTRICO.getDanno(),
            (double) PoteriPersonaggio.FULMINE_ELETTRICO.getCosto(),
            (double) PoteriPersonaggio.FULMINE_ELETTRICO.getValoreEffetto()
        ));
        this.aggiungiPotere(new Poteri(
            PoteriPersonaggio.ONDA_FUOCO_DISTRUTTIVO.name().replace("_", " "),
            PoteriPersonaggio.ONDA_FUOCO_DISTRUTTIVO.getDescrizione(),
            (double) PoteriPersonaggio.ONDA_FUOCO_DISTRUTTIVO.getDanno(),
            (double) PoteriPersonaggio.ONDA_FUOCO_DISTRUTTIVO.getCosto(),
            (double) PoteriPersonaggio.ONDA_FUOCO_DISTRUTTIVO.getValoreEffetto()
        ));
        this.aggiungiPotere(new Poteri(
            PoteriPersonaggio.PALLA_DI_GHIACCIO.name().replace("_", " "),
            PoteriPersonaggio.PALLA_DI_GHIACCIO.getDescrizione(),
            (double) PoteriPersonaggio.PALLA_DI_GHIACCIO.getDanno(),
            (double) PoteriPersonaggio.PALLA_DI_GHIACCIO.getCosto(),
            (double) PoteriPersonaggio.PALLA_DI_GHIACCIO.getValoreEffetto()
        ));
        this.aggiungiPotere(new Poteri(
            PoteriPersonaggio.CASTIGO_DEL_VUOTO.name().replace("_", " "),
            PoteriPersonaggio.CASTIGO_DEL_VUOTO.getDescrizione(),
            (double) PoteriPersonaggio.CASTIGO_DEL_VUOTO.getDanno(),
            (double) PoteriPersonaggio.CASTIGO_DEL_VUOTO.getCosto(),
            (double) PoteriPersonaggio.CASTIGO_DEL_VUOTO.getValoreEffetto()
        ));
        this.aggiungiPotere(new Poteri(
            PoteriPersonaggio.ARMAGHEDDON.name().replace("_", " "),
            PoteriPersonaggio.ARMAGHEDDON.getDescrizione(),
            (double) PoteriPersonaggio.ARMAGHEDDON.getDanno(),
            (double) PoteriPersonaggio.ARMAGHEDDON.getCosto(),
            (double) PoteriPersonaggio.ARMAGHEDDON.getValoreEffetto()
        ));
        this.aggiungiPotere(new Poteri(
            PoteriPersonaggio.CURA_MAGGIORE.name().replace("_", " "),
            PoteriPersonaggio.CURA_MAGGIORE.getDescrizione(),
            (double) PoteriPersonaggio.CURA_MAGGIORE.getDanno(),
            (double) PoteriPersonaggio.CURA_MAGGIORE.getCosto(),
            (double) PoteriPersonaggio.CURA_MAGGIORE.getValoreEffetto()
        ));
        this.aggiungiPotere(new Poteri(
            PoteriPersonaggio.AUMENTO_FORZA_TEMPORANEO.name().replace("_", " "),
            PoteriPersonaggio.AUMENTO_FORZA_TEMPORANEO.getDescrizione(),
            (double) PoteriPersonaggio.AUMENTO_FORZA_TEMPORANEO.getDanno(),
            (double) PoteriPersonaggio.AUMENTO_FORZA_TEMPORANEO.getCosto(),
            (double) PoteriPersonaggio.AUMENTO_FORZA_TEMPORANEO.getValoreEffetto()
        ));
        this.aggiungiPotere(new Poteri(
            PoteriPersonaggio.RECUPERO_STAMINA_MAGGIORE.name().replace("_", " "),
            PoteriPersonaggio.RECUPERO_STAMINA_MAGGIORE.getDescrizione(),
            (double) PoteriPersonaggio.RECUPERO_STAMINA_MAGGIORE.getDanno(),
            (double) PoteriPersonaggio.RECUPERO_STAMINA_MAGGIORE.getCosto(),
            (double) PoteriPersonaggio.RECUPERO_STAMINA_MAGGIORE.getValoreEffetto()
        ));
    }

    @Override
    public void aumentaLivelloVita(int cura) {
        double nuovaVita = getLivelloVita() + cura;
        this.setLivelloVita(nuovaVita);
        System.out.println(getNome() + " si è curato di " + cura + " punti vita! Vita attuale: " + String.format("%.0f", getLivelloVita()));
    }

    @Override
    public void diminuisciLivelloVita(int danno) {
        double nuovaVita = getLivelloVita() - danno;
        this.setLivelloVita(nuovaVita);
        System.out.println(getNome() + " ha subito " + danno + " danni. Vita rimanente: " + String.format("%.0f", getLivelloVita()));
        if (getLivelloVita() <= 0) {
            System.out.println(getNome() + " è stato sconfitto!");
        }
    }

    @Override
    public void aumentaLivelloForza(int curaStamina) {
        double nuovaForza = getLivelloForza() + curaStamina;
        this.setLivelloForza(nuovaForza);
        System.out.println(getNome() + " ha recuperato " + curaStamina + " mana. Mana attuale: " + String.format("%.0f", getLivelloForza()));
    }

    @Override
    public void diminuisciLivelloForza(int dannoStamina) {
        double nuovaForza = getLivelloForza() - dannoStamina;
        this.setLivelloForza(nuovaForza);
        System.out.println(getNome() + " ha consumato " + dannoStamina + " mana. Mana rimanente: " + String.format("%.0f", getLivelloForza()));
    }

    @Override
    public void attacca(Personaggio bersaglio) {
        System.out.println(getNome() + " lancia un incantesimo standard su " + bersaglio.getNome() + "!");
    }

    @Override
    public void usaCuraPersonaggio(Personaggio bersaglio) {
        System.out.println(getNome() + " prepara un incantesimo di cura per " + bersaglio.getNome() + ".");
    }

    @Override
    public void usaRafforzaturaPersonaggio(Personaggio bersaglio) {
        System.out.println(getNome() + " prepara un incantesimo di rafforzamento per " + bersaglio.getNome() + ".");
    }

    @Override
    public void corre(int km) {
        System.out.println(getNome() + " corre per " + km + " km.");
    }

    @Override
    public void salta(int cm) {
        System.out.println(getNome() + " salta per " + cm + " cm.");
    }
}