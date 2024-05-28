package juego;

import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import juego.entidades.Piso;
import juego.entidades.Tiranosaurio;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;	
	public Image fondo;
	public Piso pisos;
	public Tiranosaurio[] tiranosaurios;
	public int tiranosauriosPantalla;

	

	Juego() {
		Random rand = new Random();
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, " Super Elizabeth Sis, Volcano Edition - Grupo ... - v1", 800, 600);
		this.fondo  = Herramientas.cargarImagen("juego/entidades/imagenes/fondo.png");
		this.pisos = new Piso(entorno,4);
		this.tiranosaurios = new Tiranosaurio[3];
		
		this.entorno.iniciar();
	}

	
	public void tick() {
		entorno.dibujarImagen(fondo,entorno.ancho()/2,entorno.alto()/2,0,1.0);
		pisos.dibujar(entorno);
		
		
		tiranosauriosPantalla= 0;
		
		for (Tiranosaurio t : tiranosaurios) {
			if (t != null) {
				t.mover(entorno, pisos);
				t.dibujar(entorno, pisos);
				tiranosauriosPantalla++;
			}
		}
		if (tiranosauriosPantalla < 6) {
			if (tiranosauriosPantalla == 0) {
				tiranosaurios[tiranosauriosPantalla] = new Tiranosaurio(entorno, pisos);
			}
		}
		if (tiranosauriosPantalla > 0 && tiranosauriosPantalla < 6
				&& tiranosaurios[tiranosauriosPantalla - 1].superasteLaDistanciaDelPuntoDeAparicion(entorno)) {
			tiranosaurios[tiranosauriosPantalla] = new Tiranosaurio(entorno, pisos);
		}

		
		
		
		
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
