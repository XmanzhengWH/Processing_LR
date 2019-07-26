package testCase;

import processing.core.PApplet;

public class spiralShape extends PApplet {
    float angle = (float)0.0;
    float offset = 60;
    float scalar = 2;
    float speed = (float)0.05;

    public void settings(){
        size(120,120);
        smooth();
    }
    public void setup(){
        fill(120,80,200,200);
    }
    public void draw(){
        float y = offset + cos(angle)*scalar;
        float x = offset + sin(angle)*scalar;
        ellipse(x,y,2,2);
        angle += speed;
        scalar += speed;
    }

    public static void main(String[] args) {
        PApplet.main("testCase.spiralShape");
    }

}
