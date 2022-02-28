package eu.ivan.colorservice.service;

public class Color {
    private String webColor;

    

    public Color() {
    }

    

    public Color(String color) {
        this.webColor = color;
    }



    public String getwebColor() {
        return webColor;
    }

    public void setWebColor(String color) {
        this.webColor = color;
    }

    @Override
    public String toString() {
        return "Color [color=" + webColor + "]";
    }

    


}
