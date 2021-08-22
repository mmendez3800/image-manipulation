/*
This program will take a hidden image created by "HiddenImage.java"

It will then extrapolate the 2 least significant bits of the image to display the hidden image
*/

public class ShowHiddenImage
{ 
    public static void main (String[] args) 
    { 
        // This is the main method to recover a picture hidden within the 2 least significant bits of another picture
        // The "picture_with_hidden_image.bmp" file can be used to show how the function operates if one has not been created yet
        String contextPic = FileChooser.pickAFile();
        Picture context = new Picture(contextPic);
        context.show();
        Picture hiddenMessage = context.recoverSecretMessage2Bits(context);
        hiddenMessage.show();
    } 
}
