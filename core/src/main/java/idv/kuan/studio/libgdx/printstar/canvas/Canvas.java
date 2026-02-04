package idv.kuan.studio.libgdx.printstar.canvas;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

import idv.kuan.studio.libgdx.printstar.canvas.matrix.CanvasMatrix;
import idv.kuan.studio.libgdx.printstar.shape.Shape;
import idv.kuan.studio.libgdx.printstar.transform.CanvasTransformer;

public class Canvas {
    private Map<Point, Character> cells;

    private CanvasTransformer transformer;
    private char placeholder;

    public Canvas() {
        this(' ');
    }

    public Canvas(char placeholder) {
        cells = new HashMap<>();
        transformer = new CanvasTransformer();
        this.placeholder = placeholder;
    }

    //classes
    public enum FLIP {
        FLIP_X, FLIP_Y, NONE
    }

    public void paint(Shape shape, int x, int y, int size) {
        paint(shape, x, y, size, FLIP.NONE);
    }

    public void paint(Shape shape, int x, int y, int size, FLIP flip) {
        shape.draw(size);

        shape.getCells().forEach((point, character) -> {
            int flippedX = flip == FLIP.FLIP_X ? shape.getPivot().getGridX() * 2 - point.getGridX() : point.getGridX();
            int flippedY = flip == FLIP.FLIP_Y ? shape.getPivot().getGridY() * 2 - point.getGridY() : point.getGridY();

            int cx = flippedX + x;
            int cy = flippedY + y;
            this.cells.put(new Point(cx, cy), character);
        });
    }

    public void render(SpriteBatch spriteBatch, float startX, float startY) {
        CanvasMatrix canvasMatrix = this.transform();

        BitmapFont bitmapFont = new BitmapFont();
        bitmapFont.getData().setScale(transformer.getCellSize());

        if (canvasMatrix != null) {
            canvasMatrix.getMatrix().values().forEach(cell -> {
                Vector2 vector2 = cell.getWorldPosition();
                bitmapFont.draw(
                    spriteBatch,
                    cell.getString(),
                    vector2.x + startX,
                    vector2.y + startY);
            });
        }
    }

    public CanvasMatrix transform() {
        return transformer.transform(this);
    }


    public String exportAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        char[][] charMatrix = toCharMatrix(this.transform().getMatrix(), placeholder);

        for (int rowIndex = 0; rowIndex < charMatrix.length; rowIndex++) {
            for (int colIndex = 0; colIndex < charMatrix[0].length; colIndex++) {
                stringBuilder.append(charMatrix[rowIndex][colIndex]);
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    private static char[][] toCharMatrix(Map<Point, CanvasMatrix.ProjectedCell> cells, char placeholder) {
        if (cells == null || cells.isEmpty()) {
            return new char[0][0];
        }

        int maxX = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;

        for (Point point : cells.keySet()) {
            int gridX = point.getGridX();
            int gridY = point.getGridY();

            if (gridX > maxX) {
                maxX = gridX;
            }
            if (gridX < minX) {
                minX = gridX;
            }
            if (gridY > maxY) {
                maxY = gridY;
            }
            if (gridY < minY) {
                minY = gridY;
            }
        }

        int width = maxX - minX + 1;
        int height = maxY - minY + 1;

        char[][] grid = new char[height][width];

        for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
            for (int colIndex = 0; colIndex < grid[rowIndex].length; colIndex++) {
                grid[rowIndex][colIndex] = placeholder;
            }
        }

        for (Map.Entry<Point, CanvasMatrix.ProjectedCell> entry : cells.entrySet()) {
            Point point = entry.getKey();
            char value = entry.getValue().getCharacter();

            int rowIndex = point.getGridY() - minY;
            int colIndex = point.getGridX() - minX;

            // ✅ 防呆：避免任何意外越界造成隱性問題
            if (rowIndex < 0 || rowIndex >= grid.length) {
                continue;
            }
            if (colIndex < 0 || colIndex >= grid[rowIndex].length) {
                continue;
            }

            grid[rowIndex][colIndex] = value;
        }

        return grid;
    }


    //getter & setter
    public Map<Point, Character> getCells() {
        return cells;
    }


    public CanvasTransformer getTransformer() {
        return transformer;
    }


}
