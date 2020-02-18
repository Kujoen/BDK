package bdk.util.listeners;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * 
 * This convenience class allows you to extend without having to implement all
 * methods of DocumentListener.
 * 
 * @author Kujoen
 *
 */
public class BDKDocumentListener implements DocumentListener {

	@Override
	public void insertUpdate(DocumentEvent e) {
		// NaN
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// NaN
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// NaN
	}

}
