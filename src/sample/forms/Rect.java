package sample.forms;

import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Rect extends Rectangle {
	public Rect(int x, int y, int width, int height, int cordMapX, int cordMapY){
	    super(x,y,width,height);
	    this.x = cordMapX;
	    this.y = cordMapY;
        this.setOnMouseClicked(event->{setCoord();});
	}

	static void setTextField(TextField coordX,TextField coordY,TextField widthFild,TextField heightFild){
        _coordX = coordX; _coordY = coordY;
        _widthFild = widthFild; _heightFild = heightFild;
    }

    void setCoord(){
    	if(colorer == false) {
            this.setFill(Color.RED);
            colorer = true;
    	}
    	else {
    		colorer = false;
    		this.setFill(Color.TRANSPARENT);
    		//this.setStroke(Color.BLACK);
    	}
    	if(xw == false) {
            _coordX.setText(String.valueOf(x));
            _coordY.setText(String.valueOf(y));
            xw = true;
        }
	    else{
	    	int width = x+1- Integer.parseInt(_coordX.getText());
            int height = y+1- Integer.parseInt(_coordY.getText());
            
            if(width < 0 || height < 0) {
            	int ptewX = Integer.parseInt(_coordX.getText());
            	int prevY = Integer.parseInt(_coordY.getText());
            	
            	_coordX.setText(String.valueOf(x));
                _coordY.setText(String.valueOf(y));
            	

    	    	width = ptewX+1- Integer.parseInt(_coordX.getText());
                height = prevY+1- Integer.parseInt(_coordY.getText());
                   
            }
	    	_widthFild.setText(String.valueOf(width));
            _heightFild.setText(String.valueOf(height));
            
            xw = false;
        }
	}
    private int x;
	private int y;
	private boolean colorer = false;
	public static TextField _coordX;
    public static TextField _coordY;
    public static TextField _widthFild;
    public static TextField _heightFild;
    public static boolean xw = false;
    
}
