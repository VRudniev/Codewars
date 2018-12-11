package kyu6.buying_a_car;

public class BuyCar {

	public static int[] nbMonths(int startPriceOld, int startPriceNew, int savingperMonth, double percentLossByMonth) {
		if (startPriceOld >= startPriceNew)
			return new int[]{0, startPriceOld - startPriceNew};
		else {
			int months = 0;
			double decreasedStartPriceNew = startPriceNew;
			double decreasedStartPriceOld = startPriceOld;
			while (decreasedStartPriceNew > (decreasedStartPriceOld + savingperMonth * months))
			{
				months++;
				if(months % 2 == 0)
					percentLossByMonth += 0.5d;
				decreasedStartPriceOld -= decreasedStartPriceOld * (percentLossByMonth / 100d);
				decreasedStartPriceNew -= decreasedStartPriceNew * (percentLossByMonth / 100d);
			}
			return new int[]{months, (int) Math.round(savingperMonth * months + decreasedStartPriceOld - decreasedStartPriceNew)};
		}
	}
}