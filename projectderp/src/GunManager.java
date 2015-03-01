import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Guns in the game are here.
 * 
 * Handles loading the guns when the game starts, giving guns to characters
 * and looping their updates and bullets.
 *
 * @author Mikko Jokinen
 * @version 2015.0217
 * @since 1.7
 */
public class GunManager {
	public static LinkedList<GunData> armory = new LinkedList<GunData>();
	private static LinkedList<Gun> guns = new LinkedList<Gun>();
	
	public GunManager() {
		initialize();
	}
	
	public String open(String fileName) {
        String text = "";
        
        try (BufferedReader inputStream = new BufferedReader(new FileReader(fileName))) {
            String line;
            
            inputStream.readLine();
            inputStream.readLine();
            //Skips the first 2 lines.
            
            while ((line = inputStream.readLine()) != null) {
                text += line;
                text += ";";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return text;
	}

	public void initialize() {
		String rawData = open("res/Guns.txt");
		String[] rawGuns = rawData.split(";");
		
		for(int i = 0; i < rawGuns.length; i++) {
			String[] rawGun = rawGuns[i].split(",");
			GunData gunData;
			
			try {
				gunData = new GunData(rawGun);
				armory.add(gunData);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public static Gun requestGun(GunData data) {
		Gun gun = new Gun(data);
		guns.add(gun);
		return gun;
	}
	
	public LinkedList<GunData> requestArmory() {
		return armory;
	}
	
	public void drawGuns(Graphics g) {
		for (int i = 0; i < guns.size(); i++) {
			guns.get(i).draw(g);
			guns.get(i).cool();
			//System.out.println("drawing");
		}
	}
}
