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

    private CanvasMatrix matrix;
    private CanvasTransformer transformer;

    public Canvas() {
        cells = new HashMap<>();
        transformer = new CanvasTransformer();
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

        Canvas transformedCanvas = transformer.transform(this);
        CanvasMatrix canvasMatrix = transformedCanvas.getMatrix();

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

    public String exportAsString() {
        StringBuilder stringBuilder = new StringBuilder();

        return null;
    }


    //getter & setter
    public Map<Point, Character> getCells() {
        return cells;
    }

    public CanvasMatrix getMatrix() {
        return matrix;
    }

    public void setMatrix(CanvasMatrix matrix) {
        this.matrix = matrix;
    }

    public CanvasTransformer getTransformer() {
        return transformer;
    }
}
