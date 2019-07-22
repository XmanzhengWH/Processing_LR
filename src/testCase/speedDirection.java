package testCase;

import processing.core.PApplet;

public class speedDirection extends PApplet {
    int radius = 40;
//    float x = -radius;
    float x = 110;
    float speed = (float)0.5;
    int direction = 1;
    public void settings(){
        size(240,120);
    }
    public void setup(){
        smooth();
        ellipseMode(RADIUS);
    }
    public void draw() {
        background(0);
        x += speed * direction;
        if ((x > width - radius) || (x < radius)) {
            direction = -direction;
        }
        if (direction == 1) {
            arc(x, 60, radius, radius, (float) 0.52, (float) 5.76);
        } else {
            arc(x, 60, radius, radius, (float) 3.67, (float) 8.9);
        }
    }

    public static void main(String[] args) {
        PApplet.main("testCase.speedDirection");
    }
}
