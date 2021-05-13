package jcolonia.daw2020.mayo;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Gestión de números «decimales»: recogida y visualización de la suma
 * 
 * @versión 2021.2.1
 * @author <a href="dmartin.jcolonia@gmail.com">David H. Martín</a>
 */
public class ControlSumatorio {
	/**
	 * Texto identificativo de las funciones de la aplicación que aparecerán en el
	 * menú principal.
	 */
	private static final String[] OPCIONES_MENÚ_PRINCIPAL = { "Agregar valor", "Mostrar valores", "Mostrar suma",
			"Restablecer", "Salir"};

	/**
	 * Título de la aplicación. Se mostrará como encabezado del menú principal.
	 */
	private static final String TÍTULO_MENÚ_PRINCIPAL = "Sumatorio";

	/**
	 * Recurso asociado a la entrada estándar de la aplicación. Debe ser un objeto
	 * único a compartir con las diferentes vistas creadas.
	 */
	private Scanner in;
	/**
	 * Almacén de los valores introducidos por el usuario. 
	 */
	private ListaNúmeros conjunto;
	/**
	 * Menú principal. 
	 */
	private VistaMenúGeneral menúPrincipal;

	public ControlSumatorio(Scanner in) {
		this.in = in;
		conjunto = new ListaNúmeros();
	}

	private void buclePrincipal() {
		menúPrincipal = new VistaMenúGeneral(TÍTULO_MENÚ_PRINCIPAL, OPCIONES_MENÚ_PRINCIPAL, in);
		
		int entrada = -1;

		// Bucle general
		do {
			menúPrincipal.mostrarMenú();
			entrada = menúPrincipal.elegirOpción();
			switch (entrada) {

			case 1: // Opción 1: Entrada datos
				cargarSumando();
				break;
			case 2: // Opción 2: Mostrar sumandos
				mostrarSumandos();
				break;
			case 3: // Opción 3: Mostrar suma
				mostrarSuma();
				break;
			case 4: // Opción 4: Reset
				restablecer();
				break;
			case 5: // Salir
				menúPrincipal.mostrarMensaje("¡¡¡A-D-I-O-S!!");
				break;
			default: // Opción no esperada: abortar
				ejecutarGenérico(entrada);
				System.err.println("Error interno de programa - operación pendiente de desarrollo");
				System.exit(1);
			}
			
		} while (entrada != 5);
	}

	private void ejecutarGenérico(int id) {
		String mensaje;
		mensaje = String.format("%n  [%03d] Ha elegido la opción %d: «%s»", VistaMenúGeneral.getNúmRespuestas(), id,
				OPCIONES_MENÚ_PRINCIPAL[id - 1]);
		menúPrincipal.mostrarMensaje(mensaje);
	}
	
	private void cargarSumando(){
		System.out.println("Escriba un valor a añadir en el programa (Si es decimal use comas): ");
		double valorAñadido = in.nextDouble();
		try {
			conjunto.add(valorAñadido);
			System.out.println("Valor añadido con éxito");
		} catch (SumatorioNumberException e) {
			e.printStackTrace();
		}
	}
	
	private void mostrarSumandos() {
		System.out.println("Valores almacenados en la lista:");
		for(Double valor:ListaNúmeros.getLista()) {
			System.out.printf("%f%n", valor);
		}
	}
	
	private void mostrarSuma() {
		System.out.print(conjunto);
	}
	
	private void restablecer() {
		ListaNúmeros conjuntoNuevo;
		conjuntoNuevo = new ListaNúmeros();
		conjunto = conjuntoNuevo;
		System.out.println("Reset realizado con éxito");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ControlSumatorio control = new ControlSumatorio(sc);
		control.buclePrincipal();
		sc.close();
	}
}
