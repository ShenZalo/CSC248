import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class QMainGUI {
    private JFrame frame;
    private JPanel loginPanel, mainMenuPanel, employeePanel, customerPanel, productPanel, appointmentPanel, salePanel, searchPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private Queue<Employee> employeeQueue = new Queue<>();
    private Queue<Customer> customerQueue = new Queue<>();
    private Queue<Product> productQueue = new Queue<>();
    private Queue<Appointment> appointmentQueue = new Queue<>();
    private Queue<Sale> saleQueue = new Queue<>();


    public QMainGUI() {
        frame = new JFrame("GlassHopper! System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new CardLayout());

        createLoginPanel();
        createMainMenuPanel();
        createEmployeePanel();
        createCustomerPanel();
        createProductPanel();
        createAppointmentPanel();
        createSalePanel();
        createSearchPanel();

        frame.add(loginPanel, "Login");
        frame.add(mainMenuPanel, "MainMenu");
        frame.add(employeePanel, "Employee");
        frame.add(customerPanel, "Customer");
        frame.add(productPanel, "Product");
        frame.add(appointmentPanel, "Appointment");
        frame.add(salePanel, "Sale");
        frame.add(searchPanel, "Search");

        frame.setVisible(true);
    }

    private JButton createStyledButton(String text, Dimension size) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180)); // Steel blue background
        button.setForeground(Color.WHITE); // White text
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 120, 170), 2), // Darker border
                BorderFactory.createEmptyBorder(10, 20, 10, 20) // Padding inside the button
        ));
        button.setPreferredSize(size);
        return button;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180)); // Steel blue background
        button.setForeground(Color.WHITE); // White text
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 120, 170), 2), // Darker border
                BorderFactory.createEmptyBorder(10, 20, 10, 20) // Padding inside the button
        ));
        return button;
    }

    private void createLoginPanel() {
        loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding between components

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        // Set preferred size for the text fields
        Dimension textFieldSize = new Dimension(200, 30);
        usernameField.setPreferredSize(textFieldSize);
        passwordField.setPreferredSize(textFieldSize);

        // Customize labels
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Customize text fields
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton loginButton = new JButton("Login");

        // Customize the login button
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(70, 130, 180)); // Steel blue background
        loginButton.setForeground(Color.WHITE); // White text
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 120, 170), 2), // Darker border
                BorderFactory.createEmptyBorder(10, 20, 10, 20) // Padding inside the button
        ));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (username.equals("admin") && password.equals("123")) {
                    CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
                    readEmpFile(employeeQueue, "Employee.txt");
                    readCustFile(customerQueue, "Customer.txt");
                    readProdFile(productQueue, "Product.txt");
                    readAppFile(appointmentQueue, customerQueue, employeeQueue, "Appointment.txt");
                    readSaleFile(saleQueue, customerQueue, employeeQueue, productQueue, "Sale.txt");
                    frame.setSize(800, 600); // Set the new size for the frame
                    frame.setLocationRelativeTo(null); // Center the frame
                    cl.show(frame.getContentPane(), "MainMenu");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password.");
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        loginPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, gbc);
    }

    private void createMainMenuPanel() {
        mainMenuPanel = new JPanel(new GridLayout(7, 1, 10, 10)); // Add gaps between buttons
        mainMenuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the panel

        JButton employeeButton = createStyledButton("Employee");
        JButton customerButton = createStyledButton("Customer");
        JButton productButton = createStyledButton("Product");
        JButton appointmentButton = createStyledButton("Appointment");
        JButton saleButton = createStyledButton("Sale");
        JButton finaliseButton = createStyledButton("Finalise everything to Database");
        JButton exitButton = createStyledButton("Exit");

        employeeButton.addActionListener(e -> showPanel("Employee"));
        customerButton.addActionListener(e -> showPanel("Customer"));
        productButton.addActionListener(e -> showPanel("Product"));
        appointmentButton.addActionListener(e -> showPanel("Appointment"));
        saleButton.addActionListener(e -> showPanel("Sale"));
        finaliseButton.addActionListener(e -> finaliseData());
        exitButton.addActionListener(e -> System.exit(0));

        mainMenuPanel.add(employeeButton);
        mainMenuPanel.add(customerButton);
        mainMenuPanel.add(productButton);
        mainMenuPanel.add(appointmentButton);
        mainMenuPanel.add(saleButton);
        mainMenuPanel.add(finaliseButton);
        mainMenuPanel.add(exitButton);
    }



    private void createEmployeePanel() {
        employeePanel = new JPanel(new GridLayout(6, 1));
        JButton listButton = new JButton("List Employee");
        JButton addButton = new JButton("Add Employee");
        JButton updateButton = new JButton("Update Employee");
        JButton deleteButton = new JButton("Delete Employee");
        JButton finaliseButton = new JButton("Finalise Employee");
        JButton exitButton = new JButton("Back to Main Menu");

        listButton.addActionListener(e -> {
            JPanel listEmpPanel = CreateListEmployeePanel(employeeQueue);

            // Add the new panel to the frame with a unique name
            frame.add(listEmpPanel, "ListEmployee");

            // Show the new panel
            showPanel("ListEmployee");
        });

        addButton.addActionListener(e -> addEmployee());
        updateButton.addActionListener(e -> updateEmployee());
        deleteButton.addActionListener(e -> deleteEmployee());
        finaliseButton.addActionListener(e -> finaliseEmployee());
        exitButton.addActionListener(e -> showPanel("MainMenu"));

        employeePanel.add(listButton);
        employeePanel.add(addButton);
        employeePanel.add(updateButton);
        employeePanel.add(deleteButton);
        employeePanel.add(finaliseButton);
        employeePanel.add(exitButton);
    }

    private void createCustomerPanel() {
        customerPanel = new JPanel(new GridLayout(6, 1));
        JButton listButton = new JButton("List Customer");
        JButton addButton = new JButton("Add Customer");
        JButton updateButton = new JButton("Update Customer");
        JButton deleteButton = new JButton("Delete Customer");
        JButton finaliseButton = new JButton("Finalise Customer");
        JButton exitButton = new JButton("Back to Main Menu");

        listButton.addActionListener(e -> listCustomer());
        addButton.addActionListener(e -> addCustomer());
        updateButton.addActionListener(e -> updateCustomer());
        deleteButton.addActionListener(e -> deleteCustomer());
        finaliseButton.addActionListener(e -> finaliseCustomer());
        exitButton.addActionListener(e -> showPanel("MainMenu"));

        customerPanel.add(listButton);
        customerPanel.add(addButton);
        customerPanel.add(updateButton);
        customerPanel.add(deleteButton);
        customerPanel.add(finaliseButton);
        customerPanel.add(exitButton);
    }

    private void createProductPanel() {
        productPanel = new JPanel(new GridLayout(6, 1));
        JButton listButton = new JButton("List Product");
        JButton addButton = new JButton("Add Product");
        JButton updateButton = new JButton("Update Product");
        JButton deleteButton = new JButton("Delete Product");
        JButton finaliseButton = new JButton("Finalise Product");
        JButton exitButton = new JButton("Back to Main Menu");

        listButton.addActionListener(e -> listProduct());
        addButton.addActionListener(e -> addProduct());
        updateButton.addActionListener(e -> updateProduct());
        deleteButton.addActionListener(e -> deleteProduct());
        finaliseButton.addActionListener(e -> finaliseProduct());
        exitButton.addActionListener(e -> showPanel("MainMenu"));

        productPanel.add(listButton);
        productPanel.add(addButton);
        productPanel.add(updateButton);
        productPanel.add(deleteButton);
        productPanel.add(finaliseButton);
        productPanel.add(exitButton);
    }

    private void createAppointmentPanel() {
        appointmentPanel = new JPanel(new GridLayout(6, 1));
        JButton listButton = new JButton("List Appointment");
        JButton addButton = new JButton("Add Appointment");
        JButton updateButton = new JButton("Update Appointment");
        JButton deleteButton = new JButton("Delete Appointment");
        JButton finaliseButton = new JButton("Finalise Appointment");
        JButton exitButton = new JButton("Exit Appointment");

        listButton.addActionListener(e -> listAppointment());
        addButton.addActionListener(e -> addAppointment());
        updateButton.addActionListener(e -> updateAppointment());
        deleteButton.addActionListener(e -> deleteAppointment());
        finaliseButton.addActionListener(e -> finaliseAppointment());
        exitButton.addActionListener(e -> showPanel("MainMenu"));

        appointmentPanel.add(listButton);
        appointmentPanel.add(addButton);
        appointmentPanel.add(updateButton);
        appointmentPanel.add(deleteButton);
        appointmentPanel.add(finaliseButton);
        appointmentPanel.add(exitButton);
    }

    private void createSalePanel() {
        salePanel = new JPanel(new GridLayout(6, 1));
        JButton listButton = new JButton("List Sale");
        JButton addButton = new JButton("Add Sale");
        JButton updateButton = new JButton("Update Sale");
        JButton deleteButton = new JButton("Delete Sale");
        JButton finaliseButton = new JButton("Finalise Sale");
        JButton exitButton = new JButton("Back to Main Menu");

        listButton.addActionListener(e -> listSale());
        addButton.addActionListener(e -> addSale());
        updateButton.addActionListener(e -> updateSale());
        deleteButton.addActionListener(e -> deleteSale());
        finaliseButton.addActionListener(e -> finaliseSale());
        exitButton.addActionListener(e -> showPanel("MainMenu"));

        salePanel.add(listButton);
        salePanel.add(addButton);
        salePanel.add(updateButton);
        salePanel.add(deleteButton);
        salePanel.add(finaliseButton);
        salePanel.add(exitButton);
    }

    private void createSearchPanel() {
        searchPanel = new JPanel(new GridLayout(6, 1));
        JButton searchEmployeeButton = createStyledButton("Search Employee");
        JButton searchCustomerButton = createStyledButton("Search Customer");
        JButton searchProductButton = createStyledButton("Search Product");
        JButton searchAppointmentButton = createStyledButton("Search Appointment");
        JButton searchSaleButton = createStyledButton("Search Sale");
        JButton exitButton = createStyledButton("Back to Main Menu");

        searchEmployeeButton.addActionListener(e -> searchEmployee());
        searchCustomerButton.addActionListener(e -> searchCustomer());
        searchProductButton.addActionListener(e -> searchProduct());
        searchAppointmentButton.addActionListener(e -> searchAppointment());
        searchSaleButton.addActionListener(e -> searchSale());
        exitButton.addActionListener(e -> showPanel("MainMenu"));

        searchPanel.add(searchEmployeeButton);
        searchPanel.add(searchCustomerButton);
        searchPanel.add(searchProductButton);
        searchPanel.add(searchAppointmentButton);
        searchPanel.add(searchSaleButton);
        searchPanel.add(exitButton);
    }

    private void showPanel(String panelName) {
        CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
        cl.show(frame.getContentPane(), panelName);
    }

    private void finaliseData() {
        // Save data to files
        writeEmpFile(employeeQueue, "Employee.txt");
        writeCustFile(customerQueue, "Customer.txt");
        writeProdFile(productQueue, "Product.txt");
        writeAppFile(appointmentQueue, "Appointment.txt");
        writeSaleFile(saleQueue, "Sale.txt");

        // Show confirmation message
        JOptionPane.showMessageDialog(frame, "Data finalised to database.");
    }

    private JPanel CreateListEmployeePanel(Queue<Employee> employeeQueue) {
        JPanel listEmpPanel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        if (employeeQueue.isEmpty()) {
            textArea.setText("No employee found.");
        } else {
            Queue<Employee> temp = new Queue<>();
            while (!employeeQueue.isEmpty()) {
                Employee current = employeeQueue.dequeue();
                textArea.append(current.toString() + "\n");
                temp.enqueue(current);
            }
            while (!temp.isEmpty()) {
                employeeQueue.enqueue(temp.dequeue());
            }
        }

            JButton backButton = new JButton("Back");
            backButton.addActionListener(e -> showPanel("Employee"));

            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            bottomPanel.add(backButton);

            listEmpPanel.add(scrollPane, BorderLayout.CENTER);
            listEmpPanel.add(bottomPanel, BorderLayout.SOUTH);
            return listEmpPanel;
    }




    private void addEmployee() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField hireDateField = new JTextField();

        Object[] fields = {
                "Employee ID:", idField,
                "Employee Name:", nameField,
                "Hire Date:", hireDateField
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Add Employee", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String empID = idField.getText();
            String empName = nameField.getText();
            String hireDate = hireDateField.getText();

            if (!empID.isEmpty() && !empName.isEmpty() && !hireDate.isEmpty()) {
                Employee newEmployee = new Employee(empID, empName, hireDate);
                employeeQueue.enqueue(newEmployee);

                JOptionPane.showMessageDialog(frame, "Employee added successfully:\n" + newEmployee, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateEmployee() {
        JTextField idField = new JTextField();

        Object[] idInput = {
                "Enter Employee ID to update:", idField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idInput, "Update Employee", JOptionPane.OK_CANCEL_OPTION);

        if (idOption == JOptionPane.OK_OPTION) {
            String empID = idField.getText();
            Employee empChosen = searchEmployee(employeeQueue, empID);

            if (empChosen != null) {
                JTextField nameField = new JTextField(empChosen.getEmpFullName());
                JTextField hireDateField = new JTextField(empChosen.getDateHired());

                Object[] updateFields = {
                        "New Employee Name:", nameField,
                        "New Hire Date:", hireDateField
                };

                int updateOption = JOptionPane.showConfirmDialog(frame, updateFields, "Update Employee", JOptionPane.OK_CANCEL_OPTION);

                if (updateOption == JOptionPane.OK_OPTION) {
                    empChosen.setEmpFullName(nameField.getText());
                    empChosen.setDateHired(hireDateField.getText());

                    JOptionPane.showMessageDialog(frame, "Employee updated successfully:\n" + empChosen, "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "No matching employee found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteEmployee() {
        JTextField idField = new JTextField();

        Object[] idInput = {
                "Enter Employee ID to delete:", idField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idInput, "Delete Employee", JOptionPane.OK_CANCEL_OPTION);

        if (idOption == JOptionPane.OK_OPTION) {
            String empID = idField.getText();
            deleteEmployee(employeeQueue, appointmentQueue, saleQueue, empID);

            JOptionPane.showMessageDialog(frame, "Employee and related data deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Reusing your non-Swing method
    public static void deleteEmployee(Queue<Employee> employeeQueue, Queue<Appointment> appointmentQueue, Queue<Sale> saleQueue, String empID) {
        Employee empChosen = searchEmployee(employeeQueue, empID);
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

            JOptionPane.showMessageDialog(null, "Employee and related data deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(null,"No matching employee found for deletion.");
        }
    }

    private void finaliseEmployee() {
        // Save data to files
        writeEmpFile(employeeQueue, "Employee.txt");

        // Show confirmation message
        JOptionPane.showMessageDialog(frame, "Data finalised to database.");
    }


    private void listCustomer() {
        JPanel listCustPanel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        if (customerQueue.isEmpty()) {
            textArea.setText("No customer found.");
        } else {
            Queue<Customer> temp = new Queue<>();
            while (!customerQueue.isEmpty()) {
                Customer current = customerQueue.dequeue();
                textArea.append(current.toString()+ "\n");
                temp.enqueue(current);
            }
            while (!temp.isEmpty()) {
                customerQueue.enqueue(temp.dequeue());
            }
        }

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showPanel("Customer"));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(backButton);

        listCustPanel.add(scrollPane, BorderLayout.CENTER);
        listCustPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(listCustPanel, "ListCustomer");
        showPanel("ListCustomer");
    }

    private void addCustomer() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();

        Object[] fields = {
                "Customer ID:", idField,
                "Customer Name:", nameField,
                "Customer Age:", ageField,
                "Customer Gender:", genderField,
                "Customer Phone:", phoneField,
                "Customer Email:", emailField
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Add Customer", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String custID = idField.getText();
            String custName = nameField.getText();
            int custAge = Integer.parseInt(ageField.getText());
            String custGender = genderField.getText();
            String custPhone = phoneField.getText();
            String custEmail = emailField.getText();

            if (!custID.isEmpty() && !custName.isEmpty() && custAge > 0 && !custGender.isEmpty() && !custPhone.isEmpty() && !custEmail.isEmpty()) {
                Customer newCustomer = new Customer(custID, custName, custAge, custGender, custPhone, custEmail);
                customerQueue.enqueue(newCustomer);

                JOptionPane.showMessageDialog(frame, "Customer added successfully:\n" + newCustomer, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateCustomer() {
        JTextField idField = new JTextField();

        Object[] idInput = {
                "Enter Customer ID to update:", idField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idInput, "Update Customer", JOptionPane.OK_CANCEL_OPTION);

        if (idOption == JOptionPane.OK_OPTION) {
            String custID = idField.getText();
            Customer custChosen = searchCustomer(customerQueue, custID);

            if (custChosen != null) {
                JTextField nameField = new JTextField(custChosen.getCustName());
                JTextField ageField = new JTextField(String.valueOf(custChosen.getCustAge()));
                JTextField genderField = new JTextField(custChosen.getCustGender());
                JTextField phoneField = new JTextField(custChosen.getCustPhone());
                JTextField emailField = new JTextField(custChosen.getCustEmail());

                Object[] updateFields = {
                        "New Customer Name:", nameField,
                        "New Customer Age:", ageField,
                        "New Customer Gender:", genderField,
                        "New Customer Phone:", phoneField,
                        "New Customer Email:", emailField
                };

                int updateOption = JOptionPane.showConfirmDialog(frame, updateFields, "Update Customer", JOptionPane.OK_CANCEL_OPTION);

                if (updateOption == JOptionPane.OK_OPTION) {
                    custChosen.setCustName(nameField.getText());
                    custChosen.setCustAge(Integer.parseInt(ageField.getText()));
                    custChosen.setCustGender(genderField.getText());
                    custChosen.setCustPhone(phoneField.getText());
                    custChosen.setCustEmail(emailField.getText());

                    JOptionPane.showMessageDialog(frame, "Customer updated successfully:\n" + custChosen, "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "No matching customer found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteCustomer() {
        JTextField idField = new JTextField();

        Object[] idInput = {
                "Enter Customer ID to delete:", idField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idInput, "Delete Customer", JOptionPane.OK_CANCEL_OPTION);

        if (idOption == JOptionPane.OK_OPTION) {
            String custID = idField.getText();
            Customer custChosen = searchCustomer(customerQueue, custID);

            if (custChosen != null) {
                deleteCustomer(customerQueue, appointmentQueue, saleQueue, custID);
                JOptionPane.showMessageDialog(frame, "Customer deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "No matching customer found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void deleteCustomer(Queue<Customer> customerQueue, Queue<Appointment> appointmentQueue, Queue<Sale> saleQueue, String custID) {
        Customer custChosen = searchCustomer(customerQueue, custID);
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

            JOptionPane.showMessageDialog(null,"Customer and related data deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(null,"No matching customer found for deletion.");
        }
    }

    private void finaliseCustomer() {
        writeCustFile(customerQueue, "Customer.txt");
        JOptionPane.showMessageDialog(frame, "Data finalised to database.");
    }

    private void listProduct() {
        JPanel listProdPanel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        if (productQueue.isEmpty()) {
            textArea.setText("No product found.");
        } else {
            Queue<Product> temp = new Queue<>();
            while (!productQueue.isEmpty()) {
                Product current = productQueue.dequeue();
                textArea.append(current.toString()+ "\n");
                temp.enqueue(current);
            }
            while (!temp.isEmpty()) {
                productQueue.enqueue(temp.dequeue());
            }
        }

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showPanel("Product"));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(backButton);

        listProdPanel.add(scrollPane, BorderLayout.CENTER);
        listProdPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(listProdPanel, "ListProduct");
        showPanel("ListProduct");
    }

    private void addProduct() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField priceField = new JTextField();

        Object[] fields = {
                "Product ID:", idField,
                "Product Name:", nameField,
                "Product Category:", categoryField,
                "Product Price:", priceField
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Add Product", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String prodID = idField.getText();
            String prodName = nameField.getText();
            String prodCategory = categoryField.getText();
            double prodPrice = Double.parseDouble(priceField.getText());

            if (!prodID.isEmpty() && !prodName.isEmpty() && !prodCategory.isEmpty() && prodPrice > 0) {
                Product newProduct = new Product(prodID, prodName, prodCategory, prodPrice);
                productQueue.enqueue(newProduct);

                JOptionPane.showMessageDialog(frame, "Product added successfully:\n" + newProduct, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateProduct() {
        JTextField idField = new JTextField();

        Object[] idInput = {
                "Enter Product ID to update:", idField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idInput, "Update Product", JOptionPane.OK_CANCEL_OPTION);

        if (idOption == JOptionPane.OK_OPTION) {
            String prodID = idField.getText();
            Product prodChosen = searchProduct(productQueue, prodID);

            if (prodChosen != null) {
                JTextField nameField = new JTextField(prodChosen.getPrdName());
                JTextField categoryField = new JTextField(prodChosen.getPrdCategory());
                JTextField priceField = new JTextField(String.valueOf(prodChosen.getPrdPrice()));

                Object[] updateFields = {
                        "New Product Name:", nameField,
                        "New Product Category:", categoryField,
                        "New Product Price:", priceField
                };

                int updateOption = JOptionPane.showConfirmDialog(frame, updateFields, "Update Product", JOptionPane.OK_CANCEL_OPTION);

                if (updateOption == JOptionPane.OK_OPTION) {
                    prodChosen.setPrdName(nameField.getText());
                    prodChosen.setPrdCategory(categoryField.getText());
                    prodChosen.setPrdPrice(Double.parseDouble(priceField.getText()));

                    JOptionPane.showMessageDialog(frame, "Product updated successfully:\n" + prodChosen, "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "No matching product found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteProduct() {
        JTextField idField = new JTextField();

        Object[] idInput = {
                "Enter Product ID to delete:", idField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idInput, "Delete Product", JOptionPane.OK_CANCEL_OPTION);

        if (idOption == JOptionPane.OK_OPTION) {
            String prodID = idField.getText();
            Product prodChosen = searchProduct(productQueue, prodID);

            if (prodChosen != null) {
                deleteProduct(productQueue, saleQueue, prodID);
                JOptionPane.showMessageDialog(frame, "Product and related data deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "No matching product found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void deleteProduct(Queue<Product> productQueue, Queue<Sale> saleQueue, String productID) {
        Product prodChosen = searchProduct(productQueue, productID);
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

            JOptionPane.showMessageDialog(null,"Product and related data deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(null,"No matching product found for deletion.");
        }
    }

    private void finaliseProduct() {
        writeProdFile(productQueue, "Product.txt");
        JOptionPane.showMessageDialog(frame, "Data finalised to database.");
    }

    private void listAppointment() {
        JPanel listAppPanel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        if (appointmentQueue.isEmpty()) {
            textArea.setText("No appointment found.");
        } else {
            Queue<Appointment> temp = new Queue<>();
            while (!appointmentQueue.isEmpty()) {
                Appointment current = appointmentQueue.dequeue();
                textArea.append(current.toString()+ "\n");
                temp.enqueue(current);
            }
            while (!temp.isEmpty()) {
                appointmentQueue.enqueue(temp.dequeue());
            }
        }

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showPanel("Appointment"));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(backButton);

        listAppPanel.add(scrollPane, BorderLayout.CENTER);
        listAppPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(listAppPanel, "ListAppointment");
        showPanel("ListAppointment");
    }

    private void addAppointment() {
        JTextField idField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField custIDField = new JTextField();
        JTextField empIDField = new JTextField();

        Object[] fields = {
                "Appointment ID:", idField,
                "Appointment Date:", dateField,
                "Customer ID:", custIDField,
                "Employee ID:", empIDField
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Add Appointment", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String aptID = idField.getText();
            String aptDate = dateField.getText();
            String custID = custIDField.getText();
            String empID = empIDField.getText();

            Customer customer = searchCustomer(customerQueue, custID);
            Employee employee = searchEmployee(employeeQueue, empID);

            if (customer != null && employee != null && !aptID.isEmpty() && !aptDate.isEmpty()) {
                Appointment newAppointment = new Appointment(aptID, aptDate, customer, employee);
                appointmentQueue.enqueue(newAppointment);

                JOptionPane.showMessageDialog(frame, "Appointment added successfully:\n" + newAppointment, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "All fields are required and must be valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateAppointment() {
        JTextField idField = new JTextField();

        Object[] idInput = {
                "Enter Appointment ID to update:", idField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idInput, "Update Appointment", JOptionPane.OK_CANCEL_OPTION);

        if (idOption == JOptionPane.OK_OPTION) {
            String aptID = idField.getText();
            Appointment aptChosen = searchAppointment(appointmentQueue, aptID);

            if (aptChosen != null) {
                JTextField dateField = new JTextField(aptChosen.getAptDate());
                JTextField custIDField = new JTextField(aptChosen.getCustomer().getCustID());
                JTextField empIDField = new JTextField(aptChosen.getEmployee().getEmpID());

                Object[] updateFields = {
                        "New Appointment Date:", dateField,
                        "New Customer ID:", custIDField,
                        "New Employee ID:", empIDField
                };

                int updateOption = JOptionPane.showConfirmDialog(frame, updateFields, "Update Appointment", JOptionPane.OK_CANCEL_OPTION);

                if (updateOption == JOptionPane.OK_OPTION) {
                    Customer customer = searchCustomer(customerQueue, custIDField.getText());
                    Employee employee = searchEmployee(employeeQueue, empIDField.getText());

                    if (customer != null && employee != null) {
                        aptChosen.setAptDate(dateField.getText());
                        aptChosen.setCustomer(customer);
                        aptChosen.setEmployee(employee);

                        JOptionPane.showMessageDialog(frame, "Appointment updated successfully:\n" + aptChosen, "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid Customer ID or Employee ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "No matching appointment found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteAppointment() {
        JTextField idField = new JTextField();

        Object[] idInput = {
                "Enter Appointment ID to delete:", idField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idInput, "Delete Appointment", JOptionPane.OK_CANCEL_OPTION);

        if (idOption == JOptionPane.OK_OPTION) {
            String aptID = idField.getText();
            deleteAppointment(appointmentQueue, aptID);

            JOptionPane.showMessageDialog(frame, "Appointment deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void deleteAppointment(Queue<Appointment> appointmentQueue, String aptID) {
        Appointment aptChosen = searchAppointment(appointmentQueue, aptID);
        if (aptChosen != null) {

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
            JOptionPane.showMessageDialog(null,"Appointment deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(null,"No matching appointment found for deletion.");
        }
    }

    private void finaliseAppointment() {
        writeAppFile(appointmentQueue, "Appointment.txt");
        JOptionPane.showMessageDialog(frame, "Data finalised to database.");
    }

    private void listSale() {
        JPanel listSalePanel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        if (saleQueue.isEmpty()) {
            textArea.setText("No sale found.");
        } else {
            Queue<Sale> temp = new Queue<>();
            while (!saleQueue.isEmpty()) {
                Sale current = saleQueue.dequeue();
                textArea.append(current.toString()+ "\n");
                temp.enqueue(current);
            }
            while (!temp.isEmpty()) {
                saleQueue.enqueue(temp.dequeue());
            }
        }

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showPanel("Sale"));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(backButton);

        listSalePanel.add(scrollPane, BorderLayout.CENTER);
        listSalePanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(listSalePanel, "ListSale");
        showPanel("ListSale");
    }

    private void addSale() {
        JTextField idField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField custIDField = new JTextField();
        JTextField empIDField = new JTextField();
        JTextField prodIDField = new JTextField();
        JTextField qtyField = new JTextField();

        Object[] fields = {
                "Sale ID:", idField,
                "Sale Date:", dateField,
                "Customer ID:", custIDField,
                "Employee ID:", empIDField,
                "Product ID:", prodIDField,
                "Quantity:", qtyField,
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Add Sale", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String saleID = idField.getText();
            String saleDate = dateField.getText();
            String custID = custIDField.getText();
            String empID = empIDField.getText();
            String prodID = prodIDField.getText();
            int qty = Integer.parseInt(qtyField.getText());

            Customer customer = searchCustomer(customerQueue, custID);
            Employee employee = searchEmployee(employeeQueue, empID);
            Product product = searchProduct(productQueue, prodID);

            if (customer != null && employee != null && product != null && !saleID.isEmpty() && !saleDate.isEmpty() && qty > 0) {
                Sale newSale = new Sale(saleID, customer, employee, product, qty, saleDate);
                saleQueue.enqueue(newSale);

                JOptionPane.showMessageDialog(frame, "Sale added successfully:\n" + newSale, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "All fields are required and must be valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateSale() {
        JTextField idField = new JTextField();

        Object[] idInput = {
                "Enter Sale ID to update:", idField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idInput, "Update Sale", JOptionPane.OK_CANCEL_OPTION);

        if (idOption == JOptionPane.OK_OPTION) {
            String saleID = idField.getText();
            Sale saleChosen = searchSale(saleQueue, saleID);

            if (saleChosen != null) {
                JTextField dateField = new JTextField(saleChosen.getSaleDate());
                JTextField custIDField = new JTextField(saleChosen.getCust().getCustID());
                JTextField empIDField = new JTextField(saleChosen.getEmp().getEmpID());
                JTextField prodIDField = new JTextField(saleChosen.getPrd().getPrdID());
                JTextField qtyField = new JTextField(String.valueOf(saleChosen.getSaleQty()));

                Object[] updateFields = {
                        "New Sale Date:", dateField,
                        "New Customer ID:", custIDField,
                        "New Employee ID:", empIDField,
                        "New Product ID:", prodIDField,
                        "New Quantity:", qtyField,
                };

                int updateOption = JOptionPane.showConfirmDialog(frame, updateFields, "Update Sale", JOptionPane.OK_CANCEL_OPTION);

                if (updateOption == JOptionPane.OK_OPTION) {
                    Customer customer = searchCustomer(customerQueue, custIDField.getText());
                    Employee employee = searchEmployee(employeeQueue, empIDField.getText());
                    Product product = searchProduct(productQueue, prodIDField.getText());

                    if (customer != null && employee != null && product != null) {
                        saleChosen.setSaleDate(dateField.getText());
                        saleChosen.setCust(customer);
                        saleChosen.setEmp(employee);
                        saleChosen.setPrd(product);
                        saleChosen.setSaleQty(Integer.parseInt(qtyField.getText()));

                        JOptionPane.showMessageDialog(frame, "Sale updated successfully:\n" + saleChosen, "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid Customer ID, Employee ID, or Product ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "No matching sale found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteSale() {
        JTextField idField = new JTextField();

        Object[] idInput = {
                "Enter Sale ID to delete:", idField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idInput, "Delete Sale", JOptionPane.OK_CANCEL_OPTION);

        if (idOption == JOptionPane.OK_OPTION) {
            String saleID = idField.getText();
            deleteSale(saleQueue, saleID);

        }
    }


    public static void deleteSale(Queue<Sale> saleQueue, String saleID) {
        Sale saleChosen = searchSale(saleQueue, saleID);
        if (saleChosen != null) {
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
            JOptionPane.showMessageDialog(null, "Sale deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "No matching sale found for deletion.");
        }
    }

    private void finaliseSale() {
        writeSaleFile(saleQueue, "Sale.txt");
        JOptionPane.showMessageDialog(frame, "Data finalised to database.");
    }

    public static Employee searchEmployee (Queue<Employee> employeeQueue, String empID)
    {
        boolean found = false;
        Scanner sc = new Scanner(System.in);
        Employee empChosen = null;
        if (employeeQueue.isEmpty()) {
            System.out.println("No employee found.");
        }
        else if (!employeeQueue.isEmpty())
        {
            while (!found) {
                Employee current = employeeQueue.dequeue();
                Queue<Employee> temp = new Queue<>();
                ArrayList<Employee> employeeList = new ArrayList<>();
                while (current != null) {
                    employeeList.add(current);
                    temp.enqueue(current);
                    current = employeeQueue.dequeue();
                }
                while (!temp.isEmpty()){
                    current = temp.dequeue();
                    employeeQueue.enqueue(current);
                }
                for (int i = 0; i < employeeList.size(); i++) {
                    Employee empSearch = employeeList.get(i);
                    if (empSearch.getEmpID().equals(empID)) {
                        empChosen = empSearch;
                        found = true;
                        return empChosen;
                    }
                }
                if (!found) {
                    System.out.println("Employee with said ID does not exist. Please try again.");
                }
                employeeList.clear();
            }
        }
        return null;
    }

    public static Customer searchCustomer(Queue<Customer> customerQueue, String custID) {
        boolean found = false;
        Scanner sc = new Scanner(System.in);
        Customer custChosen = null;
        if (customerQueue.isEmpty()) {
            System.out.println("No customer found.");
        }
        else if (!customerQueue.isEmpty()) {
            while (!found) {
                Customer current = customerQueue.dequeue();
                Queue<Customer> temp = new Queue<>();
                ArrayList<Customer> customerList = new ArrayList<>();
                while (current != null) {
                    customerList.add(current);
                    temp.enqueue(current);
                    current = customerQueue.dequeue();
                }

                for (int i = 0; i < customerList.size(); i++) {
                    Customer custSearch = customerList.get(i);
                    if (custSearch.getCustID().equals(custID)) {
                        custChosen = custSearch;
                        found = true;
                        return custChosen;
                    }
                }

                while (!temp.isEmpty()) {
                    current = temp.dequeue();
                    customerQueue.enqueue(current);
                }

                if (!found) {
                    System.out.println("Customer with said ID does not exist. Please try again.");
                }
                customerList.clear();
            }
        }
        return null;
    }

    public static Product searchProduct(Queue<Product> productQueue, String prdID) {
        boolean found = false;
        Scanner sc = new Scanner(System.in);
        Product prdChosen = null;
        if (productQueue.isEmpty()) {
            System.out.println("No product found.");
        }
        else if (!productQueue.isEmpty()) {
            while (!found) {
                Product current = productQueue.dequeue();
                Queue<Product> temp = new Queue<>();
                ArrayList<Product> productList = new ArrayList<>();
                while (current != null) {
                    productList.add(current);
                    temp.enqueue(current);
                    current = productQueue.dequeue();
                }

                for (int i = 0; i < productList.size(); i++) {
                    Product prdSearch = productList.get(i);
                    if (prdSearch.getPrdID().equals(prdID)) {
                        prdChosen = prdSearch;
                        found = true;
                        return prdChosen;
                    }
                }

                while (!temp.isEmpty()) {
                    current = temp.dequeue();
                    productQueue.enqueue(current);
                }

                if (!found) {
                    System.out.println("Product with said ID does not exist. Please try again.");
                }
                productList.clear();
            }
        }
        return null;
    }

    public static Appointment searchAppointment(Queue<Appointment> appointmentQueue, String appID) {
        boolean found = false;
        Scanner sc = new Scanner(System.in);
        appID = null;
        Appointment appChosen = null;
        if (appointmentQueue.isEmpty())
        {
            System.out.println("No appointment found.");
        }
        else if (!appointmentQueue.isEmpty())
        {
            while (!found) {
                appID = sc.nextLine();
                Appointment current = appointmentQueue.dequeue();
                Queue<Appointment> temp = new Queue<>();
                ArrayList<Appointment> appointmentList = new ArrayList<>();
                while (current != null) {
                    appointmentList.add(current);
                    temp.enqueue(current);
                    current = appointmentQueue.dequeue();
                }

                for (int i = 0; i < appointmentList.size(); i++) {
                    Appointment appSearch = appointmentList.get(i);
                    if (appSearch.getAptID().equals(appID)) {
                        System.out.println("Appointment found.");
                        appChosen = appSearch;
                        found = true;
                        return appChosen;
                    }
                }

                while (!temp.isEmpty()) {
                    current = temp.dequeue();
                    appointmentQueue.enqueue(current);
                }

                if (!found) {
                    System.out.println("Appointment with said ID does not exist. Please try again.");
                }
                appointmentList.clear();
            }
        }
        return null;
    }

    public static Sale searchSale(Queue<Sale> saleQueue, String saleID) {
        boolean found = false;
        Scanner sc = new Scanner(System.in);
        Sale saleChosen = null;
        if (saleQueue.isEmpty())
        {
            System.out.println("No sale found.");
        }
        else if (!saleQueue.isEmpty())
        {
            while (!found) {
                saleID = sc.nextLine();
                Sale current = saleQueue.dequeue();
                Queue<Sale> temp = new Queue<>();
                ArrayList<Sale> saleList = new ArrayList<>();
                while (current != null) {
                    saleList.add(current);
                    temp.enqueue(current);
                    current = saleQueue.dequeue();
                }

                for (int i = 0; i < saleList.size(); i++) {
                    Sale saleSearch = saleList.get(i);
                    if (saleSearch.getSaleID().equals(saleID)) {
                        System.out.println("Sale found.");
                        saleChosen = saleSearch;
                        found = true;
                        return saleChosen;
                    }
                }

                while (!temp.isEmpty()) {
                    current = temp.dequeue();
                    saleQueue.enqueue(current);
                }

                if (!found) {
                    System.out.println("Sale with said ID does not exist. Please try again.");
                }
                saleList.clear();
            }
        }
        return null;
    }

    public static void readCustFile(Queue<Customer> customerQueue, String fileName) {
        while (!customerQueue.isEmpty()) {
            customerQueue.dequeue();
        }

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

                    customerQueue.enqueue(new Customer(custID, custName, custAge, custGender, custPhone, custEmail));
                } else {
                    JOptionPane.showMessageDialog(null, "Skipping invalid line: " + line);
                }
            }
            JOptionPane.showMessageDialog(null, "Data loaded successfully from " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }
    }
    public static void writeCustFile(Queue<Customer> customerQueue, String fileName) {
        if (customerQueue == null || customerQueue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Customer list is empty. Nothing to write.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            Queue<Customer> temp = new Queue<>();
            Customer current;

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

            do {
                customerQueue.enqueue(temp.dequeue());
            } while (!temp.isEmpty());

            JOptionPane.showMessageDialog(null, "Data saved successfully to " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage());
        }
    }

    public static void readEmpFile(Queue<Employee> employeeQueue, String fileName) {
        while (!employeeQueue.isEmpty()) {
            employeeQueue.dequeue();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");

                if (details.length == 3) {
                    String empID = details[0];
                    String empFullName = details[1];
                    String empDateHired = details[2];

                    employeeQueue.enqueue(new Employee(empID, empFullName, empDateHired));
                } else {
                    JOptionPane.showMessageDialog(null, "Skipping invalid line: " + line);
                }
            }
            JOptionPane.showMessageDialog(null, "Data loaded successfully from " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }
    }

    public static void writeEmpFile(Queue<Employee> employeeQueue, String fileName) {
        if (employeeQueue == null || employeeQueue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Employee list is empty. Nothing to write.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            Queue<Employee> temp = new Queue<>();
            Employee current;

            do {
                current = employeeQueue.dequeue();
                bw.write(current.getEmpID() + "," +
                        current.getEmpFullName() + "," +
                        current.getDateHired());
                bw.newLine();
                temp.enqueue(current);
            } while (!employeeQueue.isEmpty());

            do {
                employeeQueue.enqueue(temp.dequeue());
            } while (!temp.isEmpty());

            JOptionPane.showMessageDialog(null, "Data saved successfully to " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage());
        }
    }

    public static void readProdFile(Queue<Product> productQueue, String fileName) {
        while (!productQueue.isEmpty()) {
            productQueue.dequeue();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");

                if (details.length == 4) {
                    String prodID = details[0];
                    String prodName = details[1];
                    String prodCategory = details[2];
                    double prodPrice = Double.parseDouble(details[3]);

                    productQueue.enqueue(new Product(prodID, prodName, prodCategory, prodPrice));
                } else {
                    JOptionPane.showMessageDialog(null, "Skipping invalid line: " + line);
                }
            }
            JOptionPane.showMessageDialog(null, "Data loaded successfully from " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }
    }
    public static void writeProdFile(Queue<Product> productQueue, String fileName) {
        if (productQueue == null || productQueue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Product list is empty. Nothing to write.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            Queue<Product> temp = new Queue<>();
            Product current;

            do {
                current = productQueue.dequeue();
                bw.write(current.getPrdID() + "," +
                        current.getPrdName() + "," +
                        current.getPrdCategory() + "," +
                        current.getPrdPrice());
                bw.newLine();
                temp.enqueue(current);
            } while (!productQueue.isEmpty());

            do {
                productQueue.enqueue(temp.dequeue());
            } while (!temp.isEmpty());

            JOptionPane.showMessageDialog(null, "Data saved successfully to " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage());
        }
    }

    public static void readAppFile(Queue<Appointment> appointmentQueue, Queue<Customer> customerQueue, Queue<Employee> employeeQueue, String fileName) {
        while (!appointmentQueue.isEmpty()) {
            appointmentQueue.dequeue();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");

                if (details.length == 4) {
                    String aptID = details[0];
                    String aptDate = details[1];
                    String custID = details[2];
                    String empID = details[3];

                    Customer customer = searchCustomer(customerQueue, custID);
                    Employee employee = searchEmployee(employeeQueue, empID);

                    if (customer != null && employee != null) {
                        Appointment appointment = new Appointment(aptID, aptDate, customer, employee);
                        appointmentQueue.enqueue(appointment);
                    } else {
                        JOptionPane.showMessageDialog(null, "Skipping appointment with missing customer or employee: " + line);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Skipping invalid line: " + line);
                }
            }
            JOptionPane.showMessageDialog(null, "Data loaded successfully from " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }
    }

    public static void writeAppFile(Queue<Appointment> aptQueue, String fileName) {
        if (aptQueue == null || aptQueue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Appointment list is empty. Nothing to write.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            Queue<Appointment> temp = new Queue<>();
            Appointment current;

            do {
                current = aptQueue.dequeue();
                bw.write(current.getAptID() + "," +
                        current.getAptDate() + "," +
                        current.getCustomer().getCustID() + "," +
                        current.getEmployee().getEmpID());
                bw.newLine();
                temp.enqueue(current);
            } while (!aptQueue.isEmpty());

            do {
                aptQueue.enqueue(temp.dequeue());
            } while (!temp.isEmpty());

            JOptionPane.showMessageDialog(null, "Data saved successfully to " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage());
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

                if (details.length == 6) {
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
                    } else {
                        JOptionPane.showMessageDialog(null, "Skipping sale with missing customer, employee, or product: " + line);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Skipping invalid line: " + line);
                }
            }
            JOptionPane.showMessageDialog(null, "Data loaded successfully from " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }
    }

    private void searchEmployee() {
        JTextField idField = new JTextField();

        Object[] idInput = {
                "Enter Employee ID to search:", idField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idInput, "Search Employee", JOptionPane.OK_CANCEL_OPTION);

        if (idOption == JOptionPane.OK_OPTION) {
            String empID = idField.getText();
            Employee empChosen = searchEmployee(employeeQueue, empID);

            if (empChosen != null) {
                JOptionPane.showMessageDialog(frame, "Employee found:\n" + empChosen, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "No matching employee found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void searchCustomer() {
        JTextField idField = new JTextField();

        Object[] idInput = {
                "Enter Customer ID to search:", idField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idInput, "Search Customer", JOptionPane.OK_CANCEL_OPTION);

        if (idOption == JOptionPane.OK_OPTION) {
            String custID = idField.getText();
            Customer custChosen = searchCustomer(customerQueue, custID);

            if (custChosen != null) {
                JOptionPane.showMessageDialog(frame, "Customer found:\n" + custChosen, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "No matching customer found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void searchProduct() {
        JTextField idField = new JTextField();

        Object[] idInput = {
                "Enter Product ID to search:", idField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idInput, "Search Product", JOptionPane.OK_CANCEL_OPTION);

        if (idOption == JOptionPane.OK_OPTION) {
            String prdID = idField.getText();
            Product prdChosen = searchProduct(productQueue, prdID);

            if (prdChosen != null) {
                JOptionPane.showMessageDialog(frame, "Product found:\n" + prdChosen, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "No matching product found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void searchAppointment() {
        JTextField idField = new JTextField();

        Object[] idInput = {
                "Enter Appointment ID to search:", idField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idInput, "Search Appointment", JOptionPane.OK_CANCEL_OPTION);

        if (idOption == JOptionPane.OK_OPTION) {
            String aptID = idField.getText();
            Appointment aptChosen = searchAppointment(appointmentQueue, aptID);

            if (aptChosen != null) {
                JOptionPane.showMessageDialog(frame, "Appointment found:\n" + aptChosen, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "No matching appointment found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void searchSale() {
        JTextField idField = new JTextField();

        Object[] idInput = {
                "Enter Sale ID to search:", idField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idInput, "Search Sale", JOptionPane.OK_CANCEL_OPTION);

        if (idOption == JOptionPane.OK_OPTION) {
            String saleID = idField.getText();
            Sale saleChosen = searchSale(saleQueue, saleID);

            if (saleChosen != null) {
                JOptionPane.showMessageDialog(frame, "Sale found:\n" + saleChosen, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "No matching sale found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void writeSaleFile(Queue<Sale> saleQueue, String fileName) {
        if (saleQueue == null || saleQueue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Sale list is empty. Nothing to write.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            Queue<Sale> temp = new Queue<>();
            Sale current;

            do {
                current = saleQueue.dequeue();
                bw.write(current.getSaleID() + "," +
                        current.getCust().getCustID() + "," +
                        current.getEmp().getEmpID() + "," +
                        current.getPrd().getPrdID() + "," +
                        current.getSaleQty() + "," +
                        current.getSaleDate());
                bw.newLine();
                temp.enqueue(current);
            } while (!saleQueue.isEmpty());

            do {
                saleQueue.enqueue(temp.dequeue());
            } while (!temp.isEmpty());

            JOptionPane.showMessageDialog(null, "Data saved successfully to " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        new LLMainGUI();
    }
}