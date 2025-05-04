import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class LLMainGUI {
    private JFrame frame;
    private JPanel loginPanel, mainMenuPanel, employeePanel, customerPanel, productPanel, appointmentPanel, salePanel, searchPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private LinkedList<Employee> employeeLL = new LinkedList<>();
    private LinkedList<Customer> customerLL = new LinkedList<>();
    private LinkedList<Product> productLL = new LinkedList<>();
    private LinkedList<Appointment> appointmentLL = new LinkedList<>();
    private LinkedList<Sale> saleLL = new LinkedList<>();

    Dimension buttonSize = new Dimension(200, 40);

    public LLMainGUI() {
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
                    readEmpFile(employeeLL, "Employee.txt");
                    readCustFile(customerLL, "Customer.txt");
                    readProdFile(productLL, "Product.txt");
                    readAppFile(appointmentLL, customerLL, employeeLL, "Appointment.txt");
                    readSaleFile(saleLL, customerLL, employeeLL, productLL, "Sale.txt");
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
        mainMenuPanel = new JPanel(new GridBagLayout());
        mainMenuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10); // Add gaps between buttons
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        JButton employeeButton = createStyledButton("Employee");
        JButton customerButton = createStyledButton("Customer");
        JButton productButton = createStyledButton("Product");
        JButton appointmentButton = createStyledButton("Appointment");
        JButton saleButton = createStyledButton("Sale");
        JButton searchButton = createStyledButton("Search for Data");
        JButton finaliseButton = createStyledButton("Finalise everything to Database");
        JButton exitButton = createStyledButton("Exit");

        employeeButton.addActionListener(e -> showPanel("Employee"));
        customerButton.addActionListener(e -> showPanel("Customer"));
        productButton.addActionListener(e -> showPanel("Product"));
        appointmentButton.addActionListener(e -> showPanel("Appointment"));
        saleButton.addActionListener(e -> showPanel("Sale"));
        searchButton.addActionListener(e -> showPanel("Search"));
        finaliseButton.addActionListener(e -> finaliseData());
        exitButton.addActionListener(e -> System.exit(0));

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainMenuPanel.add(employeeButton, gbc);

        gbc.gridy = 1;
        mainMenuPanel.add(customerButton, gbc);

        gbc.gridy = 2;
        mainMenuPanel.add(productButton, gbc);

        gbc.gridy = 3;
        mainMenuPanel.add(appointmentButton, gbc);

        gbc.gridy = 4;
        mainMenuPanel.add(saleButton, gbc);

        gbc.gridy = 5;
        mainMenuPanel.add(searchButton, gbc);

        gbc.gridy = 6;
        mainMenuPanel.add(finaliseButton, gbc);

        gbc.gridy = 7;
        mainMenuPanel.add(exitButton, gbc);
    }


    private void createEmployeePanel() {
        employeePanel = new JPanel(new GridLayout(6, 1));
        JButton listButton = createStyledButton("List Employee", buttonSize);
        JButton searchButton = createStyledButton("Search Employee", buttonSize);
        JButton addButton = createStyledButton("Add Employee", buttonSize);
        JButton updateButton = createStyledButton("Update Employee" , buttonSize);
        JButton deleteButton = createStyledButton("Delete Employee" , buttonSize);
        JButton finaliseButton = createStyledButton("Save Employee" , buttonSize);
        JButton exitButton = createStyledButton("Back to Main Menu" , buttonSize);

        listButton.addActionListener(e -> {
            JPanel listEmpPanel = CreateListEmployeePanel(employeeLL);

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
        JButton listButton = createStyledButton("List Customer", buttonSize);
        JButton addButton = createStyledButton("Add Customer", buttonSize);
        JButton updateButton = createStyledButton("Update Customer", buttonSize);
        JButton deleteButton = createStyledButton("Delete Customer", buttonSize);
        JButton finaliseButton = createStyledButton("Save Customer", buttonSize);
        JButton exitButton = createStyledButton("Back to Main Menu", buttonSize);

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
        JButton listButton = createStyledButton("List Product", buttonSize);
        JButton addButton = createStyledButton("Add Product", buttonSize);
        JButton updateButton = createStyledButton("Update Product", buttonSize);
        JButton deleteButton = createStyledButton("Delete Product", buttonSize);
        JButton finaliseButton = createStyledButton("Save Product", buttonSize);
        JButton exitButton = createStyledButton("Back to Main Menu", buttonSize);

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
        JButton listButton = createStyledButton("List Appointment", buttonSize);
        JButton addButton = createStyledButton("Add Appointment", buttonSize);
        JButton updateButton = createStyledButton("Update Appointment", buttonSize);
        JButton deleteButton = createStyledButton("Delete Appointment", buttonSize);
        JButton finaliseButton = createStyledButton("Save Appointment", buttonSize);
        JButton exitButton = createStyledButton("Back to Main Menu", buttonSize);

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
        JButton listButton = createStyledButton("List Sale", buttonSize);
        JButton addButton = createStyledButton("Add Sale", buttonSize);
        JButton updateButton = createStyledButton("Update Sale", buttonSize);
        JButton deleteButton = createStyledButton("Delete Sale", buttonSize);
        JButton finaliseButton = createStyledButton("Save Sale", buttonSize);
        JButton exitButton = createStyledButton("Back to Main Menu", buttonSize);

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
        JButton searchEmployeeButton = createStyledButton("Search Employee", buttonSize);
        JButton searchCustomerButton = createStyledButton("Search Customer", buttonSize);
        JButton searchProductButton = createStyledButton("Search Product", buttonSize);
        JButton searchAppointmentButton = createStyledButton("Search Appointment", buttonSize);
        JButton searchSaleButton = createStyledButton("Search Sale", buttonSize);
        JButton exitButton = createStyledButton("Back to Main Menu", buttonSize);

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
        writeEmpFile(employeeLL, "Employee.txt");
        writeCustFile(customerLL, "Customer.txt");
        writeProdFile(productLL, "Product.txt");
        writeAppFile(appointmentLL, "Appointment.txt");
        writeSaleFile(saleLL, "Sale.txt");

        // Show confirmation message
        JOptionPane.showMessageDialog(frame, "Data finalised to database.");
    }

    private JPanel CreateListEmployeePanel(LinkedList<Employee> employeeLL) {
        JPanel listEmpPanel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        if (!employeeLL.isEmpty()) {
            Employee current = employeeLL.getFirst();
            while (current != null) {
                textArea.append(current.toString() + "\n");
                current = employeeLL.getNext();
            }
        } else {
            textArea.setText("No employee found.");
        }

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showPanel("Employee"));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(backButton);

        listEmpPanel.add(scrollPane, BorderLayout.CENTER);
        listEmpPanel.add(bottomPanel, BorderLayout.SOUTH);
        return listEmpPanel;
    }


    private void searchEmployee(){
        JTextField idField = new JTextField();

        Object[] idInput = {
                "Enter Employee ID to search:", idField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idInput, "Search Employee", JOptionPane.OK_CANCEL_OPTION);

        if (idOption == JOptionPane.OK_OPTION) {
            String empID = idField.getText();
            Employee empChosen = searchEmployee(employeeLL, empID);

            if (empChosen != null) {
                JOptionPane.showMessageDialog(frame, "Employee found:\n" + empChosen, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "No matching employee found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
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
                employeeLL.add(newEmployee);

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
            Employee empChosen = searchEmployee(employeeLL, empID);

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
            deleteEmployee(employeeLL, appointmentLL, saleLL, empID);

            JOptionPane.showMessageDialog(frame, "Employee and related data deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Reusing your non-Swing method
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

            JOptionPane.showMessageDialog(null,"Employee record deleted successfully!");
        }else{
            JOptionPane.showMessageDialog(null,"No matching employee found for deletion.");
        }
    }

    private void finaliseEmployee() {
        // Save data to files
        writeEmpFile(employeeLL, "Employee.txt");

        // Show confirmation message
        JOptionPane.showMessageDialog(frame, "Data finalised to database.");
    }


    private void listCustomer() {
        JPanel listCustPanel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        if (!customerLL.isEmpty()) {
            Customer current = customerLL.getFirst();
            while (current != null) {
                textArea.append(current.toString() + "\n");
                current = customerLL.getNext();
            }
        } else {
            textArea.setText("No customer found.");
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

    private void searchCustomer() {
        JTextField idField = new JTextField();

        Object[] idInput = {
                "Enter Customer ID to search:", idField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idInput, "Search Customer", JOptionPane.OK_CANCEL_OPTION);

        if (idOption == JOptionPane.OK_OPTION) {
            String custID = idField.getText();
            Customer custChosen = searchCustomer(customerLL, custID);

            if (custChosen != null) {
                JOptionPane.showMessageDialog(frame, "Customer found:\n" + custChosen, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "No matching customer found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
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
                customerLL.add(newCustomer);

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
            Customer custChosen = searchCustomer(customerLL, custID);

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
            Customer custChosen = searchCustomer(customerLL, custID);

            if (custChosen != null) {
                deleteCustomer(customerLL, appointmentLL, saleLL, custID);
                JOptionPane.showMessageDialog(frame, "Customer deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "No matching customer found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
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

            JOptionPane.showMessageDialog(null,"Customer and related data deleted successfully!");
        } else {
           JOptionPane.showMessageDialog(null,"No matching customer found for deletion.");
        }
    }

    private void finaliseCustomer() {
        writeCustFile(customerLL, "Customer.txt");
        JOptionPane.showMessageDialog(frame, "Data finalised to database.");
    }

    private void listProduct() {
        JPanel listProdPanel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        if (!productLL.isEmpty()) {
            Product current = productLL.getFirst();
            while (current != null) {
                textArea.append(current.toString() + "\n");
                current = productLL.getNext();
            }
        } else {
            textArea.setText("No product found.");
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

    private void searchProduct() {
        JTextField idField = new JTextField();

        Object[] idInput = {
                "Enter Product ID to search:", idField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idInput, "Search Product", JOptionPane.OK_CANCEL_OPTION);

        if (idOption == JOptionPane.OK_OPTION) {
            String prodID = idField.getText();
            Product prodChosen = searchProduct(productLL, prodID);

            if (prodChosen != null) {
                JOptionPane.showMessageDialog(frame, "Product found:\n" + prodChosen, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "No matching product found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
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
                productLL.add(newProduct);

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
            Product prodChosen = searchProduct(productLL, prodID);

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
            Product prodChosen = searchProduct(productLL, prodID);

            if (prodChosen != null) {
                deleteProduct(productLL, saleLL, prodID);
                JOptionPane.showMessageDialog(frame, "Product and related data deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "No matching product found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
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

            JOptionPane.showMessageDialog(null,"Product and related data deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(null,"No matching product found for deletion.");
        }
    }

    private void finaliseProduct() {
        writeProdFile(productLL, "Product.txt");
        JOptionPane.showMessageDialog(frame, "Data finalised to database.");
    }

    private void listAppointment() {
        JPanel listAppPanel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        if (!appointmentLL.isEmpty()) {
            Appointment current = appointmentLL.getFirst();
            while (current != null) {
                textArea.append(current.toString() + "\n");
                current = appointmentLL.getNext();
            }
        } else {
            textArea.setText("No appointment found.");
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

    private void searchAppointment() {
        JTextField idField = new JTextField();

        Object[] idInput = {
                "Enter Appointment ID to search:", idField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idInput, "Search Appointment", JOptionPane.OK_CANCEL_OPTION);

        if (idOption == JOptionPane.OK_OPTION) {
            String aptID = idField.getText();
            Appointment aptChosen = searchAppointment(appointmentLL, aptID);

            if (aptChosen != null) {
                JOptionPane.showMessageDialog(frame, "Appointment found:\n" + aptChosen, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "No matching appointment found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
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

            Customer customer = searchCustomer(customerLL, custID);
            Employee employee = searchEmployee(employeeLL, empID);

            if (customer != null && employee != null && !aptID.isEmpty() && !aptDate.isEmpty()) {
                Appointment newAppointment = new Appointment(aptID, aptDate, customer, employee);
                appointmentLL.add(newAppointment);

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
            Appointment aptChosen = searchAppointment(appointmentLL, aptID);

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
                    Customer customer = searchCustomer(customerLL, custIDField.getText());
                    Employee employee = searchEmployee(employeeLL, empIDField.getText());

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
            deleteAppointment(appointmentLL, aptID);

            JOptionPane.showMessageDialog(frame, "Appointment deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(null,"Appointment deleted successfully!");
        } else {
           JOptionPane.showMessageDialog(null,"No matching appointment found for deletion.");
        }
    }

    private void finaliseAppointment() {
        writeAppFile(appointmentLL, "Appointment.txt");
        JOptionPane.showMessageDialog(frame, "Data finalised to database.");
    }

    private void listSale() {
        JPanel listSalePanel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        if (!saleLL.isEmpty()) {
            Sale current = saleLL.getFirst();
            while (current != null) {
                textArea.append(current.toString() + "\n");
                current = saleLL.getNext();
            }
        } else {
            textArea.setText("No sale found.");
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

    private void searchSale() {
        JTextField idField = new JTextField();

        Object[] idInput = {
                "Enter Sale ID to search:", idField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idInput, "Search Sale", JOptionPane.OK_CANCEL_OPTION);

        if (idOption == JOptionPane.OK_OPTION) {
            String saleID = idField.getText();
            Sale saleChosen = searchSale(saleLL, saleID);

            if (saleChosen != null) {
                JOptionPane.showMessageDialog(frame, "Sale found:\n" + saleChosen, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "No matching sale found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
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

            Customer customer = searchCustomer(customerLL, custID);
            Employee employee = searchEmployee(employeeLL, empID);
            Product product = searchProduct(productLL, prodID);

            if (customer != null && employee != null && product != null && !saleID.isEmpty() && !saleDate.isEmpty() && qty > 0) {
                Sale newSale = new Sale(saleID, customer, employee, product, qty, saleDate);
                saleLL.add(newSale);

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
            Sale saleChosen = searchSale(saleLL, saleID);

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
                    Customer customer = searchCustomer(customerLL, custIDField.getText());
                    Employee employee = searchEmployee(employeeLL, empIDField.getText());
                    Product product = searchProduct(productLL, prodIDField.getText());

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
            deleteSale(saleLL, saleID);

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
            JOptionPane.showMessageDialog(null, "Sale deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "No matching sale found for deletion.");
        }
    }

    private void finaliseSale() {
        writeSaleFile(saleLL, "Sale.txt");
        JOptionPane.showMessageDialog(frame, "Data finalised to database.");
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
    public static Customer searchCustomer(LinkedList<Customer> customerLL, String custID) {
        for (int i = 0; i < customerLL.size(); i++) {
            Customer customer = customerLL.get(i);
            if (customer.getCustID().equals(custID)) {
                return customer;
            }
        }
        return null;
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

    public static Appointment searchAppointment(LinkedList<Appointment> appointmentLL, String aptID) {
        for (int i = 0; i < appointmentLL.size(); i++) {
            Appointment appointment = appointmentLL.get(i);
            if (appointment.getAptID().equals(aptID)) {
                return appointment;
            }
        }
        return null;
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
                    JOptionPane.showMessageDialog(  null,"Skipping invalid line: " + line);
                }
            }
            JOptionPane.showMessageDialog(null,"Data loaded successfully from " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }
    }

    //HEREEEEEE
    public static void writeCustFile(LinkedList<Customer> customerLL, String fileName) {
        if (customerLL == null || customerLL.isEmpty()) {
            JOptionPane.showMessageDialog(null,"Customer list is empty. Nothing to write.");
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
            JOptionPane.showMessageDialog(null,"Data saved successfully to " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error writing to file: " + e.getMessage());
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
                    JOptionPane.showMessageDialog(null,"Skipping invalid line: " + line);
                }
            }
            JOptionPane.showMessageDialog(null,"Data loaded successfully from " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error reading file: " + e.getMessage());
        }
    }

    //EMPLOYEEEE WRITE
    public static void writeEmpFile(LinkedList<Employee> employeeLL, String fileName) {
        if (employeeLL == null || employeeLL.isEmpty()) {
            JOptionPane.showMessageDialog(null,"Employee list is empty. Nothing to write.");
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
            JOptionPane.showMessageDialog(null,"Data saved successfully to " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error writing to file: " + e.getMessage());
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
                    JOptionPane.showMessageDialog(null,"Skipping invalid line: " + line);
                }
            }
            JOptionPane.showMessageDialog(null,"Data loaded successfully from " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error reading file: " + e.getMessage());
        }
    }

    //PRODUCT WRITE
    public static void writeProdFile(LinkedList<Product> productLL, String fileName) {
        if (productLL == null || productLL.isEmpty()) {
            JOptionPane.showMessageDialog(null,"Product list is empty. Nothing to write.");
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
            JOptionPane.showMessageDialog(null,"Data saved successfully to " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error writing to file: " + e.getMessage());
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
                        JOptionPane.showMessageDialog(null, "Skipping invalid line due to missing customer or employee: " + line);
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

    //APPOINTMENT WRITE
    public static void writeAppFile(LinkedList<Appointment> appointmentLL, String fileName) {
        if (appointmentLL == null || appointmentLL.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Appointment list is empty. Nothing to write.");
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
            JOptionPane.showMessageDialog(null,"Data saved successfully to " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error writing to file: " + e.getMessage());
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
                        JOptionPane.showMessageDialog(null,"Skipping invalid line due to missing customer, employee, or product: " + line);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Skipping invalid line: " + line);
                }
            }
            JOptionPane.showMessageDialog(null,"Data loaded successfully from " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error reading file: " + e.getMessage());
        }
    }

    //SALE WRITE
    public static void writeSaleFile(LinkedList<Sale> saleLL, String fileName) {
        if (saleLL == null || saleLL.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Sale list is empty. Nothing to write.");
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
            JOptionPane.showMessageDialog(null, "Data saved successfully to " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error writing to file: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        new LLMainGUI();
    }
}