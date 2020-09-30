package weather.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertDatate {

    public static void setInsertCity(String id, String province, String city, String district) {

        try {
            Class.forName("org.sqlite.JDBC");
            // 连接到数据库
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city.db");
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO tb_city VALUES ('" + id + "','" + province+ "','" + city+ "','" + district + "');"; // 通知信息内容表
//            System.out.println(sql);
            statement.executeUpdate(sql);
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
