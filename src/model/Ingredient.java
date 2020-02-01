package model;

public class Ingredient {

	    
	    public String description;
	    public int id;
	 
	    //Constructor
	    public Ingredient (String description, int id)
	    {
	     
	      this.setDescription(description);
	      this.setId(id);
	    }
	   
	    
	    public String getDescription() 
	    {
	        return description;
	    }
	    
	    public void setDescription(String description) 
	    {
	        this.description = description;
	    }
	    
	    public int getId()
	    {
	        return id;
	    }
	    
	    public void setId(int id)
	    {
	        this.id = id;
	    }
}

