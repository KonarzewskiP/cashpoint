package bankomat;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {

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

//    skitchen0,RPTHvOhprmkb,   DK4666476382688080   £4157.16
//            dbonnett1,yYtXWsJN,   EE920481338099138901   £4621.35
//    emayho2,ovZUgni,  AD0977037305U8LZZDHCWRVI   £3775.40
//            dbendle3,j0wUcumEt0,  FR0569304442285XRFKYDPUDF95   £2309.86
//    bpresnall4,5wkm92,    MU84VBJN7375279443420830314WDK    £4984.75

//    login skitchen0 RPTHvOhprmkb
//    login dbonnett1 yYtXWsJN
//    login emayho2 ovZUgni
//    login dbendle3 j0wUcumEt0
//    login bpresnall4 5wkm92