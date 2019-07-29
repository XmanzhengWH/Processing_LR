package testCase;

import processing.core.PApplet;

public class ArrayVariable extends PApplet {
    float[] x = new float[3000];

    public void settings(){
        size(480,120);

    }
    public void setup(){
        smooth();
        noStroke();
        fill(255,200);
        for(int i=0;i<x.length;i++){
            x[i] = random(-1000,200);
        }
    }
    public void draw(){
        background(204);
        for(int i = 0;i < x.length;i++ ){
            x[i] += (float)0.5;
            float y = i *(float)0.4;
            fill(255,0,0);
//            ellipse(x[i],y,12,12);
            arc(x[i],y,15,15,(float)0.52,(float)5.76);

        }
    }

    public static void main(String[] args) {
        PApplet.main("testCase.ArrayVariable");
    }
}
