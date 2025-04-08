public class RoomPresets {
    public void RoomPresets() {
        // Constructor not needed for static utility class
    }

    // Preset 1: Central Ambush - Enemies surround the center
    private static void centralAmbush(Room room) {
        new Enemy(room, 50, 50, 300, 220, "Sprites/red.png", 3, 200, 1); // Top-left
        new Enemy(room, 50, 50, 500, 220, "Sprites/red.png", 3, 200, 1); // Top-right
        new Enemy(room, 50, 50, 300, 420, "Sprites/red.png", 3, 200, 1); // Bottom-left
        new Enemy(room, 50, 50, 500, 420, "Sprites/red.png", 3, 200, 1); // Bottom-right
        new OverworldItem(room, 50, 50, 400, 320, new Item()); // Item in center
    }

    // Preset 2: Exit dudes
    private static void exitGuards(Room room) {
        if (room.getExits().containsKey("Top Exit")) {
            new Enemy(room, 50, 50, 400, 100, "Sprites/red.png", 3, 200, 1);
        }
        if (room.getExits().containsKey("Bottom Exit")) {
            new Enemy(room, 50, 50, 400, 540, "Sprites/red.png", 3, 200, 1);
        }
        if (room.getExits().containsKey("Left Exit")) {
            new Enemy(room, 50, 50, 100, 320, "Sprites/red.png", 3, 200, 1);
        }
        if (room.getExits().containsKey("Right Exit")) {
            new Enemy(room, 50, 50, 700, 320, "Sprites/red.png", 3, 200, 1);
        }
    }

    // Preset 3: Paw Patrol
    private static void patrolLine(Room room) {
        new LivingEntity(room, 100, 30, 100, 300, "Sprites/patrol.png", 3, 200);
        new LivingEntity(room, 100, 30, 300, 300, "Sprites/patrol.png", 3, 200);
        new LivingEntity(room, 100, 30, 500, 300, "Sprites/patrol.png", 3, 200);
        new LivingEntity(room, 100, 30, 700, 300, "Sprites/patrol.png", 3, 200);
    }

    // Preset 4: It's a trap!
    private static void itemBait(Room room) {
        new OverworldItem(room, 50, 50, 400, 320, new Item());
        new Enemy(room, 50, 50, 350, 270, "Sprites/red.png", 3, 200, 1);
        new Enemy(room, 50, 50, 450, 270, "Sprites/red.png", 3, 200, 1);
        new Enemy(room, 50, 50, 350, 370, "Sprites/red.png", 3, 200, 1);
        new Enemy(room, 50, 50, 450, 370, "Sprites/red.png", 3, 200, 1);
    }

    // Preset 5: THIS IS SPARTA!
    private static void chokePoint(Room room) {
        new CollidableEntity(room, 200, 50, 200, 250, "Sprites/wall.png");
        new CollidableEntity(room, 200, 50, 400, 250, "Sprites/wall.png");
        new Enemy(room, 50, 50, 300, 300, "Sprites/red.png", 3, 200, 1);
        new Enemy(room, 50, 50, 500, 300, "Sprites/red.png", 3, 200, 1);
    }

    // Preset 6: Croner
    private static void cornerTraps(Room room) {
        new Enemy(room, 50, 50, 50, 50, "Sprites/red.png", 3, 200, 1);    // Top-left
        new Enemy(room, 50, 50, 750, 50, "Sprites/red.png", 3, 200, 1);   // Top-right
        new Enemy(room, 50, 50, 50, 590, "Sprites/red.png", 3, 200, 1);   // Bottom-left
        new Enemy(room, 50, 50, 750, 590, "Sprites/red.png", 3, 200, 1);  // Bottom-right
    }

    // Preset 7: Item Maze
    private static void itemMaze(Room room) {
        new CollidableEntity(room, 200, 50, 100, 200, "Sprites/wall.png");
        new CollidableEntity(room, 200, 50, 500, 200, "Sprites/wall.png");
        new CollidableEntity(room, 50, 200, 300, 100, "Sprites/wall.png");
        new CollidableEntity(room, 50, 200, 500, 300, "Sprites/wall.png");
        new OverworldItem(room, 50, 50, 150, 150, new Item());
        new OverworldItem(room, 50, 50, 650, 350, new Item());
    }

    // Preset 8: Gobbos
    private static void enemyHorde(Room room) {
        for (int i = 0; i < 10; i++) {
            int x = 100 + (i % 5) * 100;
            int y = 100 + (i / 5) * 100;
            new Enemy(room, 30, 30, x, y, "Sprites/red.png", 1, 150, 1);
        }
    }

    // Preset 9: Boss Room
    private static void bossRoom(Room room) {
        new Enemy(room, 100, 100, 400, 320, "Sprites/patrol.png", 10, 100, 3);
    }

    // Preset 10: Treasure Room
    private static void treasureRoom(Room room) {
        new OverworldItem(room, 50, 50, 300, 200, new Item());
        new OverworldItem(room, 50, 50, 500, 200, new Item());
        new OverworldItem(room, 50, 50, 300, 400, new Item());
        new OverworldItem(room, 50, 50, 500, 400, new Item());
    }

    // Preset 11: Paw Patrol GO GO GO!
    private static void patrolGrid(Room room) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int x = 200 + i * 200;
                int y = 200 + j * 150;
                new LivingEntity(room, 50, 50, x, y, "Sprites/patrol.png", 3, 200);
            }
        }
    }

    // Preset 12: Empty room. I wonder what's in it?
    private static void emptyRoom(Room room) {
        // Intentionally left empty
    }
    public static void randomPreset(Room room){
        double chanceForRoom=1.0/12;
        double roll=Math.random();
        if(roll<chanceForRoom){
            centralAmbush(room);
        }else if(roll<2*chanceForRoom){
            exitGuards(room);
        }else if(roll<3*chanceForRoom){
            patrolLine(room);
        }else if(roll<4*chanceForRoom){
            itemBait(room);
        }else if(roll<5*chanceForRoom){
            chokePoint(room);
        }else if(roll<6*chanceForRoom){
            cornerTraps(room);
        }else if(roll<7*chanceForRoom){
            itemMaze(room);
        }else if(roll<8*chanceForRoom){
            enemyHorde(room);
        }else if(roll<9*chanceForRoom){
             bossRoom(room);
        }else if(roll<10*chanceForRoom){
            treasureRoom(room);
        }else if(roll<11*chanceForRoom){
            patrolGrid(room);
        }else {
            emptyRoom(room);
        }
    }
}
