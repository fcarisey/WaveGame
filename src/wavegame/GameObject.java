package wavegame;

import java.awt.*;

public abstract class GameObject implements IDrawable {
    protected float x, y;
    protected int width, height;
    protected ID id;
    protected float velX, velY;

    public GameObject(float x, float y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    public void setX(int x){
        this.x = x;
    }

    public float getX(){
        return x;
    }

    public void setY(int y){
        this.y = y;
    }

    public float getY(){
        return y;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public int getWidth(){
        return width;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public int getHeight(){
        return height;
    }

    public void setId(ID id){
        this.id = id;
    }

    public ID getId(){
        return id;
    }

    public void setVelX(int velX){
        this.velX = velX;
    }

    public float getVelX(){
        return velX;
    }

    public void setVelY(int velY){
        this.velY = velY;
    }

    public float getVelY(){
        return velY;
    }
}
