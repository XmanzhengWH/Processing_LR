package testCase;

import processing.core.PApplet;

public class movementCase extends PApplet {
        public void setup(){
            frameRate((float) 0.1);
        }
        public void draw(){
            println(frameRate);
        }

    public static void main(String[] args) {
        PApplet.main("testCase.movementCase");
    }
}
