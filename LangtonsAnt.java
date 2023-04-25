public class LangtonsAnt {
    public static void main(String[] args) {
        int gridSize = 20;
        boolean[][] grid = new boolean[gridSize][gridSize];
        int antX = gridSize / 2;
        int antY = gridSize / 2;
        int antDirection = 0; // 0 = up, 1 = right, 2 = down, 3 = left

        while (true) {
            // Flip the color of the cell the ant is on
            grid[antX][antY] = !grid[antX][antY];

            // Turn the ant and move it forward
            if (grid[antX][antY]) {
                antDirection = (antDirection + 1) % 4; // Turn right
            } else {
                antDirection = (antDirection + 3) % 4; // Turn left
            }
            switch (antDirection) {
                case 0: // Up
                    antY--;
                    break;
                case 1: // Right
                    antX++;
                    break;
                case 2: // Down
                    antY++;
                    break;
                case 3: // Left
                    antX--;
                    break;
            }

            // Check if the ant has moved off the edge of the grid and expand the grid if necessary
            if (antX < 0 || antX >= gridSize || antY < 0 || antY >= gridSize) {
                gridSize *= 2;
                boolean[][] newGrid = new boolean[gridSize][gridSize];
                int newCenter = gridSize / 2;
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        newGrid[newCenter - grid.length / 2 + i][newCenter - grid[i].length / 2 + j] = grid[i][j];
                    }
                }
                grid = newGrid;
                antX += gridSize / 2 - newCenter;
                antY += gridSize / 2 - newCenter;
            }

            // Display the current state of the grid
            displayGrid(grid);
        }
    }

    public static void displayGrid(boolean[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] ? "#" : ".");
            }
            System.out.println();
        }
        System.out.println();
    }
}
