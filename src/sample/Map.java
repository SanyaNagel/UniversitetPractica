package sample;

import sample.ObjectMap.Audience;
import sample.ObjectMap.Corridor;
import sample.Peoples.People;

import java.util.ArrayList;

public class Map {

    public Map() {
        Cell rect = new Cell();


        Corridor corridor1 = new Corridor(5, 20);
        Corridor corridor2 = new Corridor(3, 3, corridor1);
        Audience audience1 = new Audience(2, 2, 3, 7);    //Устанавливаем координаты расположения в коридоре

        People people1 = new People();
        corridor1.setPeople(new People());
        corridor1.setPeople(new People());
        corridor1.setPeople(new People());

        corridor2.setObject(audience1, 1, 1);                    //Добавляем в коридор
        audience1.setPeople(new People());
        Corridor corridor = new Corridor(3,4);

        people1.setPurpose(audience1);                    //Отправляем человека туда
        //people1.setRoute(generationPath(people1.getCurrentCorridor(), corridor));   //Задаём путь человеку
    }

    //Поиск пути через алгоритм дейкстры
    ArrayList<Corridor> generationPath(Corridor c1, Corridor c2){
        c1.recirseDeicstra(0,null);   //Устанавливаем степени
        ArrayList<Corridor> listPatch = new ArrayList<Corridor>();
        int sum = c2.getDegree();   //Получаем длину пути
        while(c2 != null){
            c2 = c2.readyPath(sum);
            if(c2 != null)
                listPatch.add(c2);
        }
        return listPatch;
    }

    //Запускается таймером каждую секунду
    public void update(){
        for(Corridor corridor: _corridorsList){
            corridor.updateTick();
        }
    }

    private ArrayList<Corridor> _corridorsList = new ArrayList<Corridor>();
}
