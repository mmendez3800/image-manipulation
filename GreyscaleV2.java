/*
This program will convert a selected image into greyscale
*/

public class GreyscaleV2
{
  public static void main (String[] args)
  {
     Picture myPic = new Picture (FileChooser.pickAFile());
     myPic.show();
     Picture picGray = new Picture (myPic);
     picGray.simpleGrayscaleVersion2();
     picGray.show();
  }
}
