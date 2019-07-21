package testCase;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class mediaUsed extends PApplet {
    PShape bot1;
    PShape bot2;
    PShape bot3;
    PImage landscape;

    float easing = (float) 0.05;
    float offset = 0;

    public void settings(){
        size(1020,504);
    }
    public void setup(){
        bot1 = loadShape("robot1.svg");
        bot2 = loadShape("robot2.svg");
        bot3 = loadShape("robot3.svg");
        landscape = loadImage("alpine.png");
        smooth();
    }
    public void draw(){
        background(landscape);
        float targetOffset = map(mouseY,0,height,-40,40);
        offset += (targetOffset - offset)*easing;

        shape(bot1,105 + offset,65);

        float smallerOffset = (float) (offset *0.7);
        shape(bot2,510 + smallerOffset,140,78,248);

        smallerOffset *= -0.5;
        shape(bot3,410+smallerOffset,225,39,124);
    }

    public static void main(String[] args) {
        PApplet.main("testCase.mediaUsed");
    }
}
