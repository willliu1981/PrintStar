package idv.kuan.studio.libgdx.printstar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FirstScreen extends ScreenAdapter {

    private SpriteBatch spriteBatch;
    private BitmapFont bitmapFont;

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        bitmapFont = new BitmapFont();

        // 設定字體大小（2 倍）
        bitmapFont.getData().setScale(2.0f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        bitmapFont.draw(spriteBatch, "********************************",
            20.0f, 740.0f);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        bitmapFont.dispose();
        spriteBatch.dispose();
    }
}
