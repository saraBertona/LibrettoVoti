package it.polito.tdp.librettovoti;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import it.polito.tdp.librettovoti.model.Libretto;
import it.polito.tdp.librettovoti.model.Voto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	//modello
	private Libretto model; //non new Libretto, ho già lavorato in entryPoint

    @FXML
    private Label txtStatus;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> cmbPunti; //SOLO VOTO, che è integer, non Voto

    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtVoti;

    @FXML
    void handleNuovoVoto(ActionEvent event) {
    	
    	//acquisisco e controllo dati
    	String nome= txtNome.getText();
    	Integer punti= cmbPunti.getValue(); //MENU A TENDINA SELEZIONATO
    	
    	//controlli di validità
    	if(nome.equals("")||punti==null) {
    		//errore, non faccio operazione e lo dico all'utente
    		txtStatus.setText("ERRORE: inserisci nome e voto\n");
    		return;//ESCO SENZA FARE NULLA
    	}
    	
    	//faccio operazioni (=chiedere al model di farla)
    	boolean ok=model.add(new Voto(nome,punti));
    	
    	//visualizzo e aggiorno il risultato
    	//String contenuto= model.toString();
    	//txtVoti.setText(contenuto);
    	if(ok) {
    	List<Voto> voti= model.getVoti();
    	txtVoti.clear();
    	txtVoti.appendText("Hai superato "+voti.size()+" esami\n");
    	for(Voto v:voti) {
    		txtVoti.appendText(v.toString()+"\n");
    	}
    	txtNome.clear();
    	cmbPunti.setValue(null);
    	txtStatus.setText("");
    	}
    	else {
    		txtStatus.setText("ERRORE: esame già presente");
    	}
    }
    
    //MODEL
    public void setModel (Libretto model) {
    	this.model=model;
    	
    	List<Voto> voti= model.getVoti();
       	txtVoti.clear();
       	txtVoti.appendText("Hai superato "+voti.size()+" esami\n");
       	for(Voto v:voti) {
       		txtVoti.appendText(v.toString()+"\n");
       	}
    }
    @FXML
    void initialize() {
        assert cmbPunti != null : "fx:id=\"cmbPunti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtVoti != null : "fx:id=\"txtVoti\" was not injected: check your FXML file 'Scene.fxml'.";
        
        
        //MENU A TENDINA
        cmbPunti.getItems().clear();
        for (int i=18; i<=30; i++) {
        	cmbPunti.getItems().add(i);
        }
        //non ho ancora creato la classe model qua, quindi devo agire dopo
     
    }



	

	

}
