package idv.kuan.studio.libgdx.printstar.canvas.matrix;

import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

import idv.kuan.studio.libgdx.printstar.canvas.Point;

public class CanvasMatrix {
    private Map<Point, ProjectedCell> matrix;

    public CanvasMatrix() {
        matrix = new HashMap<>();
    }

    public void put(ProjectedCell cell) {
        matrix.put(cell.logicalPoint, cell);
    }

    public Map<Point, ProjectedCell> getMatrix() {
        return matrix;
    }


    //classes
    public static class ProjectedCell {
        private final Point logicalPoint;
        private final Vector2 worldPosition;
        private final char character;

        public ProjectedCell(Point logicalPoint, Vector2 worldPosition, char character) {
            this.logicalPoint = logicalPoint;
            this.worldPosition = worldPosition;
            this.character = character;
        }

        public Vector2 getWorldPosition() {
            return worldPosition;
        }

        public char getCharacter() {
            return character;
        }

        public String getString(){
            return String.valueOf(character);
        }
    }
}
