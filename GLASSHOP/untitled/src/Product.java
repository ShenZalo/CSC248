public class Product
{
    private String prdID;
    private String prdName;
    private String prdCategory;
    private double prdPrice;

    public Product (String prdID, String prdName, String prdCategory,double prdPrice){
        this.prdID=prdID;
        this.prdName= prdName;
        this.prdCategory= prdCategory;
        this.prdPrice=prdPrice;
    }

    public String getPrdID(){return prdID;}
    public String getPrdName(){return prdName;}
    public String getPrdCategory(){return prdCategory;}
    public double getPrdPrice(){return prdPrice;}

    public void setPrdName(String prdName){this.prdName=prdName;}
    public void setPrdID(String prdID){this.prdID= prdID;}
    public void setPrdCategory(String prdCategory){this.prdCategory=prdCategory;}
    public void setPrdPrice(double prdPrice){this.prdPrice=prdPrice;}

    public String toString() {
        return ("\nProduct ID:" + prdID + "\nProduct Name:" + prdName + "\nProduct Category:" + prdCategory + "\nProduct Price:RM" + prdPrice);
    }

}