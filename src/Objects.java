import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Objects 
{
	protected int x,y,t;
	protected int width, height;
	protected int currentFrame,frameSpeed;
	protected String[] spriteNames;
	protected Stage stage;
	protected SpriteCache spriteCache;
	protected boolean markedForRemoval;
	
	public Objects(Stage stage)
	{
	    this.stage = stage;
	    spriteCache = stage.getSpriteCache();
	    currentFrame = 0;
	    frameSpeed = 1;
	    t = 0;
	}
	
	public void paint(Graphics2D g)
	{
	    g.drawImage( spriteCache.getSprite(spriteNames[currentFrame]), x,y, stage );
	}
	
	public void setX(int i)
    {
		x = i;
	}
	
	public void setY(int i) 
	{ 
		y = i;
	}
	
	public void setSpriteNames(String[] names) 
	{
		spriteNames = names;
		height = 0;
		width = 0;
		for (int i = 0; i < names.length; i++ ) 
		{
		BufferedImage image = spriteCache.getSprite(spriteNames[i]);
		height = Math.max(height,image.getHeight());
		width = Math.max(width,image.getWidth());
		}
	}
	
	public int getHeight() 
	{ 
		return height;
	}
	
	public int getWidth() 
	{ 
		return width; 
	}

	public void act() 
	{ 
		t++;
		if(t % frameSpeed == 0)
		{
			t = 0;
		    currentFrame = (currentFrame + 1) % spriteNames.length;	
		}
	}
	
	public void setFrameSpeed(int i)
	{
		frameSpeed = i;
	}
	
	public void remove() 
	{
		markedForRemoval = true;
	}
	
	public boolean isMarkedForRemoval() 
	{
		return markedForRemoval;
	}
	
	public Rectangle getBounds() 
	{
		return new Rectangle(x,y,width,height);
	}
	
	public void collision(Objects o){}
}
