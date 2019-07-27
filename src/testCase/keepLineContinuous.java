package testCase;

import processing.core.PApplet;

public class keepLineContinuous extends PApplet{
    float angle = (float) 0.0;

    public void settings(){
        size(120,120);
        smooth();
    }
    public void draw(){
        translate(mouseX,mouseY);
        float scalar = sin(angle) + 2;
        scale(scalar);
        strokeWeight((float) 1.0/scalar);
        rect(-15,-15,30,30);
        angle += (float)0.1;

    }

    public static void main(String[] args) {
        PApplet.main("testCase.keepLineContinuous");
    }

}
