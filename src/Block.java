public class Block extends Objects 
{
  
  public Block(Stage stage)
  {
    super(stage);
    setSpriteNames( new String[] {"block0.gif","block1.gif"});
    setFrameSpeed(25);
  }
  
  public void collision(Objects o)
  {
	  if (o instanceof Ball )      
	  {
	  remove();
	//stage.getSoundCache().playSound("explosion.wav"); //irytujacy dzwiek
	  stage.getPlayer().addScore(30);  // dodanie punktow za bloczek zwykly
	  }
  }
}