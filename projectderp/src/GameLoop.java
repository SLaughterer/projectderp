/**
 * Primary Game Loop for the Game Engine.
 *
 * @author Tero Pykälämäki
 * @version 2014.1216
 * @since 1.7
 */
class GameLoop implements Runnable {
    
    /**
     * Determines how often the loop is run within 1 second.
     */
    private final int FPS = 30;
    
    /**
     * 
     */
    private GameWindow host;
    
    /**
     * Constructs the game loop object and thread.
     */
    public GameLoop(GameWindow host) {
    	
    	this.host = host;
    	
        Thread thread = new Thread(this);
        thread.start();
    }
    
    /**
     * Contains the main loop of the game engine.
     *
     * Thread is by default infinite and loops slowly unless adjusted.
     */
    public void run() {
        long time = 0;
        long elapsedTime = 0;
        long interval = 0;
        long sleepTime = 0;
        
        // This is the primary game loop.
        while (true) {
            time = System.currentTimeMillis();
            
            // Start loop.
            
            host.repaint();
            
            System.out.println("looping");
            
            // End loop.
            
            elapsedTime = System.currentTimeMillis() - time;
            sleepTime = 0;
            
            interval = (int) ((1000.0 / FPS) - elapsedTime);

            if (interval > 0) {
                sleepTime = interval;
            }
            
            try {
                Thread.sleep(sleepTime);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
