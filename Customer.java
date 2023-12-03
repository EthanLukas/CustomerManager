package labs.lab9;

//Store data and attributes of customers

public class Customer implements Comparable<Customer>
{
	
	private String name;
	
	private String email;
	
	private boolean dogCheck;
	private boolean catCheck;
	private boolean birdCheck;
	private boolean fishCheck;
	private boolean otherCheck;
	
	private double totalAmountSpent;
	
	private String homeStoreLocation;
	
	private String notes;
	
	public Customer()
	{
		
		name = "";
		
		email = "";
		
		dogCheck = false;
		catCheck = false;
		birdCheck = false;
		fishCheck = false;
		otherCheck = false;
		
		totalAmountSpent = 0;
		
		homeStoreLocation = "";
		
		notes = "";
		
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setTotal(double amount)
	{
		this.totalAmountSpent = amount;
	}
	
	public double getTotal()
	{
		return this.totalAmountSpent;
	}
	
	public void setLocation(String location)
	{
		this.homeStoreLocation = location;
	}
	
	public String getLocation()
	{
		return homeStoreLocation;
	}
	
	public void setNotes(String notes)
	{
		this.notes = notes;
	}
	
	public String getNotes()
	{
		return this.notes;
	}
	
	
	// Checkboxes change
	
	public void setDog(boolean dog)
	{
		this.dogCheck = dog;
	}
	
	public boolean getDog()
	{
		return this.dogCheck;
	}
	
	public void setCat(boolean cat)
	{
		this.catCheck = cat;
	}
	
	public boolean getCat()
	{
		return this.catCheck;
	}
	
	public void setBird(boolean bird)
	{
		this.birdCheck = bird;
	}
	
	public boolean getBird()
	{
		return this.birdCheck;
	}
	
	public void setFish(boolean fish)
	{
		this.fishCheck = fish;
	}
	
	public boolean getFish()
	{
		return this.fishCheck;
	}
	
	public void setOther(boolean other)
	{
		this.otherCheck = other;
	}
	
	public boolean getOther()
	{
		return this.otherCheck;
	}
	
	//For sorting
	public int compareTo(Customer other) 
	{
	
        return this.getName().compareTo(other.getName());
 
    }
	

	
	
}
