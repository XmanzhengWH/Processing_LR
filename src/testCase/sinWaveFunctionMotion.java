package testCase;

import processing.core.PApplet;

public class sinWaveFunctionMotion extends PApplet {
    float angle = (float) 0.0;
    float offset = 60;
    float scalar =40;
    float speed = (float) 0.05;
    public void settings(){
        size(240,120);
        smooth();
    }
    public void draw(){
//        float sinval = sin(angle);
//        println(sinval);
//        float gray = map(sinval,-1,1,0,255);
//        background(gray,gray-100,gray-180);
//        println(gray);
//        angle += 0.1;
        background(0);
        float y1 = offset + sin(angle)*scalar;
        float y2 = offset + sin((float) (angle + 0.4)) * scalar;
        float y3 = offset + sin((float) (angle + 0.8)) * scalar;

        ellipse(80,y1,40,40);
        ellipse(120,y2,40,40);
        ellipse(160,y3,40,40);
        angle += speed;
    }

    public static void main(String[] args) {
        PApplet.main("testCase.sinWaveFunctionMotion");
    }
}
