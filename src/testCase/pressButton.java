package testCase;

import processing.core.PApplet;

public class pressButton extends PApplet {
    String key= "A";
    public void settings(){
        size(280,280);
    }
    public void setup(){
        smooth();
        textSize(64);
        background(204);
        textAlign(CENTER);
    }
    public void draw() {

        if (keyPressed) {
            if ((key == "h") || (key == "H")) {
                line(30, 60, 90, 60);
            }
            if ((key == "n") || (key == "N")) {
                line(30, 20, 20, 100);
            }
//            if ((key == "a") || (key == "A")) {
//                fill(0, 255, 0);
//                text(key, 60, 80);
//            }
            line(30, 20, 30, 100);
            line(90, 20, 90, 100);
        }
    }

    public static void main(String[] args) {
        PApplet.main("testCase.pressButton");
    }
}
