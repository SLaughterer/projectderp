import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

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
	
	/**
	 * All the bullets are stored and manipulated from here.
	 *
	 * @author Mikko Jokinen
	 * @version 2015.0217
	 * @since 1.7
	 */
	private static class BulletManager {
		
	}
	
	private LinkedList<GunData> armory = new LinkedList<GunData>();
	private LinkedList<Gun> guns = new LinkedList<Gun>();
	
	public String open(String fileName) {
        String text = "";
        
        try (BufferedReader inputStream = new BufferedReader(new FileReader(fileName))) {
            String line;
            
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
		String rawData = open(".\\res\\Guns.txt");
		String[] rawGuns = rawData.split(";");
		
		for(int i = 0; i < rawGuns.length; i++) {
			String[] rawGun = rawGuns[i].split(",");
			GunData gunData = new GunData(rawGun);
			armory.add(gunData);
		}

	}
}
