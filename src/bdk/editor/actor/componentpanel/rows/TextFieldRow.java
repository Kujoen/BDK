package bdk.editor.actor.componentpanel.rows;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.text.NumberFormatter;
import javax.swing.text.PlainDocument;

import bdk.util.ui.BdkFont;
import bdk.util.ui.BdkInputFilter;
import soliture.ui.swingextensions.expandinglist.ExpandableRow;

/**
 * 
 * @author Andreas Farley
 *
 */
public class TextFieldRow extends ExpandableRow {

	public static final int INT_FIELD = 0;
	public static final int STRING_FIELD = 1;
	public static final int ALLOW_DECIMALS = 2;

	private JLabel fieldLabel;
	private JFormattedTextField textField;

	/**
	 * Description of the field to input a value into. Must give the row the Object
	 * so the listener can be applied.
	 * 
	 * @param fieldLabelText
	 */
	public TextFieldRow(String fieldLabelText) {
		fieldLabel = new JLabel(fieldLabelText);
		fieldLabel.setFont(new BdkFont(Font.BOLD, 12));
		
		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumIntegerDigits(0);
		
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setCommitsOnValidEdit(true);

		textField = new JFormattedTextField(formatter);

		PlainDocument doc = (PlainDocument) textField.getDocument();
		doc.setDocumentFilter(new BdkInputFilter(BdkInputFilter.ALLOW_STRING));
		
		addComponentToRow(fieldLabel, 3, 1);
		addComponentToRow(textField, 4, 8);
	}

	// --------------------------------------------------------------------|
	// GETTERS & SETTERS
	// --------------------------------------------------------------------|

	public JFormattedTextField getTextField() {
		return textField;
	}

	public void setTextField(JFormattedTextField textField) {
		this.textField = textField;
	}

}
