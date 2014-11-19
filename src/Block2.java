
public class Block2 extends Objects 
{
  private int blockHealth = 3;
  
  public Block2(Stage stage)
  {
    super(stage);
    setSpriteNames( new String[] {"block2.gif","block3.gif"});
    setFrameSpeed(25);
  }
  
  public void collision(Objects o)
  {
	  if (o instanceof Ball )      
	  {
		blockHealth--;
	    if(blockHealth == 0)
	    {
	      remove();
	     // stage.getSoundCache().playSound("explosion.wav");
	      stage.getPlayer().addScore(100);  //duzy bloczek
	    }
	  }
  }
}
