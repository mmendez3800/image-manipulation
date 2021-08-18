/*
This program manipulates the pixel values of an image in four different ways
*/ 

public class PixelManipulation
{ 
    public static void main (String[] args) 
    { 
      // This allows us to pick a picture
      Picture original = new Picture(FileChooser.pickAFile() );
      
      // This explores our picture before any of the methods are called
      original.explore();
      
      // This stores the number of pixels in the picture to a primitive variable
      int pixels = original.getPixels().length;
      
      // This uses our negative method for the top fourth of the pixels in the image
      original.negative(0, pixels / 4);
      
      // This uses our grayscale method for the second fourth of the pixels in the image
      original.grayscale(pixels / 4, (pixels * 2) / 4);
      
      // This uses the myFilter method for the third fourth of the pixels in the image
      original.myFilter( (pixels * 2) / 4, (pixels * 3) / 4);

      // This manipulates the RGB values of the pixels for the bottom fourth of the pixels in the image
      int subtractRed = 100;
      int subtractGreen = 20;
      int subtractBlue = 45;
      original.subtractColor(subtractRed, subtractGreen, subtractBlue, (pixels * 3) / 4, pixels);
      
      // This explores our picture after the methods have been called
      original.explore();
    } 
} 

