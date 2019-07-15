package testCase;

import processing.core.PApplet;

public class roundBorder extends PApplet {
    int x = 120;
    int y = 60;
    int radius =12;
    public void settings(){
        size(420,120);
            }
    public void setup(){
        smooth();
        ellipseMode(RADIUS);
    }
    public void draw(){
        background(204);
        float d = dist(mouseX,mouseY,x,y);
        if(d<radius){
            radius++;
            fill(0);
        }else{
            fill(255);
        }
        ellipse(x,y,radius,radius);
            }

    public static void main(String[] args) {
        PApplet.main("testCase.roundBorder");
    }
}
