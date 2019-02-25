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
	}
	
	@Override
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
	
	@Override
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
	
	@Override
	public String toString() {
		String s = "";
		
		for(int i = 0; i < super.getXTam(); ++i) {
			s += System.getProperty("line.separator");
			for(int j = 0; j < super.getYTam(); ++j) {
				if(super.getSquare(i, j) == 0)
					s += "#";
				else if(super.getSquare(i, j) == 1)
					s += " ";
				else if(super.getSquare(i, j) == 2)
					s += "-";
				else
					s += "*";
			}
		}
		
		return s;
	}
	
}
