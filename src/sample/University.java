package sample;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class University {

    public University(){
        map = new Map();

        ///////////Тут запустим таймер который будет вызывать функцию update() каждую секунду////////////////
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                map.update();
            }
        }, 0, 1);
    }

    private Map map;
}
