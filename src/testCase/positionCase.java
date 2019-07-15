package testCase;

import processing.core.PApplet;

public class positionCase extends PApplet {
    float x;
    int offset = 0;
    public void settings(){
        size(240,120);
    }
    public void setup(){
        smooth();
        strokeWeight(5);
        stroke(255,0,0);
        x = width/2;
    }
    public void draw(){
        background(204);
        if(mouseX > x){
            x +=1;
            offset = -10;
        }
        if(mouseX < x){
            x -=1;
            offset = 10;
        }
        stroke(255,100,100);
        line(x,0,x,height);
        stroke(100,255,100);
        line(mouseX,mouseY,mouseX+offset,mouseY-10);
        stroke(100,100,255);
        line(mouseX,mouseY,mouseX+offset,mouseY+10);
        stroke(100,20,240);
        line(mouseX,mouseY,mouseX+offset*3,mouseY);
    }

    public static void main(String[] args) {
        PApplet.main("testCase.positionCase");
    }
}
