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

public class Project_3 extends PApplet {

PImage img;
PFont font;
PFont font2;
PFont font3;
PFont font4;

int imgSizeX = 15;
int imgSizeY = 15;
int imgX = 100;
int imgY = 130;
int displayImage = 0; // Element of the array being accessed
int numPhotos;

Table table;
boolean overImg;
ArrayList<Photo> photos = new ArrayList<Photo>();

//boolean overClear = false;
//  a.sx = a.originalSizeX;
//          a.sy = a.originalSizeY;


public void setup() {
  
  background(255);
  numPhotos = 0;
  int index = 0;
  Table table = loadTable("data.csv", "header");
  numPhotos += table.getRowCount();
  for (int i = 0; i<table.getRowCount(); i++) {
      // Iterate over all the rows in a table.
      TableRow row = table.getRow(i);
  
      // Access the fields via their column name (or index).
      String name = row.getString("name");
      String time = row.getString("date (yyyy_mm_dd_hh_mm_ss)");
      String tag1 = row.getString("tag 1");
      String tag2 = row.getString("tag 2");
      String tag3 = row.getString("tag 3");
      if (index == 0) {
        imgX = 100;
        imgY = 130;
      } else if (name.contains("_000") || name.contains("Laptop_Keyboard_001") || name.contains("Lamp_001") || name.contains("headphones_001") || name.contains("Grey_Blanket_001") || name.contains("cross_button_001") || name.contains("commuting_life_001")) {
        imgX = 100;
        imgY += 20;
        //println(name);
      } else {
        imgX += 15;
      }
      // Make a Photo object out of the data from each row.
      Photo a = new Photo(name, time, tag1, tag2, tag3, imgX, imgY, imgSizeX, imgSizeY);
      photos.add(index,a);
      //println("On " + imgX + ": photo name is " + name); 
      index += 1;
      displayImage++;
      if (displayImage >= photos.size()){
        displayImage = 0;   
      }
    }
}

public void draw() {
  background(255);
  fill(0);
  rect(80,100,5,900);
  rect(80,1000,1800,5);
  rect(975,0,5,125);
  rect(975,125,1000,5);
  fill(220,220,220);
  rect(980,0,1000,125);
  fill(255);
  strokeWeight(4);
  rect(1630,150,280,280);
  strokeWeight(1);
  fill(0);
  font = loadFont("Impact-48.vlw");
  textFont(font, 20);
  text("0", 80, 1030);
  text("10", 255, 1030);
  text("20", 405, 1030);
  text("30", 555, 1030);
  text("40", 705, 1030);
  text("50", 855, 1030);
  text("60", 1005, 1030);
  text("70", 1155, 1030);
  text("80", 1305, 1030);
  text("90", 1455, 1030);
  text("100", 1605, 1030);
  text("110", 1755, 1030);
  
  text("40", 55, 195);
  text("35", 55, 295);
  text("30", 55, 395);
  text("25", 55, 495);
  text("20", 55, 595);
  text("15", 55, 695);
  text("10", 55, 795);
  text("5", 55, 895);
  
  
  font2 = loadFont("SegoeUISymbol-48.vlw");
  textFont(font2, 30); 
  text("Name:", 1000, 50);
  text("Date:", 1000, 100);
  text("Tag 1:", 1500, 30);
  text("Tag 2:", 1500, 70);
  text("Tag 3:", 1500, 110);
  
  font3 = loadFont("Rockwell-Bold-48.vlw");
  textFont(font3, 30);  
  text("Click An Image with the Mouse to Enlarge", 10, 40);
  
  font4 = loadFont("FranklinGothic-Heavy-48.vlw");
  textFont(font4, 45);
  text("Amount of Images", 750, 1070);
  rotate(-1.6f);
  text("Data Sets", -600, 25);
  rotate(1.6f);
  //println(photos.size());
  //int num = 0;
  for (int i = 0; i<photos.size(); i++) {
    Photo a = photos.get(i);
    //println(a.name+ " , "+a +" , " + a.x + ", " + a.y);
    //println(num);
    //num++;
    a.display();
    a.overPict();
  }
}

  public boolean overRect(int x, int y, int width, int height)  {
    if (mouseX >= x && mouseX <= x+width && 
        mouseY >= y && mouseY <= y+height) {
      return true;
    } else {
      return false;
    }
  }
class Photo{
  String name;
  String tag1;
  String tag2;
  String tag3;
  String date;
  PImage image;
  int x,y;
  int sx, sy;
  int originalSizeX = imgSizeX;
  int originalSizeY = imgSizeY;
  int originalX = x;
  int originalY = y;
   
  // Create the Photo object
  Photo(String n, String d, String t1, String t2, String t3, int imgX, int imgY, int sizeX, int sizeY) {
    name = n;
    tag1 = t1;
    tag2 = t2;
    tag3 = t3;
    date = d;
    x = imgX;
    y = imgY;
    sx = sizeX;
    sy = sizeY;
    image = loadImage("Images/"+ name);
  }
  
  public void display() {
    image(image,x,y,sx,sy);
  }
  
  public void overPict() {
    if (overRect(x,y,sx,sy) && mousePressed) {
        Photo clickedPhoto = new Photo(name, date, tag1, tag2, tag3, 1630, 150, 280, 280);
        textFont(font2, 30); 
        clickedPhoto.display();
        text(name, 1100, 50);
        text(date , 1100, 100);
        text(tag1, 1600, 30);
        text(tag2, 1600, 70);
        text(tag3, 1600, 110);
       }
  }
}
  public void settings() {  fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Project_3" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
