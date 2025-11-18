package bowling;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancers successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class PartieMonoJoueur {
	private final List<Tour> tours; 
	private int tourCourant; 

	/**
	 * Constructeur
	 */
	public PartieMonoJoueur() {
		this.tours = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			tours.add(new Tour(i + 1));
		}
		this.tourCourant = 1;
		
	}

	/**
	 * Cette méthode doit être appelée à chaque lancer de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de ce lancer
	 * @throws IllegalStateException si la partie est terminée
	 * @return vrai si le joueur doit lancer à nouveau pour continuer son tour, faux sinon	
	 */
	public boolean enregistreLancer(int nombreDeQuillesAbattues) {
		if (estTerminee()) {
			throw new IllegalStateException("La partie est terminée.");
		}

		Tour tour = tours.get(tourCourant - 1);
		boolean tourContinue = tour.ajouterLancer(nombreDeQuillesAbattues);

		if (!tourContinue && tourCourant < 10) {
			tourCourant++;
		}

		return tourContinue;
	}

	/**
	 * Cette méthode donne le score du joueur.
	 * Si la partie n'est pas terminée, on considère que les lancers restants
	 * abattent 0 quille.
	 * @return Le score du joueur
	 */
	public int score() {

		int scoreTotal = 0;
		for (int i = 0; i < tours.size(); i++) {
			Tour tour = tours.get(i);
			Tour tourSuivant = (i + 1 < tours.size()) ? tours.get(i + 1) : null;
			Tour tourSuivantSuivant = (i + 2 < tours.size()) ? tours.get(i + 2) : null;
			scoreTotal += tour.calculerScore(tourSuivant, tourSuivantSuivant);
		}
		return scoreTotal;
	}

	/**
	 * @return vrai si la partie est terminée pour ce joueur, faux sinon
	 */
	public boolean estTerminee() {

		return tourCourant > 10 || (tourCourant == 10 && tours.get(9).estTermine());
	}


	/**
	 * @return Le numéro du tour courant [1..10], ou 0 si le jeu est fini
	 */
	public int numeroTourCourant() {
		return estTerminee() ? 0 : tourCourant;
	}

	/**
	 * @return Le numéro du prochain lancer pour tour courant [1..3], ou 0 si le jeu
	 *         est fini
	 */
	public int numeroProchainLancer() {

		if (estTerminee()) {
			return 0;
		}
		return tours.get(tourCourant - 1).getNumeroProchainLancer();
	}
	}

