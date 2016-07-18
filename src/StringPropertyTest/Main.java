package StringPropertyTest;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * This program is meant to provide a very basic example of how to update a Label using Timeline().
 * One method uses a SimpleStringProperty() to store the number as a String.
 * The other method uses SimpleIntegerProperty() to store the number as an Integer.
 *
 * The SimpleStringProperty() method is more complex in this example, as it requires the use of Integer.parseInt(), which must catch NFEs.
 * However, the SimpleStringProperty() method is well suited for showing updating strings.
 * The SimpleIntegerProperty() method is much better suited for numbers, as it has much less overhead and lower risk for errors.
 */
public class Main extends Application
{
	// Create a StringProperty that will be bound to a Label.
	private StringProperty testSP = new SimpleStringProperty( "0" );
	private IntegerProperty testIP = new SimpleIntegerProperty( 0 );


	@Override public void start( Stage primaryStage ) throws Exception
	{
		// Create a GridPane to hold our JavaFX elements.
		GridPane root = new GridPane();
		// Set the titlebar text.
		primaryStage.setTitle( "StringProperty Test" );
		primaryStage.setScene( new Scene( root, 300, 275 ) );

		// Create a Label to show what this line holds.
		Label stringLabelDescriptor = new Label( "SimpleStringProperty: " );
		root.add( stringLabelDescriptor, 0, 0 );

		// Create a Label to display our String value.
		Label stringLabel = new Label( "Hi" );
		// Add our Label to the GridPane.
		root.add( stringLabel, 1, 0 );

		// Create a Label to show what this line holds.
		Label integerLabelDescriptor = new Label( "SimpleIntegerProperty: " );
		root.add( integerLabelDescriptor, 0, 1 );

		// Create a Label to display our Integer value.
		Label integerLabel = new Label( testIP.getValue().toString() );
		root.add( integerLabel, 1, 1 );

		// Create a button to start the Timeline.
		Button startButton = new Button( "Start" );
		// Add our Button to the GridPane.
		root.add( startButton, 0, 2 );

		// Bind our Labels to our Properties.
		stringLabel.textProperty().bind( testSP );
		integerLabel.textProperty().bind( Bindings.convert( testIP ) );

		// Create a Timeline object that calls UpdateProperties once per second.
		Timeline ajhTL = new Timeline( new KeyFrame( Duration.millis( 1000 ), event -> UpdateProperties() ) );
		// Create a Timeline object that calls UpdateProperties ten times per second.
		//Timeline ajhTL = new Timeline( new KeyFrame( Duration.millis( 100 ), event -> UpdateProperties() ) );

		// Set the number of times the Timeline will update.
		ajhTL.setCycleCount( 120 );
		//ajhTL.setCycleCount( Timeline.INDEFINITE );

		// Start the Timeline when the button is pressed.
		startButton.setOnAction( event -> ajhTL.play() );

		// Display our stage.
		primaryStage.show();
	}


	public static void main( String[] args )
	{
		launch( args );
	}


	/**
	 * This will increment our IntegerProperty
	 * It will also read the value in our StringProperty, and update it with a new value.
	 */
	private void UpdateProperties()
	{
		// Increment our testIP value.
		testIP.setValue( testIP.getValue() + 1 );
		// Integer.parseInt requires catching NFEs.
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
