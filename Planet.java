public class Planet {

    private double xPos;
    private double yPos;
    private double xVel;
    private double yVel;
    private double mass;
    private String imageName;

    public Planet(double xPos, double yPos, double xVel, double yVel, double mass, String imageName) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVel = xVel;
        this.yVel = yVel;
        this.mass = mass;
        this.imageName = imageName;
    }

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public double getXVel() {
        return xVel;
    }

    public double getYVel() {
        return yVel;
    }

    public double getMass() {
        return mass;
    }

    public String getImageName() {
        return "assets/galaxy elements/" + imageName;
    }

    public void setXPos(double xPos) {
        this.xPos = xPos;
    }

    public void setYPos(double yPos) {
        this.yPos = yPos;
    }

    public void setXVel(double xVel) {
        this.xVel = xVel;
    }

    public void setYVel(double yVel) {
        this.yVel = yVel;
    }

    public double calcDistance(Planet p) {
        double x = p.getXPos() - this.getXPos();
        double y = p.getYPos() - this.getYPos();
        return Math.sqrt(x * x + y * y);
    }

    public double calcForceExertedBy(Planet p) {
        double G = 6.67e-11;
        double r = this.calcDistance(p);
        return G * this.getMass() * p.getMass() / (r * r);
    }

    public double calcForceExertedByX(Planet p) {
        double F = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        return F * (p.getXPos() - this.getXPos()) / r;
    }

    public double calcForceExertedByY(Planet p) {
        double F = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        return F * (p.getYPos() - this.getYPos()) / r;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double F = 0;
        for (Planet p : planets) {
            if (!this.equals(p)) {
                F += this.calcForceExertedByX(p);
            }
        }
        return F;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double F = 0;
        for (Planet p : planets) {
            if (!this.equals(p)) {
                F += this.calcForceExertedByY(p);
            }
        }
        return F;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.getMass();
        double aY = fY / this.getMass();
        this.setXVel(this.getXVel() + aX * dt);
        this.setYVel(this.getYVel() + aY * dt);
        this.setXPos(this.getXPos() + this.getXVel() * dt);
        this.setYPos(this.getYPos() + this.getYVel() * dt);
    }

    public void draw() {
        StdDraw.picture(this.getXPos(), this.getYPos(), this.getImageName());
    }
}
