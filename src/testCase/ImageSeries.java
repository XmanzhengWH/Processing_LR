package testCase;

import processing.core.PApplet;
import processing.core.PImage;


public class ImageSeries extends PApplet {
    int numFrames = 12;//frames
    PImage[] images = new PImage[numFrames];
    int currentFrame = 1;
    public void settings(){
        size(240,120);

    }
    public void setup(){
        for(int i =0;i<images.length;i++){
            String imageName = "frame-"+nf(i,4)+".png";
            images[i] = loadImage(imageName);
        }
        frameRate(24);
    }
    public void draw(){
        image(images[currentFrame],0,0);
        currentFrame++;
        if (currentFrame>= images.length){
            currentFrame = 1;
        }
    }

    public static void main(String[] args) {
        PApplet.main("testCase.ImageSeries");
    }
}
