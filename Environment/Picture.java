import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * Copyright Georgia Institute of Technology 2004-2005
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param width the width of the desired picture
   * @param height the height of the desired picture
   */
  public Picture(int width, int height)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }

  /*
    * Subtract the given values from the appropriate colors.
    * Input: rSub - the amount that should be subtracted from the original red value
    *        gSub - the amount that should be subtracted from the original green value
    *        bSub - the amount that should be subtracted from the original blue value
    * Returns: nothing
    */
    public void subtractColor(int rSub, int gSub, int bSub, int start, int end)
    {
      Pixel[] pixelArray = this.getPixels();
      while (start < end)
      {
       Pixel pixelObj = pixelArray[start];
        
       //The following code obtains the red value of a pixel and subtracts it by the value of rSub
       int redValue = pixelObj.getRed();
       pixelObj.setRed(redValue - rSub);
       
       //The following code obtains the green value of a pixel and subtracts it by the value of gSub
       int greenValue = pixelObj.getGreen();
       pixelObj.setGreen(greenValue - gSub);
       
       //The following code obtains the blue value of a pixel and subtracts it by the value of bSub
       int blueValue = pixelObj.getBlue();
       pixelObj.setBlue(blueValue - bSub);

       start = start + 1;
      }
    }
    
   /*
    * Create the negative of each pixel between the provided indices
    * Input: start - the index of the first pixel to be modified (inclusive)
    * end - the index of the last pixel to be modified (inclusive)
    * Returns: nothing
    */
    public void negative(int start, int end)
    { 
      Pixel[] pixelArray = this.getPixels();
      while (start < end)
      {
        Pixel pixelObj = pixelArray[start];
        
        //The following code obtains the red value of a pixel and subtracts it from 255
        int redValue = pixelObj.getRed();
        pixelObj.setRed(255 - redValue);
        
        //The following code obtains the green value of a pixel and subtracts it from 255
        int greenValue = pixelObj.getGreen();
        pixelObj.setGreen(255 - greenValue);
        
        //The following code obtains the blue value of a pixel and subtracts it from 255
        int blueValue = pixelObj.getBlue();
        pixelObj.setBlue(255 - blueValue);
        
        start = start + 1;
      } 
    } 

   /*
    * Create the grayscale of each pixel between the provided indices
    * Input: start - the index of the first pixel to be modified (inclusive)
    *        end - the index of the last pixel to be modified (inclusive)
    * Returns: nothing
    */
    public void grayscale(int start, int end)
    { 
      Pixel[] pixelArray = this.getPixels();
      while (start < end)
      {
        Pixel pixelObj = pixelArray[start];
        
        //The following code obtains the red, green, and blue values of a pixel
        int redValue = pixelObj.getRed();
        int greenValue = pixelObj.getGreen();
        int blueValue = pixelObj.getBlue();
        
        //The following code obtains the needed value of gray
        int grayValue = (redValue + greenValue + blueValue) / 3;
        
        //The values of red, green, and blue of a pixel are changed to the obtained gray value
        pixelObj.setRed(grayValue);
        pixelObj.setGreen(grayValue);
        pixelObj.setBlue(grayValue);
        
        start++;
      } 
    } 
    
    /*
     * Apply myFilter to each pixel between the provided indices
     * Input: start - the index of the first pixel to be modified (inclusive)
     *        end - the index of the last pixel to be modified (inclusive)
     * Returns: nothing
     */
     public void myFilter(int start, int end)
     { 
       Pixel[] pixelArray = this.getPixels();
       while (start < end)
       {
         Pixel pixelObj = pixelArray[start];
         
         //The following code obtains the red, green, and blue values of a pixel
         int redValue = pixelObj.getRed();
         int greenValue = pixelObj.getGreen();
         int blueValue = pixelObj.getBlue();
         
         //This divides the value of red in a pixel by two
         pixelObj.setRed(redValue / 2);
         
         //This gets rid of the value of green in a pixel
         pixelObj.setGreen(greenValue - greenValue);
         
         //This triples the value of blue in a pixel
         pixelObj.setBlue(blueValue * 2);
         
         start = start + 1;
       } 
     } 
  
  public void simpleGrayscale()
  {
    Pixel[] pixelArray = getPixels();

    for (int index = 0; index < pixelArray.length; index++)
    {
      Pixel pixelObj = pixelArray[index];
      
      int value = (pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 3;

      pixelObj.setRed(value);
      pixelObj.setGreen(value);
      pixelObj.setBlue(value);
    }
  }
  
  public void simpleGrayscaleVersion2()
  {
  Pixel[] pixelArray = getPixels();
  Pixel pixelObj;
    int index = 0;
    while ( index < pixelArray.length )
    {
      // System.out.println("index is: " + index);
    
      pixelObj = pixelArray[index];
      int value = (pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 3;
     
      pixelObj.setRed(value);
      pixelObj.setGreen(value);
      pixelObj.setBlue(value);
      index++;
    }
  }
                                   
public void createCollage(Picture p1, Picture p2)
{
  Pixel sourcePix, targetPix;
    
  int targetX = 0;
  int targetY = 0;
    
  for (int sourceX = 0;  sourceX < p1.getWidth(); sourceX++)
  {
    targetY = 0;
    for (int sourceY = 0; sourceY < p1.getHeight();  sourceY++)
    {
      sourcePix = p1.getPixel(sourceX, sourceY);
      targetPix = this.getPixel(targetX, targetY);
      targetPix.setColor(sourcePix.getColor());
      targetY++;
    }
    targetX++;
  }
   
  // System.out.println("Last width (x) of p1 copied into calling object: "
  //                    + (p1.getWidth()-1));
    
  targetY = 0;
  targetX = p1.getWidth();
  
  // System.out.println("First width (x) of p2 copied into calling object: "
  //                    + targetX);
    
  for (int sourceX = 0;  sourceX < p2.getWidth(); sourceX++)
  {
    targetY = 0;
    for (int sourceY = 0; sourceY < p2.getHeight();  sourceY++)
    {
      sourcePix = p2.getPixel(sourceX, sourceY);
      targetPix = this.getPixel(targetX, targetY);
      targetPix.setColor(sourcePix.getColor());
      targetY++;
    }
    targetX++;
  }

}
  
} // this } is the end of class Picture, put all new methods before this
 
