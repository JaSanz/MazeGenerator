package maze;

import java.util.ArrayList;

public class SquareGenerator extends DepthFirstSearch {
	
	/**
	 * Constructora general
	 * @param mode Modo del laberinto a generar
	 * @param xTam Número de filas del laberinto
	 * @param yTam Número de columnas del laberinto
	 */
	public SquareGenerator(int mode, int xTam, int yTam) {
		super(mode, xTam, yTam);
		/*if(establishArguments(mode, xTam, yTam)) {
			generateBoard();
			this.hasEntrance = false;
			this.hasExit = false;
			this.isCorrectGeneration = true;
		}
		else
			this.isCorrectGeneration = false;*/
	}
	
	/**
	 * Ejecuta los movimientos según el modo del laberinto, la dirección escogida y la posición actual sobre la que se va a aplicar la dirección
	 * @param d Dirección del movimiento
	 * @param posX Fila actual sobre la que se va a aplicar la dirección
	 * @param posY Columna actual sobre la que se va a aplicar la dirección
	 * @return Devuelve la nueva posición sobre la que ya se ha aplicado el cambio
	 */
	protected int fillBoardWithMovement(Directions d, int posX, int posY) {
		int newActual = -1;
		
		if(d == Directions.ARRIBA) {
			super.setSquare(posX - 1, posY, 0);
			super.setSquare(posX - 2, posY, 0);
			newActual = (posX - 2) * super.getYTam() + posY;
		}
		else if(d == Directions.ABAJO) {
			super.setSquare(posX + 1, posY, 0);
			super.setSquare(posX + 2, posY, 0);
			newActual = (posX + 2) * super.getYTam() + posY;
		}
		else if(d == Directions.IZQUIERDA) {
			super.setSquare(posX, posY - 1, 0);
			super.setSquare(posX, posY - 2, 0);
			newActual = (posX) * super.getYTam() + posY - 2;
		}
		else if(d == Directions.DERECHA) {
			super.setSquare(posX, posY + 1, 0);
			super.setSquare(posX, posY + 2, 0);
			newActual = (posX) * super.getYTam() + posY + 2;
		}
		else
			newActual = (posX) * super.getYTam() + posY;
		
		return newActual;
	}
	
	/**
	 * Crea una lista con los movimientos posibles
	 * @param x Fila del tablero para buscar el movimiento
	 * @param y Columna del tablero para buscar el movimiento
	 * @return Devuelve una lista con los movimientos posibles
	 */
	protected ArrayList<Directions> checkMovements(int x, int y) {
		ArrayList<Directions> a = new ArrayList<Directions>();
		
		//ARRIBA
		if(x - 3 > -1 &&
				super.getSquare(x-2, y) == 1 && (super.getSquare(x-2, y-1) == 1 || super.getSquare(x-2, y-1) == 2) &&
				(super.getSquare(x-2, y+1) == 1 || super.getSquare(x-2, y+1) == 2))
			a.add(Directions.ARRIBA);
		//ABAJO
		if(x + 3 < super.getXTam() + 1 &&
				super.getSquare(x+2, y) == 1 && (super.getSquare(x+2, y-1) == 1 || super.getSquare(x+2, y-1) == 2) &&
				(super.getSquare(x+2, y+1) == 1 || super.getSquare(x+2, y+1) == 2))
			a.add(Directions.ABAJO);
		//IZQUIERDA
		if(y - 3 > -1 &&
				super.getSquare(x, y-2) == 1 && (super.getSquare(x-1, y-2) == 1 || super.getSquare(x-1, y-2) == 2) &&
				(super.getSquare(x+1, y-2) == 1 || super.getSquare(x+1, y-2) == 2))
			a.add(Directions.IZQUIERDA);
		//DERECHA
		if(y + 3 < super.getYTam() + 1 &&
				super.getSquare(x, y+2) == 1 && (super.getSquare(x-1, y+2) == 1 || super.getSquare(x-1, y+2) == 2) &&
				(super.getSquare(x+1, y+2) == 1 || super.getSquare(x+1, y+2) == 2))
			a.add(Directions.DERECHA);
		;
		
		return a;
	}
	
}
