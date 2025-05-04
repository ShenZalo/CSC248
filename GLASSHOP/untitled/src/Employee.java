public class Employee
{
    public String empID;
    public String empFullName;
    public String dateHired;
    
    public Employee()
    {
        empID=" ";
        empFullName=" ";
        dateHired=" ";
    }
    
    public Employee(String empID, String empFullName, String dateHired)
    {
        this.empID=empID;
        this.empFullName=empFullName;
        this.dateHired=dateHired;
    }
    
    public void setEmpID(String empID){this.empID=empID;}
    public void setEmpFullName(String empFullName){this.empFullName=empFullName;}
    public void setDateHired(String dateHired){this.dateHired=dateHired;}
    
    public String getEmpID(){return empID;}
    public String getEmpFullName(){return empFullName;}
    public String getDateHired(){return dateHired;}

    public String toString()
    {
        String detail = " ";
        detail="\nEmployee ID: "+empID+"\nFull Name: "+empFullName+"\nDate Hired: "+dateHired;
        return detail;
    }


}
