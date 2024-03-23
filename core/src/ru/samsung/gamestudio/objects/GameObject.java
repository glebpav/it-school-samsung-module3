package ru.samsung.gamestudio.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import static ru.samsung.gamestudio.GameSettings.SCALE;

public class GameObject {

    public int width;
    public int height;

    public Body body;
    Texture texture;

    GameObject(String texturePath, int x, int y, int width, int height, World world) {
        this.width = width;
        this.height = height;

        texture = new Texture(texturePath);
        body = createBody(x * SCALE, y * SCALE, world);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture,
                body.getPosition().x / SCALE - (width / 2f),
                body.getPosition().y / SCALE - (height / 2f),
                width,
                height);
    }

    public int getX() {
        return (int) (body.getPosition().x / SCALE);
    }

    public int getY() {
        return (int) (body.getPosition().y / SCALE);
    }

    public void setX(int x) {
        body.setTransform(x * SCALE, body.getPosition().y, 0);
    }

    public void setY(int y) {
        body.setTransform(body.getPosition().x, y * SCALE, 0);
    }

    private Body createBody(float x, float y, World world) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;
        Body body = world.createBody(def);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(Math.max(width, height) * SCALE / 2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 0.1f;
        fixtureDef.friction = 1f;

        body.createFixture(fixtureDef);
        circleShape.dispose();

        body.setTransform(x, y, 0);
        return body;
    }

}
