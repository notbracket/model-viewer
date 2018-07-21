package RenderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	
	private static final int targetFPS = 120;

	public static void createDisplay(int width, int height, int antialiasSamples) {
		
		ContextAttribs attribs = new ContextAttribs(3,2)
				.withForwardCompatible(true)
				.withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create(new PixelFormat().withSamples(8).withDepthBits(24), attribs);
			Display.setTitle("First Display");
			GL11.glEnable(GL13.GL_MULTISAMPLE);
						
		} 
		catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		GL11.glViewport(0,  0, width, height);
		
	}
	
	
	public static void updateDisplay() {
		
		if(Display.wasResized()) {
			int width = Display.getWidth();
			int height = Display.getHeight();
			GL11.glScissor(0,0, width, height);
			GL11.glViewport(0, 0,  width,  height);
		}
		
		Display.sync(targetFPS); // sync engine to target fps
		Display.update();
		
	}
	
	public static void closeDisplay() {
		
		Display.destroy();
		
	}
}