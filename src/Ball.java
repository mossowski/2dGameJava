
public class Ball extends Objects 
{
	private int speedX;
	private int speedY;
	private int delay = 0;
	private int ballSpeed = 11;
	public static int setMove;
	public static boolean ballMove = false;
	
	public Ball(Stage stage)
	{
	    super(stage);
	    setSpriteNames( new String[] {"ball0.gif","ball1.gif"});
	    setFrameSpeed(15);
	}
      
	public void collision(Objects o)
	{
		if(delay == 0)
		{
		  if (o instanceof Block  || o instanceof Block2 ) //odibcie od bloczkow     
		  {
			speedY = -speedY;
			delay = 3;
		  }
		  if (o instanceof Player)  //odbicie od gracza    
		  {
			speedY = -speedY;
			delay = 3;
			if(speedX == 0)  //pierwszy ruch prosto
			{
				double random;
				random =  Math.random();
				if(random > 0.5)
			      speedX = ballSpeed;
				else
			      speedX = -ballSpeed;
			}
		  }
		}
	}
	
	public void act() 
	{
	   if(ballMove == true && setMove == 2)
	   {
	     super.act();	  
	     y = y - speedY;
	     x = x - speedX;
	     if (x < 1 || x > Stage.SZEROKOSC) //odbicia lewe i prawe 
	     {
	       speedX = -speedX;
	       delay = 3;
	     }
	     if(y < 1) // odbicie gorne
	     {
	       speedY = -speedY;
		   delay = 3;
	     }
		 if(y > Stage.WYSOKOSC_GRY) //odbicie dolne , utrata 1 hp i reset
		 {
		   Player.health -= 1;
		   spawn();
		   ballMove = false;
		   Player.playerReset = 1;
		 }
	     if(delay > 0) 
		   delay--;
	   }
	   else
	   {
	     if(Player.left) //ruch pilki na poacztku gry 
		   x = x - Player.playerSpeed;
	     if(Player.right)  //podazanie za graczem
			   x = x + Player.playerSpeed;
	     if(Player.left && setMove == 1) //strzal pilki w lewo
	     {
			speedX = ballSpeed;
			setMove = 2;
	     }
	     if(Player.right && setMove == 1) // strzal pilki w prawo
	     {
			speedX = -ballSpeed;
			setMove = 2;
	     }
	     else if (setMove == 1) //strzal prosto
	     {
	    	speedX = 0;
	    	setMove = 2;
	     }
	   }
	}
	
	public void spawn()
	{
		setMove = 0;
		speedY = ballSpeed;
		speedX = ballSpeed;
		x = Stage.SZEROKOSC/2 + 65;   // spawn nad graczem
		y = 750;
	}

    public void setVx(int i) 
	{
		speedX = i;
	}
	  
    public void setVy(int i) 
	{
		speedY = i;
	}
}
