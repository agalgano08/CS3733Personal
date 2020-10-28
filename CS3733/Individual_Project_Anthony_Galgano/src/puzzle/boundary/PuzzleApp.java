package puzzle.boundary;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import puzzle.controller.MoveTileController;
import puzzle.controller.ResetController;
import puzzle.controller.SelectTileController;
import puzzle.model.Model;
import puzzle.model.MoveType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;

public class PuzzleApp extends JFrame {

	private JPanel contentPane;
	Model model;
	PuzzlePanel panel;
	
	JButton btnUp, btnLeft, btnDown, btnRight, btnReset;
	JLabel winLoseLabel;
	
	public PuzzlePanel getPuzzlePanel() { return panel; } 
	public JButton getUpButton() { return btnUp;}
	public JButton getDownButton() { return btnDown;}
	public JButton getLeftButton() { return btnLeft;}
	public JButton getRightButton() { return btnRight;}
	public JButton getResetButton() { return btnReset;}
	public JLabel getWinLoseLabel() { return winLoseLabel;}
	

	/**
	 * Create the frame.
	 * @param model 
	 */
	public PuzzleApp(Model model) {
		super();
		this.model = model;
		setTitle("Individual Project Puzzle Application");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 783, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panel = new PuzzlePanel(model);
		
		btnUp = new JButton("^");
		btnUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MoveTileController(model, PuzzleApp.this).merge(MoveType.Up);
			}
		});

		btnDown = new JButton("v");
		btnDown.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MoveTileController(model, PuzzleApp.this).merge(MoveType.Down);
			}
		});


		btnLeft = new JButton("<");
		btnLeft.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MoveTileController(model, PuzzleApp.this).merge(MoveType.Left);
			}
		});

		btnRight = new JButton(">");
		btnRight.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MoveTileController(model, PuzzleApp.this).merge(MoveType.Right);
			}
		});
		JLabel Instructions = new JLabel("Instructions: Right adds tiles, left subtracts, up multiplies, down divides.");
		
		btnReset = new JButton("Reset Puzzle");
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ResetController(model, PuzzleApp.this).reset();
			}
		});
		
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				new SelectTileController(model, PuzzleApp.this).process(me.getPoint());
			}
		});
		
		winLoseLabel = new JLabel("");
		winLoseLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(124)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnLeft, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
									.addGap(43)
									.addComponent(btnRight, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnUp)
									.addGap(42))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnDown, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
									.addGap(40)))
							.addGap(15))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addComponent(btnReset))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(winLoseLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
								.addComponent(Instructions, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(Instructions, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnUp)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(26)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnLeft)
										.addComponent(btnRight))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDown)
							.addGap(75)
							.addComponent(btnReset))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(winLoseLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		UpdateButtons.enableButtons(this, new ArrayList<MoveType>());
	}
}
