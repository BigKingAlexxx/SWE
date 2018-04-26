package application.SpielEquipment;

public class FigurImpl {

	public static final int POSHEIMAT = -2;
	private int nummer;
	private String farbe;
	private int position;
	
	public FigurImpl(int nummer, String farbe) {
		this.nummer = nummer;
		this.farbe = farbe;
		this.position = POSHEIMAT;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getPosition() {
		return this.position;
	}

	public String getFarbe() {
		return this.farbe;
	}
	
	public int getNummer() {
		return this.nummer;
	}
	
	
	
	
}
