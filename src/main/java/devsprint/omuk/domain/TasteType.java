package devsprint.omuk.domain;

public enum TasteType {
    SPICY("매운맛"),
    SWEET("단맛"),
    SALTY("짠맛"),
    SOUR("신맛"),
    SAVORY("고소한맛"),
    BITTER("쓴맛"),
    UMAMI("감칠맛");

    private final String displayName;

    TasteType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
