package idv.kuan.studio.libgdx.printstar.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import idv.kuan.studio.libgdx.printstar.canvas.Canvas;
import idv.kuan.studio.libgdx.printstar.shape.Triangle;
import idv.kuan.studio.libgdx.printstar.transform.CanvasTransformer;


public class FirstScreen extends ScreenAdapter {

    private SpriteBatch spriteBatch;

    private Canvas canvas;


    @Override
    public void show() {
        spriteBatch = new SpriteBatch();


        canvas = new Canvas();
        canvas.getTransformer().setCellSize(2.0f);

        Triangle triangle = new Triangle();
        canvas.paint(triangle, 1, 1, 7);

    }

    int direction = 1;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float cellSize = canvas.getTransformer().getCellSize();
        float d = 0.031f;
        if (direction == 1) {
            cellSize += d;
        } else {
            cellSize -= d;
        }


        if (cellSize > 10) {
            direction = -1;
        } else if (cellSize < 1.0f) {
            direction = 1;
        }

        if (cellSize < 1.0f) {
            cellSize = 1.0f;
        }
        canvas.getTransformer().setCellSize(cellSize);

        spriteBatch.begin();
        canvas.render(spriteBatch, 120.0f, 540.0f);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }
}
