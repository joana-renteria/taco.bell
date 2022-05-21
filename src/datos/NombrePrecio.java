package datos;

public class NombrePrecio<String, Float> {
	
	Object nombre;
	Object precio;
	Object cod;

	public NombrePrecio(String nombre, Float precio, String cod) {
		this.nombre = nombre;
		this.precio = precio;
		this.cod = cod;
	}
	
	public Object getFst() {
		return nombre;
	}

	public Object getSnd() {
		return precio;
	}

	public Object getTrd() {
		return cod;
	}

}
