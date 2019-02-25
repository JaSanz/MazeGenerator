package maze;

import java.util.ArrayList;
import java.util.Random;

public abstract class DepthFirstSearch implements MazeGenerator {

	/**
	 * Tama�o del tablero
	 */
	private int xTam = 0, yTam = 0;
	/**
	 * Modo del laberinto. Hay varios modos:
	 *  El modo 0 es un laberinto normal. Se exige x e y impares
	 * Cualquier valor distinto de los anteriores, es considerado como un error
	 */
	private int mode;
	/**
	 * Tablero donde se crea el laberinto
	 * 0 es un camino
	 * 1 es un muro
	 * 2 es un borde exterior
	 * 3 es la entrada
	 * 4 es la salida
	 */
	private int board[][];
	/**
	 * Indican el n�mero donde est� la entrada y la salida
	 */
	private int entrada = -1, salida = -1;
	
	/**
	 * Indica si se ha inicializado correctamente todo lo anterior
	 */
	private boolean isCorrectGeneration;
	
	/**
	 * Indica si el laberinto tiene entrada o salida (se crean al generar el laberinto, no al generar esta clase)
	 */
	private boolean hasEntrance, hasExit;
	
	/**
	 * Constructora general
	 * @param mode Modo del laberinto a generar
	 * @param xTam N�mero de filas del laberinto
	 * @param yTam N�mero de columnas del laberinto
	 */
	protected DepthFirstSearch(int mode, int xTam, int yTam) {
		if(establishArguments(mode, xTam, yTam)) {
			generateBoard();
			this.hasEntrance = false;
			this.hasExit = false;
			this.isCorrectGeneration = true;
		}
		else
			this.isCorrectGeneration = false;
	}
	
	@Override
	public boolean establishArguments(int mode, int xTam, int yTam) {
		//Comprobamos el tama�o del tablero. Los bordes est�n incluidos, por eso el tam m�nimo es 3
		if(xTam < 3 || yTam < 3)
			return false;
		
		//Establecemos los valores
		this.mode = mode; //Se pone dentro para que no se establezca el modo cuando haya un error
		this.xTam = (xTam % 2 == 0) ? xTam + 1 : xTam; //Se suma 1 para evitar problemas de tama�o
		this.yTam = (yTam % 2 == 0) ? yTam + 1 : yTam; //Se suma 1 para evitar problemas de tama�o
		
		//Creamos el tablero
		this.board = new int[this.xTam][this.yTam];
		
		return true;
	}

	@Override
	public void generateBoard() {
		//Borde superior
		for(int i = 0; i < this.yTam; ++i)
			this.board[0][i] = 2;
		
		//Interior
		for(int i = 1; i < this.xTam - 1; ++i)
			for(int j = 0; j < this.yTam; ++j) {
				//Bordes laterales
				if(j == 0 || j == this.yTam - 1)
					this.board[i][j] = 2;
				//Interior
				else
					this.board[i][j] = 1;
			}
		
		//Borde inferior
		for(int i = 0; i < this.yTam; ++i)
			this.board[this.xTam - 1][i] = 2;
	}

	@Override
	public int createEntrance() {
		Random aleatorio = new Random();
		int entrada = aleatorio.nextInt(this.xTam/2) * 2 + 1; //N�mero entre 0 y xTam/2 - 1 (l�mite superior es exclusivo, por eso no se pone el -1 en la llamada)
		//La casilla de entrada comprende el muro. La de inmediatamente a la derecha sirve como ayuda
		this.board[entrada][0] = 3;
		this.board[entrada][1] = 0;
		this.hasEntrance = true;
		return entrada * this.yTam; //Devolvemos la posici�n sobre el tablero de la entrada
	}

	@Override
	public int createExit() {
		Random aleatorio = new Random();
		int salida = aleatorio.nextInt(this.xTam/2) * 2 + 1; //N�mero entre 0 y xTam/2 - 1 (l�mite superior es exclusivo, por eso no se pone el -1 en la llamada)
		//La casilla de salida es tan solo el muro
		this.board[salida][this.yTam - 1] = 4;
		this.hasExit = true;
		return salida * this.yTam + (this.yTam - 1); //Devolvemos la posici�n sobre el tablero de la salida
	}

	/**
	 * Ejecuta los movimientos seg�n el modo del laberinto, la direcci�n escogida y la posici�n actual sobre la que se va a aplicar la direcci�n
	 * @param d Direcci�n del movimiento
	 * @param posX Fila actual sobre la que se va a aplicar la direcci�n
	 * @param posY Columna actual sobre la que se va a aplicar la direcci�n
	 * @return Devuelve la nueva posici�n sobre la que ya se ha aplicado el cambio
	 */
	protected abstract int fillBoardWithMovement(Directions d, int posX, int posY);

	/**
	 * Crea una lista con los movimientos posibles
	 * @param x Fila del tablero para buscar el movimiento
	 * @param y Columna del tablero para buscar el movimiento
	 * @return Devuelve una lista con los movimientos posibles
	 */
	protected abstract ArrayList<Directions> checkMovements(int x, int y);

	/**
	 * Genera el laberinto usando B�squeda en profundidad y recursi�n hasta completar el tablero
	 * @param xActual Fila sobre la que se va a generar el laberinto
	 * @param yActual Columna sobre la que se va a generar el laberinto
	 * @return Devuelve verdadero si se ha generado correctamente
	 */
	private boolean mazeGeneration(int xActual, int yActual) {
		ArrayList<Directions> movs = new ArrayList<Directions>();
		Random aleatorio = new Random(System.nanoTime());
		
		int chosenMov, newActual;
		
		//Siempre que la casilla actual no conecte con la salida, se comprueban los movimientos
		if(this.board[xActual][yActual + 1] != 4)
			movs = checkMovements(xActual, yActual);
		
		while(!movs.isEmpty()) {
			//Escogemos un movimiento de la lista aleatorio
			chosenMov = aleatorio.nextInt(movs.size());
			//Creamos un fragmento del laberinto seg�n el movimiento escogido y obtenemos una nueva posici�n
			newActual = fillBoardWithMovement(movs.get(chosenMov), xActual, yActual);
			//Llamada recursiva
			mazeGeneration(newActual / this.yTam, newActual % this.yTam);
			//Reevaluamos los movimientos. No es necesario eliminar el usado
			movs = checkMovements(xActual, yActual);
		}
		
		return true;
	}

	@Override
	public boolean mazeGeneration() {
		int actual;
		
		if(!this.hasEntrance)
			this.entrada = createEntrance();
		if(!this.hasExit)
			this.salida = createExit();
		
		if(this.entrada == -1 || this.salida == -1)
			return false;
		
		actual = this.entrada + 1;
		return mazeGeneration(actual / this.yTam, actual % this.yTam);
	}
	
	/**
	 * Comprueba si la clase se ha generado correctamente
	 * @return Verdaddero si todo se ha generado correctamente, falso en caso contrario
	 */
	public boolean checkGeneration() {
		return this.isCorrectGeneration;
	}
	
	/**
	 * Devuelve el n�mero de filas del tablero
	 * @return El n�mero de filas
	 */
	public int getXTam() {
		return this.xTam;
	}
	
	/**
	 * Devuelve el n�mero de columnas del tablero
	 * @return El n�mero de columnas
	 */
	public int getYTam() {
		return this.yTam;
	}
	
	/**
	 * Devuelve el array del tablero completo
	 * @return El array del tablero completo
	 */
	public int[][] getBoard() {
		return this.board;
	}
	
	/**
	 * Devuelve el contenido de la casilla del tablero. Se asume que x e y son v�lidos
	 * @param x Fila
	 * @param y Columna
	 * @return El contenido de la casilla del tablero
	 */
	public int getSquare(int x, int y) {
		return this.board[x][y];
	}
	
	/**
	 * Establece un valor en una casilla del tablero
	 * @param x Fila
	 * @param y Columna
	 * @param square El n�mero a poner en la fila y columna
	 */
	protected void setSquare(int x, int y, int square) {
		this.board[x][y] = square;
	}
	
	/**
	 * Devuelve un string con el tablero
	 */
	public abstract String toString();

}
