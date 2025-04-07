public class OverworldItem extends Entity{
    private Item storedItem;
    public OverworldItem(Room room, int width, int height, int x, int y, Item toAdd) {
        super(room, width, height, x, y, toAdd.getSprite(), false);
        storedItem=toAdd;
        room.getItems().add(this);
        setRoom(room);
    }

    @Override
    public void despawn() {
        super.despawn();
        getRoom().removeItem(this);
    }

    public Item getStoredItem() {
        despawn();
        return storedItem;
    }
}
