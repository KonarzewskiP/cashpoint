package bankomat;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        DateUserImporter dateUserImporter = new DateUserImporter("src/main/resources/static/accounts.csv");
//        BankUsers bankUsers = new BankUsers(dateUserImporter.readUser());

        while (true) {
            try {
                User user = Login.login();
                new ATM(user).run();
            } catch (IllegalArgumentException e) {
                System.out.println("problem");
            }
        }
    }
}

//    skitchen0,RPTHvOhprmkb,DK4666476382688080
//            dbonnett1,yYtXWsJN,EE920481338099138901
//    emayho2,ovZUgni,AD0977037305U8LZZDHCWRVI
//            dbendle3,j0wUcumEt0,FR0569304442285XRFKYDPUDF95
//    bpresnall4,5wkm92,MU84VBJN7375279443420830314WDK

//    login skitchen0 RPTHvOhprmkb
//    login dbonnett1 yYtXWsJN