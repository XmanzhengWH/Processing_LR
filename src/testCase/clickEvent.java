package testCase;

import processing.core.PApplet;

public class clickEvent extends PApplet {
    public void settings(){
        size(240,120);
            }
    public void setup(){
        smooth();
        strokeWeight(30);
    }
    public void draw() {
        background(204);
        stroke(102);
        line(40, 0, 70, height);
        if (mousePressed) {
            if (mouseButton == LEFT) {
                stroke(255);
            } else {
                stroke(0);
            }
            line(0, 70, width, 50);
        }
    }

    public static void main(String[] args) {
        PApplet.main("testCase.clickEvent");
    }

}
