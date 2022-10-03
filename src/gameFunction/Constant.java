package gameFunction;

public interface Constant {
    public static final int originalTileSize = 16; // khung kinh ban dau 16 x 16
    public static final int scale = 3;  // ti le phong dai hinnh
    public static final int titleSize = originalTileSize * scale; // ficture 48 x 48.
    public static final int maxScreenColum = 31;  // So khung hinh hien thi toi da tren cot
    public static final int maxScreenRow = 13;    // So khung hinh hien thi toi da tren hang
    public static final int screenWidth = maxScreenColum * titleSize;    // Chieu rong cua man hinh hien thi toi da
    public final static int screenHeight = maxScreenRow * titleSize;  // Chieu dai cua man hien thi toi da

    public static final int MAX_WORLD_MAP = 1600;
}
