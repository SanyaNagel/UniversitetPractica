package sample.ObjectMap;

public class Sell extends ObjectMap {
    //Ширина высота и координаты
    public Sell(int x, int y,int width, int height){
        _width = width;
        _height = height;
        coordX = x;
        coordY = y;
    }

    @Override
    public int getWidth(){
        return _width;
    }
    @Override
    public int getHeight(){
        return _height;
    }
    @Override
    public int getCoordX() {
        return coordX;
    }
    @Override
    public int getCoordY() {
        return coordY;
    }

    private int[] _coordinate = new int[2]; //координаты объекта в коридоре x - coordinate[0], y - coordinate[1]
    private int _width;
    private int _height;
    private int coordX;
    private int coordY;
}
