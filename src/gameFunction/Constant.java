package gameFunction;

public interface Constant {
    public static final int originalTileSize = 16; // khung kinh ban dau 16 x 16
    public static final int scale = 3;  //
    public static final int titleSize = originalTileSize * scale; // ficture 48 x 48.
    public static final int maxScreenColum = 16;  // So khung hinh hien thi toi da tren cot
    public static final int maxScreenRow = 12;    // So khung hinh hien thi toi da tren hang
    public static final int screenWidth = maxScreenColum * titleSize;    // Chieu rong cua man hinh hien thi toi da
    public final static int screenHeight = maxScreenRow * titleSize;  // Chieu dai cua man hien thi toi da
}
