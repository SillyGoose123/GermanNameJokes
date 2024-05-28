import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.Desktop;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;


import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;


public class Buttons extends JFrame {

    public static boolean TTSOff = false;

    JLabel label;
    JPanel panel;

    public final Window w;

    public static boolean TextOff = false;

    public Buttons(Window w) {
        this.w = w;
    }

    public void buttons() {

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
                if (TTSOff) {
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
                if (TextOff) {
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
                    desktop.browse(new URI("https://discord.gg/DKRcNd7Hpn"));
                } catch (Exception exception) {
                    exception.printStackTrace();
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
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                                Main.class.getResourceAsStream("jokes/jokes.txt")
                    )
            );
            boolean dataEnd = true;
            while (dataEnd) {
                String joke;
                try {
                    joke = br.readLine();
                    if (joke != null && !joke.trim().isEmpty()) {
                        String[] split = joke.split(";");
                        if (split.length == 2) {
                            panel.add(new PlaySoundButton(split[0], split[1], w, label));
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
        } catch (Exception e1) {
            label.setText(getStatusText("Game data destroyed! Reinstall or restart, you can also conntact me when reinstall fails. Click in credits on SillyCode Discord to conntact me."));
            e1.printStackTrace();
        }


    }


    public static String getStatusText(String text) {
        return "<html><h1>&nbsp;" + text + "</h1></html>";
    }
}
