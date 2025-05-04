public class Sale {
    private String saleID;
    private Customer cust;
    private Employee emp;
    private Product prd;
    private String saleDate;
    private int saleQty;

    public Sale(String id, Customer c, Employee e, Product p, int qty, String date) {
        saleID = id;
        cust = c;
        emp = e;
        prd = p;
        saleQty = qty;
        saleDate = date;
    }

    public String getSaleID() {return saleID;}
    public Customer getCust() {return cust;}
    public Employee getEmp() {return emp;}
    public Product getPrd() {return prd;}
    public String getSaleDate() {return saleDate;}
    public int getSaleQty() {return saleQty;}

    public void setSaleID(String id) {saleID = id;}
    public void setCust(Customer c) {cust = c;}
    public void setEmp(Employee e) {emp = e;}
    public void setPrd(Product p) {prd = p;}
    public void setSaleQty(int qty) {saleQty = qty;}
    public void setSaleDate(String date) {saleDate = date;}

    public String toString() {
        return(
                "\n"+
                        "\n╔═══════════════════════════════════════════════════╗"+
                        "\n  Sale ID: "+getSaleID()+
                        "\n--------------------------------------------------------------"+
                        "\n         ☆*:.｡. .｡.:*☆ Customer details☆*:.｡. .｡.:*☆                \n"+cust.toString()+
                        "\n--------------------------------------------------------------"+
                        "\n         ☆*:.｡. .｡.:*☆ Employee details☆*:.｡. .｡.:*☆                \n"+
                        "\nEmployee ID: "+emp.getEmpID()+
                        "\nEmployee Name: "+emp.getEmpFullName()+
                        "\n--------------------------------------------------------------"+
                        "\n         ☆*:.｡. .｡.:*☆ Product details☆*:.｡. .｡.:*☆                 \n"+prd.toString()+
                        "\n--------------------------------------------------------------"+
                        "\nSale quantity:"+getSaleQty()+
                        "\n=============================================================="+
                        "\nTotal Price:RM"+String.format("%.2f", (saleQty * prd.getPrdPrice()))+
                        "\nSale date:"+getSaleDate()+
                        "\n╚═══════════════════════════════════════════════════╝") ;
    }

}
