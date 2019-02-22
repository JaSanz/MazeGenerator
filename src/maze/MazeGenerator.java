package maze;

public interface MazeGenerator {

	/**
	 * Establece los atributos principales a los valores iniciales adecuados
	 * @param mode Modo del laberinto a generar
	 * @param xTam Número de filas del laberinto. Debe ser mayor o igual que 3
	 * @param yTam Número de columnas del laberinto. Debe ser mayor o igual que 3
	 * @return Devuelve verdadero si todo se ha inicializado correctamente
	 */
	boolean establishArguments(int mode, int xTam, int yTam);
	
	/**
	 * Genera el tablero con los valores iniciales, es decir, los bordes con 2 y el centro con 1
	 */
	void generateBoard();
	
	/**
	 * Crea una entrada para el laberinto según los parámetros establecidos por el modo
	 * @return Devuelve la posición de la entrada en el tablero
	 */
	int createEntrance();
	
	/**
	 * Crea una salida para el laberinto según los parámetros establecidos por el modo
	 * @return Devuelve la posición de la salida en el tablero
	 */
	int createExit();
	
	/**
	 * Primer paso para generar el laberinto. Establece la entrada, la salida y la posición actual y llama al método homónimo para generar el recorrido
	 * @return Devuelve verdadero si se ha generado correctamente
	 */
	abstract boolean mazeGeneration();
	
}
