package com.gft.employee.Common;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {

    private static Connection connection;
    private static String connectionUrl;

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        ConnectionUtil.connection = connection;
    }

    private static String drivermanager;


    private ConnectionUtil(){
        try {
            drivermanager = "oracle. jdbc. OracleDriver";
            Class.forName(drivermanager);
            connectionUrl = "jdbc:oracle:thin:@localhost:1521:xe";
            setConnection(DriverManager.getConnection(connectionUrl, "system", "system"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
