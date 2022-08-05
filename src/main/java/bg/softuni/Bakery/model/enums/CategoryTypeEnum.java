package bg.softuni.Bakery.model.enums;

public enum CategoryTypeEnum {
    BREAD("Хляб"),
    CAKE("Торти"),
    SWEETS("Сладки изкушения"),
    PICKLES("Солени изкушения"),
    GLUTEN_FREE("Без глутен"),
    HEALTHY_VEGAN("Здравословно/Веган"),
    LAST("Постно");

    private String displayName;

    CategoryTypeEnum(String displayName){
        this.displayName =displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
