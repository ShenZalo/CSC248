public  class Appointment
{
    private String aptID;
    private String aptDate;
    protected Customer customer;
    protected Employee employee;
    
    public Appointment()
    {
        aptID = " ";
        aptDate = " ";
        customer = null;
        employee = null;        
    }
    
    public Appointment(String aptID, String aptDate, Customer customer, Employee employee)
    {
        this.aptID=aptID;
        this.aptDate=aptDate;
        this.customer=customer;
        this.employee=employee;
    }
    
    
    public void setAptID(String aptID){this.aptID=aptID;}
    public void setAptDate(String aptDate){this.aptDate=aptDate;}
    public void setCustomer(Customer customer){this.customer=customer;}
    public void setEmployee(Employee employee){this.employee=employee;}
    
    public String getAptID(){return aptID;}
    public String getAptDate(){return aptDate;}
    public Customer getCustomer(){return customer;}
    public Employee getEmployee(){return employee;}

    public String toString()
    {
        return(
                "\n╔══════════════════════════════════════════════════╗"+
                        "\n  Appointment ID:"+getAptID()+"                                        "+
                        "\n  Appointment Date:"+getAptDate()+"                                "+
                        "\n╚══════════════════════════════════════════════════╝"+
                        "\n"+
                        "\n            ☆*:.｡. .｡.:*☆Customer☆*:.｡. .｡.:*☆           \n"+getCustomer()+

                        "\n            ☆*:.｡. .｡.:*☆Employee☆*:.｡. .｡.:*☆           \n"+
                        "\nEmployee ID: "+employee.getEmpID()+
                        "\nEmployee Name: "+employee.getEmpFullName()+
                        "\n───────────────────────────────────────────────────");
    }
}
