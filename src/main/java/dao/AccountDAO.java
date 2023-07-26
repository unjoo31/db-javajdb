package dao;

import db.DBConnection;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// DB 접근 미들웨어
// SRP(Single Responsibility Principle) : 단일책임의 원칙
// DAO : Data Access Object
public class AccountDAO {
    public void insert(int accountNumber, String accountPassword, int balance){
        // 1. DB 연결
        Connection conn = DBConnection.getInstance();

        // 2. 버퍼로 SQL 쓰기
        try {
            String sql = "insert into account_tb(account_number, account_password, account_balance, account_created_at) values(?,?,?,now())";

            // 위에 코드를 다르게 바꿀 수 있다
//            StringBuilder sql = new StringBuilder();
//            sql.append("insert into account_tb");
//            sql.append("(account_number, account_password, account_balance, account_created_at) ");
//            sql.append("values(?,?,?,now())");

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, accountNumber);
            statement.setString(2, accountPassword);
            statement.setInt(3, balance);

            int result = statement.executeUpdate(); // flush (변경된 row 카운트를 응답)
            System.out.println("결과 : "+result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void update(int accountBalance, int accountNumber){
        // 1. DB 연결
        Connection conn = DBConnection.getInstance();

        // 2. 버퍼로 SQL 쓰기
        try {
            String sql = "update account_tb set account_balance = ? where account_number = ?";

            // 위에 코드를 다르게 바꿀 수 있다
//            StringBuilder sql = new StringBuilder();
//            sql.append("insert into account_tb");
//            sql.append("(account_number, account_password, account_balance, account_created_at) ");
//            sql.append("values(?,?,?,now())");

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, accountBalance);
            statement.setInt(2, accountNumber);

            int result = statement.executeUpdate(); // flush (변경된 row 카운트를 응답)
            System.out.println("결과 : "+result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void delete(int accountNumber){
        // 1. DB 연결
        Connection conn = DBConnection.getInstance();

        // 2. 버퍼로 SQL 쓰기
        try {
            String sql = "delete from account_tb where account_number = ?";

            // 위에 코드를 다르게 바꿀 수 있다
//            StringBuilder sql = new StringBuilder();
//            sql.append("insert into account_tb");
//            sql.append("(account_number, account_password, account_balance, account_created_at) ");
//            sql.append("values(?,?,?,now())");

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, accountNumber);

            // 쓰는거는 executeUpdate() 사용한다
            int result = statement.executeUpdate(); // flush (변경된 row 카운트를 응답)
            System.out.println("결과 : "+result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void selectOne(int accountNumber){
        Account account = null;
        Connection conn = DBConnection.getInstance();

        try{
            String sql = "select * from account_tb where account_number = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,accountNumber);

            // 읽을때는 executeQuery() 사용해야한다
            ResultSet rs = statement.executeQuery();

            // 커서를 한칸 내리면서 Account 클래스에 저장한다
            // rs.next() : 커서를 한칸씩 내리면서 데이터가 있으면 true, 없으면 false를 리턴한다
            // if : 전체를 조회할거면 while을 써야하는데 account_number 한 건이기 때문에 if를 사용함
            if(rs.next()){
                account = new Account(
                        rs.getInt("account_number"),
                        rs.getString("account_password"),
                        rs.getInt("account_balance"),
                        rs.getTimestamp("account_created_at")
                );
            }

            System.out.println("계좌번호: "+account.getAccountNumber());
            System.out.println("계좌비번: "+account.getAccountPassword());
            System.out.println("계좌잔액: "+account.getAccountBalance());
            System.out.println("계좌생성일: "+account.getAccountCreatedAt());


        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void selectAll(){}
}