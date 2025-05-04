import java.util.*;
import java.io.*;

public class LLMain {

    public static void Menu() {

        System.out.println("==================================================");
        System.out.println("[1] Employee");
        System.out.println("[2] Customer");
        System.out.println("[3] Product");
        System.out.println("[4] Appointment");
        System.out.println("[5] Sale");
        System.out.println("[6] Search for data");
        System.out.println("[7] Finalise all data");
        System.out.println("[8] Exit");
        System.out.println("===================================================");
    }

    public static boolean listEmployee(LinkedList<Employee> employeeLL) {
        System.out.println("==================================================");
        System.out.println("Employee List and Details");
        System.out.println("==================================================");
        boolean list = true;
        Employee current = employeeLL.getFirst();
        while (current != null) {
            System.out.println(current.toString());
            current = employeeLL.getNext();
        }
        if (employeeLL.isEmpty()) {
            System.out.println("No employee found.");
            list = false;
        }
        return list;
    }

    public static Employee searchEmployee(LinkedList<Employee> employeeLL, String empID) {
        for (int i = 0; i < employeeLL.size(); i++) {
            Employee employee = employeeLL.get(i);
            if (employee.getEmpID().equals(empID)) {
                return employee;
            }
        }
        return null;
    }

    public static void addEmployee(LinkedList<Employee> employeeLL) {
        Scanner sc = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("Add Employee");
        System.out.println("===================================================");
        System.out.println("Please fill out the following:");
        System.out.println("Enter Employee ID: ");
        String empID = sc.nextLine();
        System.out.println("Enter Employee Name: ");
        String empName = sc.nextLine();
        System.out.println("Hire Date: ");
        String hireDate = sc.nextLine();
        Employee newEmployee = new Employee(empID, empName, hireDate);
        employeeLL.add(newEmployee);
        System.out.println("[ Employee added successfully! ]");
        System.out.println(newEmployee.toString());
    }

    public static void updateEmployee(LinkedList<Employee> employeeLL) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Employee ID to update: ");
        String empID = sc.nextLine();
        Employee empChosen = searchEmployee(employeeLL, empID);
        if (empChosen != null) {
            System.out.println("Enter new Employee Name: ");
            String empName = sc.nextLine();
            empChosen.setEmpFullName(empName);

            System.out.println("Enter new Hire Date: ");
            String hireDate = sc.nextLine();
            empChosen.setDateHired(hireDate);

            System.out.println("[ Employee updated successfully! ]");
        } else {
            System.out.println("[ No matching employee found for update. ]");
        }
    }

    public static void deleteEmployee(LinkedList<Employee> employeeLL, LinkedList<Appointment> appointmentLL, LinkedList<Sale> saleLL, String empID) {
        Employee empChosen = searchEmployee(employeeLL, empID);
        LinkedList<Employee> temp= new LinkedList<>();
        if (empChosen != null) {
            // Remove related appointments
            LinkedList<Appointment> tempApp = new LinkedList<>();
            for (int i = 0; i < appointmentLL.size(); i++) {
                Appointment currentApp = appointmentLL.get(i);
                if (!currentApp.getEmployee().equals(empChosen)) {
                    tempApp.addLast(currentApp);
                }
            }
            appointmentLL.clear();
            for (int i = 0; i < tempApp.size(); i++) {
                appointmentLL.addLast(tempApp.get(i));
            }

            // Remove related sales
            LinkedList<Sale> tempSale = new LinkedList<>();
            for (int i = 0; i < saleLL.size(); i++) {
                Sale currentSale = saleLL.get(i);
                if (!currentSale.getEmp().equals(empChosen)) {
                    tempSale.addLast(currentSale);
                }
            }
            saleLL.clear();
            for (int i = 0; i < tempSale.size(); i++) {
                saleLL.addLast(tempSale.get(i));
            }

            Employee current;
            while (!employeeLL.isEmpty()){
                current = employeeLL.removeFirst();
                if (!current.equals(empChosen)) {
                    temp.addLast(current);
                }
            }

            while (!temp.isEmpty())
            {
                current = temp.removeFirst();
                employeeLL.addLast(current);
            }

            System.out.println("[ Employee record deleted successfully! ]");
        }else{
            System.out.println("[ No matching employee found for deletion. ]");
        }
    }




    public static boolean listCustomer(LinkedList<Customer> customerLL){
        System.out.println("==================================================");
        System.out.println("Customer List and Details");
        System.out.println("==================================================");
        boolean list=true;
        Customer current = customerLL.getFirst();
        while (current!=null){
            System.out.println(current.toString());
            current = customerLL.getNext();
        }
        if (customerLL.isEmpty()) {
            System.out.println("No customer found.");
            list=false;
        }
        return list;
    }

    public static Customer searchCustomer(LinkedList<Customer> customerLL, String custID) {
        for (int i = 0; i < customerLL.size(); i++) {
            Customer customer = customerLL.get(i);
            if (customer.getCustID().equals(custID)) {
                return customer;
            }
        }
        return null;
    }

    public static void addCustomer(LinkedList<Customer> customerLL) {
        Scanner sc = new Scanner(System.in);
        Scanner scno = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("Add Customer");
        System.out.println("===================================================");
        System.out.println("Please fill out the following:");
        System.out.println("Enter Customer ID: ");
        String custID = sc.nextLine();
        System.out.println("Enter Customer Name: ");
        String custName = sc.nextLine();
        System.out.println("Enter Customer Age: ");
        int custAge = scno.nextInt();
        System.out.println("Enter Customer Gender: ");
        String custGender = sc.nextLine();
        System.out.println("Enter Customer Phone: ");
        String custPhone = sc.nextLine();
        System.out.println("Enter Customer Email: ");
        String custEmail = sc.nextLine();
        Customer newCustomer = new Customer(custID, custName, custAge, custGender, custPhone, custEmail);
        customerLL.add(newCustomer);
        System.out.println("[ Customer added successfully! ]");
        System.out.println(newCustomer.toString());
    }

    public static void updateCustomer(LinkedList<Customer> customerLL) {
        Scanner sc = new Scanner(System.in);
        Scanner scno = new Scanner(System.in);
        System.out.println("Enter Customer ID to update: ");
        String custID = sc.nextLine();
        Customer custChosen = searchCustomer(customerLL, custID);
        if (custChosen != null) {
            System.out.println("Enter new Customer Name: ");
            String custName = sc.nextLine();
            custChosen.setCustName(custName);

            System.out.println("Enter new Customer Age: ");
            int custAge = scno.nextInt();
            custChosen.setCustAge(custAge);

            System.out.println("Enter new Customer Gender: ");
            String custGender = sc.nextLine();
            custChosen.setCustGender(custGender);

            System.out.println("Enter new Customer Phone: ");
            String custPhone = sc.nextLine();
            custChosen.setCustPhone(custPhone);

            System.out.println("Enter new Customer Email: ");
            String custEmail = sc.nextLine();
            custChosen.setCustEmail(custEmail);

            System.out.println("[ Customer updated successfully! ]");
        } else {
            System.out.println("[ No matching customer found for update. ]");
        }
    }

    public static void deleteCustomer(LinkedList<Customer> customerLL, LinkedList<Appointment> appointmentLL, LinkedList<Sale> saleLL, String custID) {
        Customer custChosen = searchCustomer(customerLL, custID);  // Find the customer by ID
        LinkedList<Customer> temp = new LinkedList<>();  // Temporary list to hold customers after removal
        if (custChosen != null) {
            // Remove related appointments
            LinkedList<Appointment> tempApp = new LinkedList<>();
            for (int i = 0; i < appointmentLL.size(); i++) {
                Appointment currentApp = appointmentLL.get(i);
                if (!currentApp.getCustomer().equals(custChosen)) {  // If the appointment is not related to the customer
                    tempApp.addLast(currentApp);
                }
            }
            appointmentLL.clear();  // Clear the original appointment list
            for (int i = 0; i < tempApp.size(); i++) {
                appointmentLL.addLast(tempApp.get(i));  // Restore the filtered appointments
            }

            // Remove related sales
            LinkedList<Sale> tempSale = new LinkedList<>();
            for (int i = 0; i < saleLL.size(); i++) {
                Sale currentSale = saleLL.get(i);
                if (!currentSale.getCust().equals(custChosen)) {  // If the sale is not related to the customer
                    tempSale.addLast(currentSale);
                }
            }
            saleLL.clear();  // Clear the original sale list
            for (int i = 0; i < tempSale.size(); i++) {
                saleLL.addLast(tempSale.get(i));  // Restore the filtered sales
            }

            // Remove customer from customerLL
            Customer current;
            while (!customerLL.isEmpty()) {
                current = customerLL.removeFirst();
                if (!current.equals(custChosen)) {  // If it's not the customer to delete, add it to the temp list
                    temp.addLast(current);
                }
            }

            // Restore the remaining customers to customerLL
            while (!temp.isEmpty()) {
                current = temp.removeFirst();
                customerLL.addLast(current);
            }

            System.out.println("[ Customer and related data deleted successfully! ]");
        } else {
            System.out.println("[ No matching customer found for deletion. ]");
        }
    }


    public static boolean listProduct(LinkedList<Product> productLL){
        System.out.println("==================================================");
        System.out.println("Product List and Details");
        System.out.println("==================================================");
        Product current = productLL.getFirst();
        boolean list = true;
        while (current != null) {
            System.out.println(current.toString());
            current = productLL.getNext();
        }
        if (productLL.isEmpty()) {
            list = false;
            System.out.println("No product found.");
        }
        return list;
    }


    public static Product searchProduct(LinkedList<Product> productLL, String prdID) {
        for (int i = 0; i < productLL.size(); i++) {
            Product product = productLL.get(i);
            if (product.getPrdID().equals(prdID)) {
                return product;
            }
        }
        return null;
    }

    public static void addProduct(LinkedList<Product> productLL) {
        Scanner sc = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("Add Product");
        System.out.println("==================================================");
        System.out.println("Please fill out the following:");
        System.out.println("Set the product's ID:");
        String prdID = sc.nextLine();
        System.out.println("Set the product's name:");
        String prdName = sc.nextLine();
        System.out.println("Set the product's category:");
        String prdCategory = sc.nextLine();
        System.out.println("Set the product's price: RM");
        double prdPrice = sc.nextDouble();
        Product product = new Product(prdID, prdName, prdCategory, prdPrice);
        productLL.addLast(product);
        System.out.println("[ Product added successfully! ]");
        System.out.println(product.toString());
    }

    public static void updateProduct(LinkedList<Product> productLL) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Product ID to update: ");
        String prdID = sc.nextLine();
        Product prdChosen = searchProduct(productLL, prdID);
        if (prdChosen != null) {
            System.out.println("Enter new Product Name: ");
            String prdName = sc.nextLine();
            prdChosen.setPrdName(prdName);

            System.out.println("Enter new Product Category: ");
            String prdCategory = sc.nextLine();
            prdChosen.setPrdCategory(prdCategory);

            System.out.println("Enter new Product Price: RM");
            double prdPrice = sc.nextDouble();
            prdChosen.setPrdPrice(prdPrice);

            System.out.println("[ Product updated successfully! ]");
        } else {
            System.out.println("[ No matching product found for update. ]");
        }
    }

    public static void deleteProduct(LinkedList<Product> productLL, LinkedList<Sale> saleLL, String productID) {
        Product prodChosen = searchProduct(productLL, productID);  // Find the product by ID
        LinkedList<Product> temp = new LinkedList<>();  // Temporary list to hold products after removal
        if (prodChosen != null) {
            // Remove related sales
            LinkedList<Sale> tempSale = new LinkedList<>();
            for (int i = 0; i < saleLL.size(); i++) {
                Sale currentSale = saleLL.get(i);
                if (!currentSale.getPrd().equals(prodChosen)) {  // If the sale is not related to the product
                    tempSale.addLast(currentSale);
                }
            }
            saleLL.clear();  // Clear the original sale list
            for (int i = 0; i < tempSale.size(); i++) {
                saleLL.addLast(tempSale.get(i));  // Restore the filtered sales
            }

            // Remove product from productLL
            Product current;
            while (!productLL.isEmpty()) {
                current = productLL.removeFirst();
                if (!current.equals(prodChosen)) {  // If it's not the product to delete, add it to the temp list
                    temp.addLast(current);
                }
            }

            // Restore the remaining products to productLL
            while (!temp.isEmpty()) {
                current = temp.removeFirst();
                productLL.addLast(current);
            }

            System.out.println("[ Product and related data deleted successfully! ]");
        } else {
            System.out.println("[ No matching product found for deletion. ]");
        }
    }


    public static boolean listAppointment(LinkedList<Appointment> appointmentLL){
        System.out.println("==================================================");
        System.out.println("Appointment List and Details");
        System.out.println("==================================================");
        Appointment current = appointmentLL.getFirst();
        boolean list = true;
        while (current!=null){
            System.out.println(current.toString());
            current = appointmentLL.getNext();
        }
        if (appointmentLL.isEmpty())
        {
            list = false;
            System.out.println("No appointment found.");
        }
        return list;
    }

    public static Appointment searchAppointment(LinkedList<Appointment> appointmentLL, String aptID) {
        for (int i = 0; i < appointmentLL.size(); i++) {
            Appointment appointment = appointmentLL.get(i);
            if (appointment.getAptID().equals(aptID)) {
                return appointment;
            }
        }
        return null;
    }

    public static void addAppointment(LinkedList<Appointment> appointmentLL, LinkedList<Customer> customerLL, LinkedList<Employee> employeeLL) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add Appointment");
        System.out.println("==================================================");
        System.out.println("Set one (1) employee who will be managing the appointment.");
        for (int i = 0; i < employeeLL.size(); i++) {
            System.out.println(employeeLL.get(i));
        }
        System.out.println("Enter Employee ID: ");
        String empID = sc.nextLine();
        Employee empChosen = searchEmployee(employeeLL, empID);
        if (empChosen == null) {
            System.out.println("Employee not found. Please try again.");
            return;
        }
        System.out.println("Set one (1) customer who will be attended to the appointment.");
        for (int i = 0; i < customerLL.size(); i++) {
            System.out.println(customerLL.get(i));
        }
        System.out.println("Enter Customer ID: ");
        String custID = sc.nextLine();
        Customer custChosen = searchCustomer(customerLL, custID);
        if (custChosen == null) {
            System.out.println("Customer not found. Please try again.");
            return;
        }
        System.out.println("Please fill out the following:");
        System.out.println("Set the appointment ID:");
        String aptID = sc.nextLine();
        System.out.println("Set the appointment date:");
        String aptDate = sc.nextLine();
        Appointment apt = new Appointment(aptID, aptDate, custChosen, empChosen);
        appointmentLL.addLast(apt);
        System.out.println("[ Appointment added successfully! ]");
    }

    public static void updateAppointment(LinkedList<Appointment> appointmentLL, LinkedList<Customer> customerLL, LinkedList<Employee> employeeLL) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Appointment ID to update: ");
        String aptID = sc.nextLine();
        Appointment aptChosen = searchAppointment(appointmentLL, aptID);
        if (aptChosen != null) {
            System.out.println("Enter new Appointment Date: ");
            String aptDate = sc.nextLine();
            aptChosen.setAptDate(aptDate);

            System.out.println("Enter new Employee ID: ");
            String empID = sc.nextLine();
            Employee empChosen = searchEmployee(employeeLL, empID);
            if (empChosen != null) {
                aptChosen.setEmployee(empChosen);
            } else {
                System.out.println("[ No matching employee found. ]");
            }

            System.out.println("Enter new Customer ID: ");
            String custID = sc.nextLine();
            Customer custChosen = searchCustomer(customerLL, custID);
            if (custChosen != null) {
                aptChosen.setCustomer(custChosen);
            } else {
                System.out.println("[ No matching customer found. ]");
            }

            System.out.println("[ Appointment updated successfully! ]");
        } else {
            System.out.println("[ No matching appointment found for update. ]");
        }
    }

    public static void deleteAppointment(LinkedList<Appointment> appointmentLL, String aptID) {
        Appointment aptChosen = searchAppointment(appointmentLL, aptID);
        if (aptChosen != null) {
            LinkedList<Appointment> temp = new LinkedList<>();
            Appointment current;
            while (!appointmentLL.isEmpty()) {
                current = appointmentLL.removeFirst();
                if (!current.equals(aptChosen)) {
                    temp.addLast(current);
                }
            }
            while (!temp.isEmpty()) {
                appointmentLL.addLast(temp.removeFirst());
            }
            System.out.println("[ Appointment deleted successfully! ]");
        } else {
            System.out.println("[ No matching appointment found for deletion. ]");
        }
    }

    public static boolean listSale(LinkedList<Sale> saleLL){
        System.out.println("==================================================");
        System.out.println("Sale List and Details");
        System.out.println("==================================================");
        Sale current = saleLL.getFirst();
        boolean list = true;
        while (current != null){
            System.out.println(current.toString());
            current = saleLL.getNext();
        }
        if (saleLL.isEmpty()) {
            list = false;
            System.out.println("No sale found.");
        }
        return list;
    }

    public static Sale searchSale(LinkedList<Sale> saleLL, String saleID) {
        for (int i = 0; i < saleLL.size(); i++) {
            Sale sale = saleLL.get(i);
            if (sale.getSaleID().equals(saleID)) {
                return sale;
            }
        }
        return null;
    }

    public static void addSale(LinkedList<Sale> saleLL, LinkedList<Customer> customerLL, LinkedList<Employee> employeeLL, LinkedList<Product> productLL) {
        Scanner sc = new Scanner(System.in);
        Scanner scno = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("Add Sale");
        System.out.println("==================================================");
        System.out.println("State the sale ID:");
        String saleID = sc.nextLine();
        System.out.println("Set one (1) employee who managed the sale.");
        for (int i = 0; i < employeeLL.size(); i++) {
            System.out.println(employeeLL.get(i));
        }
        System.out.println("Enter Employee ID: ");
        String empID = sc.nextLine();
        Employee empChosen = searchEmployee(employeeLL, empID);
        if (empChosen == null) {
            System.out.println("Employee not found. Please try again.");
            return;
        }
        System.out.println("Set one (1) customer who bought the products in the sale.");
        for (int i = 0; i < customerLL.size(); i++) {
            System.out.println(customerLL.get(i));
        }
        System.out.println("Enter Customer ID: ");
        String custID = sc.nextLine();
        Customer custChosen = searchCustomer(customerLL, custID);
        if (custChosen == null) {
            System.out.println("Customer not found. Please try again.");
            return;
        }
        System.out.println("Set the product purchased in the sale.");
        for (int i = 0; i < productLL.size(); i++) {
            System.out.println(productLL.get(i));
        }
        System.out.println("Enter Product ID: ");
        String prdID = sc.nextLine();
        Product prdChosen = searchProduct(productLL, prdID);
        if (prdChosen == null) {
            System.out.println("Product not found. Please try again.");
            return;
        }
        System.out.println("State the quantity of the product bought:");
        int prdQty = scno.nextInt();
        System.out.println("State the sale date:");
        String saleDate = sc.nextLine();
        Sale sale = new Sale(saleID, custChosen, empChosen, prdChosen, prdQty, saleDate);
        saleLL.addLast(sale);
        System.out.println("[ Sale added successfully! ]");
    }

    public static void updateSale(LinkedList<Sale> saleLL, LinkedList<Customer> customerLL, LinkedList<Employee> employeeLL, LinkedList<Product> productLL) {
        Scanner sc = new Scanner(System.in);
        Scanner scno = new Scanner(System.in);
        System.out.println("Enter Sale ID to update: ");
        String saleID = sc.nextLine();
        Sale saleChosen = searchSale(saleLL, saleID);
        if (saleChosen != null) {
            System.out.println("Enter new Sale Date: ");
            String saleDate = sc.nextLine();
            saleChosen.setSaleDate(saleDate);

            System.out.println("Enter new Employee ID: ");
            String empID = sc.nextLine();
            Employee empChosen = searchEmployee(employeeLL, empID);
            if (empChosen != null) {
                saleChosen.setEmp(empChosen);
            } else {
                System.out.println("[ No matching employee found. ]");
            }

            System.out.println("Enter new Customer ID: ");
            String custID = sc.nextLine();
            Customer custChosen = searchCustomer(customerLL, custID);
            if (custChosen != null) {
                saleChosen.setCust(custChosen);
            } else {
                System.out.println("[ No matching customer found. ]");
            }

            System.out.println("Enter new Product ID: ");
            String prdID = sc.nextLine();
            Product prdChosen = searchProduct(productLL, prdID);
            if (prdChosen != null) {
                saleChosen.setPrd(prdChosen);
            } else {
                System.out.println("[ No matching product found. ]");
            }

            System.out.println("Enter new Product Quantity: ");
            int prdQty = scno.nextInt();
            saleChosen.setSaleQty(prdQty);

            System.out.println("[ Sale updated successfully! ]");
        } else {
            System.out.println("[ No matching sale found for update. ]");
        }
    }

    public static void deleteSale(LinkedList<Sale> saleLL, String saleID) {
        Sale saleChosen = searchSale(saleLL, saleID);
        if (saleChosen != null) {
            LinkedList<Sale> temp = new LinkedList<>();
            Sale current;
            while (!saleLL.isEmpty()) {
                current = saleLL.removeFirst();
                if (!current.equals(saleChosen)) {
                    temp.addLast(current);
                }
            }
            while (!temp.isEmpty()) {
                saleLL.addLast(temp.removeFirst());
            }
            System.out.println("[ Sale deleted successfully! ]");
        } else {
            System.out.println("[ No matching sale found for deletion. ]");
        }
    }



    //HEREEEEEEEE CUST
    public static void readCustFile(LinkedList<Customer> customerLL, String fileName) {
        customerLL.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 6) {
                    String custID = details[0];
                    String custName = details[1];
                    int custAge = Integer.parseInt(details[2]);
                    String custGender = details[3];
                    String custPhone = details[4];
                    String custEmail = details[5];
                    customerLL.add(new Customer(custID, custName, custAge, custGender, custPhone, custEmail));
                } else {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
            System.out.println("Data loaded successfully from " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    //HEREEEEEE
    public static void writeCustFile(LinkedList<Customer> customerLL, String fileName) {
        if (customerLL == null || customerLL.isEmpty()) {
            System.out.println("Customer list is empty. Nothing to write.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < customerLL.size(); i++) {
                Customer customer = customerLL.get(i);
                bw.write(customer.getCustID() + "," +
                        customer.getCustName() + "," +
                        customer.getCustAge() + "," +
                        customer.getCustGender() + "," +
                        customer.getCustPhone() + "," +
                        customer.getCustEmail());
                bw.newLine();
            }
            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


    //EMPLOYEEEEEEEE
    public static void readEmpFile(LinkedList<Employee> employeeLL, String fileName) {
        employeeLL.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 3) {
                    String empID = details[0];
                    String empFullName = details[1];
                    String empDateHired = details[2];
                    employeeLL.add(new Employee(empID, empFullName, empDateHired));
                } else {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
            System.out.println("Data loaded successfully from " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    //EMPLOYEEEE WRITE
    public static void writeEmpFile(LinkedList<Employee> employeeLL, String fileName) {
        if (employeeLL == null || employeeLL.isEmpty()) {
            System.out.println("Employee list is empty. Nothing to write.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < employeeLL.size(); i++) {
                Employee employee = employeeLL.get(i);
                bw.write(employee.getEmpID() + "," +
                        employee.getEmpFullName() + "," +
                        employee.getDateHired());
                bw.newLine();
            }
            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    //PRODUCT READDDD
    public static void readProdFile(LinkedList<Product> productLL, String fileName) {
        productLL.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 4) {
                    String prodID = details[0];
                    String prodName = details[1];
                    String prodCategory = details[2];
                    double prodPrice = Double.parseDouble(details[3]);
                    productLL.add(new Product(prodID, prodName, prodCategory, prodPrice));
                } else {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
            System.out.println("Data loaded successfully from " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    //PRODUCT WRITE
    public static void writeProdFile(LinkedList<Product> productLL, String fileName) {
        if (productLL == null || productLL.isEmpty()) {
            System.out.println("Product list is empty. Nothing to write.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < productLL.size(); i++) {
                Product product = productLL.get(i);
                bw.write(product.getPrdID() + "," +
                        product.getPrdName() + "," +
                        product.getPrdCategory() + "," +
                        product.getPrdPrice());
                bw.newLine();
            }
            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    //APPOINTMENT READDDD
    public static void readAppFile(LinkedList<Appointment> appointmentLL, LinkedList<Customer> customerLL, LinkedList<Employee> employeeLL, String fileName) {
        appointmentLL.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 4) { // Ensure there are 4 fields
                    String aptID = details[0];
                    String aptDate = details[1];
                    String custID = details[2];
                    String empID = details[3];

                    Customer customer = searchCustomer(customerLL, custID);
                    Employee employee = searchEmployee(employeeLL, empID);

                    if (customer != null && employee != null) {
                        appointmentLL.add(new Appointment(aptID, aptDate, customer, employee));
                    } else {
                        System.out.println("Skipping invalid line due to missing customer or employee: " + line);
                    }
                } else {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
            System.out.println("Data loaded successfully from " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    //APPOINTMENT WRITE
    public static void writeAppFile(LinkedList<Appointment> appointmentLL, String fileName) {
        if (appointmentLL == null || appointmentLL.isEmpty()) {
            System.out.println("Appointment list is empty. Nothing to write.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < appointmentLL.size(); i++) {
                Appointment appointment = appointmentLL.get(i);
                bw.write(appointment.getAptID() + "," +
                        appointment.getAptDate() + "," +
                        appointment.getCustomer().getCustID() + "," +
                        appointment.getEmployee().getEmpID());
                bw.newLine();
            }
            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


    //SALE READDDD
    public static void readSaleFile(LinkedList<Sale> saleLL, LinkedList<Customer> customerLL, LinkedList<Employee> employeeLL, LinkedList<Product> productLL, String fileName) {
        saleLL.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 6) { // Ensure there are 6 fields
                    String saleID = details[0];
                    String custID = details[1];
                    String empID = details[2];
                    String prdID = details[3];
                    int saleQty = Integer.parseInt(details[4]);
                    String saleDate = details[5];

                    Customer customer = searchCustomer(customerLL, custID);
                    Employee employee = searchEmployee(employeeLL, empID);
                    Product product = searchProduct(productLL, prdID);

                    if (customer != null && employee != null && product != null) {
                        saleLL.add(new Sale(saleID, customer, employee, product, saleQty, saleDate));
                    } else {
                        System.out.println("Skipping invalid line due to missing customer, employee, or product: " + line);
                    }
                } else {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
            System.out.println("Data loaded successfully from " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    //SALE WRITE
    public static void writeSaleFile(LinkedList<Sale> saleLL, String fileName) {
        if (saleLL == null || saleLL.isEmpty()) {
            System.out.println("Sale list is empty. Nothing to write.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < saleLL.size(); i++) {
                Sale sale = saleLL.get(i);
                bw.write(sale.getSaleID() + "," +
                        sale.getCust().getCustID() + "," +
                        sale.getEmp().getEmpID() + "," +
                        sale.getPrd().getPrdID() + "," +
                        sale.getSaleQty() + "," +
                        sale.getSaleDate());
                bw.newLine();
            }
            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    ////MAINNNNN
    public static void  main (String[] args) {
        Scanner sc = new Scanner(System.in);
        Scanner scno = new Scanner(System.in);
        LinkedList<Employee> employeeLL = new LinkedList<>();
        LinkedList<Customer> customerLL = new LinkedList<>();
        LinkedList<Product> productLL = new LinkedList<>();
        LinkedList <Appointment> appointmentLL = new LinkedList<>();
        LinkedList <Sale> saleLL = new LinkedList<>();


        System.out.println("==================================================");
        System.out.println("Welcome to GlassHopper! System");
        System.out.println("==================================================");
        System.out.println("Please verify yourself.");
        String username;
        String password;
        System.out.println("Username:");
        username = sc.nextLine();
        System.out.println("Password:");
        password = sc.nextLine();
        if (!username.equals("admin") || !password.equals("123"))
        {
            while (!username.equals("admin") || !password.equals("123")) {
                System.out.println("Invalid username or password.");
                System.out.println("Please try again.");
                System.out.println("Username:");
                username = sc.nextLine();
                System.out.println("Password:");
                password = sc.nextLine();
            }
        }
        else
        {
            System.out.println("Welcome "+ username);
            System.out.println("Loading data from database...");
            readEmpFile(employeeLL, "Employee.txt");
            readCustFile(customerLL, "Customer.txt");
            readProdFile(productLL, "Product.txt");
            readAppFile(appointmentLL, customerLL, employeeLL, "Appointment.txt");
            readSaleFile(saleLL, customerLL, employeeLL, productLL, "Sale.txt");
        }


        boolean loop = true;
        do{
            Menu();

            int menuchoice = scno.nextInt();
            while (menuchoice < 1 || menuchoice > 8)
            {
                System.out.println("Invalid input.");
                System.out.println("Input your choice (1-8)");
                menuchoice = scno.nextInt();
                if (menuchoice < 1 || menuchoice > 8)
                {
                    break;
                }
            }
            if (menuchoice==1)
            {

                System.out.println("Employee Menu");
                System.out.println("==================================================");
                System.out.println("[1] View Employee List and Details");
                System.out.println("[2] Add Employee");
                System.out.println("[3] Update Employee");
                System.out.println("[4] Delete Employee");
                System.out.println("[5] Finalise Employee List and Details");
                System.out.println("[6] Exit Employee Menu");
                int empMenuChoice = scno.nextInt();
                while (empMenuChoice < 1 || empMenuChoice > 6) {
                    System.out.println("Invalid input.");
                    System.out.println("Input your choice (1-6)");
                    empMenuChoice = scno.nextInt();
                    if (empMenuChoice < 1 || empMenuChoice > 6) {
                        break;
                    }
                }
                if (empMenuChoice == 1)
                {
                    //Show Employee List and Details
                    listEmployee (employeeLL);
                }
                else if (empMenuChoice == 2)//Add employees
                {
                    addEmployee(employeeLL);
                }
                else if (empMenuChoice == 3)//update employee
                {
                    System.out.println("Update Employee");
                    System.out.println("==================================================");
                    updateEmployee(employeeLL);
                }
                else if(empMenuChoice == 4) {
                    boolean list = false;
                    list = listEmployee(employeeLL);
                    if (list) {
                        System.out.println("Delete Employee record..");
                        System.out.println("==================================================");
                        System.out.println("Enter Employee ID to delete: ");
                        String empID = sc.nextLine();
                        deleteEmployee(employeeLL, appointmentLL, saleLL, empID);
                    }
                    else{
                        System.out.println("No employee found.");
                    }
                }

                else if (empMenuChoice == 5)
                {
                    System.out.println("==================================================");
                    System.out.println("Finalising Employee List and Details");
                    System.out.println("==================================================");
                    System.out.println("Printing it to the database...");
                    String fileName = "Employee.txt";
                    writeEmpFile(employeeLL, fileName);
                    System.out.println("Complete. Returning to menu.");
                }

                else if (empMenuChoice == 6)
                {
                    System.out.println("Returning to main menu...");
                }
            }

            else if (menuchoice==2)
            {


                System.out.println("Customer Menu");
                System.out.println("==================================================");
                System.out.println("[1] View Customer List and Details");
                System.out.println("[2] Add Customer");
                System.out.println("[3] Update Customer");
                System.out.println("[4] Delete Customer");
                System.out.println("[5] Print Customer List and Details");
                System.out.println("[6] Exit Customer Menu");
                int custMenuChoice = scno.nextInt();
                while (custMenuChoice < 1 || custMenuChoice > 6) {
                    System.out.println("Invalid input.");
                    System.out.println("Input your choice (1-6)");
                    custMenuChoice = scno.nextInt();
                    if (custMenuChoice < 1 || custMenuChoice > 6) {
                        break;
                    }
                }
                if (custMenuChoice == 1)
                {
                    // Call listCustomer to display the customer list and details
                    listCustomer(customerLL);
                }
                else if (custMenuChoice == 2)//Add employees
                {
                    addCustomer(customerLL);
                }
                else if (custMenuChoice == 3)//update employee
                {
                    System.out.println("Update Customer");
                    System.out.println("==================================================");
                    updateCustomer(customerLL);
                }
                else if(custMenuChoice == 4) {
                    boolean list = false;
                    list = listCustomer(customerLL);
                    if (list) {
                        System.out.println("Delete Customer record..");
                        System.out.println("==================================================");
                        System.out.println("Enter Customer ID to delete: ");
                        String custID = sc.nextLine();
                        deleteCustomer(customerLL, appointmentLL, saleLL, custID);
                    }
                    else{
                        System.out.println("No customer found.");
                    }
                }

                else if (custMenuChoice == 5)
                {
                    System.out.println("==================================================");
                    System.out.println("Finalising Customer List and Details");
                    System.out.println("==================================================");
                    System.out.println("Printing it to the database...");
                    String fileName = "Customer.txt";
                    writeCustFile(customerLL, fileName);
                    System.out.println("Complete. Returning to menu.");

                }

                else if (custMenuChoice == 6)
                {
                    System.out.println("Returning to main menu...");
                }
            }

            else if (menuchoice == 3)
            {
                System.out.println("Product Menu");
                System.out.println("==================================================");
                System.out.println("[1] View Product List and Details");
                System.out.println("[2] Add Product");
                System.out.println("[3] Update Product");
                System.out.println("[4] Delete Product");
                System.out.println("[5] Print Product List and Details");
                System.out.println("[6] Exit Product Menu");
                int prdmenuchoice = scno.nextInt();
                while (prdmenuchoice < 1 || prdmenuchoice > 6)
                {
                    System.out.println("Invalid input.");
                    System.out.println("Input your choice (1-6)");
                    prdmenuchoice = scno.nextInt();
                    if (prdmenuchoice < 1 || prdmenuchoice > 6)
                    {
                        break;
                    }
                }

                if(prdmenuchoice ==1)
                {
                    System.out.println("View Product List and Details");
                    System.out.println("==================================================");
                    // Call listProduct to display the product list and details
                    listProduct(productLL);
                }
                else if(prdmenuchoice == 2)
                {
                    addProduct(productLL);
                }
                else if (prdmenuchoice == 3)
                {
                    System.out.println("Update Product");
                    System.out.println("==================================================");
                    updateProduct(productLL);
                }
                else if(prdmenuchoice == 4) {
                    System.out.println("Delete Product record..");
                    System.out.println("==================================================");
                    System.out.println("Enter Product ID to delete: ");
                    String prdID = sc.nextLine();
                    deleteProduct(productLL, saleLL, prdID);
                }
                else if(prdmenuchoice == 5)
                {
                    System.out.println("Finalising Product List and Details");
                    System.out.println("==================================================");
                    System.out.println("Printing it to the database...");
                    String fileName = "Product.txt";
                    writeProdFile(productLL, fileName);
                    System.out.println("Complete. Returning to menu.");
                }
                else if (prdmenuchoice == 6)
                {
                    System.out.println("Returning to main menu...");
                }
            }
            else if (menuchoice == 4)
            {
                System.out.println("Appointment Menu");
                System.out.println("==================================================");
                System.out.println("[1] View Appointment List and Details");
                System.out.println("[2] Add Appointment");
                System.out.println("[3] Update Appointment");
                System.out.println("[4] Delete Appointment");
                System.out.println("[5] Print Appointment List and Details");
                System.out.println("[6] Exit Appointment Menu");
                int appmenuchoice = scno.nextInt();
                while (appmenuchoice < 1 || appmenuchoice > 6)
                {
                    System.out.println("Invalid input.");
                    System.out.println("Input your choice (1-6)");
                    appmenuchoice = scno.nextInt();
                    if (appmenuchoice < 1 || appmenuchoice > 6)
                    {
                        break;
                    }
                }
                if (appmenuchoice == 1)
                {
                    System.out.println("View Appointment List and Details");
                    System.out.println("==================================================");
                    listAppointment(appointmentLL);
                }
                else if (appmenuchoice == 2) {

                    addAppointment(appointmentLL, customerLL, employeeLL);
                }


                else if (appmenuchoice == 3) {
                    System.out.println("Update Appointment");
                    System.out.println("==================================================");
                    updateAppointment(appointmentLL, customerLL, employeeLL);
                }
                else if (appmenuchoice == 4){
                    System.out.println("Delete Appointment record..");
                    System.out.println("==================================================");
                    System.out.println("Enter Appointment ID to delete: ");
                    String aptID = sc.nextLine();
                    deleteAppointment(appointmentLL, aptID);
                }
                else if (appmenuchoice == 5) {
                    System.out.println("Finalising Appointment List and Details");
                    System.out.println("==================================================");
                    System.out.println("Printing it to the database...");
                    String fileName = "Appointment.txt";
                    writeAppFile(appointmentLL, fileName);
                    System.out.println("Complete. Returning to menu.");
                }
                else if (appmenuchoice == 6) {
                    System.out.println("Returning to main menu...");
                }
            }
            else if (menuchoice == 5) {
                System.out.println("Sale Menu");
                System.out.println("==================================================");
                System.out.println("[1] View Sale List and Details");
                System.out.println("[2] Add Sale");
                System.out.println("[3] Update Sale");
                System.out.println("[4] Delete Sale");
                System.out.println("[5] Print Sale List and Details");
                System.out.println("[6] Exit Sale Menu");
                int salemenuchoice = scno.nextInt();
                while (salemenuchoice < 1 || salemenuchoice > 6) {
                    System.out.println("Invalid input.");
                    System.out.println("Input your choice (1-6)");
                    salemenuchoice = scno.nextInt();
                    if (salemenuchoice < 1 || salemenuchoice > 6) {
                        break;
                    }
                }
                if (salemenuchoice == 1) {
                    System.out.println("View Sale List and Details");
                    System.out.println("==================================================");
                    // Call listProduct to display the product list and details
                    listSale(saleLL);
                }
                else if (salemenuchoice == 2) {
                    addSale(saleLL, customerLL, employeeLL, productLL);
                }

                else if (salemenuchoice == 3) {
                    System.out.println("Update Sale");
                    System.out.println("==================================================");
                    updateSale(saleLL, customerLL, employeeLL, productLL);
                }
                else if (salemenuchoice == 4){
                    System.out.println("Delete Sale record..");
                    System.out.println("==================================================");
                    System.out.println("Enter Sale ID to delete: ");
                    String saleID = sc.nextLine();
                    deleteSale(saleLL, saleID);
                }
                else if (salemenuchoice == 5) {
                    System.out.println("==================================================");
                    System.out.println("Finalising Sale List and Details");
                    System.out.println("==================================================");
                    System.out.println("Printing it to the database...");
                    String fileName = "Sale.txt";
                    writeSaleFile(saleLL, fileName);
                    System.out.println("Complete. Returning to menu.");
                }
                else if (salemenuchoice == 6) {
                    System.out.println("Returning to main menu...");
                }
            }
            else if (menuchoice == 6) {
                System.out.println("Search Menu");
                System.out.println("==================================================");
                System.out.println("[1] Search Employee");
                System.out.println("[2] Search Customer");
                System.out.println("[3] Search Product");
                System.out.println("[4] Search Appointment");
                System.out.println("[5] Search Sale");
                System.out.println("[6] Exit Search Menu");
                int searchmenuchoice = scno.nextInt();
                while (searchmenuchoice < 1 || searchmenuchoice > 6) {
                    System.out.println("Invalid input.");
                    System.out.println("Input your choice (1-6)");
                    searchmenuchoice = scno.nextInt();
                    if (searchmenuchoice < 1 || searchmenuchoice > 6) {
                        break;
                    }
                }
                if (searchmenuchoice == 1) {
                    System.out.println("Search Employee");
                    System.out.println("==================================================");
                    System.out.println("Enter Employee ID: ");
                    String empID = sc.nextLine();
                    Employee empChosen = searchEmployee(employeeLL, empID);
                    if (empChosen != null) {
                        System.out.println(empChosen.toString());
                    } else {
                        System.out.println("[ No matching employee found. ]");
                    }
                }

                else if (searchmenuchoice == 2) {
                    System.out.println("Search Customer");
                    System.out.println("==================================================");
                    System.out.println("Enter Customer ID: ");
                    String custID = sc.nextLine();
                    Customer custChosen = searchCustomer(customerLL, custID);
                    if (custChosen != null) {
                        System.out.println(custChosen.toString());
                    } else {
                        System.out.println("[ No matching customer found. ]");
                    }
                }

                else if (searchmenuchoice == 3) {
                    System.out.println("Search Product");
                    System.out.println("==================================================");
                    System.out.println("Enter Product ID: ");
                    String prdID = sc.nextLine();
                    Product prdChosen = searchProduct(productLL, prdID);
                    if (prdChosen != null) {
                        System.out.println(prdChosen.toString());
                    } else {
                        System.out.println("[ No matching product found. ]");
                    }
                }

                else if (searchmenuchoice == 4) {
                    System.out.println("Search Appointment");
                    System.out.println("==================================================");
                    System.out.println("Enter Appointment ID: ");
                    String aptID = sc.nextLine();
                    Appointment aptChosen = searchAppointment(appointmentLL, aptID);
                    if (aptChosen != null) {
                        System.out.println(aptChosen.toString());
                    } else {
                        System.out.println("[ No matching appointment found. ]");
                    }
                }

                else if (searchmenuchoice == 5) {
                    System.out.println("Search Sale");
                    System.out.println("==================================================");
                    System.out.println("Enter Sale ID: ");
                    String saleID = sc.nextLine();
                    Sale saleChosen = searchSale(saleLL, saleID);
                    if (saleChosen != null) {
                        System.out.println(saleChosen.toString());
                    } else {
                        System.out.println("[ No matching sale found. ]");
                    }
                }

                else if (searchmenuchoice == 6) {
                    System.out.println("Returning to main menu...");
                }

            }

            else if (menuchoice == 7) {
                System.out.println("==================================================");
                System.out.println("Saving data to database...");
                String fileName = "Employee.txt";
                writeEmpFile(employeeLL, fileName);
                fileName = "Customer.txt";
                writeCustFile(customerLL, fileName);
                fileName = "Product.txt";
                writeProdFile(productLL, fileName);
                fileName = "Appointment.txt";
                writeAppFile(appointmentLL, fileName);
                fileName = "Sale.txt";
                writeSaleFile(saleLL, fileName);
                System.out.println("Data saved successfully.");
            }
            else if (menuchoice == 8) {
                System.out.println("Logging off...");
                loop = false;
            }
        } while (loop);
    }
}