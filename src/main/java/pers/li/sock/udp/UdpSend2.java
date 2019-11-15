package pers.li.sock.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpSend2
{
	public static void main(String [] args) throws Exception
	{
		Scanner scanner = new Scanner(System.in);
		DatagramSocket ds=new DatagramSocket();
		while (true){
			System.out.println("输入要发送的内容：");
			String str = scanner.nextLine().trim();
			DatagramPacket dp=new DatagramPacket(str.getBytes(),str.length(),
					InetAddress.getByName("127.0.0.1"),3000);
			ds.send(dp);
			byte [] buf=new byte[1024];
			DatagramPacket dp2=new DatagramPacket(buf,1024);
			ds.receive(dp2);
			String str2=new String(dp2.getData(),0,dp2.getLength()) +
					" from " + dp2.getAddress().getHostAddress()+":"+dp2.getPort();
			System.err.println(str2);
//			ds.close();
		}
	}
}
