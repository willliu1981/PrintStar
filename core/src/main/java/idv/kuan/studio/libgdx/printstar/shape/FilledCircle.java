// =======================
// 說明：填滿圓盤（白日中心）
// size = 直徑格數
// =======================
package idv.kuan.studio.libgdx.printstar.shape;

import idv.kuan.studio.libgdx.printstar.canvas.Point;
import idv.kuan.studio.libgdx.printstar.canvas.writer.CellWriter;
 public final class FilledCircle extends Shape {

    private final Material material;
    private final int radius;

    FilledCircle(Material material, int radius) {
        this.material = material;
        this.radius = Math.max(1, radius);
    }

    @Override
    protected Point draw(CellWriter writer, int size) {
        int diameter = Math.max(3, size);
        int centerX = diameter / 2;
        int centerY = diameter / 2;

        int radiusSquared = radius * radius;

        for (int gridY = centerY - radius; gridY <= centerY + radius; gridY++) {
            for (int gridX = centerX - radius; gridX <= centerX + radius; gridX++) {
                int dx = gridX - centerX;
                int dy = gridY - centerY;
                int distanceSquared = dx * dx + dy * dy;

                if (distanceSquared <= radiusSquared) {
                    writer.set(gridX, gridY, material.token());
                }
            }
        }

        return new Point(centerX, centerY);
    }
}