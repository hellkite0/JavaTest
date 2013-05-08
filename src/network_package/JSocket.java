package network_package;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import java.net.Socket;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.BitSet;

import mainpackage.Main;

public class JSocket {
	private String sHost_;
	private int iPort_;
	
	private ByteOrder cByteOrder_;
	
	private Socket cSock_;
	private InputStream cInput_;
	private OutputStream cOutput_;
	
	private BufferedReader cReader_;
	private BufferedWriter cWriter_;
	
	public JSocket() 
	{
		cByteOrder_ = ByteOrder.LITTLE_ENDIAN;
	}
	
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
			finalize();
			System.exit(1);
			//return false;
		}
		
		return true;
	}
	
	public void finalize()
	{
		if (cSock_.isClosed() == false)
			Close();
		else
			System.out.println("Socket is already closed.");
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
			finalize();
			System.exit(1);
		}
	}
	
	public void Write(String sString)
	{
		byte[] cBuffer = null;
		try 
		{
			cBuffer = sString.getBytes("UTF-8");
		} 
		catch (UnsupportedEncodingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			finalize();
			System.exit(1);
		}
		
		Write(cBuffer);
	}
	
	public void Write(char cValue)
	{
		ByteBuffer cBuffer = ByteBuffer.allocate(2);
		cBuffer.order(cByteOrder_);
		try
		{
			cBuffer.putChar(cValue);
		}
		catch (BufferOverflowException e)
		{
			e.printStackTrace();
			finalize();
			System.exit(1);
		}
		
		Write(cBuffer.array());
	}
	
	public void Write(Integer iValue)
	{
		ByteBuffer cBuffer = ByteBuffer.allocate(Integer.SIZE/8);
		cBuffer.order(cByteOrder_);
		cBuffer.putInt(iValue);
		
		Write(cBuffer.array());
	}
	public void Write(byte[] cBuffer)
	{
		try
		{
			cOutput_.write(cBuffer);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			finalize();
			System.exit(1);
		}
	}
	
	public void Send()
	{
		try 
		{
			cWriter_.flush();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			finalize();
			System.exit(1);
		}
	}
}
