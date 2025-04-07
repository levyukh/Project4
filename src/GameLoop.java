import javax.swing.*;

public class GameLoop extends JPanel implements Runnable{
    private JPanel panel;
    private Player player;
    private boolean running=true;


    public GameLoop(Player player,JPanel panel){
        this.player=player;
        player.setGameLoop(this);
        this.panel=panel;
    }
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double deltaTime;

        while (running) {
            long now = System.nanoTime();
            deltaTime = (now - lastTime) / 1000000000.0;
            lastTime = now;
            Entity.setDeltaTime(deltaTime);
            player.getRoom().roomPhysics();

            panel.repaint();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                System.out.println("frame skipped");
            }
        }
    }
    public void stopRunning(){
        running=false;
    }
}
