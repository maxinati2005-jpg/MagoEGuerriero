package com.corso;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public abstract class Personaggio implements Azioni {

    private String nome;
    private int età;
    private double livelloForza; // Stamina/Mana
    private double livelloVita;
    private List<Poteri> listaPoteri;
    private final double MAX_FORZA_INIZIALE;
    private final double MAX_VITA_INIZIALE;

    public Personaggio(String nome, int età, double livelloVita, double livelloForza) {
        super();
        this.nome = nome;
        this.età = età;
        this.livelloVita = livelloVita;
        this.livelloForza = livelloForza;
        this.MAX_FORZA_INIZIALE = livelloForza;
        this.MAX_VITA_INIZIALE = livelloVita;
        this.listaPoteri = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEtà() {
        return età;
    }

    public void setEtà(int età) {
        this.età = età;
    }

    public double getLivelloForza() {
        return livelloForza;
    }

    public void setLivelloForza(double livelloForza) {
        this.livelloForza = Math.max(0, Math.min(livelloForza, MAX_FORZA_INIZIALE));
    }

    public double getLivelloVita() {
        return livelloVita;
    }

    public void setLivelloVita(double livelloVita) {
        this.livelloVita = Math.max(0, Math.min(livelloVita, MAX_VITA_INIZIALE));
    }

    public List<Poteri> getListaPoteri() {
        return listaPoteri;
    }

    public void setListaPoteri(List<Poteri> listaPoteri) {
        this.listaPoteri = listaPoteri;
    }

    public void aggiungiPotere(Poteri potere) {
        this.listaPoteri.add(potere);
    }

    /**
     * Tenta una schivata o un contrattacco.
     * Restituisce true se l'attacco è stato evitato (schivato o contrattaccato), false altrimenti.
     * Nel caso di contrattacco, infligge danno al chiamante del metodo.
     * @param costoAzioneDifensiva Il costo in energia per tentare l'azione difensiva.
     * @param attaccanteIlCuiAttaccoVieneEvitato Il personaggio che ha sferrato l'attacco originale.
     * @return true se l'attacco è stato evitato, false altrimenti.
     */
    public boolean tentaSchivataOContrattacca(int costoAzioneDifensiva, Personaggio attaccanteIlCuiAttaccoVieneEvitato) {
        if (this.getLivelloForza() < costoAzioneDifensiva) {
            System.out.println(getNome() + " non ha abbastanza energia per tentare una difesa!");
            return false;
        }

        this.diminuisciLivelloForza(costoAzioneDifensiva); // Consuma stamina/mana per la difesa

        Random random = new Random();
        // Probabilità di successo per schivata/contrattacco combinata
        double probabilitaSuccesso = 0.10; // Ad esempio, 10% di possibilità di schivare o contrattaccare
        double numeroCasuale = random.nextDouble();

        if (numeroCasuale < probabilitaSuccesso) {
            // Se la difesa ha successo, decidi se è una schivata o un contrattacco
            if (random.nextBoolean()) { // 50% di probabilità per schivata, 50% per contrattacco
                System.out.println(getNome() + " ha **schivato** con successo l'attacco!");
                return true; // Attacco evitato
            } else {
                int dannoContrattacco = 50; // Danno fisso del contrattacco
                System.out.println(getNome() + " ha **contrattaccato** infliggendo " + dannoContrattacco + " danni a " + attaccanteIlCuiAttaccoVieneEvitato.getNome() + "!");
                attaccanteIlCuiAttaccoVieneEvitato.diminuisciLivelloVita(dannoContrattacco);
                return true; // Attacco evitato grazie al contrattacco
            }
        } else {
            System.out.println(getNome() + " ha fallito la difesa!");
            return false; // Attacco non evitato
        }
    }

    @Override
    public abstract void aumentaLivelloVita(int cura);

    @Override
    public abstract void diminuisciLivelloVita(int danno);

    @Override
    public abstract void aumentaLivelloForza(int curaStamina);

    @Override
    public abstract void diminuisciLivelloForza(int dannoStamina);

    @Override
    public abstract void attacca(Personaggio bersaglio);

    @Override
    public abstract void corre(int km);

    @Override
    public abstract void salta(int cm);

    @Override
    public String toString() {
        return "Nome: " + nome + ", Età: " + età + ", Vita: " + String.format("%.0f", livelloVita) +
               ", Forza/Mana: " + String.format("%.0f", livelloForza) +
               ", Poteri: " + (listaPoteri.isEmpty() ? "Nessuno" : Arrays.toString(listaPoteri.stream().map(Poteri::getNome).toArray()));
    }
}