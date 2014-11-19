import java.awt.event.KeyEvent;

public class Player extends Objects 
{
	protected static final int playerSpeed = 12;
	public static final int maxHealth = 5;
	private int score;
	public static int health;
	protected int speedX;
	protected int speedY;
	public static boolean left;
	public static boolean right;
	public static int playerReset;
	
    public Player(Stage stage) 
	{
		super(stage);
		setSpriteNames( new String[] {"player.gif"});
		setFrameSpeed(25);
		health = maxHealth;
	}
	
	public void addScore(int x) 
	{ 
		score = score +  x; 
	}
	
	public void act() 
	{
		super.act();
		x = x + speedX;
		y = y + speedY;
		if (x < 0 )
			x = 0;
		if (x > Stage.SZEROKOSC - getWidth())
			x = Stage.SZEROKOSC - getWidth();
		if (y < 0 )
			y = 0;
		if ( y > Stage.WYSOKOSC_GRY - getHeight())
			y = Stage.WYSOKOSC_GRY - getHeight();
		if(playerReset == 1)
			spawn();
	}
	
	public int getVx() { return speedX; }
	public void setVx(int i) {speedX = i; }
	public int getVy() { return speedY; }
	public void setVy(int i) {speedY = i; }
	
	public int getScore() 
	{ 
		return score; 
	}
	
	public void setScore(int x) 
	{ 
		score = x; 
	}
	
	public int getHealth() 
	{ 
		return health; 
	}
	
	public void setHealth(int x) 
	{ 
		health = x; 
	}
	
	protected void updateSpeed() 
	{
		speedX=0;
		if(left && right)
			return;
		if (left) 
			speedX = -playerSpeed;
		if (right) 
			speedX = playerSpeed;
		
	}
	
	public  void spawn()
	{
		x = Stage.SZEROKOSC/2;
		y = Stage.WYSOKOSC_GRY;
		playerReset = 0;
	}
	
	public void keyReleased(KeyEvent e) 
	{
		switch (e.getKeyCode()) 
		{
		case KeyEvent.VK_LEFT : left = false; break;
		case KeyEvent.VK_RIGHT : right = false; break;
		case KeyEvent.VK_ENTER : MojaGra.gameStart = true; break;
		}
		updateSpeed();
	}
	
	public void keyPressed(KeyEvent e) 
	{
		switch (e.getKeyCode()) 
		{
		case KeyEvent.VK_LEFT : left = true; break;
		case KeyEvent.VK_RIGHT : right = true; break;
		case KeyEvent.VK_SPACE : { Ball.ballMove = true; Ball.setMove = 1; } break;
		case KeyEvent.VK_ENTER : MojaGra.gameStart = true;  break;
		case KeyEvent.VK_ESCAPE : System.exit(0);  break;
		}
		updateSpeed();
	}
}
