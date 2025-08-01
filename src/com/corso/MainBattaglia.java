package com.corso;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MainBattaglia {

	private static final int COSTO_AZIONE_DIFENSIVA = 50; // Costo per tentare una schivata o contrattacco
	private static final int RECUPERO_ENERGIA_PER_TURNO = 100; // Recupero fisso di energia per turno
	private static final int RECUPERO_VITA_PER_TURNO = 100; // Recupero fisso di vita per turno
	private static final int AZIONI_PER_TURNO_FISSE = 3; // Numero fisso di azioni per turno

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// 1. Creazione dei personaggi - AUMENTATA VITA E FORZA/MANA INIZIALE
		Guerriero guerriero = new Guerriero("Maxi", 20, 2000, 800);
		Mago mago = new Mago("Dio Distruzione 19 Universo", 188, 1500, 1000);

		System.out.println("--- La Battaglia Inizia! ---");
		System.out.println(guerriero);
		System.out.println(mago);
		System.out.println("---------------------------\n");

		int turno = 1;
		while (guerriero.getLivelloVita() > 0 && mago.getLivelloVita() > 0) {
			System.out.println("--- Turno " + turno + " ---");

			// Turno del Guerriero
			System.out.println("\nÈ il turno del GUERRIERO!");
			eseguiTurnoCompleto(guerriero, mago, input);
			if (mago.getLivelloVita() <= 0) {
				System.out.println("\n" + mago.getNome() + " è stato sconfitto!");
				break;
			}

			System.out.println("\n---------------------------");
			System.out.println("Situazione attuale:");
			System.out.println(guerriero);
			System.out.println(mago);
			System.out.println("---------------------------\n");

			// Turno del Mago
			System.out.println("È il turno del MAGO!");
			eseguiTurnoCompleto(mago, guerriero, input);
			if (guerriero.getLivelloVita() <= 0) {
				System.out.println("\n" + guerriero.getNome() + " è stato sconfitto!");
				break;
			}

			System.out.println("\n---------------------------");
			System.out.println("Situazione attuale:");
			System.out.println(guerriero);
			System.out.println(mago);
			System.out.println("---------------------------\n");

			turno++;
			if (turno > 50) {
				System.out.println("La battaglia è durata troppo! Pareggio.");
				break;
			}
		}

		System.out.println("\n--- Battaglia Terminata! ---");
		if (guerriero.getLivelloVita() > 0) {
			System.out.println(guerriero.getNome() + " ha vinto!");
		} else if (mago.getLivelloVita() > 0) {
			System.out.println(mago.getNome() + " ha vinto!");
		} else {
			System.out.println("Entrambi i personaggi sono stati sconfitti! Un pareggio sanguinoso.");
		}
		input.close();
	}

	public static void eseguiTurnoCompleto(Personaggio attaccante, Personaggio bersaglio, Scanner input) {
		int azioniRimaste = AZIONI_PER_TURNO_FISSE; // Numero fisso di azioni per turno

		while (azioniRimaste > 0 && attaccante.getLivelloVita() > 0 && bersaglio.getLivelloVita() > 0) {
			System.out.println("\n" + attaccante.getNome() + " ha " + azioniRimaste + " azioni rimaste.");
			System.out.println("Vita: " + String.format("%.0f", attaccante.getLivelloVita()) + ", Forza/Mana: "
					+ String.format("%.0f", attaccante.getLivelloForza()));

			List<Poteri> poteriDisponibili = new ArrayList<>();
			for (Poteri p : attaccante.getListaPoteri()) {
				// Per i poteri di recupero stamina/mana, il costo è in vita
				if (p.getNome().toLowerCase().contains("recupero stamina")
						|| p.getNome().toLowerCase().contains("recupero mana")) {
					poteriDisponibili.add(p); // Il controllo della vita viene fatto dopo la selezione
				} else if (attaccante.getLivelloForza() >= p.getCosto()) {
					poteriDisponibili.add(p);
				}
			}

			int scelta;
			boolean sceltaValida = false;

			while (!sceltaValida) {
				System.out.println("\nScegli un'azione per " + attaccante.getNome() + ":");
				System.out.println("0. Termina turno (passa il turno)");
				for (int i = 0; i < poteriDisponibili.size(); i++) {
					System.out.println((i + 1) + ". " + poteriDisponibili.get(i));
				}

				System.out.print("La tua scelta: ");
				try {
					scelta = input.nextInt();
					input.nextLine();

					if (scelta == 0) {
						System.out.println(attaccante.getNome() + " termina il turno.");
						azioniRimaste = 0;
						sceltaValida = true;
					} else if (scelta > 0 && scelta <= poteriDisponibili.size()) {
						Poteri potereScelto = poteriDisponibili.get(scelta - 1);

						// Gestione speciale per i poteri di recupero stamina/mana (costano vita)
						if (potereScelto.getNome().toLowerCase().contains("recupero stamina")
								|| potereScelto.getNome().toLowerCase().contains("recupero mana")) {
							int costoVitaRecupero = 50; // Costo base per recupero leggero
							if (potereScelto.getNome().toLowerCase().contains("maggiore")) {
								costoVitaRecupero = 100; // Costo maggiore per recupero maggiore
							}

							if (attaccante.getLivelloVita() > costoVitaRecupero) {
								attaccante.diminuisciLivelloVita(costoVitaRecupero); // Consuma vita
								attaccante.aumentaLivelloForza((int) potereScelto.getValoreEffetto()); // Recupera
																										// forza/mana
								System.out.println(attaccante.getNome() + " usa " + potereScelto.getNome() + "! Costo: "
										+ costoVitaRecupero + " Vita.");
								System.out.println("Vita attuale: " + String.format("%.0f", attaccante.getLivelloVita())
										+ ", Energia attuale: " + String.format("%.0f", attaccante.getLivelloForza()));
								azioniRimaste--; // Consuma un'azione
								sceltaValida = true;
							} else {
								System.out.println(attaccante.getNome() + " non ha abbastanza vita per usare "
										+ potereScelto.getNome() + "!");
							}
						}
						// Gestione per tutti gli altri poteri (costano mana/forza)
						else if (attaccante.getLivelloForza() >= potereScelto.getCosto()) {
							attaccante.diminuisciLivelloForza((int) potereScelto.getCosto()); // Consuma il costo del
																								// potere

							if (potereScelto.getDanno() > 0) {
								System.out.println("\n--- " + bersaglio.getNome() + " può tentare una difesa! ---");
								// Chiamata al nuovo metodo che gestisce schivata o contrattacco
								if (bersaglio.tentaSchivataOContrattacca(COSTO_AZIONE_DIFENSIVA, attaccante)) {
									// Se la difesa ha successo, il danno originale viene evitato.
									// Il contrattacco è già stato gestito all'interno del metodo.
								} else {
									System.out.println("L'attacco di " + attaccante.getNome() + " colpisce "
											+ bersaglio.getNome() + "!");
									bersaglio.diminuisciLivelloVita((int) potereScelto.getDanno());
								}
								System.out.println("------------------------------------");
							} else if (potereScelto.getValoreEffetto() > 0) {
								System.out.println(attaccante.getNome() + " usa " + potereScelto.getNome() + "!");
								if (potereScelto.getNome().toLowerCase().contains("cura")) {
									attaccante.aumentaLivelloVita((int) potereScelto.getValoreEffetto()); // Recupera
																											// vita
								} else if (potereScelto.getNome().toLowerCase().contains("aumento forza")) {
									attaccante.aumentaLivelloForza((int) potereScelto.getValoreEffetto()); // Buff
																											// temporaneo
																											// alla
																											// forza
								}
								System.out.println("Vita attuale: " + String.format("%.0f", attaccante.getLivelloVita())
										+ ", Energia attuale: " + String.format("%.0f", attaccante.getLivelloForza()));
							} else {
								System.out.println(attaccante.getNome() + " usa " + potereScelto.getNome()
										+ " ma non ha un effetto diretto di danno o cura/buff.");
							}

							azioniRimaste--; // Consuma un'azione
							sceltaValida = true;
						} else {
							System.out
									.println("Non hai abbastanza forza/mana per usare " + potereScelto.getNome() + "!");
						}
					} else {
						System.out.println(
								"Scelta non valida. Inserisci un numero tra 0 e " + poteriDisponibili.size() + ".");
					}
				} catch (java.util.InputMismatchException e) {
					System.out.println("Input non valido. Inserisci un numero.");
					input.nextLine();
				}
			}
		}
		// Recupero automatico di vita e energia a fine turno
		if (attaccante.getLivelloVita() > 0) {
			attaccante.aumentaLivelloForza(RECUPERO_ENERGIA_PER_TURNO);
			attaccante.aumentaLivelloVita(RECUPERO_VITA_PER_TURNO);
			System.out.println(attaccante.getNome() + " recupera " + RECUPERO_ENERGIA_PER_TURNO + " energia e "
					+ RECUPERO_VITA_PER_TURNO + " vita a fine turno!");
		}
	}
}