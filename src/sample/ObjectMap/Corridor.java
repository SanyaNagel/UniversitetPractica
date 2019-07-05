package sample.ObjectMap;

import javafx.scene.paint.Color;
import sample.Peoples.People;

import java.util.ArrayList;

public class Corridor {
    public Corridor(int x, int y, int width, int height){
        coordX = x;
        coordY = y;
        _width = width;
        _height = height;
        mapCorridor = new int[width][height];
        for(int i = 0; i < width; i++)
            for(int j = 0; j < height; j++)
                if( (i == 0 || j == 0) || (i == _width-1 || j == _height-1))
                    mapCorridor[i][j] = 1;
                else
                    mapCorridor[i][j] = 0;
    }

    public Corridor(int x, int y, int width, int height, Corridor addCorridor){
        _listCorridor.add(addCorridor);
        mapCorridor = new int[width][height];
        coordY = y; coordX = x;
    }

    //Присоединяем коридор
    public void setCorridor(Corridor addCorridor) {
        _listCorridor.add(addCorridor);
    }

    //Добавляем объекты в коридор и закрашиваем эти места
    public void setSell(Sell sell){
        for(int i = sell.getCoordX(); i < sell.getWidth()+sell.getCoordX(); i++)
            for(int j = sell.getCoordY(); j < sell.getCoordY()+sell.getHeight(); j++)
                mapCorridor[i-coordX][j-coordY] = 4;

            _listObject.add(sell);
    }

    public void setPrint(Print print){
        for(int i = print.getCoordX(); i < print.getWidth()+print.getCoordX(); i++)
            for(int j = print.getCoordY(); j < print.getCoordY()+print.getHeight(); j++)
                mapCorridor[i-coordX][j-coordY] = 2;

            _listObject.add(print);
    }
    
    //Добавляем человека в коридор
    public void setPeople(People people){

        for(int i = 0; i < get_width(); i++)
            for(int j = 0; j < get_height(); j++)
                if((mapCorridor[i][j] == 5) && (allowCoordPeople(i,j) == true))
                    people.setCoordinate(i,j);

        System.out.println("���������� ������");
        _listPeople.add(people);
        paintPeople(people);
    }
    private boolean allowCoordPeople(int x, int y) {
        for(People people: _listPeople)
            if(people.getX() == x && people.getY() == y)
                return false;
        return true;
    }

    public void paintPeople(People people) {
    	if(people.notDrawing == this)
    		return;
    	if(mapCorridor[people.getX()][people.getY()] != 5)
        	mapCorridor[people.getX()][people.getY()] = 6;
	}
    public void paintNullPeople(People people) {
    	if(people.notDrawing == this)
    		return;
    	if(mapCorridor[people.getX()][people.getY()] != 5)
    		mapCorridor[people.getX()][people.getY()] = 0;
	}
    //Обновление каждую секунду
    public void updateTick(){
    	ArrayList<People> peopleDelet = new ArrayList<People>();
    	for(People people: _listPeople){
            boolean next = false;
        	if(people.come == true) {
	        	if(people.getX() < get_width()-1){                  //Если мы не дошли до конца коридора, то
	            	paintNullPeople(people);
	            	people.setCoordinate(people.getX() + people.getSpeed());   //Перемещаем человека на один вперёд
	            	System.out.println("���������");
	            }
	            else{   //Переходим на следующий коридор
	                for (Corridor corridor: _listCorridor) {
	                	if (people.getNextPath() == corridor) {  //Определяем коридор
	                        people.setCoordinate(0, 1);//people.getY()); //Отправляем координаты человеку в коридоре
	                        corridor.setPeople(people);    //Отправляем в него человека
	                    }
	                }
	                people.nextCorridor();  //Удаляем текущий коридор, который должны пройти
	                people.notDrawing = this;
	                peopleDelet.add(people); //Удаляем человека с этого коридора, так как он перешёл в следующий
	            }
	        	paintPeople(people);	//���������� �� �����������
	        	System.out.println("�����������");
        	
        	}
        }
    	for(People people: peopleDelet)
    	{
    		people.notDrawing = null;
    		_listPeople.remove(people); //Удаляем человека с этого коридора, так как он перешёл в следующий
    	}
    	peopleDelet.clear();
        
    }

    public int getDegree(){
        return degree;
    }

    public boolean getIsCheked(){
        return isChecked;
    }

    public int setDegree(int degree) {
        if(degree != -1) {
            if (degree < this.degree)
                this.degree = degree;
        }
        else{
            this.degree = degree;
        }
        return degree;
    }

    //Функция для поиска минимального маршрута
    public void recirseDeicstra(int lngthPath, Corridor prev){
        this.isChecked = true;  //Помечаем как пройденную

        Corridor minDegree = null;
        for(Corridor corridor: _listCorridor) {
            if(corridor != prev) {
                minDegree = corridor;  //Получаем первый элемент для сравнения
                break;
            }
        }
        if(minDegree == null) return;   //Если все узлы кончились то выходим

        //Устанавливаем степени вершин и находим минимальную вершину
        for(Corridor corridor: _listCorridor){
            if(corridor != prev && corridor.isChecked == false && minDegree.getDegree() > corridor.setDegree(lngthPath+ get_width()));
                minDegree = corridor;
        }

        //Переходим в неё
        minDegree.recirseDeicstra(degree+ get_width(),this);
    }

    public Corridor readyPath(int sum){
        for(Corridor corridor: _listCorridor){
            if(sum - get_width() == corridor.getDegree()){
                return corridor;
            }
        }
        return null;
    }
    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    
    //Проверка, пренадлежат ли координаты этому коридору + влазит ли?
    public boolean belongsCoord(ObjectMap objectMap){
        if(coordX+ get_width() >= objectMap.getCoordX()+objectMap.getWidth() && coordY+ get_height() >= objectMap.getCoordY()+objectMap.getHeight())
            return true;
        else
            return false;
    }
    public boolean belongsCoordC(int x, int y, int widthC, int heightC){
        if(coordX+ get_width() >= x+widthC && coordY+ get_height() >= y+heightC)
            return true;
        else
            return false;
    }

    //Если координаты прендалежат коридору
    public boolean belongsCoord(int x, int y, int width, int height){
        if(coordX+ get_width() >= x+width && coordY+ get_height() >= y+height)
            return true;
        else
            return false;
    }
    public void setInput(int x, int y, int width, int height){
        for(int i = x-coordX; i < width; i++)
            for(int j = y-coordY; j <= height; j++)
                mapCorridor[i][j] = 5;
            input = true;
    }
    public int get_width() {
        return _width;
    }

    public int get_height() {
        return _height;
    }

    public int[][] getMapCorridor(){
        return mapCorridor;
    }

    private int[][] mapCorridor;    //Карта коридора состоит из клеток - битовая матрица
    private ArrayList<Corridor> _listCorridor = new ArrayList<Corridor>();

    private ArrayList<Object> _listObject = new ArrayList<Object>();           //Столовые, аудитории, печати
    private ArrayList<People> _listPeople = new ArrayList<People>();           //Люди находящиеся в коридорах
    private int _width;
    private int _height;
    private int coordX;
    private int coordY;
    public boolean input = false;

    //Переменные для алгоритма дейкстры
    private int degree = -1;
    private boolean isChecked = false;



}
