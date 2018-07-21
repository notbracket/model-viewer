package Shaders;

import org.lwjgl.util.vector.Matrix4f;

import Entities.Camera;
import Entities.Light;
import Toolbox.Maths;

public class StaticShader extends ShaderProgram
{
	
	private static final String VERTEX_FILE = "C:\\Users\\Louis\\eclipse-workspace\\model-viewer\\src\\Shaders\\VertexShader.txt";
	private static final String FRAGMENT_FILE = "C:\\Users\\Louis\\eclipse-workspace\\model-viewer\\src\\Shaders\\FragmentShader.txt";
	
	private int location_transformationmatrix;
	private int location_projectionmatrix;
	private int location_viewmatrix;
	
	private int location_lightpos;
	private int location_lightcol;
	
	private int location_shinedamper;
	private int location_reflectivity;
	
	
	public StaticShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
	
	
	@Override
	protected void bindAttribs() {
		// nuttin
		super.bindAttrib(0, "position");
		super.bindAttrib(1, "texcoords");
		super.bindAttrib(2, "normal");
	}
	
	@Override
	protected void getAllUniformLocations() {
		location_transformationmatrix = super.getUniformLocations("transformationmatrix");
		location_projectionmatrix = super.getUniformLocations("projectionmatrix");
		location_viewmatrix = super.getUniformLocations("viewmatrix");
		location_lightpos = super.getUniformLocations("lightpos");
		location_shinedamper = super.getUniformLocations("shinedamper");
		location_reflectivity = super.getUniformLocations("reflectivity");
	}
	
	public void loadShineVars(float damper, float reflectivity) {
		super.loadFloat(location_shinedamper, damper);
		super.loadFloat(location_reflectivity, reflectivity);
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationmatrix, matrix);
	}
	
	public void loadLight(Light light) {
		super.loadVector(location_lightpos, light.getPosition());
		super.loadVector(location_lightcol, light.getColour());
	}
	
	public void loadViewMatrix(Camera camera) {
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewmatrix, viewMatrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix) {
		super.loadMatrix(location_projectionmatrix, matrix);
	}
	
}
