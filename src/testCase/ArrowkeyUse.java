package testCase;

import processing.core.PApplet;

public class ArrowkeyUse extends PApplet {
    int x = 215;
    int y = 215;
    public void settings(){
        size(480,480);
    }
    public void setup(){

    }
    public void draw(){
        if(keyPressed && (key == CODED)){
            if(keyCode == LEFT){
                x--;
            }else if(keyCode == RIGHT){
                x++;
            }
        }
        if(keyPressed && (key == CODED)){
            if(keyCode == UP){
                y--;
            }else if(keyCode == DOWN){
                y++;
            }
        }
        rect(x,y,50,50);
    }

    public static void main(String[] args) {
        PApplet.main("testCase.ArrowkeyUse");
    }
}
