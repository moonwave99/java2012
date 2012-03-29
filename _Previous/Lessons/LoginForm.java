import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

// Classe Persona goes british - nothing new under the sun.
class User{
	
	protected String username;
	protected String password;
	
	public User(String username, String password){
		this.username = username;
		this.password = password;	
	}
	
	public String getUsername(){return this.username;}
	public String getPassword(){return this.password;}	
	
	public String toString(){return this.username;}
	
	// Occhio, quando confrontate stringhe tra loro ricordate di usare equals()!
	public boolean equals(User other){

		return this.getUsername().equals( other.getUsername() )
			&& this.getPassword().equals( other.getPassword() );
		
	}
	
}

// Il Controller è un entità deputata a ricevere richieste, ad assolverle ed eventualmente a restituire una risposta.
// Notare come esso sia totalmente scorporato dall'interfaccia [in questo caso il LoginForm] - per dirlo in maniera cool, si dice che il codice non è "tightly coupled",
// termine che non va perversamente interpretato alla lettera chairamente, ma che vuole indicare la separazione funzionale tra classi distinte in questo caso.
class LoginController{
	
	protected ArrayList userList;
	
	public LoginController(){
		
		// Inizializzo la lista
		userList = new ArrayList();
		
		// Creo due pupazzi - hint: mai usare la stessa parola per username e passwd!
		userList.add(new User("utente", "utente"));
		userList.add(new User("admin", "admin"));		
		
	}
	
	// Il meccanismo di login è molto rustico - confronto la coppia username-pwd passata, e vedo se questa coincide con qualcuna di quelle inserite nella lista utenti
	public boolean login(String username, String password){
		
		User loginUser = new User(username, password);
		User tempUser = null;
		
		for(int i = 0; i < userList.size(); i++){
			
			tempUser = (User)(userList.get(i));
			
			if(tempUser.equals(loginUser))
				return true;
				
		}
		
		return false;

	}
	
}

// La classe LoginForm è un JFrame che implementa ActionListener in modo da agire quando il pulsante in esso contenuto viene premuto
public class LoginForm extends JFrame implements ActionListener{
	
	// I 5 componenti inseriti nel frame: 2 campi di testo, le 2 etichette associati, il pulsante di invio del form
	protected JTextField username, password;
	protected JLabel usernameLabel, passwordLabel;
	protected JButton submit;
	
	// Riferimento ad un'istanza della classe LoginController, di cui invocheremo il metodo login() alla pressione del pulsante
	protected LoginController controller;

	public LoginForm(LoginController controller){
		
		// Invoco il costruttore della superclasse (JFrame) indicando il titolo della finestra
		super("Please login.");
		
		// Assegno il riferimento al controller
		this.controller = controller;
		
		// La solita storia
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.getContentPane().setLayout(
			
			new GridLayout(5, 1)	// Inserisco 5 elementi in una griglia 5 x 1
		
		);
		
		username = new JTextField();				// Creo il campo di testo
		usernameLabel = new JLabel("Username");		// Creo l'etichetta
		this.getContentPane().add(usernameLabel);	// Li aggiungo al frame [passando per getContentPane() !]
		this.getContentPane().add(username);
		
		password = new JTextField();				// Vedi sopra
		passwordLabel = new JLabel("Password");		
		this.getContentPane().add(passwordLabel);		
		this.getContentPane().add(password);	
		
		submit = new JButton("Login");				// Creo il pulsante
		submit.addActionListener(this);				// Gli dico che esso verrà ascoltato dal frame stesso [che implementa l'interfaccia ActionListener]
		this.getContentPane().add(submit);			// Lo aggiungo al frame
		
		this.pack();								// Faccio bagagli
		
	}
	
	// Il metodo actionPerformed() viene invocato alla pressione del JButton
	public void actionPerformed(ActionEvent e){
		
		// Invoco il metodo login del LoginController
		boolean logged = this.controller.login(
			// Prelevo i valori dei due campi
			username.getText(),
			password.getText()
		);
		
		// Visualizzo un popup con un messaggio di successo o errore
		JOptionPane.showMessageDialog(
			this,
			logged ? "Login effettuato!" : "Username / Password errate."
		);
		
	}

	public static void main(String[] args){

		LoginController lc = new LoginController();	// Creo il controller
		LoginForm lf = new LoginForm(lc);			// Creo il form e gli passo un *riferimento* al controller appena creato, in modo che il frame possa "parlare" con esso
		
		lf.setVisible(true);						// Showtime!
	
	} 

}