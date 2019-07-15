package testCase;
import processing.core.PApplet;
public class forLOOP2 extends PApplet {
    public static void main(String[] args) {
        PApplet.main("testCase.forLOOP2");
    }

    public void settings() {
        size(480, 220);

    }

    public void setup() {
        smooth();
        background(165, 120, 120);
        noStroke();
        strokeWeight(2);
    }

    public void draw() {
        for (int y = 0; y <= height; y += 40) {
            for (int x = 0; x <= width; x += 40) {
                fill(165, 45, 45, 140);
                ellipse(x, y, 40, 40);
            }
        }


    }
}

