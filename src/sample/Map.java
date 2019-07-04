package sample;

import javafx.scene.control.Alert;
import sample.ObjectMap.Audience;
//import sample.ObjectMap.Corridor;
//import sample.ObjectMap.Print;
import sample.ObjectMap.Sell;
import sample.Peoples.People;

import java.util.ArrayList;

import java.io.*;
import java.util.*;

public class Map {

    public Map() {
    	mapUniver = new int[width][height];
    	for(int i = 0; i < width; i++)
    		for(int j = 0; j < height; j++)
    			mapUniver[i][j] = 0;
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
    
    public void setCorridor(Corridor corridor) {
    	_corridorsList.add(corridor);
    }
    public void setAudince(Audience audince) {
        _audinceList.add(audince);
    }
    public void setPrint(Print print) {
        boolean find = false;
        for(Corridor corridor: _corridorsList){
            if(corridor.belongsCoord(print) == true){
                corridor.setObject(print);
                find = true;
                break;
            }
        }
        if(find == false){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("��������������");
            alert.setContentText("�� ����������� �� ������ ��������");
            alert.show();
        }
    }
    public void setSell(Sell sell) {
        boolean find = false;
        for(Corridor corridor: _corridorsList){
            if(corridor.belongsCoord(sell) == true){
                corridor.setObject(sell);
                find = true;
                break;
            }
        }
        if(find == false){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("��������������");
            alert.setContentText("�� ����������� �� ������ ��������");
            alert.show();
        }
    }

    // ��������� �� �����
    public void loadFromFile(File file){

       /*// ������ �����������
        int c;
        while((c=file.read())!=-1){
                 
            	System.out.print((char)c);
        	} 
    	  */
    }
    
    public void saveToFile(File file){
    	//try(FileWriter writer = new FileWriter("notes3.txt", false))
        
    	/*for(Corridor corridor: _corridorsList) {
    		corridor.
    	}
           // ������ ���� ������
            String text = "Hello Gold!";
            file.write(text);
            // ������ �� ��������
            file.append('\n');
            file.append('E');
             
            file.flush();*/
        
    }
    		
    public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	private int width = 30;
    private int height = 30;
    private int[][] mapUniver;
    private ArrayList<Corridor> _corridorsList = new ArrayList<Corridor>();
    private ArrayList<Audience> _audinceList = new ArrayList<Audience>();
}
