package pers.li.sock.tcp;

import java.net.*;
public class TcpServer2
{
	public static void main(String [] args)
	{
		try
		{
			ServerSocket ss=new ServerSocket(8001);
			while(true)
			{
				Socket s=ss.accept();
				System.out.println("来了一个client");
				//此处有风险，不应该使用无限制线程数，应当使用共享线程池来控制线程个数
				new Thread(new Worker(s)).start();
			}
			//ss.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
