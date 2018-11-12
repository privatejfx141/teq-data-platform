package com.devlopp.teq.sql;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SQLDriver {
    public static boolean runScript(Connection connection, String fileName) {
        // initialize script runner
        ScriptRunner scriptRunner = new ScriptRunner(connection, false, true);
        try {
            // create buffered reader
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // run the script
            scriptRunner.runScript(bufferedReader);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static java.sql.Date parseDate(String dateString) throws ParseException {
        return parseDate(dateString, "yyyy-MM-dd");
    }

    public static java.sql.Date parseDate(String dateString, String dateFormat) throws ParseException {
        if (dateString.isEmpty()) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        java.util.Date date = simpleDateFormat.parse(dateString);
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        return sqlStartDate;
    }
}
