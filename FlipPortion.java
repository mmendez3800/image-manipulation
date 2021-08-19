/*
This program allows you to flip portions of an image either horizontally or vertically based on user input
*/

import java.io.*; 
public class FlipPortion
{ 
  public static void main(String[] args)throws IOException 
  { 
    // Choosing a picture and initializing variables
    Picture pic = new Picture(FileChooser.pickAFile()); 
    int x, y, size; 
    pic.show(); 
    int width = pic.getWidth(); 
    int height = pic.getHeight(); 
    System.out.println("Picture loaded - width: " + pic.getWidth()+" height: "+ pic.getHeight());

    // This for loop executes flipAcrossHorizontal three times 
    for(int loop = 0; loop < 3; loop++)
    { 
      // Prompting the user for coordinates 
      String prompt = "Please enter a position (first x then press enter, then y and press enter) in the "; 
      String prompt2 = "picture to flip horizontally."; 
      System.out.println(prompt + prompt2);

      // Converting coordinates to ints
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
      x = (new Integer(br.readLine())).intValue();
      y = (new Integer(br.readLine())).intValue(); 

      // Prompting the user for size
      System.out.println("Please enter the size of the box to flip."); 
      System.out.println("Ensure that the size of the box does not exceed the boundaries of the image");
      size = (new Integer(br.readLine())).intValue(); 

      // The following code calls our flip horizontal method 
      pic.flipAcrossHorizontal(x, y, size);
      
      // Repainting the picture with the horizontal flips completed
      pic.repaint(); 
    } 

    // This for loop executes flipAcrossVertical three times
    for(int loop = 0; loop < 3; loop++)
    { 
      // Prompting the user for coordinates 
      String prompt = "Please enter a position (first x then press enter, then y and press enter) in the "; 
      String prompt2 = "picture to flip vertically."; 
      System.out.println(prompt + prompt2);

      // Converting coordinates to ints
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
      x = (new Integer(br.readLine())).intValue();
      y = (new Integer(br.readLine())).intValue(); 

      // Prompting the user for size
      System.out.println("Please enter the size of the box to flip."); 
      System.out.println("Ensure that the size of the box does not exceed the boundaries of the image");
      size = (new Integer(br.readLine())).intValue(); 

      // The following code calls our flip vertical method
      pic.flipAcrossVertical(x, y, size);
      
      // Repainting the picture with the vertical flips completed
      pic.repaint(); 
    } 
  } 
} 