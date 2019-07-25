package testCase;

import processing.core.PApplet;

public class betweenLocations extends PApplet {
    int startX = 20 ;
    int stopX = 160 ;
    int startY = 30;
    int stopY = 80 ;
    float x = startX;
    float y = startY;
    float step = (float)0.005 ;
    float pct = (float) 0.0;

    public void settings(){
        size(240,120);
        smooth();
    }

    public void draw(){
        background(204);
        for (int a = 20; a <width;a +=20){
            float mx = mouseX / 10;
            float offsetA = random(-mx,mx);
            float offsetB = random(-mx,mx);
            line(a + offsetA,20,a - offsetB,100);
        }
//        float r = random(0,mouseX);
//        println(r);
        if (pct < 1.0){
            x = startX + ((stopX-startX)*pct);
            y = startY + ((stopY-startY)*pct);
            pct += step;
        }
        ellipse(x,y,20,20);
    }

    public static void main(String[] args) {
        PApplet.main("testCase.betweenLocations");
    }
}
