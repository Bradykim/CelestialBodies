

/**
 * Celestial Body class for NBody
 * @author ola
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
		this.myXPos= xp;
		this.myYPos= yp;
		this.myXVel= xv;
		this.myYVel= yv;
		this.myMass= mass;
		this.myFileName= filename;
	}

	/**
	 * Copy constructor: copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */
	public CelestialBody(CelestialBody b){
		this.myXPos= b.getX();
		this.myYPos= b.getY();
		this.myXVel= b.getXVel();
		this.myYVel= b.getYVel();
		this.myMass= b.getMass();
		this.myFileName= b.getName();
	}

	public double getX() {
		return myXPos;
	}
	public double getY() {
		return myYPos;
	}
	public double getXVel() {
		return myXVel;
	}
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() {
		return myYVel;
	}
	
	public double getMass() {
		return myMass;
	}
	public String getName() {
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		double distancex = Math.pow(myXPos-b.getX(),2);
		double distancey = Math.pow(myYPos-b.getY(),2);
		double totaldistance = Math.sqrt(distancex+distancey);
		return totaldistance;
	}

	public double calcForceExertedBy(CelestialBody b) {
		double totalforce = (6.67*1e-11)*(myMass*b.getMass())/Math.pow(this.calcDistance(b),2);
		return totalforce;
	}

	public double calcForceExertedByX(CelestialBody b) {
		double forcex = this.calcForceExertedBy(b)*(b.getX()-myXPos)/this.calcDistance(b);
		return forcex;
	}
	public double calcForceExertedByY(CelestialBody b) {
		double forcey = this.calcForceExertedBy(b)*(b.getY()-myYPos)/this.calcDistance(b);
		return forcey;
	}

	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		double sum = 0.0;
		for(CelestialBody b : bodies)
		{
			if(! b.equals(this))
			{
				sum+= this.calcForceExertedByX(b);
			}
		}

		return sum;
	}

	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double sum = 0.0;
		for(CelestialBody b : bodies)
		{
			if(! b.equals(this))
			{
				sum+= this.calcForceExertedByY(b);
			}
		}

		return sum;
	}

	public void update(double deltaT, 
			           double xforce, double yforce) {
		double ax = xforce/getMass();
		double ay = yforce/getMass();
		double nvx = myXVel+deltaT*ax;
		double nvy = myYVel+deltaT*ay;
		double nx = myXPos+deltaT*nvx;
		double ny = myYPos+deltaT*nvy;
		myXPos= nx;
		myYPos= ny;
		myXVel=nvx;
		myYVel=nvy;


	}

	/**
	 * Draws this planet's image at its current position
	 */
	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}
