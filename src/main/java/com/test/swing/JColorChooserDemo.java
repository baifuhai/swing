package com.test.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JColorChooserDemo {

	public static void main(String[] args) {
		JFrame jf = new JFrame("JColorChooserDemo");
		jf.setSize(300, 300);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// panel
		JPanel panel = new JPanel();

		// label
		JLabel label = new JLabel();
		label.setPreferredSize(new Dimension(150, 150));
		label.setOpaque(true);
		panel.add(label);

		// button
		JButton button = new JButton("choose color");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(jf, "JColorChooser", null);

				if (color == null) {
					return;
				}

				label.setBackground(color);

				int red = color.getRed();
				int green = color.getGreen();
				int blue = color.getBlue();
				int alpha = color.getAlpha();

				label.setText(String.format("#%02x%02x%02x %d", red, green, blue, alpha));
			}
		});
		panel.add(button);

		jf.setContentPane(panel);
		jf.setVisible(true);
	}

}
