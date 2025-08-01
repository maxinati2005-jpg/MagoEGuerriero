package com.corso;

public class Poteri {

    private String nome;
    private String descrizione;
    private double danno;
    private double costo;
    private double valoreEffetto;

    // Costruttore corretto con 3 double (danno, costo, valoreEffetto)
    public Poteri(String nome, String descrizione, double danno, double costo, double valoreEffetto) {
        super();
        this.nome = nome;
        this.descrizione = descrizione;
        this.danno = danno;
        this.costo = costo;
        this.valoreEffetto = valoreEffetto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getDanno() {
        return danno;
    }

    public void setDanno(double danno) {
        this.danno = danno;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getValoreEffetto() {
        return valoreEffetto;
    }

    public void setValoreEffetto(double valoreEffetto) {
        this.valoreEffetto = valoreEffetto;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();

        if (danno > 0) {
            info.append("Danno: ").append(String.format("%.0f", danno));
        }
        if (valoreEffetto > 0) {
            if (!info.isEmpty()) {
                info.append(", ");
            }
            if (nome.toLowerCase().contains("cura")) {
                info.append("Cura Vita: ").append(String.format("%.0f", valoreEffetto));
            } else if (nome.toLowerCase().contains("recupero stamina") || nome.toLowerCase().contains("recupero mana")) {
                info.append("Recupero Energia: ").append(String.format("%.0f", valoreEffetto));
            } else {
                info.append("Effetto: ").append(String.format("%.0f", valoreEffetto));
            }
        }
        
        if (nome.toLowerCase().contains("recupero stamina") || nome.toLowerCase().contains("recupero mana")) {
            info.append(", Costo: Vita (vedi descrizione)");
        } else {
            info.append(", Costo: ").append(String.format("%.0f", costo)).append(" Energia");
        }
       
        return nome + " (" + info.toString() + ") - " + descrizione;
    }
}