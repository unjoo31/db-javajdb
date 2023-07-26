package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getInstance(){
        // mysql 연결 정보
        // url의 프로토콜은 만든사람의 몫이기 때문에 이해할 필요없음
        String url = "jdbc:mysql://localhost:3306/metadb";
        String username = "root";
        String password = "root1234";


        // JDBC 드라이버 로드
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // connection 은 프로토콜이 적용된 소켓이다
            // 여기에 버퍼를 연결해서 데이터를 보낸다
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("됨");
            return connection;
        } catch (Exception e) {
            System.out.println("false : " + e.getMessage());
        }

        return  null;
    }

}
