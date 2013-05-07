package network_package;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.net.Socket;

public class JSocket {
	private String sHost_;
	private int iPort_;
	
	private Socket cSock_;
	private InputStream cInput_;
	private OutputStream cOutput_;
	
	private BufferedReader cReader_;
	private BufferedWriter cWriter_;
	
	public JSocket() {}
	
	public boolean Connect(String sHost, int iPort)
	{
		try
		{
			sHost_ = sHost;
			iPort_ = iPort;
			
			cSock_ = new Socket(sHost_, iPort_);
			
			System.out.println("Connected to " + sHost_ + " : " + iPort_);
			
			cInput_ = cSock_.getInputStream();
			cOutput_ = cSock_.getOutputStream();
			
			cReader_ = new BufferedReader(new InputStreamReader(cInput_));
			cWriter_ = new BufferedWriter(new OutputStreamWriter(cOutput_));
		}
		catch (IOException e)
		{
			e.getStackTrace();
			return false;
		}
		
		return true;
	}
	
	public void Close()
	{
		try 
		{
			System.out.println("Disconnected from " + sHost_ + " : " + iPort_);
			cSock_.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Write(String sString)
	{
		try {
			cWriter_.write(sString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Send()
	{
		try {
			cWriter_.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
