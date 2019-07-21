package testCase;

import processing.core.PApplet;
import processing.core.PShape;

public class shapeDraw extends PApplet {
    PShape network;
    public void settings(){
        size(240,120);
    }
    public void setup(){
        smooth();
        shapeMode(CENTER);
        network = loadShape("network.svg");
    }
    public void draw(){
        background(0);
        float diameter = map(mouseX,0,width,10,800);
//        shape(network,30,10);
        shape(network,120,60,diameter,diameter);
    }

    public static void main(String[] args) {
        PApplet.main("testCase.shapeDraw");
    }
}
