// =======================
// 說明：圓環
// size = 直徑格數
// ringThickness = 厚度（格數）
// =======================
package idv.kuan.studio.libgdx.printstar.shape;

import idv.kuan.studio.libgdx.printstar.canvas.Point;
import idv.kuan.studio.libgdx.printstar.canvas.writer.CellWriter;

// ===== 圓環：只負責圓環幾何 =====
 public final class Ring extends Shape {

    private final Material material;
    private final int innerRadius;
    private final int outerRadius;

    Ring(Material material, int innerRadius, int outerRadius) {
        this.material = material;
        this.innerRadius = Math.max(0, innerRadius);
        this.outerRadius = Math.max(this.innerRadius + 1, outerRadius);
    }

    @Override
    protected Point draw(CellWriter writer, int size) {
        int diameter = Math.max(3, size);
        int centerX = diameter / 2;
        int centerY = diameter / 2;

        int innerSquared = innerRadius * innerRadius;
        int outerSquared = outerRadius * outerRadius;

        for (int gridY = centerY - outerRadius; gridY <= centerY + outerRadius; gridY++) {
            for (int gridX = centerX - outerRadius; gridX <= centerX + outerRadius; gridX++) {
                int dx = gridX - centerX;
                int dy = gridY - centerY;
                int distanceSquared = dx * dx + dy * dy;

                if (distanceSquared >= innerSquared && distanceSquared <= outerSquared) {
                    writer.set(gridX, gridY, material.token());
                }
            }
        }

        return new Point(centerX, centerY);
    }
}