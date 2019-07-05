package sample.ObjectMap;

import sample.Peoples.People;

import java.util.ArrayList;

public class Audience extends ObjectMap {

    public Audience(int x, int y,int width, int height){
        _width = width;     //Длина аудитории
        _height = height;
        coordX = x;  //Начальные точки
        coordY = y;
        mapAudience =  new int[width][height];
        for(int i = 0; i < width; i++)
        {    for(int j = 0; j < height; j++)
            {    if( (i == 0 || j == 0) || (i == _width-1 || j == _height-1))
            		mapAudience[i][j] = 3;
                else
                	mapAudience[i][j] = 0;
            }
        }
    }

    public void updateTick(){
    	for(People people: _listPeople){
    		people.updateTick();
    	}
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

    public void setPeople(People people){
        people.setCoordinate(0,0);
        _listPeople.add(people);
    }

    public int[][] getMapAudience() {
        return mapAudience;
    }

    private int _width;
    private int _height;
    private int coordX;
    private int coordY;
    private ArrayList<People> _listPeople = new ArrayList<People>();
    private int[][] mapAudience;    //Карта коридора состоит из клеток - битовая матрица
}
