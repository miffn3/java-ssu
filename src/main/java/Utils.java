import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

    public static int readNum() {
        int k = 0;
        String tmp = "";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            tmp = reader.readLine();
        }
        catch (IOException io) {
            io.printStackTrace();
        }

        if (StringUtils.isNumeric(tmp)) {
            k = Integer.parseInt(tmp);
        }

        while (k == 0) {
            System.out.println("Enter number");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                tmp = reader.readLine();

                if (StringUtils.isNumeric(tmp)) {
                    k = Integer.parseInt(tmp);
                }
            }
            catch (IOException io) {
                io.printStackTrace();
            }
        }

        return k;
    }

    private static int readNumInRange(int a, int b) {
        int k = 0;
        String tmp = "";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            tmp = reader.readLine();
        }
        catch (IOException io) {
            io.printStackTrace();
        }

        if (StringUtils.isNumeric(tmp)) {
            k = Integer.parseInt(tmp);
        }

        while (k < a || k > b) {
            System.out.println("Error! Enter number from " + a + " to " + b + ".");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                tmp = reader.readLine();

                if (StringUtils.isNumeric(tmp)) {
                    k = Integer.parseInt(tmp);
                }
            }
            catch (IOException io) {
                io.printStackTrace();
            }
        }

        return k;
    }

    public static int readNumInRangeMonths() {
        return readNumInRange(1,12);
    }

    public static int readNumInRangeNum() {
        return readNumInRange(0,9);
    }

    public static double readNumDouble() {
        String tmp = "";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            tmp = reader.readLine();
        }
        catch (IOException io) {
            io.printStackTrace();
        }

        return Double.parseDouble(tmp);
    }
}
