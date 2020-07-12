package dao;

public class ItemDAO {
    public String icon;
    public String icon_large;
    public String id;
    public String type;
    public String typeIcon;
    public String name;
    public String description;
    public String members;
    public current current = new current();
    public today today = new today();
    public day30 day30 = new day30();
    public day90 day90 = new day90();
    public day180 day180 = new day180();

    public static class current {
        public String trend;
        public String price;
    }

    public static class today {
        public String trend;
        public String price;
    }

    public static class day30 {
        public String trend;
        public String change;
    }

    public static class day90 {
        public String trend;
        public String change;
    }

    public static class day180 {
        public String trend;
        public String change;
    }



}
