import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.Desktop;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;


import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;



public class Buttons extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public static boolean TTSOff = false;
	
	JLabel label;
	JPanel panel;
	
	public final Window w;

	public static  boolean TextOff = false;
	
	public Buttons(Window w) {
		this.w = w;
	}

	public void buttons()  {

//menubar 
		JMenuBar menu = new JMenuBar();
		w.setJMenuBar(menu);
		JMenu Settings = new JMenu("Settings");
		menu.add(Settings);
		JMenu Credits = new JMenu("Credits");
		menu.add(Credits);
// Settings-ttsOn/Off
		JMenuItem tts = new JMenuItem("TTS Off");
		Settings.add(tts);
		tts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				JMenuItem item = (JMenuItem) a.getSource();
				if (TTSOff == true) {
					TTSOff = false;
					item.setText("TTS Off");

				} else {
					TTSOff = true;
					item.setText("TTS On");
				}
			}
		});
//Settings-Text		
		JMenuItem text = new JMenuItem("Text Off");
		Settings.add(text);
		text.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				JMenuItem item = (JMenuItem) a.getSource();
				if (TextOff == true) {
					TextOff = false;
					item.setText("Text Off");

				} else {
					TextOff = true;
					item.setText("Text On");
				}
			}
		});		
//Settings-Version
		JMenuItem version = new JMenuItem("Version 1.0");
		Settings.add(version);
//Credits-SillyGoose
		JMenuItem SillyGoose = new JMenuItem("SillyGoose");
		Credits.add(SillyGoose);
		JMenuItem Discord = new JMenuItem("SillyCode Discord");
		Credits.add(Discord);
		Discord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Desktop desktop = Desktop.getDesktop();
				try {
					URL url = new URL("https://discord.gg/DKRcNd7Hpn");
					desktop.browse(url.toURI());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				}
			}
		});

//panel

		Container pane = w.getContentPane();
		label = new JLabel(getStatusText("Status"));
		label.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		pane.add(label, BorderLayout.SOUTH);
		FlowLayout flow = new FlowLayout(3);
		panel = new JPanel(flow);
		
		
		pane.add(panel, BorderLayout.CENTER);
		

//buttons
		
		try {
			FileReader fr = new FileReader("sources/jokes/jokes.txt");
			BufferedReader br = new BufferedReader(fr);
			boolean dataEnd = true;
			while(dataEnd  == true) {
			String joke;
			try {
				joke = br.readLine();
				if(joke != null && joke.trim().length() > 0) {
					String [] split = joke.split(";");
					if(split.length == 2) {
					panel.add(new PlaySoundButton(split[0], split[1],w, label));
					} else {
						System.out.println("Zeile fehlerhaft: " + joke);
					}
				
				} else {
					dataEnd = false;
					try {
						br.close();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					break;
				}
				}
				
			} catch (IOException e1) {
				dataEnd = false;
				try {
					br.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				break;
			}
			
			
			}
		} catch(FileNotFoundException e1) {
		    label.setText(getStatusText("Game data destroyed! Reinstall or restart, you can also conntact me when reinstall fails. Click in credits on SillyCode Discord to conntact me."));
			e1.printStackTrace();
		}
		
		
}	
			

			public static String getStatusText(String text) {
				return "<html><h1>&nbsp;" + text + "</h1></html>";
			}



	
}
