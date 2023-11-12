package it.unibo.nestedenum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.lang.String;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    @Override
    public Comparator<String> sortByDays() {
        return null;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return null;
    }

    private enum Month{
        JANUARY("JANUARY", 31),
        FEBRUARY("FEBRUARY", 28),
        MARCH("MARCH", 31),
        APRIL("APRIL", 30),
        MAY("MAY", 31),
        JUNE("JUNE", 30),
        JULY("JULY", 31),
        AUGUST("AUGUST", 31),
        SEPTEMBER("SEPTEMBER", 30),
        OCTOBER("OCTOBER", 31),
        NOVEMBER("NOVEMBER", 30),
        DICEMBER("DICEMBER", 31);

        private final int daysOfTheMonth;
        private final String nameOfTheMonth;

        private Month(final String name, final int days){
            this.daysOfTheMonth = days;
            this.nameOfTheMonth = name;
        }

        public static Month fromString(final String stringedMonth) {
            Month found = null;
            List<Month> occurencyList = new ArrayList<>();
            for(Month month : values()){
                if(month.nameOfTheMonth.equals(stringedMonth.toUpperCase())){
                    found=month;
                }
                else if(month.nameOfTheMonth.startsWith(stringedMonth.toUpperCase())){
                    occurencyList.add(month);
                }
            }
            if(occurencyList.size() > 1){
                throw new IllegalArgumentException("Ambiguous string");
            }
            else if(occurencyList.size() == 1){
                found = occurencyList.get(0);
            }
            if(found == null){
                throw new IllegalArgumentException("The given string is not a month");
            }
            return found;
        }
    }
}
