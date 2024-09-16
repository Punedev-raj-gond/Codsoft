import java.util.Scanner;
public class AtmInterface
{
    private double balance;
    private Scanner userInput;

    public AtmInterface()
    {
        balance = 30000;
        userInput = new Scanner(System.in);
    }


    public void userDispaly()
    {
        // to show the multiple entry options to the user
        System.out.println("ATM Menu");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");


        System.out.println();
    }


    public void checkBalance()
    {
       System.out.println("Your current balance is "+ balance);
        System.out.println();
    }

    public void depositBalance()
    {
       System.out.println("Enter the amount to deposit :");
       double amount = userInput.nextDouble();
       if(amount>0)
       {
           balance= balance+amount;
           System.out.println("Rupees "+amount + " has been deposited");
           System.out.println("your current balance is "+ balance );
       }
       else{
           System.out.println("Please enter the right amount ");
       }
        System.out.println();
    }

    public void withdrawBalance()
    {
      System.out.println("Enter the amount to withdraw");
      double amount=userInput.nextDouble();
      if(amount>0 && amount<=balance)
      {
          balance=balance-amount;
          System.out.println("Rupees " + amount + " has been withdrawn");
          System.out.println("Your current balance is "+balance);

      }
      else if(balance<amount)
      {
       System.out.println("Insufficent balance , you have "+ balance);
      }
      else
      {
          System.out.println("Invalid amount , Please enter the right amount");
      }
        System.out.println();
    }
    public void thankYou()
    {
        System.out.println("Thank you for using atm service..");
        System.out.println();
    }


     public static void main(String[] args)
     {
       AtmInterface atm = new AtmInterface();

       while(true)
       {
           atm.userDispaly();
           System.out.println("Please Select an option..");
           System.out.println();
           int choice =atm.userInput.nextInt();

           switch (choice)
           {
               case 1 :
                   atm.checkBalance();
                   break;

               case 2 :
                   atm.depositBalance();
                   break;

               case 3 :
                   atm.withdrawBalance();
                   break;

               case 4 :
                   atm.thankYou();
                   break;

               default:
                   System.out.println("Invalid Input ! Please select an appropriate option for the transaction");

           }
       }
     }
}
