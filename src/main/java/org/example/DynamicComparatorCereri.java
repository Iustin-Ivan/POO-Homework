package org.example;

import java.util.Comparator;

public class DynamicComparatorCereri implements Comparator<CerereCompleta> {

    private int compareBy = 0;

    public DynamicComparatorCereri() {
    }

    public int getCompareBy() {
        return compareBy;
    }

    public void setCompareBy(int compareBy) {
        this.compareBy = compareBy;
    }

    public int compare(CerereCompleta a, CerereCompleta b) {
        if (compareBy == 0) {
            return a.getData().compareTo(b.getData());
        }
        int p = b.getPrioritate()-a.getPrioritate();
        if (p == 0) {
            return a.getData().compareTo(b.getData());
        }
        return p;
    }
}
