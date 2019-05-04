package com.example.listviewsqlite.Db;

public class Constants {

    //COLUMNS
    static final String ROW_ID="id";
    static final String TASK="task";
    static final String DATE="date";
    static final String TIME="time";

    //DB PROPERTIES
    static final String DB_NAME="db_name";
    static final String TB_NAME="tb_name";
    static final int DB_VERSION=1;

    //CREATE TB STMT
    static final String CREATE_TB="CREATE TABLE tb_name(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "task TEXT NOT NULL," + "date TEXT NOT NULL," + "time TEXT NOT NULL);";

    //DROP TB STMT
    static final String DROP_TB="DROP TABLE IF EXISTS "+TB_NAME;

}
