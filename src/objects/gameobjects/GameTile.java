package objects.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import engine.main.Window;

public class GameTile extends GameObject {

	private boolean isDrawn = false;
	private Image image;
	private int type;
	public boolean testdraw = true;

	public GameTile(float x, float y, ObjectID ID, int type) {
		super(x, y, ID);
		this.type = type;
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		if (!isDrawn) {
			System.out.println("drawn");
			isDrawn = true;
			if (type == 1) {
				try {
					image = ImageIO.read(new File("res/images/grass2.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(image, (int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT, null);
				g.setColor(Color.RED);
				//g.drawRect((int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT);
				image.flush();
			}
			// is dark grass texture ?
			else if (type == 2) {
				try {
					image = ImageIO.read(new File("res/images/grass.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(image, (int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT, null);
				g.setColor(Color.RED);
				//g.drawRect((int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT);
				image.flush();
			}
			// is stone texture ?
			else if (type == 3) {
				try {
					image = ImageIO.read(new File("res/images/stone.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(image, (int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT, null);
				g.setColor(Color.RED);
				//g.drawRect((int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT);
				image.flush();
			}	
			else if (type == 4) {
				
				try {
					image = ImageIO.read(new File("res/images/metal1test.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(image, (int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT, null);
				g.setColor(Color.RED);
				//g.drawRect((int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT);
				image.flush();
			}
			else if (type == 5) {
				
				try {
					image = ImageIO.read(new File("res/images/metal2.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(image, (int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT, null);
				g.setColor(Color.RED);
				//g.drawRect((int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT);
				image.flush();
			}
			else if (type == 6) {
				
				try {
					image = ImageIO.read(new File("res/images/metal3.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(image, (int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT, null);
				g.setColor(Color.RED);
				//g.drawRect((int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT);
				image.flush();
			}
			else if (type == 7) {
				
				try {
					image = ImageIO.read(new File("res/images/metal4.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(image, (int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT, null);
				g.setColor(Color.RED);
				//g.drawRect((int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT);
				image.flush();
			}
			else if (type == 8) {
				
				try {
					image = ImageIO.read(new File("res/images/metal5.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(image, (int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT, null);
				g.setColor(Color.RED);
				//g.drawRect((int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT);
				image.flush();
			}
			else if (type == 9) {
				
				try {
					image = ImageIO.read(new File("res/images/metal6.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(image, (int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT, null);
				g.setColor(Color.RED);
				//g.drawRect((int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT);
				image.flush();
			}
			else if (type == 10) {
				
				try {
					image = ImageIO.read(new File("res/images/metal7.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(image, (int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT, null);
				g.setColor(Color.RED);
				//g.drawRect((int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT);
				image.flush();
			}
			else if (type == 11) {
				
				try {
					image = ImageIO.read(new File("res/images/metal8.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(image, (int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT, null);
				g.setColor(Color.RED);
				//g.drawRect((int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT);
				image.flush();
			}
			else if (type == 12) {
				
				try {
					image = ImageIO.read(new File("res/images/metal9.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(image, (int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT, null);
				g.setColor(Color.RED);
				//g.drawRect((int) this.getX(), (int) this.getY(), Window.TILEWIDTH, Window.TILEHEIGHT);
				image.flush();
			}
		}
		return;
	}

	public void setDrawn(boolean isDrawn) {
		this.isDrawn = isDrawn;
	}
}
