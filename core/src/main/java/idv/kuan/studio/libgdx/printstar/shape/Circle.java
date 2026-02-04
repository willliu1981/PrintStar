package idv.kuan.studio.libgdx.printstar.shape;

import idv.kuan.studio.libgdx.printstar.canvas.Point;
import idv.kuan.studio.libgdx.printstar.canvas.writer.CellWriter;

public class Circle extends Shape {
    @Override
    protected Point draw(CellWriter cellMapWriter, int size) {
        String character = "*";

        float radius = (size - 1) / 2.0f;

        float cneterGridX = radius;
        float cneterGridY = radius;

        int minX = (int) Math.floor(cneterGridX - radius);
        int maxX = (int) Math.ceil(cneterGridX + radius);
        int minY = (int) Math.floor(cneterGridY - radius);
        int maxY = (int) Math.ceil(cneterGridY + radius);

        for (int gridY = minY; gridY <= maxY; gridY++) {
            for (int gridX = minX; gridX <= maxX; gridX++) {
                double dx = gridX - cneterGridX;
                double dy = gridY - cneterGridY;
                double distanceSquared = dx * dx + dy * dy;

                if (distanceSquared <= radius * radius) {
                    cellMapWriter.set(gridX, gridY, character);
                }
            }
        }

        return new Point((int) cneterGridX, (int) cneterGridY);
    }
}
