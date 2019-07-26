package testCase;

import processing.core.PApplet;

public class circularMotion extends PApplet {
    float angle = (float)0.0;
    float offset = 60;
    float scalar = 40;
    float offsetB = 120;
    float speed = (float)0.05;

    public void settings(){
        size(480,120);
        smooth();
    }
    public void draw(){
        fill(255,0,0,50);
        float y = offset + cos(angle)*scalar;
        float x = offset + sin(angle)*scalar;
        ellipse(x,y,40,40);
        fill(0,255,0,50);
        float a = offset + cos(angle)*scalar;
        float b = offset + sin(angle)*scalar;
        ellipse(a+offsetB,b,40,40);
        fill(0,0,255,60);
        float d = offset + cos(angle)*scalar;
        float c = offset + sin(angle)*scalar;
        ellipse(c+offsetB*2,d,40,40);
        fill(128,128,128,128);
        float e = offset + cos(angle)*scalar;
        float f = offset + sin(angle)*scalar;
        ellipse(e+offsetB*3,f,40,40);
        angle += speed;

    }

    public static void main(String[] args) {
        PApplet.main("testCase.circularMotion");
    }
}
