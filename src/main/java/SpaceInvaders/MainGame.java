package SpaceInvaders;


import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class MainGame extends Canvas implements Runnable {

    private Image canvasBuffer;
    private Player player;
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Projectile> playerBullets = new ArrayList<>();
    private ArrayList<Projectile> enemyBullets = new ArrayList<>();

    private MovementActionListener movementAction;
    private int currentWidth;
    private int currentHeight;
    private boolean gamePaused;
    GameLoop moveBulletsLoop;
    private boolean gameRunning;

    public MainGame() {
        setupCanvas();
        setupGame();

    }

    public void setupCanvas() {
        currentWidth = 960;
        currentHeight = 540;
        this.setSize(currentWidth, currentHeight);
        this.setBackground(new Color(66, 96, 112));
        this.setFocusable(true);
        this.addKeyListener(new MovementActionListener());
        this.addComponentListener(new ResizeActionListener());
        this.addMouseListener(new MouseActionListener());
    }

    public void setupGame() {

        player = new Player(currentWidth / 15, currentHeight / 16,5);
        int playerStartX = (currentWidth / 2) - (player.getPlayerWidth() / 2);
        int playerStartY = currentHeight - player.getPlayerHeight();
        player.setPlayerXandY(playerStartX, playerStartY);
        player.createHitbox();
        int intersection = (currentWidth / 4) - 15;
        int nextXPos = intersection / 2;
        for (int i = 0; i < 4; i++) {
            obstacles.add(new Obstacle(55, 35, nextXPos, 400));
            nextXPos += intersection;
        }
        moveBulletsLoop = new GameLoop();

        gameRunning = true;
        moveBulletsLoop.start();
    }
    public void createEnemies(){
        enemies.add(new Enemy(-30,10,25,25,3));
    }
    public void update(Graphics g) {
        Graphics2D bufferG;
        canvasBuffer = createImage(currentWidth, currentHeight);
        bufferG = (Graphics2D) canvasBuffer.getGraphics();
        bufferG.setBackground(new Color(66, 96, 112));
        player.drawPlayer(bufferG);
        for (Obstacle e : obstacles) {
            e.drawObstacle(bufferG);
            bufferG.draw(e.getHitbox());
        }
        for(Enemy e : enemies) {
            e.drawEnemy(bufferG);
            bufferG.draw(e.getHitbox());
        }
        for (Projectile p : playerBullets) {
            p.drawProjectile(bufferG);
            bufferG.draw(p.getHitbox());
        }
        for(Projectile p : enemyBullets){
            p.drawProjectile(bufferG);
            bufferG.draw(p.getHitbox());
        }
        if (gamePaused) {
            drawPause(bufferG);
        }
        g.drawImage(canvasBuffer, 0, 0, this);
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        update(graphics);
    }

    public void drawPause(Graphics2D g) {
        String msg = "Press ENTER...";
        g.setColor(new Color(255, 255, 255, 175));

        Font msgFont = new Font(Font.DIALOG_INPUT, Font.BOLD, 18);
        g.setFont(msgFont);
        g.drawString(msg, 20, 20);

        int pauseWidth = currentWidth / 50;
        int pauseHeight = (int) (currentHeight / 7.5);
        g.fillRect((currentWidth / 2) - (pauseWidth), (currentHeight / 2) - (pauseHeight / 2), pauseWidth, pauseHeight);
        g.fillRect((currentWidth / 2) + (pauseWidth), (currentHeight / 2) - (pauseHeight / 2), pauseWidth, pauseHeight);

    }

    public void playerResizeAndPos(int newWidth, int newHeight, int offset) {

        player.setPlayerSize(newWidth / 15, newHeight / 16);
        int newPlayerX = offset + player.getPlayerX();
        int newPlayerY = newHeight - player.getPlayerHeight();
        player.setPlayerXandY(newPlayerX, newPlayerY);
    }

    public void obstacleResizeAndPos(int newWidth, int newHeight) {

    }

    public void checkPlayerBulletCollision(Projectile p){
        for (Obstacle o: obstacles) {
            if(o.getHitbox().intersects(p.getHitbox()))playerBullets.remove(p);
        }
        Iterator<Enemy> itr = enemies.iterator();
        while(itr.hasNext()){
            Enemy e = itr.next();
            if(e.getHitbox().intersects(p.getHitbox())){
                playerBullets.remove(p);
                if(e.enemyHit(1))itr.remove();
            }
        }
    }

    public void checkEnemyBulletCollision(Projectile p){
        for(Obstacle o : obstacles){
            if(p.getHitbox().intersects(o.getHitbox()))enemyBullets.remove(p);
        }
        if(p.getHitbox().intersects(player.getPlayerHitbox())){
            player.healthDown(p);
            enemyBullets.remove(p);
        }
    }
    @Override
    public void run() {

    }

    public class MovementActionListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE && !gamePaused) gamePaused = true;
            if (!gamePaused) player.movePlayer(e.getKeyCode(), currentWidth);
            if (e.getKeyCode() == KeyEvent.VK_ENTER && gamePaused) gamePaused = false;
        }
    }

    public class ResizeActionListener extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {

            int distanceToLeft = currentWidth - player.getPlayerX();
            currentWidth = e.getComponent().getWidth();
            currentHeight = e.getComponent().getHeight();
            int distanceAfterResize = currentWidth - player.getPlayerX();
            int offSet;
            if ((distanceAfterResize - distanceToLeft) % 2 != 0) {
                offSet = ((distanceAfterResize - distanceToLeft) + 1) / 2;
            } else offSet = (distanceAfterResize - distanceToLeft) / 2;


            playerResizeAndPos(currentWidth, currentHeight, offSet);
            gamePaused = true;
        }
    }

    public class MouseActionListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            player.shotBullet(playerBullets);
        }
    }

    public class GameLoop extends Thread {
        int counter;
        @Override
        public void run() {
            while(gameRunning){
                if(counter==10) {
                    createEnemies();
                    counter = 0;
                }
                for(int i = 0;i<playerBullets.size();i++) {
                    if(!playerBullets.get(i).movePlayerProjectile())playerBullets.remove(playerBullets.get(i));
                    else checkPlayerBulletCollision(playerBullets.get(i));
                }
                for(int i =0; i<enemies.size();i++){
                    enemies.get(i).moveEnemy(currentWidth,currentHeight);
                    enemies.get(i).shootProjectile(enemyBullets);
                }
                for(int i = 0; i<enemyBullets.size();i++){
                    if(!enemyBullets.get(i).moveEnemyProjectile(currentHeight))enemyBullets.remove(enemyBullets.get(i));
                    else checkEnemyBulletCollision(enemyBullets.get(i));
                }
                repaint();
                try {
                    Thread.sleep(66);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            counter++;
            }
        }
    }
}
