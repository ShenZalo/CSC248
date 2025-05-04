import java.util.*;
import java.io.*;

public class QMain {

    public static void Menu() {
        System.out.println("==================================================");
        System.out.println("[1] Employee");
        System.out.println("[2] Customer");
        System.out.println("[3] Product");
        System.out.println("[4] Appointment");
        System.out.println("[5] Sale");
        System.out.println("[6] Search for data");
        System.out.println("[7] Finalise to database");
        System.out.println("[8] Exit");
        System.out.println("===================================================");
    }

    public static boolean listEmployee(Queue<Employee> employeeQueue) {
        System.out.println("==================================================");
        System.out.println("Employee List and Details");
        System.out.println("==================================================");
        boolean list = false;
        if (employeeQueue.isEmpty()) {
            System.out.println("No employee found.");
        } else {
            list = true;
            Queue<Employee> temp = new Queue<>();
            while (!employeeQueue.isEmpty()) {
                Employee current = employeeQueue.dequeue();
                System.out.println(current.toString());
                temp.enqueue(current);
            }
            while (!temp.isEmpty()) {
                employeeQueue.enqueue(temp.dequeue());
            }
        }
        return list;
    }

    public static void addEmployee(Queue<Employee> employeeQueue) {
        Scanner sc = new Scanner(System.in);

        listEmployee(employeeQueue);
        System.out.println("==================================================");
        System.out.println("Add Employee");
        System.out.println("==================================================");

        System.out.println("Please fill out the following:");
        System.out.println("Enter the employee ID:");
        String empID = sc.nextLine();

        System.out.println("Enter the full name:");
        String empName = sc.nextLine();

        System.out.println("Enter the date hired (DD-MM-YYYY):");
        String empDate = sc.nextLine();

        Employee newEmployee = new Employee(empID, empName, empDate);
        employeeQueue.enqueue(newEmployee);

        System.out.println("[ Employee added successfully! ]");
        System.out.println(newEmployee.toString());
        System.out.println("Complete. Returning to menu.");
    }

    public static void updateEmployee(Queue<Employee> employeeQueue) {
        Scanner sc = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("Update Employee");
        System.out.println("==================================================");

        boolean list = listEmployee(employeeQueue);
        if (list) {
            String empID = sc.nextLine();
            Employee empChosen = searchEmployee(employeeQueue, empID);
            if (empChosen == null) {
                System.out.println("[ Employee not found. Please try again. ]");
                return;
            }

            if (empChosen != null) {
                System.out.println("Enter the updated full name:");
                String empName = sc.nextLine();
                empChosen.setEmpFullName(empName);

                System.out.println("Enter the updated date hired (DD-MM-YYYY):");
                String empDate = sc.nextLine();
                empChosen.setDateHired(empDate);

                System.out.println("[ Employee updated successfully! ]");
            } else {
                System.out.println("[ No matching employee found for update. ]");
            }
        }
    }


    public static void deleteEmployee(Queue<Employee> employeeQueue, Queue<Appointment> appointmentQueue, Queue<Sale> saleQueue, String empID) {
        Employee empChosen = searchEmployee(employeeQueue, empID);
        if (empChosen == null) {
            System.out.println("[ Employee not found. Please try again. ]");
            return;
        }
        if (empChosen != null) {
            // Remove related appointments
            Queue<Appointment> tempAppQueue = new Queue<>();
            while (!appointmentQueue.isEmpty()) {
                Appointment currentApp = appointmentQueue.dequeue();
                if (!currentApp.getEmployee().equals(empChosen)) {
                    tempAppQueue.enqueue(currentApp);
                }
            }
            while (!tempAppQueue.isEmpty()) {
                appointmentQueue.enqueue(tempAppQueue.dequeue());
            }

            // Remove related sales
            Queue<Sale> tempSaleQueue = new Queue<>();
            while (!saleQueue.isEmpty()) {
                Sale currentSale = saleQueue.dequeue();
                if (!currentSale.getEmp().equals(empChosen)) {
                    tempSaleQueue.enqueue(currentSale);
                }
            }
            while (!tempSaleQueue.isEmpty()) {
                saleQueue.enqueue(tempSaleQueue.dequeue());
            }

            // Remove the employee
            Queue<Employee> tempEmpQueue = new Queue<>();
            while (!employeeQueue.isEmpty()) {
                Employee currentEmp = employeeQueue.dequeue();
                if (!currentEmp.equals(empChosen)) {
                    tempEmpQueue.enqueue(currentEmp);
                }
            }
            while (!tempEmpQueue.isEmpty()) {
                employeeQueue.enqueue(tempEmpQueue.dequeue());
            }

            System.out.println("[ Employee and related data deleted successfully! ]");
        } else {
            System.out.println("[ No matching employee found for deletion. ]");
        }
    }


    public static Employee searchEmployee(Queue<Employee> employeeQueue, String empID) {
        Queue<Employee> tempQueue = new Queue<>(); // Temporary queue for traversal
        Employee foundEmployee = null;

        while (!employeeQueue.isEmpty()) {
            Employee employee = employeeQueue.dequeue(); // Dequeue from the original queue
            if (employee.getEmpID().equals(empID)) {
                foundEmployee = employee; // Match found
            }
            tempQueue.enqueue(employee); // Add the employee to the temporary queue
        }

        // Restore the original queue
        while (!tempQueue.isEmpty()) {
            employeeQueue.enqueue(tempQueue.dequeue());
        }

        return foundEmployee; // Return the found employee, or null if not found
    }


    public static void addCustomer(Queue<Customer> customerQueue) {
        Scanner sc = new Scanner(System.in);
        Scanner scno = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("Add Customer");
        System.out.println("==================================================");

        System.out.println("Enter the customer ID:");
        String custID = sc.nextLine();

        System.out.println("Enter the full name:");
        String custName = sc.nextLine();

        System.out.println("Enter the age:");
        int custAge = scno.nextInt();

        System.out.println("Enter the gender:");
        String custGender = sc.nextLine();

        System.out.println("Enter the phone number:");
        String custPhone = sc.nextLine();

        System.out.println("Enter the email:");
        String custEmail = sc.nextLine();

        Customer newCustomer = new Customer(custID, custName, custAge, custGender, custPhone, custEmail);
        customerQueue.enqueue(newCustomer);

        System.out.println("[ Customer added successfully! ]");
        System.out.println(newCustomer.toString());
    }

    public static void updateCustomer(Queue<Customer> customerQueue) {
        Scanner sc = new Scanner(System.in);
        Scanner scno = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("Update Customer");
        System.out.println("==================================================");
        boolean list = listCustomer(customerQueue);
        if (list) {
            String custID = sc.nextLine();
            Customer custChosen = searchCustomer(customerQueue, custID);
            if (custChosen == null) {
                System.out.println("[ Customer not found. Please try again. ]");
                return;
            }

            if (custChosen != null) {
                System.out.println("Enter the updated full name:");
                String custName = sc.nextLine();
                custChosen.setCustName(custName);

                System.out.println("Enter the updated age:");
                int custAge = scno.nextInt();
                custChosen.setCustAge(custAge);

                System.out.println("Enter the updated gender:");
                String custGender = sc.nextLine();
                custChosen.setCustGender(custGender);

                System.out.println("Enter the updated phone number:");
                String custPhone = sc.nextLine();
                custChosen.setCustPhone(custPhone);

                System.out.println("Enter the updated email:");
                String custEmail = sc.nextLine();
                custChosen.setCustEmail(custEmail);


                System.out.println("[ Customer updated successfully! ]");
            } else {
                System.out.println("[ No matching customer found for update. ]");
            }
        }
    }


    public static void deleteCustomer(Queue<Customer> customerQueue, Queue<Appointment> appointmentQueue, Queue<Sale> saleQueue, String custID) {
        Customer custChosen = searchCustomer(customerQueue, custID);
        if (custChosen == null) {
            System.out.println("[ Customer not found. Please try again. ]");
            return;
        }
        if (custChosen != null) {
            // Remove related appointments
            Queue<Appointment> tempAppQueue = new Queue<>();
            while (!appointmentQueue.isEmpty()) {
                Appointment currentApp = appointmentQueue.dequeue();
                if (!currentApp.getCustomer().equals(custChosen)) {
                    tempAppQueue.enqueue(currentApp);
                }
            }
            while (!tempAppQueue.isEmpty()) {
                appointmentQueue.enqueue(tempAppQueue.dequeue());
            }

            // Remove related sales
            Queue<Sale> tempSaleQueue = new Queue<>();
            while (!saleQueue.isEmpty()) {
                Sale currentSale = saleQueue.dequeue();
                if (!currentSale.getCust().equals(custChosen)) {
                    tempSaleQueue.enqueue(currentSale);
                }
            }
            while (!tempSaleQueue.isEmpty()) {
                saleQueue.enqueue(tempSaleQueue.dequeue());
            }

            // Remove the customer
            Queue<Customer> tempCustQueue = new Queue<>();
            while (!customerQueue.isEmpty()) {
                Customer currentCust = customerQueue.dequeue();
                if (!currentCust.equals(custChosen)) {
                    tempCustQueue.enqueue(currentCust);
                }
            }
            while (!tempCustQueue.isEmpty()) {
                customerQueue.enqueue(tempCustQueue.dequeue());
            }

            System.out.println("[ Customer and related data deleted successfully! ]");
        } else {
            System.out.println("[ No matching customer found for deletion. ]");
        }
    }






    public static boolean listCustomer(Queue<Customer> customerQueue) {
        System.out.println("==================================================");
        System.out.println("Customer List and Details");
        System.out.println("==================================================");
        boolean list = false;
        if (customerQueue.isEmpty()) {
            System.out.println("No customer found.");
        } else {
            list = true;
            Queue<Customer> temp = new Queue<>();
            while (!customerQueue.isEmpty()) {
                Customer current = customerQueue.dequeue();
                System.out.println(current.toString());
                temp.enqueue(current);
            }
            while (!temp.isEmpty()) {
                customerQueue.enqueue(temp.dequeue());
            }
        }
        return list;
    }

    public static Customer searchCustomer(Queue<Customer> customerQueue, String custID) {
        Queue<Customer> tempQueue = new Queue<>(); // Temporary queue for traversal
        Customer foundCustomer = null;

        while (!customerQueue.isEmpty()) {
            Customer customer = customerQueue.dequeue(); // Dequeue from the original queue
            if (customer.getCustID().equals(custID)) {
                foundCustomer = customer; // Match found
            }
            tempQueue.enqueue(customer); // Add the employee to the temporary queue
        }

        // Restore the original queue
        while (!tempQueue.isEmpty()) {
            customerQueue.enqueue(tempQueue.dequeue());
        }

        return foundCustomer; // Return the found employee, or null if not found
    }


    public static boolean listProduct(Queue<Product> productQueue) {
        System.out.println("==================================================");
        System.out.println("Product List and Details");
        System.out.println("==================================================");
        boolean list = false;
        if (productQueue.isEmpty()) {
            System.out.println("No product found.");
        } else {
            list = true;
            Queue<Product> temp = new Queue<>();
            while (!productQueue.isEmpty()) {
                Product current = productQueue.dequeue();
                System.out.println(current.toString());
                temp.enqueue(current);
            }
            while (!temp.isEmpty()) {
                productQueue.enqueue(temp.dequeue());
            }
        }
        return list;
    }

    public static void addProduct(Queue<Product> productQueue) {
        Scanner sc = new Scanner(System.in);
        Scanner scno = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("Add Product");
        System.out.println("==================================================");

        System.out.println("Enter the product ID:");
        String prdID = sc.nextLine();

        System.out.println("Enter the product name:");
        String prdName = sc.nextLine();

        System.out.println("Enter the product category:");
        String prdCategory = sc.nextLine();

        System.out.println("Enter the product price:");
        double prdPrice = scno.nextDouble();

        Product newProduct = new Product(prdID, prdName, prdCategory, prdPrice);
        productQueue.enqueue(newProduct);

        System.out.println("[ Product added successfully! ]");
        System.out.println(newProduct.toString());
    }

    public static void updateProduct(Queue<Product> productQueue) {
        Scanner sc = new Scanner(System.in);
        Scanner scno = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("Update Product");
        System.out.println("==================================================");
        boolean list = listProduct(productQueue);
        if (list) {
            String productID = sc.nextLine();
            Product prdChosen = searchProduct(productQueue, productID);
            if (prdChosen == null) {
                System.out.println("[ Product not found. Please try again. ]");
                return;
            }

            if (prdChosen != null) {
                System.out.println("Enter the updated product name:");
                String prdName = sc.nextLine();
                prdChosen.setPrdName(prdName);

                System.out.println("Enter the updated product category:");
                String prdCategory = sc.nextLine();
                prdChosen.setPrdCategory(prdCategory);

                System.out.println("Enter the updated product price:");
                double prdPrice = scno.nextDouble();
                prdChosen.setPrdPrice(prdPrice);


                System.out.println("[ Product updated successfully! ]");
            } else {
                System.out.println("[ No matching product found for update. ]");
            }
        }
    }


    public static void deleteProduct(Queue<Product> productQueue, Queue<Sale> saleQueue, String productID) {
        Product prodChosen = searchProduct(productQueue, productID);
        if (prodChosen == null) {
            System.out.println("[ Product not found. Please try again. ]");
            return;
        }
        if (prodChosen != null) {
            // Remove related sales
            Queue<Sale> tempSaleQueue = new Queue<>();
            while (!saleQueue.isEmpty()) {
                Sale currentSale = saleQueue.dequeue();
                if (!currentSale.getPrd().equals(prodChosen)) {
                    tempSaleQueue.enqueue(currentSale);
                }
            }
            while (!tempSaleQueue.isEmpty()) {
                saleQueue.enqueue(tempSaleQueue.dequeue());
            }

            // Remove the product
            Queue<Product> tempProdQueue = new Queue<>();
            while (!productQueue.isEmpty()) {
                Product currentProd = productQueue.dequeue();
                if (!currentProd.equals(prodChosen)) {
                    tempProdQueue.enqueue(currentProd);
                }
            }
            while (!tempProdQueue.isEmpty()) {
                productQueue.enqueue(tempProdQueue.dequeue());
            }

            System.out.println("[ Product and related data deleted successfully! ]");
        } else {
            System.out.println("[ No matching product found for deletion. ]");
        }
    }




    public static Product searchProduct(Queue<Product> productQueue, String prdID) {
        Queue<Product> tempQueue = new Queue<>(); // Temporary queue for traversal
        Product foundProduct = null;

        while (!productQueue.isEmpty()) {
            Product product = productQueue.dequeue(); // Dequeue from the original queue
            if (product.getPrdID().equals(prdID)) {
                foundProduct = product; // Match found
            }
            tempQueue.enqueue(product); // Add the product to the temporary queue
        }

        // Restore the original queue
        while (!tempQueue.isEmpty()) {
            productQueue.enqueue(tempQueue.dequeue());
        }

        return foundProduct; // Return the found product, or null if not found
    }

    public static boolean listAppointment(Queue<Appointment> appointmentQueue) {
        System.out.println("==================================================");
        System.out.println("Appointment List and Details");
        System.out.println("==================================================");
        boolean list = false;
        if (appointmentQueue.isEmpty()) {
            System.out.println("No appointment found.");
        } else {
            list = true;
            Queue<Appointment> temp = new Queue<>();
            while (!appointmentQueue.isEmpty()) {
                Appointment current = appointmentQueue.dequeue();
                System.out.println(current.toString());
                temp.enqueue(current);
            }
            while (!temp.isEmpty()) {
                appointmentQueue.enqueue(temp.dequeue());
            }
        }
        return list;
    }

    public static Appointment searchAppointment(Queue<Appointment> appointmentQueue, String aptID) {
        Queue<Appointment> tempQueue = new Queue<>(); // Temporary queue for traversal
        Appointment foundAppointment = null;

        while (!appointmentQueue.isEmpty()) {
            Appointment appointment = appointmentQueue.dequeue(); // Dequeue from the original queue
            if (appointment.getAptID().equals(aptID)) {
                foundAppointment = appointment; // Match found
            }
            tempQueue.enqueue(appointment); // Add the appointment to the temporary queue
        }

        // Restore the original queue
        while (!tempQueue.isEmpty()) {
            appointmentQueue.enqueue(tempQueue.dequeue());
        }

        return foundAppointment; // Return the found appointment, or null if not found
    }

    public static void addAppointment(Queue<Appointment> appointmentQueue, Queue<Customer> customerQueue, Queue<Employee> employeeQueue) {
        Scanner sc = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("Add Appointment");
        System.out.println("==================================================");

        listCustomer(customerQueue);
        System.out.println("Set one (1) customer for the appointment:");
        String custID = sc.nextLine();
        Customer custChosen = searchCustomer(customerQueue, custID);
        if (custChosen == null) {
            System.out.println("[ Customer not found. Please try again. ]");
            return;
        }

        listEmployee(employeeQueue);
        System.out.println("Set one (1) employee for the appointment:");
        String empID = sc.nextLine();
        Employee empChosen = searchEmployee(employeeQueue, empID);
        if (empChosen == null) {
            System.out.println("[ Employee not found. Please try again. ]");
            return;
        }

        System.out.println("Enter the appointment ID:");
        String aptID = sc.nextLine();

        System.out.println("Enter the appointment date:");
        String aptDate = sc.nextLine();

        Appointment newAppointment = new Appointment(aptID, aptDate, custChosen, empChosen);
        appointmentQueue.enqueue(newAppointment);

        System.out.println("[ Appointment added successfully! ]");
    }


    public static void updateAppointment(Queue<Appointment> appointmentQueue, Queue<Employee> employeeQueue, Queue<Customer> customerQueue) {
        Scanner sc = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("Update Appointment");
        System.out.println("==================================================");
        boolean list = listAppointment(appointmentQueue);
        if (list) {
            String appID = sc.nextLine();
            Appointment aptChosen = searchAppointment(appointmentQueue, appID);
            if (aptChosen == null) {
                System.out.println("[ Appointment not found. Please try again. ]");
                return;
            }

            System.out.println("Enter the updated appointment ID:");
            String aptID = sc.nextLine();
            aptChosen.setAptID(aptID);

            System.out.println("Enter the updated appointment date:");
            String aptDate = sc.nextLine();
            aptChosen.setAptDate(aptDate);

            listEmployee(employeeQueue);
            System.out.println("Select the updated employee for the appointment:");
            String empID = sc.nextLine();
            Employee empChosen = searchEmployee(employeeQueue, empID);
            aptChosen.setEmployee(empChosen);

            listCustomer(customerQueue);
            System.out.println("Select the updated customer for the appointment:");
            String custID = sc.nextLine();
            Customer custChosen = searchCustomer(customerQueue, custID);
            aptChosen.setCustomer(custChosen);


            System.out.println("[ Appointment updated successfully! ]");
        }
    }

    public static void deleteAppointment(Queue<Appointment> appointmentQueue, String appID) {
        System.out.println("==================================================");
        System.out.println("Delete Appointment");
        System.out.println("==================================================");
        boolean list = listAppointment(appointmentQueue);
        if (list) {
            Appointment aptChosen = searchAppointment(appointmentQueue, appID);
            if (aptChosen == null) {
                System.out.println("[ Appointment not found. Please try again. ]");
                return;
            }

            Queue<Appointment> temp = new Queue<>();
            while (!appointmentQueue.isEmpty()) {
                Appointment current = appointmentQueue.dequeue();
                if (!current.equals(aptChosen)) {
                    temp.enqueue(current);
                }
            }

            while (!temp.isEmpty()) {
                appointmentQueue.enqueue(temp.dequeue());
            }

            System.out.println("[ Appointment deleted successfully! ]");
        }
    }




    public static boolean listSale(Queue<Sale> saleQueue) {
        System.out.println("==================================================");
        System.out.println("Sale List and Details");
        System.out.println("==================================================");
        boolean list = false;
        if (saleQueue.isEmpty()) {
            System.out.println("No sale found.");
        } else {
            list = true;
            Queue<Sale> temp = new Queue<>();
            while (!saleQueue.isEmpty()) {
                Sale current = saleQueue.dequeue();
                System.out.println(current.toString());
                temp.enqueue(current);
            }
            while (!temp.isEmpty()) {
                saleQueue.enqueue(temp.dequeue());
            }
        }
        return list;
    }

    public static Sale searchSale(Queue<Sale> saleQueue, String saleID) {
        Queue<Sale> tempQueue = new Queue<>(); // Temporary queue for traversal
        Sale foundSale = null;

        while (!saleQueue.isEmpty()) {
            Sale sale = saleQueue.dequeue(); // Dequeue from the original queue
            if (sale.getSaleID().equals(saleID)) {
                foundSale = sale; // Match found
            }
            tempQueue.enqueue(sale); // Add the sale to the temporary queue
        }

        // Restore the original queue
        while (!tempQueue.isEmpty()) {
            saleQueue.enqueue(tempQueue.dequeue());
        }

        return foundSale; // Return the found sale, or null if not found
    }

    public static void addSale(Queue<Sale> saleQueue, Queue<Employee> employeeQueue, Queue<Customer> customerQueue, Queue<Product> productQueue) {
        Scanner sc = new Scanner(System.in);
        Scanner scno = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("Add Sale");
        System.out.println("==================================================");

        System.out.println("Enter the sale ID:");
        String saleID = sc.nextLine();

        listEmployee(employeeQueue);
        System.out.println("Select an employee for the sale:");
        String empID = sc.nextLine();
        Employee empChosen = searchEmployee(employeeQueue, empID);
        if (empChosen == null) {
            System.out.println("[ Employee not found. Please try again. ]");
            return;
        }

        listCustomer(customerQueue);
        System.out.println("Select a customer for the sale:");
        String custID = sc.nextLine();
        Customer custChosen = searchCustomer(customerQueue, custID);
        if (custChosen == null) {
            System.out.println("[ Customer not found. Please try again. ]");
            return;
        }

        listProduct(productQueue);
        System.out.println("Select a product for the sale:");
        String prdID = sc.nextLine();
        Product prdChosen = searchProduct(productQueue, prdID);
        if (prdChosen == null) {
            System.out.println("[ Product not found. Please try again. ]");
            return;
        }

        System.out.println("Enter the quantity of the product sold:");
        int saleQty = scno.nextInt();

        System.out.println("Enter the sale date:");
        String saleDate = sc.nextLine();

        Sale newSale = new Sale(saleID, custChosen, empChosen, prdChosen, saleQty, saleDate);
        saleQueue.enqueue(newSale);

        System.out.println("[ Sale added successfully! ]");
        System.out.println(newSale.toString());
    }

    public static void updateSale(Queue<Sale> saleQueue, Queue<Employee> employeeQueue, Queue<Customer> customerQueue, Queue<Product> productQueue) {
        Scanner sc = new Scanner(System.in);
        Scanner scno = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("Update Sale");
        System.out.println("==================================================");
        boolean list = listSale(saleQueue);
        if (list) {
            String saleID = sc.nextLine();
            Sale saleChosen = searchSale(saleQueue, saleID);
            if (saleChosen == null) {
                System.out.println("[ Sale not found. Please try again. ]");
                return;
            }

            System.out.println("Enter the updated sale ID:");
            saleID = sc.nextLine();
            saleChosen.setSaleID(saleID);

            listEmployee(employeeQueue);
            System.out.println("Select the updated employee for the sale:");
            String empID = sc.nextLine();
            Employee empChosen = searchEmployee(employeeQueue, empID);
            saleChosen.setEmp(empChosen);

            listCustomer(customerQueue);
            System.out.println("Select the updated customer for the sale:");
            String custID = sc.nextLine();
            Customer custChosen = searchCustomer(customerQueue, custID);
            saleChosen.setCust(custChosen);

            listProduct(productQueue);
            System.out.println("Select the updated product for the sale:");
            String prdID = sc.nextLine();
            Product prdChosen = searchProduct(productQueue, prdID);
            saleChosen.setPrd(prdChosen);

            System.out.println("Enter the updated quantity of the product sold:");
            int saleQty = scno.nextInt();
            saleChosen.setSaleQty(saleQty);

            System.out.println("Enter the updated sale date:");
            String saleDate = sc.nextLine();
            saleChosen.setSaleDate(saleDate);


            System.out.println("[ Sale updated successfully! ]");
        }
    }

    public static void deleteSale(Queue<Sale> saleQueue, String saleID) {
        System.out.println("==================================================");
        System.out.println("Delete Sale");
        System.out.println("==================================================");

        boolean list = listSale(saleQueue);
        if (list) {
            Sale saleChosen = searchSale(saleQueue, saleID);
            if (saleChosen == null) {
                System.out.println("[ Sale not found. Please try again. ]");
                return;
            }

            Queue<Sale> temp = new Queue<>();
            while (!saleQueue.isEmpty()) {
                Sale current = saleQueue.dequeue();
                if (!current.equals(saleChosen)) {
                    temp.enqueue(current);
                }
            }

            while (!temp.isEmpty()) {
                saleQueue.enqueue(temp.dequeue());
            }

            System.out.println("[ Sale deleted successfully! ]");
        }
    }




    public static void readCustFile(Queue<Customer> customerQueue, String fileName) {
        // Clear the list to avoid duplicates
        while (!customerQueue.isEmpty()) {
            customerQueue.dequeue();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");

                // Check if the line contains the expected number of columns
                if (details.length == 6) {
                    String custID = details[0];
                    String custName = details[1];
                    int custAge = Integer.parseInt(details[2]);
                    String custGender = details[3];
                    String custPhone = details[4];
                    String custEmail = details[5];

                    customerQueue.enqueue(new Customer(custID, custName, custAge, custGender, custPhone, custEmail));
                } else {
                    // Handle the case where the line doesn't contain the expected number of fields
                    System.out.println("Skipping invalid line: " + line);
                }
            }
            System.out.println("Data loaded successfully from " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void writeCustFile(Queue<Customer> customerQueue, String fileName) {
        if (customerQueue == null || customerQueue.isEmpty()) {
            System.out.println("Customer list is empty. Nothing to write.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            Queue<Customer> temp = new Queue<>();
            Customer current;

            // Write each customer to the file and move them to a temporary list
            do {
                current = customerQueue.dequeue();
                bw.write(current.getCustID() + "," +
                        current.getCustName() + "," +
                        current.getCustAge() + "," +
                        current.getCustGender() + "," +
                        current.getCustPhone() + "," +
                        current.getCustEmail());
                bw.newLine();
                temp.enqueue(current);
            } while (!customerQueue.isEmpty());

            // Restore customers back to the original list
            do {
                customerQueue.enqueue(temp.dequeue());
            } while (!temp.isEmpty());

            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void readEmpFile(Queue<Employee> employeeQueue, String fileName) {
        // Clear the list to avoid duplicates
        while (!employeeQueue.isEmpty()) {
            employeeQueue.dequeue();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");

                // Check if the line contains the expected number of columns
                if (details.length == 3) {
                    String empID = details[0];
                    String empFullName = details[1];
                    String empDateHired = details[2];

                    employeeQueue.enqueue(new Employee(empID, empFullName, empDateHired));
                } else {
                    // Handle the case where the line doesn't contain the expected number of fields
                    System.out.println("Skipping invalid line: " + line);
                }
            }
            System.out.println("Data loaded successfully from " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void writeEmpFile(Queue<Employee> employeeQueue, String fileName) {
        if (employeeQueue == null || employeeQueue.isEmpty()) {
            System.out.println("Customer list is empty. Nothing to write.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            Queue<Employee> temp = new Queue<>();
            Employee current;

            // Write each customer to the file and move them to a temporary list
            do {
                current = employeeQueue.dequeue();
                bw.write(current.getEmpID() + "," +
                        current.getEmpFullName() + "," +
                        current.getDateHired());

                bw.newLine();
                temp.enqueue(current);
            } while (!employeeQueue.isEmpty());

            // Restore customers back to the original list
            do {
                employeeQueue.enqueue(temp.dequeue());
            } while (!temp.isEmpty());

            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void readProdFile(Queue<Product> productQueue, String fileName) {
        // Clear the list to avoid duplicates
        while (!productQueue.isEmpty()) {
            productQueue.dequeue();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");

                // Check if the line contains the expected number of columns
                if (details.length == 4) {
                    String prodID = details[0];
                    String prodName = details[1];
                    String prodCategory = details[2];
                    double prodPrice = Double.parseDouble(details[3]);

                    productQueue.enqueue(new Product(prodID, prodName, prodCategory, prodPrice));
                } else {
                    // Handle the case where the line doesn't contain the expected number of fields
                    System.out.println("Skipping invalid line: " + line);
                }
            }
            System.out.println("Data loaded successfully from " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing product price: " + e.getMessage());
        }
    }

    public static void writeProdFile(Queue<Product> productQueue, String fileName) {
        if (productQueue == null || productQueue.isEmpty()) {
            System.out.println("Customer list is empty. Nothing to write.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            Queue<Product> temp = new Queue<>();
            Product current;

            // Write each customer to the file and move them to a temporary list
            do {
                current = productQueue.dequeue();
                bw.write(current.getPrdID() + "," +
                        current.getPrdName() + "," +
                        current.getPrdCategory()+ ","+
                        current.getPrdPrice());
                bw.newLine();
                temp.enqueue(current);
            } while (!productQueue.isEmpty());

            // Restore customers back to the original list
            do {
                productQueue.enqueue(temp.dequeue());
            } while (!temp.isEmpty());

            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void readAppFile(Queue<Appointment> appointmentQueue, Queue<Customer> customerQueue, Queue<Employee> employeeQueue, String fileName) {
        // Clear the appointment queue to avoid duplicates
        while (!appointmentQueue.isEmpty()) {
            appointmentQueue.dequeue();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");

                // Ensure the line contains the expected number of fields
                if (details.length == 4) {
                    String aptID = details[0];
                    String aptDate = details[1];
                    String custID = details[2];
                    String empID = details[3];

                    // Search for the associated Customer and Employee
                    Customer customer = searchCustomer(customerQueue, custID);
                    Employee employee = searchEmployee(employeeQueue, empID);

                    if (customer != null && employee != null) {
                        // Create and enqueue a new Appointment
                        Appointment appointment = new Appointment(aptID, aptDate, customer, employee);
                        appointmentQueue.enqueue(appointment);
                    } else {
                        System.out.println("Skipping appointment with missing customer or employee: " + line);
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


    public static void writeAppFile(Queue<Appointment> aptQueue, String fileName) {
        if (aptQueue == null || aptQueue.isEmpty()) {
            System.out.println("Customer list is empty. Nothing to write.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            Queue<Appointment> temp = new Queue<>();
            Appointment current;

            // Write each customer to the file and move them to a temporary list
            do {
                current = aptQueue.dequeue();
                Appointment appointment = current;
                bw.write(appointment.getAptID() + "," +
                        appointment.getAptDate() + "," +
                        appointment.getCustomer().getCustID() + "," +
                        appointment.getEmployee().getEmpID());
                bw.newLine();
                temp.enqueue(current);
            } while (!aptQueue.isEmpty());

            // Restore customers back to the original list
            do {
                aptQueue.enqueue(temp.dequeue());
            } while (!temp.isEmpty());

            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void readSaleFile(Queue<Sale> saleQueue, Queue<Customer> customerQueue, Queue<Employee> employeeQueue, Queue<Product> productQueue, String fileName) {
        while (!saleQueue.isEmpty()) {
            saleQueue.dequeue();
        }
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

                    Customer customer = searchCustomer(customerQueue, custID);
                    Employee employee = searchEmployee(employeeQueue, empID);
                    Product product = searchProduct(productQueue, prdID);

                    if (customer != null && employee != null && product != null) {
                        Sale sale = new Sale(saleID, customer, employee, product, saleQty, saleDate);
                        saleQueue.enqueue(sale);
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

    public static void writeSaleFile(Queue<Sale> saleQueue, String fileName) {
        if (saleQueue == null || saleQueue.isEmpty()) {
            System.out.println("Customer list is empty. Nothing to write.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            Queue<Sale> temp = new Queue<>();
            Sale current;

            // Write each customer to the file and move them to a temporary list
            do {
                current = saleQueue.dequeue();
                Sale sale = current;
                bw.write(sale.getSaleID() + "," +
                        sale.getCust().getCustID() + "," +
                        sale.getEmp().getEmpID() + "," +
                        sale.getPrd().getPrdID() + "," +
                        sale.getSaleQty() + "," +
                        sale.getSaleDate());
                bw.newLine();
                temp.enqueue(current);
            } while (!saleQueue.isEmpty());

            // Restore customers back to the original list
            do {
                saleQueue.enqueue(temp.dequeue());
            } while (!temp.isEmpty());

            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scanner scno = new Scanner(System.in);
        Queue<Employee> employeeQueue = new Queue<>();
        Queue<Customer> customerQueue = new Queue<>();
        Queue<Product> productQueue = new Queue<>();
        Queue<Appointment> appointmentQueue = new Queue<>();
        Queue<Sale> saleQueue = new Queue<>();

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
        if (!username.equals("admin") || !password.equals("123")) {
            while (!username.equals("admin") || !password.equals("123")) {
                System.out.println("Invalid username or password.");
                System.out.println("Please try again.");
                System.out.println("Username:");
                username = sc.nextLine();
                System.out.println("Password:");
                password = sc.nextLine();
            }
        } else {
            System.out.println("Welcome " + username);
            System.out.println("Loading data from database...");
            readEmpFile(employeeQueue, "Employee.txt");
            readCustFile(customerQueue, "Customer.txt");
            readProdFile(productQueue, "Product.txt");
            readAppFile(appointmentQueue, customerQueue, employeeQueue, "Appointment.txt");
            readSaleFile(saleQueue, customerQueue, employeeQueue, productQueue, "Sale.txt");
        }

        boolean loop = true;
        do {
            Menu();

            int menuchoice = scno.nextInt();
            while (menuchoice < 1 || menuchoice > 8) {
                System.out.println("Invalid input. (1-8)");
                menuchoice = scno.nextInt();
                if (menuchoice < 1 || menuchoice > 8) {
                    break;
                }
            }
            if (menuchoice == 1) {
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
                if (empMenuChoice == 1) {
                    listEmployee(employeeQueue);
                } else if (empMenuChoice == 2) {
                    addEmployee(employeeQueue);
                } else if (empMenuChoice == 3) {
                    updateEmployee(employeeQueue);
                } else if (empMenuChoice == 4) {
                    boolean list = listEmployee(employeeQueue);
                    if (list) {
                        System.out.println("Delete Employee record..");
                        System.out.println("==================================================");
                        System.out.println("Enter Employee ID to delete: ");
                        String empID = sc.nextLine();
                        deleteEmployee(employeeQueue, appointmentQueue, saleQueue, empID);
                    } else {
                        System.out.println("No employee found.");
                    }
                } else if (empMenuChoice == 5) {
                    System.out.println("==================================================");
                    System.out.println("Finalising Employee List and Details");
                    System.out.println("==================================================");
                    System.out.println("Printing it to the database...");
                    writeEmpFile(employeeQueue,"Employee.txt");
                    System.out.println("Complete. Returning to menu.");
                } else if (empMenuChoice == 6) {
                    System.out.println("Returning to main menu...");
                }
            } else if (menuchoice == 2) {
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
                if (custMenuChoice == 1) {
                    listCustomer(customerQueue);
                } else if (custMenuChoice == 2) {
                    addCustomer(customerQueue);
                } else if (custMenuChoice == 3) {
                    updateCustomer(customerQueue);
                } else if (custMenuChoice == 4) {
                    boolean list = listCustomer(customerQueue);
                    if (list) {
                        System.out.println("Delete Customer record..");
                        System.out.println("==================================================");
                        System.out.println("Enter Customer ID to delete: ");
                        String custID = sc.nextLine();
                        deleteCustomer(customerQueue, appointmentQueue, saleQueue, custID);
                    } else {
                        System.out.println("No customer found.");
                    }
                } else if (custMenuChoice == 5) {
                    System.out.println("==================================================");
                    System.out.println("Finalising Customer List and Details");
                    System.out.println("==================================================");
                    System.out.println("Printing it to the database...");
                    writeCustFile(customerQueue, "Customer.txt");
                    System.out.println("Complete. Returning to menu.");
                } else if (custMenuChoice == 6) {
                    System.out.println("Returning to main menu...");
                }
            } else if (menuchoice == 3) {
                System.out.println("Product Menu");
                System.out.println("==================================================");
                System.out.println("[1] View Product List and Details");
                System.out.println("[2] Add Product");
                System.out.println("[3] Update Product");
                System.out.println("[4] Delete Product");
                System.out.println("[5] Print Product List and Details");
                System.out.println("[6] Exit Product Menu");
                int prdmenuchoice = scno.nextInt();
                while (prdmenuchoice < 1 || prdmenuchoice > 6) {
                    System.out.println("Invalid input.");
                    System.out.println("Input your choice (1-6)");
                    prdmenuchoice = scno.nextInt();
                    if (prdmenuchoice < 1 || prdmenuchoice > 6) {
                        break;
                    }
                }
                if (prdmenuchoice == 1) {
                    listProduct(productQueue);
                } else if (prdmenuchoice == 2) {
                    addProduct(productQueue);
                } else if (prdmenuchoice == 3) {
                    updateProduct(productQueue);
                } else if (prdmenuchoice == 4) {
                    System.out.println("Delete Product record..");
                    System.out.println("==================================================");
                    System.out.println("Enter Product ID to delete: ");
                    String prdID = sc.nextLine();
                    deleteProduct(productQueue, saleQueue, prdID);
                } else if (prdmenuchoice == 5) {
                    System.out.println("Finalising Product List and Details");
                    System.out.println("==================================================");
                    System.out.println("Printing it to the database...");
                    writeProdFile(productQueue,"Product.txt");
                    System.out.println("Complete. Returning to menu.");
                } else if (prdmenuchoice == 6) {
                    System.out.println("Returning to main menu...");
                }
            } else if (menuchoice == 4) {
                System.out.println("Appointment Menu");
                System.out.println("==================================================");
                System.out.println("[1] View Appointment List and Details");
                System.out.println("[2] Add Appointment");
                System.out.println("[3] Update Appointment");
                System.out.println("[4] Delete Appointment");
                System.out.println("[5] Print Appointment List and Details");
                System.out.println("[6] Exit Appointment Menu");
                int appmenuchoice = scno.nextInt();
                while (appmenuchoice < 1 || appmenuchoice > 6) {
                    System.out.println("Invalid input.");
                    System.out.println("Input your choice (1-6)");
                    appmenuchoice = scno.nextInt();
                    if (appmenuchoice < 1 || appmenuchoice > 6) {
                        break;
                    }
                }
                if (appmenuchoice == 1) {
                    listAppointment(appointmentQueue);
                } else if (appmenuchoice == 2) {
                    addAppointment(appointmentQueue, customerQueue, employeeQueue);
                } else if (appmenuchoice == 3) {
                    updateAppointment(appointmentQueue, employeeQueue, customerQueue);
                } else if (appmenuchoice == 4) {
                    System.out.println("Delete Appointment record..");
                    System.out.println("==================================================");
                    System.out.println("Enter Appointment ID to delete: ");
                    String aptID = sc.nextLine();
                    deleteAppointment(appointmentQueue, aptID);
                } else if (appmenuchoice == 5) {
                    System.out.println("Finalising Appointment List and Details");
                    System.out.println("==================================================");
                    System.out.println("Printing it to the database...");
                    writeAppFile(appointmentQueue, "Appointment.txt");
                    System.out.println("Complete. Returning to menu.");
                } else if (appmenuchoice == 6) {
                    System.out.println("Returning to main menu...");
                }
            } else if (menuchoice == 5) {
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
                    listSale(saleQueue);
                } else if (salemenuchoice == 2) {
                    addSale(saleQueue, employeeQueue, customerQueue, productQueue);
                } else if (salemenuchoice == 3) {
                    updateSale(saleQueue, employeeQueue, customerQueue, productQueue);
                } else if (salemenuchoice == 4) {
                    System.out.println("Delete Sale record..");
                    System.out.println("==================================================");
                    System.out.println("Enter Sale ID to delete: ");
                    String saleID = sc.nextLine();
                    deleteSale(saleQueue, saleID);
                } else if (salemenuchoice == 5) {
                    System.out.println("==================================================");
                    System.out.println("Finalising Sale List and Details");
                    System.out.println("==================================================");
                    System.out.println("Printing it to the database...");
                    String fileName = "Sale.txt";
                    writeSaleFile(saleQueue, fileName);
                    System.out.println("Complete. Returning to menu.");
                } else if (salemenuchoice == 6) {
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

                int searchMenuChoice = scno.nextInt();
                while (searchMenuChoice < 1 || searchMenuChoice > 6) {
                    System.out.println("Invalid input.");
                    System.out.println("Input your choice (1-6)");
                    searchMenuChoice = scno.nextInt();
                    if (searchMenuChoice < 1 || searchMenuChoice > 6) {
                        break;
                    }
                }

                if (searchMenuChoice == 1) {
                    System.out.println("Search Employee");
                    System.out.println("==================================================");
                    System.out.println("Enter Employee ID: ");
                    String empID = sc.nextLine();
                    Employee employeeChosen = searchEmployee(employeeQueue, empID);
                    if (employeeChosen!= null) {
                        System.out.println(employeeChosen.toString());
                    }

                }
                else if (searchMenuChoice == 2) {
                    System.out.println("Search Customer");
                    System.out.println("==================================================");
                    System.out.println("Enter Customer ID: ");
                    String custID = sc.nextLine();
                    Customer customerChosen = searchCustomer(customerQueue, custID);
                    if (customerChosen!= null) {
                        System.out.println(customerChosen.toString());
                    }

                }
                else if (searchMenuChoice == 3) {
                    System.out.println("Search Product");
                    System.out.println("==================================================");
                    System.out.println("Enter Product ID: ");
                    String prdID = sc.nextLine();
                    Product productChosen = searchProduct(productQueue, prdID);
                    if (productChosen!= null) {
                        System.out.println(productChosen.toString());
                    }
                }
                else if (searchMenuChoice == 4) {
                    System.out.println("Search Appointment");
                    System.out.println("==================================================");
                    System.out.println("Enter Appointment ID: ");
                    String aptID = sc.nextLine();
                    //readAppFile(appointmentQueue, customerQueue, employeeQueue, "Appointment.txt");
                    Appointment appointmentChosen = searchAppointment(appointmentQueue, aptID);
                    if (appointmentChosen!= null) {
                        System.out.println(appointmentChosen.toString());
                    }
                }
                else if (searchMenuChoice == 5) {
                    System.out.println("Search Sale");
                    System.out.println("==================================================");
                    System.out.println("Enter Sale ID: ");
                    String saleID = sc.nextLine();
                    Sale saleChosen = searchSale(saleQueue, saleID);
                    if (saleChosen!= null) {
                        System.out.println(saleChosen.toString());
                    }
                }
                else if (searchMenuChoice == 6) {
                    System.out.println("Returning to main menu...");
                }
            }
            else if (menuchoice == 7) {
                System.out.println("==================================================");
                System.out.println("Saving data to database...");
                String fileName = "Employee.txt";
                writeEmpFile(employeeQueue, fileName);
                fileName = "Customer.txt";
                writeCustFile(customerQueue, fileName);
                fileName = "Product.txt";
                writeProdFile(productQueue, fileName);
                fileName = "Appointment.txt";
                writeAppFile(appointmentQueue, fileName);
                fileName = "Sale.txt";
                writeSaleFile(saleQueue, fileName);
                System.out.println("Data saved successfully.");
            } else if (menuchoice == 8) {
                System.out.println("Logging off...");
                loop = false;
            }
        } while (loop);
    }
}
