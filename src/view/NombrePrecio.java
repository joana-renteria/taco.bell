package view;

public class NombrePrecio<String, Float> {
	
	Object nombre;
	Object precio;

	public NombrePrecio(String nombre, Float precio) {
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public Object getFst() {
		return nombre;
	}

	public Object getSnd() {
		return precio;
	}

}
