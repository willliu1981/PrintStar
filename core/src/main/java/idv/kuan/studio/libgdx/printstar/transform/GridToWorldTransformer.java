package idv.kuan.studio.libgdx.printstar.transform;

import com.badlogic.gdx.math.Vector2;

import idv.kuan.studio.libgdx.printstar.canvas.Canvas;

public class GridToWorldTransformer {
    private float cellSize;
    private float cellGapX;
    private float cellGapY;
    private char placeholder;

    private char[][] matrix;

    public GridToWorldTransformer() {
        this(2.0f, 20.0f, 10.0f, ' ');
    }

    public GridToWorldTransformer(float cellSize, float cellGapX, float cellGapY, char placeholder) {
        this.cellSize = cellSize;
        this.cellGapX = cellGapX;
        this.cellGapY = cellGapY;
        this.placeholder = placeholder;
    }

    public static Vector2 toWorld(Canvas canvas) {

        canvas.getCells().forEach((cell, character) -> {

        });

        return null;
    }
}
