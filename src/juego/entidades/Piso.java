package juego.entidades;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Piso {

	private int cantidadPisos;
	private Image imgPiso;
	private int[][] bloquesPiso;
	private int anchoLadrillo;
	private int altoLadrillo;

	public Piso(Entorno e, int numeroDePisos) {
		this.cantidadPisos = numeroDePisos;
		this.imgPiso = Herramientas.cargarImagen("juego/entidades/imagenes/ladrillo.png");
		this.anchoLadrillo = imgPiso.getWidth(e); // vale 32
		this.altoLadrillo = imgPiso.getHeight(e);
		this.bloquesPiso = new int[cantidadPisos][e.ancho() / anchoLadrillo];
		// Crea el primer piso:
		for (int j = 0; j < e.ancho() / anchoLadrillo; j++) {
			bloquesPiso[0][j] = 1;
		}
		// Crea el resto de los pisos
		for (int i = 1; i < cantidadPisos; i++) {
			for (int j = 0; j < e.ancho() / anchoLadrillo; j++) {
				bloquesPiso[i][j] = 1;
				if (i % 2 == 1 && j > e.ancho() / anchoLadrillo - 3) {
					bloquesPiso[i][j] = 0;
				}
				if (i % 2 == 0 && j < 2) {
					bloquesPiso[i][j] = 0;
				}
			}
		}
	}

	public void dibujar(Entorno e) {
		double x;
		double y;
		for (int i = 0; i < cantidadPisos; i++) {
			for (int j = 0; j < e.ancho() / anchoLadrillo; j++) {
				x = j * anchoLadrillo + anchoLadrillo / 2;
				y = alturaPiso(i + 1) + altoLadrillo / 2;
				if (bloquesPiso[i][j] == 1) {
					e.dibujarImagen(imgPiso, x, y, 0, 1);
				}
			}
		}
	}

	public boolean hayBloques(double xDeUnPersonaje, double yDeUnPersonaje) {
		for (int i = 0; i < cantidadPisos; i++) {
			if (yDeUnPersonaje >= alturaPiso(i + 1) && yDeUnPersonaje < alturaPiso(i + 1) + altoLadrillo) {
				for (int j = 0; j < bloquesPiso[i].length; j++) {
					if (j * anchoLadrillo <= xDeUnPersonaje && xDeUnPersonaje < (j + 1) * anchoLadrillo) {
						return bloquesPiso[i][j] == 1;
					}
				}
			}
		}
		return false;
	}

	public int numeroPiso(double yDeUnPersonaje) {
		for (int i = cantidadPisos; i > -3; i--) {
			if (yDeUnPersonaje <= alturaPiso(i + 1) && yDeUnPersonaje > alturaPiso(i + 2)) {
				return (i + 1);
			}
		}
		return -1;
	}

	public double alturaPiso(int piso) {
		return 500 - (500 * (piso - 1) / cantidadPisos); // expliquen en el informe
	}
}
