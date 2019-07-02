package sample;

public class Sell extends Object {
    //Ширина высота и координаты
    Sell(int width, int height, int x, int y){
        _width = width;
        _height = height;
        _coordinate[0] = x;
        _coordinate[1] = y;
    }

    @Override
    public int[] getCoordinate(){
        return _coordinate ;
    }

    @Override
    public int getWidth(){
        return _width;
    }
    @Override
    public int getHeight(){
        return _height;
    }


    private int[] _coordinate = new int[2]; //координаты объекта в коридоре x - coordinate[0], y - coordinate[1]
    private int _width;
    private int _height;
}
