package utils;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatUtil {

    private static final NumberFormat format =
            NumberFormat.getInstance(new Locale("vi", "VN"));

    public static String money(double money){

        return format.format(money) + " VNĐ";

    }

}