package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

public class SampleController {

	@FXML private Pane pane;
	

    @FXML private CheckBox cbCreateLine;
    @FXML private CheckBox cbCreateState;
	
	private ArrayList<TextArea> nodes;
	
	/**
	 * Class Constructor
	 */
	public SampleController() {
		setNodes(new ArrayList<TextArea>());
	}
	
	
	/*
	 * defautl method that initializes every GUI conponent.
	 */
	public void initialize() {
		
		initialQuestions();
		
		cbCreateState.setSelected(true);
		
		cbCreateState.setOnAction(e->{validateCBS(1);});
		cbCreateLine.setOnAction(e->{validateCBS(-1);});
		
		pane.setOnMouseClicked(e->{
			if(e.getButton().equals(MouseButton.PRIMARY)){
	            if(e.getClickCount() == 2){
	            	createNewState(e.getX(), e.getY());
	            }
	        }
		});
	}
	
	/**
	 * This method makes the user decide wheter is going to create
	 * a Moore machne or a Mealey machine.
	 */
	public void initialQuestions(){
		List<String> choices = new ArrayList<>();
		choices.add("Moore");
		choices.add("Mealey");
		
		ChoiceDialog<String> dialog = new ChoiceDialog<>("-", choices);
		dialog.setTitle("Machine selection");
		dialog.setHeaderText(null);
		dialog.setContentText("Select the type of your machine");
		
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			if(result.toString().equals("Moore")) {
				System.out.println("Moore");
			}else {
				System.out.println("Mealey");
			}
		}
	}
	
	/**
	 * This method validates witch of the two
	 * checkbox where clicked, in case that is the
	 * one that draws lines, it validates if there are
	 * enought states to actually create a single line
	 * if not it shows a warning explaining the situation.
	 * @param i, if i>0 the CreateState checkbox was clicked, otherwise
	 * it means that the one that was clicked was CreateLine one.
	 */
	public void validateCBS(int i) {
		if(i>0) {
			cbCreateLine.setSelected(false);
			cbCreateState.setSelected(true);
		}else {
			if(nodes.size()>1) {
				cbCreateState.setSelected(false);
				cbCreateLine.setSelected(true);
			}else {
				cbCreateLine.setSelected(false);
				cbCreateState.setSelected(true);
				JOptionPane.showMessageDialog(null, "No puedes crear lineas aun\npues no hay suficientes estados", null, 0);
			}
		}
		
	}
	
	/**
	 * This method creates a new state object over the pane
	 * @param x, the x cordinate where the double click had place.
	 * @param y, the y cordinate where the double click had place.
	 */
	public void createNewState(double x, double y) {
		TextArea node=new TextArea();
		node.setStyle("-fx-border-radius: 10 10 10 10;" + 
				"-fx-background-radius: 10 10 10 10;");
		node.setLayoutX(x);
		node.setLayoutY(y);
		node.setPrefSize(52, 52);
//		node.setEditable(false);
	//	node.setOnMouseClicked(e->{editText();});
		nodes.add(node);
		pane.getChildren().add(node);
	}

	public ArrayList<TextArea> getNodes() {
		return nodes;
	}



	public void setNodes(ArrayList<TextArea> nodes) {
		this.nodes = nodes;
	}
	
}
