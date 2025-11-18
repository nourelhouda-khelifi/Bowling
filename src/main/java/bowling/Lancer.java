package bowling;

public class Lancer {
	private final int quillesAbattues; // nombre de quilles quil a abattues lors de ce lancer

	public Lancer(int quillesAbattues) {
		if (quillesAbattues < 0 || quillesAbattues > 10) {
			throw new IllegalArgumentException("Le nombre de quilles abattues doit être entre 0 et 10.");
		}
		this.quillesAbattues = quillesAbattues;
	}

	public int getQuillesAbattues() {
		return quillesAbattues;
	}
}
