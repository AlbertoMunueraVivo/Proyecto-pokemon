package application;

public class Movimiento {
    private String nombre;
    private int potencia;
    private int precision;
    private String tipoDaño;
    private String tipo;  // Nuevo atributo para el tipo del movimiento


    public Movimiento(String nombre, int potencia, int precision, String tipo, String tipoDaño) {
        this.nombre = nombre;
        this.potencia = potencia;
        this.precision = precision;
        this.tipo = tipo;
        this.tipoDaño = tipoDaño;
    }

    public String getTipoDaño() {
		return tipoDaño;
	}

	public void setTipoDaño(String tipoDaño) {
		this.tipoDaño = tipoDaño;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public String getNombre() {
        return nombre;
    }

	public int getPotencia() {
        return potencia;
    }

    public int getPrecision() {
        return precision;
    }
}
