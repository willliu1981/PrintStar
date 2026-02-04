package idv.kuan.studio.libgdx.printstar.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import idv.kuan.studio.libgdx.printstar.canvas.Canvas;
import idv.kuan.studio.libgdx.printstar.canvas.matrix.CanvasMatrix;
import idv.kuan.studio.libgdx.printstar.shape.Triangle;
import idv.kuan.studio.libgdx.printstar.transform.CanvasTransformer;


public class FirstScreen extends ScreenAdapter {

    private SpriteBatch spriteBatch;
    private BitmapFont bitmapFont;

    private Canvas canvas;


    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        bitmapFont = new BitmapFont();

        canvas = new Canvas();
        canvas.getTransformer().setCellSize(2.0f);

        Triangle triangle = new Triangle();
        canvas.paint(triangle, 1, 1, 7, Canvas.FLIP.FLIP_Y);

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        String gridString = canvas.exportAsString();

        bitmapFont.getData().setScale(2.0f);

        spriteBatch.begin();
        bitmapFont.draw(spriteBatch, gridString, 120.0f, 540.0f);
        canvas.render(spriteBatch, 240.0f, 540.0f);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }
}
