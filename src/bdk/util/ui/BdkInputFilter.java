package bdk.util.ui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class BdkInputFilter extends DocumentFilter {

	public static final int ALLOW_INT = 0;
	public static final int ALLOW_DOUBLE = 1;
	public static final int ALLOW_STRING = 2;

	private int filterType;
	private int min;
	private int max;
	private boolean isMinMax;

	public BdkInputFilter(int filterType) {
		this.isMinMax = false;
		this.filterType = filterType;
	}
	
	public BdkInputFilter(int filterType, int min, int max) {
		this.isMinMax = true;
		this.filterType = filterType;
		this.min = min;
		this.max = max;
	}

	/**
	 * The actual bit that tests if the input was valid
	 * 
	 * @param text
	 * @return
	 */
	private boolean test(String text) {

		if (filterType != ALLOW_STRING) {
			try {
				if (filterType == ALLOW_INT) {
					int result = Integer.parseInt(text);
					
					if(isMinMax && !(result >= min && result <= max)) {
						WarningDialog.showWarning("Input out of range. Min: " + min + " , Max: " + max);
					}
					
					return true;
				} else {
					double result = Double.parseDouble(text);
					
					if(isMinMax && !(result >= min && result <= max)) {
						WarningDialog.showWarning("Input out of range. Min: " + min + " , Max: " + max);
					}
					
					return true;
				}

			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return true;
		}
	}

	@Override
	public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
			throws BadLocationException {

		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.insert(offset, string);

		if (test(sb.toString())) {
			super.insertString(fb, offset, string, attr);
		} else {
			WarningDialog.showWarning("Invalid input");
		}
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
			throws BadLocationException {

		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.replace(offset, offset + length, text);

		if (test(sb.toString())) {
			super.replace(fb, offset, length, text, attrs);
		} else {
			WarningDialog.showWarning("Invalid input");
		}

	}

	@Override
	public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.delete(offset, offset + length);

		if (test(sb.toString())) {
			super.remove(fb, offset, length);
		} else {
			WarningDialog.showWarning("Invalid input");
		}

	}
}