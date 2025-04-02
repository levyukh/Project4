public class Exit extends Entity{

    public Exit(Room room ,String exit){
        super(room,100,100,-500,-500,"Sprites/Exit.png",false);
        room.getCollidableEntities().remove(this);
        switch (exit){
            case "Bottom Exit":getPosition().setVector(400,450);
                break;
            case "Top Exit":getPosition().setVector(400,50);
                break;
            case "Left Exit":getPosition().setVector(50,320);
                break;
            case "Right Exit":getPosition().setVector(650,320);
        }
        room.getExits().put(exit,this);


    }
}
