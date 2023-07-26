package practice;

import dao.AccountDAO;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountUpdate {

    public static void main(String[] args) {
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.update(900,1111);
    }
}