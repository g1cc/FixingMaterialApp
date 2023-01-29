package com.example.fixingmaterial;

import android.os.StrictMode;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection
{

    String url = "jdbc:jtds:sqlserver://g1ccserv.database.windows.net:1433;DatabaseName=PizzaDB;user=g1cc@g1ccserv;password=m7A@dGLP9q6Yazz;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;";
    public Connection connection = null;
    TextView textview;
    public Statement statement;
    public String result;
    public String query;
    public ResultSet resultSet;
    public int itsOk = 0;

    public void ConnectionVoid()
    {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connection = DriverManager.getConnection(url);
            itsOk = 1;
        }

        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            itsOk = 2;
        }

    }

}
