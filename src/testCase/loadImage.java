package testCase;

import processing.core.PApplet;
import processing.core.PImage;


public class loadImage extends PApplet {
    PImage img;
    PImage img2;
    public void settings(){
    size(320,480);
    }
    public void setup(){
        img = loadImage("opYDY.png");
        img2 = loadImage("octopus.png");

    }
    public void draw(){
        background(255);
        image(img,0,0,320,480);
        image(img2,0,0,mouseX*2,mouseY*2);
    }

    public static void main(String[] args) {
        PApplet.main("testCase.loadImage");
    }

}
