package idv.kuan.studio.libgdx.printstar.transform;

import com.badlogic.gdx.math.Vector2;

import java.util.Map;

import idv.kuan.studio.libgdx.printstar.canvas.Canvas;
import idv.kuan.studio.libgdx.printstar.canvas.Point;
import idv.kuan.studio.libgdx.printstar.canvas.matrix.CanvasMatrix;

public class CanvasTransformer {
    private float cellSize;
    private float gapX;
    private float gapY;

    public CanvasTransformer() {
        this(2.0f, 20.0f, 10.0f);
    }

    public CanvasTransformer(float cellSize, float gapX, float gapY) {
        this.cellSize = cellSize;
        this.gapX = gapX;
        this.gapY = gapY;
    }

    public CanvasMatrix transform(Canvas source) {
        Map<Point, Character> cells = source.getCells();

        CanvasMatrix matrix = new CanvasMatrix();
        cells.forEach((cell, character) -> {
            CanvasMatrix.ProjectedCell projectedCell = new CanvasMatrix.ProjectedCell(
                new Point(cell.getGridX(), cell.getGridY()),
                new Vector2(cell.getGridX() * cellSize * gapX, (cell.getGridY() * cellSize * gapY) * -1),
                character);

            matrix.put(projectedCell);
        });

        return matrix;
    }

    //getter and setter

    public float getCellSize() {
        return cellSize;
    }

    public void setCellSize(float cellSize) {
        this.cellSize = cellSize;
    }
}
