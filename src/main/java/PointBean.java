import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "PointBean")
public class PointBean implements Serializable {

    private Point point = new Point();

    private final DataBase dataBase = new DataBase();

    private List<Point> pointList = new ArrayList<Point>();

    public PointBean () throws SQLException, ClassNotFoundException {}

    @PostConstruct
    public void postConstruct () {
        pointList = dataBase.selectFromTable(pointList, point);
    }

    private boolean checkPoint(double x, double y) {
        return x >= -2 && x <= 2 && y >= -5 && y <= 3;
    }

    public void insertPoint() {

        if (point.getX() == null) {
            point.setX(0);
        }

        if (point.getX() != null && point.getY() != null && checkPoint(point.getX(), point.getY())) {
            Point point  = new Point();

            point.setX(this.point.getX());
            point.setY(this.point.getY());

            if (this.point.isR1() || this.point.isR2() || this.point.isR3() || this.point.isR4() || this.point.isR5()) {
                if (this.point.isR1()) point.setR(1);
                if (this.point.isR2()) point.setR(2);
                if (this.point.isR3()) point.setR(3);
                if (this.point.isR4()) point.setR(4);
                if (this.point.isR5()) point.setR(5);

                point.initialize(point);
                dataBase.addPoint(point);
                refresh();
            }
        }
    }

    private void refresh() {
        pointList = new ArrayList<Point>(dataBase.selectFromTable(pointList, point));
    }

    public void removePoint() {
        pointList.clear();
        dataBase.clearPoints();
    }

    public Point getPoint () {
        return point;
    }

    public void setPoint (Point point) {
        this.point = point;
    }

    public List<Point> getPointList () {
        return pointList;
    }

    public void setPointList (List<Point> pointList) {
        this.pointList = pointList;
    }
}

