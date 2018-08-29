import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;
import oracle.spatial.geometry.JGeometry;

import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

class DBConnection {
    private Connection conn;
    private List<JGeometry> result = new ArrayList<>(0);

    void connect(String user, String password) {
        if (user == null) {
            user = "";
        }

        if (password == null) {
            password = "";
        }



        String url = "jdbc:oracle:thin:@localhost:1521:XE";

        Properties props = new Properties();
        props.setProperty(OracleConnection.CONNECTION_PROPERTY_THIN_NET_ENCRYPTION_LEVEL, "REQUIRED");
        props.setProperty(OracleConnection.CONNECTION_PROPERTY_THIN_NET_ENCRYPTION_TYPES, "( DES40C )");
        props.setProperty(OracleConnection.CONNECTION_PROPERTY_THIN_NET_CHECKSUM_LEVEL, "REQUESTED");
        props.setProperty(OracleConnection.CONNECTION_PROPERTY_THIN_NET_CHECKSUM_TYPES, "( MD5 )");
        props.setProperty("user", user);
        props.setProperty("password", password);

        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setConnectionProperties(props);
            ods.setURL(url);
            conn = ods.getConnection();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Connection Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    List<JGeometry> getJList() {
        return result;
    }

    void getData() {
        try {
            File file = new File("tables.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String tableName;

            while ((tableName = reader.readLine()) != null) {
                Statement stmt = conn.createStatement();
                String sql = String.format("SELECT a.geometrija geom FROM %s a", tableName);
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    Struct st = (Struct) rs.getObject(1);
                    JGeometry j_geom = JGeometry.loadJS(st);
                    result.add(j_geom);
                }
            }
        } catch (IOException | SQLException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Getting Data Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    void close() {
        try {
            this.conn.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Database Closing Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
