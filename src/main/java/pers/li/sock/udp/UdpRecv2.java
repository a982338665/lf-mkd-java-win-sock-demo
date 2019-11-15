package pers.li.sock.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpRecv2
{
	public static void main(String[] args) throws Exception
	{
		Scanner scanner = new Scanner(System.in);
		DatagramSocket	ds=new DatagramSocket(3000);
		while (true){
			//数据接收
			byte [] buf=new byte[1024];
			DatagramPacket dp=new DatagramPacket(buf,1024);
			ds.receive(dp);
			String strRecv=new String(dp.getData(),0,dp.getLength()) +
					" from " + dp.getAddress().getHostAddress()+":"+dp.getPort();
			System.err.println(strRecv);
			System.out.println("输入要发送的内容：");
//			String str="hello world 222";
			String str = scanner.nextLine().trim();
			DatagramPacket dp2=new DatagramPacket(str.getBytes(),str.length(),
					InetAddress.getByName("127.0.0.1"),dp.getPort());
			ds.send(dp2);
//			ds.close();
		}
	}
}
