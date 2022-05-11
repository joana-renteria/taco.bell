package exceptions;

public GestorExcepciones extends Exception {

	private String msg;
	int err;

	public GestorExcepciones(int err) {
		this.err = err;
		switch (err) {
			case 1:
				msg = "Error de conexion con la base de datos";
			case 2:
				msg = "Acceso denegado a la base de datos";
			case 11:
				msg = "Codigo no valido";
			case 21:
				msg = "Tipo de producto erroneo";
			case 31:
				msg = "Error al abrir la ventana";
			case 404:
				msg = "No existe";
			case 101:
				msg = "Campo vacio";
			case 102:
				msg = "Error al seleccionar el producto";
			case 301:
				msg = "Usuario o contraseña incorrecta";
			default:
				msg = "Fatal error";
		}
	}

	public String getMsg() {
		return "[ERR: " + this.err + "] " + msg;
	}

}
