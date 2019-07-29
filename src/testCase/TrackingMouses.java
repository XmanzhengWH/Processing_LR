package testCase;

import processing.core.PApplet;

public class TrackingMouses extends PApplet {
    int num = 120;
    int x[] = new int[num];
    int y[] = new  int[num];
    public void settings(){
        size(480,240);
    }
    public void setup(){
        smooth();
        noStroke();
    }
    public void draw(){
        background(0);
        //Copy array values from back to front
        for(int i = x.length-1;i > 0;i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        x[0] = mouseX;
        y[0] = mouseY;
        for(int i = 0;i < x.length;i++){
            fill(i*4);
            ellipse(x[i],y[i],40,40);
        }
    }

    public static void main(String[] args) {
        PApplet.main("testCase.TrackingMouses");
    }
}
