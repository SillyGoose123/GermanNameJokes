import java.awt.BorderLayout;
import java.awt.Container;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Window extends JFrame {

    public Window() {
        // GUI
        setTitle("GermanNameJokes");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // layout
        Container pane = getContentPane();
        BorderLayout BL = new BorderLayout();
        pane.setLayout(BL);
        new Buttons(this).buttons();

        // Humor popup
        final ImageIcon icon = new ImageIcon("sources/icons/attention.png");
        int result = JOptionPane.showInternalConfirmDialog(
                pane,
                "Dieses Programm enthält Humor, der Niemanden diskriminieren soll",
                "Disclaimer",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                icon
        );
        if (result == JOptionPane.CANCEL_OPTION) {
            System.exit(0);
        }

    }

}
