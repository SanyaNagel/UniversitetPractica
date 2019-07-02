package sample;

public class Print extends Object {
    public Print(int width, int height, int x, int y){
        _width = width;
        _height = height;
        coordinate[0] = x;
        coordinate[1] = y;
    }

    @Override
    public int[] getCoordinate(){
        return coordinate ;
    }
    @Override
    public int getWidth(){
        return _width;
    }
    @Override
    public int getHeight(){
        return _height;
    }

    private int[] coordinate = new int[2]; //координаты объекта в коридоре x - coordinate[0], y - coordinate[1]
    private int _width;
    private int _height;
}
