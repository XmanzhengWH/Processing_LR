package testCase;

import processing.core.PApplet;


public class LightlyFollow extends PApplet {
    float x;
    float y;
    float px;
    float py;
    double easing = 0.05;
//    float diameter = 12;

    public void settings(){
        size(480,120);
    }
    public void setup(){
        smooth();
        stroke(0,120);
    }
    public void draw(){
        float targetX = mouseX;
        x += (targetX-x)*easing;
        float targetY = mouseY;
        y += (targetY-y)*easing;
        float weight = dist(x,y,px,py);
        strokeWeight(weight);
        line(x,y,px,py);
        py = y;
        px = x;
//        ellipse(x,40,12,12);
//        print(targetX+":"+x);
    }

    public static void main(String[] args) {
        PApplet.main("testCase.LightlyFollow");
    }


}
