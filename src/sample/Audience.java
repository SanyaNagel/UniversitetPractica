package sample;

import java.util.ArrayList;

public class Audience extends Object {

    Audience(int width, int height, int x, int y){
        _width = width;     //Длина аудитории
        _height = height;
        _coordinate[0] = x;  //Начальные точки
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

    public void setPeople(People people){
        people.setCoordinate(0,0);
        _peopleList.add(people);
    }

    private int[] _coordinate = new int[2]; //координаты объекта в коридоре x - coordinate[0], y - coordinate[1]
    private int _width;
    private int _height;
    private ArrayList<People> _peopleList = new ArrayList<People>();

}
