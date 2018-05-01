package application.SpielEquipment;

import application.Spieler.SpielerImpl;

public class FigurImpl {

	public static final int POSHEIMAT = -2;
	private String farbe;
	private int position;
	private SpielerImpl spieler;
	private int nummer;
	
	public FigurImpl(SpielerImpl spieler, String farbe, int nummer) {
		this.spieler = spieler;
		this.farbe = farbe;
		this.nummer = nummer;
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
	
	public SpielerImpl getSpieler() {
		return this.spieler;
	}
	
	
	
}
