package testCase;
import processing.core.PApplet;

public class needleLine extends PApplet {


    public void settings() {
        size(480, 120);
    }

    public void setup() {
        smooth();
        background(160, 145, 145);
        stroke(102);
        strokeWeight(2);
    }

    public void draw() {
        for (int y = 20; y <= height-20; y += 10) {
            for (int x = 0; x <= width-20; x += 10) {
                ellipse(x, y, 4, 4);
                line(x, y, 240, 60);
            }
        }
    }
    public static void main(String[] args) {
        PApplet.main("testCase.needleLine");
    }
}