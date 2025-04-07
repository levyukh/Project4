public class Exit extends Entity{

    public Exit(Room room ,String exit){
        super(room,128,128,-500,-500,"Sprites/Exit.png",false);
        room.getCollidableEntities().remove(this);
        switch (exit){
            case "Bottom Exit":getPosition().setVector(576,832);
                break;
            case "Top Exit":getPosition().setVector(576,64);
                break;
            case "Left Exit":getPosition().setVector(64,448);
                break;
            case "Right Exit":getPosition().setVector(1088,448);
        }
        room.getExits().put(exit,this);


    }
}
