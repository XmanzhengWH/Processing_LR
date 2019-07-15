package testCase;

import processing.core.PApplet;

public class rectangularBorder extends PApplet {
    int x = 80;
    int y = 30;
    int w = 80;
    int h = 60;
    public void settings(){
        size(240,120);
    }
    public void setup(){
        smooth();
        background(204);
    }
    public void draw(){
        if((mouseX>x)&&(mouseX<x+w)&&(mouseY>y)&&(mouseY<y+h)){
            fill(0);
        }else{
            fill(255);
        }
        rect(x,y,w,h);
    }

    public static void main(String[] args) {
        PApplet.main("testCase.rectangularBorder");
    }
}
