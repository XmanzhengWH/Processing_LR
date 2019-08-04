package testCase;

import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class sketch_3Dboll extends PApplet {

    float t;
    public void setup(){
//size(720,404, P3D);

        t=0;
        textSize(32);
    }


    public void draw(){
        background(20);
        noFill();
        stroke(0xff74F599);

        lights();
        pushMatrix();
        translate(width/2, height/2, 200 * sin(t));
        rotateY(t);
        rotateX(PI/6);
        sphere(300);
        fill(0xff74F599);
        textMode(CENTER);
        text(str(second()*19840921),0,0,0);
        popMatrix();
        String time_now = str(year()) + " - " + str(month()) + " - " + str(day()) + " - " + str(hour()) +" : " + str(minute()) +" : " + str(second());
        text(time_now,100,150,0);
        text("Frame count: " + str(frameCount),100,200,0);
        t = (t + 0.01f) % TWO_PI;
    }


    public void mouseClicked(){
        if(mouseButton == RIGHT){
            String picName = "PDE_" + year()+ "_" + month()+"_" + day()+"_" +
                    hour()+"_" + minute()+"_" + second();
            save("D:\\myProcessingPics\\" + picName + ".png");
            println(picName + " ----> Picture saved.");
        }
    }

    public void settings() {
        fullScreen(P3D); }
    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[] { "testCase.sketch_3Dboll" };
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }
}

