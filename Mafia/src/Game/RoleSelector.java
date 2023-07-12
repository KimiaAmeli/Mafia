package Game;

import Roles.Bulletproof;
import Roles.Psychologist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * this class specifies roles and randomly plays roles
 * @version 2021,3,2
 * @author Ameli
 */
public abstract class RoleSelector {
    private static final List<String> list1 = Arrays.asList("Citizen");
    private static final List<String> list2 = Arrays.asList("Citizen", "Mafia");
    private static final List<String> list3 = Arrays.asList("Mayor", "Psychologist", "GodFather");
    private static final List<String> list5 = Arrays.asList("Mayor", "Sniper", "GodFather", "Doctor", "DoctorLecter");
    private static final List<String> list6 = Arrays.asList("Psychologist", "Sniper", "GodFather", "Detective", "DoctorLecter", "Bulletproof");
    private static final List<String> list9 = Arrays.asList("Detective", "Doctor", "Sniper", "Bulletproof", "Citizen", "Psychologist", "GodFather", "DoctorLecter", "Mafia");
    private static final List<String> list10 = Arrays.asList("Detective", "Doctor", "Sniper", "Bulletproof", "Psychologist", "Citizen", "Citizen", "GodFather", "DoctorLecter", "Mafia");
    private static final List<String> list11 = Arrays.asList("Detective", "Doctor", "Sniper", "Bulletproof", "Psychologist", "Citizen", "Citizen", "Citizen", "GodFather", "DoctorLecter", "Mafia");
    private static final List<String> list12 = Arrays.asList("Detective", "Doctor", "Sniper", "Bulletproof", "Psychologist", "Mayor", "Citizen", "Citizen", "GodFather", "DoctorLecter", "Mafia", "Mafia");

    /**
     * specifies roles and randomly plays roles
     * @param n
     * @return
     */
    public static List<String> getRoles(int n) {
        List<String> list = new ArrayList<>();
        if (n == 1) list = list1;
        else if (n == 2) list = list2;
        else if (n == 3) list = list3;
        else if (n == 5) list = list5;
        else if (n == 6) list = list6;
        else if (n == 9) list = list9;
        else if (n == 10) list = list10;
        else if (n == 11) list = list11;
        else if (n == 12) list = list12;
        Collections.shuffle(list);
        return list;
    }
}
