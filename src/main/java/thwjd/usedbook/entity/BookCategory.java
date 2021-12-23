package thwjd.usedbook.entity;


public enum BookCategory{
    NOVEL("소설"),
    HUMANITIES("인문"),
    CARTOON("만화");

    private final String name;
    private BookCategory(String name) {
        this.name = name;
    }
    public String getName() { return name; }
}
