class Circle{

	public double x;
	public double y;
	public double radius;

	public Circle(){
		
	}
	
	public Circle(double x, double y, double radius){
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public double getLength(){
		return 2 * Math.PI * this.radius;
	}
	
	public double getArea(){
		return Math.PI * this.radius * this.radius;
	}
	
	public boolean contains(double px, double py){
	
		double distance = Math.sqrt(
			(this.x - px)*(this.x - px)
			+
			(this.y - py)*(this.y - py)
		);
		
		return distance <= this.radius;
	
	}
	
	public String toString(){
	
		return "x: "+this.x+" y: "+this.y+" r: "+this.radius;
	
	}
	
	public boolean equals(Circle c){
	
		return this.x == c.x && this.y == c.y && this.radius == c.radius;
	
	}

}

class Persona{

	private String name;
	private int age;
	private String password;

	public Persona(String name){
	
		this.name = name;
		
	}

	public Persona(String name, int age){  
	
		this.name = name;
		this.setAge(age);
		
	}
	
	public void setName(String name){

		this.name = name;
	
	}
	
	public String getName(){
	
		return this.name;
		
	}
	
	public void setAge(int age){
		this.age = Math.max(0, age);
	}
	
	public int getAge(){
		return this.age;
	}
	
	public boolean isOverAge(){
	
		return this.age >= 18;
	
	}

	public String toString(){
		return name + " - " + age;
	}
	
	public boolean equals(Persona p){
		
		return p.getAge() == this.getAge() && p.getName() == this.getName();
		
	}

}


public class Lezione_05{

	public Lezione_05(){
	
		Persona p = new Persona();
		
		Circle c = new Circle(0,0,1);
		
		System.out.println(c.contains(1.5, 0.5));

	}
	
	public static void main(String[] args){
		new Lezione_05();
	}

}