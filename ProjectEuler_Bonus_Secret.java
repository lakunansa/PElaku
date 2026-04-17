import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

public class ProjectEuler_Bonus_Secret {

    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        try {
            // Pfade anpassen (Julia nutzt relative Pfade zum Skript)
            String inputPath = "C:\\Users\\Dell\\Downloads\\bonus_secret_statement.png";
            String outputPath = "C:\\Users\\Dell\\Downloads\\bonus_secret_result.png";

            // 3. Parameter aus dem Problem
            long totalSteps = 1_000_000_000_000L; // 10^12
            int modulo = 7;

            
            // 1. Bild laden
            File inputFile = new File(inputPath);
            BufferedImage img = ImageIO.read(inputFile);
            int width = img.getWidth();
            int height = img.getHeight();

            
            // 2. Konvertierung in Graustufen-Grid (0-255)
            int[][] grid = new int[height][width];

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = img.getRGB(x, y);
                    // Entspricht Float64.(gray_img) .* 255
                    grid[y][x] = (rgb & 0xFF)%modulo;
                }
            }

            // 4. Simulation
            int[][] result = simulateCellularAutomaton(grid, totalSteps, modulo);

            // 5. Skalieren und Speichern
            BufferedImage outputImg = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    // Skalierung auf [0, 1] und zurück auf 255
                    int val = (int) Math.round((result[y][x] / (double) modulo) * 255);
                    int gray = (val << 16) | (val << 8) | val;
                    outputImg.setRGB(x, y, gray);
                }
            }

            ImageIO.write(outputImg, "png", new File(outputPath));
            System.out.println("Fertig! Ergebnis gespeichert unter: " + outputPath);

        } catch (IOException e) {
            System.err.println("Fehler: Bilddatei nicht gefunden oder schreibgeschützt.");
        }
    }

    public static BufferedImage convertToGrayScale(BufferedImage image) {
    	  BufferedImage result = new BufferedImage(
    	            image.getWidth(),
    	            image.getHeight(),
    	            BufferedImage.TYPE_BYTE_GRAY);
    	  Graphics g = result.getGraphics();
    	  g.drawImage(image, 0, 0, null);
    	  g.dispose();
    	  return result;
    	}
    
    public static int[][] simulateCellularAutomaton(int[][] grid, long totalSteps, int modulo) {
        int rows = grid.length;
        int cols = grid[0].length;

        // grid = grid .% modulo
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                grid[y][x] %= modulo;
            }
        }

        long currentSteps = totalSteps;
        int power = 0;

        while (currentSteps > 0) {
            int digit = (int) (currentSteps % modulo);
            currentSteps /= modulo;

            // shift = modulo^power
            long shift = ((long) Math.pow(modulo, power));

            for (int d = 0; d < digit; d++) {
                int[][] nextGrid = new int[rows][cols];
                for (int y = 0; y < rows; y++) {
                    for (int x = 0; x < cols; x++) {
                        // Julia circshift Nachbildung
                        int up    = grid[((y - (int)(shift%rows)) + rows) % rows][x];
                        int down  = grid[((y + (int)(shift%rows)) + rows) % rows][x];
                        int left  = grid[y][((x - (int)(shift%cols)) + cols) % cols];
                        int right = grid[y][((x + (int)(shift%cols)) + cols) %cols];

                        // grid = (up .+ down .+ left .+ right) .% modulo
                        nextGrid[y][x] = (up + down + left + right) % modulo;
                    }
                }
                grid = nextGrid;
            }
            power++;
        }
        return grid;
    }
}
