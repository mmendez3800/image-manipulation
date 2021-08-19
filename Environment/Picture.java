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

  //The following method copies our calling object and performs the background change on the copy
  public Picture chromakeyBackgroundChange(Picture backgroundSourceImage) 
  { 
    //This copies our calling object
    Picture copy = new Picture(this);
    
    Pixel currentPixel = null;
    Pixel newPixel = null;
    
    //This outer for loop loops through all of the columns of the copy
    for(int x = 0; x < copy.getWidth(); x++) 
    { 
      //This inner for loop loops through all of the rows of the copy
      for(int y = 0; y < copy.getHeight(); y++) 
      { 
        currentPixel = copy.getPixel(x,y);
        
        /* This if statement allows us to specify that the nested loop will occur only when the red value and green 
         * value of the pixels of our calling object is greater than 100. This avoids the area (my head) where 
         * 60 < x < 125 and 100 < y < 185 */
        if(currentPixel.getGreen() > 100 && currentPixel.getRed() > 100 && ((x < 60 || x > 125) || (y < 100 || 
           y > 185)))
        { 
          newPixel = backgroundSourceImage.getPixel(x,y);
          currentPixel.setColor(newPixel.getColor());
        } 
      } 
    } 
    //Here we return the new background image
    return copy;
  } 
  
  //The following method copies our calling object and performs the shirt change on the copy
  public Picture chromakeyShirtChange(Picture shirtSourceImage, Picture originalSourceImage)
  { 
    //This copies our calling object
    Picture copy = new Picture(this);
    
    Pixel currentPixel = null;
    Pixel newPixelShirt = null;
    Pixel newPixelOriginal = null;
    
    //This outer for loop loops through all of the columns of the copy
    for(int x = 0; x < copy.getWidth(); x++) 
    { 
      //This inner for loop loops through all of the rows of the copy
      for(int y = 0; y < copy.getHeight(); y++) 
      { 
        currentPixel = copy.getPixel(x,y);
        newPixelOriginal = originalSourceImage.getPixel(x,y);
        
        /* This if statement allows us to specify that the nested loop will occur only when the sum of the red and
         * green values of the original picture is less than 150 and the sum of the red and green values of our calling
         * object is less than 150. This contains the shirt area where 180 < y < 394 */
        if(newPixelOriginal.getRed() + newPixelOriginal.getGreen() < 150 &&
           currentPixel.getRed() + currentPixel.getGreen() < 150 && y > 180 && y < 394)
        { 
          //This code changes the color of a pixel from the original image to the color of a pixel from the shirt image
          newPixelShirt = shirtSourceImage.getPixel(x,y);
          newPixelOriginal.setColor(newPixelShirt.getColor());
          
          /* This code changes the color of a pixel from the copy to the color of a pixel from the original image, thus
           * allowing us to replace the pixels of the shirt easier */
          currentPixel.setColor(newPixelOriginal.getColor());
        }
      } 
    } 
    return copy;
  } 

  //The following method creates a filter which calls for a picture object and makes it negative
  public void filterChoice1()  
  { 
    Pixel[] pixelArray = this.getPixels();
    for(Pixel pixelObj: pixelArray) 
    { 
      //The following code obtains the values of Red, Green, and Blue at a Pixel
      int redValue = pixelObj.getRed();
      int greenValue = pixelObj.getGreen();
      int blueValue = pixelObj.getBlue();
      
      //The following code sets the values of Red, Green, and Blue of a pixel to be negative
      pixelObj.setColor(new Color(255 - redValue, 255 - greenValue, 255 - blueValue));
    } 
  } 
  
  //The following method creates a filter which calls for a picture object and makes it negative grayscale
  public void filterChoice2() 
  {
    Pixel[] pixelArray = this.getPixels();
    for(Pixel pixelObj: pixelArray) 
    { 
      //The following code obtains the values of Red, Green, and Blue at a pixel 
      int redValue = pixelObj.getRed();
      int greenValue = pixelObj.getGreen();
      int blueValue = pixelObj.getBlue();
      
      //The following code changes the values of Red, Green, and Blue of a pixel to be negative
      redValue = 255 - redValue;
      greenValue = 255 - greenValue;
      blueValue = 255 - blueValue;
      
      //The following code changes the values of Red, Green, and Blue of a negative pixel to be gray
      int grayValue = (redValue + greenValue + blueValue) / 3;
      
      //The following code sets the values of Red, Green, and Blue of a pixel to be negative gray
      pixelObj.setColor(new Color(grayValue, grayValue, grayValue));
    } 
  } 
  
  //The following method creates a filter which calls for a picture object and mirros pixels along a vertical line
  public void mirrorFilter()
  { 
    //The following code obtains the witdh of the calling object and half of the width of the calling object
    int width = this.getWidth();
    int mirrorPoint = width / 2;
    
    //The outer for loop loops through all of the rows in the calling object
    for(int y = 0; y < getHeight(); y++) 
    { 
      //The inner loop loops from the column zero to half of the width of the calling object
      for(int x = 0; x < mirrorPoint; x++) 
      { 
        /* The following code obtains the location of the pixel left of the vertical line and the location of it's 
         * mirror pixel right of vertical line */
        Pixel leftPixel = getPixel(x, y);
        Pixel rightPixel = getPixel(width - 1 - x, y);
        
        /* The following codes sets the values of Red, Green, and Blue of the right pixel to match the value of Red,
         * Green, and Blue of the left pixel */
        rightPixel.setColor(leftPixel.getColor());
      } 
    } 
  } 
  
  //The following method allows us to create a collage using three picture objects
  public void collage(Picture p1, Picture p2, Picture p3) 
  { 
    Pixel sourcePixel, targetPixel;
    
    //The following code refers to our x and y position of our calling object
    int targetX = 0;
    int targetY = 0;
    
    /* The following nested loop obtains the values of Red, Green, and Blue of the pixels of the first picture object
     * and sets the values of Red, Green, and Blue of the pixels of the calling object to those of the pixels of the 
     * first picture object with respect to their position */
    for(int sourceX = 0; sourceX < p1.getWidth(); sourceX++) 
    { 
      //The following code sets the y position of our calling object to zero everytime the outer loop is executed
      targetY = 0;
      
      for(int sourceY = 0; sourceY < p1.getHeight(); sourceY++) 
      { 
        sourcePixel = p1.getPixel(sourceX, sourceY);
        targetPixel = this.getPixel(targetX, targetY);
        targetPixel.setColor(sourcePixel.getColor());
        targetY++;
      } 
      targetX++;
    } 
    
    //The following code changes the x position of our calling object to the width of the first picture object
    targetX = p1.getWidth();
    
    /* The following nested loop obtains the values of Red, Green, and Blue of the pixels of the second picture object
     * and sets the values of Red, Green, and Blue of the pixels of the calling object to those of the pixels of the
     * second picture object with respect to their position */
    for(int sourceX = 0; sourceX < p2.getWidth(); sourceX++) 
    { 
      //The following code sets the y position of our calling object to zero everytime the outer loop is executed
      targetY = 0;
      
      for(int sourceY = 0; sourceY < p2.getHeight(); sourceY++) 
      { 
        sourcePixel = p2.getPixel(sourceX, sourceY);
        targetPixel = this.getPixel(targetX, targetY);
        targetPixel.setColor(sourcePixel.getColor());
        targetY++;
      } 
      targetX++;
    } 
    
    /* The following code changes the x position of our calling object to the width of the first picture object plus 
     * the width of the second picture object */
    targetX = p1.getWidth() + p2.getWidth();
    
    /* The following nested loop obtains the values of Red, Green, and Blue of the pixels of the third picture object
     * and sets the values of Red, Green, and Blue of the pixels of the calling object to those of the pixels of the 
     * third picture object with respect to their position */
    for(int sourceX = 0; sourceX < p3.getWidth(); sourceX++) 
    { 
      //The following code sets the y position of our calling object to zero everytime the outer loop is executed
      targetY = 0;
      
      for(int sourceY = 0; sourceY < p3.getHeight(); sourceY++) 
      { 
        sourcePixel = p3.getPixel(sourceX, sourceY);
        targetPixel = this.getPixel(targetX, targetY);
        targetPixel.setColor(sourcePixel.getColor());
        targetY++;
      } 
      targetX++;
    } 
  }
  
  /* The following method allows us to flip a portion of a picture vertically using three parameters: the middle x and y
   * coordinates of the portion and the length of the portion we are flipping */
  public void flipAcrossVertical(int middleX, int middleY, int squareLength) 
  { 
    /* The outer for loop loops through all of the y coordinates in the portion of the picture we are flipping 
     * vertically */
    for(int y = middleY - (squareLength / 2); y < middleY + (squareLength / 2); y++) 
    { 
      /* The primitive variable allows us to determine the x coordinate of our target variable every time the inner for
       * loop finishes executing */
      int target = 1;
      
      /* The inner for loop loops through half of the x coordinates in the portion of the picture we are flipping
       * vertically, from the left most x coordinate to the middle x coordinate of the portion */
      for(int x = middleX - (squareLength / 2); x < middleX; x++) 
      { 
        Pixel leftPixel = getPixel(x, y);
        Pixel rightPixel = getPixel(middleX + (squareLength / 2) - target, y);
        
        //The following code saves the values of Red, Green, and Blue of leftPixel and rightPixel into a variable
        Color left = leftPixel.getColor();
        Color right = rightPixel.getColor();
        
        rightPixel.setColor(left);
        leftPixel.setColor(right);
        target++;
      } 
    } 
  } 
  
  /* The following method allows us to flip a portion of a pictuer horizontally using three parameters: the middle x
   * and y coordinates of the portion and the length of the portion we are flipping */
  public void flipAcrossHorizontal(int middleX, int middleY, int squareLength) 
  { 
    /* The outer for loop loops through all of the x coordinates in the portion of the picture we are flipping
     * horizontally */
    for(int x = middleX - (squareLength / 2); x < middleX + (squareLength / 2); x++) 
    { 
      /* The primitive variable allows us to determine the y coordinate of our target variable every time the inner for
       * loop finishes executing */
      int target = 1;
      
      /* The inner for loop loops through half of the y coordinates in the portion of the picture we are flipping
       * horizontally, from the top most y coordinate to the middle y coordinate of the portion */
      for(int y = middleY - (squareLength / 2); y < middleY; y++) 
      { 
        Pixel topPixel = getPixel(x, y);
        Pixel bottomPixel = getPixel(x, middleY + (squareLength / 2) - target);
        
        //The following code saves the values of Red, Green, and Blue of topPixel and bottomPixel into a variable
        Color top = topPixel.getColor();
        Color bottom = bottomPixel.getColor();
        
        bottomPixel.setColor(top);
        topPixel.setColor(bottom);
        target++;
      } 
    } 
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
 
