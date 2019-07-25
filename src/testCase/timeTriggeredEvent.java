package testCase;

import processing.core.PApplet;

public class timeTriggeredEvent extends PApplet {
    int time1 = 2000;
    int time2 = 4000;
    float x = 0;

    public void settings(){
        size(480,120);
        smooth();
    }
    public void draw(){
        int currentTime = millis();
        background(204);
        if(currentTime > time2){
            x -=0.5;
        }else if (currentTime > time1){
            x += 2;
        }
        ellipse(x,60,90,90);
    }

    public static void main(String[] args) {
        PApplet.main("testCase.timeTriggeredEvent");
    }
}
