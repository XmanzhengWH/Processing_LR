package testCase;

import processing.core.PApplet;

public class randomMovement extends PApplet {
    float speed = (float)2.5;
    int diameter = 20 ;
    float x;
    float y;
    public void settings(){
        size(240,120);
        smooth();
        x = width/2;
        y = height/2;
    }
    public void draw(){
        x += random(-speed,speed);
        y += random(-speed,speed);
        x = constrain(x,0,width);
        y = constrain(y,0,height);
        ellipse(x,y,diameter,diameter);
    }

    public static void main(String[] args) {
        PApplet.main("testCase.randomMovement");
    }
}
