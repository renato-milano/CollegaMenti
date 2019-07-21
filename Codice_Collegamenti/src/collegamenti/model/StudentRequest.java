package collegamenti.model;

public class StudentRequest {

	private String Email;
	private String Category;
	private String Description;
	private int ID;
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public StudentRequest(int ID,String Email, String Category, String Description) {
	this.ID= ID;
	this.Email = Email;
	this.Category = Category;
	this.Description = Description;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	@Override
	public boolean equals(Object e) {
		StudentRequest a=(StudentRequest) e;
		if(this.ID==a.getID()) {
		return true;	
		}
		 return false;
	}
}
