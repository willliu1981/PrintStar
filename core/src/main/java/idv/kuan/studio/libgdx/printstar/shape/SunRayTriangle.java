package idv.kuan.studio.libgdx.printstar.shape;

import idv.kuan.studio.libgdx.printstar.canvas.Point;
import idv.kuan.studio.libgdx.printstar.canvas.writer.CellWriter;
import idv.kuan.studio.libgdx.printstar.shape.Shape;

// ===== 尖銳光芒：用幾何三角形填格，保留銳角，不會被「先格點再旋轉」磨掉 =====
public final class SunRayTriangle extends Shape {

    private final Material material;
    private final int baseWidth;
    private final int baseRadius;
    private final int tipRadius;

    SunRayTriangle(Material material, int baseWidth, int baseRadius, int tipRadius) {
        this.material = material;
        this.baseWidth = Math.max(3, baseWidth);
        this.baseRadius = Math.max(1, baseRadius);
        this.tipRadius = Math.max(this.baseRadius + 2, tipRadius);
    }

    @Override
    protected Point draw(CellWriter writer, int size) {
        int diameter = Math.max(3, size);
        int centerX = diameter / 2;
        int centerY = diameter / 2;

        // angle = 0 的 ray：方向朝右（1,0）
        double dirX = 1.0;
        double dirY = 0.0;
        double perpX = -dirY;
        double perpY = dirX;

        double baseCenterX = centerX + dirX * baseRadius;
        double baseCenterY = centerY + dirY * baseRadius;

        double halfBase = baseWidth / 2.0;

        double ax = baseCenterX + perpX * halfBase;
        double ay = baseCenterY + perpY * halfBase;

        double bx = baseCenterX - perpX * halfBase;
        double by = baseCenterY - perpY * halfBase;

        double cx = centerX + dirX * tipRadius;
        double cy = centerY + dirY * tipRadius;

        int minX = (int) Math.floor(Math.min(ax, Math.min(bx, cx)));
        int maxX = (int) Math.ceil(Math.max(ax, Math.max(bx, cx)));
        int minY = (int) Math.floor(Math.min(ay, Math.min(by, cy)));
        int maxY = (int) Math.ceil(Math.max(ay, Math.max(by, cy)));

        for (int gridY = minY; gridY <= maxY; gridY++) {
            for (int gridX = minX; gridX <= maxX; gridX++) {
                double sampleX = gridX + 0.5;
                double sampleY = gridY + 0.5;

                if (isPointInTriangle(sampleX, sampleY, ax, ay, bx, by, cx, cy)) {
                    writer.set(gridX, gridY, material.token());
                }
            }
        }

        // pivot 取「圓心」，方便 FlagSun 旋轉擺放
        return new Point(centerX, centerY);
    }

    private boolean isPointInTriangle(
        double px, double py,
        double ax, double ay,
        double bx, double by,
        double cx, double cy
    ) {
        double cross1 = cross(bx - ax, by - ay, px - ax, py - ay);
        double cross2 = cross(cx - bx, cy - by, px - bx, py - by);
        double cross3 = cross(ax - cx, ay - cy, px - cx, py - cy);

        boolean hasNeg = (cross1 < 0) || (cross2 < 0) || (cross3 < 0);
        boolean hasPos = (cross1 > 0) || (cross2 > 0) || (cross3 > 0);

        if (hasNeg && hasPos) {
            return false;
        }
        return true;
    }

    private double cross(double ax, double ay, double bx, double by) {
        return ax * by - ay * bx;
    }
}