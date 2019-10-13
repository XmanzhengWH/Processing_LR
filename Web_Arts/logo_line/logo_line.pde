
static final int NUM_LINES = 35;
float t;

void setup(){
  background(20);
   size(500,500);
}

void draw(){
  background(20);
   //stroke(#F58F23);
   strokeWeight(5);
   
   translate(width/2,height/2);
   for(int i= 0; i < NUM_LINES;i++){
     stroke(#0E1FCB);
   line(x1(t +i),y1(t +i),x2(t + i),y2(t + i));
   stroke(#AD20E5);
   line(x1(-t +-i),y1(-t +-i),x2(-t + -i),y2(-t + -i));
   }
   for(int j =0; j< width;j++){
   stroke(#DB0B31);
   point(x(t+j),y(t+j));
   stroke(#A9E582);
   point(x(-t+-j),y(-t+-j));
   }
   //point(x(t),y(t));
   t += 0.4;
}

float x(float t){
  return sin(t / 5) * 50;
}
float y(float t){
  return cos(t / 20)*100 ;
}
float x1(float t){
  return sin(t /10)*100 + sin(t/15)*20 + cos(t *2)/ 16;
}
float y1(float t){
  return cos(-t / 10)*100 + sin(t / 25)* 50;
}
float x2(float t){
  return sin(t /10) * 200+ sin(t)*2 + cos(t)*30;
}
float y2(float t){
  return cos(t / 20)*200 + cos(t / 24)* 40;
}
