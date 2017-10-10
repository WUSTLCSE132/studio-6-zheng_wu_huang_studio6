package studio6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jssc.*;

public class SerialComm {

	SerialPort port;

	private boolean debug;  // Indicator of "debugging mode"
	
	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	
	

	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
			SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1,
			SerialPort.PARITY_NONE);
		
		debug = false; // Default is to NOT be in debug mode
	}
		
	// TODO: Add writeByte() method from Studio 5
	public void writeByte(byte x) {
		try {
			port.writeByte(x);
			if (debug) {
				int i = (byte) x;
				String y = Integer.toHexString(i);
				System.out.println("<"+y+">");
			}
		}catch (Exception e) {
				
		}
	}
	// TODO: Add available() method
	public boolean available() {
		try {
			if(port.getInputBufferBytesCount()>0) {
				return true;
			}
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	// TODO: Add readByte() method
	public byte readByte() {
		byte[] b= {};
		try {
			b = port.readBytes(1);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b[0];
	}
	
	// TODO: Add a main() method
	public static void main(String args[]) {
		BufferedReader sys = new BufferedReader(new InputStreamReader(System.in));
		try {
			boolean indi = true;
			SerialComm SC = new SerialComm("COM4");
			while(indi) {
				if (SC.available()) {
				while(SC.available()) {
						char c = (char) SC.readByte();
						System.out.println(c);
					}
				}
			}
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
