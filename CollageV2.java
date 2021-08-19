/*
This program creates a collage of three pictures based on a single image selected by the user
*/

public class CollageV2
{ 
  public static void main (String[] args) 
  { 
    // The following code chooses a picture and copies it twice
    Picture original = new Picture(FileChooser.pickAFile());
    Picture copy1 = new Picture(original);
    Picture copy2 = new Picture(original);
    
    // The following code shows the original picture
    original.show();

    // The following code creates a new picture that is the same height as our original picture but three times as wide
    Picture pictObj = new Picture (original.getWidth() * 3, original.getHeight());
    
    // The following code calls the filter methods, one for the original picture and the other two for the copies
    original.filterChoice1();
    copy1.filterChoice2();
    copy2.mirrorFilter();
    
    // The following code calls the collage method which sends our three altered pictures to our new picture
    pictObj.collage(original, copy1, copy2);

    // The following displays our collage of images and saves it as a file in our working directory
    pictObj.show();
    pictObj.write(System.getProperty("user.dir") + "/collage.jpg");
  }  
} 
