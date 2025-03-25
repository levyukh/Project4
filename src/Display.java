import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Display extends JPanel implements KeyListener {


    Room room=new Room("Sprites/floor.png",25,20,false,true,false,true);
    CollidableEntity z=new CollidableEntity(room,130,120,300,210,3,"Sprites/wall.png");
    CollidableEntity ce=new CollidableEntity(room,60,180,500,510,3,"Sprites/wall.png");
    Room room2=new Room("Sprites/floor.png",25,20,true,true,true,true);
    CollidableEntity gf=new CollidableEntity(room2,130,120,300,120,3,"Sprites/wall.png");
    Room room3=new Room("Sprites/floor.png",25,20,true,true,true,true);
    CollidableEntity gff=new CollidableEntity(room3,130,120,300,120,3,"Sprites/image.png");
    CollidableEntity f=new CollidableEntity(room3,130,120,400,180,3,"Sprites/image.png");
    CollidableEntity gfff=new CollidableEntity(room3,130,20,500,220,3,"Sprites/image.png");
    CollidableEntity ff=new CollidableEntity(room3,130,120,700,190,3,"Sprites/image.png");
    Room room4=new Room("Sprites/floor.png",25,20,true,true,true,true);

    Room[][] x=
    {{room, room2},
    {room3, room4}};
    Player player=new Player(x,100,100,70,70,3,"Sprites/image.png",0,0);
    Thread gameLoop=new Thread(new GameLoop(player,this));

    public Display(int width, int height, String title){

        ce.setSpeed(new Vector2(0,-100));
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);

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



}
