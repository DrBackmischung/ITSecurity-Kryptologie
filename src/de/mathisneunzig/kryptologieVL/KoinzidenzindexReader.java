package de.mathisneunzig.kryptologieVL;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

public class KoinzidenzindexReader {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KoinzidenzindexReader window = new KoinzidenzindexReader();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public KoinzidenzindexReader() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.getContentPane().setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 45, 418, 21);
		frame.getContentPane().add(progressBar);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 75, 418, 179);
		frame.getContentPane().add(textArea);
		
		JButton btnFileSelect = new JButton("Select File");
		btnFileSelect.setBounds(10, 11, 418, 23);
		btnFileSelect.addActionListener(new LoadDataListener(progressBar, textArea));
		frame.getContentPane().add(btnFileSelect);
	}
	public class LoadDataListener implements ActionListener {
		
		private JProgressBar p;
		private JTextArea t;
		   
		    public LoadDataListener(JProgressBar p, JTextArea t) {
			   this.p = p;
			   this.t = t;
		    }

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setAcceptAllFileFilterUsed(false);
				int rueckgabeWert = chooser.showOpenDialog(null);
				
				if(rueckgabeWert == JFileChooser.APPROVE_OPTION){
					File f = new File(chooser.getSelectedFile().getAbsolutePath());
					p.setMaximum(100);
					p.setValue(0);
					if(f.getName().endsWith(".txt")) {
						try {
							BufferedReader br = new BufferedReader(new FileReader(f));
							String s = br.readLine();
							HashMap<Character, Integer> map = new HashMap<>();
							p.setValue(10);
							for(int i = 0; i<s.length(); i++) {
								p.setValue((int) 10 + (i*(85/s.length())));
								char c = s.charAt(i);
								if(map.get(c) != null) {
									int n = map.get(c);
									map.remove(c);
									map.put(c, n+1);
								} else {
									map.put(c, 1);
								}
							}
							
							double k = 0.0;
							
							for(int v : map.values()) {
								double r = (double) v / (double) s.length();
								k += r*r;
							}
							
							t.append("Koinzidenzindex: "+k+"\n");
							
							for (HashMap.Entry<Character, Integer> entry : map.entrySet()) {
							    t.append(entry.getKey()+": "+entry.getValue()+" mal\n");
							}
							
							p.setValue(100);
							System.out.println(map.toString());
							br.close();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} else {
						System.out.println("Nur Text-Dateien!");
					}
				}
			
			}
	 
	    }
	
}


