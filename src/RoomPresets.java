public class RoomPresets {
    public void RoomPresets() {
        // Constructor not needed for static utility class
    }
    private static void randomEnemy(Room room,int width,int height,int x,int y,int hp,int speed,int attack){
        double rand=Math.random();
        if(rand<.25) new Enemy(room, width, height, x, y, hp, speed, attack);
        else if(rand<.5) new FireEnemy(room, width, height, x, y, hp, speed, attack);
        else if(rand<.75) new SquidEnemy(room, width, height, x, y, hp, speed, attack);
        else  new LeafEnemy(room, width, height, x, y, hp, speed, attack);
    }
    // Preset 1: Central Ambush - Enemies surround the center
    private static void centralAmbush(Room room) {
        randomEnemy(room, 50, 50, 300, 220, 3, 200, 1); // Top-left
        randomEnemy(room, 50, 50, 500, 220, 3, 200, 1); // Top-right
        randomEnemy(room, 50, 50, 300, 420, 3, 200, 1); // Bottom-left
        randomEnemy(room, 50, 50, 500, 420, 3, 200, 1); // Bottom-right
        new OverworldItem(room, 50, 50, 400, 320, new Item()); // Item in center
    }

    // Preset 2: Exit dudes
    private static void exitGuards(Room room) {
        if (room.getExits().containsKey("Top Exit")) {
            randomEnemy(room, 50, 50, 400, 100, 3, 200, 1);
        }
        if (room.getExits().containsKey("Bottom Exit")) {
            randomEnemy(room, 50, 50, 400, 540, 3, 200, 1);
        }
        if (room.getExits().containsKey("Left Exit")) {
            randomEnemy(room, 50, 50, 100, 320,  3, 200, 1);
        }
        if (room.getExits().containsKey("Right Exit")) {
            randomEnemy(room, 50, 50, 700, 320,  3, 200, 1);
        }
    }

    // Preset 3: Paw Patrol
    private static void patrolLine(Room room) {
        new LivingEntity(room, 100, 30, 100, 300, "Sprites/patrol.png", 3, 200);
        new LivingEntity(room, 100, 30, 300, 300, "Sprites/patrol.png", 3, 200);
        new LivingEntity(room, 100, 30, 500, 300, "Sprites/patrol.png", 3, 200);
        new LivingEntity(room, 100, 30, 700, 300, "Sprites/patrol.png", 3, 200);
        randomEnemy(room, 50, 50, 350, 270,  3, 200, 1);
    }

    // Preset 4: It's a trap!
    private static void itemBait(Room room) {
        new OverworldItem(room, 50, 50, 400, 320, new Item());
        randomEnemy(room, 50, 50, 350, 270,  3, 200, 1);
        randomEnemy(room, 50, 50, 450, 270,  3, 200, 1);
        randomEnemy(room, 50, 50, 350, 370,  3, 200, 1);
        randomEnemy(room, 50, 50, 450, 370,  3, 200, 1);
    }

    // Preset 5: THIS IS SPARTA!
    private static void chokePoint(Room room) {
        new CollidableEntity(room, 200, 50, 200, 250, "Sprites/wall.png");
        new CollidableEntity(room, 200, 50, 400, 250, "Sprites/wall.png");
        randomEnemy(room, 50, 50, 300, 300,  3, 200, 1);
        randomEnemy(room, 50, 50, 500, 300,  3, 200, 1);
    }

    // Preset 6: Croner
    private static void cornerTraps(Room room) {
        randomEnemy(room, 50, 50, 50, 50,  3, 200, 1);    // Top-left
        randomEnemy(room, 50, 50, 750, 50,  3, 200, 1);   // Top-right
        randomEnemy(room, 50, 50, 50, 590,  3, 200, 1);   // Bottom-left
        randomEnemy(room, 50, 50, 750, 590,  3, 200, 1);  // Bottom-right
    }

    // Preset 7: Item Maze
    private static void itemMaze(Room room) {
        new CollidableEntity(room, 200, 50, 100, 200, "Sprites/wall.png");
        new CollidableEntity(room, 200, 50, 500, 200, "Sprites/wall.png");
        new CollidableEntity(room, 50, 200, 300, 300, "Sprites/wall.png");
        new CollidableEntity(room, 50, 200, 500, 300, "Sprites/wall.png");
        new OverworldItem(room, 50, 50, 150, 150, new Item());
        new OverworldItem(room, 50, 50, 650, 350, new Item());
    }

    // Preset 8: Gobbos
    private static void enemyHorde(Room room) {
        for (int i = 0; i < 4; i++) {
            int x = 100 + (i % 5) * 100;
            int y = 100 + (i / 5) * 100;
            randomEnemy(room, 30, 30, x, y,  1, 150, 1);
        }
    }

    // Preset 9: Boss Room
    public static void bossRoom(Room room) {
        new Boss(room,200,200,1000,700,100,700,3);
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
             treasureRoom(room);
        }else if(roll<10*chanceForRoom){
            treasureRoom(room);
        }else if(roll<11*chanceForRoom){
            patrolGrid(room);
        }else {
            patrolGrid(room);
        }
    }
}
