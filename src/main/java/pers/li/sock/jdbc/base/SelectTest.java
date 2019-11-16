package pers.li.sock.jdbc.base;

import java.sql.*;

/**
 * 此处使用的mysql版本为： select VERSION(); 5.7.28
 * 1.高版本的驱动已经由 "com.mysql.jdbc.Driver" 变更为 com.mysql.cj.jdbc.Driver
 * 2.报错1：According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL
 *      修改：useSSL=false
 * 3.报错2：java.sql.SQLException: The server time zone value 'ÖÐ¹ú±ê×¼Ê±¼ä' is
 *      修改：serverTimezone=UTC
 * 4.综上：
 *      String url="jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
 */
public class SelectTest {
    public static void main(String[] args){
    	
    	//构建Java和数据库之间的桥梁介质
        try{            
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("注册驱动成功!");
        }catch(ClassNotFoundException e1){
            System.out.println("注册驱动失败!");
            e1.printStackTrace();
            return;
        }
//        private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc_example?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
//        String url="jdbc:mysql://localhost:3306/test";
        String url="jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
        Connection conn = null;
        try {
        	//构建Java和数据库之间的桥梁：URL，用户名，密码
            conn = DriverManager.getConnection(url, "root", "root");
            
            //构建数据库执行者
            Statement stmt = conn.createStatement(); 
            System.out.println("创建Statement成功！");      
            
            //执行SQL语句并返回结果到ResultSet
            ResultSet rs = stmt.executeQuery("select bookid, bookname, price from t_book order by bookid");
                        
            //开始遍历ResultSet数据
            while(rs.next())
            {
            	System.out.println(rs.getInt(1) + "," + rs.getString(2) + "," + rs.getInt("price"));
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        finally
        {
        	try
        	{
        		if(null != conn)
        		{
            		conn.close();
            	}
        	}
        	catch (SQLException e){
                e.printStackTrace();
        	}        	
        }
    }
}
