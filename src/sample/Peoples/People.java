package sample.Peoples;

import sample.ObjectMap.Corridor;

import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.BoldAction;

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
        this.x = x;
        this.y = y;
    }

    public int getX() {return x;}
    public int getY() {return y;}
    
    public void setCoordinate(int x){
        this.x = x;
    }

    //Обновление каждую секунду
    public void updateTick()
    {

    }

    //Задаём маршрут
    public void setRoute(ArrayList<Corridor> route){
        _route = route;
        come = true;
    }

    public Corridor getNextPath(){
        _currentCorridor = _route.get(0);
        return _route.get(0);
    }

    public void nextCorridor(){
    	if(_route.size() > 0)
    		_route.remove(0);
    }

    Corridor getCurrentCorridor(){
        return _currentCorridor;
    }
    
    public Corridor notDrawing = null;
    public boolean come = false;
    private int _speed = 1;         //Скорость
    private int _expectation;   //Время ожидания
    private Object _purpose;   //Цели до которых нужно дойти
    private int x;
    private int y;
    private ArrayList<Corridor> _route;  //Назначеный путь
    private Corridor _currentCorridor;  //Коридор в котором находится человек
}
