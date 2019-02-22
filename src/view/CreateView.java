package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

import maze.SquareGenerator;

public class CreateView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JFrame f;
	private JPanel casilla;
	private JLabel l;
	
	private SquareGenerator a;
	private int[][] board;
	
	public CreateView(SquareGenerator a) {
		this.a = a;
		this.board = a.getBoard();
		this.f = new JFrame("Maze Generator");
		this.casilla = new JPanel(new GridLayout(((SquareGenerator) this.a).getXTam(), ((SquareGenerator) this.a).getYTam()));
		this.l = new JLabel("Rows = " + ((SquareGenerator) this.a).getXTam() + " Cols = " + ((SquareGenerator) this.a).getYTam());
		
		initializeGUI();
	}
	
	private final void initializeGUI() {
		//Añade la etiqueta arriba
		this.f.add(this.l, BorderLayout.PAGE_START);
		
		for (int i = 0; i < ((SquareGenerator) this.a).getXTam(); ++i) {
            for (int j = 0; j < ((SquareGenerator) this.a).getYTam(); ++j) {
            	JPanel b = new JPanel();
            	if(this.board[i][j] == 0)
            		b.setBackground(Color.white);
            	else if(this.board[i][j] == 1)
            		b.setBackground(Color.black);
            	else if(this.board[i][j] == 2)
            		b.setBackground(Color.green);
            	else
            		b.setBackground(Color.red);
                this.casilla.add(b);
            }
        }
		this.f.add(this.casilla);
		
		this.f.setSize(((SquareGenerator) this.a).getYTam()*10, ((SquareGenerator) this.a).getXTam()*10);
		this.f.setVisible(true);
	}
	
}
