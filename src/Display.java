import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Display extends JPanel implements KeyListener, MouseListener {


    Room room=new Room("Sprites/floor.png",25,20,false,true,false,true);
    Room room2=new Room("Sprites/floor.png",25,20,true,true,true,true);
    CollidableEntity gf=new CollidableEntity(room2,130,120,300,120,"Sprites/wall.png");
    Room room3=new Room("Sprites/floor.png",25,20,true,true,true,true);
    CollidableEntity gff=new CollidableEntity(room3,130,120,300,120,"Sprites/image.png");
    CollidableEntity f=new CollidableEntity(room3,130,120,400,180,"Sprites/image.png");
    CollidableEntity gfff=new CollidableEntity(room3,130,20,500,220,"Sprites/image.png");
    CollidableEntity ff=new CollidableEntity(room3,130,120,700,190,"Sprites/image.png");
    Room room4=new Room("Sprites/floor.png",25,20,true,true,true,true);



    Room[][] x=
            {{room, room2},
                    {room3, room4}};
    Player player=new Player(x,100,100,70,70,"Sprites/image.png",3,500,2,0,0);
    Thread gameLoop=new Thread(new GameLoop(player,this));

    public Display(int width, int height, String title){
        new OverworldItem(room,50,50,400,520,new Item());
        new OverworldItem(room,50,50,480,520,new Item());
        new LivingEntity(room,100,30,500,520,"Sprites/floor.png",3,200);
        new Enemy(room2,50,30,400,320,"Sprites/red.png",3,200,1);
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        gameLoop.start();

    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // always put this
        Graphics2D g2d = (Graphics2D) g;
        player.getRoom().drawRoom(g2d);
        player.drawInventory(g2d);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e.getKeyCode());

    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e.getKeyCode());
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            player.setTargetPos(new Vector2(e.getX(), e.getY()));
            player.attack();
        }else {

            if (player.getInventory()[player.getSelected()] != null)
                player.getInventory()[player.getSelected()].useItem();

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
