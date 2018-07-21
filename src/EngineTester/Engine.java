package EngineTester;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import Entities.Camera;
import Entities.Object;
import Entities.Light;
import Models.RawModel;
import Models.TexturedModel;
import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.MasterRenderer;
import RenderEngine.OBJLoader;
import Textures.ModelTexture;


public class Engine {
	
	public static void main(String[] args) {
		
		int windowWidth = 1280;
		int windowHeight = 720;
		
		DisplayManager.createDisplay(windowWidth, windowHeight,16);
		
		Loader loader = new Loader();
		MasterRenderer renderer = new MasterRenderer();
		
		RawModel model = OBJLoader.loadObjModel("dragon", loader);
		
		ModelTexture texture = new ModelTexture(loader.loadTexture("plaintex"));
		texture.setShineDamper(25);
		texture.setReflectivity(1);
				
		TexturedModel cubeModel = new TexturedModel(model, texture);
		Object cubeEnt = new Object(cubeModel, new Vector3f(0,-4f,-15), 0,0,0,1);
		
		Light light = new Light(new Vector3f(0,5,0), new Vector3f(1,1,1));
		
		Camera camera = new Camera();
		
		// main loop of game logic + rendering
		while(!Display.isCloseRequested())
		{			
			camera.move();
			
			// rotate model using arrow keys
			if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
				cubeEnt.increaseRotation(0,-1, 0);		
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
				cubeEnt.increaseRotation(0, 1, 0);		
			}

			// process entity for rendering
			renderer.processEntity(cubeEnt);
			
			// RENDER THIS BIATCH
			renderer.render(light,  camera);
			
			// update display
			DisplayManager.updateDisplay();
		}
		
		// clean up loader/shader and close
		renderer.cleanUp();
		loader.cleanUp();
		
		DisplayManager.closeDisplay();

	}
	
}
