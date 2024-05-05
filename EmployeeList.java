package Project;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeList {

    static Scanner inp = new Scanner(System.in);

    static ArrayList<Employee> emp = new ArrayList<>();
    static String posCode, depCode, empName, empPos;
    static double hoursWorked, regPay, otPay, netPay;

    static double hourlyRate, taxRate;
    static double regHours = 176;
    static char subCode;
    static String ans ="";

    public static void main(String[] args){

        do{
            menu();
            System.out.print("Back to Main Menu? ");
            ans = inp.nextLine();
        }while(ans.equalsIgnoreCase("YES"));

    }//main
    public static void menu(){
        System.out.println("------------------------------------------------------------------");
        System.out.println("••••••••••••••••• EMPLOYEE PAYROLL SYSTEM •••••••••••••••••••");
        System.out.println("------------------------------------------------------------------");
        System.out.println("******************************************************************");
        System.out.println("           Enter Choice from the Following Options:");
        System.out.println("******************************************************************");
        System.out.println("------------------------------------------------------------------");
        System.out.println("\t [1] - Add Employee Record");
        System.out.println("\t [2] - Display Employee Record");
        System.out.println("------------------------------------------------------------------");

        System.out.print("Enter Choice: ");
        int choice = inp.nextInt();
        inp.nextLine();

        switch(choice){
            case 1:{
                do{
                    System.out.println("------------------------------------------------------------------");
                    addRecord();
                    inp.nextLine();
                    System.out.print("Add another employee? ");
                    ans = inp.nextLine();
                }while(ans.equalsIgnoreCase("YES"));
                break;
            }
            case 2:{
                display();
                break;
                //display the records plus mag c-create na ng file dito
            }
            default:{
                break;
            }
        }

    }//menu

    public static void addRecord(){
        //int j = posCode.length();
        //posCode1 = parseInt(posCode.substring(0,j-1));

        System.out.print("Enter Position Code (000X): ");
        posCode = inp.nextLine();
        subCode = posCode.charAt(3);
        //System.out.println(subCode);
        //inp.nextLine();

        System.out.print("Employee Name: ");
        empName = inp.nextLine();

        System.out.print("Hours Worked: ");
        hoursWorked = inp.nextDouble();

        if(posCode.equals("011A") || posCode.equals("011B")){
            if(subCode == 'A'){
                hourlyRate = 170.00;
                empPos = "Senior Programmer";
            }else{
                hourlyRate = 160.00;
                empPos = "Junior Programmer";
            }
            depCode = "MIS";//
            computeSalary(hoursWorked, hourlyRate);
       }//else if(another department)
        emp.add(new Employee(posCode,depCode,empName,empPos,hoursWorked,regPay,otPay,netPay));
        System.out.println("------------------------------------------------------------------");

    }//addRecord

    public static double computeSalary(double hoursWorked, double hourlyRate){

        if(hoursWorked < regHours){
            regPay = hoursWorked * hourlyRate;
            otPay = 0;
            //sample w/dependent muna
            taxRate = 0.05 * regPay;
            netPay = regPay - taxRate;
        }//else if(may overtime)
        return netPay;
    }//computeSalary

    public static void display(){
        int x;
        if(emp.isEmpty()){
            System.out.println("No Employee Record Found...");
            System.out.println("------------------------------------------------------------------");
        }
        else{
            System.out.println("------------------------------------------------------------------");
            for(x =0; x<emp.size(); x++){
                System.out.println("Employee Position Code: " + emp.get(x).posCode);
                System.out.println("Department: " + emp.get(x).depCode);
                System.out.println("Name: " + emp.get(x).empName);
                System.out.println("Name: " + emp.get(x).empPos);
                System.out.println("Hours Worked: " + emp.get(x).hoursWorked);
                System.out.println("Regular Pay: " + emp.get(x).regPay);
                System.out.println("Overtime Pay: " + emp.get(x).otPay);
                System.out.println("Net Pay: " + emp.get(x).netPay);
                System.out.println ("------------------------------------------------------------");
            }

            try{
                FileWriter writer = new FileWriter("employeeList.txt");
                //C:Users//
                writer.write("Employee Payroll Record");
                for(x=0; x<emp.size(); x++){
                    writer.write("\n\nEmployee Position Code: " + emp.get(x).posCode);
                    writer.write("\nDepartment: " + emp.get(x).depCode);
                    writer.write("\nName: " + emp.get(x).empName);
                    writer.write("\nName: " + emp.get(x).empPos);
                    writer.write("\nHours Worked: " + emp.get(x).hoursWorked);
                    writer.write("\nRegular Pay: " + emp.get(x).regPay);
                    writer.write("\nOvertime Pay: " + emp.get(x).otPay);
                    writer.write("\nNet Pay: " + emp.get(x).netPay);
                    System.out.println ("------------------------------------------------------------");
                }
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }//diplay

}

class Employee{

    String posCode,empName, depCode, empPos;
    //int posCode;
    double hoursWorked, regPay, otPay, netPay;
    //deduction;

    Employee(String posCode, String depCode, String empName, String empPos, double hoursWorked, double regPay, double otPay, double netPay){
        this.posCode = posCode;
        this.depCode = depCode;
        this.empName = empName;
        this.empPos = empPos;
        this.hoursWorked = hoursWorked;
        this.regPay = regPay;
        this.otPay = otPay;
        this.netPay = netPay;
        //this.deduction = deduction;
    }

}
