package resources;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

public class Fuentes {

	File fontFile = new File("src/resources/iosevka-aile-heavy.ttf");

	GraphicsEnvironment ge = GraphicsEnvironment
	        .getLocalGraphicsEnvironment();
	
	public Fuentes() {
		
	}
	
	/**
	 * Cargar la tipografia abierta Iosevka
	 */
		
	public void cargarTipografia() {
		try {
			Font myFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
			ge.registerFont(myFont);
		} catch (Exception e) {
			System.err.println("Error cargando la tipografia Iosevka Aile Heavy");
			e.printStackTrace();
		}
	}


}
