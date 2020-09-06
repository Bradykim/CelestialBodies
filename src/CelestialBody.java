

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
	//Allows the x position of this Celestial body to be accessed.
	/**
	 * Return x-position of this Body.
	 * @return value of x-position.
	 */
	public double getX() {
		return myXPos;
	}
	/**
	 * Return y-position of this Body.
	 * @return value of y-position.
	 */
	//Allows the y position of this Celestial body to be accessed.
	public double getY() {
		return myYPos;
	}
	/**
	 * Return x-velocity of this Body.
	 * @return value of x-velocity.
	 */
	//Allows the x velocity of this Celestial body to be accessed.
	public double getXVel() {
		return myXVel;
	}
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	//Allows the y velocity of this Celestial body to be accessed.
	public double getYVel() {
		return myYVel;
	}
	/**
	 * Return the mass of this Body.
	 * @return value of mass.
	 */
	//Allows the mass of this Celestial body to be accessed.
	public double getMass() {
		return myMass;
	}
	/**
	 * Return the file name of this Body.
	 * @return value of file name.
	 */
	//Allows the fie name of this Celestial body to be accessed.
	public String getName() {
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	//Calculates the total distance from this Celestial body to the other.
	public double calcDistance(CelestialBody b) {
		double distancex = Math.pow(myXPos-b.getX(),2);
		double distancey = Math.pow(myYPos-b.getY(),2);
		double totaldistance = Math.sqrt(distancex+distancey);
		return totaldistance;
	}
	/**
	 * Return the total force exerted on this Celestial body by the other
	 * @param b the other body to which the force exerted is calculated
	 * @return force exerted by b onto this body
	 */
	//The total force exerted on this Celestial body by the other.
	public double calcForceExertedBy(CelestialBody b) {
		double totalforce = (6.67*1e-11)*(myMass*b.getMass())/Math.pow(this.calcDistance(b),2);
		return totalforce;
	}
	/**
	 * Return the force exerted in the x direction on this Celestial body by the other
	 * @param b the other body to which the force exerted in the x direction is calculated
	 * @return force in x direction exerted by b onto this body
	 */
	//The force exerted in the x direction on this Celestial body by the other.
	public double calcForceExertedByX(CelestialBody b) {
		double forcex = this.calcForceExertedBy(b)*(b.getX()-myXPos)/this.calcDistance(b);
		return forcex;
	}
	/**
	 * Return the force exerted in the y direction on this Celestial body by the other
	 * @param b the other body to which the force exerted in the y direction is calculated
	 * @return force in y direction exerted by b onto this body
	 */
	//The force exerted in the y direction on this Celestial body by the other.
	public double calcForceExertedByY(CelestialBody b) {
		double forcey = this.calcForceExertedBy(b)*(b.getY()-myYPos)/this.calcDistance(b);
		return forcey;
	}
	/**
	 * Return the net force exerted in the x direction on this Celestial body by all the other celestial bodies in the array
	 * @param bodies the other celestial bodies to which the force exerted in the x direction is calculated
	 * @return net force in x direction exerted by all bodies onto this body
	 */
	//The net force exerted in the x direction on this Celestial body by all the other celestial bodies in the array.
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
	/**
	 * Return the net force exerted in the y direction on this Celestial body by all the other celestial bodies in the array
	 * @param bodies the other celestial bodies to which the force exerted in the y direction is calculated
	 * @return net force in y direction exerted by all bodies onto this body
	 */
	//The net force exerted in the y direction on this Celestial body by all the other celestial bodies in the array.
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
	/**
	 * Updates all values of this Celestial body including x and y position, x and y velocity.
	 * @param deltaT the constant that you multiply by the derivative of each (i.e velocity or acceleration) and then add to the original value
	 * @param xforce the x force of the new state of the Celestial Body
	 * @param yforce the y force of the new state of the Celestial Body
	 */
	//Updates all values of this Celestial body including x and y position, x and y velocity.
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
