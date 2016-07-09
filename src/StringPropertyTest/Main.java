package StringPropertyTest;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * This program is meant to provide a very basic example of how to update a Label using Timeline()
 */
public class Main extends Application
{
	// Create a StringProperty that will be bound to a Label.
	private StringProperty testSP = new SimpleStringProperty( "0" );


	@Override public void start( Stage primaryStage ) throws Exception
	{
		// Create a GridPane to hold our JavaFX elements.
		GridPane root = new GridPane();
		// Set the titlebar text.
		primaryStage.setTitle( "StringProperty Test" );
		primaryStage.setScene( new Scene( root, 300, 275 ) );

		// Create a Label to display our value.
		Label testLabel = new Label();
		// Set the Label to a value, so we can see if it changes.
		testLabel.setText( "Hi" );
		// Add our Label to the GridPane.
		root.add( testLabel, 0, 0 );

		// Bind our Label to our StringProperty.
		testLabel.textProperty().bind( testSP );

		// Create a Timeline object that updates as often as the value that KeyFrame Duration is set to.
		Timeline ajhTL = new Timeline( new KeyFrame( Duration.millis( 1000 ), event -> UpdateStringProperty() ) );
		// Set the number of times the Timeline will update.
		// This can be set to Timeline.INDEFINITE if you wish to run forever.~
		ajhTL.setCycleCount( 120 );
		// Start the Timeline.
		ajhTL.play();

		// Display our stage.
		primaryStage.show();
	}


	public static void main( String[] args )
	{
		launch( args );
	}


	/**
	 * This will read the value in our StringProperty, and update it with a new value.
	 */
	private void UpdateStringProperty()
	{
		try
		{
			// Create a temporary Integer, and assign it to the value in our StringProperty.
			Integer tempInt = Integer.parseInt( testSP.getValue() );
			// Increment the value we read.
			tempInt++;
			// Set our StringProperty to the new value.
			testSP.setValue( tempInt.toString() );
		}
		catch( NumberFormatException nfe )
		{
			// If a non-integer is read in above, this will handle the exception.
			nfe.printStackTrace();
		}
	}
}
