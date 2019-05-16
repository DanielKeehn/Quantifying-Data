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
  
  void display() {
    image(image,x,y,sx,sy);
  }
  
  void overPict() {
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
