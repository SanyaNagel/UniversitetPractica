package sample.ObjectMap;

import sample.ObjectMap.ObjectMap;

import sample.Peoples.People;

import java.util.ArrayList;

public class Corridor {
    public Corridor(int x, int y, int width, int height){
        coordX = x;
        coordY = y;
        mapCorridor = new int[width][height];
        for(int i = 0; i < width; i++)
            for(int j = 0; j < height; j++)
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
    public void setObject(ObjectMap object){
        for(int i = object.getCoordX(); i < object.getWidth()+object.getCoordX(); i++)
            for(int j = object.getCoordY(); j < object.getCoordY()+object.getHeight(); j++)
                mapCorridor[i-coordX][j-coordY] = 1;

            _listObject.add(object);
    }

    //Добавляем человека в коридор
    public void setPeople(People people){
        people.setCoordinate(0,allowCoordinate(0));
        _listPeople.add(people);
    }

    //Обновление каждую секунду
    public void updateTick(){
        for(People people: _listPeople){
            int[] coord = people.getCoordinate();   //Получаем координаты текущего человека
            if(coord[0] < _width){                  //Если мы не дошли до конца коридора, то
                people.setCoordinate(coord[0] + people.getSpeed());   //Перемещаем человека на один вперёд
            }
            else{   //Переходим на следующий коридор
                for (Corridor corridor: _listCorridor) {

                    if (people.getNextPath() == corridor) {  //Определяем коридор
                        corridor.setPeople(people);    //Отправляем в него человека
                        people.setCoordinate(0, corridor.allowCoordinate(0)); //Отправляем координаты человеку в коридоре
                    }
                }
                people.nextCorridor();  //Удаляем текущий коридор, который должны пройти
                _listPeople.remove(people); //Удаляем человека с этого коридора, так как он перешёл в следующий
            }
        }
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
            if(corridor != prev && corridor.isChecked == false && minDegree.getDegree() > corridor.setDegree(lngthPath+_width));
                minDegree = corridor;
        }

        //Переходим в неё
        minDegree.recirseDeicstra(degree+_width,this);
    }

    public Corridor readyPath(int sum){
        for(Corridor corridor: _listCorridor){
            if(sum - _width == corridor.getDegree()){
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

    //Поиск свободной координаты Y по линии X
    private int allowCoordinate(int x){
        for(int i = 0; i < _height; i++)
            if(mapCorridor[x][i] == 0) return i;
        return -1;  //Если всё занято
    }

    //Проверка, пренадлежат ли координаты этому коридору + влазит ли?
    public boolean belongsCoord(ObjectMap objectMap){
        if(coordX+_width >= objectMap.getCoordX()+objectMap.getWidth() && coordY+_height >= objectMap.getCoordY()+objectMap.getHeight())
            return true;
        else
            return false;
    }

    private int[][] mapCorridor;    //Карта коридора состоит из клеток - битовая матрица
    private ArrayList<Corridor> _listCorridor = new ArrayList<Corridor>();

    private ArrayList<Object> _listObject = new ArrayList<Object>();           //Столовые, аудитории, печати
    private ArrayList<People> _listPeople = new ArrayList<People>();           //Люди находящиеся в коридорах
    private int _width;
    private int _height;
    private int coordX;
    private int coordY;
    //Переменные для алгоритма дейкстры
    private int degree = -1;
    private boolean isChecked = false;


}
