package juego.entidades;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Tiranosaurio {

	private double x;
	private double y;
	private double velocidad;
	private Image img;
	private double gravedad;
	private double factorDeDesplazamiento;
	private int ancho;
	private int alto;

	public Tiranosaurio(Entorno e, Piso estructura) {
		this.img = Herramientas.cargarImagen("juego/entidades/imagenes/dinoman.png");
		this.x = 100;
		this.y = estructura.alturaPiso(4) - 32;
		this.factorDeDesplazamiento = 4;
		this.velocidad = 0;
		this.gravedad = 0;
		this.alto = img.getHeight(e);
		this.ancho = img.getWidth(e);
	}

	public void dibujar(Entorno e, Piso estructura) {
		if (estructura.numeroPiso(this.y) % 2 == 0) {
			img = Herramientas.cargarImagen("juego/entidades/imagenes/dinoman.png");
		} else {
			img = Herramientas.cargarImagen("juego/entidades/imagenes/dinoman.png");
		}

		e.dibujarImagen(img, x, y, 0, 0.15);
	}

	public void mover(Entorno e, Piso estructura) {
		if (estructura.hayBloques(x, y + 32)) {
			gravedad = 0;
			velocidad = 0;
			if (estructura.numeroPiso(this.y) % 2 == 0 && x < e.ancho() - 50) {
				x += factorDeDesplazamiento;
			}
			if (estructura.numeroPiso(this.y) % 2 != 0) {
				x += -factorDeDesplazamiento;
			}
		} else {
			int piso = estructura.numeroPiso(y + 32);
			if (y < estructura.alturaPiso(piso - 1)) {
				gravedad = 1;
				velocidad += gravedad;
				y += velocidad;
			}
		}
	}

	public boolean llegoAlLimiteDel(Entorno e) {
		return x < 10;
	}

	public boolean superasteLaDistanciaDelPuntoDeAparicion(Entorno e) {
		return x > e.ancho() / 2 + 200;
	}

	/*public RayoLaser dispararRayoLaser(Entorno e, Piso estructura) {
		if (estructura.numeroPiso(this.y) % 2 == 0) {
			return new RayoLaser(this.x, this.y, true);
		} else {
			return new RayoLaser(this.x - 200, this.y, false);
		}
	}*/

	// prueba
	/*public boolean chocasteConRelampago(Piso estructura, Relampago r) {
		return estructura.numeroDePiso(r.getY()) == estructura.numeroPiso(y) && x < r.getX() + r.getAncho() / 2
				&& x > r.getX() - r.getAncho() / 2;
	}*/

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}
	
	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}
}