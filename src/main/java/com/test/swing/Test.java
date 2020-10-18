package com.test.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

public class Test {

	private JFrame frame;

	private JPanel panelContainer;

	private JTextField textFieldChosenFilePath;
	private JButton buttonOpenFileChooser;

	private JFileChooser fileChooser;
	private File chosenFile;

	private JLabel labelRegex;
	private JTextField textFieldRegex;

	private JLabel labelReplacement;
	private JTextField textFieldReplacement;

	private JButton buttonPreview;

	private JButton buttonExecute;

	private JTextArea textAreaResult;

	public static void main(String[] args) {
		new Test();
	}

	public Test(){
		init();
	}

	public void init() {
		// Panel Container
		panelContainer = new JPanel();
		panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));

		{
			// Panel
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.LEFT));

			// TextField ChosenFilePath
			textFieldChosenFilePath = new JTextField(20);
			textFieldChosenFilePath.setEditable(false);
			panel.add(textFieldChosenFilePath);

			// Button OpenFileChooser
			buttonOpenFileChooser = new JButton("选择目录");
			buttonOpenFileChooser.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					int returnVal = fileChooser.showOpenDialog(panelContainer);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						chosenFile = fileChooser.getSelectedFile();
						textFieldChosenFilePath.setText(chosenFile.getAbsolutePath());
						preview();
					}
				}
			});
			panel.add(buttonOpenFileChooser);

			// File Chooser
			fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.setMultiSelectionEnabled(false);

			// add to panelContainer
			panelContainer.add(panel);
		}

		{
			// Panel
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.LEFT));

			// Label Regex
			labelRegex = new JLabel("正则表达式");
			panel.add(labelRegex);

			// TextField Regex
			textFieldRegex = new JTextField(20);
			textFieldRegex.addKeyListener(new KeyListener(){
				@Override
				public void keyTyped(KeyEvent e) {
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
				}
			});
			panel.add(textFieldRegex);

			// Label Replacement
			labelReplacement = new JLabel("替换字符串");
			panel.add(labelReplacement);

			// TextField Replacement
			textFieldReplacement = new JTextField(15);
			textFieldReplacement.addKeyListener(new KeyListener(){
				@Override
				public void keyTyped(KeyEvent e) {
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
				}
			});
			panel.add(textFieldReplacement);

			// Button Preview
			buttonPreview = new JButton("预览");
			buttonPreview.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					if (chosenFile == null) {
						JOptionPane.showMessageDialog(panelContainer, "请选择目录", "提示", JOptionPane.INFORMATION_MESSAGE);
						return;
					}

					String regex = textFieldRegex.getText();
					if (regex == null || regex.equals("")) {
						JOptionPane.showMessageDialog(panelContainer, "请输入正则表达式", "提示", JOptionPane.INFORMATION_MESSAGE);
						return;
					}

//					int returnVal = JOptionPane.showConfirmDialog(panelContainer, "确定？", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//					if (returnVal == JOptionPane.YES_OPTION) {
//						preview();
//					}
					preview();
				}
			});
			panel.add(buttonPreview);

			// Button Execute
			buttonExecute = new JButton("执行");
			buttonExecute.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					if (chosenFile == null) {
						JOptionPane.showMessageDialog(panelContainer, "请选择目录", "提示", JOptionPane.INFORMATION_MESSAGE);
						return;
					}

					String regex = textFieldRegex.getText();
					if (regex == null || regex.equals("")) {
						JOptionPane.showMessageDialog(panelContainer, "请输入正则表达式", "提示", JOptionPane.INFORMATION_MESSAGE);
						return;
					}

					int returnVal = JOptionPane.showConfirmDialog(panelContainer, "确定？", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (returnVal == JOptionPane.YES_OPTION) {
						execute();
						JOptionPane.showMessageDialog(panelContainer, "执行成功", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			panel.add(buttonExecute);

			// add to panelContainer
			panelContainer.add(panel);
		}

		{
			// TextArea Result
			textAreaResult = new JTextArea(30, 0);
			textAreaResult.setEditable(false);
			JScrollPane scrollPane = new JScrollPane(textAreaResult);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

			// add to panelContainer
			panelContainer.add(scrollPane);
		}

		// Frame
		frame = new JFrame("title");
		frame.setSize(800, 700);
//		frame.setLocation(200, 200);
//		frame.setBounds(400, 100, 800, 700);
		frame.setLocationRelativeTo(null);
		frame.setContentPane(panelContainer);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

		frame.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
//				System.out.println("windowOpened");
			}

			@Override
			public void windowActivated(WindowEvent e) {
//				System.out.println("windowActivated");
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
//				System.out.println("windowDeactivated");
			}

			@Override
			public void windowIconified(WindowEvent e) {
//				System.out.println("windowIconified");
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
//				System.out.println("windowDeiconified");
			}

			@Override
			public void windowClosing(WindowEvent e) {
//				System.out.println("windowClosing");
//				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {
//				System.out.println("windowClosed");
			}
		});
	}

	private void preview() {
		File dir = chosenFile;
		String regex = textFieldRegex.getText();
		String replacement = textFieldReplacement.getText();

		if (dir != null) {
			File[] files = dir.listFiles();
			if (files != null) {
				textAreaResult.setText("");
				StringBuilder sb = new StringBuilder();
				for (File file : files) {
					sb.append(file.getName());
					sb.append(System.lineSeparator());
					if (!"".equals(regex)) {
						sb.append(file.getName().replaceAll(regex, replacement));
						sb.append(System.lineSeparator());
						sb.append(System.lineSeparator());
					}
				}
				textAreaResult.setText(sb.toString());
			}
		}
	}

	private void execute() {
		File dir = chosenFile;
		String regex = textFieldRegex.getText();
		String replacement = textFieldReplacement.getText();

		if (dir != null && !"".equals(regex)) {
			File[] files = dir.listFiles();
			if (files != null) {
				for (File file : files) {
					String newFileName = file.getName().replaceAll(regex, replacement);
					File newFile = new File(dir, newFileName);
					file.renameTo(newFile);
				}
			}
		}
	}

}
