
public class Level {
	private int[][] background;
	private int[][] walls;
	private int[][] data;
	
	public Level(int[][][] input) {
		background = input[0];
		walls = input[1];
		data = input[2];
	}
	
	public int[][] readBackground() {
		return background;
	}
	
	public int[][] readWalls() {
		return walls;
	}
	
	public int[][] readData() {
		return data;
	}
}
