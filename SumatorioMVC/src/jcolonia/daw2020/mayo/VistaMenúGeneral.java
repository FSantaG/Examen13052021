package jcolonia.daw2020.mayo;

import java.util.Scanner;

public class VistaMenúGeneral {
	
	private String título;
	private String[] listaOpciones;
	private static Scanner in;
	
	public VistaMenúGeneral(String título, String[] listaOpciones, Scanner scEntrada) {
		this.título=título;
		this.listaOpciones=listaOpciones;
		in=scEntrada;
	}
	
	public void mostrarMenú() {
		mostrarTítulo();
		mostrarOpciones();
	}
	public int elegirOpción() {
		int valorElegido;
		valorElegido = in.nextInt();
		if(valorElegido > listaOpciones.length) {
			System.err.print("Valor no válido. Inserte uno nuevo, por favor.");
		}
		return valorElegido;
	}
	
	public void mostrarMensaje(String mensaje) {
		String mensajeDespedida;
		mensajeDespedida = mensaje;
		System.out.println(mensajeDespedida);
	}
	
	public static int getNúmRespuestas() {
		int númRespuesta;
		númRespuesta = in.nextInt();
		return númRespuesta;
	}
	
	public void mostrarTítulo() {
		System.out.printf("%n%s%n%s%n", título, VistaGeneral.rellenar(título.length(), '-'));
	}
	
	public void mostrarOpciones() {
		System.out.printf("%n  Escriba la opción elegida → %n%n");
		for(int i=0; i<listaOpciones.length; i++) {
			System.out.printf("  (%d) %s%n", i+1, listaOpciones[i]);
		}
		System.out.println("");
	}
}
