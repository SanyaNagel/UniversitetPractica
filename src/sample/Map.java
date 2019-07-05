package sample;

import javafx.scene.control.Alert;
import sample.ObjectMap.Audience;
import sample.ObjectMap.Corridor;
import sample.ObjectMap.Print;
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

    public void deicstraWay(int x, int y, int x2, int y2) {
    	int i = 0;
    	Corridor[] arrCor = new Corridor[2];
    	for(Corridor corridor: _corridorsList) {
    		if(corridor.getCoordX()+corridor.get_width() >= x && corridor.getCoordY()+corridor.get_height() >= y &&
    				corridor.getCoordX() <= x && corridor.getCoordY() <= y) {
    			arrCor[i++] = corridor;
    		}
    		if(corridor.getCoordX()+corridor.get_width() >= x2 && corridor.getCoordY()+corridor.get_height() >= y2&&
    				corridor.getCoordX() <= x && corridor.getCoordY() <= y) {
    			arrCor[i] = corridor;
    		}
    	}
    	ArrayList<Corridor> buf = generationPath(arrCor[0], arrCor[1]);
    	for(Group grop: _listGroup) {
    		grop.setWey(buf);
    	}
    	
    }
    
    //РџРѕРёСЃРє РїСѓС‚Рё С‡РµСЂРµР· Р°Р»РіРѕСЂРёС‚Рј РґРµР№РєСЃС‚СЂС‹
    public ArrayList<Corridor> generationPath(Corridor c1, Corridor c2){
        c1.recirseDeicstra(0,null);   //РЈСЃС‚Р°РЅР°РІР»РёРІР°РµРј СЃС‚РµРїРµРЅРё
        ArrayList<Corridor> listPatch = new ArrayList<Corridor>();
        int sum = c2.getDegree();   //РџРѕР»СѓС‡Р°РµРј РґР»РёРЅСѓ РїСѓС‚Рё
        while(c2 != null){
            c2 = c2.readyPath(sum);
            if(c2 != null)
                listPatch.add(c2);
        }
        return listPatch;
    }

    //Р—Р°РїСѓСЃРєР°РµС‚СЃСЏ С‚Р°Р№РјРµСЂРѕРј РєР°Р¶РґСѓСЋ СЃРµРєСѓРЅРґСѓ
    public void update(){
        for(Corridor corridor: get_corridorsList()){
            corridor.updateTick();
        }
        for(Audience audience: get_audinceList()){
            audience.updateTick();
        }
    }
    
    
    
    public void setCorridor(Corridor corridor) {
    	for(Corridor corridor1: _corridorsList){
    	    if(corridor1 != corridor){
    	        if(corridor.belongsCoordC(corridor1.getCoordX(),corridor1.getCoordY(), corridor1.get_width(), corridor1.get_height())){
                    corridor.setCorridor(corridor1);
                }
            }
        }
    	get_corridorsList().add(corridor);
    }
    public void setAudince(Audience audince) {
        get_audinceList().add(audince);
    }
    public void setPrint(Print print) {
        boolean find = false;
        for(Corridor corridor: get_corridorsList()){
            if(corridor.belongsCoord(print)){
                corridor.setPrint(print);
                find = true;
                break;
            }
        }
        if(find == false){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение");
            alert.setContentText("Не принадлежит ни одному коридору");
            alert.show();
        }
    }
    public void setInput(int x,int y, int width, int height){
        boolean find = false;
        for(Corridor corridor: get_corridorsList()){
            if(corridor.belongsCoord(x,y,width,height) == true){
                corridor.setInput(x,y,width,height);
                find = true;
                break;
            }
        }
        if(find == false){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение");
            alert.setContentText("Сдесь нельзя расположить вход!");
            alert.show();
        }
    }
    public void setSell(Sell sell) {
        boolean find = false;
        for(Corridor corridor: get_corridorsList()){
            if(corridor.belongsCoord(sell) == true){
                corridor.setSell(sell);
                find = true;
                break;
            }
        }
        if(find == false){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение");
            alert.setContentText("Не принадлежит ни одному коридору");
            alert.show();
        }
    }

    // Загрузить из файла
    public void loadFromFile(File file){

       /*// читаем посимвольно
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
           // запись всей строки
            String text = "Hello Gold!";
            file.write(text);
            // запись по символам
            file.append('\n');
            file.append('E');
             
            file.flush();*/
        
    }
    public void creatPiople(People peope){
    	System.out.println("Добавляем студента в коридор");
        boolean yes = false;    //Если есть кориидор со входом
        for(Corridor corridor: _corridorsList){
            if(corridor.input == true)  //Если в коридоре есть вход
            {
            	corridor.setPeople(peope);
                yes = true;
                break;
            }
        }
        if(yes == false)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение");
            alert.setContentText("Нет входа для людей. Человек погиб");
            alert.show();
            return;
        }
        //Random rnd = new Random(System.currentTimeMillis());
        //Corridor buf = _corridorsList.get(rnd.nextInt(_corridorsList.size()));
        //if(buf.input == false)
          //  creatPiople(peope);
        //else
            //buf.setPeople(peope);
    }

    public int getWidth() {
		return width;
	}
    public int getHeight() {
		return height;
	}
    public void setSizeMap(int widthMap, int heightMap){
        width = widthMap;
        height = heightMap;
    }

	public ArrayList<Corridor> get_corridorsList() {
		return _corridorsList;
	}

	public ArrayList<Audience> get_audinceList() {
		return _audinceList;
	}

	public void setGroop(ArrayList<Group> listGroup) {
		_listGroup = listGroup;
	}

    private ArrayList<Group> _listGroup = new ArrayList<Group>();
	private int width = 60;
    private int height = 60;
    private int[][] mapUniver;
    private ArrayList<Corridor> _corridorsList = new ArrayList<Corridor>();
    private ArrayList<Audience> _audinceList = new ArrayList<Audience>();
}
