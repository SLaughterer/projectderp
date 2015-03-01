import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


public class LevelManager {
	private static LinkedList<Level> levels = new LinkedList<Level>();
	
	public LevelManager() {
		initialize();
	}
	
	public String open(String fileName) {
        String text = "";
        
        try (BufferedReader inputStream = new BufferedReader(new FileReader(fileName))) {
            String line;
            
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
		String rawData = null;
		
		//try {
			rawData = open("res/Levels/Level01.txt");
		/*} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		String[] rows = rawData.split(";");
		String[][] cells = new String[rows.length][];
		Level level;
		
		for (int i = 0; i < rows.length; i++) {
			cells[i] = rows[i].split(",");
		}
		
		int[][] levelData = new int[cells.length][rows.length];
		
		for (int i = 0; i < rows.length; i++) {
			for (int j = 0; j < cells.length; j++) {
				levelData[j][i] = Integer.parseInt(cells[j][i]);
			}
		}
		
		level = new Level(levelData);
		levels.add(level);
	}
	
	public static Level getLevel(int index) {
		return levels.get(index);
	}
}
