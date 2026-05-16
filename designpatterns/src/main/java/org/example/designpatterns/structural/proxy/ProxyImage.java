package org.example.designpatterns.structural.proxy;

public class ProxyImage implements Image {

    private String filename;
    private Realimage realimage; // reference to the real image

    public ProxyImage(String filename){
        this.filename = filename;
    }

    @Override
    public void display() {

        // now we need the image, if it is already loaded, do not load it again
        if(realimage == null){
            realimage = new Realimage(filename);
        }
        realimage.display();
    }
}
