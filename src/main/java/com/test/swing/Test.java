package com.test.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

	public Test() {
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
			panel.setBorder(BorderFactory.createTitledBorder("Sex"));

			// RadioButton
			ButtonGroup buttonGroup = new ButtonGroup();

			JRadioButton radioButton = new JRadioButton("Male", true);
			radioButton.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					JRadioButton rb = (JRadioButton) e.getSource();
					if (e.getStateChange() == ItemEvent.SELECTED) {
						System.out.println(rb.getText() + " selected");
					} else if (e.getStateChange() == ItemEvent.DESELECTED) {
						System.out.println(rb.getText() + " deselected");
					}
				}
			});
			buttonGroup.add(radioButton);
			panel.add(radioButton);

			JRadioButton radioButton2 = new JRadioButton("Female", false);
			buttonGroup.add(radioButton2);
			panel.add(radioButton2);

			// CheckBox
			JCheckBox checkBox = new JCheckBox("Bold", true);
			panel.add(checkBox);

			checkBox = new JCheckBox("Italic", false);
			panel.add(checkBox);

			// ComboBox
			JComboBox<String> comboBox = new JComboBox<>();
			comboBox.setEditable(false);
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("JComboBox ActionListener");
				}
			});
			comboBox.addPopupMenuListener(new PopupMenuListener() {
				@Override
				public void popupMenuCanceled(PopupMenuEvent e) {
					System.out.println("下拉菜单取消");
				}

				@Override
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
					System.out.println("下拉菜单合上");
				}

				@Override
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					System.out.println("下拉菜单弹出");
				}
			});
			comboBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						String item = e.getItem().toString();
						System.out.println("selectedItem: " + item);
					} else if (e.getStateChange() == ItemEvent.DESELECTED) {
						String item = e.getItem().toString();
						System.out.println("deselectedItem: " + item);
					}
				}
			});
			comboBox.setEnabled(true);
			comboBox.addItem("java");
			comboBox.addItem("c");
			comboBox.addItem("php");
			comboBox.addItem("phthon");
			panel.add(comboBox);

			// PasswordField
			JPasswordField passwordField = new JPasswordField(10);
			passwordField.setText("123456");
			panel.add(passwordField);

			// PopupMenu
//			JPopupMenu jPopupMenu = new JPopupMenu();
//			jPopupMenu.add(new JMenuItem("mi"));
//			jPopupMenu.setVisible(true);
//			panel.add(jPopupMenu);

			// ProgressBar
			JProgressBar progressBar = new JProgressBar(JProgressBar.VERTICAL, 10, 20);
			progressBar.setOrientation(SwingConstants.HORIZONTAL);
			progressBar.setMinimum(10);
			progressBar.setMaximum(20);
			progressBar.setValue(15);
			panel.add(progressBar);

			// Slider
			JSlider slider = new JSlider(JProgressBar.HORIZONTAL, 10, 20, 15);
			slider.setOrientation(SwingConstants.HORIZONTAL);
			slider.setMinimum(10);
			slider.setMaximum(20);
			slider.setValue(15);
			slider.setMajorTickSpacing(5);
			slider.setMinorTickSpacing(1);
			slider.setPaintTicks(true);
			slider.setPaintLabels(true);
			slider.setPaintTrack(true);
			slider.setInverted(true);
			slider.setSnapToTicks(true);
//			Hashtable<Integer, JComponent> hashTable = new Hashtable<>();
//			hashTable.put(10, new JLabel("Start"));
//			hashTable.put(15, new JLabel("Middle"));
//			hashTable.put(20, new JLabel("End"));
//			slider.setLabelTable(hashTable);
			slider.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					JSlider s = (JSlider) e.getSource();
					System.out.println("slider value: " + s.getValue());
				}
			});
			panel.add(slider);

			// Button
			JButton button = new JButton("button");
			button.setBackground(new Color(0, 173, 232));
			button.setFocusPainted(false);
			panel.add(button);

			// ToggleButton
			JToggleButton toggleButton = new JToggleButton("toggle", true);
			toggleButton.setForeground(null);
			toggleButton.setBackground(null);
			toggleButton.setFocusPainted(false);
			toggleButton.setBorderPainted(false);
			toggleButton.setOpaque(false);
			toggleButton.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("toggle_off.png")));
			toggleButton.setSelectedIcon(new ImageIcon(this.getClass().getClassLoader().getResource("toggle_on.png")));
			toggleButton.setPressedIcon(new ImageIcon(this.getClass().getClassLoader().getResource("toggle_on.png")));
			toggleButton.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					JToggleButton toggleBtn = (JToggleButton) e.getSource();
					System.out.println("toggleButton: " + toggleBtn.isSelected());
				}
			});
			panel.add(toggleButton);

			// Spinner
			SpinnerModel spinnerModel = new SpinnerNumberModel(10, 0, 100, 1);
			JSpinner spinner = new JSpinner(spinnerModel);
			spinner.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					JSpinner s = (JSpinner) e.getSource();
					System.out.println("spinner: " + s.getValue());
				}
			});
			panel.add(spinner);

			// add to panelContainer
			panelContainer.add(panel);
		}

		{
			// Panel
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.LEFT));

			// ToolBar
			JToolBar toolBar = new JToolBar("toolBar");
			{
				JLabel label = new JLabel("alignment");
				panel.add(label);

				JButton leftButton = new JButton("left");
				JButton centerButton = new JButton("center");
				JButton rightButton = new JButton("right");

				JButton[] buttonArray = new JButton[]{leftButton, centerButton, rightButton};
				for (JButton btn : buttonArray) {
					btn.setToolTipText(btn.getText());
					btn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if (e.getSource() == leftButton) {
								label.setHorizontalAlignment(JLabel.LEFT);
								label.setText("alignment: " + leftButton.getText());
							} else if (e.getSource() == centerButton) {
								label.setHorizontalAlignment(JLabel.CENTER);
								label.setText("alignment: " + centerButton.getText());
							} else if (e.getSource() == rightButton) {
								label.setHorizontalAlignment(JLabel.RIGHT);
								label.setText("alignment: " + rightButton.getText());
							}
						}
					});
					toolBar.add(btn);
				}
				toolBar.setFloatable(true);
			}
			panel.add(toolBar);

			// add to panelContainer
			panelContainer.add(panel);
		}

		{
			// Panel
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.LEFT));

			// Tree
			DefaultMutableTreeNode biology = new DefaultMutableTreeNode("生物");
			DefaultMutableTreeNode animal = new DefaultMutableTreeNode("动物");
			DefaultMutableTreeNode plant = new DefaultMutableTreeNode("植物");
			DefaultMutableTreeNode zaolei = new DefaultMutableTreeNode("藻类植物");
			DefaultMutableTreeNode juelei = new DefaultMutableTreeNode("蕨类植物");
			DefaultMutableTreeNode elephant = new DefaultMutableTreeNode("大象");
			DefaultMutableTreeNode monkey = new DefaultMutableTreeNode("猴子");
			plant.add(zaolei);
			plant.add(juelei);
			animal.add(elephant);
			animal.add(monkey);
			biology.add(animal);
			biology.add(plant);

			JTree biologyTree = new JTree(biology);
			biologyTree.setAutoscrolls(true);
			biologyTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

			JScrollPane biologyScrollPane = new JScrollPane(biologyTree);
			biologyScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			biologyScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

			JPanel biologyPanel = new JPanel();
			biologyPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			biologyPanel.add(biologyScrollPane);

			panel.add(biologyPanel);

			// add to panelContainer
			panelContainer.add(panel);
		}

		{
			// Panel
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.LEFT));

			// Table
			Object[][] rowData = {{"a", "b"}, {"c", "d"}};
			Object[] columnNames = {"col1", "col2"};
			DefaultTableModel defaultTableModel = new DefaultTableModel(rowData, columnNames);

			JTable table = new JTable(defaultTableModel);

			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//			table.setPreferredScrollableViewportSize(new Dimension(500, 170));
//			table.setFillsViewportHeight(true);

			for (int i = 0; i < table.getColumnCount(); i++) {
				TableColumn column = table.getColumnModel().getColumn(i);
				column.setPreferredWidth(30);
				column.setMaxWidth(30);
				column.setMinWidth(30);
			}

			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			tableModel.setColumnCount(5);
			tableModel.setRowCount(2);
			tableModel.addColumn("col3");
			tableModel.addRow(new Object[]{"e", "f"});
			tableModel.addRow(new Object[]{"g", "h"});
			tableModel.removeRow(3);
			table.invalidate();

			String cellValue = (String) tableModel.getValueAt(0, 0);
			System.out.println(cellValue);

			JScrollPane scrollPane = new JScrollPane(table);

			panel.add(scrollPane);

			// add to panelContainer
			panelContainer.add(panel);
		}

		{
			// TabbedPane
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			{
				JPanel[] jPanels = new JPanel[5];

				jPanels[0] = new JPanel();
				jPanels[1] = new JPanel();
				jPanels[2] = new JPanel();
				jPanels[3] = new JPanel();
				jPanels[4] = new JPanel();

				jPanels[0].setBackground(Color.RED);
				jPanels[1].setBackground(Color.BLUE);
				jPanels[2].setBackground(Color.GREEN);
				jPanels[3].setBackground(Color.YELLOW);
				jPanels[4].setBackground(Color.CYAN);

				tabbedPane.add("Panel 1", jPanels[0]);
				tabbedPane.add("Panel 2", jPanels[1]);
				tabbedPane.add("Panel 3", jPanels[2]);
				tabbedPane.add("Panel 4", jPanels[3]);
				tabbedPane.add("Panel 5", jPanels[4]);
				tabbedPane.add("Label 6", new JLabel("This is Label 6"));

				tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
				tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
				tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
				tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
				tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);
				tabbedPane.setMnemonicAt(5, KeyEvent.VK_6);

				tabbedPane.setEnabledAt(2, false);

				Font font = new Font("宋体", Font.BOLD, 12);
				tabbedPane.setFont(font);

				tabbedPane.setForeground(Color.RED);
				tabbedPane.setForegroundAt(0, Color.RED);
				tabbedPane.setForegroundAt(1, Color.BLUE);
				tabbedPane.setForegroundAt(2, Color.GREEN);
				tabbedPane.setForegroundAt(3, Color.YELLOW);
				tabbedPane.setForegroundAt(4, Color.CYAN);
				tabbedPane.setForegroundAt(5, null);

				tabbedPane.setBackground(Color.BLACK);
				tabbedPane.setBackgroundAt(0, new Color(120, 120, 120));
				tabbedPane.setBackgroundAt(1, new Color(140, 140, 140));
				tabbedPane.setBackgroundAt(2, new Color(160, 160, 160));
				tabbedPane.setBackgroundAt(3, new Color(180, 180, 180));
				tabbedPane.setBackgroundAt(4, new Color(200, 200, 200));
				tabbedPane.setBackgroundAt(5, new Color(220, 220, 220));

				tabbedPane.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						JTabbedPane tp = (JTabbedPane) e.getSource();
						System.out.println("tabbedPane tab " + (tp.getSelectedIndex() + 1));
					}
				});
			}
			panelContainer.add(tabbedPane);
		}

		// EditorPane
		JEditorPane jEditorPane = new JEditorPane();
		panelContainer.add(jEditorPane);

		// Separator
		JSeparator separator = new JSeparator();
		panelContainer.add(separator);

		// TextPane
		JTextPane textPane = new JTextPane();
		panelContainer.add(textPane);

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
			buttonOpenFileChooser.addActionListener(new ActionListener() {
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

			// FileChooser
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
			textFieldRegex.addKeyListener(new KeyListener() {
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
			textFieldReplacement.addKeyListener(new KeyListener() {
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
			buttonPreview.addActionListener(new ActionListener() {
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
			buttonExecute.addActionListener(new ActionListener() {
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

		// Menu
		{
			JMenuBar menuBar = new JMenuBar();

			{
				JMenu menu = new JMenu("File", false);

				{
					JMenu newMenu = new JMenu("New");
					{
						JMenuItem menuItem;

						menuItem = new JMenuItem("Class");
						menuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								JMenuItem m = (JMenuItem) e.getSource();
								System.out.println("menuItem: " + m.getText());
							}
						});
						newMenu.add(menuItem);

						menuItem = new JMenuItem("Interface");
						newMenu.add(menuItem);
					}
					menu.add(newMenu);
				}

				menu.addSeparator();

				{
					JMenuItem menuItem = new JMenuItem("Disabled");
					menuItem.setEnabled(false);
					menu.add(menuItem);
				}

				menu.addSeparator();

				{
					JMenuItem menuItem = new JCheckBoxMenuItem("Bold");
					menu.add(menuItem);
				}

				{
					JMenuItem menuItem = new JCheckBoxMenuItem("Italic");
					menu.add(menuItem);
				}

				menu.addSeparator();

				{
					ButtonGroup buttonGroup = new ButtonGroup();

					JRadioButtonMenuItem rRadioButtonItem = new JRadioButtonMenuItem("a");
					buttonGroup.add(rRadioButtonItem);
					rRadioButtonItem.setSelected(true);
					menu.add(rRadioButtonItem);

					JRadioButtonMenuItem eRadioButtonItem = new JRadioButtonMenuItem("b");
					buttonGroup.add(eRadioButtonItem);
					menu.add(eRadioButtonItem);
				}

				menuBar.add(menu);
			}

			frame.setJMenuBar(menuBar);
		}

		frame.setVisible(true);
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
