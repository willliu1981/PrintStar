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

    public void paint(Shape shape, int x, int y, int size) {
        shape.draw(size);

        shape.getCells().forEach((point, character) -> {
            int cx = point.getGridX() + x;
            int cy = point.getGridY() + y;
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

        int maxX = 0, maxY = 0;

        for (Point point : cells.keySet()) {
            if (point.getGridX() > maxX) {
                maxX = point.getGridX();
            }
            if (point.getGridY() > maxY) {
                maxY = point.getGridY();
            }
        }

        char[][] grid = new char[maxY + 1][maxX + 1];

        for (int rowIndex = 0; rowIndex <= maxY; rowIndex++) {
            for (int colIndex = 0; colIndex <= maxX; colIndex++) {
                grid[rowIndex][colIndex] = placeholder;
            }
        }

        for (Map.Entry<Point, CanvasMatrix.ProjectedCell> entry : cells.entrySet()) {
            Point point = entry.getKey();
            Character value = entry.getValue().getCharacter();

            if (value != null) {
                grid[point.getGridY()][point.getGridX()] = value;
            }
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
