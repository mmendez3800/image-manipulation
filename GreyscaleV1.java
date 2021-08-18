/*
This program will convert a selected image into greyscale
*/

public class GreyscaleV1
{
  public static void main (String[] args)
  {
     Picture pic = new Picture (FileChooser.pickAFile());
     Picture picGray = new Picture (pic);
     pic.show();
     picGray.simpleGrayscale();
     picGray.show();
  }
}

