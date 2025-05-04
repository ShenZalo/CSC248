public class Customer
{
   private String custID;
   private String custName;
   private int custAge;
   private String custGender;
   private String custPhone;
   private String custEmail;
   
   public Customer()
   {
       custID= " ";
       custName= " ";
       custAge=0;
       custGender=" ";
       custPhone=" ";
       custEmail=" ";
   }
   
   public Customer (String custID, String custName, int custAge, String custGender, String custPhone, String custEmail)
   {
       this.custID=custID;
       this.custName=custName;
       this.custAge=custAge;
       this.custGender=custGender;
       this.custPhone=custPhone;
       this.custEmail=custEmail;
   }
   
   public void setCustID(String custID){this.custID=custID;}
   public void setCustName(String custName){this.custName=custName;}
   public void setCustAge(int custAge){this.custAge=custAge;}
   public void setCustGender(String custGender){this.custGender=custGender;}
   public void setCustPhone(String custPhone){this.custPhone=custPhone;}
   public void setCustEmail(String custEmail){this.custEmail=custEmail;}
   
   public String getCustID(){return custID;}
   public String getCustName(){return custName;}
   public int getCustAge(){return custAge;}
   public String getCustGender(){return custGender;}
   public String getCustPhone(){return custPhone;}
   public String getCustEmail(){return custEmail;}

    public String toString() {
        return"\nCustomer ID: " + custID +
                "\nCustomer Name: " + custName+
                "\nCustomer Age: " + custAge+
                "\nCustomer Gender: " + custGender+
                "\nCustomer Phone: " + custPhone+
                "\nCustomer Email: " + custEmail+
                "\n";

    }



}
