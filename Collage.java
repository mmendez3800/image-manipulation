/*
This program creates a collage of two separate pictures
*/

public class Collage
{
  public static void main(String[] args)
  { 
    Picture collage = new Picture(1300, 800);

    // The two pictures being referenced for the collage
    Picture input1 = new Picture("smash.jpg");
    Picture input2 = new Picture("bioshock.jpg");
    
    collage.createCollage(input1, input2);
    collage.show();
  }
}
