package practice;

import dao.AccountDAO;
import db.DBConnection;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountSelectOne {

    public static void main(String[] args) {
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.selectOne(2222);
    }
}