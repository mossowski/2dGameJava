import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.TexturePaint;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class MojaGra extends Canvas implements Stage, KeyListener
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public long usedTime;
	public static BufferStrategy strategia;
	public static SpriteCache spriteCache;
	private ArrayList<Objects> blocks;
	public static Player player;
	private Ball ball;
	private SoundCache soundCache;
	private BufferedImage background, backgroundTile;
	private int backgroundY;
	public static int currentLevel = 1;
	public static boolean gameStart = false;
	
	
	public MojaGra()
	{
		spriteCache = new SpriteCache();
		soundCache = new SoundCache();
		JFrame okno = new JFrame(" PinkPonk ");
		JPanel panel = (JPanel)okno.getContentPane();
		setBounds(0,0,Stage.SZEROKOSC,Stage.WYSOKOSC);
		panel.setPreferredSize(new Dimension(Stage.SZEROKOSC,Stage.WYSOKOSC));
		panel.setLayout(null);
		panel.add(this);
		okno.setBounds(0,0,Stage.SZEROKOSC,Stage.WYSOKOSC);
		okno.setVisible(true);
		okno.addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			System.exit(0);
			}
			});
		okno.setResizable(false);
		createBufferStrategy(2);
		strategia = getBufferStrategy();
		requestFocus();
		addKeyListener(this);
		BufferedImage cursor = spriteCache.createCompatible(10,10,Transparency.BITMASK);
		Toolkit t = Toolkit.getDefaultToolkit();
		Cursor c = t.createCustomCursor(cursor,new Point(5,5),"null");
		setCursor(c);
	    setIgnoreRepaint(true);
	}
	
	public void Menu()
	{
		player = new Player(this);
		while (isVisible() && gameStart == false) 
		{
		  Graphics2D g = (Graphics2D)strategia.getDrawGraphics();
		  BufferedImage menuScreen = spriteCache.getSprite("tlo.gif");
		  g.drawImage( menuScreen,0,0,Stage.SZEROKOSC,Stage.WYSOKOSC,spriteCache);
		  strategia.show();
		}
	}
	
	public SoundCache getSoundCache() 
	{
		return soundCache;
	}
	
	public void initWorld() 
	{
		ball = new Ball(this);
		player = new Player(this);
		backgroundTile = spriteCache.getSprite("mapa.gif");
		background = spriteCache.createCompatible(
		Stage.SZEROKOSC,
		Stage.WYSOKOSC+backgroundTile.getHeight(),
		Transparency.OPAQUE);
		Graphics2D g = (Graphics2D)background.getGraphics();
		g.setPaint( new TexturePaint( backgroundTile,
		new
		Rectangle(0,0,backgroundTile.getWidth(),backgroundTile.getHeight())));
		g.fillRect(0,0,background.getWidth(),background.getHeight());
		backgroundY = backgroundTile.getHeight();
	}
	
	public void level()
	{
		//level 1
		if(currentLevel == 1)
		{
			blocks = new ArrayList<Objects>();
		  for(int j = 1; j <= 2 ; j++)
		    for (int i = 3; i <= 12; i++)
		    {
		      Block b = new Block(this);
		      b.setX( i*35 + 50*i );
		      b.setY( j*20 + 35*j);
		      blocks.add(b);
		    }
		  ball.spawn();
		  player.spawn();
		  currentLevel = 7;
		}
		//level 2
		if(currentLevel == 2)
		{
			blocks = new ArrayList<Objects>();
			for(int j = 1; j <= 2 ; j++)
			    for (int i = 4; i <= 13; i++)
			    {
			      Block b = new Block(this);
			      b.setX( i*35 + 50*i );
			      b.setY( j*20 + 35*j);
			      blocks.add(b);
			    }
			    for (int i = 4; i <= 13; i++)
			    {
			      int j = 3;	
			      Block2 b2 = new Block2(this);
			      b2.setX( i*35 + 50*i );
			      b2.setY( j*20 + 35*j);
			      blocks.add(b2);
			    }
		  ball.spawn();
		  player.spawn();
	      currentLevel = 8;
	    }
		//level 3
		if(currentLevel == 3)
		{
			blocks = new ArrayList<Objects>();
			
			for (int i = 4; i <= 13; i++)
			{
			    int j = 1;
			    Block b = new Block(this);
			    b.setX( i*35 + 50*i );
			    b.setY( j*20 + 35*j);
			    blocks.add(b);
		    }
			for(int j = 2; j <= 3 ; j++)
			  for (int i = 4; i <= 13; i++)
			  {
			    Block2 b2 = new Block2(this);
			    b2.setX( i*35 + 50*i );
			    b2.setY( j*20 + 35*j);
			    blocks.add(b2);
			  }
		  ball.spawn();
		  player.spawn();
	      currentLevel = 9;
	    }
		//level 4
		if(currentLevel == 4)
		{
			blocks = new ArrayList<Objects>();
			for(int j = 1; j <= 3 ; j++)
				  for (int i = 4; i <= 13; i++)
				  {
				    Block2 b2 = new Block2(this);
				    b2.setX( i*35 + 50*i );
				    b2.setY( j*20 + 35*j);
				    blocks.add(b2);
				  }
		  ball.spawn();
		  player.spawn();
	      currentLevel = 10;
	    }
		if(currentLevel == 5)
		{
		   currentLevel = 6;
		}
	}
	
	public void paintWorld() throws IOException 
	{
		Graphics2D g = (Graphics2D)strategia.getDrawGraphics();
		g.drawImage( background,0,0,Stage.SZEROKOSC,Stage.WYSOKOSC,
			0,backgroundY,Stage.SZEROKOSC,backgroundY+Stage.WYSOKOSC,this);
		for (int i = 0; i < blocks.size(); i++) 
		{
		  Objects o = (Objects)blocks.get(i);
		  o.paint(g);
		}
		player.paint(g);
		ball.paint(g);
		Gui.paintStatus(g);
		strategia.show();
	}
	
	public void updateWorld() 
	{
		int i = 0;
		while (i < blocks.size()) 
		{
		  Objects o = (Objects)blocks.get(i);
		  if (o.isMarkedForRemoval()) 
		  {
			  blocks.remove(i);
		  } 
		  else 
		  {
		    o.act();
		    i++;
		  }
		}
		ball.act();
		player.act();
	}
	
	public SpriteCache getSpriteCache() 
	{
		  return spriteCache;
	}
	
	public void game() throws IOException 
	{
		usedTime = 1000;
		initWorld();
		while (isVisible() && player.getHealth() > 0) 
		{
			long startTime = System.currentTimeMillis();
			backgroundY--;
			if (backgroundY < 0)
			    backgroundY = backgroundTile.getHeight();
			
			level();
			updateWorld();
			checkCollisions();
			paintWorld();
			usedTime = System.currentTimeMillis()-startTime;
			do {
				Thread.yield();
			} while (System.currentTimeMillis()-startTime < 15);
			
			if(blocks.size() <= 0)
				currentLevel = currentLevel - 5;
		}
		  Gui.Top3();
	      Graphics2D gr = (Graphics2D)strategia.getDrawGraphics(); 
		  Gui.paintGameOver(gr);
	      Gui.Zapis();
	}
	
	public void keyPressed(KeyEvent e) 
	{
		player.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) 
	{
		player.keyReleased(e);
	}
	
	public void keyTyped(KeyEvent e) {}
	
	public void addObject(Objects o) 
	{
		blocks.add(o);
	}
	
	public Player getPlayer() 
	{ 
		return player;
	}
	
	public void checkCollisions() 
	{
		Rectangle ballBounds = ball.getBounds();
		Rectangle playerBounds = player.getBounds();
		for (int i = 0; i < blocks.size(); i++) 
		{
		  Objects o1 = (Objects)blocks.get(i);
		  Rectangle r1 = o1.getBounds();
		  if (r1.intersects(ballBounds)) 
		  {
		     ball.collision(o1);
		     o1.collision(ball);
		  }
		}
		Rectangle r3 = ball.getBounds();
		 if (r3.intersects(playerBounds))
		 {
		    ball.collision(player);
		 }
	}
	
	public static void main(String[] args) throws IOException
	{
		MojaGra inv = new MojaGra();
		inv.Menu();
		if(gameStart == true)
		inv.game();
	}

}