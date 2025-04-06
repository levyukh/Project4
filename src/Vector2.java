public class Vector2 {
    private int x;
    private int y;
    public Vector2(int x, int y){
        this.x=x;
        this.y=y;
    }
    public void setVector(int newX, int newY){
        x=newX;
        y=newY;
    }
    public Vector2 addVector(Vector2 vector){
        return new Vector2(x+vector.getX(),y+vector.getY());

    }
    public Vector2 addVector(int x,int y){
        return new Vector2(this.x+x,this.y+y);

    }

    public int distance(Vector2 point){
        int dx=point.getX()-x;
        int dy=point.getY()-y;
        return (int)Math.sqrt((dx*dx)+(dy*dy));
    }
    public Vector2 subtractVector(Vector2 vector){
        return new Vector2(x-vector.getX(),y-vector.getY());
    }

    public Vector2 multiply(double scale){
        return new Vector2((int)(x*scale),(int)(y*scale));
    }
    public Vector2 multiply(Vector2 scale){
        return new Vector2((x*scale.getX()),(y*scale.getY()));
    }
    public Vector2 justDir(){
        int xDir=0;
        int yDir=0;
        if(x!=0) {
            xDir=x/Math.abs(x);
        }
        if(y!=0) {
            yDir=y/Math.abs(y);
        }
        return new Vector2(xDir,yDir);
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void normalize(){
        float diagonalFactor = (float) (1 / Math.sqrt(2));
        x*=diagonalFactor;
        y*=diagonalFactor;
    }

    @Override
    public String toString() {
        return x+" "+y;
    }
}
