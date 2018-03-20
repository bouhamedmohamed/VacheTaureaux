package com.mohamed.rest.vt.springboot2restservicevachetaureaux.model;

public enum Stat {
    WAIT,
    FULL,
    END;

    private boolean end;

    public static Stat nextStat(Stat stat) {
        Stat nextStat = END;
        if (!stat.equals(END)) {
            int nextPosition = stat.ordinal() + 1;
            nextStat = values()[nextPosition];
        }
        return nextStat;
    }

    public boolean inGame(){
        return (this.equals(FULL));
    }

    public boolean isWait() {
        return this.equals(WAIT);
    }

    public boolean isEnd() {
        return this.equals(END);
    }
}
