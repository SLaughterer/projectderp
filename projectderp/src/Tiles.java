/**
 * Tile background layer for the Game Engine.
 *
 * @author Tero Pykälämäki
 * @version 2014.1217
 * @since 1.7
 */
import java.awt.Image;
import java.awt.Graphics;

class Tiles {
    
    /**
     * The amount of rows tiles are set within.
     */
    private int tileRows;
    
    /**
     * The amount of columns tiles are set within.
     */
    private int tileColumns;
    
    /**
     * A single tile's width.
     */
    private int tileWidth;
    
    /**
     * A single tile's height.
     */
    private int tileHeight;
    
    /**
     * Multiple tile indexes in 2d table.
     */
    private int[][] tile;
    
    /**
     * The amount of differing tiles formed from Image.
     */
    private int maxTiles;
    
    /**
     * Image containing tile data.
     */
    private Image image;
    
    /**
     * The width of the image file.
     */
    private int imageWidth;
    
    /**
     * The height of the image file.
     */
    private int imageHeight;
    LevelManager levels = GameCanvas.levels;
    
    /**
     * Constructs the tile layer and initializes it.
     *
     * Initially the layer will be fully filled with the tile in the first 
     * tile index. Tile width multiplied by tile count should result in
     * image width. Also image height and tile height should be the same.
     *
     * @param img Contains the tile's graphical information.
     * @param tileWidth A single tile's width.
     * @param tileHeight A single tile's height.
     * @param imageWidth The width of the full image.
     * @param imageHeight The height of the full image.
     * @param tileRows The amount of rows that tiles will be layered in.
     * @param tileColumns The amount of columns that tiles will be layered in.
     */
    public Tiles(Image img, int tileWidth, int tileHeight, 
                            int imageWidth, int imageHeight, 
                            int tileRows, int tileColumns) {
        image = img;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.tileRows = tileRows;
        this.tileColumns = tileColumns;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        tile = new int[tileRows][tileColumns];
        
        calculateTiles();
    }
    
    /**
     * Calculates the amount of differing tile indexes.
     *
     * The amount is dependent on;
     * index count = image width / tile width * image height / tile height.
     * The result must be an integer value.
     *
     * @throws InvalidFrameException
     */
    private void calculateTiles() {
        if (imageWidth % tileWidth == 0 && imageHeight % tileHeight == 0) {
            maxTiles = (imageWidth / tileWidth) * (imageHeight / tileHeight);
        } else {
            throw new InvalidFrameException(
                    "Image width/height divided with"
                    + " tile width/height must result in an Integer value.");
        }
    }
    
    /**
     * Paints the layer.
     *
     * @param g Graphics object the layer will be drawn on.
     */
    public void draw(Graphics g) {
        int row;
        int col;
        
        for (int i = 0; i < tileRows; i++) {
            for (int n = 0; n < tileColumns; n++) {
                row = 1;
                
                while (tile[i][n] >= (imageWidth / tileWidth * row)) {
                    row++;
                }
                
                row--;
                col = tile[i][n] - (imageWidth / tileWidth * row);
                
                // Destination coordinates 1 & 2 then source coordinates 1 & 2.
                g.drawImage(image, tileWidth * n, tileHeight * i,
                        tileWidth * n + tileWidth, tileHeight * i + tileHeight,
                        tileWidth * col, tileHeight * row,
                        tileWidth * col + tileWidth, 
                        tileHeight * row + tileHeight, null);
            }
        }
    }
    
    /**
     * Determines if a specific type of tile is touching given Sprite.
     *
     * @param sprite The sprite to be tested.
     * @param tileIndex The index of the tile type being tested.
     * @return If given tile type and sprite are touching, true is returned.
     */
    public boolean collidesWith(Sprite sprite, int tileIndex) {
        boolean collides = false;
        int spriteX = sprite.getX();
        int spriteY = sprite.getY();
        double spriteScale = sprite.getScale();
        double spriteWidth = sprite.getWidth() * spriteScale;
        double spriteHeight = sprite.getHeight() * spriteScale;
        
        int startRow = spriteY / tileHeight;
        int startColumn = spriteX / tileWidth;
        int endRow = (int) ((spriteY + spriteHeight) / tileHeight + 0.6);
        int endColumn = (int) ((spriteX + spriteWidth) / tileWidth + 0.6);
        
        for (int row = startRow; row < endRow; row++) {
            for (int column = startColumn; column < endColumn; column++) {
                
                if (tileIndex == tile[row][column]) {
                    collides = true;
                    break;
                }
            }
        }
        
        return collides;
    }
    
    public void printTiles() {
    	for (int i = 0; i < tileRows; i++) {
    		for (int j = 0; j < tileColumns; j++) {
    			System.out.println(tile[j][i]);
    		}
    	}
    }
    
    public void loadBackground(int index) {
    	tile = levels.getLevel(index).readBackground();
    }
    
    /**
     * Fills the layer with a specific tile type in a square area.
     *
     * @param tileIndex Index of the tile type.
     * @param fromRow Starting row index.
     * @param fromColumn Starting column index.
     * @param toRow End row index.
     * @param toColumn End column index.
     */
    public void fillTiles(int tileIndex, int fromRow, int fromColumn, 
            int toRow, int toColumn) {
        for (int row = fromRow; row < toRow; row++) {
            for (int column = fromColumn; column < toColumn; column++) {
                setTile(tileIndex, row, column);
            }
        }
    }
    
    /**
     * Places a single tile of chosen type to a specific coordinate.
     *
     * @param tileIndex Index of the tile type.
     * @param row Horizontal index of tile table.
     * @param column Vertical index of tile table.
     */
    public void setTile(int tileIndex, int row, int column) {
        if (tileIndex < maxTiles && row < tileRows && column < tileColumns) {
            tile[row][column] = tileIndex;
        }
    }
    
    /**
     * Returns the tileIndex in given coordinates.
     *
     * @param x Horizontal index value in tile table.
     * @param y Vertical index value in tile table.
     * @return tile type/index in given coordinates.
     */
    public int getTile(int x, int y) {
        return tile[y][x];
    }
    
    /**
     * Returns the amount of tile types.
     *
     * @return The amount of tile types/indexes.
     */
    public int getMaxTiles() {
        return maxTiles;
    }
    
    /**
     * Returns the amount of rows in this tile layer.
     *
     * @return The amount of rows in the layer.
     */
    public int getRows() {
        return tileRows;
    }
    
    /**
     * Returns the amount of columns in this tile layer.
     *
     * @return The amount of columns in the layer.
     */
    public int getColumns() {
        return tileColumns;
    }
    
    /**
     * Returns the full width of the tile layer.
     *
     * @return The width of the layer in pixels.
     */
    public int getWidth() {
        return tileWidth * tileColumns;
    }
    
    /**
     * Returns the full height of the tile layer.
     *
     * @return The height of the layer in pixels.
     */
    public int getHeight() {
        return tileHeight * tileRows;
    }
}
