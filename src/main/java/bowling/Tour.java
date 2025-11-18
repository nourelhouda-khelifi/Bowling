package bowling;

import java.util.ArrayList;
import java.util.List;

public class Tour {
	private final int numero; // Turn number from 1 to 10
	private final List<Integer> lancers; //liste des lancers dans ce tour
	private final int maxLancers; // max lancers dans ce tour

	public Tour(int numero) {
		this.numero = numero;
		this.lancers = new ArrayList<>();
		this.maxLancers = (numero == 10) ? 3 : 2; // dernier tour peut avoir 3 lancers
	}

	public boolean ajouterLancer(int nombreDeQuillesAbattues) {
		if (estTermine()) {
			throw new IllegalStateException("Le tour est déjà terminé.");
		}
		lancers.add(nombreDeQuillesAbattues);
		return !estTermine();
	}

	public boolean estStrike() {
		return lancers.size() > 0 && lancers.get(0) == 10;
	}

	public boolean estSpare() {
		return lancers.size() == 2 && lancers.get(0) + lancers.get(1) == 10;
	}

	public boolean estTermine() {
		if (numero == 10) {
			if (lancers.size() == 3) return true;
			if (lancers.size() == 2 && lancers.get(0) + lancers.get(1) < 10) return true;
			return false;
		}
		return lancers.size() == maxLancers || estStrike();
	}

	public int calculerScore(Tour tourSuivant, Tour tourSuivantSuivant) {
		int score = lancers.stream().mapToInt(Integer::intValue).sum();
		if (estStrike()) {
			score += bonusStrike(tourSuivant, tourSuivantSuivant);
		} else if (estSpare()) {
			score += bonusSpare(tourSuivant);
		}
		return score;
	}

	private int bonusStrike(Tour tourSuivant, Tour tourSuivantSuivant) {
		if (tourSuivant == null) return 0;
		if (tourSuivant.lancers.size() >= 2) {
			return tourSuivant.lancers.get(0) + tourSuivant.lancers.get(1);
		} else if (tourSuivantSuivant != null) {
			return tourSuivant.lancers.get(0) + tourSuivantSuivant.lancers.get(0);
		}
		return tourSuivant.lancers.get(0);
	}

	private int bonusSpare(Tour tourSuivant) {
		if (tourSuivant == null || tourSuivant.lancers.isEmpty()) return 0;
		return tourSuivant.lancers.get(0);
	}

	public int getNumeroProchainLancer() {
		return lancers.size() + 1;
	}
}
