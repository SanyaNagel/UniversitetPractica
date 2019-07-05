package sample;

import sample.ObjectMap.Corridor;
import sample.Peoples.People;
import sample.Peoples.Student;

import java.util.ArrayList;

public class Group {
    public Group(int numberStudent){
        _nameGroup = ++nameGroup;
        for(int i = 0; i < numberStudent; i++)
            _listStudent.add(new Student());
        creatStudent = _listStudent.size();
    }

    public void setWey(ArrayList<Corridor> way) {
    	for(Student student: _listStudent) {
    		student.setRoute(way);
    	}
    }
    
    public void setPeople(Student student){
        _listStudent.add(student);
        creatStudent = _listStudent.size();
    }
    public Student popStudent(){
        if(creatStudent > 0)
            return _listStudent.get(--creatStudent);
        else
            return null;
    }
    int creatStudent;
    static int nameGroup = 0;
    private int _nameGroup;
    private ArrayList<Student> _listStudent = new ArrayList<Student>();
}
