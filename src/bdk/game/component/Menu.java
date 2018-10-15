package bdk.game.component;

import java.awt.Graphics2D;

/**
 * 
 * @author Andreas Farley
 *
 */
public class Menu extends Component {

	public Menu(String name) {
		super(name);
	}

	@Override
	public void update() {
		checkButtons();
	}

	@Override
	public void render(Graphics2D g) {
		// spriteList.stream().forEach(x -> render(g));
	}

	private void checkButtons() {

	}

}
