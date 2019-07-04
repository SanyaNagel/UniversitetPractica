package sample.ObjectMap;

public class Print extends ObjectMap {
    public Print(int x, int y, int width, int height){
        _width = width;
        _height = height;
        coordX= x;
        coordY = y;
    }

    @Override
    public int getCoordX() {
        return coordX;
    }
    @Override
    public int getCoordY() {
        return coordY;
    }


    @Override
    public int getWidth(){
        return _width;
    }
    @Override
    public int getHeight(){
        return _height;
    }

     private int _width;
    private int _height;
    private int coordX;
    private int coordY;
}
