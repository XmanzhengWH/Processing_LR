package testCase;
import processing.core.PApplet;

public class followMouse extends PApplet {


    public void settings() {
        size(480, 120);
    }

    public void setup() {
        fill(0,102);
        smooth();
        background(160, 145, 145);
        noStroke();

    }

    public void draw() {
        fill(126, 250, 78, 180);
        ellipse(mouseX,mouseY,9,9);
    }


    public static void main(String[] args) {
        PApplet.main("testCase.followMouse");
    }
}