package idv.kuan.studio.libgdx.printstar.canvas.writer;

public interface CellWriter {
    void set(int gridX, int gridY, char character);

    void set(int x, int y, String signal);
}
