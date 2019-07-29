package testCase;

import processing.core.PApplet;

public class JitterBug extends PApplet{
    float x;
    float y;
    float diameter;
    float speed = (float)2.5;

    JitterBug(float tempX,float tempY,float tempDiameter){
        x = tempX;
        y = tempY;
        diameter = tempDiameter;
    }
    public void move(){
        x += random(-speed,speed);
        y += random(-speed,speed);
    }
    public void display(){
        ellipse(x,y,diameter,diameter);
    }
}
