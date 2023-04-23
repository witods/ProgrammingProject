package SpaceInvaders;


import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class MainGame extends Canvas {

    private Graphics2D bufferG;
    private Font dialogFont;
    private Player player;
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Projectile> playerBullets = new ArrayList<>();
    private ArrayList<Projectile> enemyBullets = new ArrayList<>();
    private ArrayList<PowerUp> powerUps = new ArrayList<>();
    private int enemiesKilled;
    private int currentWidth;
    private int currentHeight;
    private boolean EMPActive;
    private boolean blackholeActive;
    private Blackhole blackhole;
    private int blackholeCenterX;
    private int blackholeCenterY;
    private Thread blackholeThread;
    private boolean drawString;
    private boolean gamePaused;
    private boolean gameRunning;
    private String enemiesKilledMSG;
    private GameLoop moveBulletsLoop;

    public MainGame() {
        setupCanvas();
        setupGame();
    }

    public void setupCanvas() {
        currentWidth = 960;
        currentHeight = 540;
        this.setSize(currentWidth, currentHeight);
        this.setBackground(new Color(66, 96, 112));
        dialogFont = new Font(Font.DIALOG_INPUT, Font.BOLD, 16);
        this.setFocusable(true);
        this.addKeyListener(new MovementActionListener());
        this.addComponentListener(new ResizeActionListener());
        this.addMouseListener(new MouseActionListener());


    }

    public void setupGame() {

        player = new Player(currentWidth / 15, currentHeight / 16,10);
        int playerStartX = (currentWidth / 2) - (player.getPlayerWidth() / 2);
        int playerStartY = currentHeight - player.getPlayerHeight();
        player.setPlayerXandY(playerStartX, playerStartY);
        player.createHitbox();
        int intersection = (currentWidth / 4) - 15;
        int nextXPos = intersection / 2;
        for (int i = 0; i < 4; i++) {
            obstacles.add(new Obstacle(55, 30, nextXPos, 400));
            nextXPos += intersection;
        }
        enemiesKilledMSG = "Enemies killed: " + enemiesKilled;
        Thread.currentThread().setPriority(10);
        moveBulletsLoop = new GameLoop();
        moveBulletsLoop.setPriority(8);
        gameRunning = true;
        moveBulletsLoop.start();
    }

    public int getCurrentWidth(){return currentWidth;}
    public int getCurrentHeight(){return currentHeight;}
    public Player getPlayer() {return player;}

    public ArrayList<Obstacle> getObstacles() {return obstacles;}

    public ArrayList<Enemy> getEnemies() {return enemies;}

    public ArrayList<Projectile> getPlayerBullets() {return playerBullets;}

    public ArrayList<Projectile> getEnemyBullets() {return enemyBullets;}
    public void createEnemies(){
        enemies.add(new Enemy(-30,35,25,25,5,this));
    }
    public void setEnemiesKilled(int x){this.enemiesKilled+=x;}
    public void setEMPActive(boolean EMPActive) {
        this.EMPActive = EMPActive;
    }

    public void setBlackholeActive(boolean blackholeActive) {
        this.blackholeActive = blackholeActive;
    }
    public void setBlackhole(Blackhole b){
        blackhole = b;
        blackholeThread = new Thread(blackhole);
    }
    public void setBlackholeCenter(int x, int y){
        blackholeCenterX = x;
        blackholeCenterY = y;
    }

    public void update(Graphics g) {

        Image canvasBuffer = createImage(currentWidth, currentHeight);
        bufferG = (Graphics2D) canvasBuffer.getGraphics();
        bufferG.setBackground(new Color(66, 96, 112));
        player.drawPlayer(bufferG);
        for (Obstacle e : obstacles) {
            e.drawObstacle(bufferG);
        }
        if (gamePaused) {
            drawPause(bufferG);
        }else{
            for(Enemy e : enemies) {
                e.drawEnemy(bufferG);
            }
            for (Projectile p : playerBullets) {
                p.drawProjectile(bufferG);
            }
            for(Projectile p : enemyBullets){
                p.drawProjectile(bufferG);
            }
            for(PowerUp powerUp : powerUps){
                powerUp.draw(bufferG);
            }
            if(blackholeActive){
                blackhole.drawBackhole(bufferG);
            }
            bufferG.setColor(new Color(255, 255, 255, 175));
            bufferG.setFont(dialogFont);
            bufferG.drawString("Enemies killed: "+Integer.toString(enemiesKilled),20,20);
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
        g.setFont(dialogFont);
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
        Iterator<Enemy> itrEnemy = enemies.iterator();
        while(itrEnemy.hasNext()){
            Enemy e = itrEnemy.next();
            if(e.getHitbox().intersects(p.getHitbox())){
                playerBullets.remove(p);
                if(e.enemyHit(p.getDamage(),powerUps)) {
                    itrEnemy.remove();
                    enemiesKilled++;
                }
            }
        }
        Iterator<PowerUp> itrPowerUp = powerUps.iterator();
        while (itrPowerUp.hasNext()){
            PowerUp powerUp = itrPowerUp.next();
            if(p.getHitbox().intersects(powerUp.getHitbox())){
                powerUp.activatePower();
                itrPowerUp.remove();
                playerBullets.remove(p);
            }
        }
    }

    public void checkEnemyBulletCollision(Projectile p){
        for(Obstacle o : obstacles){
            if(p.getHitbox().intersects(o.getHitbox())){
                enemyBullets.remove(p);
                o.hitTaken();
            }
        }
        if(p.getHitbox().intersects(player.getPlayerHitbox())){
            player.healthDown(p);
            enemyBullets.remove(p);
            if(player.getPlayerHitpoints()<=0) gameOver();
        }
    }
    public void checkBlackholeCollision(Blackhole b){
        Iterator<Enemy> itr = enemies.iterator();
        while(itr.hasNext()){
            Enemy e = itr.next();
            if(b.getHitbox().intersects(e.getHitbox())) itr.remove();
        }

    }
    public void gameOver(){
        gameRunning = false;
        moveBulletsLoop.stop();
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
        public void mousePressed(MouseEvent e) {
            player.shootProjectile(playerBullets);
        }
    }

    public class GameLoop extends Thread {
        int counter;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                createEnemies();
            }
        };
        @Override
        public void run() {
            timer.schedule(task,0,750);
            while(gameRunning){
                if(!blackholeActive){

                    for(int i = 0;i<playerBullets.size();i++) {
                        if(!playerBullets.get(i).movePlayerProjectile())playerBullets.remove(playerBullets.get(i));
                        else checkPlayerBulletCollision(playerBullets.get(i));
                    }
                    for(int i =0; i<enemies.size();i++){
                        enemies.get(i).moveEnemy(currentWidth,currentHeight,blackholeActive);
                        if(!EMPActive){
                            enemies.get(i).shootProjectile(enemyBullets);
                        }
                    }
                    for(int i = 0; i<enemyBullets.size();i++){
                        if(!enemyBullets.get(i).moveEnemyProjectile(currentHeight))enemyBullets.remove(enemyBullets.get(i));
                        else checkEnemyBulletCollision(enemyBullets.get(i));
                    }

                    try {
                        Thread.sleep(66);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    counter++;
                }else{
                    if(!blackholeThread.isAlive()){
                        blackholeThread.start();
                    }
                    for(int i = 0;i<enemies.size();i++){
                        enemies.get(i).gravityPull(blackholeCenterX,blackholeCenterY);
                        checkBlackholeCollision(blackhole);
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                repaint();
            }
        }
    }
}
