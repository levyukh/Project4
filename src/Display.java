import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;



public class Display extends JPanel implements KeyListener, MouseListener, ActionListener {


  Room[][] rooms=new Room[10][10];



    Player player;
    Thread gameLoop;
    private BufferedImage titleScreenImage;
    private JButton startButton;

    public void start(){
        boolean[][][] maze = MazeGenerator.maze(rooms.length, rooms[0].length);


        for (int i=0;i<rooms.length;i++) {
            for(int j=0;j<rooms[0].length;j++) {
                rooms[i][j] = new Room("Sprites/floor.png", 40, 32, maze[i][j][0], maze[i][j][1], maze[i][j][2], maze[i][j][3]);
                RoomPresets.randomPreset(rooms[i][j]);
            }

        }
        player=new Player(rooms,100,100,70,70,"Sprites/image.png",3,500,2,0,0);
        gameLoop=new Thread(new GameLoop(player,this));
    }
    public Display(int width, int height, String title){
        start();
        startButton = new JButton("Start Game");
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        frame.setResizable(false);
        try {
            Image icon = ImageIO.read(new File("Sprites/icon.png"));
            frame.setIconImage(icon);
        } catch (IOException e) {
            System.out.println("Icon image not found!");
        }
        try {
            titleScreenImage = ImageIO.read(new File("Sprites/titleScreen.png"));
        } catch (IOException e) {
            System.out.println("Title screen image not found");
        }
        requestFocusInWindow();
        startButton.addActionListener(this);
        gameLoop.start();



    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // always put this
        Graphics2D g2d = (Graphics2D) g;
        if (GameLoop.getGameState() == 0) {
            drawTitleScreen(g2d);
        } else if (GameLoop.getGameState() == 1) {
            player.getRoom().drawRoom(g2d);
            player.drawInventory(g2d);
        }

    }

    public void drawTitleScreen(Graphics2D graphic) {
        graphic.drawImage(titleScreenImage, 0, 0, 1408, 1024, null);
        add(startButton);
        startButton.setLocation(512, 512);
        startButton.setSize(384, 96);
    }

    public void actionPerformed(ActionEvent e) {
        GameLoop.setGameState(1);
        remove(startButton);
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
