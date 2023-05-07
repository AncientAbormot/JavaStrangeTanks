package project.something.game;

import project.something.IO.Input;
import project.something.display.Display;
import project.something.game.bullet.Bullet;
import project.something.game.level.Level;
import project.something.graphics.TextureAtlas;
import project.something.utils.Time;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game implements Runnable {

    public static final int		WIDTH			= 800;
    public static final int		HEIGHT			= 600;
    public static final String	TITLE			= "Танчики | не меню";
    public static final int		CLEAR_COLOR		= 0xff000000;
    public static final int		NUM_BUFFERS		= 3;
    public static final float	UPDATE_RATE		= 60.0f;
    public static final float	UPDATE_INTERVAL	= Time.SECOND / UPDATE_RATE;
    public static final long	IDLE_TIME		= 1;

    public static final String YT_T = "tanks\\yellow_tank.png";
    public static final String RT_T = "tanks\\red_tank.png";
    public static final String GT_T = "tanks\\green_tank.png";
    public static final String WT_T = "tanks\\white_tank.png";
    public static final String B_T = "map\\blocks.png";
    public static final String BUL_T = "bullets\\n_bullets.png";

    private boolean running;
    private Thread gameThread;
    private Graphics2D graphics;
    private Input input;
    private TextureAtlas atlas;
    private Level lvl;
    private Player player;
    private Player player2;
    private Player player3;
    private Player player4;
    private Bullet bullet;
    private int tanks;

    public ArrayList<Player> tankslist = new ArrayList<>();

    public Game(int tanks) {
        this.tanks = tanks;
        running = false;
        Display.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
        graphics = Display.getGraphics();
        input = new Input();
        Display.addInputListener(input);

        TextureAtlas atlastank1 = new TextureAtlas(YT_T);
        TextureAtlas atlastank2 = new TextureAtlas(GT_T);
        TextureAtlas atlastank3 = new TextureAtlas(RT_T);
        TextureAtlas atlastank4 = new TextureAtlas(WT_T);
        TextureAtlas atlasblocks = new TextureAtlas(B_T);

        lvl = new Level(atlasblocks);
        player = new Player(300, 300, 1, 1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,1, atlastank1);
        tankslist.add(player);
        if (tanks > 1) {
            player2 = new Player(300, 150, 1, 1,KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D,2, atlastank2);
            tankslist.add(player2);
        }
        if (tanks > 2) {
            player3 = new Player(150, 150, 1, 1, KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L,3, atlastank3);
            tankslist.add(player3);
        }
        if (tanks > 3) {
            player4 = new Player(150, 300, 1, 1, KeyEvent.VK_NUMPAD8, KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD4, KeyEvent.VK_NUMPAD6,4, atlastank4);
            tankslist.add(player4);
        }
        /*public float getX(){
            return this.x;
        }
        public float getY(){
            return this.y;
        }
        public float getScale(){
            return this.scale;
        }
        public float getSpriteScale(int ID){ //SPRTSCL_IEAE
            return this.SPRITE_SCALE;
        }*/
    }

    public synchronized void start() {

        if (running)
            return;

        running = true;
        gameThread = new Thread(this);
        gameThread.start();

    }

    public synchronized void stop() {

        if (!running)
            return;

        running = false;

        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cleanUp();

    }

    private void update() {
        for(int i =0; i<tankslist.size();i++){
            tankslist.get(i).update(input);
        }
       /* player.update(input);
        if (tanks > 1) {
            player2.update(input);
        }
        if (tanks > 2) {
            player3.update(input);
        }
        if (tanks > 3) {
            player4.update(input);
        }*/
        boolean bull = !(bullet == null);
        if (bull) {
            bullet.update(input);
        }

        lvl.update();
    }

    private void render() {
        Display.clear();
        lvl.render(graphics);
        for(int i =0; i<tankslist.size();i++){
            tankslist.get(i).render(graphics);
        }
       /* player.render(graphics);
        if (tanks > 1) {
            player2.render(graphics);
        }
        if (tanks > 2) {
            player3.render(graphics);
        }
        if (tanks > 3) {
            player4.render(graphics);
        }*/
        Display.swapBuffers();
    }

    public void run() {

        int fps = 0;
        int upd = 0;
        int updl = 0;

        long count = 0;

        float delta = 0;

        long lastTime = Time.get();
        while (running) {
            long now = Time.get();
            long elapsedTime = now - lastTime;
            lastTime = now;

            count += elapsedTime;

            boolean render = false;
            delta += (elapsedTime / UPDATE_INTERVAL);
            while (delta > 1) {
                update();
                upd++;
                delta--;
                if (render) {
                    updl++;
                } else {
                    render = true;
                }
            }

            if (render) {
                render();
                fps++;
            } else {
                try {
                    Thread.sleep(IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (count >= Time.SECOND) {
                Display.setTitle(TITLE + " || Fps: " + fps + " | Upd: " + upd + " | Updl: " + updl + " MRBEAST!");
                upd = 0;
                fps = 0;
                updl = 0;
                count = 0;
            }

        }

    }

        private void cleanUp() {
        Display.destroy();
    }
    public float getScale(int id) {
        for (int i=0;i<tankslist.size();i++){
            if (tankslist.get(i).getID()==id){
                return tankslist.get(i).getScale();
            }
        }
        return -1f;
    }
    public float getX(int id) {
        for (int i=0;i<tankslist.size();i++){
            if (tankslist.get(i).getID()==id){
                return tankslist.get(i).getX();
            }
        }
        return -1f;
    }
    public float getY(int id) {
        for (int i=0;i<tankslist.size();i++){
            if (tankslist.get(i).getID()==id){
                return tankslist.get(i).getY();
            }
        }
        return -1f;
    }
    public float getSPRSCL(int id) {
        for (int i=0;i<tankslist.size();i++){
            if (tankslist.get(i).getID()==id){
                return tankslist.get(i).getSPRSCL();
            }
        }
        return -1f;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }
}