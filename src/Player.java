
import java.util.HashSet;

public class Player extends LivingEntity{
    private HashSet<Integer> keysPressed=new HashSet<Integer>();
    private Room[][] rooms;
    private double dashTime=2;
    private double dashCooldown=0;
    private int dashSpeed=4000;
    private int roomX;
    private int roomY;
    private int xDir=0;
    private int yDir=0;
    private int lastXDir=xDir;
    private int lastYDir=yDir;

    private boolean dash=false;
    private Room room;

    public Player(Room[][] rooms,int w, int h, int x, int y,String spitePath,int hp,int speed,int roomX, int roomY){
        super(rooms[roomY][roomX],w,h,x,y,spitePath,hp,speed);
        this.room=rooms[roomY][roomX];
        room.setPlayer(this);
        this.rooms=rooms;
        this.roomX=roomX;
        this.roomY=roomY;
    }
    public void enterRoom(Room newRoom,String exit){
        room.removePlayer(this);
        room=newRoom;
        room.setPlayer(this);
        setCollidable(room.getCollidableEntities());
        setPosition(room.getExits().get(exit).getPosition());
        room.addDrawable(this);
        room.addCollidable(this);
    }

    public void keyPressed(int keyCode) {

        keysPressed.add(keyCode);
    }
    public void keyReleased(int keyCode){
        keysPressed.remove(keyCode);
        if(keyCode>36&&keyCode<41){
            lastXDir=xDir;
            lastYDir=yDir;
        }
    }
    private void checkExitCollision(){
        if(room.getExits().get("Bottom Exit")!=null&&willCollide(room.getExits().get("Bottom Exit"))&&keysPressed.contains(69)) enterRoom(rooms[++roomY][roomX],"Top Exit");
        else if(room.getExits().get("Top Exit")!=null&&willCollide(room.getExits().get("Top Exit"))&&keysPressed.contains(69)) enterRoom(rooms[--roomY][roomX],"Bottom Exit");
        else if(room.getExits().get("Right Exit")!=null&&willCollide(room.getExits().get("Right Exit"))&&keysPressed.contains(69)) enterRoom(rooms[roomY][++roomX],"Left Exit");
        else if(room.getExits().get("Left Exit")!=null&&willCollide(room.getExits().get("Left Exit"))&&keysPressed.contains(69)) enterRoom(rooms[roomY][--roomX],"Right Exit");
        keysPressed.remove(69);
    }
    @Override
    protected  void movementLogic(){
       if(!dash) {
           getSpeed().setVector(getSpeedStat()*xDir,getSpeedStat()*yDir);
            xDir = (keysPressed.contains(37) ? -1 : 0) + (keysPressed.contains(39) ? 1 : 0);
            yDir = (keysPressed.contains(38) ? -1 : 0) + (keysPressed.contains(40) ? 1 : 0);

           if (dashCooldown > 0) dashCooldown -= deltaTime;
           else if (keysPressed.contains(32)) {
               dash = true;
               getSpeed().setVector(dashSpeed*lastXDir,dashSpeed*lastYDir);
           }

       }else {
        if (dashTime > 0) {
            dashTime -= deltaTime;
        } else {
            getSpeed().setVector(0, 0);
            dash = false;
            dashTime = 0.2;
            dashCooldown = 2;
        }
       }

    }



    @Override
    public void move() {

        checkExitCollision();

        super.move();


    }

    public Room getRoom(){
        return room;
    }
}
