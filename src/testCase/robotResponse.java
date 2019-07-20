package testCase;

import processing.core.PApplet;
import processing.core.PImage;

public class robotResponse extends PApplet {
    float x = 60;
    float y = 440;
    int radius = 45;
    int bodyHeight = 160;
    int neckHeight = 70;
    float easing = (float) 0.02;
    PImage img;
    int Pwidth = 360;
    int Pheight =580;


    public void settings(){
        size(Pwidth,Pheight);
    }
    public void setup(){
        smooth();
        strokeWeight(2);
        ellipseMode(RADIUS);
        img = loadImage("opYDY.png");
    }
    public void draw(){
        image(img,0,0,Pwidth,Pheight);
        int targetX = mouseX;
        x += (targetX -x)*easing;
        int targetY = mouseY;
        y += (targetY-y)*easing;
        if(mousePressed){
            neckHeight = 20;
            bodyHeight = 90;
        }else{
            neckHeight = 70;
            bodyHeight = 160;
        }
        float ny = y - bodyHeight - neckHeight -radius;
//        background(204);
        //neck
        stroke(102);
        strokeWeight(12);
        line(x+12,y-bodyHeight,x+12,ny);
        //antenna
        line(x+12,ny,x-18,ny-43);
        line(x+12,ny,x+42,ny-99);
        line(x+12,ny,x+78,ny+15);
        //body
        noStroke();
        fill(102);
        ellipse(x,y-33,33,33);
        fill(0);
        rect(x-45,y-bodyHeight,90,bodyHeight-33);
        //head
        fill(0);
        ellipse(x+12,ny,radius,radius);
        fill(255);
        ellipse(x+24,ny-6,14,14);
        fill(0);
        ellipse(x+24,ny-6,3,3);
    }

    public static void main(String[] args) {
        PApplet.main("testCase.robotResponse");
    }

}
