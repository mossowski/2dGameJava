import java.awt.image.ImageObserver;


public interface Stage extends ImageObserver 
{
    public static final int SZEROKOSC = 1440;
    public static final int WYSOKOSC = 900;
    public static final int SZYBKOSC = 20;
    public static final int WYSOKOSC_GRY = 800;
    public SpriteCache getSpriteCache();
    public void addObject(Objects o);
    public Player getPlayer();
    public SoundCache getSoundCache();
}