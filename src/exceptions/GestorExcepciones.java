package exceptions;

public class GestorExcepciones extends Exception {

	private String msg;
	int err;
	
	public GestorExcepciones(String err) {
		this.msg = err;
	}

	public GestorExcepciones(int err) {
		this.err = err;
		switch (err) {
			case 1:
				msg = "Error de conexion con la base de datos";
				break;
			case 2:
				msg = "Acceso denegado a la base de datos";
				break;
			case 3:
				msg = "Error en la operacion con la base de datos";
				break;
			case 11:
				msg = "Codigo no valido";
				break;
			case 21:
				msg = "Tipo de producto erroneo";
				break;
			case 22:
				msg = "Prefijo de usuarie erroneo";
				break;
			case 23:
				msg = "Error al crear usuarie";
				break;
			case 31:
				msg = "Error al abrir la ventana";
				break;
			case 404:
				msg = "No existe";
				break;
			case 101:
				msg = "Campo vacio";
				break;
			case 102:
				msg = "Error al seleccionar el producto";
				break;
			case 301:
				msg = "Usuario o contraseña incorrecta";
				break;
			default:
				msg = "Fatal error";
				break;
		}
	}

	public String getFullMsg() {
		return "[ERR: " + this.err + "] " + msg;
	}
	
	public String getMsg() {
		return "[ERR] " + msg;
	}
	
	public int getErrCod() {
		return this.err;
	}

}