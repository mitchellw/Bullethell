import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class MakeAtlas {
    public static String assetDir = "Bullethell/Bullethell/assets/sprites";
    public static String outputDir = "Bullethell/Bullethell-android/assets";

    public static void main(String[] args) {
    	// TODO: support different densities
    	TexturePacker2 .process(assetDir + "/", outputDir + "/", "sprites");
    }
}
