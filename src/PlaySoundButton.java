import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class PlaySoundButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final Cursor WAIT = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);

	private static final Cursor DEFAULT = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);

	private final Window w;

	private JLabel statusLabel;

	private String playText;

	public PlaySoundButton(String buttonText, String playText, Window w, JLabel statusLabel) {
		super(buttonText);
		this.statusLabel = statusLabel;
		this.playText = playText;
		this.w = w;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (Buttons.TextOff == false) {
			statusLabel.setText(Buttons.getStatusText(playText));
		}
		if (Buttons.TTSOff == false) {
			play();
		}
	}

	private void play() {
		setEnabled(false);
		w.setCursor(WAIT);

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				String speech = getText();
				ttsLoader ttsLoader = new ttsLoader();
				ttsLoader.load(speech);
				setEnabled(true);
				w.setCursor(DEFAULT);
			}
		});
	}

}
