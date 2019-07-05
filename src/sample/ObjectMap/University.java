package sample.ObjectMap;

import sample.Group;
import sample.Map;
import sample.Peoples.People;
import sample.Peoples.Student;
import sample.Peoples.Teacher;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class University {

    public University(){
        map = new Map();
        univer = this;
        
    }

    public static University getUniver() {
    	return univer;
    }
    
    public void start(){
        Timer timerCreate = new Timer();
        timerCreate.schedule(new TimerTask() {
            @Override
            public void run() {
            	Student stud = getStudent();
                if(stud == null)
                    timerCreate.cancel();
                else
                    map.creatPiople(stud);
            }
        }, 0, intervalCreate);
    }

    public void createGroup(int numberStudent){
        _listGroup.add(new Group(numberStudent));
        map.setGroop(_listGroup);
        ++createGroop;
    }
    public  void createTeacher(int numberTeacher){
        for(int i = 0; i < numberTeacher; i++)
            _listTeacher.add(new Teacher());
        ++createTeacher;
    }

    public void setIntervalCreate(int intervalCreate) {
        this.intervalCreate = intervalCreate;
    }

    public Student getStudent(){
        if(_listGroup.size() == 0) {
        	System.out.println("Ноль групп");
            return null;
        }
        
        Student buf = _listGroup.get(createGroop-1).popStudent();
        if(buf == null)
        {
        	System.out.println("Группы закончились");
            _listGroup.remove(createGroop-1);
            --createGroop;
            return getStudent();
        }
        else{
        	System.out.println("Нашли студента");
            return buf;
        }
    }

    public Map getMap() {return map;}

    private ArrayList<Group> _listGroup = new ArrayList<Group>();
    private ArrayList<Teacher> _listTeacher = new ArrayList<Teacher>();
    private Map map;
    private int intervalCreate = 0;
    private int createGroop = 0;
    private int createTeacher = 0;
    private static University univer;
}
