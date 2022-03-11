public class Point {

    private Integer id;
    private Integer x;
    private Double y;
    private Integer r;
    private Boolean result;

    private boolean r1, r2, r3, r4, r5;

    public void initialize(Point point) {
        result = checkArea(point.getX(), point.getY(), point.getR());
    }

    private boolean checkArea(double x, double y, double r) {
        if (x >= 0 && y <= 0 && x * x + y * y <= r * r / 4) {
            return true;
        }
        if (x >= 0 && y >= 0 && y <= -x + r) {
            return true;
        }
        return x <= 0 && y <= 0 && x >= - r && y >= -r / 2;
    }

    public Boolean getResult ( ) {
        return result;
    }

    public void setResult (Boolean result) {
        this.result = result;
    }

    public Integer getX ( ) {
        return x;
    }

    public void setX (Integer x) {
        this.x = x;
    }

    public Double getY ( ) {
        return y;
    }

    public void setY (Double y) {
        this.y = y;
    }

    public Integer getR ( ) {
        return r;
    }

    public void setR (Integer r) {
        this.r = r;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isR1() {
        return r1;
    }

    public boolean isR2() {
        return r2;
    }

    public boolean isR3() {
        return r3;
    }

    public boolean isR4() {
        return r4;
    }

    public boolean isR5() {
        return r5;
    }

    public void setR1(boolean r1) {
        this.r1 = r1;
    }

    public void setR2(boolean r2) {
        this.r2 = r2;
    }

    public void setR3(boolean r3) {
        this.r3 = r3;
    }

    public void setR4(boolean r4) {
        this.r4 = r4;
    }

    public void setR5(boolean r5) {
        this.r5 = r5;
    }
}


