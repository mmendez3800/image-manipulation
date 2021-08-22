/*
This program will take two pictures chosen by the user

The second image will be hidden into the 2 lease significant bits of the first picture

The resulting image will be the second image hidden in the first image

To display the hidden image, this can be done within "ShowHiddenImage.java"
*/

public class HiddenImage
{ 
    public static void main (String[] args) 
    { 
      // This is the main method to hide a picture into the 2 least significant bits of another picture

      // First image is selected and shown to the user
      String contextPic = FileChooser.pickAFile();
      Picture context = new Picture(contextPic);
      context.show();

      // Second image is selected and shown to the user
      String messagePic = FileChooser.pickAFile();
      Picture message = new Picture(messagePic);
      message.show();

      // New picture is produced where the second image is hidden within the 2 lease significant bits of the first image
      Picture myPicWithMessage = context.hideSecretMessage2Bits(context, message);
      myPicWithMessage.show();
      myPicWithMessage.write(System.getProperty("user.dir") + "/hidden_message.bmp");
    } 
}
