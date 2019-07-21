package testCase;

import processing.core.PApplet;
import processing.core.PFont;

public class fontUsage extends PApplet {
    PFont font;
    String quote = "The first font look good";
    public void settings(){
        size(480,120);
    }
    public void setup(){
        smooth();
        font = loadFont("Algerian-48.vlw");
        textFont(font);
    }
    public void draw(){
        background(102);
        textSize(36);
        text(quote,25,15,300,100);
        textSize(18);
        text(quote,27,85,200,100);
    }

    public static void main(String[] args) {
        PApplet.main("testCase.fontUsage");
    }
}
