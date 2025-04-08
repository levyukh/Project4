import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimatedSprite extends StaticSprite{
    BufferedImage[] animation;
    private int frame=0;
    private final double timePerFrame;
    private double timePassed=0;
    public AnimatedSprite(BufferedImage[] animation) {
        super(animation[0]);
        this.animation=animation;
        timePerFrame=0.084;
    }
    public AnimatedSprite(BufferedImage[] animation,double timePerFrame) {
        super(animation[0]);
        this.animation=animation;
        this.timePerFrame=timePerFrame;
    }
    private void updateFrame(){
        timePassed+=Entity.deltaTime;
        if(timePassed>timePerFrame){
            if(frame<animation.length-1) frame++;
            else frame=0;
            setSprite(animation[frame]);
            timePassed=0;
        }
    }

    @Override
    public void draw(Graphics2D graphic, int x, int y, int width, int height) {
        updateFrame();
        super.draw(graphic, x, y, (int)(animation[frame].getWidth()/(double)animation[0].getWidth()*width), (int)(animation[frame].getHeight()/(double)animation[0].getHeight()*height));
    }
}
