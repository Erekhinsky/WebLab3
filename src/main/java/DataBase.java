import java.sql.*;
import java.util.List;

public class DataBase {

    private final Connection connection;
    private PreparedStatement ps;

    private final String sql_select_from_table = "SELECT * FROM points";
    private final String sql_add_point = "INSERT INTO points (x, y, r, result) values (?, ?, ?, ?)";
    private final String sql_clear_table = "TRUNCATE points";
    private final String sql_create_table = "CREATE TABLE IF NOT EXISTS points " +
            "(id SERIAL PRIMARY KEY," +
            " x INTEGER  NOT NULL , " +
            " y REAL NOT NULL , " +
            " r INTEGER NOT NULL," +
            " result BOOLEAN NOT NULL);";


    public DataBase() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "ykh666";
        connection = DriverManager.getConnection(url, user, password);
        createTable();
    }

    private void createTable() throws SQLException {
        ps = connection.prepareStatement(sql_create_table);
        ps.execute();
    }

    public List<Point> selectFromTable(List<Point> pointBeans, Point point) {
        try {
            ps = connection.prepareStatement(sql_select_from_table);
            ResultSet rs = ps.executeQuery();
            pointBeans.clear();
            while (rs.next()) {
                int id = rs.getInt("id");
                point.setId(id);
                point.setX(rs.getInt("x"));
                point.setY(rs.getDouble("y"));
                point.setR(rs.getInt("r"));
                point.setResult(rs.getBoolean("result"));
                pointBeans.add(point);
                point = new Point();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pointBeans;
    }

    public void addPoint(Point point) {
        try {
            ps = connection.prepareStatement(sql_add_point);
//            ps.setInt(1, point.getId());
            ps.setInt(1, point.getX());
            ps.setDouble(2, point.getY());
            ps.setInt(3, point.getR());
            ps.setBoolean(4, point.getResult());
            ps.execute();
            System.out.println("точка залетела в БД:" + point.getX());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearPoints() {
        try {
            ps = connection.prepareStatement(sql_clear_table);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

