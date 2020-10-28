package example.sliding.boundary;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import example.sliding.controller.MovePieceController;
import example.sliding.controller.ResetController;
import example.sliding.controller.SelectPieceController;
import example.sliding.model.Model;
import example.sliding.model.MoveType;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class SlidingPuzzleApp extends JFrame {

	private JPanel contentPane;
	PuzzlePanel panel;

	Model model;
	
	JButton btnUp, btnDown, btnLeft, btnRight, btnReset;
	JLabel actualMoves;
	
	public PuzzlePanel getPuzzlePanel() { return panel; } 
	public JButton getUpButton() { return btnUp;}
	public JButton getDownButton() { return btnDown;}
	public JButton getLeftButton() { return btnLeft;}
	public JButton getRightButton() { return btnRight;}
	public JButton getResetButton() { return btnReset;}
	public JLabel getActualMovesLabel() {return actualMoves;}
	
	/**
	 * Create the frame.
	 */
	
	
	
	public SlidingPuzzleApp(Model m) {
		super();
		this.model = m;
		setTitle("Sliding Puzzle Application");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 695, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panel = new PuzzlePanel(model);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				new SelectPieceController(model, SlidingPuzzleApp.this).process(me.getPoint());
			}
		});
		
		JLabel nmLabel = new JLabel("Number of Moves:");
		
		actualMoves = new JLabel("" + model.getNumMoves());
		
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ResetController(model, SlidingPuzzleApp.this).reset();
			}
		});
		
		btnUp = new JButton("^");
		btnUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MovePieceController(model, SlidingPuzzleApp.this).move(MoveType.Up);
			}
		});

		btnDown = new JButton("v");
		btnDown.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MovePieceController(model, SlidingPuzzleApp.this).move(MoveType.Down);
			}
		});

		btnLeft = new JButton("<");
		btnLeft.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MovePieceController(model, SlidingPuzzleApp.this).move(MoveType.Left);
			}
		});

		btnRight = new JButton(">");
		btnRight.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MovePieceController(model, SlidingPuzzleApp.this).move(MoveType.Right);
			}
		});
		 
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(nmLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(actualMoves))
								.addComponent(btnReset))
							.addContainerGap(257, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLeft, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(61)
									.addComponent(btnRight, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(9)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnUp)
										.addComponent(btnDown, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))))
							.addGap(135))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(nmLabel)
								.addComponent(actualMoves))
							.addGap(56)
							.addComponent(btnUp)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLeft)
								.addComponent(btnRight))
							.addGap(5)
							.addComponent(btnDown)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnReset)))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		UpdateButtons.enableButtons(this, new ArrayList<MoveType>());
	}
}
