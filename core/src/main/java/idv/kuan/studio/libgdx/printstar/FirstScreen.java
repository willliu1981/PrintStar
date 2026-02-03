package idv.kuan.studio.libgdx.printstar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import idv.kuan.studio.libgdx.printstar.canvas.Canvas;
import idv.kuan.studio.libgdx.printstar.shape.Triangle;


public class FirstScreen extends ScreenAdapter {

    private SpriteBatch spriteBatch;
    private BitmapFont bitmapFont;

    private Canvas canvas;


    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        bitmapFont = new BitmapFont();

        // 設定字體大小（2 倍）
        bitmapFont.getData().setScale(2.0f);


        canvas = new Canvas();

        Triangle triangle = new Triangle();
        canvas.draw(triangle, 1, 1, 1);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        canvas.draw(spriteBatch, 20.0f, 740.0f);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        bitmapFont.dispose();
        spriteBatch.dispose();
    }
}
