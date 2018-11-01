package com.example.sensor;

/**
 * Created by Chen on 2018-11-01.
 */

public class GPIOUtil {
    private GpioChangeListener listener;
    private Thread getGpioThread;
    private  boolean isRun = true;
    private  int fd;
    private  static GPIOUtil instance;
    private GPIOUtil(){

    }
    public static GPIOUtil getInstance(){
        if (instance ==null){
            synchronized (GPIOUtil.class){
                if (instance == null){
                    instance = new GPIOUtil();
                    instance.init();
                }
            }
        }
        return instance;
    }

    private void init(){
        //如果已经启动
        if (getGpioThread !=null && isRun){
            return;
        }
        fd = detect.open();
        getGpioThread = new Thread(){
            @Override
            public void run() {
                int level1 = 1;
                int level2 = 1;
                while (isRun) {
                    try {
                        int pl2 = detect.Lightsensor_Get_Brightness(fd); //PL2
                        if (level1 !=pl2){
                            level1 = pl2;
                            if (listener != null){
                                if (level1 == 0){
                                    listener.onChange(1,true);
                                }else if (level1 == 1){
                                    listener.onChange(1,false);
                                }

                            }
                        }
                        int pb2 = detect.Lightsensor_Settime(10000,fd); //PB2
                        if (level2 !=pb2){
                            level2 = pb2;
                            if (listener != null){
                                if (level2 == 0){
                                    listener.onChange(2,true);
                                }else if (level2 == 1){
                                    listener.onChange(2,false);
                                }
                            }
                        }
                        Thread.sleep(500L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        getGpioThread.start();
    }

    public void setListener(GpioChangeListener listener) {
        this.listener = listener;
    }

    public void close(){
        isRun = false;
        getGpioThread = null;
    }
    public interface GpioChangeListener{
        void onChange(int index, boolean level);
    }
}
