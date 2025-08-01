package com.corso;

public interface Azioni {
	void aumentaLivelloVita(int cura);

	void diminuisciLivelloVita(int danno);

	void aumentaLivelloForza(int curaStamina);

	void diminuisciLivelloForza(int dannoStamina);

	void attacca(Personaggio bersaglio);

	void corre(int km);

	void salta(int cm);
}
