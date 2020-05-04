package Utils;

import service.PriceService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static boolean isNoPriceForToday(PriceService priceService) {
        //is today
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if(priceService.findLatestPricedDate(priceService.findPrices()) == null) {
            return true;
        }
        System.out.println(sdf.format(priceService.findLatestPricedDate(priceService.findPrices())));
        sdf.format(new Date());
        return !sdf.format(priceService.findLatestPricedDate(priceService.findPrices())).equals(sdf.format(new Date()));
    }

}
