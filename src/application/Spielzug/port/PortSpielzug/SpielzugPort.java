package application.Spielzug.port.PortSpielzug;
public interface SpielzugPort {
	
	SpielzugManagement spielzugManagement();
	void setAnzahlWuerfe(int num);
}
