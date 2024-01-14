import Controllers.BillController;
import Controllers.BookController;
import Controllers.OrderController;
import Controllers.UserController;
import Exceptions.InvalidCredentials;
import Exceptions.OutOfStockException;
import Helpers.*;
import Roles.User;
import Roles.UserInterface;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class Main extends Application {
	public Stage stage;
	public UserInterface currentuser;
	public BillInterface newbill;
	public OrderInterface neworder;

	@Override
	public void start(Stage pstage) {
		stage = pstage;
		Scene scene = createLoginScene();
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

	public Scene createLoginScene() {
		stage.setTitle("User Log In");
		UserController tempController = new UserController(new File("Files/bills.dat"));
		VBox mainpane = new VBox();
		mainpane.setAlignment(Pos.CENTER);
		mainpane.setPadding(new Insets(20, 10, 20, 10));
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(0, 10, 0, 10));
		pane.setVgap(10);
		pane.setHgap(10);

		TextField nameTF = new TextField();
		nameTF.setId("nameTF");
		Label nameLbl = new Label("Name:");

		PasswordField passwordTF = new PasswordField();
		Label passwordLbl = new Label("Password:");

		HBox error = new HBox();
		Label errorLbl = new Label("Invalid Password or Name!");
		errorLbl.setVisible(false);
		errorLbl.setTextFill(Color.color(1, 0, 0));
		error.setAlignment(Pos.CENTER);
		error.setPadding(new Insets(10, 0, 10, 0));
		error.getChildren().add(errorLbl);

		Button loginButton = new Button("Log In");
		HBox bottom = new HBox();
		bottom.setAlignment(Pos.CENTER);
		bottom.setPadding(new Insets(15, 0, 10, 0));
		bottom.getChildren().add(loginButton);

		nameTF.setOnAction(e -> {
			passwordTF.requestFocus();
		});
		passwordTF.setOnAction(e -> {
			loginButton.requestFocus();
		});

		loginButton.setOnAction(e -> {
			try {
				String name = nameTF.getText();
				String password = passwordTF.getText();
				currentuser = tempController.checkUserCredentials(name, password);

				if (currentuser.getAccesslevel()  == 0){
					errorLbl.setText("Access Denied!");
					errorLbl.setVisible(true);
				} else if (currentuser.getRole() == Role.Librarian) {
					switchScene(createLibrarianScene(),"Order Checkout");
				} else if (currentuser.getRole() == Role.Manager) {
					switchScene(createHomeScene(),"BookStore");
				} else if (currentuser.getRole() == Role.Admin) {
					switchScene(createHomeScene(),"BookStore");
				}

			} catch (InvalidCredentials ex) {
				errorLbl.setText("Invalid Password or Name!");
				errorLbl.setVisible(true);
			} catch (Exception ex) {
				System.out.print("Incorrect Format or Empty field.");
			}

		});

		pane.add(nameLbl, 0, 0);
		pane.add(nameTF, 1, 0);
		pane.add(passwordLbl, 0, 1);
		pane.add(passwordTF, 1, 1);
		mainpane.getChildren().add(pane);
		mainpane.getChildren().add(bottom);
		mainpane.getChildren().add(error);

		Scene scene = new Scene(mainpane, 300, 300);
		return scene;
	}

	public MenuBar MenuCreator() {
		Menu homeMenu = new Menu("Home");
		MenuItem home = new MenuItem("Home");
		Menu booksMenu = new Menu("BookList");
		MenuItem viewBooks = new MenuItem("View");
		MenuItem sellBooks = new MenuItem("Checkout");
		Menu ordersMenu = new Menu("Purchase");
		MenuItem newBook = new MenuItem("New");
		MenuItem existingBooks = new MenuItem("Existing");
		Menu salesMenu = new Menu("Sales");
		MenuItem librarianSales = new MenuItem("Librarians");
		MenuItem bookSales = new MenuItem("Books");
		MenuItem totalIncome = new MenuItem("Total");
		Menu profileMenu = new Menu("Profile");
		MenuItem logout = new MenuItem("Log Out");
		Menu usersMenu = new Menu("Users");
		MenuItem registerUser = new MenuItem("Register");
		MenuItem modifyUser = new MenuItem("Modify");

		homeMenu.getItems().add(home);
		booksMenu.getItems().addAll(viewBooks, sellBooks);
		ordersMenu.getItems().addAll(newBook, existingBooks);
		salesMenu.getItems().addAll(librarianSales, bookSales, totalIncome);
		profileMenu.getItems().addAll(logout, usersMenu);
		usersMenu.getItems().addAll(registerUser,modifyUser);

		MenuBar mb = new MenuBar();

		mb.getMenus().add(homeMenu);
		mb.getMenus().add(booksMenu);
		mb.getMenus().add(ordersMenu);
		mb.getMenus().add(salesMenu);
		mb.getMenus().add(profileMenu);

		if(currentuser.getAccesslevel() < 3){
			usersMenu.setVisible(false);
			totalIncome.setVisible(false);

		}
		if(currentuser.getAccesslevel() < 2){
			salesMenu.setVisible(false);
			ordersMenu.setVisible(false);
			homeMenu.setVisible(false);
		}

		home.setOnAction(e -> {
			switchScene(createHomeScene(),"BookStore");
		});
		newBook.setOnAction(e -> {
			switchScene(createBookBuyingScene(),"Order");
		});
		existingBooks.setOnAction(e -> {
			switchScene(createExistingBuyScene(),"Restock");
		});
		librarianSales.setOnAction(e -> {
			switchScene(createLibrarianRecordsScene(),"Librarian Records");
		});
		bookSales.setOnAction(e -> {
			switchScene(createBookRecordScene(),"Book Records");
		});
		totalIncome.setOnAction(e -> {
			switchScene(createTotalExpensesScene(),"Income & Expenses");
		});
		sellBooks.setOnAction(e -> {
			switchScene(createLibrarianScene(),"Order Checkout");
		});
		viewBooks.setOnAction(e -> {
			switchScene(createBookViewScene(),"Book Inventory");
		});
		logout.setOnAction(e -> {
			switchScene(createLoginScene(),"User Log In");
		});
		registerUser.setOnAction(e -> {
			switchScene(createRegisterUserScene(),"Register User");
		});
		modifyUser.setOnAction(e -> {
			switchScene(createModifyUserScene(),"Modify User");
		});

		return mb;
	}

	public Scene createBookBuyingScene() {
		BookController tempBookController = new BookController(new File("Files/books.dat"));
		OrderController tempOrderController = new OrderController(new File("Files/orders.dat"));
		VBox mainpane = new VBox();
		mainpane.setPadding(new Insets(0, 10, 20, 10));
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(10, 10, 0, 10));
		pane.setVgap(10);
		pane.setHgap(10);

		MenuBar mb = MenuCreator();

		TextField titleTF = new TextField();
		Label titleLbl = new Label("Title:");

		TextField isbnTF = new TextField();
		Label isbnLbl = new Label("ISBN 13:");
		TextField quantityTF = new TextField();
		quantityTF.setMaxWidth(35);
		Label quantityLbl = new Label("Quantity:");

		TextField SpriceTF = new TextField();
		SpriceTF.setMaxWidth(100);
		Label SpriceLbl = new Label("Selling Price:");
		TextField BpriceTF = new TextField();
		BpriceTF.setMaxWidth(100);
		Label BpriceLbl = new Label("Buying Price:");

		Label versionLbl = new Label("Version");
		RadioButton rbPaperback = new RadioButton("Paperback");
		RadioButton rbEbook = new RadioButton("E-book");
		ToggleGroup group = new ToggleGroup();
		rbPaperback.setToggleGroup(group);
		rbEbook.setToggleGroup(group);
		HBox hbox = new HBox(10);
		hbox.getChildren().addAll(rbPaperback, rbEbook);

		Label descriptionLbl = new Label("Description");
		TextArea descriptionTA = new TextArea();
		descriptionTA.setPrefColumnCount(20);
		descriptionTA.setPrefRowCount(5);
		descriptionTA.setWrapText(true);

		Author[] authors = tempBookController.getAvailableAuthors();
		ComboBox<Author> cbo = new ComboBox<>();
		cbo.getItems().addAll(authors);
		Label authosLbl = new Label("Select an author: ");

		VBox paneForGenres = new VBox(10);
		paneForGenres.setPadding(new Insets(4));
		ArrayList<CheckBox> genreCheckboxes = new ArrayList<>();
		for (Genre g : Genre.values()) {
			genreCheckboxes.add(new CheckBox(g.toString()));
		}
		paneForGenres.getChildren().addAll(genreCheckboxes);
		Label genreLbl = new Label("Genres: ");

		Button submitBtn = new Button("Submit");
		submitBtn.setOnAction(e -> {
			try {
				String isbn13 = isbnTF.getText();
				String title = titleTF.getText();
				float sprice = Float.parseFloat(SpriceTF.getText());
				float bprice = Float.parseFloat(BpriceTF.getText());
				int quantity = Integer.parseInt(quantityTF.getText());
				String description = descriptionTA.getText();
				Author author = cbo.getValue();
				boolean isPaperback = rbPaperback.isSelected();
				Book newBook = new Book(isbn13, title, description, sprice, bprice, quantity, author, isPaperback);
				neworder = new Order(tempOrderController.calculateIdNumber(), tempOrderController.orderDate(),
						currentuser.getName(), newBook.recordToString());
				float total = tempOrderController.calculateTotal(newBook,
						Integer.parseInt(quantityTF.getText()));
				neworder.addTotal(total);
				neworder.addQuantity(Integer.parseInt(quantityTF.getText()));
				for (int i = 0; i < genreCheckboxes.size(); i++) {
					if (genreCheckboxes.get(i).isSelected())
						newBook.addGenre(Genre.values()[i]);
				}
				boolean res = tempBookController.writeBooktoFile(newBook);
				tempOrderController.writeOrdertoFile(neworder);
				if (res)
					System.out.println("Book saved sucessfully");
				else
					System.out.println("Failure");
			} catch (Exception ex) {
				System.out.print("Incorrect Format or Empty field.");
			}

		});

		pane.add(titleLbl, 0, 0);
		pane.add(titleTF, 1, 0);
		pane.add(isbnLbl, 0, 1);
		pane.add(isbnTF, 1, 1);
		pane.add(quantityLbl, 0, 2);
		pane.add(quantityTF, 1, 2);
		pane.add(SpriceLbl, 0, 3);
		pane.add(SpriceTF, 1, 3);
		pane.add(BpriceLbl, 0, 4);
		pane.add(BpriceTF, 1, 4);
		pane.add(versionLbl, 0, 5);
		pane.add(hbox, 1, 5);
		pane.add(descriptionLbl, 0, 6);
		pane.add(descriptionTA, 1, 6);
		pane.add(authosLbl, 0, 7);
		pane.add(cbo, 1, 7);
		pane.add(genreLbl, 0, 8);
		pane.add(paneForGenres, 1, 8);
		pane.add(submitBtn, 1, 9);
		mainpane.getChildren().add(mb);
		mainpane.getChildren().add(pane);

		Scene scene = new Scene(mainpane, 500, 500);
		return scene;
	}

	public Scene createHomeScene() {
		BookController tempBookController = new BookController(new File("Files/books.dat"));
		VBox mainpane = new VBox();
		mainpane.setPadding(new Insets(0, 0, 20, 0));
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(10, 10, 0, 10));
		pane.setVgap(10);
		pane.setHgap(10);

		MenuBar mb = MenuCreator();

		Label bookLabel = new Label("Running out of:");
		TextArea listTA = new TextArea();
		listTA.setPrefColumnCount(30);
		listTA.setEditable(false);
		for (BookInterface book : tempBookController.getBookList()) {
			if (book.getQuantity() < 5) {
				listTA.setText(listTA.getText() + book.StocktoString());
			}
		}

		mainpane.getChildren().add(mb);
		pane.add(bookLabel, 0, 0);
		pane.add(listTA, 0, 1);
		mainpane.getChildren().add(pane);

		Scene scene = new Scene(mainpane, 400, 300);
		return scene;
	}

	public Scene createExistingBuyScene() {

		BookController tempBookController = new BookController(new File("Files/books.dat"));
		OrderController tempOrderController = new OrderController(new File("Files/orders.dat"));

		VBox mainpane = new VBox();
		GridPane gridpane1 = new GridPane();
		GridPane gridpane2 = new GridPane();
		gridpane1.setPadding(new Insets(10, 10, 10, 10));
		gridpane1.setVgap(10);
		gridpane1.setHgap(10);
		gridpane2.setPadding(new Insets(10, 10, 10, 10));
		gridpane2.setVgap(10);
		gridpane2.setHgap(10);

		MenuBar mb = MenuCreator();

		ArrayList<BookInterface> booklist = tempBookController.getBookList();
		ComboBox<BookInterface> bookComboBox = new ComboBox<>();
		bookComboBox.getItems().addAll(booklist);
		Label titleLbl = new Label("Book Name:");

		TextField isbnTF = new TextField();
		Label isbnLbl = new Label("Isbn:");

		TextField quantityTF = new TextField();
		quantityTF.setMaxWidth(35);
		Label quantityLbl = new Label("Quantity:");
		Label orderLbl = new Label("Order:");
		TextArea orderTA = new TextArea();
		orderTA.setEditable(false);
		orderTA.setPrefColumnCount(25);
		orderTA.setPrefRowCount(2);

		HBox orderBox1 = new HBox();
		orderBox1.setPadding(new Insets(10, 10, 5, 10));
		orderBox1.getChildren().add(orderLbl);

		HBox orderBox2 = new HBox();
		orderBox2.setPadding(new Insets(10, 10, 10, 10));
		orderBox2.getChildren().add(orderTA);

		Button buyButton = new Button("Buy");
		Button newButton = new Button("New");

		bookComboBox.setOnAction(e -> {
			if (bookComboBox.getValue() != null)
				isbnTF.setText(bookComboBox.getValue().getIsbn13());
		});
		isbnTF.setOnAction(e -> {
			for (int i = 0; i < booklist.size(); i++) {
				try {
					if (isbnTF.getText().matches(booklist.get(i).getIsbn13())) {
						bookComboBox.setValue(booklist.get(i));
					} else {
						bookComboBox.setValue(null);
						throw new Exception("No results found, book does not exist!");
					}
				} catch (Exception ex) {
					System.out.print(ex + "\n");
				}
			}
		});

		newButton.setOnAction(e -> {
			try {
				neworder = new Order(tempOrderController.calculateIdNumber(), tempOrderController.orderDate(),
						currentuser.getName(), bookComboBox.getValue().recordToString());
				float total = tempOrderController.calculateTotal(bookComboBox.getValue(),
						Integer.parseInt(quantityTF.getText()));
				neworder.addTotal(total);
				neworder.addQuantity(Integer.parseInt(quantityTF.getText()));
				orderTA.setText(neworder.toString());

			} catch (Exception ex) {
				System.out.print("Action did not happen");
			}
		});

		buyButton.setOnAction(e -> {
			try {
				tempOrderController.writeOrdertoFile(neworder);
				bookComboBox.getValue().addQuantity(Integer.parseInt(quantityTF.getText()));
				tempBookController.Overwrite();
			} catch (Exception ex) {
				System.out.print("\n" + ex);
			}
		});

		gridpane1.add(titleLbl, 0, 0);
		gridpane1.add(bookComboBox, 1, 0);
		gridpane1.add(isbnLbl, 0, 1);
		gridpane1.add(isbnTF, 1, 1);
		gridpane1.add(quantityLbl, 0, 2);
		gridpane1.add(quantityTF, 1, 2);
		mainpane.getChildren().add(mb);
		mainpane.getChildren().add(gridpane1);
		mainpane.getChildren().add(orderBox1);
		mainpane.getChildren().add(orderBox2);
		gridpane2.add(newButton, 0, 0);
		gridpane2.add(buyButton, 1, 0);
		mainpane.getChildren().add(gridpane2);

		Scene scene = new Scene(mainpane, 400, 300);
		return scene;
	}

	public Scene createLibrarianRecordsScene() {

		BillController tempBillController = new BillController(new File("Files/bills.dat"));
		UserController tempUserController = new UserController(new File("Files/users.dat"));

		VBox mainpane = new VBox();
		GridPane gridpane1 = new GridPane();
		GridPane gridpane3 = new GridPane();
		gridpane1.setPadding(new Insets(10, 10, 10, 10));
		gridpane1.setVgap(10);
		gridpane1.setHgap(10);
		gridpane3.setPadding(new Insets(10, 10, 10, 10));
		gridpane3.setVgap(10);
		gridpane3.setHgap(10);

		MenuBar mb = MenuCreator();

		Label startDate = new Label("Start Date:");
		TextField sDay = new TextField();
		sDay.setMaxWidth(30);
		TextField sMonth = new TextField();
		sMonth.setMaxWidth(30);
		TextField sYear = new TextField();
		sYear.setMaxWidth(45);
		Label format1 = new Label("Day, Month, Year");
		Label endDate = new Label("End Date");
		TextField eDay = new TextField();
		eDay.setMaxWidth(30);
		TextField eMonth = new TextField();
		eMonth.setMaxWidth(30);
		TextField eYear = new TextField();
		eYear.setMaxWidth(45);
		Label format2 = new Label("Day, Month, Year");

		TextArea recordsTA = new TextArea();
		recordsTA.setEditable(false);
		recordsTA.setPrefColumnCount(25);
		recordsTA.setPrefRowCount(5);
		String attributes = ("User \tBills\tBooks Sold\t\tTotal\n");
		recordsTA.setText(attributes);

		HBox recordBox = new HBox();
		recordBox.setPadding(new Insets(10, 10, 10, 10));
		recordBox.getChildren().add(recordsTA);

		Label total = new Label("Total");
		Label totaltxt = new Label("");

		Button showButton = new Button("Show Records");
		Button showAllButton = new Button("Show All");

		sDay.setOnAction(e -> {
			sMonth.requestFocus();
		});
		sMonth.setOnAction(e -> {
			sYear.requestFocus();
		});
		sYear.setOnAction(e -> {
			eDay.requestFocus();
		});
		eDay.setOnAction(e -> {
			eMonth.requestFocus();
		});
		eMonth.setOnAction(e -> {
			eYear.requestFocus();
		});

		showButton.setOnAction(e -> {
			try {
				int sday = Integer.parseInt(sDay.getText());
				int smonth = Integer.parseInt(sMonth.getText());
				int syear = Integer.parseInt(sYear.getText());
				int eday = Integer.parseInt(eDay.getText());
				int emonth = Integer.parseInt(eMonth.getText());
				int eyear = Integer.parseInt(eYear.getText());

				String records = tempBillController.printLibrarianRecords(
						tempBillController.setDate(sday, smonth, syear),
						tempBillController.setDate(eday, emonth, eyear), tempUserController.getUserList());
				recordsTA.setText(attributes + records);
				totaltxt.setText(tempBillController.totalRecords());
			} catch (Exception ex) {
				System.out.print(ex);
			}
		});

		showAllButton.setOnAction(e -> {
			try {
				String records = tempBillController.printLibrarianRecords(
						tempBillController.setDate(1, 1, 2000),
						tempBillController.setDate(1, 1, 3000), tempUserController.getUserList());
				recordsTA.setText(attributes + records);
				totaltxt.setText(tempBillController.totalRecords());
			} catch (Exception ex) {
				System.out.print(ex);
			}
		});

		gridpane1.addRow(0, startDate, sDay, sMonth, sYear, format1);
		gridpane1.addRow(1, endDate, eDay, eMonth, eYear, format2);
		gridpane3.addRow(0, total, totaltxt);
		gridpane3.addRow(1, showButton, showAllButton);

		mainpane.getChildren().add(mb);
		mainpane.getChildren().add(gridpane1);
		mainpane.getChildren().add(recordBox);
		mainpane.getChildren().add(gridpane3);

		Scene scene = new Scene(mainpane, 400, 300);
		return scene;
	}

	public Scene createBookRecordScene() {

		BillController tempBillController = new BillController(new File("Files/bills.dat"));
		OrderController tempOrderController = new OrderController(new File("Files/orders.dat"));

		VBox mainpane = new VBox();
		GridPane gridpane1 = new GridPane();
		GridPane gridpane3 = new GridPane();
		gridpane1.setPadding(new Insets(10, 10, 10, 10));
		gridpane1.setVgap(10);
		gridpane1.setHgap(10);
		gridpane3.setPadding(new Insets(10, 10, 10, 10));
		gridpane3.setVgap(10);
		gridpane3.setHgap(10);

		MenuBar mb = MenuCreator();

		Label startDate = new Label("Start Date:");
		TextField sDay = new TextField();
		sDay.setMaxWidth(30);
		TextField sMonth = new TextField();
		sMonth.setMaxWidth(30);
		TextField sYear = new TextField();
		sYear.setMaxWidth(45);
		Label format1 = new Label("Day, Month, Year");
		Label endDate = new Label("End Date");
		TextField eDay = new TextField();
		eDay.setMaxWidth(30);
		TextField eMonth = new TextField();
		eMonth.setMaxWidth(30);
		TextField eYear = new TextField();
		eYear.setMaxWidth(45);
		Label format2 = new Label("Day, Month, Year");

		TextArea booksboughtTA = new TextArea();
		booksboughtTA.setEditable(false);
		booksboughtTA.setPrefColumnCount(30);
		booksboughtTA.setPrefRowCount(5);
		String attributes1 = ("Books Bought:\n");
		booksboughtTA.setText(attributes1);

		HBox booksboughtBox = new HBox();
		booksboughtBox.setPadding(new Insets(10, 10, 10, 10));
		booksboughtBox.getChildren().add(booksboughtTA);

		TextArea bookssoldTA = new TextArea();
		bookssoldTA.setEditable(false);
		bookssoldTA.setPrefColumnCount(30);
		bookssoldTA.setPrefRowCount(5);
		String attributes2 = ("Books Sold:\n");
		bookssoldTA.setText(attributes2);

		HBox bookssoldBox = new HBox();
		bookssoldBox.setPadding(new Insets(10, 10, 10, 10));
		bookssoldBox.getChildren().add(bookssoldTA);

		Button showButton = new Button("Show Records");
		Button showAllButton = new Button("Show All");

		sDay.setOnAction(e -> {
			sMonth.requestFocus();
		});
		sMonth.setOnAction(e -> {
			sYear.requestFocus();
		});
		sYear.setOnAction(e -> {
			eDay.requestFocus();
		});
		eDay.setOnAction(e -> {
			eMonth.requestFocus();
		});
		eMonth.setOnAction(e -> {
			eYear.requestFocus();
		});

		showButton.setOnAction(e -> {
			try {
				int sday = Integer.parseInt(sDay.getText());
				int smonth = Integer.parseInt(sMonth.getText());
				int syear = Integer.parseInt(sYear.getText());
				int eday = Integer.parseInt(eDay.getText());
				int emonth = Integer.parseInt(eMonth.getText());
				int eyear = Integer.parseInt(eYear.getText());

				String records1 = tempOrderController.printBookRecords(
						tempOrderController.setDate(sday, smonth, syear),
						tempOrderController.setDate(eday, emonth, eyear));
				booksboughtTA.setText(attributes1 + records1);

				String records2 = tempBillController.printBookRecords(
						tempBillController.setDate(sday, smonth, syear),
						tempBillController.setDate(eday, emonth, eyear));
				bookssoldTA.setText(attributes2 + records2);

			} catch (Exception ex) {
				System.out.print(ex);
			}
		});

		showAllButton.setOnAction(e -> {
			try {
				String records1 = tempOrderController.printBookRecords(
						tempOrderController.setDate(1, 1, 2000),
						tempOrderController.setDate(1, 1, 2050));
				booksboughtTA.setText(attributes1 + records1);
				String records2 = tempBillController.printBookRecords(
						tempBillController.setDate(1, 1, 2000),
						tempBillController.setDate(1, 1, 2050));
				bookssoldTA.setText(attributes2 + records2);
			} catch (Exception ex) {
				System.out.print(ex);
			}
		});

		gridpane1.addRow(0, startDate, sDay, sMonth, sYear, format1);
		gridpane1.addRow(1, endDate, eDay, eMonth, eYear, format2);
		gridpane3.addRow(0, showButton, showAllButton);

		mainpane.getChildren().add(mb);
		mainpane.getChildren().add(gridpane1);
		mainpane.getChildren().add(booksboughtBox);
		mainpane.getChildren().add(bookssoldBox);
		mainpane.getChildren().add(gridpane3);

		Scene scene = new Scene(mainpane, 400, 400);
		return scene;
	}

	public Scene createLibrarianScene() {

		BookController tempBookController = new BookController(new File("Files/books.dat"));
		BillController tempBillController = new BillController(new File("Files/bills.dat"));

		VBox mainpane = new VBox();
		GridPane gridpane1 = new GridPane();
		GridPane gridpane2 = new GridPane();
		gridpane1.setPadding(new Insets(10, 10, 10, 10));
		gridpane1.setVgap(10);
		gridpane1.setHgap(10);
		gridpane2.setPadding(new Insets(10, 10, 10, 10));
		gridpane2.setVgap(10);
		gridpane2.setHgap(10);

		MenuBar mb = MenuCreator();

		ArrayList<BookInterface> booklist = tempBookController.getBookList();
		ComboBox<BookInterface> bookComboBox = new ComboBox<>();
		bookComboBox.getItems().addAll(booklist);
		Label titleLbl = new Label("Book Name:");

		TextField isbnTF = new TextField();
		Label isbnLbl = new Label("Isbn:");

		TextField quantityTF = new TextField();
		quantityTF.setMaxWidth(35);
		Label quantityLbl = new Label("Quantity:");

		Label billLbl = new Label("Bill:");

		TextArea billTA = new TextArea();
		billTA.setPrefColumnCount(30);
		billTA.setPrefRowCount(10);
		billTA.setWrapText(true);
		billTA.setEditable(false);

		HBox billBox1 = new HBox();
		billBox1.setPadding(new Insets(10, 10, 5, 10));
		billBox1.getChildren().add(billLbl);

		HBox billBox2 = new HBox();
		billBox2.setPadding(new Insets(10, 10, 10, 10));
		billBox2.getChildren().add(billTA);

		Button createBillBtn = new Button("New");
		Button addBookBtn = new Button("Add Book");
		Button submitBtn = new Button("Submit");
		addBookBtn.setVisible(false);
		submitBtn.setVisible(false);

		bookComboBox.setOnAction(e -> {
			if (bookComboBox.getValue() != null)
				isbnTF.setText(bookComboBox.getValue().getIsbn13());
		});
		isbnTF.setOnAction(e -> {
			for (int i = 0; i < booklist.size(); i++) {
				try {
					if (isbnTF.getText().matches(booklist.get(i).getIsbn13())) {
						bookComboBox.setValue(booklist.get(i));
					} else {
						bookComboBox.setValue(null);
						throw new Exception("No results found, book does not exist!");
					}
				} catch (Exception ex) {
					System.out.print(ex + "\n");
				}
			}
		});
		createBillBtn.setOnAction(e -> {
			newbill = new Bill(tempBillController.calculateIdNumber(), tempBillController.billDate(),
					currentuser.getName());
			billTA.setText(newbill.getBillText());
			addBookBtn.setVisible(true);
			submitBtn.setVisible(true);

		});

		addBookBtn.setOnAction(e -> {
			try {
				if (bookComboBox.getValue().getQuantity() >= Integer.parseInt(quantityTF.getText())) {
					String booktranslated = tempBillController.billtoString(bookComboBox.getValue(),
							Integer.parseInt(quantityTF.getText()));
					newbill.addBillText(booktranslated);
					newbill.addBook(bookComboBox.getValue().recordToString());
					newbill.addTotal(bookComboBox.getValue().getSPrice(), Integer.parseInt(quantityTF.getText()));
					System.out.print(newbill.getTotal());
					billTA.setText(newbill.getBillText());
					bookComboBox.getValue().addQuantity(-Integer.parseInt(quantityTF.getText()));
					;
				} else {
					throw new OutOfStockException();
				}
			} catch (Exception ex) {
				System.out.print(ex + "\n");
			}

		});
		submitBtn.setOnAction(e -> {
			try {
				tempBillController.writeBilltoFile(newbill);
				tempBillController.writeBilltoTxt(newbill);
				tempBookController.Overwrite();
			} catch (Exception ex) {
				System.out.print("Incorrect Format or Empty field.");
			}

		});

		gridpane1.add(titleLbl, 0, 0);
		gridpane1.add(bookComboBox, 1, 0);
		gridpane1.add(isbnLbl, 0, 1);
		gridpane1.add(isbnTF, 1, 1);
		gridpane1.add(quantityLbl, 0, 2);
		gridpane1.add(quantityTF, 1, 2);
		mainpane.getChildren().add(mb);
		mainpane.getChildren().add(gridpane1);
		mainpane.getChildren().add(billBox1);
		mainpane.getChildren().add(billBox2);
		gridpane2.add(createBillBtn, 0, 0);
		gridpane2.add(addBookBtn, 1, 0);
		gridpane2.add(submitBtn, 2, 0);
		mainpane.getChildren().add(gridpane2);

		Scene scene = new Scene(mainpane, 390, 400);
		return scene;
	}

	public Scene createRegisterUserScene() {
		UserController tempUserController = new UserController(new File("Files/users.dat"));

		VBox mainpane = new VBox();
		GridPane gridpane1 = new GridPane();
		GridPane gridpane2 = new GridPane();
		GridPane gridpane3 = new GridPane();
		gridpane1.setPadding(new Insets(10, 10, 10, 10));
		gridpane1.setVgap(10);
		gridpane1.setHgap(10);
		gridpane2.setPadding(new Insets(10, 10, 10, 10));
		gridpane2.setVgap(10);
		gridpane2.setHgap(10);
		gridpane3.setPadding(new Insets(10, 10, 10, 10));
		gridpane3.setVgap(10);
		gridpane3.setHgap(10);

		MenuBar mb = MenuCreator();

		Label firstName = new Label("First Name");
		TextField fNameTF = new TextField();
		Label lastName = new Label("Last Name");
		TextField lNameTF = new TextField();
		Label birthday = new Label("Birthday    ");
		TextField day = new TextField();
		day.setMaxWidth(30);
		TextField month = new TextField();
		month.setMaxWidth(30);
		TextField year = new TextField();
		year.setMaxWidth(45);
		Label email = new Label("Email");
		TextField emailTF = new TextField();
		Label phone = new Label("Phone No ");
		TextField phoneTF = new TextField();
		Label salary = new Label("Salary");
		TextField salaryTF = new TextField();
		Label role = new Label("Role");
		TextField roleTF = new TextField();
		Label password = new Label("Password");
		TextField passwordTF = new TextField();

		Button createButton = new Button("Register");

		createButton.setOnAction(e -> {
			Calendar cal = Calendar.getInstance();
			int yearint = Integer.parseInt(year.getText());
			int monthint = Integer.parseInt(month.getText()) - 1;
			int dayint = Integer.parseInt(day.getText());
			cal.set(yearint, monthint , dayint);
			tempUserController.registerUser(new User(fNameTF.getText(), lNameTF.getText(), cal, phoneTF.getText(),
					emailTF.getText(), Integer.parseInt(salaryTF.getText()), Role.valueOf(roleTF.getText()),
					passwordTF.getText()) {
			});
		});

		HBox dateBox = new HBox();
		GridPane dategrid = new GridPane();
		dategrid.setPadding(new Insets(10, 10, 10, 10));
		dategrid.setVgap(10);
		dategrid.setHgap(10);
		dategrid.addRow(0, birthday, day, month, year);
		dateBox.getChildren().addAll(dategrid);

		gridpane1.addRow(0, firstName, fNameTF);
		gridpane1.addRow(1, lastName, lNameTF);
		gridpane2.addRow(0, email, emailTF);
		gridpane2.addRow(1, phone, phoneTF);
		gridpane2.addRow(2, salary, salaryTF);
		gridpane2.addRow(3, role, roleTF);
		gridpane2.addRow(4, password, passwordTF);
		gridpane3.addRow(0, createButton);

		mainpane.getChildren().add(mb);
		mainpane.getChildren().add(gridpane1);
		mainpane.getChildren().add(dateBox);
		mainpane.getChildren().add(gridpane2);
		mainpane.getChildren().add(gridpane3);

		Scene scene = new Scene(mainpane, 400, 400);
		return scene;
	}

	public Scene createModifyUserScene() {
		UserController tempUserController = new UserController(new File("Files/users.dat"));

		VBox mainpane = new VBox();
		GridPane gridpane1 = new GridPane();
		GridPane gridpane2 = new GridPane();
		gridpane1.setPadding(new Insets(10, 10, 10, 10));
		gridpane1.setVgap(10);
		gridpane1.setHgap(10);
		gridpane2.setPadding(new Insets(10, 10, 10, 10));
		gridpane2.setVgap(10);
		gridpane2.setHgap(10);

		MenuBar mb = MenuCreator();

		Label user = new Label("User");
		ArrayList<UserInterface> userlist = tempUserController.getUserList();
		ComboBox<UserInterface> userComboBox = new ComboBox<>();
		userComboBox.getItems().addAll(userlist);

		Label email = new Label("Email");
		TextField emailTF = new TextField();
		Label phone = new Label("Phone No ");
		TextField phoneTF = new TextField();
		Label salary = new Label("Salary");
		TextField salaryTF = new TextField();
		Label role = new Label("Role");
		TextField roleTF = new TextField();
		Label password = new Label("Password");
		TextField passwordTF = new TextField();
		Label accessLevel = new Label("Access Level");
		TextField accessLevelTF = new TextField();

		Button saveButton = new Button("Save");
		Button deleteButton = new Button("Delete");

		userComboBox.setOnAction(e -> {
			emailTF.setText(userComboBox.getValue().getEmail());
			phoneTF.setText(userComboBox.getValue().getPhone());
			salaryTF.setText("" + userComboBox.getValue().getSalary());
			roleTF.setText(userComboBox.getValue().getRole().toString());
			passwordTF.setText(userComboBox.getValue().getPassword());
			accessLevelTF.setText("" + userComboBox.getValue().getAccesslevel());
			if(userComboBox.getValue().getName().matches(currentuser.getName()) && userComboBox.getValue().getLastName().matches(currentuser.getLastName()) ){
				deleteButton.setDisable(true);
			}
			else{
				deleteButton.setDisable(false);
			}
		});

		saveButton.setOnAction(e -> {
			try {

				userComboBox.getValue().setEmail(emailTF.getText());
				userComboBox.getValue().setPhone(phoneTF.getText());
				userComboBox.getValue().setSalary(Integer.parseInt(salaryTF.getText()));
				userComboBox.getValue().setRole(Role.valueOf(roleTF.getText()));
				userComboBox.getValue().setPassword(passwordTF.getText());
				userComboBox.getValue().setAccesslevel(Integer.parseInt(accessLevelTF.getText()));
				tempUserController.Overwrite();
			} catch (Exception ex) {
				System.out.print(ex);
			}
		});

		deleteButton.setOnAction(e -> {
			tempUserController.deleteUser(userComboBox.getValue());
			userComboBox.getItems().remove(userComboBox.getValue());
		});

		gridpane1.addRow(0, user, userComboBox);
		gridpane1.addRow(1, email, emailTF);
		gridpane1.addRow(2, phone, phoneTF);
		gridpane1.addRow(3, salary, salaryTF);
		gridpane1.addRow(4, role, roleTF);
		gridpane1.addRow(5, password, passwordTF);
		gridpane1.addRow(6, accessLevel, accessLevelTF);
		gridpane2.addRow(0, saveButton, deleteButton);

		mainpane.getChildren().add(mb);
		mainpane.getChildren().add(gridpane1);
		mainpane.getChildren().add(gridpane2);

		Scene scene = new Scene(mainpane, 400, 400);
		return scene;
	}

	public Scene createTotalExpensesScene() {

		BillController tempBillController = new BillController(new File("Files/bills.dat"));
		OrderController tempOrderController = new OrderController(new File("Files/orders.dat"));
		UserController tempUserController = new UserController(new File("Files/users.dat"));

		VBox mainpane = new VBox();
		GridPane gridpane1 = new GridPane();
		GridPane gridpane3 = new GridPane();
		gridpane1.setPadding(new Insets(10, 10, 10, 10));
		gridpane1.setVgap(10);
		gridpane1.setHgap(10);
		gridpane3.setPadding(new Insets(10, 10, 10, 10));
		gridpane3.setVgap(10);
		gridpane3.setHgap(10);

		MenuBar mb = MenuCreator();

		Label startDate = new Label("Start Date:");
		TextField sDay = new TextField();
		sDay.setMaxWidth(30);
		TextField sMonth = new TextField();
		sMonth.setMaxWidth(30);
		TextField sYear = new TextField();
		sYear.setMaxWidth(45);
		Label format1 = new Label("Day, Month, Year");
		Label endDate = new Label("End Date");
		TextField eDay = new TextField();
		eDay.setMaxWidth(30);
		TextField eMonth = new TextField();
		eMonth.setMaxWidth(30);
		TextField eYear = new TextField();
		eYear.setMaxWidth(45);
		Label format2 = new Label("Day, Month, Year");

		TextArea profitTA = new TextArea();
		profitTA.setEditable(false);
		profitTA.setPrefColumnCount(30);
		profitTA.setPrefRowCount(5);
		String attributes1 = ("Income:\nBook Expenses:\nSalary Expenses:\nTotal Expenses:\nProfit:");
		profitTA.setText(attributes1);

		HBox profitBox = new HBox();
		profitBox.setPadding(new Insets(10, 10, 10, 10));
		profitBox.getChildren().add(profitTA);

		Button showButton = new Button("Show Profit");

		sDay.setOnAction(e -> {
			sMonth.requestFocus();
		});
		sMonth.setOnAction(e -> {
			sYear.requestFocus();
		});
		sYear.setOnAction(e -> {
			eDay.requestFocus();
		});
		eDay.setOnAction(e -> {
			eMonth.requestFocus();
		});
		eMonth.setOnAction(e -> {
			eYear.requestFocus();
		});

		showButton.setOnAction(e -> {
			try {
				int sday = Integer.parseInt(sDay.getText());
				int smonth = Integer.parseInt(sMonth.getText());
				int syear = Integer.parseInt(sYear.getText());
				int eday = Integer.parseInt(eDay.getText());
				int emonth = Integer.parseInt(eMonth.getText());
				int eyear = Integer.parseInt(eYear.getText());

				float bookexpenses = tempOrderController.totalBookExpenses(
						tempOrderController.setDate(sday, smonth, syear),
						tempOrderController.setDate(eday, emonth, eyear));


				float income = tempBillController.totalbookIncome(
						tempBillController.setDate(sday, smonth, syear),
						tempBillController.setDate(eday, emonth, eyear));

				float salaryexpenses = tempUserController.getTotalSalaries();
				if(emonth - smonth > 0 && syear == eyear){
					salaryexpenses=salaryexpenses*(emonth-smonth);
				}
				else if(eyear > syear){
					salaryexpenses=salaryexpenses*(emonth+(eyear-syear)*12-smonth);
				}
				float totalexpenses = bookexpenses + salaryexpenses;
				float profit = income - totalexpenses;
				String text = ("Income: "+income+"$\nBook Expenses: "+bookexpenses+"$\nSalary Expenses: "+salaryexpenses+"$\nTotal Expenses: "+totalexpenses+"$\nProfit: "+profit+"$");
				profitTA.setText(text);

			} catch (Exception ex) {
				System.out.print(ex);
			}
		});


		gridpane1.addRow(0, startDate, sDay, sMonth, sYear, format1);
		gridpane1.addRow(1, endDate, eDay, eMonth, eYear, format2);
		gridpane3.addRow(0, showButton);

		mainpane.getChildren().add(mb);
		mainpane.getChildren().add(gridpane1);
		mainpane.getChildren().add(profitBox);
		mainpane.getChildren().add(gridpane3);

		Scene scene = new Scene(mainpane, 400, 400);
		return scene;
	}
	public Scene createBookViewScene(){
		BookController tempBookController = new BookController(new File("Files/books.dat"));
		VBox mainpane = new VBox();
		MenuBar mb = MenuCreator();
		Label bookLbl = new Label("Book Collection");
		TextArea bookTA = new TextArea();
		bookTA.setPrefColumnCount(30);
		bookTA.setPrefRowCount(10);
		bookTA.setWrapText(true);
		bookTA.setEditable(false);
		bookTA.setText(tempBookController.getBookCollection());

		HBox bookBox1 = new HBox();
		bookBox1.setPadding(new Insets(10, 10, 5, 10));
		bookBox1.getChildren().add(bookLbl);

		HBox bookBox2 = new HBox();
		bookBox2.setPadding(new Insets(10, 10, 10, 10));
		bookBox2.getChildren().add(bookTA);

		mainpane.getChildren().add(mb);
		mainpane.getChildren().add(bookBox1);
		mainpane.getChildren().add(bookBox2);

		Scene scene = new Scene(mainpane, 400, 400);
		return scene;
	}
	public void switchScene(Scene nextscene, String title) {
		stage.setScene(nextscene);
		stage.setTitle(title);
	}

}
