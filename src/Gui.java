import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Gui 
{
	
	private static Scanner odczyt;
	private static int one,two,three;
	
    public static void paintInterface(Graphics2D g)  //czarny pasek na dole
	{
		BufferedImage interfejs = MojaGra.spriteCache.getSprite("interface.gif");
		g.drawImage( interfejs,0,Stage.WYSOKOSC_GRY,Stage.SZEROKOSC,Stage.WYSOKOSC,MojaGra.spriteCache);
	}
	
	public static void paintLevel(Graphics2D g) //Level
	{
		g.setFont(new Font("SWTOR Trajan",Font.BOLD,20));
		g.setPaint(Color.white);
		g.drawString("Level " + (MojaGra.currentLevel - 6),1200,Stage.WYSOKOSC_GRY + 45);
	}
	
	public static void paintScore(Graphics2D g) throws IOException  //Score
	{
		g.setFont(new Font("SWTOR Trajan",Font.BOLD,20));
		g.setPaint(Color.white);
		g.drawString("Score ",80,Stage.WYSOKOSC_GRY + 30);
		g.drawString("Best ", 80,Stage.WYSOKOSC_GRY + 60);
		g.setPaint(Color.red);
		g.drawString(MojaGra.player.getScore()+"",180,Stage.WYSOKOSC_GRY + 30);
		g.drawString(Odczyt()+"",180,Stage.WYSOKOSC_GRY + 60);
	}
	
	public static void paintHealth(Graphics2D g) //health
	{
		int position = 350+Player.maxHealth+100;
		for (int i = 1; i <= MojaGra.player.getHealth();i++) 
		{
	      BufferedImage health = MojaGra.spriteCache.getSprite("health.gif");
		  g.drawImage( health,position+i*35,Stage.WYSOKOSC_GRY + 20,MojaGra.spriteCache);
		}
	}
	
	public static void paintStatus(Graphics2D g) throws IOException //Wszystko razem
	{
		paintInterface(g);
		paintScore(g);
		paintHealth(g);
		paintLevel(g);
	}
	
	public static void paintGameOver(Graphics2D gr) //Game over
	{
		BufferedImage scoreScreen = MojaGra.spriteCache.getSprite("score.gif");
		gr.drawImage( scoreScreen,0,0,Stage.SZEROKOSC,Stage.WYSOKOSC,MojaGra.spriteCache);
		Graphics2D g = (Graphics2D)MojaGra.strategia.getDrawGraphics();
		g.setColor(Color.white);
		g.setFont(new Font("SWTOR Trajan",Font.BOLD,56));
		g.drawString("GAME OVER",Stage.SZEROKOSC/2-200,200);
		g.drawString("Your Score  "+MojaGra.player.getScore(),Stage.SZEROKOSC/2-200,300);
		g.drawString("Top 3",Stage.SZEROKOSC/2-200,400);
		g.drawString("1. " + one,Stage.SZEROKOSC/2-200,500);
		g.drawString("2. " + two,Stage.SZEROKOSC/2-200,600);
		g.drawString("3. " + three,Stage.SZEROKOSC/2-200,700);
		MojaGra.strategia.show();
	}
	
	public static void Zapis() throws IOException //wyniki
    {
	    File file = new File("score.txt");
        odczyt = new Scanner(file);
		int firstScore = odczyt.nextInt();
		int secondScore = odczyt.nextInt();
		int thirdScore = odczyt.nextInt();
		int newScore = MojaGra.player.getScore();
		
		if( newScore > firstScore)
		{
			odczyt.close();
			PrintWriter zapis = new PrintWriter("score.txt");
			zapis.println(newScore);
			zapis.println(firstScore);
			zapis.println(secondScore);
			zapis.close();
		}
		
		else if( newScore > secondScore)
		{
			odczyt.close();
			PrintWriter zapis = new PrintWriter("score.txt");
			zapis.println(firstScore);
			zapis.println(newScore);
			zapis.println(secondScore);
			zapis.close();
		}
		
		else if( newScore > thirdScore)
		{
			odczyt.close();
			PrintWriter zapis = new PrintWriter("score.txt");
			zapis.println(firstScore);
			zapis.println(secondScore);
			zapis.println(newScore);
			zapis.close();
		}
	}
    
    public static int Odczyt() throws IOException 
    {
	    File file = new File("score.txt");
        odczyt = new Scanner(file);
		return odczyt.nextInt();
	}
    
    public static void Top3() throws IOException 
    {
	    File file = new File("score.txt");
        odczyt = new Scanner(file);
		one = odczyt.nextInt();
		two = odczyt.nextInt();
		three = odczyt.nextInt();
	}
}
