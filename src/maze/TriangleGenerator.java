package maze;

import java.util.ArrayList;

public class TriangleGenerator extends DepthFirstSearch {

	protected TriangleGenerator(int mode, int xTam, int yTam) {
		super(mode, xTam, yTam);
	}

	@Override
	protected int fillBoardWithMovement(Directions d, int posX, int posY) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected ArrayList<Directions> checkMovements(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
