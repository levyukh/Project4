import java.util.Arrays;



   import java.util.Arrays;
import java.util.Random;

    public class MazeGenerator {

        static Random rand = new Random();
        static int[] dx = {0, 1, 0, -1}; // Directions: top, right, bottom, left
        static int[] dy = {-1, 0, 1, 0};
        static int[] opposite = {2, 3, 0, 1}; // Opposites: 0<->2, 1<->3

        public static boolean[][][] maze(int width, int height) {
            boolean[][][] rooms = new boolean[width][height][4]; // Each cell has 4 exits
            boolean[][] visited = new boolean[width][height];


            generate(0, 0, rooms, visited, width, height);
            return rooms;
        }

        private static void generate(int x, int y, boolean[][][] rooms, boolean[][] visited, int width, int height) {
            visited[x][y] = true;

            int[] directions = {0, 1, 2, 3}; // 0 = top, 1 = right, 2 = bottom, 3 = left
            shuffle(directions);

            for (int dir : directions) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx >= 0 && ny >= 0 && nx < width && ny < height && !visited[nx][ny]) {

                    rooms[x][y][dir] = true;
                    rooms[nx][ny][opposite[dir]] = true;


                    generate(nx, ny, rooms, visited, width, height);
                }
            }
        }

        private static void shuffle(int[] array) {
            for (int i = array.length - 1; i > 0; i--) {
                int j = rand.nextInt(i + 1);
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }



    }

