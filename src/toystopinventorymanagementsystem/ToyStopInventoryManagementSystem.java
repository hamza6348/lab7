package toystopinventorymanagementsystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.*;

/**
 *
 * @author Fahad Satti
 */
public class ToyStopInventoryManagementSystem {
    ToyStopService tsService = new ToyStopService();
    public void init(){
        
        tsService.initEmployees();
        tsService.initStores();
        tsService.initToys();
        
        System.out.println("Init complete");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        ToyStopInventoryManagementSystem tsims = new ToyStopInventoryManagementSystem();
        tsims.init();
        
        //load previous data
        //tsims.loadData();
        
        //tsims.showMenu();
        //
        
        //tsims.printAll();
        for(int i=1;i<=60;i++){
           
        tsims.showMenu(i);
        }
        
        
        
        
    }

    private void loadData() {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tsService= null;
            try {
            try (FileInputStream fileIn = new FileInputStream("/tmp/employee.ser"); ObjectInputStream in = new ObjectInputStream(fileIn)) {
                tsService= ( ToyStopService) in.readObject();
            }
      }catch(IOException i) {
      }catch(ClassNotFoundException c) {
         System.out.println("Employee class not found");
      }
    }

private void showMenu(int i) {// this function was edited to run in the main function
        int a = -1;// option creation
        int daycounter=1;
        //loadData();
        while (a!=5){
        System.out.println("Welcome to Toy Stop Inventory Management System "+"at day "+i);
        System.out.println("Enter 1 to show all data");
        System.out.println("Enter 2 to add a new Store");
        System.out.println("Enter 3 to add a new Employee");
        System.out.println("Enter 4 to add a new Toy");
        System.out.println("Enter 0 to save state");
        Scanner in = new Scanner (System.in);
        a = in.nextInt();
        //System.out.println(a);
        if (a==1)
        {
            printAll();//use built in print function
        }
        else if (a==2)
        {
            tsService.addStore();//add store function from services file
            System.out.println("store added!");//acknowledgement that the adding successful
        }
        
        else if (a==3)
        {
           int e_id=tsService.addEmployee();//built in function from services file
            System.out.println("Employee added!");//acknowledgement that the adding successful
        }
        else if (a==4)
        {
            addnewtoy();//call new toy function (below)
            System.out.println("Toy added!");//acknowledgement that the adding successful
        }
        else if (a==0)
        {
        savedata();
        System.out.println("State saved");
        daycounter=daycounter+1;
        break;
        
        
      
        
        }
   }
}
        
private void addnewtoy(){
           
            Toy newToy = new Toy();
            newToy.setUID(Util.getSaltNum(-1));
            newToy.setMinAge(Util.getSaltNum(1));
            newToy.setMaxAge(Util.getSaltNum(18));
            newToy.setPrice(Util.getSaltNum(1000));
            newToy.setName(Util.getSaltAlphaString());
            newToy.setAddedOn(LocalDateTime.now());
            
            Random randStore = new Random();
            int index = randStore.nextInt(tsService.stores.size());
            Store selectedStore = (Store)tsService.stores.get(index);
            selectedStore.addToy(newToy);
            
           
       }


private void savedata(){
ToyStopService tsServices = new ToyStopService();
      
      
      try {
    try (FileOutputStream fileOut = new FileOutputStream("/tmp/employee.ser")) {
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(tsService);
        out.close();
    }
         System.out.printf("Serialized data is saved in /tmp/employee.ser");
      }catch(IOException i) {
      }
   }



private void printAll(){


        System.out.println(this.tsService.employees);
    }
    
    
    
    
}
