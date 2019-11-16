package pers.li.sock.jdbc.base;

import java.sql.*;

public class UpdateTest {
	public static void main(String[] args){
		executeUpdate("update t_book set price = 300 where bookid = 1");
		executeUpdate("insert into t_book(bookid, bookname, price) values(4, '编译原理', 90)");
		executeUpdate("delete from t_book where id = 4");
	}
    public static void executeUpdate(String sql)
    {
    	//构建Java和数据库之间的桥梁介质
        try{            
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("注册驱动成功!");
        }catch(ClassNotFoundException e1){
            System.out.println("注册驱动失败!");
            e1.printStackTrace();
        }

        String url="jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
        Connection conn = null;
        try {
        	//构建Java和数据库之间的桥梁：URL，用户名，密码
            conn = DriverManager.getConnection(url, "root", "root");
            
            //构建数据库执行者
            Statement stmt = conn.createStatement(); 
            System.out.println("创建Statement成功！");      
            
            //执行SQL语句
            int result = stmt.executeUpdate(sql);
                        
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
