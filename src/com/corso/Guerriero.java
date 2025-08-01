package com.corso;

public class Guerriero extends Personaggio implements PoteriSpeciali {

    public Guerriero(String nome, int età, double livelloVita, double livelloForza) {
        super(nome, età, livelloVita, livelloForza);
        
        // Tutti i parametri numerici passati al costruttore di Poteri sono ora castati a (double)
        this.aggiungiPotere(new Poteri(
            PoteriPersonaggio.ATTACCO_BASE_GUERRIERO.name().replace("_", " "),
            PoteriPersonaggio.ATTACCO_BASE_GUERRIERO.getDescrizione(),
            (double) PoteriPersonaggio.ATTACCO_BASE_GUERRIERO.getDanno(),
            (double) PoteriPersonaggio.ATTACCO_BASE_GUERRIERO.getCosto(),
            (double) PoteriPersonaggio.ATTACCO_BASE_GUERRIERO.getValoreEffetto()
        ));
        this.aggiungiPotere(new Poteri(
            PoteriPersonaggio.ATTACCO_FRONTALE_MISTO.name().replace("_", " "),
            PoteriPersonaggio.ATTACCO_FRONTALE_MISTO.getDescrizione(),
            (double) PoteriPersonaggio.ATTACCO_FRONTALE_MISTO.getDanno(),
            (double) PoteriPersonaggio.ATTACCO_FRONTALE_MISTO.getCosto(),
            (double) PoteriPersonaggio.ATTACCO_FRONTALE_MISTO.getValoreEffetto()
        ));
        this.aggiungiPotere(new Poteri(
            PoteriPersonaggio.RAFFICA_DI_ENERGIA.name().replace("_", " "),
            PoteriPersonaggio.RAFFICA_DI_ENERGIA.getDescrizione(),
            (double) PoteriPersonaggio.RAFFICA_DI_ENERGIA.getDanno(),
            (double) PoteriPersonaggio.RAFFICA_DI_ENERGIA.getCosto(),
            (double) PoteriPersonaggio.RAFFICA_DI_ENERGIA.getValoreEffetto()
        ));
        this.aggiungiPotere(new Poteri(
            PoteriPersonaggio.ONDA_ENERGETICA.name().replace("_", " "),
            PoteriPersonaggio.ONDA_ENERGETICA.getDescrizione(),
            (double) PoteriPersonaggio.ONDA_ENERGETICA.getDanno(),
            (double) PoteriPersonaggio.ONDA_ENERGETICA.getCosto(),
            (double) PoteriPersonaggio.ONDA_ENERGETICA.getValoreEffetto()
        ));
        this.aggiungiPotere(new Poteri(
            PoteriPersonaggio.SPIRIT_BOMB.name().replace("_", " "),
            PoteriPersonaggio.SPIRIT_BOMB.getDescrizione(),
            (double) PoteriPersonaggio.SPIRIT_BOMB.getDanno(),
            (double) PoteriPersonaggio.SPIRIT_BOMB.getCosto(),
            (double) PoteriPersonaggio.SPIRIT_BOMB.getValoreEffetto()
        ));
        this.aggiungiPotere(new Poteri(
            PoteriPersonaggio.ONDA_ENERGETICA_X10_SUPER_SAJAN.name().replace("_", " "),
            PoteriPersonaggio.ONDA_ENERGETICA_X10_SUPER_SAJAN.getDescrizione(),
            (double) PoteriPersonaggio.ONDA_ENERGETICA_X10_SUPER_SAJAN.getDanno(),
            (double) PoteriPersonaggio.ONDA_ENERGETICA_X10_SUPER_SAJAN.getCosto(),
            (double) PoteriPersonaggio.ONDA_ENERGETICA_X10_SUPER_SAJAN.getValoreEffetto()
        ));
        this.aggiungiPotere(new Poteri(
            PoteriPersonaggio.CURA_LEGGERA.name().replace("_", " "),
            PoteriPersonaggio.CURA_LEGGERA.getDescrizione(),
            (double) PoteriPersonaggio.CURA_LEGGERA.getDanno(),
            (double) PoteriPersonaggio.CURA_LEGGERA.getCosto(),
            (double) PoteriPersonaggio.CURA_LEGGERA.getValoreEffetto()
        ));
         this.aggiungiPotere(new Poteri(
            PoteriPersonaggio.RECUPERO_STAMINA_LEGGERO.name().replace("_", " "),
            PoteriPersonaggio.RECUPERO_STAMINA_LEGGERO.getDescrizione(),
            (double) PoteriPersonaggio.RECUPERO_STAMINA_LEGGERO.getDanno(),
            (double) PoteriPersonaggio.RECUPERO_STAMINA_LEGGERO.getCosto(),
            (double) PoteriPersonaggio.RECUPERO_STAMINA_LEGGERO.getValoreEffetto()
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
        System.out.println(getNome() + " ha recuperato " + curaStamina + " forza. Forza attuale: " + String.format("%.0f", getLivelloForza()));
    }

    @Override
    public void diminuisciLivelloForza(int dannoStamina) {
        double nuovaForza = getLivelloForza() - dannoStamina;
        this.setLivelloForza(nuovaForza);
        System.out.println(getNome() + " ha consumato " + dannoStamina + " forza. Forza rimanente: " + String.format("%.0f", getLivelloForza()));
    }

    @Override
    public void attacca(Personaggio bersaglio) {
        System.out.println(getNome() + " esegue un attacco standard su " + bersaglio.getNome() + "!");
    }

    @Override
    public void usaCuraPersonaggio(Personaggio bersaglio) {
        System.out.println(getNome() + " prepara una cura per " + bersaglio.getNome() + ".");
    }

    @Override
    public void usaRafforzaturaPersonaggio(Personaggio bersaglio) {
        System.out.println(getNome() + " si prepara a rafforzare " + bersaglio.getNome() + ".");
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