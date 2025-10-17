package org.qaautomation;

import APIs.listGET;
import Utils.DBOperation;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        DBOperation dbOperation = new DBOperation();
        listGET listapi= new listGET();
        System.out.println("Hello world!");
        //dbOperation.getDBResult(dbOperation.MysqlConnectionOwn());
        //listapi.listAPI();
        //System.out.println("Get ID: "+listapi.getId(1));
    }
}