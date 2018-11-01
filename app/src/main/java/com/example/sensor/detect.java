package com.example.sensor;

public class detect {	
	static
	{
		System.loadLibrary("detect");
	}
	public final static native int open();
	public final static native int Humansensor_Settime(int time, int fd);
	public final static native int Humansensor_GetValue(int fd);
	public final static native int open_lightsensor();
	public final static native int Lightsensor_Settime(int time, int fd);
	public final static native int Lightsensor_Set_on_off(int value, int fd);
	public final static native int Lightsensor_Get_Brightness(int fd);
}
