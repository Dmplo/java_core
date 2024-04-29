import enums.Type;
import exceptions.OwnerNotFoundException;
import service.AccountService;

public class Main {


    public static void main(String[] args) {
        AccountService.add(50, "John", Type.DEBIT2);
        AccountService.add(150, "John", Type.CREDIT);

        AccountService.add(100, "Will", Type.DEBIT);
        AccountService.add(50, "Will", Type.CREDIT);

        AccountService.add(-100, "Jane", Type.DEBIT);
        AccountService.add(0, "Jane", Type.CREDIT);


        transfer("John", 50, Type.DEBIT, Type.CREDIT);
        transfer("Will", 50, Type.DEBIT, Type.CREDIT);
        transfer("Will", 150, Type.DEBIT, Type.CREDIT);
        transfer("Will", -500, Type.CREDIT, Type.DEBIT);

    }


    static void transfer(String owner, int sum, Type from, Type to) {
        try {
            AccountService.transferMoney(owner, sum, from, to);
        } catch (IllegalArgumentException | OwnerNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

}
