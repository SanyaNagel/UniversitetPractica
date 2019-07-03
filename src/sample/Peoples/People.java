package sample.Peoples;

import sample.ObjectMap.Corridor;

import java.util.ArrayList;

public class People {
    //Задаём цель
    public void setPurpose(Object purpose){
        _purpose = purpose;
    }

    //Время ожидания
    void setExpectation(int time)
    {

    }

    //Задаём скорость
    void setSpeed(int speed)
    {
        _speed = speed;
    }

    public int getSpeed(){
        return _speed;
    }
    public void setCoordinate(int x, int y){
        _coordinate[0] = x;
        _coordinate[1] = y;
    }

    public void setCoordinate(int x){
        _coordinate[0] = x;
    }

    //Обновление каждую секунду
    void updateTick()
    {

    }

    //Получаем координаты
    public int[] getCoordinate(){
        return _coordinate;
    }

    //Задаём маршрут
    void setRoute(ArrayList<Corridor> route){
        _route = route;
    }

    public Corridor getNextPath(){
        _currentCorridor = _route.get(0);
        return _route.get(0);
    }

    public void nextCorridor(){
        _route.remove(0);
    }

    Corridor getCurrentCorridor(){
        return _currentCorridor;
    }

    private int _speed;         //Скорость
    private int _expectation;   //Время ожидания
    private Object _purpose;   //Цели до которых нужно дойти
    private int[] _coordinate = new int[2]; //координаты объекта в коридоре x - coordinate[0], y - coordinate[1]
    private ArrayList<Corridor> _route;  //Назначеный путь
    private Corridor _currentCorridor;  //Коридор в котором находится человек
}
