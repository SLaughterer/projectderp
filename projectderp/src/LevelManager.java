import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


public class LevelManager {
	private static LinkedList<Level> levels = new LinkedList<Level>();
	private LinkedList<Wall> walls = new LinkedList<Wall>();
	private int width;
	private int height;
	
	public LevelManager() {
		initialize();
	}
	
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
		
		width = Integer.parseInt(rawData.substring(0, 2));
		height = Integer.parseInt(rawData.substring(3, 5));
		rawData = rawData.substring(6);
		
		Level level;
		int[][][] levelLayers = new int[3][width][height];
		
		for (int l = 0; l < 3; l++) {
			String[] rows = rawData.split(";", height + 1);
			
			if (l != 2) {
				rawData = rawData.substring(width * height * 3 + 1);
			}
			
			String[][] cells = new String[width][height];
			
			for (int i = 0; i < width; i++) {
				cells[i] = rows[i].split(",", width);
			}
			
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					levelLayers[l][j][i] = Integer.parseInt(cells[j][i]);
				}
			}
		}
		
		level = new Level(levelLayers);
		levels.add(level);
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public static Level getLevel(int index) {
		return levels.get(index);
	}
	
	public void loadWalls(int index) {
		int[][] wallType = getLevel(index).readWalls();
		int[][] wallData = getLevel(index).readData();
		
		for (int i = 0; i < wallType.length; i++) {
			for (int j = 0; j < wallType[0].length; j++) {
				if (wallType[j][i] != 0) {
					String img = "res/Levels/wall_";
					switch (wallType[j][i]) {
						case 2:
							img += "shot";
							break;
						case 3:
							img += "corner";
							break;
						case 4:
							img += "stop";
							break;
						default:
							img = "res/Levels/wall";
							break;
					}
					
					img += ".png";
					
					Wall wall = new Wall(img, 64, 64, 64, 64);
					wall.setX(i * wall.getWidth());
					wall.setY(j * wall.getHeight());
					
					if (wallData[j][i] != 00) {
						switch (wallData[j][i]) {
							case 1:
								wall.rotation(90);
								break;
							case 2:
								wall.rotation(180);
								break;
							default:
								wall.rotation(270);
								break;
						}
					}
					
					walls.add(wall);
				}
			}
		}
	}
	
	public void draw(Graphics g) {
		for (int i = 0; i < walls.size(); i++) {
			walls.get(i).draw(g);
			//System.out.println("drawing");
		}
	}
}
