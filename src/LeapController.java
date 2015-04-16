import java.io.IOException;
import com.leapmotion.leap.*;

class LeapListener extends Listener{
	
	public void onInit(Controller controller){
		System.out.println("Inicializou");
	}
	
	public void onConnect(Controller controller) {
	    System.out.println("Conectado");
	    
	    controller.enableGesture(Gesture.Type.TYPE_SWIPE);
	    //controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
	    controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
	    //controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);

	    controller.config().setFloat("Gesture.Swipe.MinLength", 175.0f);
	    controller.config().setFloat("Gesture.Swipe.MinVelocity",550f);
	  //  controller.config().save();*/
	    
	    controller.config().setFloat("Gesture.Circle.MinRadius", 50.0f);
	    controller.config().setFloat("Gesture.Circle.MinArc", 5f);
	   // controller.config().save();
	   
	    controller.config().setFloat("Gesture.ScreenTap.MinForwardVelocity", 25.0f);
	    controller.config().setFloat("Gesture.ScreenTap.HistorySeconds", 1f);
	    controller.config().setFloat("Gesture.ScreenTap.MinDistance", 30.0f);
	    controller.config().save();
	       
	}
	
	public void onDisconnect(Controller controller){
		System.out.println("Desconectado");
	}
	
	public void onExit(Controller controller){
		System.out.println("Fechado");
	}
	
	public void onFrame(Controller controller) {
			   
	    Frame frame = controller.frame();
	    
	   Vector swipeDirection =  Vector.xAxis();
	   Vector rightVector = swipeDirection;
	   
	       
	    for(Gesture gesture : frame.gestures())
		{
	    	if ((gesture.type().toString() == "TYPE_SWIPE")/* && (swipeDirection.getX() == 1)*/){		
	    		System.out.println("Proximo Manequim"+" : "+ rightVector);  
	    		
	    		

	    	}
	    	
	    	if (gesture.type().toString() == "TYPE_CIRCLE"){	
	    		
	    		System.out.println("Gira Manequim"); 

	    	}	    	
	    	
	    	if (gesture.type().toString() == "TYPE_SCREEN_TAP"){		
	    		System.out.println("Seleciona Manequim");    	
	    		
	    		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);	    		
	    		
	    		System.out.println("Circle Ativo");
	    	}		    
		}		
	}
		
}


public class LeapController {
	
	public static void main(String[] args) {

		LeapListener listener = new LeapListener();
		Controller controller = new Controller();	
		
		controller.addListener(listener);		
		System.out.println("Pressione para Sair");		
	
		try{
			System.in.read();		
		}catch(IOException e){
			e.printStackTrace();		
		}			
		controller.removeListener(listener);				
	}

}
