package RenderEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Entities.Camera;
import Entities.Object;
import Entities.Light;
import Models.TexturedModel;
import Shaders.StaticShader;

public class MasterRenderer {
	
	private StaticShader shader = new StaticShader();
	private Renderer renderer = new Renderer(shader);
	
	private Map<TexturedModel, List<Object>> entities = new HashMap<TexturedModel, List<Object>>();
	
	public void render(Light sun, Camera camera) {
		renderer.prepare3D();
		shader.start();
		shader.loadLight(sun);
		shader.loadViewMatrix(camera);
		
		renderer.render3D(entities);
		
		shader.stop();
		entities.clear();
	}
	
	public void processEntity(Object object) {
		TexturedModel entityModel = object.getModel();
		List<Object> batch = entities.get(entityModel);
		if(batch!=null) {
			batch.add(object);
		} else {
			List<Object> newBatch = new ArrayList<Object>();
			newBatch.add(object);
			entities.put(entityModel,newBatch);
		}
	}
	
	
	public void cleanUp() {
		shader.cleanUp();
	}
	
	

}
