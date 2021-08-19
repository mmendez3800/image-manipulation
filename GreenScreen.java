/*
This program implements a green screen effect by using three images

The first image is of a person in front of a green screen wearing a blue shirt

The second image is the photo that will be placed on top of the green screen as the background

The third image is the photo that will be placed on top of the person's shirt as a second type of green screen effect
*/

public class GreenScreen
{ 
  public static void main (String[] args) 
  { 
    //The following code obtains the original image with me in front of a green screen
    String fileName1 = "pic0.jpg";
    Picture sourceImage = new Picture(fileName1);
    
    //The following code obtains the image that will be used as the new background
    String fileName2 = "pic1.jpg";
    Picture backgroundImage = new Picture(fileName2);
    
    //The following code obtains the image that will be used as the new shirt
    String fileName3 = "pic2.jpg";
    Picture shirtImage = new Picture(fileName3);
    
    //The following code makes a copy of the original image and shows it
    Picture sourceImageCopy = new Picture(sourceImage);
    sourceImageCopy.show();
    
    //The following code calls the chromakey method to change the background
    Picture changeBackground = sourceImage.chromakeyBackgroundChange(backgroundImage);
    
    //The following code calls the chromakey method to change the shirt
    Picture changeBackgroundAndShirt = changeBackground.chromakeyShirtChange(shirtImage, sourceImageCopy);
    
    //The following code shows our new picture
    changeBackgroundAndShirt.show();
  } 
} 