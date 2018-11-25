package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.devlopp.teq.databasehelper.DatabaseDriverHelper;
import com.devlopp.teq.databasehelper.DatabaseInsertHelper;
import com.devlopp.teq.databasehelper.DatabaseSelectHelper;
import com.devlopp.teq.databasehelper.DatabaseValidHelper;
import com.devlopp.teq.databasepreset.DatabasePresetQuery;
import com.devlopp.teq.databasepreset.DatabasePresetQueryHelper;
import com.devlopp.teq.parser.TemplateParser;
import com.devlopp.teq.parser.TemplateParserFactory;
import com.devlopp.teq.reporting.GenerateReport;
import com.devlopp.teq.security.PasswordHelper;
import com.devlopp.teq.sql.SQLDriver;
import com.sun.javafx.stage.ScreenHelper;

public class Controller {

    @FXML
    private ComboBox<String> templateTypes;
    @FXML
    private ComboBox<String> presetTypes;
    @FXML
    private Button addFileButton;
    @FXML
    private Button goReportButton;
    @FXML
    private Button goUploadButton;
    @FXML
    private Button logInButton;
    @FXML
    private Button signUpButton;
    @FXML
    private Button preReportButton;
    @FXML
    private Button ownReportButton;
    @FXML
    private TextField SignName;
    @FXML
    private ComboBox<String> SignRole;
    @FXML
    private PasswordField SignPwd;
    @FXML
    private TextField LogInName;
    @FXML
    private TextField p1;
    @FXML
    private TextField p2;
    @FXML
    private TextField p3;
    @FXML
    private PasswordField LogInPwd;
    @FXML
    private TextField numOfCol;
    @FXML
    private TextArea sqlInput;
    @FXML
    private TextArea reportResult;
    @FXML
    private Label SignUpNotice;
    @FXML
    private Label LogInNotice;
    @FXML
    private Label uploadNotice;
    @FXML
    private Label preNotice;

    public Controller() {
    }

    public String getFileExtension(File file) {
        String fileName = file.getName();
        int delimIndex = fileName.lastIndexOf('.');
        String ext = fileName.substring(delimIndex + 1, fileName.length());
        return ext.toLowerCase();
    }

    public void handleAddFileAction(ActionEvent actionEvent) {
        String templateType = templateTypes.getValue();
        if (templateType != null) {
            FileChooser chooser = new FileChooser();
            File dataFile = chooser.showOpenDialog(new Stage());
            // if data file was selected
            if (dataFile != null) {
                // if data file was of Excel
                String ext = getFileExtension(dataFile);
                if (ext.startsWith("xls")) {
                    insertData(dataFile.getPath(), templateType);
                    uploadNotice.setText("The information has been uploaded.");
                } else {
                    uploadNotice.setText("File must be an Excel file!");
                }
            }
        }
    }

    public void insertData(String filePath, String templateType) {
        System.out.println("File path: " + filePath);
        System.out.println("Template:  " + templateType);
        TemplateParser templateParser = TemplateParserFactory.getParser(templateType);
        templateParser.read(filePath, 0);
        for (Object record : templateParser.parse()) {
            Object recordId = DatabaseInsertHelper.insertRecord(record);
            System.out.println("Template type: " + templateType);
            System.out.println("New record ID: " + recordId);
        }
    }

    public boolean verifyPwd(int userId, String pwd) {
        String databasePwd = DatabaseSelectHelper.getPlatformPassword(userId);
        return PasswordHelper.comparePassword(databasePwd, pwd);
    }

    public void handleSignUp(ActionEvent actionEvent) {
    	String userName = SignName.getText();
    	int checkUserId = DatabaseSelectHelper.getPlatformUserId(userName);
    	if (checkUserId != -1) {
    		SignUpNotice.setText("The user name already exist");
    	} else {
     	    String pwd = SignPwd.getText();
    	    String roleName = ""; //SignRole.getText();
    	    int roleId = DatabaseSelectHelper.getPlatformRoleId(roleName);
    	    if (roleId == -1 ) {
    	    	SignUpNotice.setText("No such role exists");
    	    } else if ( !(DatabaseValidHelper.validPassword(pwd))|| !(DatabaseValidHelper.validUsername(userName))) {
    	    	SignUpNotice.setText("Username or password is not valid (should be between 4 and 64 chars).");
    	    } else {
    		    DatabaseInsertHelper.insertPlatformUser(userName, pwd, roleId);
    		    SignUpNotice.setText("Successfully signed up!");
    	    }
    	}
  	
    }

    public void handleLogIn(ActionEvent actionEvent) {
        String userName = LogInName.getText();
        int userId = DatabaseSelectHelper.getPlatformUserId(userName);
        if (userId == -1) {
            LogInNotice.setText("The user doesn't exist please sign up first");
        } else {
            String pwd = LogInPwd.getText();
            if (verifyPwd(userId, pwd)) {
                goUpload(actionEvent);
            }
        }
    }

    public void goReport(ActionEvent actionEvent) {
        try {
            Parent report_parent = FXMLLoader.load(getClass().getResource("/fxml/report.fxml"));
            Scene report_scene = new Scene(report_parent);
            Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            app_stage.setScene(report_scene);
            app_stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goUpload(ActionEvent actionEvent) {
        try {
            Parent upload_parent = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
            Scene upload_scene = new Scene(upload_parent);
            Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            app_stage.setScene(upload_scene);
            app_stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void selectPre(ActionEvent actionEvent) {
        String presetQuery = presetTypes.getValue();

        if (presetQuery.equals("getNumberOfClients")) {
            p1.setText("serviceId");
            p2.setText("");
            p3.setText("");

        } else if (presetQuery.equals("getNumUsersOfServiceWithinAgeRange")) {
            p1.setText("serviceType");
            p2.setText("ageRange");
            p3.setText("");

        } else if (presetQuery.equals("getClientIDsForService")) {
            p1.setText("serviceType");
            p2.setText("");
            p3.setText("");

        } else if (presetQuery.equals("getLanguagesSpoken")) {
            p1.setText("serviceType");
            p2.setText("");
            p3.setText("");

        } else if (presetQuery.equals("getNumberUsersServices")) {
            p1.setText("");
            p2.setText("");
            p3.setText("");

        } else if (presetQuery.equals("getAverageClientAge")) {
            p1.setText("");
            p2.setText("");
            p3.setText("");

        } else if (presetQuery.equals("getPercentageOfClientsWithinAgeRange")) {
            p1.setText("minAge");
            p2.setText("maxAge");
            p3.setText("");

        } else if (presetQuery.equals("getClientIDsAtAddressByPostalCode")) {
            p1.setText("postalCode");
            p2.setText("");
            p3.setText("");

        } else if (presetQuery.equals("getClientIDsAtAddressByCity")) {
            p1.setText("city");
            p2.setText("province");
            p3.setText("");

        } else if (presetQuery.equals("getAllCourseStudents")) {
            p1.setText("courseCode");
            p2.setText("");
            p3.setText("");

        } else if (presetQuery.equals("getCoursesTaken")) {
            p1.setText("clientId");
            p2.setText("");
            p3.setText("");

        } else if (presetQuery.equals("getNumOfUsersWithinRange")) {
            p1.setText("serviceType");
            p2.setText("startDate");
            p3.setText("endDate");

        } else if (presetQuery.equals("generateTrendsInService")) {
            p1.setText("serviceType");
            p2.setText("startYear");
            p3.setText("endYear");

        } else if (presetQuery.equals("generateChartofAge")) {
            p1.setText("");
            p2.setText("");
            p3.setText("");

        } else if (presetQuery.equals("generateChartOfServicesUsed")) {
            p1.setText("");
            p2.setText("");
            p3.setText("");

        } else if (presetQuery.equals("getAllServiceIds")) {
            p1.setText("");
            p2.setText("");
            p3.setText("");

        }

    }

    public void generatePreset(ActionEvent actionEvent) throws ParseException, SQLException {
        String presetQuery = presetTypes.getValue();
        String p1value = p1.getText();
        String p2value = p2.getText();
        String p3value = p3.getText();

        if (presetQuery.equals("getNumberOfClients")) {
            int serviceId = Integer.parseInt(p1value);
            int result = DatabasePresetQuery.getNumberOfClients(serviceId);
            reportResult.setText(Integer.toString(result));

        } else if (presetQuery.equals("getNumUsersOfServiceWithinAgeRange")) {
            String serviceType = p1value;
            String ageRange = p2value;
            int result = DatabasePresetQuery.getNumUsersOfServiceWithinAgeRange(serviceType, ageRange);
            reportResult.setText(Integer.toString(result));

        } else if (presetQuery.equals("getClientIDsForService")) {
            String serviceType = p1value;
            List<Integer> result = DatabasePresetQuery.getClientIDsForService(serviceType);
            reportResult.setText(Arrays.toString(result.toArray()));

        } else if (presetQuery.equals("getLanguagesSpoken")) {
            String serviceType = p1value;
            String result = DatabasePresetQuery.getLanguagesSpoken(serviceType);
            reportResult.setText(result);

        } else if (presetQuery.equals("getNumberUsersServices")) {
            String result = DatabasePresetQuery.getNumberUsersServices();
            reportResult.setText(result);

        } else if (presetQuery.equals("getAverageClientAge")) {
            double result = DatabasePresetQuery.getAverageClientAge();
            reportResult.setText(Double.toString(result));

        } else if (presetQuery.equals("getPercentageOfClientsWithinAgeRange")) {
            int minAge = Integer.parseInt(p1value);
            int maxAge = Integer.parseInt(p2value);
            String result = DatabasePresetQuery.getPercentageOfClientsWithinAgeRange(minAge, maxAge);
            reportResult.setText(result);

        } else if (presetQuery.equals("getClientIDsAtAddressByPostalCode")) {
            String postalCode = p1value;
            List<Integer> result = DatabasePresetQuery.getClientIDsAtAddress(postalCode);
            reportResult.setText(Arrays.toString(result.toArray()));

        } else if (presetQuery.equals("getClientIDsAtAddressByCity")) {
            String city = p1value;
            String province = p2value;
            List<Integer> result = DatabasePresetQuery.getClientIDsAtAddress(city, province);
            reportResult.setText(Arrays.toString(result.toArray()));

        } else if (presetQuery.equals("getAllCourseStudents")) {
            String courseCode = p1value;
            List<Integer> result = DatabasePresetQuery.getAllCourseStudents(courseCode);
            reportResult.setText(Arrays.toString(result.toArray()));

        } else if (presetQuery.equals("getCoursesTaken")) {
            int clientId = Integer.parseInt(p1value);
            List<String> result = DatabasePresetQuery.getCoursesTaken(clientId);
            reportResult.setText(Arrays.toString(result.toArray()));

        } else if (presetQuery.equals("getNumOfUsersWithinRange")) {
            String serviceType = p1value;
            String inputStart = p2value;
            String inputEnd = p3value;
            SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY-MM-DD");
            java.util.Date date = sdf1.parse(inputStart);
            java.util.Date date2 = sdf1.parse(inputEnd);
            java.sql.Date startDate = new java.sql.Date(date.getTime());
            java.sql.Date endDate = new java.sql.Date(date2.getTime());
            int result = DatabasePresetQuery.getNumOfUsersWithinRange(serviceType, startDate, endDate);
            reportResult.setText(Integer.toString(result));

        } else if (presetQuery.equals("generateTrendsInService")) {
            String serviceType = p1value;
            Integer startYear = Integer.parseInt(p2value);
            Integer endYear = Integer.parseInt(p3value);
        	GenerateReport.generateTrendsInService(serviceType, startYear, endYear);

        } else if (presetQuery.equals("generateChartofAge")) {
        	GenerateReport.generateChartofAge();

        } else if (presetQuery.equals("generateChartOfServicesUsed")) {
        	GenerateReport.generateChartOfServicesUsed();

        } else if (presetQuery.equals("getAllServiceIds")) {
        	List<Integer> result = DatabaseSelectHelper.getAllServiceIds();
        	reportResult.setText(Arrays.toString(result.toArray()));

        }
    }

    public static ResultSet getSqlResult(String sqlCommand) throws SQLException {
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sqlCommand);
        return rs;
    }

    public void generateOwn(ActionEvent actionEvent) throws SQLException {
        String sqlCommand = sqlInput.getText();
        if (SQLDriver.isSelectQuery(sqlCommand)) {
            String reportText = "";
            ResultSet rs = getSqlResult(sqlCommand);

            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                if (i > 1)
                    reportText += ", ";
                reportText += rsmd.getColumnName(i);
            }
            reportText += "\n";

            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1)
                        reportText += ", ";
                    reportText += rs.getString(i);
                }
                reportText += "";
            }
            reportResult.setText(reportText);
        } else {
            reportResult.setText("Your sql command is not a valid select query");
        }

    }

}
