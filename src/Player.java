
import java.util.HashSet;

public class Player extends CollidableEntity{
    HashSet<Integer> keysPressed=new HashSet<Integer>();
    Room[][] rooms;
    int roomX;
    int roomY;
    private Room room;
    public Player(Room[][] rooms,int w, int h, int x, int y,int hp,String spitePath,int roomX, int roomY){
        super(rooms[roomY][roomX],w,h,x,y,hp,spitePath);
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
    }

    @Override
    public void move() {
        if(keysPressed.contains(38)) getSpeed().setY(-500);
        else if(keysPressed.contains(40)) getSpeed().setY(500);
        else getSpeed().setY(0);
        if(keysPressed.contains(37)) getSpeed().setX(-500);
        else if(keysPressed.contains(39)) getSpeed().setX(500);
        else getSpeed().setX(0);
        if(room.getExits().get("Bottom Exit")!=null&&willCollide(room.getExits().get("Bottom Exit"))&&keysPressed.contains(32)) enterRoom(rooms[++roomY][roomX],"Top Exit");
        else if(room.getExits().get("Top Exit")!=null&&willCollide(room.getExits().get("Top Exit"))&&keysPressed.contains(32)) enterRoom(rooms[--roomY][roomX],"Bottom Exit");
        else if(room.getExits().get("Right Exit")!=null&&willCollide(room.getExits().get("Right Exit"))&&keysPressed.contains(32)) enterRoom(rooms[roomY][++roomX],"Left Exit");
        else if(room.getExits().get("Left Exit")!=null&&willCollide(room.getExits().get("Left Exit"))&&keysPressed.contains(32)) enterRoom(rooms[roomY][--roomX],"Right Exit");
        keysPressed.remove(32);
        super.move();


    }

    public Room getRoom(){
        return room;
    }
}
